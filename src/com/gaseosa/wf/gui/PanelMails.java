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


import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import java.util.Vector;

import com.gaseosa.wf.data.Comms;
import com.gaseosa.wf.models.Mail;

import de.timroes.axmlrpc.XMLRPCException;

import javax.swing.JPasswordField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JList;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;



public class PanelMails extends JPanel implements ActionListener, ItemListener,
        ListSelectionListener
{


    private static final long      serialVersionUID = 1L;
    private JTextField             txtAdress;
    private JTextField             txtMailbox;
    private JPasswordField         pwdContra;

    private String                 buzonElegido;
    private String                 direccionElegida;

    private boolean                creando;

    private Vector<Mail>           v                = new Vector<Mail>();

    private JComboBox<String>      dominios;
    private JList<Mail>            list;
    private DefaultListModel<Mail> lm;
    private JButton btnCrear;
    private JButton btnBorrar;
    private JButton btnContrasea;



    /**
     * Create the panel.
     */
    public PanelMails()
    {

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
        gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
        gridBagLayout.columnWeights = new double[] { 0.25, 0.0, 1 };
        gridBagLayout.rowWeights = new double[] { 1, 1, 1, 1, 1, 1 };
        setLayout(gridBagLayout);

        lm = new DefaultListModel<Mail>();
        list = new JList<Mail>(lm);
        for (Mail m : v)
            lm.addElement(m);
        // list.setBounds(168, 107, 232, 81);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);

        JScrollPane scroll = new JScrollPane(list);
        GridBagConstraints gbc_scroll = new GridBagConstraints();
        gbc_scroll.fill = GridBagConstraints.BOTH;
        gbc_scroll.insets = new Insets(10, 15, 0, 10);
        gbc_scroll.gridheight = 6;
        gbc_scroll.gridx = 0;
        gbc_scroll.gridy = 1;
        add(scroll, gbc_scroll);

        JLabel lblDireccin = new JLabel("Dirección");
        GridBagConstraints gbc_lblDireccin = new GridBagConstraints();
        gbc_lblDireccin.anchor = GridBagConstraints.WEST;
        gbc_lblDireccin.insets = new Insets(0, 0, 5, 5);
        gbc_lblDireccin.gridx = 1;
        gbc_lblDireccin.gridy = 0;
        add(lblDireccin, gbc_lblDireccin);

        JLabel lblBuzn = new JLabel("Buzón");
        GridBagConstraints gbc_lblBuzn = new GridBagConstraints();
        gbc_lblBuzn.insets = new Insets(0, 0, 5, 5);
        gbc_lblBuzn.anchor = GridBagConstraints.WEST;
        gbc_lblBuzn.gridx = 1;
        gbc_lblBuzn.gridy = 1;
        add(lblBuzn, gbc_lblBuzn);

        txtAdress = new JTextField();
        txtAdress.setEditable(false);
        txtAdress.setColumns(10);
        GridBagConstraints gbc_txtAdress = new GridBagConstraints();
        gbc_txtAdress.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtAdress.insets = new Insets(0, 0, 5, 10);
        gbc_txtAdress.gridx = 2;
        gbc_txtAdress.gridy = 0;
        add(txtAdress, gbc_txtAdress);

        txtMailbox = new JTextField();
        txtMailbox.setEditable(false);
        txtMailbox.setColumns(10);
        GridBagConstraints gbc_txtMailbox = new GridBagConstraints();
        gbc_txtMailbox.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtMailbox.insets = new Insets(0, 0, 5, 10);
        gbc_txtMailbox.gridx = 2;
        gbc_txtMailbox.gridy = 1;
        add(txtMailbox, gbc_txtMailbox);

        btnContrasea = new JButton("Contraseña");
        btnContrasea.setActionCommand("cambiocontra");
        btnContrasea.addActionListener(this);
        GridBagConstraints gbc_btnContrasea = new GridBagConstraints();
        gbc_btnContrasea.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnContrasea.insets = new Insets(0, 0, 5, 10);
        gbc_btnContrasea.gridwidth = 2;
        gbc_btnContrasea.gridx = 1;
        gbc_btnContrasea.gridy = 4;
        add(btnContrasea, gbc_btnContrasea);

        btnCrear = new JButton("Crear");
        btnCrear.addActionListener(new ActionListener()
        {


            public void actionPerformed(ActionEvent arg0)
            {
                dialogoCrear();
            }
        });
        GridBagConstraints gbc_btnCrear = new GridBagConstraints();
        gbc_btnCrear.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnCrear.insets = new Insets(0, 0, 5, 10);
        gbc_btnCrear.gridwidth = 2;
        gbc_btnCrear.gridx = 1;
        gbc_btnCrear.gridy = 5;
        add(btnCrear, gbc_btnCrear);

        btnBorrar = new JButton("Borrar");
        btnBorrar.addActionListener(new ActionListener()
        {


            public void actionPerformed(ActionEvent arg0)
            {
                alertaBorrar();
            }
        });
        GridBagConstraints gbc_btnBorrar = new GridBagConstraints();
        gbc_btnBorrar.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnBorrar.insets = new Insets(0, 0, 20, 10);
        gbc_btnBorrar.gridwidth = 2;
        gbc_btnBorrar.gridx = 1;
        gbc_btnBorrar.gridy = 6;
        add(btnBorrar, gbc_btnBorrar);

        JLabel lblContrasea = new JLabel("Contraseña");
        GridBagConstraints gbc_lblContrasea = new GridBagConstraints();
        gbc_lblContrasea.insets = new Insets(0, 0, 5, 5);
        gbc_lblContrasea.anchor = GridBagConstraints.WEST;
        gbc_lblContrasea.gridx = 1;
        gbc_lblContrasea.gridy = 2;
        add(lblContrasea, gbc_lblContrasea);

        pwdContra = new JPasswordField();
        pwdContra.setEchoChar('*');
        GridBagConstraints gbc_pwdContra = new GridBagConstraints();
        gbc_pwdContra.insets = new Insets(0, 0, 5, 10);
        gbc_pwdContra.fill = GridBagConstraints.HORIZONTAL;
        gbc_pwdContra.gridx = 2;
        gbc_pwdContra.gridy = 2;
        add(pwdContra, gbc_pwdContra);

        dominios = new JComboBox<String>();
        dominios.addItemListener(this);
        GridBagConstraints gbc_dominios = new GridBagConstraints();
        gbc_dominios.insets = new Insets(0, 0, 5, 5);
        gbc_dominios.gridx = 0;
        gbc_dominios.gridy = 0;
        add(dominios, gbc_dominios);

        dominios.setBounds(12, 300, 144, 24);
        // add(dominios);

        refrescar();

    }



    private void alertaContra()
    {
        JOptionPane.showMessageDialog(this,
                "La contraseña debe tener 6 o más caracteres",
                "Longitud de la contraseña", JOptionPane.ERROR_MESSAGE);

    }



    private void alertaBorrar()
    {

        int n = JOptionPane.showConfirmDialog(this,
                "Si borra la cuenta perdera el buzón y\n"
                        + "todo su contenido.\n"
                        + "Todo su contenido será irrecuperable.\n"
                        + "¿Quiere seguir adelante?", "Borrar cuenta",
                JOptionPane.YES_NO_OPTION);

        if (n == 0)
        {
            Comms.mailDelte(buzonElegido, direccionElegida);
            System.out.println("Se va a borrar la dirección "
                    + direccionElegida);

            refrescar();
        }

    }



    private void dialogoCrear()
    {

        if (creando == false)
        {

            creando = true;

            JOptionPane
                    .showMessageDialog(
                            this,
                            "Introduzca los datos de la nueva cuenta en los campos correspondientes,\n"
                                    + "cuando los complete, vuelva a pulsar sobre el botón crear."
                                    + "Para cancelar deje los campos en blanco.",
                            "Añadir cuenta de correo",
                            JOptionPane.INFORMATION_MESSAGE);
            
            txtAdress.setText("");
            txtAdress.setEditable(true);
            txtMailbox.setText("");
            txtMailbox.setEditable(true);
            btnBorrar.setEnabled(false);
            btnContrasea.setEnabled(false);
        }
        else
        {
            creando = false;
            
            btnBorrar.setEnabled(true);
            btnContrasea.setEnabled(true);

            String contra = new String(pwdContra.getPassword());
            String direc = txtAdress.getText();
            String buz = txtMailbox.getText();
            
            if ( contra.equals("") || direc.equals(""))
            {
                refrescar();
                return;
            }

            txtAdress.setText("");
            txtMailbox.setText("");

            txtAdress.setEditable(false);
            txtMailbox.setEditable(false);

            int n = JOptionPane.showConfirmDialog(this,
                    "Se procede a crear la cuenta de dirección:\n\n" + direc
                            + "\n\n"
                            + "Utilizando un buzón/nombre de usuario:\n\n"
                            + buz + "\n\n" + "¿Todo correcto?", "Crear cuenta",
                    JOptionPane.YES_NO_OPTION);

            if (n == 0)
            {
                try
                {
                    Comms.mailCreate(direc, buz, contra);
                }
                catch (XMLRPCException e)
                {
                    JOptionPane
                            .showMessageDialog(
                                    this,
                                    "Ha ocurrido un error creando el correo.\n"
                                            + "revise los datos por vafor.\n"
                                            + "Si el problema persiste contacte con el administrador",
                                    "Error creando correo",
                                    JOptionPane.ERROR_MESSAGE);
                }
                refrescar();
            }
        }
    }



    public void actionPerformed(ActionEvent ev)
    {
        if (ev.getActionCommand().equals("cambiocontra")
                && new String(pwdContra.getPassword()).toCharArray().length < 6)
        {
            alertaContra();
        }
        else
        {
            try
            {

                Comms.mailChangePass(buzonElegido,
                        new String(pwdContra.getPassword()));
            }
            catch (XMLRPCException e)
            {
                JOptionPane
                        .showMessageDialog(
                                this,
                                "Ha ocurrido un error cambiando de contraseña.\n"
                                        + "Reintentelo de nuevo, si el problema persiste, contacte con el administrador",
                                "Error cambiando contraseña",
                                JOptionPane.ERROR_MESSAGE);

            }
        }
    }



    public void itemStateChanged(ItemEvent ev)
    {
        if (ev.getStateChange() == ItemEvent.DESELECTED) return;
        String domSelected = (String) ev.getItem();

        list.removeSelectionInterval(0, 0);
        lm.removeAllElements();
        for (Mail m : v)
            if (m.direccion.substring(m.direccion.indexOf("@") + 1).equals(
                    domSelected)) lm.addElement(m);
    }



    private void refrescar()
    {
        try
        {
            v = Comms.mailListAll();

            Vector<String> strDominios = new Vector<String>();
            for (int i = 0; i < v.size(); i++)
            {
                Mail m = v.get(i);
                String aux = m.direccion
                        .substring(m.direccion.indexOf("@") + 1);
                if (!strDominios.contains(aux)) strDominios.add(aux);
            }

            for (String i : strDominios)
            {
                dominios.addItem(i);
            }

            for (Mail m : v)
                lm.addElement(m);
        }
        catch (XMLRPCException e)
        {
            e.printStackTrace();
            System.out.println("Imposible obtener la lista de correos,"
                    + " consulte la excepción");
        }

    }



    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        int recupera = 0;
        recupera = list.getSelectedIndex();

        try
        {
            Mail m = lm.get(recupera);
            txtAdress.setEditable(false);
            txtMailbox.setEditable(false);
            txtAdress.setText(m.direccion);
            txtMailbox.setText(m.buzon);
            buzonElegido = m.buzon;
            direccionElegida = m.direccion;
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            return;
        }

    }
}
