package jogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Lixo extends Objeto{

    public Lixo(int x, int y, int velocidade){
        super(x, y, velocidade);

        Random random = new Random();
        gerador = random.nextInt(3);

        if(gerador == 1){
        gerarImagem("res//lixo_1.png");
        }else{
            gerarImagem("res//lixo_2.png");
        }
    }


        
}
