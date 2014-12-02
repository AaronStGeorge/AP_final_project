package scheduling;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Event;
import java.awt.EventQueue;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JFrame;

public class GUI extends JFrame{

	//create variables
	private static JPanel main;
	private JPanel clSched;
	private JPanel aEquip;
	private JPanel dSched;
	static String day;

	//start the GUI
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
		//Testing hard-coding some classes and their equipment for Monday, should return the same result as the test class
		EquipmentCollection eq = new EquipmentCollection();
		ClassCollection c = new ClassCollection("Monday");
		String[] e  ={"bars","floor","rings","tramp"};
		String[] e1 ={"floor","rings"};
		String[] e2 ={"bars","tramp"};
		c.addClass("Paper", "Bobby",  900, 1000, 15 ,e);
		c.addClass("Winning",  "Sam",   915, 1015, 30 ,e1);
		c.addClass("Losing",  "Dean",  915, 1015, 15 ,e);
		c.addClass("Plastic", "Crowley", 1000, 1100, 30 ,e2);
		c.save();
		eq.addEquipment("bars", "Climbing");
		eq.addEquipment("floor", "CleanFloor");
		eq.addEquipment("rings", "1ToRuleThemAll");
		eq.addEquipment("tramp", "BouncyBouncy");
		eq.save();
	}

	//create the main JFrame
	public GUI(){
		setTitle("Scheduling Assistant");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 500);
		mainFrame();
	}

	//*******************************************************************************************************\\
	//JPanel for day selection
	public void mainFrame() {
		main = new JPanel();
		main.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(main);
		main.setLayout(null);

		JLabel lblPickADay = new JLabel("Pick a day to schedule:");
		lblPickADay.setBounds(235, 32, 168, 16);
		main.add(lblPickADay);

		JButton btnMonday = new JButton("Monday");
		btnMonday.addActionListener(new ActionListener() {
			//action listener for Mon
			public void actionPerformed(ActionEvent e) {
				day = "monday";
				main.setVisible(false);
				daySched();
			}
		});
		btnMonday.setBounds(200, 60, 200, 75);
		main.add(btnMonday);

		JButton btnTuesday = new JButton("Tuesday");
		btnTuesday.addActionListener(new ActionListener() {
			//action listener for Tues
			public void actionPerformed(ActionEvent e) {
				day = "tuesday";
				main.setVisible(false);
				daySched();
			}
		});
		btnTuesday.setBounds(200, 135, 200, 75);
		main.add(btnTuesday);

		JButton btnWednesday = new JButton("Wednesday");
		btnWednesday.addActionListener(new ActionListener() {
			//action listener for Wed
			public void actionPerformed(ActionEvent e) {
				day = "wednesday";
				main.setVisible(false);
				daySched();
			}
		});
		btnWednesday.setBounds(200, 210, 200, 75);
		main.add(btnWednesday);

		JButton btnThursday = new JButton("Thursday");
		btnThursday.addActionListener(new ActionListener() {
			//action listener for Thurs
			public void actionPerformed(ActionEvent e) {
				day = "thursday";
				main.setVisible(false);
				daySched();
			}
		});
		btnThursday.setBounds(200, 285, 200, 75);
		main.add(btnThursday);

		JButton btnFriday = new JButton("Friday");
		btnFriday.addActionListener(new ActionListener() {
			//action listener for Fri
			public void actionPerformed(ActionEvent e) {
				day = "friday";
				main.setVisible(false);
				daySched();
			}
		});
		btnFriday.setBounds(200, 360, 200, 75);
		main.add(btnFriday);
	}

	//*******************************************************************************************************\\	
	//JPanel for the day scheduling
	public void daySched() {
		dSched = new JPanel();
		dSched.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(dSched);
		dSched.setLayout(null);
		
		//create variables
		final ClassCollection c = new ClassCollection(day);	
		final EquipmentCollection eq = new EquipmentCollection();
		
		//testing	
		/*String[] e  ={"bars","floor","rings","tramp"};
		String[] e1 ={"floor","rings"};
		String[] e2 ={"bars","tramp"};
		c.addClass("ClassA", "Bobby",  900, 1000, 15 ,e);
		c.addClass("ClassB",  "Sam",   915, 1015, 30 ,e1);
		c.addClass("ClassC",  "Dean",  915, 1015, 15 ,e);
		c.addClass("ClassD", "Crowley", 1000, 1100, 30 ,e2);
		eq.addEquipment("bars", "climbing bars");
		eq.addEquipment("floor", "a clean floor");
		eq.addEquipment("rings", "one to rule them all");
		eq.addEquipment("tramp", "bouncy bouncy");*/
		
		final Class[] allClasses = c.getClasses();
		final ArrayList<Class> neededClasses = new ArrayList<Class>();
		final String[] allEquip = eq.getEquipmentList();
		
		final JComboBox<String> classBox = new JComboBox<String>();
		classBox.setBounds(12, 13, 200, 37);
		dSched.add(classBox);
		for (int x=0; x < allClasses.length; x++){
			classBox.addItem(allClasses[x].getName());
		}

		final JComboBox<String> schedBox = new JComboBox<String>();
		schedBox.setBounds(363, 13, 200, 37);
		dSched.add(schedBox);
		for (int x=0; x < neededClasses.size(); x++){
			schedBox.addItem(neededClasses.get(x).getName());
		}

		final JTextArea txtClasses = new JTextArea();
		txtClasses.setBounds(12, 63, 200, 302);
		dSched.add(txtClasses);
		txtClasses.setEditable(false);
		for (int x=0; x < allClasses.length; x++){
			txtClasses.append(allClasses[x].getName() + "\n");
		}

		final JTextArea txtSched = new JTextArea();
		txtSched.setBounds(363, 63, 200, 300);
		dSched.add(txtSched);
		txtSched.setEditable(false);
		for (int x=0; x < neededClasses.size(); x++){
			txtSched.append(neededClasses.get(x).getName());
		}
		
		JButton btnAdd = new JButton("Add -->");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int a = classBox.getSelectedIndex();
				neededClasses.add(allClasses[a]);
				schedBox.addItem(classBox.getSelectedItem().toString());
				txtSched.append(classBox.getSelectedItem().toString() + "\n");
				repaint();
			}
		});
		btnAdd.setBounds(224, 69, 127, 37);
		dSched.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				neededClasses.remove(schedBox.getSelectedIndex());
				//redraw the comboBox
				schedBox.removeAllItems();
				for (int x=0; x < neededClasses.size(); x++){
					schedBox.addItem(neededClasses.get(x).getName());
				}
				//redraw the textarea items
				txtSched.setText("");
				for (int x=0; x < neededClasses.size(); x++){
					txtSched.append((String) neededClasses.get(x).getName() + "\n");
				}
				repaint();
			}
		});
		btnRemove.setBounds(224, 119, 127, 37);
		dSched.add(btnRemove);

		JButton btnSchedule = new JButton("Schedule!");
		btnSchedule.addActionListener(new ActionListener() {
			//pass class list onto the scheduling section of the program
			public void actionPerformed(ActionEvent arg0) {
				Class[] needClassArray = neededClasses.toArray(new Class[neededClasses.size()]);
				Schedule s = new Schedule(allEquip, needClassArray); //pass in the entire equip list, the class checks it anyways
			}
		});
		btnSchedule.setBounds(12, 376, 200, 61);
		dSched.add(btnSchedule);

		JButton btnNewClass = new JButton("New Class");
		btnNewClass.addActionListener(new ActionListener() {
			//create a new class
			public void actionPerformed(ActionEvent arg0) {
				dSched.setVisible(false);
				classSched();
			}
		});
		btnNewClass.setBounds(224, 278, 127, 37);
		dSched.add(btnNewClass);

		JButton btnDeleteClass = new JButton("Delete Class");
		btnDeleteClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//need a delete class method
			}
		});
		btnDeleteClass.setBounds(224, 328, 127, 37);
		dSched.add(btnDeleteClass);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dSched.setVisible(false);
				mainFrame();
			}
		});
		btnCancel.setBounds(363, 376, 200, 61);
		dSched.add(btnCancel);
	}

	//*******************************************************************************************************\\	
	//class scheduling frame
	public void classSched() {
		clSched = new JPanel();
		clSched.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(clSched);
		clSched.setLayout(null);
		
		//create variables
		final EquipmentCollection eq = new EquipmentCollection();
		final ClassCollection c = new ClassCollection(day);
		
		//testing
		/*eq.addEquipment("bars", "climbing bars");
		eq.addEquipment("floor", "a clean floor");
		eq.addEquipment("rings", "one to rule them all");
		eq.addEquipment("tramp", "bouncy bouncy");*/
		
		final String[] allEquipment = eq.getEquipmentList();
		final ArrayList<Class> neededEquipment = new ArrayList<Class>();
		for (int x=0; x<allEquipment.length;x++){
			System.out.println("Equipment Piece " + x + ": " + allEquipment[x]);
		}//make sure all the equipment is there

		final JTextField textClass = new JTextField();
		textClass.setBounds(100, 15, 160, 40);
		clSched.add(textClass);
		textClass.setColumns(10);

		JLabel lblClassName = new JLabel("Class name:");
		lblClassName.setBounds(12, 15, 84, 30);
		clSched.add(lblClassName);

		final JTextField classDesc = new JTextField();
		classDesc.setBounds(100, 70, 160, 40);
		clSched.add(classDesc);

		JLabel lblClassDescription = new JLabel("Instructor:");
		lblClassDescription.setBounds(12, 70, 195, 16);
		clSched.add(lblClassDescription);

		final JTextField textS = new JTextField();
		textS.setBounds(100, 125, 160, 40);
		clSched.add(textS);
		textS.setColumns(10);

		JLabel lblS = new JLabel("Start:");
		lblS.setBounds(12, 125, 84, 30);
		clSched.add(lblS);
		
		final JTextField textE = new JTextField();
		textE.setBounds(100, 180, 160, 40);
		clSched.add(textE);
		textE.setColumns(10);

		JLabel lblE = new JLabel("End:");
		lblE.setBounds(12, 180, 84, 30);
		clSched.add(lblE);
		
		final JTextField textR = new JTextField();
		textR.setBounds(100, 235, 160, 40);
		clSched.add(textR);
		textR.setColumns(10);

		JLabel lblR = new JLabel("Rotations:");
		lblR.setBounds(12, 235, 84, 30);
		clSched.add(lblR);
		
		final JComboBox<String> comboBoxEquip = new JComboBox<String>();
		comboBoxEquip.setBounds(324, 30, 200, 22);
		clSched.add(comboBoxEquip);
		for (int x=0; x < allEquipment.length; x++){
			comboBoxEquip.addItem(allEquipment[x].toString());
		}
		
		final JComboBox<String> cbNEquip = new JComboBox<String>();
		cbNEquip.setBounds(324, 90, 200, 22);
		clSched.add(cbNEquip);

		final JTextArea equipAdded = new JTextArea();
		equipAdded.setBounds(305, 125, 230, 200);
		clSched.add(equipAdded);
		equipAdded.setEditable(false);

		JLabel lblEquipmentNeeded = new JLabel("Equipment needed:");
		lblEquipmentNeeded.setBounds(324, 10, 200, 16);
		clSched.add(lblEquipmentNeeded);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			//action listener for the add button (to add from drop down box to the text area)
			public void actionPerformed(ActionEvent e) {
				neededEquipment.add((Class) comboBoxEquip.getSelectedItem());
				cbNEquip.addItem(comboBoxEquip.getSelectedItem().toString());
				equipAdded.append(comboBoxEquip.getSelectedItem().toString() + "\n");
				repaint();
			}
		});
		btnAdd.setBounds(310, 60, 97, 25);
		clSched.add(btnAdd);
		
		JButton btnRem = new JButton("Remove");
		btnRem.addActionListener(new ActionListener() {
			//action listener for the add button (to add from drop down box to the text area)
			public void actionPerformed(ActionEvent e) {
				neededEquipment.remove(cbNEquip.getSelectedItem());
				//redraw the comboBox
				cbNEquip.removeAllItems();
				for (int x=0; x < neededEquipment.size(); x++){
					cbNEquip.addItem(neededEquipment.get(x).getName());
				}
				//redraw the textarea items
				equipAdded.setText("");
				for (int x=0; x < neededEquipment.size(); x++){
					equipAdded.append((String) neededEquipment.get(x).getName() + "\n");
				}
				repaint();
			}
		});
		btnRem.setBounds(430, 60, 97, 25);
		clSched.add(btnRem);
		
		JButton btnMenu = new JButton("Back");
		btnMenu.addActionListener(new ActionListener() {
			//action listener for the menu button
			public void actionPerformed(ActionEvent e) {
				clSched.setVisible(false);
				daySched();
			}
		});
		btnMenu.setBounds(22, 350, 195, 34);
		clSched.add(btnMenu);
		
		JButton btnSched = new JButton("Create!");
		btnSched.addActionListener(new ActionListener() {
			//action listener for the schedule button
			public void actionPerformed(ActionEvent e) {
				String[] needEquipArray = new String[neededEquipment.size()];
				for (int x = 0; x < needEquipArray.length; x++){
					needEquipArray[x] = neededEquipment.get(x).getName();
				}
				int st = (int) Double.parseDouble(textS.getText());
				int en = (int) Double.parseDouble(textE.getText());
				int ro = (int) Double.parseDouble(textR.getText());
				c.addClass(textClass.getText(), classDesc.getText(),  st, en, ro , needEquipArray);
				c.save();
			}
		});
		btnSched.setBounds(22, 400, 195, 34);
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
		

		JButton btnDelEquip = new JButton("Delete Equipment");
		btnDelEquip.addActionListener(new ActionListener() {
			//action listener for delete equip button
			public void actionPerformed(ActionEvent arg0) {
				//Need a way to delete a piece of equip
			}
		});
		btnDelEquip.setBounds(330, 400, 195, 34); 
		clSched.add(btnDelEquip);
	}

	//*******************************************************************************************************\\	
	//new equipment frame
	public void newEquip() {
		aEquip = new JPanel();
		aEquip.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(aEquip);
		aEquip.setLayout(null);
		
		//create variables
		final EquipmentCollection eq = new EquipmentCollection();

		JLabel lblNewEquipmentName = new JLabel("New Equipment Name:");
		lblNewEquipmentName.setBounds(25, 22, 169, 14);
		aEquip.add(lblNewEquipmentName);

		final JTextField equipName = new JTextField();
		equipName.setBounds(25, 47, 169, 20);
		aEquip.add(equipName);
		equipName.setColumns(10);

		JLabel lblShortDescription = new JLabel("Short Description:");
		lblShortDescription.setBounds(25, 78, 169, 14);
		aEquip.add(lblShortDescription);

		final JTextArea equipDesc = new JTextArea();
		equipDesc.setBounds(25, 103, 380, 88);
		aEquip.add(equipDesc);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			//action listener for submit button
			public void actionPerformed(ActionEvent e) {
				eq.addEquipment(equipName.getText(), equipDesc.getText());
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
