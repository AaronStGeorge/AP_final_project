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

public class newEquip extends JFrame {
	
	//create the variables
	private static JPanel aEquip;
	mainFrame frame1 = new mainFrame();
	classSched frame2 = new classSched();
	newEquip frame3 = new newEquip();
	private JTextField equipName;

	/**
	 * Create the frame.
	 */
	public NewEquip() {
		setTitle("Add Equipment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		aEquip = new JPanel();
		aEquip.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(aEquip);
		aEquip.setLayout(null);
		
		JLabel lblNewEquipmentName = new JLabel("New Equipment Name:");
		lblNewEquipmentName.setBounds(25, 22, 169, 14);
		aEquip.add(lblNewEquipmentName);
		
		equipName = new JTextField();
		equipName.setBounds(25, 47, 169, 20);
		aEquip.add(equipName);
		equipName.setColumns(10);
		
		JLabel lblShortDescription = new JLabel("Short Description:");
		lblShortDescription.setBounds(25, 78, 169, 14);
		aEquip.add(lblShortDescription);
		
		JTextArea equipDesc = new JTextArea();
		equipDesc.setBounds(25, 103, 380, 88);
		aEquip.add(equipDesc);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			//action listener for submit button
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSubmit.setBounds(25, 215, 89, 23);
		aEquip.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			//action listener for the cancel button
			public void actionPerformed(ActionEvent e) {
				frame3.dispose();
				frame2.setVisible(true);
			}
		});
		btnCancel.setBounds(316, 215, 89, 23);
		aEquip.add(btnCancel);
	}
}
