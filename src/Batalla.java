/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;
/**
 *
 * @author sebas
 */
public class Batalla {
    int vida1;
    int vida2;
    Pokemon pokemon1;
    Pokemon pokemon2;
    
    int turno=0;
    public Batalla(Pokemon pokemon1, Pokemon pokemon2) {
        this.pokemon1=pokemon1;
        this.pokemon2=pokemon2;
        this.vida1=vida1;
        this.vida2=vida2;
    }
    public int calcularDMG(Movimiento move){
        int multiplicador=1;
        String tipo1=pokemon1.getTipo();
        String tipo2=pokemon2.getTipo();
        if (turno%2==0){
            if((move.getTipo()=="agua")&&(tipo2=="fuego")){
                multiplicador=2;
            }
            else if((move.getTipo()=="planta")&&(tipo2=="agua")){
                multiplicador=2;
            }
            else if((move.getTipo()=="fuego")&&(tipo2=="planta")){
                multiplicador=2;
            }
            else if((move.getTipo()=="volador")&&(tipo2=="planta")){
                multiplicador=2;
            }
            System.out.println("Multiplicador de daño = "+multiplicador);
            System.out.println(((pokemon1.getATK()*move.getDMG()/20)-pokemon2.getDEF())*multiplicador+" de daño");
            return ((pokemon1.getATK()*move.getDMG()/20)-pokemon2.getDEF())*multiplicador;
        }
        else{
            if((move.getTipo()=="agua")&&(tipo1=="fuego")){
                multiplicador=2;
            }
            else if((move.getTipo()=="planta")&&(tipo1=="agua")){
                multiplicador=2;
            }
            else if((move.getTipo()=="fuego")&&(tipo1=="planta")){
                multiplicador=2;
            }
            else if((move.getTipo()=="volador")&&(tipo1=="planta")){
                multiplicador=2;
            }
            System.out.println(((pokemon2.getATK()*move.getDMG()/20)-pokemon1.getDEF())*multiplicador+" de daño");
            return ((pokemon2.getATK()*move.getDMG()/20)-pokemon1.getDEF())*multiplicador;
        }
    }
    public void letsPlay(){
        int vida1=pokemon1.getHP();
        int vida2=pokemon2.getHP();
        java.util.Scanner a= new java.util.Scanner(System.in);
        System.out.println("HP de "+pokemon1.getNombre()+" = "+vida1);
        System.out.println("HP de "+pokemon2.getNombre()+" = "+vida2);
        while((vida1>0)&&(vida2>0)){
            
            System.out.println("Que deberia hacer "+pokemon1.getNombre());
            int i = a.nextInt();
            Movimiento move= pokemon1.getMovimientos()[i-1];
            System.out.println(pokemon1.getNombre()+ " uso "+move.getNombre());
            vida2=vida2-calcularDMG(move);
            if(vida2>0){
                System.out.println("HP de "+pokemon2.getNombre()+" = "+vida1);
            }
            else{
                System.out.println("HP de "+pokemon2.getNombre()+" = 0 ");
            }
            turno++;
            if(vida2<0){
                break;
            }
            System.out.println("Que deberia hacer "+pokemon2.getNombre());
            i = a.nextInt();
            move= pokemon2.getMovimientos()[i-1];
            System.out.println(pokemon2.getNombre()+ " uso "+move.getNombre());
            vida1=vida1-calcularDMG(move);
            if(vida1>0){
                System.out.println("HP de "+pokemon1.getNombre()+" = "+vida1);
            }
            else{
                System.out.println("HP de "+pokemon1.getNombre()+" = 0 ");
            }
            turno++;
        }
        System.out.println("La batalla ha terminado");
        if(vida1<0){
            System.out.println(pokemon2.getNombre()+" Ha ganado");
        }
        if(vida2<0){
            System.out.println(pokemon1.getNombre()+" Ha ganado");
        }
    }
}
