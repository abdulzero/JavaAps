package jogo;

import java.awt.event.KeyEvent;

public class Player extends Sprite{

	private int dx, dy; //movimentação
	private int life; //variavel vida
		
	public Player(String path){ //constructor do jogador
		super(100, 100, 2);
		gerarImagem(path);
		life = 3;		
	}

	@Override
	public void mexer(){ //metodo da movimentação
		x += (x + dx < 0 | x + dx > Fase.LARGURA_TELA - this.largura)? 0: dx;
		y += (y + dy < 0 | y + dy > Fase.ALTURA_TELA - this.altura)? 0: dy; //dx e dy são os comandos do jogador atribuidos a variavel de sua posição 

        //System.out.println(this.x + "," + this.y); 
		// a seguir, validação se o personagem exceder o limite da tela:
		
	}

	// Getters e setters
	public int getLife(){ //metodo que retorna a vida
		return life;
	}
	public void setLife(int life){ //metodo que seta a vida
        this.life = life;
		if(this.life > 3){ // Teto de vida
			this.life = 3;
		}
	}
	public void setImagem(int status) {
		// classe que muda o tamanho do personagem caso ele perca vida, por enquanto ele só perde, add depois ganho
		int area = 60 - ((3 - status) * 5); // Fórmula do tamanho
		imagem = imagem.getScaledInstance(area, area, imagem.SCALE_DEFAULT);
		altura = largura = area;
		//System.out.printf("largura: %d \naltura: %d \n", largura, altura);
	}
	
	
	public void keyPressed(KeyEvent tecla){

		switch(tecla.getKeyCode()){
			case KeyEvent.VK_UP:
				dy = velocidade * -1;
				break;
			case KeyEvent.VK_DOWN:
				dy = velocidade;
				break;
			case KeyEvent.VK_LEFT:
				dx = velocidade *-1;
				break;
			case KeyEvent.VK_RIGHT:
				dx = velocidade;
				break;
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
