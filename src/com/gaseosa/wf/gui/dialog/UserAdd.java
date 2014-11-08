/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 Copyright 2014 Gabriel Franco <gabriel.franco.martinez@gmail.com>
 
 This file is part of WF Control Panel.
 
 WF Control Panel is free software: you can redistribute it and/or 
 modify it under the terms of the GNU General Public License as 
 published by the Free Software Foundation, either version 3 of the 
 License, or (at your option) any later version.
 
 WF Control Panel is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with WF Control Panel.  If not, see 
 <http://www.gnu.org/licenses/>.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

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
        setBounds(100, 100, 376, 159);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 8));
        
        
        
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
                textMail.setColumns(18);
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
                textBuzon.setColumns(18);
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
            password.setColumns(18);
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
