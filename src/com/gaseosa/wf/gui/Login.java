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

package com.gaseosa.wf.gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;

import javax.swing.JPasswordField;

import com.gaseosa.wf.data.Comms;

import de.timroes.axmlrpc.XMLRPCException;



/**
 * First window to be seen.
 * 
 * A login window to begin server-client communication. Onces started it
 * executes MainGUI JFrame
 * 
 * @author Gabriel
 * @version 0.1
 *
 */
public class Login extends JFrame implements ActionListener
{


    private static final long serialVersionUID = 1L;
    private JPanel            contentPane;

    /**
     * Text field for the user
     */
    private JTextField        txtLogin;

    private static Login      frame;

    /**
     * Password field for the password
     */
    private JPasswordField    txtPassword;



    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {


            public void run()
            {
                try
                {
                    frame = new Login();
                    frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }



    /**
     * Create the frame.
     */
    public Login()
    {
        setTitle("Login en Webfaction");
        addWindowListener(new WindowAdapter()
        {


            @Override
            public void windowClosed(WindowEvent arg0)
            {
                System.exit(0);
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 300, 253, 201);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtLogin = new JTextField();
        txtLogin.setBounds(92, 12, 114, 19);
        contentPane.add(txtLogin);
        txtLogin.setColumns(10);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(12, 12, 70, 15);
        contentPane.add(lblUsuario);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(12, 67, 70, 15);
        contentPane.add(lblPassword);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.addActionListener(this);

        btnEntrar.setBounds(55, 98, 117, 25);
        contentPane.add(btnEntrar);

        JLabel lblAcercaDe = new JLabel("Licencia");
        lblAcercaDe.addMouseListener(new MouseAdapter()
        {


            @Override
            public void mouseClicked(MouseEvent arg0)
            {
                showLicenses();
            }
        });
        lblAcercaDe.setForeground(Color.BLUE);
        lblAcercaDe.setBounds(65, 135, 107, 15);
        contentPane.add(lblAcercaDe);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(92, 65, 114, 19);
        contentPane.add(txtPassword);
    }



    @Override
    public void actionPerformed(ActionEvent e)
    {
        int res = 1;
        try
        {
            res = Comms.init(txtLogin.getText(),
                    new String(txtPassword.getPassword()));

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
            MainGUI.main(new String[] {});
            frame.setVisible(false);
            ;
        }
    }



    public void showLicenses()
    {
        String contenido = "- WF Control Panel -\n"
                + "Copyright (C) Gabriel Franco <gabriel.franco.martinez@gmail.com>\n"
                + "Bajo licencia GNU GPL v3\n"
                + "https://github.com/Gabriel-fm/WF-Control\n\n"
                + "- Librería aXMLRPC -\n"
                + "Copyright (C) 2011-2012 Tim Roes";
        JOptionPane.showMessageDialog(this, contenido, "Licencia",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
