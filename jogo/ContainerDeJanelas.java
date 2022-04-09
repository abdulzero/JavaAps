// import das bibliotecas
package jogo;

import javax.swing.JFrame;
import javax.swing.JButton;

// classe que vai gerar a janela do jogo

public class ContainerDeJanelas extends JFrame{

    // constructor 
	public ContainerDeJanelas() {
		
        
		add(new JButton("Teste"));
		setTitle("Jogo APS");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,1000);
        setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ContainerDeJanelas();
	}
}
