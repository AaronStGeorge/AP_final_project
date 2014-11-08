package builder.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class classSched extends JFrame {

	JPanel contentPane;
	private JTextField textClass;
	private JTextField textTeach;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					classSched frame = new classSched();
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
	public classSched() {
		setTitle("Schedule A Class");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textClass = new JTextField();
		textClass.setBounds(100, 28, 160, 22);
		contentPane.add(textClass);
		textClass.setColumns(10);

		JLabel lblClassName = new JLabel("Class name:");
		lblClassName.setBounds(12, 31, 84, 16);
		contentPane.add(lblClassName);

		textTeach = new JTextField();
		textTeach.setBounds(100, 57, 160, 22);
		contentPane.add(textTeach);
		textTeach.setColumns(10);

		JLabel lblInstructor = new JLabel("Instructor:");
		lblInstructor.setBounds(12, 60, 84, 16);
		contentPane.add(lblInstructor);

		JTextArea classDesc = new JTextArea();
		classDesc.setBounds(12, 121, 248, 78);
		contentPane.add(classDesc);

		JLabel lblClassDescription = new JLabel("Class description:");
		lblClassDescription.setBounds(12, 92, 195, 16);
		contentPane.add(lblClassDescription);

		JComboBox comboBoxEquip = new JComboBox();
		comboBoxEquip.setBounds(324, 57, 200, 22);
		contentPane.add(comboBoxEquip);

		JTextArea equipAdded = new JTextArea();
		equipAdded.setBounds(324, 121, 200, 200);
		contentPane.add(equipAdded);

		JLabel lblEquipmentNeeded = new JLabel("Equipment needed:");
		lblEquipmentNeeded.setBounds(324, 31, 200, 16);
		contentPane.add(lblEquipmentNeeded);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			//action listener for the add button (to add from drop down box to the text area)
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAdd.setBounds(377, 88, 97, 25);
		contentPane.add(btnAdd);

		JButton btnSched = new JButton("Schedule!");
		btnSched.addActionListener(new ActionListener() {
			//action listener for the schedule button
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSched.setBounds(22, 267, 195, 34);
		contentPane.add(btnSched);

		JButton btnNewEquip = new JButton("New Equipment");
		btnNewEquip.addActionListener(new ActionListener() {
			//action listener for new equip button
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
                contentPane.invalidate();
                NewEquip obj = new NewEquip();
                obj.contentPane.setVisible(true);
                contentPane.add(obj.contentPane);
                ((JPanel) contentPane).revalidate();
                contentPane.repaint();
			}
		});
		btnNewEquip.setBounds(22, 220, 195, 34);
		contentPane.add(btnNewEquip);
	}
}
