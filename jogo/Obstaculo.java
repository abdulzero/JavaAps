package jogo;

import java.awt.Image;
import java.awt.Rectangle;

import java.util.Random;

import javax.swing.ImageIcon;

public class Obstaculo {

    private Image imagem;
    private int x, y;
    private int largura, altura;
    private int dano; // Qtd de vidas que o obstaculo irá tirar do player
    private boolean isVisivel;

    private static final int LARGURA_TELA = 500;
    private static final int VELOCIDADE = 1;

    private int gerador;

    private static int contador = 0;

    public Obstaculo(int x, int y){
        Random random = new Random();
        gerador = random.nextInt(6);

        ImageIcon referencia;
        if(gerador == 2){
            dano = 5;
            referencia = new ImageIcon("res\\tree.png");
        }
        else if(gerador == 5){
            dano = -1;
            referencia = new ImageIcon("res\\heart.png");
        }
        else{
            dano = 1;
            referencia = new ImageIcon("res//pedra.png");
        }
        imagem = referencia.getImage();

        this.x = x;
        this.y = y;

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);

        isVisivel = true;
    }

    public void mexer(){

        if(this.x < 0){
            this.x = LARGURA_TELA;
        } else {
            this.x -= VELOCIDADE;
        }
    }

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean isVisivel){
        this.isVisivel = isVisivel;
    }

    public Image getImagem() {
        return imagem;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getDano(){
        return dano;
    }


    // tratar colisão vida etc
    public Rectangle getBounds(){
        return new Rectangle(x, y, largura, altura);
    }
        
}
