package jogo;

import java.awt.Image;
import java.awt.Rectangle;

import java.util.Random;

import javax.swing.ImageIcon;

public class Obstaculo extends Objeto{

    //Atributos
    private int dano; // Qtd de vidas que o obstaculo irá tirar do player
    private int gerador;

    // Construtor
    public Obstaculo(int x, int y){
        super(x, y);
        Random random = new Random();
        gerador = random.nextInt(6);

        ImageIcon referencia;
        if(gerador == 2){
            dano = 5;
            gerarImagem("res\\tree.png");
        }
        else if(gerador == 5){
            dano = -1;
            gerarImagem("res\\heart.png");
        }
        else{
            dano = 1;
            gerarImagem("res//pedra.png");
        }

        velocidade = 1; // Mantém pois poderemos mudar futuramente
      
    }

    // Getter
    public int getDano(){
        return dano;
    }
 
        
}
