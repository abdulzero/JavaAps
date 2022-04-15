package jogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player extends Objeto{

	private int dx, dy; //movimentação
	private int life = 3; //variavel vida
		
	public Player(){ //constructor do jogador
		gerarImagem("res//bike3.gif"); //instancia a referencia
		
	}

	public int getLife(){ //metodo que retorna a vida
		return life;
	}

	public void setLife(int life){ //metodo que seta a vida
        this.life = life;
		if(this.life > 3){
			this.life = 3;
		}
	}
	
	@Override
	public void mexer(){ //metodo da movimentação

		x += dx;
		y += dy; //dx e dy são os comandos do jogador atribuidos a variavel de sua posição 

        // System.out.println(this.x + "," + this.y); 
		// a seguir, validação se o personagem exceder o limite da tela:

		if(this.x < 1){ 
			x = 1;
		}
		
		if(this.x > 424){
			x = 424;
		}
		
		if(this.y < 1){
			y = 1;
		}

		if(this.y > 300){
			y = 300;
		}

		// ends 
		
	}
	

	public void setImagem(int status) {
		// classe que muda o tamanho do personagem caso ele perca vida, por enquanto ele só perde, add depois ganho
		switch(status){
			case 5:
			ImageIcon referencia5 = new ImageIcon("res//bike3.gif");
			this.imagem = referencia5.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
			break;
			case 4:
			ImageIcon referencia4 = new ImageIcon("res//bike3.gif");
			this.imagem = referencia4.getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT);
			break;
			case 3:
			ImageIcon referencia3 = new ImageIcon("res//bike3.gif");
			this.imagem = referencia3.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
			break;
			case 2:
			ImageIcon referencia2 = new ImageIcon("res//bike3.gif");
			this.imagem = referencia2.getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT);
			break;
			case 1:
			ImageIcon referencia1 = new ImageIcon("res//bike3.gif");
			this.imagem = referencia1.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			break;
			default:
			break;
		}
	}


	
	
	public void keyPressed(KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_UP){
			dy = velocidade * -1;
		}
		
		if(codigo == KeyEvent.VK_DOWN){
			dy = velocidade;
		}
		
		if(codigo == KeyEvent.VK_LEFT){
			dx = velocidade *-1;
		}
		
		if(codigo == KeyEvent.VK_RIGHT){
			dx = velocidade;
		}
		
	}
	
	public void keyReleased(KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_DOWN){
			dy = 0;
		}
 
		
		if(codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT){
			dx = 0;
		}
		
		
	}
	
	
}
