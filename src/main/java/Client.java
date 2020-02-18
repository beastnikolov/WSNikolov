import javax.swing.*;
import java.awt.*;

public class Client extends JFrame {
    Panel panel;

    public Client() {
        panel = new Panel();
        paintWindow();
    }

    private void paintWindow() {
        getContentPane().setBackground(Color.white);
        setTitle("WebService Nikolov");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1000,800));
        setResizable(false);
        paintContents();
        pack();
        setVisible(true);
    }

    private void paintContents() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 14;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10,10,10,10);
        add(panel,gbc);

    }

    public static void main(String[] args) {
        Client client = new Client();
    }
}
