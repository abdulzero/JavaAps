// import das bibliotecas
package jogo;

import javax.swing.JFrame;

// classe que vai gerar a janela do jogo

public class ContainerDeJanelas extends JFrame{

    // constructor 
	public ContainerDeJanelas() {
		
        add(new Fase());
		setTitle("Jogo APS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setLocationRelativeTo(null);
        setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ContainerDeJanelas();
	}
}
