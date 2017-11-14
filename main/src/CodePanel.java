import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CodePanel extends JDialog {

	private JTextField codeField;
	private JLabel infoLabel, codeLabel;
	private String userCode;
	
	public CodePanel(Frame frame, String title, boolean modality) {
		
		super(frame, title, modality);
		
		setPreferredSize(new Dimension(300, 250));
		
		// Creating components
		JLabel infoLabel = new JLabel("Please enter the transaction code below: ");
		infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel codeLabel = new JLabel("Transaction Code: ");
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
	
	public String getCode() { return codeField.getText(); }
	
	private void emptyWarning() {
		
		JOptionPane.showMessageDialog(this, "Code input cannot be empty",
				"Warning!", JOptionPane.WARNING_MESSAGE);
	}
	
	private void invalidCodeWarning() {
		
		JOptionPane.showMessageDialog(this, "Please enter a 4-digit transaction code",
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
			
			if(!codeField.getText().matches("[0-9]{4}")) {
				
				invalidCodeWarning();
				return;
			}
		}
	}
}
