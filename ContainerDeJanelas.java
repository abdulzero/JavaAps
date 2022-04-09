import javax.swing.JFrame;
import javax.swing.JButton;

public class ContainerDeJanelas extends JFrame{
	public ContainerDeJanelas() {
		
		add(new JButton("Teste"));
		setTitle("Jogo APS");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,400);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ContainerDeJanelas();
	}
}
