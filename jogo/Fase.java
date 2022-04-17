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
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Font;


public class Fase extends JPanel implements ActionListener{

    private int fase;
    
    private Image fundo;
    private Player player;
    private Timer timer;

    private boolean venceu, emJogo, iniciou;

    private List<Lixo> lixos;

    private String avatar;

    private List<Obstaculo> obstaculos;

    private int[][] coordenadas2 = 
     {{20,450},{300, 480}, {320, 1000}, {500, 564}, {600,350},{750, 900}, {900,1}, {950, 500}, {1100, 320}, {950, 100}, {750, 200},
     {600,30},{100,200}, {500,256}
};


    // cords dos lixos
    private int[][] coordenadas = {
        {20,300},
        {400,200},
        {320,550},
        {200,290},
        {300,1},
        {750,390},
        {900,564},
        {1100,600},
        {1300,100},
        {1300, 400},
    };

    public Fase(){

        fase = 1; // O nivel da fase está correlacionado com a velocidade dos objetos;

        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TecladoAdapter());
        ImageIcon referencia = new ImageIcon("res//fundo.png");
        fundo = referencia.getImage();

        iniciou = false;
        emJogo = false;

        inicializaLixos();
        inicializaObstaculos();

        player = new Player("");
        timer = new Timer(5, this);
        timer.start();

        

    }

    public void inicializaLixos(){
        lixos = new ArrayList<Lixo>();

        for(int i = 0;i < coordenadas.length; i++){
            lixos.add(new Lixo(coordenadas[i][0], coordenadas[i][1], fase));
        }

    }

    public void inicializaObstaculos(){
        obstaculos = new ArrayList<Obstaculo>();

        for(int i = 0;i < coordenadas2.length; i++){
            obstaculos.add(new Obstaculo(coordenadas2[i][0], coordenadas2[i][1], fase));
        }

    }
    public void inicializarJogo(){
        player = new Player(avatar);
        inicializaLixos();
        inicializaObstaculos();
        iniciou = true;
        emJogo = true;
    }
 

    public void paint(Graphics g){

        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);
        graficos.setColor(Color.WHITE);
        graficos.setFont(new Font("Arial Black", Font.PLAIN, 18));;

        if(emJogo){
            
            venceu = false;

            graficos.drawImage(player.getImagem(),player.getX(),player.getY(), this);

            for(int i = 0; i < lixos.size(); i++){

                Lixo in = (Lixo) lixos.get(i);
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }
            for(int i = 0; i < obstaculos.size(); i++){

                Obstaculo in = (Obstaculo) obstaculos.get(i);
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }
            
            graficos.drawString("LIXOS NA ESTRADA: " + lixos.size(), 5, 20);
            graficos.drawString("LIFE: " + player.getLife(), 5, 40);
            graficos.drawString("FASE: " + fase, 1160, 20);

        } else {
            if(venceu){
               System.out.println("ok");
               graficos.drawString("VOCÊ VENCEU!! APERTE ENTER E VÁ PARA PRÓXIMA FASE!", 5, 20);
            }
            else if (iniciou == false){
              
              graficos.drawString("ESCOLHA SEU AVATAR", 540, 100);
              graficos.drawImage((Image) new ImageIcon("res//boy.png").getImage(),800, 200, null);
              graficos.drawString("APERTE B", 840, 430);
              graficos.drawImage((Image) new ImageIcon("res//girl.png").getImage(),280, 200, null);
              graficos.drawString("APERTE G", 320, 430);
            } 
            else {
                ImageIcon fimJogo = new ImageIcon("res//game_over.jpg");
                graficos.drawImage(fimJogo.getImage(), -5,-50,null);
                graficos.setColor(Color.WHITE);
                graficos.drawString("Pressione Enter para jogar denovo", 480, 500);  
                fase = 1;  
            }
        }

        g.dispose();
        // limpa a tela
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if(player.getLife() > 0 && lixos.size() == 0){
           venceu = true;
           emJogo = false;
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

        for(int i = 0; i < lixos.size(); i++){
            
            Lixo tempLixo = lixos.get(i);
            formaLixo = tempLixo.getBounds();

            if(formaPlayer.intersects(formaLixo)){
                tempLixo.setVisivel(false);
            }


        }

        for(int i = 0; i < obstaculos.size(); i++){
            
            Obstaculo tempObstaculo = obstaculos.get(i);
            formaObstaculo = tempObstaculo.getBounds();

            if(formaPlayer.intersects(formaObstaculo)){
                player.setLife(player.getLife()- tempObstaculo.getDano());
                player.setImagem(player.getLife());
                if(player.getLife() < 1){
                    player.setVisivel(false);
                    emJogo = false;
                }
                tempObstaculo.setVisivel(false);

            }


        }
    }

    private class TecladoAdapter extends KeyAdapter{

        @Override
		public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            if (!emJogo && !iniciou){
                if (code == KeyEvent.VK_B){
                    avatar = "res//bike_boy.gif";
                    inicializarJogo();
                }
                else if (code == KeyEvent.VK_G){
                    avatar = "res//bike_girl.gif";
                    inicializarJogo();
                }
                
            }
			else if(!emJogo /*&& iniciou*/){
                if(code == KeyEvent.VK_ENTER){
                    fase = (venceu)? ++fase: 1; // A cada vitoria a haverá um incremento na fase, caso perca, a fase irá retornar para 1 (a fase influencia na velocidade dos obstaculos e do lixo)
                    inicializaLixos();
                    inicializaObstaculos();
                    iniciou = true;
                    emJogo = true;
                    player = new Player(avatar);
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