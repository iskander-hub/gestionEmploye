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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class GestionEmp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	Connection cnx = null;
	PreparedStatement prepared =null;
	ResultSet rs = null;
	private JTable table;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionEmp frame = new GestionEmp();
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
	public GestionEmp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = ConnectionBD.connectDb();
		
		textField = new JTextField();
		textField.setBounds(78, 72, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(78, 103, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(78, 134, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(78, 165, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(78, 196, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("nom");
		lblNewLabel.setBounds(10, 78, 58, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("prenom");
		lblNewLabel_1.setBounds(10, 109, 58, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("age");
		lblNewLabel_2.setBounds(10, 137, 58, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("nb_heure");
		lblNewLabel_3.setBounds(10, 171, 58, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("prix_heure");
		lblNewLabel_4.setBounds(10, 202, 58, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql= "insert into employe (nom_emp,prenom_emp,age,nb_heure,prix_heure) values (?,?,?,?,?) ";
				
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1,textField.getText().toString() );
					prepared.setString(2,textField_1.getText().toString() );
					prepared.setString(3,textField_2.getText().toString() );
					prepared.setString(4,textField_3.getText().toString() );
					prepared.setString(5,textField_4.getText().toString() );
					if(!textField.getText().equals("")||!textField_1.getText().equals("")||!textField_2.getText().equals("")||!textField_3.getText().equals("")||!textField_4.getText().equals("")){
					 prepared.execute();
					 JOptionPane.showMessageDialog(null, "employé ajouté avec succes");
					}
					else{
						JOptionPane.showMessageDialog(null, "remplir les champs vides!!!");
					}
					 textField.setText("");
					 textField_1.setText("");
					 textField_2.setText("");
					 textField_3.setText("");
					 textField_4.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(171, 102, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("modifier");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
String sql = "update employe  set age=? , nb_heure=? , prix_heure=?  where nom_emp=? and prenom_emp=?";
				
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1,textField_2.getText().toString() );
					prepared.setString(2,textField_3.getText().toString() );
					prepared.setString(3,textField_4.getText().toString() );
					prepared.setString(4,textField.getText().toString() );
					prepared.setString(5,textField_1.getText().toString() );
					if(!textField.getText().equals("")||!textField_1.getText().equals("")||!textField_2.getText().equals("")||!textField_3.getText().equals("")||!textField_4.getText().equals("")){
					prepared.execute();
					JOptionPane.showMessageDialog(null,"utilisateur mis à jour");
					}
					else {
						JOptionPane.showMessageDialog(null, "remplir les champs vides!!!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(171, 133, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("supprimer");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "delete from employe where nom_emp=? and prenom_emp=? and age=? and nb_heure=? and prix_heure=?  ";
				
				try {
					prepared=cnx.prepareStatement(sql);
					prepared.setString(1,textField.getText().toString() );
					prepared.setString(2,textField_1.getText().toString() );
					prepared.setString(3,textField_2.getText().toString() );
					prepared.setString(4,textField_3.getText().toString() );
					prepared.setString(5,textField_4.getText().toString() );
					if(!textField.getText().equals("")||!textField_1.getText().equals("")||!textField_2.getText().equals("")||!textField_3.getText().equals("")||!textField_4.getText().equals("")){
					 prepared.execute();
					 JOptionPane.showMessageDialog(null, "employe supprime!!!");
					}
					else {
						JOptionPane.showMessageDialog(null, "remplir les champs vides!!!");
					}
					 textField.setText("");
					 textField_1.setText("");
					 textField_2.setText("");
					 textField_3.setText("");
					 textField_4.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(171, 164, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(270, 11, 392, 273);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int ligne = table.getSelectedRow();
				String nom_emp=table.getValueAt(ligne, 1).toString();
				String prenom_emp=table.getValueAt(ligne, 2).toString();
				String age=table.getValueAt(ligne, 3).toString();
				String nb_heure=table.getValueAt(ligne, 4).toString();
				String prix_heure=table.getValueAt(ligne, 5).toString();
				
				
				textField.setText(nom_emp);
				textField_1.setText(prenom_emp);
				 textField_2.setText(age);
				 textField_3.setText(nb_heure);
				 textField_4.setText(prix_heure);
				 textField_5.setText("");
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_3 = new JButton("actualiser");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
		btnNewButton_3.setBounds(332, 305, 89, 23);
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
		btnRetour.setBounds(171, 305, 89, 23);
		contentPane.add(btnRetour);
		
		textField_5 = new JTextField();
		textField_5.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				float nb_heure= Float.parseFloat(textField_3.getText());
				float prix_heure = Float.parseFloat(textField_4.getText());
				float res = nb_heure * prix_heure;
				textField_5.setText(String.valueOf(res));
			}
		});
		textField_5.setBounds(78, 227, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblSalaire = new JLabel("salaire");
		lblSalaire.setBounds(10, 230, 46, 14);
		contentPane.add(lblSalaire);
		
	}
public void  updateTable(){
		
		String sql= "select * from employe";
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