
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sebas
 */
public class Entrenador {
    private String nombre;
    private boolean sexo;
    private String personaje;
    private int wins;
    private int loses;
    private ArrayList<Pokemon> pokemones;

    public Entrenador(String nombre,String personaje, int wins, int loses, ArrayList<Pokemon> pokemones) {
        this.nombre = nombre;
        this.wins = wins;
        this.loses = loses;
        this.pokemones = pokemones;
        this.personaje=personaje;
    }

    public ArrayList<Pokemon> getPokemones() {
        return pokemones;
    }
    
    
    public String getNombre() {
        return nombre;
    }
    
    
    public String getPersonaje() {
        return personaje+".png";
    }
    
    
    

    public void showPerfil() {
        System.out.println ( "Entrenador{" + "nombre=" + nombre + ", sexo=" + sexo + ", wins=" + wins + ", loses=" + loses + '}');
    }
    public void showPokemon(){
        for(int i=0;i < pokemones.size();i++){
            System.out.println(pokemones.get(i).getNombre());
        }
    } 
    
    
}
