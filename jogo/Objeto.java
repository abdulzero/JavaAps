// Classe abstrata que será o molde para todos os objetos que se deslocam e interagem entre si

package jogo;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Random;
import java.awt.Rectangle;

public abstract class Objeto {

    protected Image imagem;
    protected int x, y;
    protected int largura, altura;
    protected boolean isVisivel;
    protected static final int LARGURA_TELA = 500;
    protected int velocidade;

    protected int gerador;

    // Construtores
    public Objeto(){
        this(100, 100, 2); // - posições x e y iniciais
    }

    public Objeto(int x, int y, int velocidade){
        this.x = x;
        this.y = y;
        this.velocidade = velocidade;
        isVisivel = true;
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

     // tratar colisão vida etc
    public Rectangle getBounds(){
        return new Rectangle(x, y, largura, altura);
    }

    
}
