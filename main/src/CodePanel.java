import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CodePanel extends JDialog {
	
	private JTextField codeField;
	private JLabel infoLabel, codeLabel;
	private String userCode;
	private Controller controller;
	private ArrayList<String> customCodes;
	
	public CodePanel(Frame frame, String title, boolean modality) {
		
		super (frame, title, modality);
		
		setPreferredSize(new Dimension(300, 250));
		
		// Creating components
		JLabel infoLabel = new JLabel ("Please enter the transaction code below: ");
		infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel codeLabel = new JLabel ("Code: ");
		codeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		codeField = new JTextField(10);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new addAction());
		
		JPanel mainPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel infoPanel = new JPanel();
		JPanel codePanel = new JPanel();
		
		// Layouts
		centerPanel.setLayout(new GridLayout(5, 0, 90, 0));
		mainPanel.setPreferredSize(new Dimension(300, 400));
		mainPanel.setLayout(new BorderLayout());
		
		// Add components
		add(mainPanel);
		mainPanel.add(addButton, BorderLayout.PAGE_END);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		centerPanel.add(infoPanel);
		centerPanel.add(Box.createRigidArea(new Dimension(10, 20)));
		centerPanel.add(codePanel);
		
		infoPanel.add(Box.createRigidArea(new Dimension(10, 50)));
		infoPanel.add(infoLabel);
		
		codePanel.add(codeLabel);
		codePanel.add(codeField);
		
		pack();
		setVisible(true);
		
	}
	
	private void addCode() throws FileNotFoundException {
		loadCodes();
		userCode = getCode();
		
		for(String code : customCodes) {
			if (userCode.equals(code)) {
				duplicateCodeWarning();
				return;
			}
		}
		
		saveCode();
		
	}
	
	private void loadCodes() throws FileNotFoundException {
		customCodes = controller.loadCustomCodes();
	}
	
	private void saveCode() throws FileNotFoundException {
		FileWriter writer = null;
		try {
			writer = new FileWriter("Codes.txt");
			writer.write(userCode + "\n");
		}
		catch (IOException e) {
			System.out.println("IO Exception: ");
			e.printStackTrace();
		}
		finally {
			try {
				writer.close();
			}
			catch(IOException e) {
				System.out.println("IO Exception: ");
				e.printStackTrace();
			}
		}
		addConfirmation();
	}
	
	public String getCode() { return codeField.getText(); }
	
	private void emptyWarning() {
		
		JOptionPane.showMessageDialog(this, "Code input cannot be empty",
				"Warning!", JOptionPane.WARNING_MESSAGE);
	}
	
	private void invalidCodeWarning() {
		
		JOptionPane.showMessageDialog(this, "Please enter a 6-digit transaction code",
				"Warning!", JOptionPane.WARNING_MESSAGE);
	}
	
	private void duplicateCodeWarning() {
		
		JOptionPane.showMessageDialog(this, "Duplicate code",
				"Warning!", JOptionPane.WARNING_MESSAGE);
	}
	
	private void addConfirmation() {
		JOptionPane.showMessageDialog(this, "Code successfully added",
				"Completed", JOptionPane.WARNING_MESSAGE);
	}
	
	class addAction implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			
			if(codeField.getText().isEmpty()) {
				
				emptyWarning();
				return;
			}
			
			if(!codeField.getText().matches("[0-9]{6}")) {
				
				invalidCodeWarning();
				return;
			}
			
			try {
				addCode();
			} catch (FileNotFoundException e1) {
				System.out.println("FileNotFound Exception: ");
				e1.printStackTrace();
			}
		}
	}

}
