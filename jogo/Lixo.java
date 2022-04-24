package jogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Lixo extends Sprite{

    public Lixo(int x, int y, int velocidade){
        super(x, y, velocidade);

        gerarImagem("res//lixo_1.png");
    }


        
}
