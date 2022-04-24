package jogo;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class Sprite {

    protected Image imagem;
    protected int x, y;
    protected int largura, altura;
    protected boolean visivel;
    protected static final int LARGURA_TELA = 1280;
    protected int velocidade;

    // Construtor

    public Sprite(int x, int y, int velocidade){
        this.x = x;
        this.y = y;
        this.velocidade = velocidade;
        visivel = true;
    }

    // Métodos

    public void mexer(){

        if(this.x < 0){
            this.x = LARGURA_TELA;
        } else {
            this.x -= velocidade;
        }
    }

    public void gerarImagem(String path){
        ImageIcon referencia = new ImageIcon(path); //instancia a referencia
		this.imagem = referencia.getImage(); //atribui a 
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);

    }
    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean isVisivel){
        this.visivel = isVisivel;
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

     // tratar colisão vida etc
    public Rectangle getBounds(){
        return new Rectangle(x, y, largura, altura);
    }

    
}
