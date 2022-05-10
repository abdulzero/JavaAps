package jogo;

import java.util.Random;

public class Obstaculo extends Sprite{

    private int dano; // Qtd de vidas que o obstaculo irá tirar (ou recuperar) do player

    // Construtor
    public Obstaculo(int x, int y, int velocidade){
        super(x, y, velocidade);
        Random random = new Random();
        switch(random.nextInt(6)){
            case 2:       // 1/6 de chance de gerar uma árvore
                dano = 3;           //hit kill
                gerarImagem("res//tree2.png");
                break;
            case 5:  // 1/6 de chance de gerar o coração
                dano = -1;          // restaura 1 ponto de vida
                gerarImagem("res//heart.png");
                break;
            default:                // 2/3 de chance de gerar pedra
                dano = 1;
                gerarImagem("res//pedra.png");
        }
     
    }

    // Getter
    public int getDano(){
        return dano;
    }
        
}
