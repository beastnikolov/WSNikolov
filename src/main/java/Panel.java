import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

        logo = new JLabel(new ImageIcon("src/main/java/icons/logo.png"));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(5,5,5,5);
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        add(logo,gridBagConstraints);

        JPanel urlPanel = new JPanel(new GridBagLayout());
        urlPanel.setBackground(Color.white);


        label = new JLabel("Target URL");
        label.setForeground(Color.black);
        label.setFont(new Font("MS Gothic", Font.PLAIN, 14));
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        urlPanel.add(label,gridBagConstraints);

        urlTextField = new JTextField(60);
        urlTextField.setFont(new Font("MS Gothic", Font.PLAIN, 12));
        urlTextField.setForeground(Color.black);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        urlPanel.add(urlTextField,gridBagConstraints);

    //    JPanel optionsPanel = new JPanel(new GridBagLayout());
   //     optionsPanel.setBackground(Color.white);

        String[] methodOptions = {"GET","POST"};
        final JComboBox methodChoice = new JComboBox(methodOptions);
        methodChoice.setSelectedIndex(0);
        methodChoice.setFont(new Font("MS Gothic", Font.PLAIN, 12));
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        urlPanel.add(methodChoice,gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        add(urlPanel,gridBagConstraints);

        buttonSend = new JButton("Send");
        buttonSend.setFont(new Font("MS Gothic", Font.PLAIN, 12));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        add(buttonSend,gridBagConstraints);

        httpCodeLabel = new JLabel("HTTP Response Code: ");
        httpCodeLabel.setForeground(Color.blue);
        httpCodeLabel.setFont(new Font("MS Gothic", Font.PLAIN, 12));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        add(httpCodeLabel,gridBagConstraints);

        JPanel textAreaPanel = new JPanel(new GridBagLayout());
        textAreaPanel.setBackground(Color.white);

        JLabel inputLabel = new JLabel("User input");
        inputLabel.setForeground(Color.black);
        inputLabel.setFont(new Font("MS Gothic", Font.PLAIN, 12));
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        textAreaPanel.add(inputLabel,gridBagConstraints);

        JLabel outputLabel = new JLabel("Server output");
        outputLabel.setForeground(Color.black);
        outputLabel.setFont(new Font("MS Gothic", Font.PLAIN, 12));
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 1;
        textAreaPanel.add(outputLabel,gridBagConstraints);

        jTextArea = new JTextArea(25,25);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        jTextArea.setForeground(Color.black);
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);
        jTextArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        final JScrollPane areaScrollPane = new JScrollPane(jTextArea);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(300, 200));
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        textAreaPanel.add(areaScrollPane,gridBagConstraints);

        JTextArea outputTextArea = new JTextArea(25,25);
        Border borderouput = BorderFactory.createLineBorder(Color.BLACK);
        outputTextArea.setForeground(Color.black);
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        outputTextArea.setBorder(BorderFactory.createCompoundBorder(borderouput, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        final JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
        outputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        outputScrollPane.setPreferredSize(new Dimension(300, 200));
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        textAreaPanel.add(outputScrollPane,gridBagConstraints);

        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        add(textAreaPanel,gridBagConstraints);


        JPanel outputPanel = new JPanel(new GridBagLayout());
        outputPanel.setBackground(Color.white);


        JButton pdfButton = new JButton(new ImageIcon("src/main/java/icons/pdf.png"));
        pdfButton.setFocusable(false);
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        outputPanel.add(pdfButton,gridBagConstraints);

        JButton imageButton = new JButton(new ImageIcon("src/main/java/icons/imagen.png"));
        imageButton.setFocusable(false);
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 1;
        outputPanel.add(imageButton,gridBagConstraints);

        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setFont(new Font("MS Gothic", Font.PLAIN, 12));
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        outputPanel.add(errorLabel,gridBagConstraints);



        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 0;
        add(outputPanel,gridBagConstraints);

        JPanel examplePanel = new JPanel(new GridBagLayout());
        examplePanel.setBackground(Color.white);

        JButton example1 = new JButton("Example 1");
        example1.setForeground(Color.black);
        example1.setFont(new Font("MS Gothic", Font.PLAIN, 12));
        example1.setFocusable(false);
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        examplePanel.add(example1,gridBagConstraints);

        JButton example2 = new JButton("Example 2");
        example2.setForeground(Color.black);
        example2.setFont(new Font("MS Gothic", Font.PLAIN, 12));
        example2.setFocusable(false);
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 1;
        examplePanel.add(example2,gridBagConstraints);

        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridx = 0;
        add(examplePanel,gridBagConstraints);


        request.setHttpcode(httpCodeLabel);
        request.setErrorMessage(errorLabel);

        // Action listeners

        buttonSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String url = urlTextField.getText();
                String userInput = jTextArea.getText();
                if (!(url.equalsIgnoreCase("")) && methodChoice.getSelectedItem().toString().equalsIgnoreCase("POST") && !(userInput.equalsIgnoreCase(""))) {
                    System.out.println("Sending POST request to: " + url);
                    File file = new File("");
                   // byte[] decoder = Base64.getDecoder().decode()
                    outputTextArea.setText(request.requestPOST(userInput,url));
                   // JOptionPane.showMessageDialog(null, request.requestPOST(userInput,url));
                } else if (!(url.equalsIgnoreCase("")) && methodChoice.getSelectedItem().toString().equalsIgnoreCase("GET")) {
                    System.out.println("Sending GET request to: " + url);
                    outputTextArea.setText(request.requestGET(url));
                }
            }
        });

        pdfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(outputTextArea.toString().equalsIgnoreCase(""))) {
                    try {
                        File file = new File("src/main/java/outputFiles/outputpdf.pdf");
                        String base64 = outputTextArea.getText();
                        byte[] decoder = Base64.getMimeDecoder().decode(base64);
                        FileOutputStream fos = new FileOutputStream(file);
                        fos.write(decoder);
                        Desktop.getDesktop().open(file);
                    } catch (FileNotFoundException ex) {
                        errorLabel.setText("Error finding the file!");
                    } catch (IOException e1) {
                        errorLabel.setText("There was an error on conversion, check your base64 string!");
                    }
                }
            }
        });

        imageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(outputTextArea.toString().equalsIgnoreCase(""))) {
                    try {
                        File file = new File("src/main/java/outputFiles/outputimage.png");
                        FileOutputStream fos = new FileOutputStream(file);
                        String base64 = outputTextArea.getText();
                        byte[] btDataFile = new sun.misc.BASE64Decoder().decodeBuffer(base64);
                        fos.write(btDataFile);
                        Desktop.getDesktop().open(file);
                    } catch (FileNotFoundException ex) {
                        errorLabel.setText("Error finding the file!");
                    } catch (IOException ex) {
                        errorLabel.setText("There was an error on conversion, check your base64 string!");
                    }
                }
            }
        });

        example1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                urlTextField.setText("https://www.gladtolink.com:8080/api/G2LIntegration/8646d274-13e6-43ed-8590-81b0b0b49a62");
                jTextArea.setText("data={\n" +
                        "  \"documentUniqueId\": \"76d07c8c-1cf9-4277-be17-5d0724156a93\",\n" +
                        "  \"version\": \"1\"\n" +
                        "}");
                methodChoice.setSelectedItem("POST");
                outputTextArea.setText("");
            }
        });

        example2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                urlTextField.setText("http://localhost:8080/REST_JerseyServer/rest/testing/getDummy");
                jTextArea.setText("");
                methodChoice.setSelectedItem("GET");
                outputTextArea.setText("");
            }
        });


    }


}
