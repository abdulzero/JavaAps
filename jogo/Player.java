package jogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Player {

	private int x, y;
	private int dx, dy;
	private int altura, largura;
	private int life = 5;
	private boolean isVisivel;

	private Image imagem;
	
	private List<Missel> misseis;
	
	public Player(){
		
		ImageIcon referencia = new ImageIcon("res//bike3.gif");
		imagem = referencia.getImage();
		
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
		
		misseis = new ArrayList<Missel>();
		
		this.x = 100;
		this.y = 100;
		
	}

	public int getLife(){
		return life;
	}

	public void setLife(int life){
        this.life -= life;
	}
	
	public void mexer(){

		x += dx; 
		y += dy; 

        System.out.println(this.x + "," + this.y);

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
		
	}
	
	public List<Missel> getMisseis() {
		return misseis;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {	
		return y;
	}
	
	public Image getImagem() {
		return imagem;
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

	public boolean isVisivel() {
		return isVisivel;
	}
	
	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}
	
	public void atira(){
		this.misseis.add(new Missel(x+largura, y + altura/2 ));
	}

	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}
	
	
	public void keyPressed(KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_SPACE){
			atira();
		}

		if(codigo == KeyEvent.VK_UP){
			dy = -1;
		}
		
		if(codigo == KeyEvent.VK_DOWN){
			dy = 1;
		}
		
		if(codigo == KeyEvent.VK_LEFT){
			dx = -1;
		}
		
		if(codigo == KeyEvent.VK_RIGHT){
			dx = 1;
		}
		
	}
	
	public void keyReleased(KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_UP){
			dy = 0;
		}
 
		if(codigo == KeyEvent.VK_DOWN){
			dy = 0;
		}
		
		if(codigo == KeyEvent.VK_LEFT){
			dx = 0;
		}
		
		if(codigo == KeyEvent.VK_RIGHT){
			dx = 0;
		}
		
	}
	
	
}
