import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuidePanel extends JDialog {

	public GuidePanel(Frame frame, String title, boolean modality) {
		
		super(frame, title, modality);
		
		setPreferredSize(new Dimension(700, 740));
		
		// Create components
		JLabel guideLabel = new JLabel("<html><center><u><p>User Guide</p></u></center>"
				+ "<br><p><u>Accounts:</u></p>"
					+"<p>Add Account: Select the 'Add Account' option and supply all account information including:</p>"
						+ "<ul><li>Account name"
						+ "<li>Initial account balance (decimal amount)"
						+ "<li>Email associated with account"
						+ "<li>Account description</ul>"
					+ "<p>Created accounts will be available for viewing transactions, account information, and adding/deleting transactions.</p>"
					+ "<br><p>Delete Account: Select the 'Delete Account' option and choose the account to be removed from the drop down list.</p>"
				+ "<br><p><u>Transactions:</u></p>"
					+ "<p>Add Transaction: Select the 'Add' option listed beneath Transactions and supply all transaction information including:</p>"
						+ "<ul><li>Account associated with transaction"
						+ "<li>Transaction date"
						+ "<li>Transaction amount"
						+ "<li>Transaction type (check, credit card, or cash)"
						+ "<li>Transaction code"
						+ "<li>Credit or debit amount</ul>"
					+ "<p>Created transactions will be viewable in the transaction list and available for deletion once added.</p>"
					+ "<br><p>Delete Transaction: Select the 'Delete' option listed beneath Transactions and choose the transaction to be removed</p>"
					+ "<p>from the drop down list.</p>"
					+ "<br><p>Add New Code: Select the 'Add New Code' option listed beneath Transactions and supply a custom 6-digit transaction</p>"
					+ "<p>code to be used when adding transactions.</p>"
				+ "<br><p><u>Benefits Calculator:</u></p>"
					+ "<p>Select 'Benefits Calculator' option and supply amount and percentage to be calculated.</p>"
					+ "<p>Select the 'Calculate' option to calculate and view the result.</p>"
				+ "<br><p><u>Save Function: </u></p>"
					+ "<p>Manually saves all user created accounts and associated transactions.</p>"
					+ "<p>Choose 'File -> Save' from the menu bar to use this function.</p>"
				+ "<br><p><u>Table Filtering: </u></p>"
					+ "<p>The transaction table located in the main window can be filtered by specific accounts (left drop-down box) and their</p>"
					+ "<p> respective transaction types (right radio buttons)</html>");
		guideLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JPanel mainPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel guidePanel = new JPanel();
		JButton contactButton = new JButton("Contact Us");
		
		contactButton.addActionListener(new contactAction());
		
		// Setting layouts
		centerPanel.setLayout(new GridLayout(5, 0, 90, 0));
		mainPanel.setPreferredSize(new Dimension(500,500));
		mainPanel.setLayout(new BorderLayout());
		
		// Add components
		add(mainPanel);
		
		mainPanel.add(contactButton, BorderLayout.PAGE_END);
		mainPanel.add(guidePanel, BorderLayout.CENTER);
		
		guidePanel.add(guideLabel);
		
		
		pack();
		setVisible(true);
	}
	
	class contactAction implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			// Contact action
		}
	}
}
