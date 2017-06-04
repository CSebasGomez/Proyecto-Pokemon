
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sebas
 */
public class main{
    public static ArrayList<Pokemon> read() throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader("movimientos.txt"));
        ArrayList<Movimiento> Movimientos= new ArrayList<Movimiento>();
        try {
            
            String line = br.readLine();
            String[] datos;
            while (line != null) {
                datos = line.split(",");
                int DMG = Integer.parseInt(datos[1]);
                Movimientos.add(new Movimiento(datos[0],DMG,datos[2]));
                line = br.readLine();
                
            }
        } finally {
            br.close();
        }    
        BufferedReader brp = new BufferedReader(new FileReader("pokemones.txt"));
        ArrayList<Pokemon> Pokemones= new ArrayList<Pokemon>();
        try {
            String linea = brp.readLine();
            while (linea != null) {
                String[] datos = linea.split(",");
                
                int ID =Integer.parseInt(datos[0]);
                Movimiento[] movimientos= new Movimiento[4];
                for(int i=0;i<Movimientos.size();i++){
                    if(datos[7].equals(Movimientos.get(i).getNombre())){
                        movimientos[0]=Movimientos.get(i);
                    }
                    if(datos[8].equals(Movimientos.get(i).getNombre())){
                        movimientos[1]=Movimientos.get(i);
                    }
                    if(datos[9].equals(Movimientos.get(i).getNombre())){
                        movimientos[2]=Movimientos.get(i);
                    }
                    if(datos[10].equals(Movimientos.get(i).getNombre())){
                        movimientos[3]=Movimientos.get(i);
                    }
                    
                }
                int ATK =Integer.parseInt(datos[4]);
                int DEF =Integer.parseInt(datos[5]);
                int HP =Integer.parseInt(datos[6]);
                Pokemones.add(new Pokemon(ID,datos[1],datos[2],datos[3],ATK,DEF,HP,movimientos[0],movimientos[1],movimientos[2],movimientos[3]));
                linea = brp.readLine();
                
            }

        } finally {
            brp.close();
        }    
        return Pokemones;
    }
    public static ArrayList<Entrenador> readEntrenadores(ArrayList<Pokemon> Pokemones) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader("Entrenadores.txt"));
        ArrayList<ArrayList<Pokemon>> Equipo= new ArrayList<ArrayList<Pokemon>>();
        ArrayList<Entrenador> Entrenadores= new ArrayList<Entrenador>();
        try {
            
            String line = br.readLine();
            String[] datos;
            while (line != null) {
                datos = line.split(",");
                int wins = Integer.parseInt(datos[2]);
                int loses = Integer.parseInt(datos[3]);
                ArrayList<Pokemon> PokeEquipo= new ArrayList<Pokemon>();
                for(int i=4;i<datos.length;i++){
                    for(int j=0;j<Pokemones.size();j++){
                        if(datos[i].equals(Pokemones.get(j).getNombre())){
                            PokeEquipo.add(Pokemones.get(j));
                        }
                    }
                }
                Entrenadores.add(new Entrenador(datos[0],datos[1],wins,loses,PokeEquipo));
                line = br.readLine();
            }
        } finally {
            br.close();
        }
        return Entrenadores;
    }
    
    public static void main(String[] args) throws IOException {
       
        ArrayList<Pokemon> Pokemones = read();
        ArrayList<Entrenador> Entrenadores=readEntrenadores(Pokemones);
        SelecEntrenadores frame = new SelecEntrenadores(Entrenadores);
        frame.PintarEntrenadores();          
    }
}
