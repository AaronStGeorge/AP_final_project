package builder.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainFrame extends JFrame {

	private static JPanel main;
	static mainFrame frame1 = new mainFrame();
	static classSched frame2 = new classSched();
	static newEquip frame3 = new newEquip();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame1.setVisible(true);
					frame2.setVisible(false);
					frame3.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainFrame() {
		setTitle("Day Select");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 450);
		main = new JPanel();
		main.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(main);
		main.setLayout(null);

		JButton btnMonday = new JButton("Monday");
		btnMonday.addActionListener(new ActionListener() {
			//action listener for Mon
			public void actionPerformed(ActionEvent e) {
				classSched frame2 = new classSched();
				frame1.dispose();
				frame2.setVisible(true);
			}
		});
		btnMonday.setBounds(96, 60, 130, 50);
		main.add(btnMonday);

		JButton btnTuesday = new JButton("Tuesday");
		btnTuesday.addActionListener(new ActionListener() {
			//action listener for Tues
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				frame2.setVisible(true);
			}
		});
		btnTuesday.setBounds(96, 109, 130, 50);
		main.add(btnTuesday);

		JButton btnWednesday = new JButton("Wednesday");
		btnWednesday.addActionListener(new ActionListener() {
			//action listener for Wed
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				frame2.setVisible(true);
			}
		});
		btnWednesday.setBounds(96, 160, 130, 50);
		main.add(btnWednesday);

		JButton btnThursday = new JButton("Thursday");
		btnThursday.addActionListener(new ActionListener() {
			//action listener for Thurs
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				frame2.setVisible(true);
			}
		});
		btnThursday.setBounds(96, 214, 130, 50);
		main.add(btnThursday);

		JButton btnFriday = new JButton("Friday");
		btnFriday.addActionListener(new ActionListener() {
			//action listener for Fri
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				frame2.setVisible(true);
			}
		});
		btnFriday.setBounds(96, 260, 130, 50);
		main.add(btnFriday);

		JLabel lblPickADay = new JLabel("Pick a day to schedule:");
		lblPickADay.setBounds(96, 32, 168, 16);
		main.add(lblPickADay);
	}
}
