package gestionEmployes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class MenuAdmin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdmin frame = new MenuAdmin();
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
	public MenuAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Gestion Employes");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Public\\Pictures\\Sample Pictures\\GestionEmp.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionEmp gestionEmp = new GestionEmp();
				gestionEmp.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 21, 195, 208);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Gestion Utilisateurs");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\Public\\Pictures\\Sample Pictures\\user.jpg"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionUser gestionUser = new GestionUser();
				gestionUser.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(254, 21, 170, 208);
		contentPane.add(btnNewButton_1);
	}

}
