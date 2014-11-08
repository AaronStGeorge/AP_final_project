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

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
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
	public mainFrame() {
		setTitle("Day Select");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnMonday = new JButton("Monday");
		btnMonday.addActionListener(new ActionListener() {
			//action listener for Mon
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
                contentPane.invalidate();
                classSched obj = new classSched();
                obj.contentPane.setVisible(true);
                contentPane.add(obj.contentPane);
                ((JPanel) contentPane).revalidate();
                contentPane.repaint();
			}
		});
		btnMonday.setBounds(96, 60, 130, 50);
		contentPane.add(btnMonday);
		
		JButton btnTuesday = new JButton("Tuesday");
		btnTuesday.addActionListener(new ActionListener() {
			//action listener for Tues
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
                contentPane.invalidate();
                classSched obj = new classSched();
                obj.contentPane.setVisible(true);
                contentPane.add(obj.contentPane);
                ((JPanel) contentPane).revalidate();
                contentPane.repaint();
			}
		});
		btnTuesday.setBounds(96, 109, 130, 50);
		contentPane.add(btnTuesday);
		
		JButton btnWednesday = new JButton("Wednesday");
		btnWednesday.addActionListener(new ActionListener() {
			//action listener for Wed
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
                contentPane.invalidate();
                classSched obj = new classSched();
                obj.contentPane.setVisible(true);
                contentPane.add(obj.contentPane);
                ((JPanel) contentPane).revalidate();
                contentPane.repaint();
			}
		});
		btnWednesday.setBounds(96, 160, 130, 50);
		contentPane.add(btnWednesday);
		
		JButton btnThursday = new JButton("Thursday");
		btnThursday.addActionListener(new ActionListener() {
			//action listener for Thurs
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
                contentPane.invalidate();
                classSched obj = new classSched();
                obj.contentPane.setVisible(true);
                contentPane.add(obj.contentPane);
                ((JPanel) contentPane).revalidate();
                contentPane.repaint();
			}
		});
		btnThursday.setBounds(96, 214, 130, 50);
		contentPane.add(btnThursday);
		
		JButton btnFriday = new JButton("Friday");
		btnFriday.addActionListener(new ActionListener() {
			//action listener for Fri
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
                contentPane.invalidate();
                classSched obj = new classSched();
                obj.contentPane.setVisible(true);
                contentPane.add(obj.contentPane);
                ((JPanel) contentPane).revalidate();
                contentPane.repaint();
			}
		});
		btnFriday.setBounds(96, 260, 130, 50);
		contentPane.add(btnFriday);
		
		JLabel lblPickADay = new JLabel("Pick a day to schedule:");
		lblPickADay.setBounds(96, 32, 168, 16);
		contentPane.add(lblPickADay);
	}
}
