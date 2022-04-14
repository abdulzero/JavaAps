package jogo;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Random;
import java.awt.Rectangle;

public abstract class Objeto {

    protected Image imagem;
    protected int x, y;
    protected int largura, altura;
    private boolean isVisivel;
    protected static final int LARGURA_TELA = 500;
    protected static int velocidade;

    protected int gerador;

    public Objeto(){
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
            this.x -= velocidade;
        }
    }

    public void gerarImagem(String path){
        ImageIcon referencia = new ImageIcon(path); //instancia a referencia
		imagem = referencia.getImage(); //atribui a imagem

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
    public Rectangle getBounds(){
        return new Rectangle(x, y, largura, altura);
    }

    
}
