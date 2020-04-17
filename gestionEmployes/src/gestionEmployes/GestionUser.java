package gestionEmployes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class GestionUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	Connection cnx = null;
	PreparedStatement prepared =null;
	ResultSet rs = null;
	String userOld= null;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionUser frame = new GestionUser();
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
	public GestionUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 591, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx =  ConnectionBD.connectDb();
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
			/*	String sql = "select password from utilisateur where username=?";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1,textField.getText().toString());
					rs = prepared.executeQuery();
					if(rs.next()){
					String password = rs.getString("password");
					textField_1.setText(password);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
			}
		});
		textField.setBounds(81, 87, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(81, 118, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("username");
		lblNewLabel.setBounds(23, 90, 75, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("password");
		lblNewLabel_1.setBounds(23, 121, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("ajouter");
		btnNewButton.setIcon(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql= "insert into utilisateur (username,password) values (?,?) ";
				
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1,textField.getText().toString() );
					prepared.setString(2,textField_1.getText().toString() );
					if(!textField.getText().equals("")||!textField_1.getText().equals("")){
					 prepared.execute();
					 JOptionPane.showMessageDialog(null, "utilisateur ajouté avec succes");
					}
					else {
						 JOptionPane.showMessageDialog(null, "remplir les champs vides!!!");
					}
					 textField.setText("");
					 textField_1.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(173, 69, 89, 20);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("modifier");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String sql = "update utilisateur  set username=?,password=? where username = '"+userOld+"'";
				
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1,textField.getText().toString());
					prepared.setString(2,textField_1.getText().toString());
					if(!textField.getText().equals("")||!textField_1.getText().equals("")){
					prepared.execute();
					
					JOptionPane.showMessageDialog(null,"utilisateur mis à jour");
					}
					else {
						JOptionPane.showMessageDialog(null,"remplir les champs vides !!!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(173, 100, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("supprimer");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "delete from utilisateur where username=? and password=?";
				try {
					prepared=cnx.prepareStatement(sql);
					prepared.setString(1,textField.getText().toString() );
					prepared.setString(2,textField_1.getText().toString() );
					if(!textField.getText().equals("")||!textField_1.getText().equals("")){
					 prepared.execute();
					 JOptionPane.showMessageDialog(null, "utilisateur supprime!!!");
					}
					else {
						JOptionPane.showMessageDialog(null,"remplir les champs vides !!!");
					}
					 textField.setText("");
					 textField_1.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setBounds(173, 137, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(278, 11, 287, 232);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int ligne = table.getSelectedRow();
				 userOld=table.getValueAt(ligne, 0).toString();
				String password=table.getValueAt(ligne, 1).toString();
				
				textField.setText(userOld);
				textField_1.setText(password);
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_3 = new JButton("actualiser");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateTable();
			}
		});
		btnNewButton_3.setBounds(375, 254, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnRetour = new JButton("retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuAdmin menuAdmin = new MenuAdmin();
				menuAdmin.setVisible(true);
				menuAdmin.setLocationRelativeTo(null);
			}
		});
		btnRetour.setBounds(78, 254, 89, 23);
		contentPane.add(btnRetour);
	}
	public void  updateTable(){
		
		String sql= "select * from utilisateur";
		try {
			prepared=cnx.prepareStatement(sql);
			rs=prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
