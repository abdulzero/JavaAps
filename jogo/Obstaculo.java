package jogo;

import java.awt.Image;
import java.awt.Rectangle;

import java.util.Random;

import javax.swing.ImageIcon;

public class Obstaculo extends Objeto{

    //Atributos
    private int dano; // Qtd de vidas que o obstaculo irá tirar (ou recuperar) do player
    private int gerador;

    // Construtor
    public Obstaculo(int x, int y, int velocidade){
        super(x, y, velocidade);
        Random random = new Random();
        gerador = random.nextInt(6);
        if(gerador == 2){       // 33% de chance de gerar uma árvore
            dano = 3;           //hit kill
            gerarImagem("res\\tree2.png");
        }
        else if(gerador == 5){  // 16.666% de chance de gerar o coração
            dano = -1;          // restaura 1 ponto de vida
            gerarImagem("res\\heart.png");

        }
        else{                  // 50% de chance de gerar pedra
            dano = 1;
            gerarImagem("res//pedra.png");
        }

      
    }

    // Getter
    public int getDano(){
        return dano;
    }
 
        
}
