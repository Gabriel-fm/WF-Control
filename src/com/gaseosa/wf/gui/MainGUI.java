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


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gaseosa.wf.data.Comms;



/**
 * Main frame of the GUI of the application
 * 
 * @author Gabriel
 * @version 0.1
 */
public class MainGUI extends JFrame
{


    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
     * Content panel
     */
    private JPanel            contentPane;



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
                    MainGUI frame = new MainGUI();
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
    public MainGUI()
    {
        setTitle("Panel de Control - Webfaction - " + Comms.getUserName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 300, 800, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        PanelMails pm = new PanelMails();
        setContentPane(pm);
    }

}
