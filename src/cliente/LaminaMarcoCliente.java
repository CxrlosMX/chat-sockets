/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author: CxrlosMX
 * @Git-Hub: https://github.com/CxrlosMX
 * @Phone: 953-212-97-27
 * @Email: LuisCRendon131@gmail.com
 * @Date: 05-oct-2021
 *
 */
public class LaminaMarcoCliente extends JPanel {

    public LaminaMarcoCliente() {

        JLabel texto = new JLabel("CLIENTE");

        add(texto);

        campo1 = new JTextField(20);

        add(campo1);

        miboton = new JButton("Enviar");
        miboton.addActionListener(new EnviaTexto());
        add(miboton);

    }

    //Crearemos un evento
    private class EnviaTexto implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            //System.out.println(campo1.getText());
                /*
             Crearemos el socket
             Socket(InetAddress address, int port)
             Creates a stream socket and connects it to the specified port number at the specified IP address.
             Parameters:
             address - the IP address.
             port - the port number.
             */
            try {
                Socket misocket = new Socket("192.168.0.7", 9999);
                /*
                 Especificamos por donde sircularan los datos
                 */
                DataOutputStream flujo_salida = new DataOutputStream(misocket.getOutputStream());
                flujo_salida.writeUTF(campo1.getText()); //Especificamos que en nuetro flujo de datos viajara el texto que tengamos en el campo
                flujo_salida.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(campo1, ex.getMessage(), "Error", 0);
            }
        }

    }

    private JTextField campo1;

    private JButton miboton;
}
