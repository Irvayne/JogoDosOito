package visão;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Tela extends JFrame {

	private JPanel contentPane;
	public JTextArea textArea;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public Tela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		textArea = new JTextArea();
		textArea.setBounds(58, 31, 328, 183);
		contentPane.add(textArea);
		
	}
}
