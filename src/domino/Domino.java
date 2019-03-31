/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * integrantes: Mayeleen Rodriguez Gomez cod:2016114118 Luis Hatum Clavijo
 */
public class Domino {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MontoFicha main = new MontoFicha();
        Jugador j1 = new Jugador("Maye");
        Jugador j2 = new Jugador("Luis");
        Jugador j3 = new Jugador("Carlos");
        Jugador j4 = new Jugador("Andrea");
        Jugador gano = null;
        main.barajar(j1, j2, j3, j4);
        int suiche = 0;
        int pasadas = 0;
        Scanner teclado = new Scanner(System.in);

        ArrayList<Jugador> turnos = new ArrayList<Jugador>();
        Deque<Ficha> fichas_juego = new LinkedList<Ficha>();
        //para saber quien comienza el juego
        if (j1.eliminar(new Ficha(6, 6))) {
            fichas_juego.add(new Ficha(6, 6));
            turnos.add(j2);
            turnos.add(j3);
            turnos.add(j4);
            turnos.add(j1);
        } else if (j2.eliminar(new Ficha(6, 6))) {
            fichas_juego.add(new Ficha(6, 6));
            turnos.add(j1);
            turnos.add(j3);
            turnos.add(j4);
            turnos.add(j2);
        } else if (j3.eliminar(new Ficha(6, 6))) {
            fichas_juego.add(new Ficha(6, 6));
            turnos.add(j1);
            turnos.add(j2);
            turnos.add(j4);
            turnos.add(j3);
        } else {
            fichas_juego.add(new Ficha(6, 6));
            j4.eliminar(new Ficha(6, 6));
            turnos.add(j1);
            turnos.add(j2);
            turnos.add(j3);
            turnos.add(j4);
        }

        while (suiche == 0) {
            for (Jugador turno : turnos) {
                if (suiche != 0) {
                    break;
                } else {
                    int ficha;
                    int posicion;
                    System.out.println("Es El Turno De:" + turno.getNickname());
                    System.out.println("En La Mesa Hay:");

                    for (Ficha fichaEnJuego : fichas_juego) {
                        System.out.print("[" + fichaEnJuego.getNum1() + "| " + fichaEnJuego.getNum2() + "]");
                    }
                    System.out.println("");
                    System.out.println("Su Mano Es:");
                    turno.fichas();
                    int contador = 0;

                    for (Ficha fichaEnMano : turno.getMano()) {
                        if (fichas_juego.getFirst().getNum1() == fichaEnMano.getNum1() || fichas_juego.getFirst().getNum1() == fichaEnMano.getNum2()) {
                            contador += 1;
                        }
                        if (fichas_juego.getLast().getNum2() == fichaEnMano.getNum1() || fichas_juego.getLast().getNum2() == fichaEnMano.getNum2()) {
                            contador += 1;
                        }
                    }
                    if (contador != 0) {
                        int suiche2 = 0;
                        pasadas = 0;

                        while (suiche2 == 0) {
                            System.out.println("Digite la Ficha que Quiere Colocar");
                            ficha = teclado.nextInt();
                            System.out.println("Digite UNO para Colocar la Ficha a la Izquierda o DOS para Colocarla a la Derecha");
                            posicion = teclado.nextInt();
                            teclado.nextLine();
                            if (turno.poner(fichas_juego, turno.getMano().get(ficha - 1), posicion)) {

                                if (turno.getMano().isEmpty()) {
                                    gano = turno;
                                    suiche++;
                                }
                                suiche2++;
                            }
                        }
                    } else {
                        System.out.println("El Jugador no Tiene Fichas por lo Tanto Pasa el Turno");
                        pasadas++;
                    }
                }
                System.out.println("Final de Turno de " + turno.getNickname());
            }

            if (gano != null) {
                System.out.println("EL GANADOR ES: " + gano.getNickname());
                suiche++;
            }

            if (pasadas >= 4) {
                System.out.println("Todos los Jugadores Pasaron el Juego se Termina");
                int cuenta1 = 0, cuenta2 = 0, cuenta3 = 0, cuenta4 = 0;
                for (Ficha ficha_contada : j1.getMano()) {
                    cuenta1 += (ficha_contada.getNum1() + ficha_contada.getNum2());
                }
                for (Ficha ficha_contada : j2.getMano()) {
                    cuenta2 += (ficha_contada.getNum1() + ficha_contada.getNum2());
                }
                for (Ficha ficha_contada : j3.getMano()) {
                    cuenta3 += (ficha_contada.getNum1() + ficha_contada.getNum2());
                }
                for (Ficha ficha_contada : j4.getMano()) {
                    cuenta4 += (ficha_contada.getNum1() + ficha_contada.getNum2());
                }

                int max = cuenta1;

                if (cuenta2 > max) {
                    max = cuenta2;
                }
                if (cuenta3 > max) {
                    max = cuenta3;
                }
                if (cuenta4 > max) {
                    max = cuenta4;
                }

                if (max == cuenta1) {
                    System.out.println("EL GANADOR ES: " + j1.getNickname());
                }
                if (max == cuenta2) {
                    System.out.println("EL GANADOR ES: " + j2.getNickname());
                }
                if (max == cuenta3) {
                    System.out.println("EL GANADOR ES: " + j3.getNickname());
                }
                if (max == cuenta4) {
                    System.out.println("EL GANADOR ES: " + j4.getNickname());
                }
                suiche++;
            }
        }
    }

}
