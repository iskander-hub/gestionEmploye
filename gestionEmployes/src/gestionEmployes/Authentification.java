package gestionEmployes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;



import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Authentification extends JFrame {

	private JFrame frame;
	private JTextField userField;
	private JTextField pwdField;
	private JLabel lblUser;
	private JLabel lblPwd;
	Connection cnx = null;
	PreparedStatement prepared =null;
	ResultSet rs = null;
	private JLabel lblMotDePasse;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification window = new Authentification();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Authentification() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		cnx =  ConnectionBD.connectDb();
		
		userField = new JTextField();
		userField.setBounds(162, 85, 102, 25);
		frame.getContentPane().add(userField);
		userField.setColumns(10);
		
		pwdField = new JTextField();
		pwdField.setBounds(162, 121, 102, 25);
		frame.getContentPane().add(pwdField);
		pwdField.setColumns(10);
		
		JButton btnNewButton = new JButton("se connecter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String userName = userField.getText().toString();
				String pwd = pwdField.getText().toString();
				
				String sql = "select username,password from utilisateur";
				try {
					prepared= cnx.prepareStatement(sql);
					rs= prepared.executeQuery();
					int i =0;
					if(userName.equals("") || pwd.equals("")){
						JOptionPane.showMessageDialog(null, "remplir les champs vides");
					}
					else {
					while(rs.next()){
						String username1= rs.getString("username");
						String password1= rs.getString("password");
						
						if(username1.equals(userName)&& password1.equals(pwd)){

							JOptionPane.showMessageDialog(null, "connection réussie :D");
							i=1;
							MenuAdmin menuAdmin = new MenuAdmin();
							menuAdmin.setVisible(true);
							
						}
						
					}
					if(i==0){
						JOptionPane.showMessageDialog(null, "connection échoué :(");
					}
					
					}
				} 
				catch (SQLException e) {
					
					e.printStackTrace();
				}
			/*	if(!userName.equals("")|| !pwd.equals("")){
				if(userName.equals("admin")&& pwd.equals("admin")){
					JOptionPane.showMessageDialog(null, "connection réussie");
					MenuAdministrateur menu = new MenuAdministrateur();
					menu.setVisible(true);
				}
				
				else {
					JOptionPane.showMessageDialog(null, "connection echoué");
				}
				}*/
			}
		});
		btnNewButton.setBounds(162, 169, 115, 23);
		frame.getContentPane().add(btnNewButton);
		
		lblMotDePasse = new JLabel("Mot de passe oubli\u00E9!");
		lblMotDePasse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				IndicationPasse indPass = new IndicationPasse();
				indPass.setVisible(true);
				indPass.setLocationRelativeTo(null);
			}
		});
		lblMotDePasse.setBounds(172, 144, 124, 25);
		frame.getContentPane().add(lblMotDePasse);
		
		lblPwd = new JLabel("pwd");
		lblPwd.setForeground(Color.WHITE);
		lblPwd.setBounds(72, 126, 46, 14);
		frame.getContentPane().add(lblPwd);
		
		lblUser = new JLabel("user");
		lblUser.setForeground(Color.WHITE);
		lblUser.setBounds(72, 93, 46, 14);
		frame.getContentPane().add(lblUser);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Public\\Pictures\\Sample Pictures\\Jellyfish.jpg"));
		lblNewLabel.setBounds(0, 0, 434, 262);
		frame.getContentPane().add(lblNewLabel);
	}
}
