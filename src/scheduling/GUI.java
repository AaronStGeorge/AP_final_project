package scheduling;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class GUI extends JFrame{
	
	//create variables
	private static JPanel main;
	private JPanel clSched;
	private JPanel aEquip;
	private JTextField textClass;
	private JTextField textTeach;
	private JTextField equipName;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
	
			public void run() {
				try {
					GUI makeFrame = new GUI();
					makeFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//main frame
	public GUI(){
		setTitle("Schedule Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 450);
		mainFrame();
	}
	
	public void mainFrame() {
		main = new JPanel();
		main.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(main);
		main.setLayout(null);
		
		JLabel lblPickADay = new JLabel("Pick a day to schedule:");
		lblPickADay.setBounds(200, 32, 168, 16);
		main.add(lblPickADay);

		JButton btnMonday = new JButton("Monday");
		btnMonday.addActionListener(new ActionListener() {
			//action listener for Mon
			public void actionPerformed(ActionEvent e) {
				main.setVisible(false);
				classSched();
			}
		});
		btnMonday.setBounds(200, 60, 130, 50);
		main.add(btnMonday);

		JButton btnTuesday = new JButton("Tuesday");
		btnTuesday.addActionListener(new ActionListener() {
			//action listener for Tues
			public void actionPerformed(ActionEvent e) {
				main.setVisible(false);
				classSched();
			}
		});
		btnTuesday.setBounds(200, 109, 130, 50);
		main.add(btnTuesday);

		JButton btnWednesday = new JButton("Wednesday");
		btnWednesday.addActionListener(new ActionListener() {
			//action listener for Wed
			public void actionPerformed(ActionEvent e) {
				main.setVisible(false);
				classSched();
			}
		});
		btnWednesday.setBounds(200, 160, 130, 50);
		main.add(btnWednesday);

		JButton btnThursday = new JButton("Thursday");
		btnThursday.addActionListener(new ActionListener() {
			//action listener for Thurs
			public void actionPerformed(ActionEvent e) {
				main.setVisible(false);
				classSched();
			}
		});
		btnThursday.setBounds(200, 214, 130, 50);
		main.add(btnThursday);

		JButton btnFriday = new JButton("Friday");
		btnFriday.addActionListener(new ActionListener() {
			//action listener for Fri
			public void actionPerformed(ActionEvent e) {
				main.setVisible(false);
				classSched();
			}
		});
		btnFriday.setBounds(200, 260, 130, 50);
		main.add(btnFriday);
	}
	
	//class scheduling frame
	public void classSched() {
		clSched = new JPanel();
		clSched.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(clSched);
		clSched.setLayout(null);

		textClass = new JTextField();
		textClass.setBounds(100, 28, 160, 22);
		clSched.add(textClass);
		textClass.setColumns(10);

		JLabel lblClassName = new JLabel("Class name:");
		lblClassName.setBounds(12, 31, 84, 16);
		clSched.add(lblClassName);

		textTeach = new JTextField();
		textTeach.setBounds(100, 57, 160, 22);
		clSched.add(textTeach);
		textTeach.setColumns(10);

		JLabel lblInstructor = new JLabel("Instructor:");
		lblInstructor.setBounds(12, 60, 84, 16);
		clSched.add(lblInstructor);

		JTextArea classDesc = new JTextArea();
		classDesc.setBounds(12, 121, 248, 78);
		clSched.add(classDesc);

		JLabel lblClassDescription = new JLabel("Class description:");
		lblClassDescription.setBounds(12, 92, 195, 16);
		clSched.add(lblClassDescription);

		JComboBox comboBoxEquip = new JComboBox();
		comboBoxEquip.setBounds(324, 57, 200, 22);
		clSched.add(comboBoxEquip);

		JTextArea equipAdded = new JTextArea();
		equipAdded.setBounds(324, 121, 200, 200);
		clSched.add(equipAdded);

		JLabel lblEquipmentNeeded = new JLabel("Equipment needed:");
		lblEquipmentNeeded.setBounds(324, 31, 200, 16);
		clSched.add(lblEquipmentNeeded);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			//action listener for the add button (to add from drop down box to the text area)
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnAdd.setBounds(377, 88, 97, 25);
		clSched.add(btnAdd);

		JButton btnSched = new JButton("Schedule!");
		btnSched.addActionListener(new ActionListener() {
			//action listener for the schedule button
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnSched.setBounds(22, 230, 195, 45);
		clSched.add(btnSched);

		JButton btnNewEquip = new JButton("New Equipment");
		btnNewEquip.addActionListener(new ActionListener() {
			//action listener for new equip button
			public void actionPerformed(ActionEvent arg0) {
				clSched.setVisible(false);
				newEquip();
			}
		});
		btnNewEquip.setBounds(330, 350, 195, 34);
		clSched.add(btnNewEquip);
		
		JButton btnMenu = new JButton("Main Menu");
		btnMenu.addActionListener(new ActionListener() {
			//action listener for the menu button
			public void actionPerformed(ActionEvent e) {
				clSched.setVisible(false);
				mainFrame();
			}
		});
		btnMenu.setBounds(22, 350, 195, 34);
		clSched.add(btnMenu);
	}
	
	//new equipment frame
	public void newEquip() {
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
		btnSubmit.setBounds(25, 215, 150, 40);
		aEquip.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			//action listener for the cancel button
			public void actionPerformed(ActionEvent e) {
				aEquip.setVisible(false);
				classSched();
			}
		});
		btnCancel.setBounds(245, 215, 150, 40);
		aEquip.add(btnCancel);
	}

}
