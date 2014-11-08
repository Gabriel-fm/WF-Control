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
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.gaseosa.wf.data.Comms;

import de.timroes.axmlrpc.XMLRPCException;

import java.awt.Dimension;
import java.awt.Component;
import java.net.MalformedURLException;



public class Login extends JDialog implements ActionListener
{


    /**
     * 
     */
    private final JPanel   contenidoPane = new JPanel();
    private JTextField     textUser;
    private JPasswordField password;

    /**
     * Create the dialog.
     */
    public Login()
    {
        setResizable(false);
        setTitle("Login");
        setModal(true);
        setBounds(100, 100, 289, 143);
        getContentPane().setLayout(new BorderLayout());
        FlowLayout fl_contenidoPane = new FlowLayout();
        fl_contenidoPane.setAlignment(FlowLayout.RIGHT);
        contenidoPane.setLayout(fl_contenidoPane);
        contenidoPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contenidoPane, BorderLayout.CENTER);
        {
            JLabel lblUsuario = new JLabel("Usuario");
            contenidoPane.add(lblUsuario);
        }
        {
            textUser = new JTextField();
            contenidoPane.add(textUser);
            textUser.setColumns(15);
        }
        {
            JLabel lblPassword = new JLabel("Password");
            contenidoPane.add(lblPassword);
        }
        {
            password = new JPasswordField();
            password.setColumns(15);
            contenidoPane.add(password);
        }
        {
            JPanel buttonPane = new JPanel();
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            buttonPane.setLayout(new BorderLayout(0, 0));
            JPanel buttons = new JPanel(new FlowLayout());

            {
                JButton okButton = new JButton("Login");
                okButton.setActionCommand("OK");
                buttons.add(okButton);
                getRootPane().setDefaultButton(okButton);
                okButton.addActionListener(this);
            }
            {
                JButton cancelButton = new JButton("Salir");
                cancelButton.addActionListener(new ActionListener()
                {


                    public void actionPerformed(ActionEvent arg0)
                    {
                        System.exit(0);
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttons.add(cancelButton);
            }
            buttonPane.add(buttons, BorderLayout.NORTH);
            {
                JLabel lblVerLicencia = new JLabel("Licencias");
                lblVerLicencia.setHorizontalAlignment(SwingConstants.CENTER);
                lblVerLicencia.setAlignmentX(Component.CENTER_ALIGNMENT);
                lblVerLicencia.setMinimumSize(new Dimension(150, 15));
                lblVerLicencia.setForeground(UIManager
                        .getColor("RadioButtonMenuItem.acceleratorForeground"));
                buttonPane.add(lblVerLicencia, BorderLayout.SOUTH);
                lblVerLicencia.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        super.mouseClicked(e);
                        Licenses l = new Licenses();
                        
                    }
                });
            }
        }
    }



    public void showDialog()
    {
        this.setVisible(true);
        return;
    }



    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        int res = 1;

        if (textUser.getText().equals("")
                || new String(password.getPassword()).equals(""))
        {
            fallo();
            return;
        }

        try
        {
            res = Comms.init(textUser.getText(),
                    new String(password.getPassword()));

        }
        catch (XMLRPCException ex)
        {
            ex.printStackTrace();
        }
        catch (MalformedURLException ex)
        {
            ex.printStackTrace();
        }

        if (res == 0)
        {
            setVisible(false);
            dispose();
        }
        else
        {

            fallo();

        }
    }



    private void fallo()
    {
        JOptionPane.showMessageDialog(this, "Algo ha ido mal durante el"
                + " login.\n\nPor favor vuelva a intentarlo.",
                "Fallo en el login", JOptionPane.ERROR_MESSAGE);
        textUser.setText("");
        password.setText("");
    }

}
