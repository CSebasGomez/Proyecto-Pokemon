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
    int fase=1;
    int mov;
    
    public Batalla(Pokemon pokemon1, Pokemon pokemon2) {
        this.pokemon1=pokemon1;
        this.pokemon2=pokemon2;
        this.vida1=pokemon1.getHP();
        this.vida2=pokemon2.getHP();
        add(new PanelBatalla(pokemon1,pokemon2));
    }
    public void letsPlay(){
            Batalla bt = new Batalla(pokemon1,pokemon2);
            bt.setTitle("Pokemon Amethyst- Batalla");
            bt.setSize(900,735);
            bt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            bt.setVisible(true);
//            System.out.println("HP de "+pokemon1.getNombre()+" = "+vida1);
//            System.out.println("HP de "+pokemon2.getNombre()+" = "+vida2);
//            while((vida1>0)&&(vida2>0)){
//                java.util.Scanner a=new java.util.Scanner(System.in);
//                System.out.println("Que deberia hacer "+pokemon1.getNombre());
//                int i = a.nextInt();
//                Movimiento move= pokemon1.getMovimientos()[i-1];
//                System.out.println(pokemon1.getNombre()+ " uso "+move.getNombre());
//                vida2=vida2-calcularDMG(move);
//                if(vida2>0){
//                    System.out.println("HP de "+pokemon2.getNombre()+" = "+vida1);
//                }
//                else{
//                    System.out.println("HP de "+pokemon2.getNombre()+" = 0 ");
//                }
//                turno++;
//                if(vida2<0){
//                    break;
//                }
//                System.out.println("Que deberia hacer "+pokemon2.getNombre());
//                i = a.nextInt();
//                move= pokemon2.getMovimientos()[i-1];
//                System.out.println(pokemon2.getNombre()+ " uso "+move.getNombre());
//                vida1=vida1-calcularDMG(move);
//                if(vida1>0){
//                    System.out.println("HP de "+pokemon1.getNombre()+" = "+vida1);
//                }
//                else{
//                    System.out.println("HP de "+pokemon1.getNombre()+" = 0 ");
//                }
//                turno++;
//            }
//            System.out.println("La batalla ha terminado");
//            if(vida1<0){
//                System.out.println(pokemon2.getNombre()+" Ha ganado");
//            }
//            if(vida2<0){
//                System.out.println(pokemon1.getNombre()+" Ha ganado");
//            }
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
               
                    if(key==KeyEvent.VK_1){
                        mov=0;
                    }
                    if(key==KeyEvent.VK_2){
                       mov =1;
                    }
                    if(key==KeyEvent.VK_3){
                        mov =2;
                    }
                    if(key==KeyEvent.VK_4){
                        mov=3;
                    }
                
                if(key==KeyEvent.VK_ENTER){
                    timer.start();
                }
            }
        }
        
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            Image fondoBatalla = loadImagen("Fondo.png");
            g.drawImage(fondoBatalla, 0, 0, null);
            
            Image poke1 = loadImagen(pokemon1.getNombre()+"-Espalda.png");  
            g.drawImage(poke1, 0, 220, this);

            
            Image poke2 = loadImagen(pokemon2.getNombre()+"-Frente.png");
            g.drawImage(poke2, 530, 105, this);
            
            Image barra = loadImagen("Barra Combate.png");
            g.drawImage(barra, 0, 530, null);
            g.drawString("¿Qué debería hacer "+pokemon1.getNombre()+" ?", 100, 590);
//            while(vida1>0 && vida2>0){
//               
//                g.setColor(Color.white);
//                g.drawString("¿Qué debería hacer "+pokemon1.getNombre(), 100, 590);
//                timer.stop();
//                mov=-1;
//                while(mov<0 || mov>3){
//                    g.drawImage(fondoBatalla, 0, 0, null);
//                    g.drawImage(poke1, 0, 220, this);
//                    g.drawImage(barra, 0, 530, null);
//                    g.drawString("(1) "+pokemon1.getMovimientos()[0], 100, 590);
//                    g.drawString("(2) "+pokemon1.getMovimientos()[1], 200, 590);
//                    g.drawString("(3) "+pokemon1.getMovimientos()[2], 100, 620);
//                    g.drawString("(4) "+pokemon1.getMovimientos()[3], 200, 620);
//                }
//                vida2=vida2-calcularDMG(pokemon1.getMovimientos()[mov]);
//                g.drawString("HP de "+pokemon2.getNombre()+" reducidos a "+vida2, 100, 590);
//                turno++;
//                g.drawString("¿Qué debería hacer "+pokemon2.getNombre(), 100, 590);
//                timer.stop();
//                mov=-1;
//                while(mov<0 || mov>3){
//                    g.drawImage(fondoBatalla, 0, 0, null);
//                    g.drawImage(poke1, 0, 220, this);
//                    g.drawImage(barra, 0, 530, null);
//                    g.drawString("(1) "+pokemon2.getMovimientos()[0], 100, 590);
//                    g.drawString("(2) "+pokemon2.getMovimientos()[1], 200, 590);
//                    g.drawString("(3) "+pokemon2.getMovimientos()[2], 100, 620);
//                    g.drawString("(4) "+pokemon2.getMovimientos()[3], 200, 620);
//                }
//                vida1=vida1-calcularDMG(pokemon2.getMovimientos()[mov]);
//                g.drawString("HP de "+pokemon2.getNombre()+" reducidos a "+vida2, 100, 590);
//                turno++;
//            }
            repaint();
        }

        @Override
        public void actionPerformed(ActionEvent e) {

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
}
