package gestionEmployes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IndicationPasse extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	Connection cnx = null;
	PreparedStatement prepared =null;
	ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IndicationPasse frame = new IndicationPasse();
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
	public IndicationPasse() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 415, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx =  ConnectionBD.connectDb();
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String username = textField.getText().toString();
				String sql= "select password from utilisateur where username=?";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, username);
					rs= prepared.executeQuery();
					if(rs.next()){
					String pass = rs.getString("password");
					String pass1= pass.substring(0,3);
					textField_1.setText("les 3 premiers lettres de ton mot de passe est :"+pass1);
					}
				} catch (SQLException a) {
					// TODO Auto-generated catch block
					a.printStackTrace();
				}
			}
		});
		textField.setBounds(203, 55, 113, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("username");
		lblNewLabel.setBounds(86, 64, 68, 14);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
				
				
				
			}
		});
		textField_1.setBounds(203, 116, 186, 32);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("password");
		lblNewLabel_1.setBounds(86, 125, 68, 14);
		contentPane.add(lblNewLabel_1);
	}

}
