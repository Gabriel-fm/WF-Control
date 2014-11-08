package com.gaseosa.wf.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.Component;
import javax.swing.BoxLayout;



public class UserAdd extends JDialog implements ActionListener
{


    private final JPanel contentPanel = new JPanel();
    private String resultado[] = new String[4];
    private JTextField textMail;
    private JTextField textBuzon;
    private JPasswordField password;

    /**
     * Create the dialog.
     */
    public UserAdd()
    {
        setModal(true);
        setTitle("Crear nuevo usuario");
        setResizable(false);
        setBounds(100, 100, 350, 159);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        BoxLayout bl = new BoxLayout(contentPanel, BoxLayout.Y_AXIS);
        contentPanel.setLayout(bl);
        
        
        
        {
            Box horizontalBox = Box.createHorizontalBox();
            
            contentPanel.add(horizontalBox);
            {
                JLabel lblMail = new JLabel("Dirección de correo");
                horizontalBox.add(lblMail);
            }
            {
                textMail = new JTextField();
                horizontalBox.add(textMail);
                textMail.setColumns(10);
            }
        }
        {
            Box horizontalBox = Box.createHorizontalBox();
            contentPanel.add(horizontalBox);
            horizontalBox.setAlignmentY(Component.CENTER_ALIGNMENT);
            {
                JLabel lblBuzon = new JLabel("Buzón");
                horizontalBox.add(lblBuzon);
            }
            {
                textBuzon = new JTextField();
                horizontalBox.add(textBuzon);
                textBuzon.setColumns(10);
            }
        }
        {
            Box horizontalBox = Box.createHorizontalBox();
            contentPanel.add(horizontalBox);
            {
                JLabel lblPass = new JLabel("Contraseña");
                horizontalBox.add(lblPass);
            }
            
            password = new JPasswordField();
            horizontalBox.add(password);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("Crear");
                okButton.setActionCommand("OK");
                okButton.addActionListener(this);
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancelar");
                cancelButton.setActionCommand("cancel");
                cancelButton.addActionListener(this);
                buttonPane.add(cancelButton);
            }
        }
    }
    
    public String[] showDialog()
    {
        setVisible(true);
        return resultado;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if ( e.getActionCommand().equals("OK") )
        {
            resultado[0] =textMail.getText();
            resultado[1]=textBuzon.getText();
            resultado[2]=password.getPassword().toString();
            resultado[3]="ok";
            
            setVisible(false);
            dispose();
                    
        }
        else
        {
            resultado[0] ="";
            resultado[1]="";
            resultado[2]="";
            resultado[3]="";
            
            setVisible(false);
            dispose();
        }
    }
}
