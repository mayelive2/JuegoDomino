/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino;

import java.util.ArrayList;
import java.util.Deque;

/**
 *integrantes: 
  Mayeleen Rodriguez Gomez cod:2016114118
  Luis Hatum Clavijo
 */
public class Jugador {
    private ArrayList<Ficha>mano;
    private String nickname;

    public Jugador(String nickname) {
        this.mano = new ArrayList<Ficha>();
        this.nickname = nickname;
    }

    public ArrayList<Ficha> getMano() {
        return mano;
    }

    public String getNickname() {
        return nickname;
    }
    
    public boolean eliminar(Ficha borrar){
        boolean retorno = false;
        for(Ficha ficha : this.getMano()){
            if(this.verificar(ficha, borrar)){
                borrar = ficha;
                retorno = true;
            }
        }
        this.getMano().remove(borrar);
        return retorno;
    }
    
    public boolean verificar(Ficha f1, Ficha f2){
        
        if((f1.getNum1() == f2.getNum1()) && (f1.getNum2() == f2.getNum2())){
            return true;
        }else{
            return false;
        }
    }
    
    public void fichas(){
        int i = 1;
        for(Ficha ficha : this.mano){
            System.out.print(" "+i+".["+ficha.getNum2()+", "+ficha.getNum1()+"]");
            i++;
        }
        System.out.println("");
    }
    
    public boolean poner(Deque<Ficha> juego, Ficha ficha, int posicion){
        boolean retorno = false;
        if(posicion == 2){
            if(juego.getLast().getNum2() == ficha.getNum1()){
                juego.addLast(ficha);
                this.eliminar(ficha);
                retorno = true;
            }else if(juego.getLast().getNum2() == ficha.getNum2()){
                juego.addLast(new Ficha(ficha.getNum2(),ficha.getNum1()));
                this.eliminar(ficha);
                retorno = true;
            }else{
                System.out.println("Posicion Incorrecta De La Ficha");
                retorno = false;
            }
        }else if(posicion == 1){
            if(juego.getFirst().getNum1() == ficha.getNum1()){
                juego.addFirst(new Ficha(ficha.getNum2(),ficha.getNum1()));
                this.eliminar(ficha);
                retorno = true;
            }else if(juego.getFirst().getNum1() == ficha.getNum2()){
                juego.addFirst(ficha);
                this.eliminar(ficha);
                retorno = true;
            }else{
                System.out.println("Posicion Incorrecta De La Ficha");
                retorno = false;
            }
        }
        return retorno;
    }
}
