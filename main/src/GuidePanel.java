import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuidePanel extends JDialog {

	public GuidePanel(Frame frame, String title, boolean modality) {
		
		super(frame, title, modality);

		StringBuilder sb = new StringBuilder();
		setPreferredSize(new Dimension(700, 780));

		// Create components
        sb.append("<html><center><u><p>User Guide</p></u></center><br><p><u>Accounts:</u></p><p>Add Account: Select the");
        sb.append(" 'Add Account' option and supply all account information including:</p><ul><li>Account name");
        sb.append("<li>Initial account balance (decimal amount)<li>Email associated with account<li>Account description</ul>");
        sb.append("<p>Created accounts will be available for viewing transactions, account information, and adding/deleting transactions.</p>");
        sb.append( "<br><p>Delete Account: Select the 'Delete Account' option and choose the account to be removed from the drop down list.</p>");
        sb.append("<br><p><u>Transactions:</u></p><p>Add Transaction: Select the 'Add' option listed beneath Transactions ");
        sb.append("and supply all transaction information including:</p><ul><li>Account associated with transaction");
        sb.append("<li>Transaction date<li>Transaction amount<li>Transaction type (check, credit card, or cash)<li>Transaction code");
        sb.append("<li>Credit or debit amount</ul><p>Created transactions will be viewable in the transaction list and available for deletion once added.</p>");
		sb.append("<br><p>Delete Transaction: Select the 'Delete' option listed beneath Transactions and choose the transaction to be removed</p>");
		sb.append("<p>from the drop down list.</p><br><p>Add New Code: Select the 'Add New Code' option listed beneath Transactions and supply a custom 6-digit transaction</p>");
        sb.append("<p>code to be used when adding transactions.</p><br><p><u>Benefits Calculator:</u></p><p>Select 'Benefits Calculator' option and supply amount and percentage to be calculated.</p>");
		sb.append("<p>Select the 'Calculate' option to calculate and view the result.</p><br><p><u>Save Function:</u></p>");
		sb.append("<p>Manually saves all user created accounts and associated transactions.</p><p>Choose 'File -> Save' from the menu bar to use this function.</p>");
        sb.append("<br><p><u>Table Filtering: </u></p><p>The transaction table located in the main window can be filtered by specific accounts (left drop-down box) and their</p>");
        sb.append("<p> respective transaction types (right radio buttons)</html>");

        JLabel guideLabel = new JLabel(sb.toString());
		guideLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel guidePanel = new JPanel();
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		JButton contactButton = new JButton("Contact Us");
		JButton okButton = new JButton("Ok");
		
		contactButton.addActionListener(new contactAction());
		okButton.addActionListener(new okAction());
		
		// Add components
		add(mainPanel);
		
		mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
		mainPanel.add(guidePanel, BorderLayout.CENTER);

        bottomPanel.add(okButton);
        bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        bottomPanel.add(contactButton);

		guidePanel.add(guideLabel);

		pack();
		setVisible(true);
	}

	private void close() { this.dispose(); }
	
	class contactAction implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			// Contact action
		}
	}

    class okAction implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            close();
        }
    }
}
