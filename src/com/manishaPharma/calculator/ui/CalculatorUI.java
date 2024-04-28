package com.manishaPharma.calculator.ui;

import com.manishaPharma.calculato.constants.PxConstants;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

public class CalculatorUI {

    private final String title ;
    private JPanel appPanel = null;
    private JLabel label_MRP;
    private JLabel label_GST;
    private JLabel label_RetailMargin;
    private JLabel label_StockistMargin;
    private JLabel label_Scheme;
    private JTextField txtField_MRP;
    private JTextField txtField_GST;
    private JTextField txtField_RetailMargin;
    private final JTextField txtField_StockistMargin = new JTextField(PxConstants.TEXT_FIELD_SIZE);
    private JTextField txtField_PTR;
    private JTextField txtField_PTS;
    private JTextField txtField_Scheme;
    private JTextField txtField_NetScheme;

    public CalculatorUI(String title) {
        super();
        this.title = title;
        init();
    }

    void init() {

        JFrame appFrame = new JFrame(title);
        appFrame.setSize(PxConstants.FRAME_WIDTH, PxConstants.FRAME_HEIGHT);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        appPanel = new JPanel();
        appPanel.setLayout(new BorderLayout());

        JLabel label_Header = new JLabel(PxConstants.PTR_PTS_TITLE);
        label_Header.setFont(new Font(PxConstants.FONT_NAME, Font.BOLD, PxConstants.LABEL_HEADER_SIZE));
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.add(label_Header);

        //MRP content
        label_MRP = new JLabel(PxConstants.MRP);
        txtField_MRP = new JTextField(PxConstants.TEXT_FIELD_SIZE);
        ((AbstractDocument) txtField_MRP.getDocument()).setDocumentFilter(new NumberDocumentFilter());

        //GST
        label_GST = new JLabel(PxConstants.GST);
        txtField_GST = new JTextField(PxConstants.TEXT_FIELD_SIZE);
        ((AbstractDocument) txtField_GST.getDocument()).setDocumentFilter(new NumberDocumentFilter());

        //Retail Margin
        label_RetailMargin = new JLabel(PxConstants.RETAIL_MARGIN);
        txtField_RetailMargin = new JTextField(PxConstants.TEXT_FIELD_SIZE);
        ((AbstractDocument) txtField_RetailMargin.getDocument()).setDocumentFilter(new NumberDocumentFilter());

        //Stock Margin
        label_StockistMargin =  new JLabel(PxConstants.STOCKIST_MARGIN);
        ((AbstractDocument) txtField_StockistMargin.getDocument()).setDocumentFilter(new NumberDocumentFilter());

        //PTR
        JLabel label_PTR = new JLabel(PxConstants.PTR);
        txtField_PTR = new JTextField(PxConstants.TEXT_FIELD_SIZE);
        txtField_PTR.setEditable(false);

        //PTS
        JLabel label_PTS = new JLabel(PxConstants.PTS);
        txtField_PTS =  new JTextField(PxConstants.TEXT_FIELD_SIZE);
        txtField_PTS.setEditable(false);

        //Scheme
        label_Scheme = new JLabel(PxConstants.SCHEME);
        txtField_Scheme = new JTextField(PxConstants.TEXT_FIELD_SIZE);
        ((AbstractDocument) txtField_Scheme.getDocument()).setDocumentFilter(new NumberDocumentFilter());

        //Net Scheme
        JLabel label_NetScheme = new JLabel(PxConstants.NET_SCHEME);
        txtField_NetScheme =  new JTextField(PxConstants.TEXT_FIELD_SIZE);
        txtField_NetScheme.setEditable(false);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton enterButton = new JButton(PxConstants.ENTER_BUTTON_LABEL);
        JButton clearButton = new JButton(PxConstants.CLEAR_BUTTON_LABEL);
        buttonPanel.add(enterButton);
        buttonPanel.add(clearButton);

        //Component Panel for adding Labels and Text fields
        JPanel compPanel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(compPanel);
        compPanel.setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        GroupLayout.SequentialGroup horGroupLayout = groupLayout.createSequentialGroup();
        horGroupLayout.addGroup(groupLayout.createParallelGroup().addComponent(label_MRP)
                .addComponent(label_GST)
                .addComponent(label_RetailMargin)
                .addComponent(label_StockistMargin)
                .addComponent(label_Scheme)
                .addComponent(label_PTR)
                .addComponent(label_PTS)
                .addComponent(label_NetScheme));

        horGroupLayout.addGroup(groupLayout.createParallelGroup().addComponent(txtField_MRP)
                .addComponent(txtField_GST)
                .addComponent(txtField_RetailMargin)
                .addComponent(txtField_StockistMargin)
                .addComponent(txtField_Scheme)
                .addComponent(txtField_PTR)
                .addComponent(txtField_PTS)
                .addComponent(txtField_NetScheme));

        groupLayout.setHorizontalGroup(horGroupLayout);

        GroupLayout.SequentialGroup verGroupLayout = groupLayout.createSequentialGroup();

        verGroupLayout.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label_MRP).addComponent(txtField_MRP));
        verGroupLayout.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label_GST).addComponent(txtField_GST));
        verGroupLayout.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label_RetailMargin).addComponent(txtField_RetailMargin));
        verGroupLayout.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label_StockistMargin).addComponent(txtField_StockistMargin));
        verGroupLayout.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label_Scheme).addComponent(txtField_Scheme));
        verGroupLayout.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label_PTR).addComponent(txtField_PTR));
        verGroupLayout.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label_PTS).addComponent(txtField_PTS));
        verGroupLayout.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label_NetScheme).addComponent(txtField_NetScheme));

        groupLayout.setVerticalGroup(verGroupLayout);

        appPanel.add(headerPanel, BorderLayout.NORTH);

        appPanel.add(compPanel, BorderLayout.CENTER);

        appPanel.add(buttonPanel, BorderLayout.SOUTH);

        appFrame.add(appPanel);
        appFrame.setLocationRelativeTo(null);
        appFrame.setResizable(false);
        appFrame.setVisible(true);

        //Logic for ActionListener and Buttons
        txtField_MRP.addKeyListener(new ArrowKeyListener(txtField_GST, txtField_Scheme));
        txtField_GST.addKeyListener(new ArrowKeyListener(txtField_RetailMargin, txtField_MRP));
        txtField_RetailMargin.addKeyListener(new ArrowKeyListener(txtField_StockistMargin, txtField_GST));
        txtField_StockistMargin.addKeyListener(new ArrowKeyListener(txtField_Scheme, txtField_RetailMargin));
        txtField_Scheme.addKeyListener(new ArrowKeyListener(txtField_MRP, txtField_StockistMargin));

        clearButton.addActionListener(e -> {
            setClearButton();
            txtField_MRP.requestFocus();
        });

        enterButton.addActionListener(e -> calculate());

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        };

        txtField_MRP.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enterAction");
        txtField_MRP.getActionMap().put("enterAction", action);
        txtField_MRP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtField_MRP.setText(PxConstants.EMPTY_STRING);
            }
        });

        txtField_GST.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enterAction");
        txtField_GST.getActionMap().put("enterAction", action);
        txtField_GST.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtField_GST.setText(PxConstants.EMPTY_STRING);
            }
        });

        txtField_RetailMargin.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enterAction");
        txtField_RetailMargin.getActionMap().put("enterAction", action);
        txtField_RetailMargin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtField_RetailMargin.setText(PxConstants.EMPTY_STRING);
            }
        });

        txtField_StockistMargin.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enterAction");
        txtField_StockistMargin.getActionMap().put("enterAction", action);
        txtField_StockistMargin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtField_StockistMargin.setText(PxConstants.EMPTY_STRING);
            }
        });

        txtField_Scheme.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enterAction");
        txtField_Scheme.getActionMap().put("enterAction", action);
        txtField_Scheme.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtField_Scheme.setText(PxConstants.EMPTY_STRING);
            }
        });


    }

    /**
     * Clear all TextField of Calculator
     * @returns null
     */
    public void setClearButton(){
        txtField_MRP.setText(PxConstants.EMPTY_STRING);
        txtField_GST.setText(PxConstants.EMPTY_STRING);
        txtField_RetailMargin.setText(PxConstants.EMPTY_STRING);
        txtField_StockistMargin.setText(PxConstants.EMPTY_STRING);
        txtField_PTR.setText(PxConstants.EMPTY_STRING);
        txtField_PTS.setText(PxConstants.EMPTY_STRING);
        txtField_Scheme.setText(PxConstants.EMPTY_STRING);
        txtField_NetScheme.setText(PxConstants.EMPTY_STRING);
    }

    /**
     * Calculate the values and populate to respective Text Fields
     */
    public void calculate(){

        if(txtField_MRP.getText().isEmpty()){
            JOptionPane.showMessageDialog(appPanel, label_MRP.getText() + PxConstants.ERROR_EMPTY_STRING, PxConstants.TITLE, JOptionPane.INFORMATION_MESSAGE);
            txtField_MRP.requestFocus();
            return;
        }
        if(txtField_GST.getText().isEmpty()){
            JOptionPane.showMessageDialog(appPanel, label_GST.getText() + PxConstants.ERROR_EMPTY_STRING, PxConstants.TITLE, JOptionPane.INFORMATION_MESSAGE);
            txtField_GST.requestFocus();
            return;
        }
        if(txtField_RetailMargin.getText().isEmpty()){
            JOptionPane.showMessageDialog(appPanel, label_RetailMargin.getText() + PxConstants.ERROR_EMPTY_STRING, PxConstants.TITLE, JOptionPane.INFORMATION_MESSAGE);
            txtField_RetailMargin.requestFocus();
            return;
        }
        if(txtField_StockistMargin.getText().isEmpty()){
            JOptionPane.showMessageDialog(appPanel, label_StockistMargin.getText() + PxConstants.ERROR_EMPTY_STRING, PxConstants.TITLE, JOptionPane.INFORMATION_MESSAGE);
            txtField_StockistMargin.requestFocus();
            return;
        }
        if(txtField_Scheme.getText().isEmpty()){
            JOptionPane.showMessageDialog(appPanel, label_Scheme.getText() + PxConstants.ERROR_EMPTY_STRING, PxConstants.TITLE, JOptionPane.INFORMATION_MESSAGE);
            txtField_Scheme.requestFocus();
            return;
        }

        double MRP = Double.parseDouble(txtField_MRP.getText());
        double GST = Double.parseDouble(txtField_GST.getText());
        double retailMargin = Double.parseDouble(txtField_RetailMargin.getText());
        double stockistMargin = Double.parseDouble(txtField_StockistMargin.getText());
        double scheme = Double.parseDouble(txtField_Scheme.getText());

        //PTR Logic
        double retailValuePercentage = (retailMargin/100) * MRP;
        //double retailValue =  retailValuePercentage*100;
        double netMargin = MRP-retailValuePercentage;

        double GSTFactor = (100+GST)/100;

        Double PTR = netMargin/GSTFactor;
        String setPTR = String.format("%.2f",PTR);

        txtField_PTR.setText(setPTR);

        //PTS Logic
        Double PTSValue = (Math.round(PTR * 100.0) / 100.0);
        Double stockistValue1 = stockistMargin/100.0;
        Double stockistValue = stockistValue1*PTSValue;
        Double PTS = PTSValue - stockistValue;

        String setPTS = String.format("%.2f", PTS);

        txtField_PTS.setText(setPTS);

        //Net Scheme Logic ((ptr)-(ptr*scheme/100))
        Double netScheme1 =  scheme/100.0;
        Double netScheme2 = netScheme1 * PTR;
        Double netScheme = PTR - netScheme2;

        String setNetScheme = String.format("%.2f",netScheme);
        txtField_NetScheme.setText(setNetScheme);


    }
}

class NumberDocumentFilter extends DocumentFilter {
    private final Pattern numberPattern = Pattern.compile("\\d*\\.?\\d*");

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        String newStr = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
        if (numberPattern.matcher(newStr).matches()) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        String newStr = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
        if (numberPattern.matcher(newStr).matches()) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}

class ArrowKeyListener extends KeyAdapter {
    private final JTextField nextTextField;
    private final JTextField prevTextField;

    public ArrowKeyListener(JTextField nextTextField, JTextField prevTextField) {
        this.nextTextField = nextTextField;
        this.prevTextField = prevTextField;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            prevTextField.requestFocusInWindow();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            nextTextField.requestFocusInWindow();
        }
    }
}

