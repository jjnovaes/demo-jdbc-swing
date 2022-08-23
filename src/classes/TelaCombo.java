package classes;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;

public class TelaCombo extends JFrame {

	private JPanel contentPane;
	private Object[] combo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCombo frame = new TelaCombo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 * @throws ParseException 
	 */
	public TelaCombo() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ConsultarCombo cc = new ConsultarCombo();
		combo = new String[cc.retornar().size()];
		combo = cc.retornar().toArray();
		
		JComboBox comboBox = new JComboBox(combo);
		comboBox.setBounds(10, 11, 185, 22);
		contentPane.add(comboBox);
	}
}
