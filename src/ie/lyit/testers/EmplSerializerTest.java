package ie.lyit.testers;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import ie.lyit.serialize.EmployeeSerializer;

public class EmplSerializerTest extends JFrame {
	
	/**
	 * Added to keep eclipse happy
	 */
	private static final long serialVersionUID = 4213555715285104871L;
	
	private ArrayList<JButton> buttons;
	private EmployeeSerializer serializer;
	
	public EmplSerializerTest() {
		//set layout of menu
		setLayout(new GridLayout(6, 1));
		
		// initialise button arraylist and add buttons
		buttons = new ArrayList<>();
		buttons.add(new JButton("1. Add"));
		buttons.add(new JButton("2. List"));
		buttons.add(new JButton("3. View"));
		buttons.add(new JButton("4. Edit"));
		buttons.add(new JButton("5. Delete"));
		buttons.add(new JButton("6. Quit"));
		
		// initialise serializer and deserialise any employees
		serializer = new EmployeeSerializer();
		serializer.deserializeEmployees();
		
		// initialise listener, add listener to each button and add each button to frame
		btnActionListener listener = new btnActionListener();
		for(JButton btn : buttons) {
			btn.addActionListener(listener);
			add(btn);
		}
		
		
	}
	
	public static void main(String[] args) {
		EmplSerializerTest frame = new EmplSerializerTest();
		frame.setSize(400, 400);
		frame.setTitle("Flight Menu");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
	/**
	 * Action Listener for menu buttons
	 * @author Sean
	 *
	 */
	private class btnActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
				case "1. Add":
					serializer.add();
					break;
				case "2. List":
					serializer.list();
					break;
				case "3. View":
					serializer.view();
					break;
				case "4. Edit":
					serializer.edit();
					break;
				case "5. Delete":
					serializer.delete();
					break;
				case "6. Quit":
					serializer.serializeEmployees();
					int option = JOptionPane.showConfirmDialog(null, "Are you sure you wish to quit?", "Quit", JOptionPane.OK_CANCEL_OPTION);
					if(option == JOptionPane.OK_OPTION)
						dispose();
					break;
				
			}
		}
	}

}
