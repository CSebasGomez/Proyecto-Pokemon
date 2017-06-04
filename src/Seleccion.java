
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sebas
 */
public class Seleccion extends JFrame {
    public ArrayList<Pokemon> pokemones1;
    public ArrayList<Pokemon> pokemones2;
    public Entrenador Entrenador1;
    public Entrenador Entrenador2;
    public ArrayList<Pokemon> seleccionados=new ArrayList();
    public int seleccion=0;
    
    public Seleccion(Entrenador Entrenador1,Entrenador Entrenador2){
        this.Entrenador1=Entrenador1;
        this.Entrenador2=Entrenador2;
        this.pokemones1=Entrenador1.getPokemones();
        this.pokemones2=Entrenador2.getPokemones();
        add(new PanelSeleccion(pokemones1,pokemones2));
    }
    
   public void Pintar() {
        Seleccion frame = new Seleccion(Entrenador1,Entrenador2);
        frame.setTitle("Pokemon Amethyst- Seleccion");
        frame.setSize(900,735);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
    public class PanelSeleccion extends JPanel  implements ActionListener {
    Timer timer;
    int i=0;
    int j=0;
    
    
    public PanelSeleccion(ArrayList<Pokemon> Pokemones1,ArrayList<Pokemon> Pokemones2){
        timer = new Timer(25, this);
        timer.start();
        addKeyListener(new TAdapter());
        setFocusable(true);
    }

        
        private class TAdapter extends KeyAdapter{
            Graphics u;
            public void keyPressed(KeyEvent e){
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_RIGHT){
                    if(seleccion==0){
                        if(i<pokemones1.size()){
                            if((i+1)==pokemones1.size()){
                                i=0;
                            }
                            else{
                                i++;
                            }
                        }
                    }
                    if(seleccion==1){
                        if(i<pokemones2.size()){
                            if((j+1)==pokemones2.size()){
                                j=0;
                            }
                            else{
                                j++;
                            }
                        }
                    }
                }
                if(key == KeyEvent.VK_LEFT){
                   if(seleccion==0){
                        if(i<pokemones1.size()){
                            if(i==0){
                                i=pokemones1.size()-1;
                            }
                            else{
                                i--;
                            }
                        }
                    }
                    if(seleccion==1){
                        if(j<pokemones2.size()){
                            if(j==0){
                                j=pokemones2.size()-1;
                            }
                            else{
                                j--;
                            }
                        }
                    }
                }
                if(key==KeyEvent.VK_ENTER){
                    if(seleccion==1){
                        seleccionados.add(pokemones2.get(j));
                        Batalla batalla= new Batalla(seleccionados.get(0),seleccionados.get(1),Entrenador1,Entrenador2);
                        batalla.letsPlay();
                    }
                    if(seleccion==0){
                        seleccionados.add(pokemones1.get(i));
                        seleccion++;
                        i=0;
                    }
                    
                }
            }
        }
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            Image fondoSeleccion = loadImage("FondoSeleccion.jpg");
            g.drawImage(fondoSeleccion,0,0,null);
            
            if(seleccion==0){
                String imagenpkmn=pokemones1.get(i).getImagenFrente();
                Image PokemonImagen= loadImage(imagenpkmn);
                g.drawImage(PokemonImagen, 300, 200, this);



                repaint();
                g.setColor(Color.white);
                g.drawString("¿Qué pokemon desea escoger "+Entrenador1.getNombre()+" ?", 350, 100);
                Image fD=loadImage("FD.png");

                g.drawString(pokemones1.get(i).getNombre(), 400, 150);

                g.drawImage(fD, 590, 330, null);

                Image fI = loadImage("FI.png");
                g.drawImage(fI, 180, 330,null);

                g.drawString("Presione ENTER para seleccionar el pokemon que luchara junto a "+Entrenador1.getNombre()+" ?", 317, 500);
            }
            else{
                String imagenpkmn=pokemones2.get(j).getImagenFrente();
                Image PokemonImagen= loadImage(imagenpkmn);
                g.drawImage(PokemonImagen, 300, 200, this);



                repaint();
                g.setColor(Color.white);
                g.drawString("¿Qué pokemon desea escoger "+Entrenador2.getNombre()+" ?", 350, 100);
                Image fD=loadImage("FD.png");

                g.drawString(pokemones2.get(j).getNombre(), 400, 150);

                g.drawImage(fD, 590, 330, null);

                Image fI = loadImage("FI.png");
                g.drawImage(fI, 180, 330,null);

                g.drawString("Presione ENTER para seleccionar el pokemon que luchara junto a "+Entrenador2.getNombre()+" ?", 317, 500);
            }
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            
        }
        public Image loadImage(String imageName){
                ImageIcon ii = new ImageIcon(imageName);
                Image image = ii.getImage();
                return image;
        }
    }
}

