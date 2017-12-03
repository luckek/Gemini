import javax.swing.*;
import java.awt.*;

public class AllAcctsForm extends JDialog {

    String[] columnHeaders = {"Name", "Balance"};

    public AllAcctsForm(Frame frame, String title, boolean modality, String[][] allInfo) {

        super(frame, title, modality);

        setPreferredSize(new Dimension(300, 300));


        JPanel infoPanel = new JPanel();
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Setting up info table
        JTable acctInfoTable = new JTable(allInfo, columnHeaders);
        acctInfoTable.setPreferredScrollableViewportSize(new Dimension(200, 200));

        JScrollPane infoPane =  new JScrollPane(acctInfoTable);

        // Populating panels / content pane
        add(mainPanel);

        mainPanel.add(infoPanel);

        infoPanel.add(infoPane);

        pack();
        setVisible(true);
    }

}
