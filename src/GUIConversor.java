import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class GUIConversor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private static Conversor conversor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIConversor frame = new GUIConversor(conversor);
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
	public GUIConversor(Conversor conversor) {
		setTitle("JAVA Conversor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Perform conversion:");
		lblNewLabel.setBounds(48, 46, 142, 29);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIMainMenu menu = new GUIMainMenu();
				menu.show();
				dispose();
			}
		});
		btnNewButton.setBounds(167, 194, 89, 23);
		contentPane.add(btnNewButton);

		JComboBox comboBox = new JComboBox(conversor.getListaOpciones());
		comboBox.setBounds(48, 93, 120, 22);
		contentPane.add(comboBox);

		JComboBox comboBox_1 = new JComboBox(conversor.getListaOpciones());
		comboBox_1.setSelectedIndex(1);
		comboBox_1.setBounds(48, 132, 120, 22);
		contentPane.add(comboBox_1);

		textField = new JTextField();
		
		textField.setBounds(195, 94, 118, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		
		textField_1.setBounds(195, 133, 118, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		
	
		
		JButton btnNewButton_2 = new JButton("Convert");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String enteredValue = textField.getText();
					try {
						double value = Double.parseDouble(enteredValue);
						double resultado = conversor.convertirValores(value, (String)comboBox.getSelectedItem(), 
								(String)comboBox_1.getSelectedItem(), comboBox.getSelectedIndex(), comboBox_1.getSelectedIndex());
						textField_1.setForeground(new Color(0, 0, 0));
						textField_1.setText(Double.toString(resultado));
					}catch(NumberFormatException nf) {
						textField_1.setForeground(new Color(255, 0, 0));
						textField_1.setText("Ingrese numeros");
					}
					
			}
		});
		btnNewButton_2.setBounds(323, 132, 89, 23);
		contentPane.add(btnNewButton_2);
	}
}
