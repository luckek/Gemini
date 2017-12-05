import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AllBalancesForm extends JDialog {

    String[] columnHeaders = {"Name", "Balance"};

    public AllBalancesForm(Frame frame, String title, boolean modality, String[][] allInfo) {

        super(frame, title, modality);

        setPreferredSize(new Dimension(330, 300));

        JPanel infoPanel = new JPanel();
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Setting up info table
        JTable acctInfoTable = new JTable(allInfo, columnHeaders);
        acctInfoTable.setPreferredScrollableViewportSize(new Dimension(300, 300));

        JScrollPane infoPane =  new JScrollPane(acctInfoTable);

        JButton okButton = new JButton("Ok");
        okButton.setPreferredSize(new Dimension(50, 25));

        // Populating panels / content pane
        add(infoPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);

        infoPanel.add(infoPane);

        bottomPanel.add(okButton);

        okButton.addActionListener(new okAction());
        getRootPane().setDefaultButton(okButton);

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
