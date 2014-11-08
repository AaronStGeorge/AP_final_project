package builder.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewEquip extends JFrame {

	JPanel contentPane;
	private JTextField equipName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewEquip frame = new NewEquip();
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
	public NewEquip() {
		setTitle("Add Equipment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewEquipmentName = new JLabel("New Equipment Name:");
		lblNewEquipmentName.setBounds(25, 22, 169, 14);
		contentPane.add(lblNewEquipmentName);
		
		equipName = new JTextField();
		equipName.setBounds(25, 47, 169, 20);
		contentPane.add(equipName);
		equipName.setColumns(10);
		
		JLabel lblShortDescription = new JLabel("Short Description:");
		lblShortDescription.setBounds(25, 78, 169, 14);
		contentPane.add(lblShortDescription);
		
		JTextArea equipDesc = new JTextArea();
		equipDesc.setBounds(25, 103, 380, 88);
		contentPane.add(equipDesc);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			//action listener for sumbit button
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSubmit.setBounds(25, 215, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			//action listener for the cancel button
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCancel.setBounds(316, 215, 89, 23);
		contentPane.add(btnCancel);
	}
}
