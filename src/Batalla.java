/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author sebas
 */
public class Batalla extends JFrame {
    int vida1;
    int vida2;
    Pokemon pokemon1;
    Pokemon pokemon2;
    int turno=0;
    int mov=-1;
    int fase=0;
    int fasefin=0;
    Entrenador entrenador1;
    Entrenador entrenador2;
    Entrenador ganador;
    Entrenador perdedor;
    
    public Batalla(Pokemon pokemon1, Pokemon pokemon2,Entrenador Entrenador1,Entrenador Entrenador2) {
        this.pokemon1=pokemon1;
        this.pokemon2=pokemon2;
        this.vida1=pokemon1.getHP();
        this.vida2=pokemon2.getHP();
        this.entrenador1=Entrenador1;
        this.entrenador2=Entrenador2;
        add(new PanelBatalla(pokemon1,pokemon2));
    }
    public void letsPlay(){
            Batalla bt = new Batalla(pokemon1,pokemon2,entrenador1,entrenador2);
            bt.setTitle("Pokemon Amethyst- Batalla");
            bt.setSize(900,735);
            bt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            bt.setVisible(true);
        }
    
    
    public class PanelBatalla extends JPanel implements ActionListener {
        Timer timer;
        
        public PanelBatalla(Pokemon pokemon1,Pokemon pokemon2){
            timer = new Timer(25,this);
            timer.start();
            addKeyListener(new TAdapter());
            setFocusable(true);
            
        }

        
        private class TAdapter extends KeyAdapter{
            Graphics u;
            @Override
            public void keyPressed(KeyEvent e){
                int key = e.getKeyCode();
               
                    if((key==KeyEvent.VK_1)&&(fase==0)){
                        mov=0;
                        fase++;
                    }
                    if((key==KeyEvent.VK_2)&&(fase==0)){
                       mov =1;
                       fase++;
                    }
                    if((key==KeyEvent.VK_3)&&(fase==0)){
                        mov =2;
                        fase++;
                    }
                    if((key==KeyEvent.VK_4)&&(fase==0)){
                        mov=3;
                        fase++;
                    }
                
                if(key==KeyEvent.VK_ENTER){
                    if((vida1)>0 &&(vida2)>0){
                        if((mov<5)&&(mov>-1)){
                            fase++;
                        }
                    }
                    else{
                        fasefin++;
                    }
                }
            }
        }
        
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            Image fondoBatalla = loadImagen("Fondo.png");
            
            Image barra = loadImagen("Barra Combate.png");
            
            g.setColor(Color.white);
            Font fuente=new Font("Dialog", Font.BOLD, 36);
            
            if((vida1)>0 &&(vida2)>0){
                g.drawImage(fondoBatalla, 0, 0, null);
                g.drawImage(barra, 0, 530, null);
                Image poke1 = loadImagen(pokemon1.getNombre()+"-Espalda.png");  
                g.drawImage(poke1, 0, 220, this);
                Image poke2 = loadImagen(pokemon2.getNombre()+"-Frente.png");
                g.drawImage(poke2, 530, 105, this);
                g.drawImage(barra, 0, 530, null);
                if (turno%2==0){
                    if(fase==0){
                        g.drawString("¿Qué debería hacer "+pokemon1.getNombre()+" ?", 100, 590);
                        g.drawString("Oprima el numero del ataque que desea", 100, 615);
                        g.setColor(Color.black);
                        g.drawString("[1]"+pokemon1.getMovimientos()[0].getNombre()+".", 605, 590);
                        g.drawString("[2]"+pokemon1.getMovimientos()[1].getNombre()+".", 725, 590);
                        g.drawString("[3]"+pokemon1.getMovimientos()[2].getNombre()+".", 605, 645);
                        g.drawString("[4]"+pokemon1.getMovimientos()[3].getNombre()+".", 725, 645);
                    }
                    if(fase==3){
                        fase=0;
                        turno++;
                    }
                    if(fase==2){
                       g.drawString("la vida de "+pokemon2.getNombre()+" ha sido reducida a "+vida2,100,590);
                    }
                    if(fase==1){
                       vida2=vida2-calcularDMG(pokemon1.getMovimientos()[mov]);
                       fase++;
                    }
                    

                } 
                else{
                    if(fase==0){
                        g.drawString("¿Qué debería hacer "+pokemon2.getNombre()+" ?", 100, 590);
                        g.drawString("Oprima el numero del ataque que desea", 100, 615);
                        g.setColor(Color.black);
                        g.drawString("[1]"+pokemon2.getMovimientos()[0].getNombre()+".", 605, 590);
                        g.drawString("[2]"+pokemon2.getMovimientos()[1].getNombre()+".", 725, 590);
                        g.drawString("[3]"+pokemon2.getMovimientos()[2].getNombre()+".", 605, 645);
                        g.drawString("[4]"+pokemon2.getMovimientos()[3].getNombre()+".", 725, 645);
                    }
                    if(fase==1){
                       vida1=vida1-calcularDMG(pokemon2.getMovimientos()[mov]);
                       fase++;
                    }
                    if(fase==2){
                       g.drawString("la vida de "+pokemon1.getNombre()+" ha sido reducida a "+vida1,100,590);
                    }
                    if(fase==3){
                        fase=0;
                        turno++;
                    }
                }
            }
            else{
                if (fasefin==2){
                    try {
                        addWin(ganador,perdedor);
                    } catch (IOException ex) {
                        System.out.println("No hay ganador");
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        System.out.println("No se pudo parar la ejecucion");
                    }
                    System.exit(0);
                }
                if(fasefin==1){
                    Image fondoSeleccion = loadImagen("FondoSeleccion.jpg");
                    g.drawImage(fondoSeleccion,0,0,null);
                    Image perfil;
                    String imganador = ganador.getPersonaje();
                    perfil = loadImagen(imganador);
                    g.drawImage(perfil, 300, 200, null);
                    g.drawString("Felicitaciones! "+ganador.getNombre()+" eres el ganador de esta partida, se agregara a tu historial" , 317, 500);
                    
                }
                if(fasefin==0){
                    g.drawImage(barra, 0, 530, null);
                    g.drawImage(fondoBatalla,0,0,null);
                    if(vida1<0){
                        ganador=entrenador2;
                        perdedor=entrenador1;
                        g.drawString(pokemon1.getNombre()+" ha sido debilitado "+pokemon2.getNombre()+" es el ganador", 100, 590);
                    }
                    if(vida2<0){
                        ganador=entrenador1;
                        perdedor=entrenador2;
                        g.drawString(pokemon2.getNombre()+" ha sido debilitado "+pokemon1.getNombre()+" es el ganador", 100, 590);
                    }
                }
                
            }
            repaint();
        }

        public void actionPerformed(ActionEvent e) {
          repaint();
        }

        private Image loadImagen(String imageName) {
            ImageIcon ii = new ImageIcon(imageName);
            Image image = ii.getImage();
            return image;
        }
        
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
            return ((pokemon2.getATK()*move.getDMG()/10)-pokemon1.getDEF())*multiplicador;
        }
    }
    public void addWin(Entrenador winner,Entrenador loser) throws FileNotFoundException, IOException{
        File antiguo = new File("Entrenadores.txt");
        BufferedReader br = new BufferedReader(new FileReader(antiguo));
        ArrayList<String[]> datosEntrenadores= new ArrayList<String[]>();
        try {
            String line = br.readLine();
            String[] datos;
            while (line != null) {
                datos = line.split(",");
                datosEntrenadores.add(datos);
                line = br.readLine(); 
            }
        } finally {
            br.close();
        }
        for(int i=0;i<datosEntrenadores.size();i++){
            if(datosEntrenadores.get(i)[0].equals(winner.getNombre())){
                int wins=Integer.parseInt(datosEntrenadores.get(i)[2]);
                wins++;
                String ganadas=String.valueOf(wins);
                datosEntrenadores.get(i)[2]=ganadas;
            }
            if(datosEntrenadores.get(i)[0].equals(loser.getNombre())){
                int loses=Integer.parseInt(datosEntrenadores.get(i)[3]);
                loses++;
                String ganadas=String.valueOf(loses);
                datosEntrenadores.get(i)[3]=ganadas;
            }
        }
        antiguo.delete();
        File nuevo=new File("Entrenadores.txt");
        PrintStream output= new PrintStream(new File("Entrenadores.txt"));
        for(int i=0;i<datosEntrenadores.size();i++){
           for(int j=0;j<datosEntrenadores.get(i).length;j++){
               if(j<datosEntrenadores.get(i).length-1){
                       output.print(datosEntrenadores.get(i)[j]+",");
               }
               if(j==datosEntrenadores.get(i).length-1){
                       output.print(datosEntrenadores.get(i)[j]);
               }
           }
           output.println();
        }
        
    }
}
