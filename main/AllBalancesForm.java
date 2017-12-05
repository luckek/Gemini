import javax.swing.*;
import java.awt.*;

public class AllBalancesForm extends JDialog {

    String[] columnHeaders = {"Name", "Balance"};

    public AllBalancesForm(Frame frame, String title, boolean modality, String[][] allInfo) {

        super(frame, title, modality);

        setPreferredSize(new Dimension(330, 300));

        JPanel infoPanel = new JPanel();

        // Setting up info table
        JTable acctInfoTable = new JTable(allInfo, columnHeaders);
        acctInfoTable.setPreferredScrollableViewportSize(new Dimension(300, 300));

        JScrollPane infoPane =  new JScrollPane(acctInfoTable);

        // Populating panels / content pane
        add(infoPanel);

        infoPanel.add(infoPane);

        pack();
        setVisible(true);
    }
}
