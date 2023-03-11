import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class GUIMainMenu extends JFrame {
	private String[] opcionesConvertidor = {"Divisas","Temperatura","Tiempo"};
	private Conversor[] convertidores = {new Divisas(), new Temperatura(), new Tiempo()};
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					GUIMainMenu frame = new GUIMainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIMainMenu() {
		setTitle("JAVA Conversor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select conversion type:");
		lblNewLabel.setBounds(127, 57, 206, 30);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox(opcionesConvertidor);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Currency", "Temperature", "Time"}));
		comboBox.setBounds(150, 100, 117, 22);
		contentPane.add(comboBox);
		
		//Aqui se decide que objeto sera el que se inicialice al momento de generar el conversor
		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int item = (int) comboBox.getSelectedIndex();
				Conversor conversor = convertidores[item];
				System.out.println(conversor.testEntrada());
				
				
				GUIConversor GUIconversor = new GUIConversor(conversor);
				GUIconversor.show();
				dispose();
			}
		});
		btnNewButton.setBounds(151, 146, 89, 23);
		contentPane.add(btnNewButton);
	}

}
