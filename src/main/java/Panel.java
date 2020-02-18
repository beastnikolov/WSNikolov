import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Base64;

public class Panel extends JPanel {
    private JLabel label;
    private GridBagConstraints gridBagConstraints;
    private JButton button;
    private JTextField jTextField;
    private JTextArea jTextArea;
    private Request request;

    public Panel() {
        setBackground(Color.white);
        gridBagConstraints = new GridBagConstraints();
        request = new Request();
        setLayout(new GridBagLayout());
        paintComponents();
    }

    private void paintComponents() {
        JButton buttonSend;
        final JTextField urlTextField;
        JLabel httpCodeLabel;
        JLabel logo;

        logo = new JLabel(new ImageIcon("src/main/java/logo/logo.png"));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(5,5,5,5);
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        add(logo,gridBagConstraints);


        label = new JLabel("Target URL");
        label.setForeground(Color.black);
        label.setFont(new Font("MS Gothic", Font.PLAIN, 14));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(label,gridBagConstraints);

        urlTextField = new JTextField(60);
        urlTextField.setFont(new Font("MS Gothic", Font.PLAIN, 12));
        urlTextField.setForeground(Color.black);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        add(urlTextField,gridBagConstraints);

        JPanel optionsPanel = new JPanel(new GridBagLayout());
        optionsPanel.setBackground(Color.white);

        String[] methodOptions = {"GET","POST"};
        final JComboBox methodChoice = new JComboBox(methodOptions);
        methodChoice.setSelectedIndex(0);
        methodChoice.setFont(new Font("MS Gothic", Font.PLAIN, 12));
        gridBagConstraints.weightx = 1;

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        optionsPanel.add(methodChoice,gridBagConstraints);

        String[] recieveOptions = {"Receive String","Receive PDF","Receive Image"};
        final JComboBox recieveChoice = new JComboBox(recieveOptions);
        recieveChoice.setSelectedIndex(0);
        recieveChoice.setFont(new Font("MS Gothic", Font.PLAIN, 12));
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        optionsPanel.add(recieveChoice,gridBagConstraints);

        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridx = 0;
        add(optionsPanel,gridBagConstraints);

        buttonSend = new JButton("Send");
        buttonSend.setFont(new Font("MS Gothic", Font.PLAIN, 12));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        add(buttonSend,gridBagConstraints);



        httpCodeLabel = new JLabel("HTTP Response Code: ");
        httpCodeLabel.setForeground(Color.red);
        httpCodeLabel.setFont(new Font("MS Gothic", Font.PLAIN, 12));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        add(httpCodeLabel,gridBagConstraints);

        jTextArea = new JTextArea(20,40);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        jTextArea.setForeground(Color.black);
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);
        jTextArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        final JScrollPane areaScrollPane = new JScrollPane(jTextArea);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(400, 300));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        add(areaScrollPane,gridBagConstraints);


        request.setHttpcode(httpCodeLabel);

        // Action listeners

        buttonSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String url = urlTextField.getText();
                String userInput = jTextArea.getText();
                if (!(url.equalsIgnoreCase("")) && methodChoice.getSelectedItem().toString().equalsIgnoreCase("POST") && !(userInput.equalsIgnoreCase(""))) {
                    System.out.println("Sending POST request to: " + url);
                    File file = new File("");
                   // byte[] decoder = Base64.getDecoder().decode()
                    JOptionPane.showMessageDialog(null, request.requestPOST(userInput,url));

                }
            }
        });


    }


}
