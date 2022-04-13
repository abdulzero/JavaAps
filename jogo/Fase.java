package jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener{
    
    private Image fundo;
    private Player player;
    private Timer timer;

    private boolean emJogo;

    private List<Lixo> lixos;

    private List<Obstaculo> obstaculos;

    private int[][] coordenadas2 = {{ 920, 200 }, { 900, 259 }, { 660, 50 }, { 540, 90 }, { 810, 220 },
    { 860, 20 }, { 740, 180 }, 
    // { 820, 128 }, { 490, 170 }, { 700, 30 },
    { 920, 300 }, { 856, 328 }, { 456, 320 } };


    // cords dos inimigos
    private int[][] coordenadas = { { 2380, 29 }, { 2600, 59 }, { 1380, 89 },
        { 780, 109 }, { 580, 139 }, { 880, 239 }, { 790, 259 },
        { 760, 50 }, { 790, 150 }, { 1980, 209 }, { 560, 45 }, { 510, 70 },
        { 930, 159 }, { 590, 80 }, { 530, 60 }, { 940, 59 }, { 990, 30 },};

    public Fase(){

        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TecladoAdapter());
        ImageIcon referencia = new ImageIcon("res//fundo.png");
        fundo = referencia.getImage();

        player = new Player();

        emJogo = false;

        inicializaLixos();
        inicializaObstaculos();

        timer = new Timer(5, this);
        timer.start();

    }

    public void inicializaLixos(){
        lixos = new ArrayList<Lixo>();

        for(int i = 0;i < coordenadas.length; i++){
            lixos.add(new Lixo(coordenadas[i][0], coordenadas[i][1]));
        }

    }

    public void inicializaObstaculos(){
        obstaculos = new ArrayList<Obstaculo>();

        for(int i = 0;i < coordenadas2.length; i++){
            obstaculos.add(new Obstaculo(coordenadas2[i][0], coordenadas2[i][1]));
        }

    }

    public void paint(Graphics g){

        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);

        if(emJogo){

            graficos.drawImage(player.getImagem(),player.getX(),player.getY(), this);

            List<Missel> misseis = player.getMisseis();

            for(int i = 0; i < misseis.size(); i++){

                Missel m = (Missel) misseis.get(i);
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
            }

            for(int i = 0; i < lixos.size(); i++){

                Lixo in = (Lixo) lixos.get(i);
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }
            for(int i = 0; i < obstaculos.size(); i++){

                Obstaculo in = (Obstaculo) obstaculos.get(i);
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }
            
            graficos.setColor(Color.WHITE);
            graficos.drawString("LIXOS NA ESTRADA: " + lixos.size(), 5, 15);
            graficos.drawString("LIFE: " + player.getLife(), 5, 30);

        } else {
            ImageIcon fimJogo = new ImageIcon("res//game_over.jpg");
            graficos.drawImage(fimJogo.getImage(), 0,0,null);
        }

        g.dispose();
        // limpa a tela
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if(lixos.size() == 0){
            emJogo = false;
        }

        List<Missel> misseis = player.getMisseis();

        for(int i = 0; i < misseis.size() ; i++){

            Missel m = (Missel) misseis.get(i);

            if(m.isVisivel()){
                m.mexer();
            } else {
                misseis.remove(i);
            }
        }

        for(int i = 0; i < lixos.size() ; i++){

            Lixo in = (Lixo) lixos.get(i);

            if(in.isVisivel()){
                in.mexer();
            } else {
                lixos.remove(i);
            }
        }

        for(int i = 0; i < obstaculos.size() ; i++){

            Obstaculo in = (Obstaculo) obstaculos.get(i);

            if(in.isVisivel()){
                in.mexer();
            } else {
                obstaculos.remove(i);
            }
        }


        player.mexer();
        checarColisoes();
        repaint();

    }

    public void checarColisoes(){

        Rectangle formaPlayer = player.getBounds();
        Rectangle formaLixo;
        Rectangle formaObstaculo;
        Rectangle formaMissel;

        for(int i = 0; i < lixos.size(); i++){
            
            Lixo tempLixo = lixos.get(i);
            formaLixo = tempLixo.getBounds();

            if(formaPlayer.intersects(formaLixo)){
                player.setLife(1);
                player.setImagem(player.getLife());
                if(player.getLife() == 0){
                    player.setVisivel(false);
                    emJogo = false;
                }
                tempLixo.setVisivel(false);
            }


        }

        for(int i = 0; i < obstaculos.size(); i++){
            
            Obstaculo tempObstaculo = obstaculos.get(i);
            formaObstaculo = tempObstaculo.getBounds();

            if(formaPlayer.intersects(formaObstaculo)){
                player.setLife(1);
                player.setImagem(player.getLife());
                if(player.getLife() == 0){
                    player.setVisivel(false);
                    emJogo = false;
                }
                tempObstaculo.setVisivel(false);
                // add classe de animação dos obstaculos quebrando ao colidir

            }


        }

        List<Missel> misseis = player.getMisseis();

        for(int i = 0; i < misseis.size(); i++){

            Missel tempMissel = misseis.get(i);
            formaMissel = tempMissel.getBounds();

            for(int f = 0; f < lixos.size(); f++){

                Lixo tempLixo = lixos.get(f);
                formaLixo = tempLixo.getBounds();

                if(formaMissel.intersects(formaLixo)){
                    tempLixo.setVisivel(false);
                    tempMissel.setVisivel(false);
                }

            }
        }

    }

    private class TecladoAdapter extends KeyAdapter{

        @Override
		public void keyPressed(KeyEvent e) {

			if(emJogo == false){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    emJogo = true;
                    player = new Player();
                    inicializaLixos();
                }
            }

			
			player.keyPressed(e);
		}

        @Override
        public void keyReleased(KeyEvent e){
            player.keyReleased(e);
        }
        
    }
}