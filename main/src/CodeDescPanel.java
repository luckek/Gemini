
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CodeDescPanel extends JDialog {
    
    public CodeDescPanel(Frame frame, String title, boolean modality) {
        
        super(frame, title, modality);
        setPreferredSize(new Dimension(350, 400));
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel descriptionPanel = new JPanel();

        JPanel bottomPanel = new JPanel();

        JButton okButton = new JButton("Ok");
        getRootPane().setDefaultButton(okButton);
        
        JLabel descriptionLabel = new JLabel("<html><br><p><u>Income Codes:</p></u>"
                    +"<p>50109 Other Income</p>"
                    +"<p>50287 Credit Card Sales</p>"
                + "<br><p><u>Expense Codes:</u></p>"
                    + "<p>61123 Contract Faculty</p>"
                    + "<p>61225 Student</p>"
                    + "<p>62210 Minor Equipment</p>"
                    + "<p>62241 Office Supplies</p>"
                    + "<p>62245 Computer Equipment <$5,000</p>"
                    + "<p>62249 Minor Software <$100,000</p>"
                    + "<p>62255 Promotional Ads</p>"
                    + "<p>62280 Program Expense</p>"
                    + "<p>62282 Ink</p>"
                    + "<p>62315 Advertising-Newspaper Non Refundable</p>"
                    + "<p>62817 Meetings & Conference Costs</p>"
                    + "<p>62852 Bank Service Charges</p></html>");
        descriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Add all the components
        add(mainPanel);

        mainPanel.add(descriptionPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.PAGE_END);

        descriptionPanel.add(descriptionLabel);

        bottomPanel.add(okButton);

        okButton.addActionListener(new okAction());

        pack();
        setVisible(true);
    }

    private void close() { this.dispose(); }

    class okAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            close();
        }
    }
}