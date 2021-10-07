/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import datos.c.DatosEnvio;
import java.awt.BorderLayout;
import java.awt.Font;
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
import javax.swing.JTextArea;
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

    private final JPanel laminaMensaje, laminaAreaText, laminaCampo;

    public LaminaMarcoCliente() {
        laminaMensaje = new JPanel();
        laminaAreaText = new JPanel();
        laminaCampo = new JPanel();
        //--------------------------
        setLayout(new BorderLayout());
        JLabel texto = new JLabel("CHAT");
        nick = new JTextField(5);
        ip = new JTextField(5);
        texto.setFont(new Font("Serif", Font.ITALIC, 20));
        laminaMensaje.add(nick);
        laminaMensaje.add(texto);
        laminaMensaje.add(ip);
        //-----------------------
        //Area de chat
        areaChat = new JTextArea(20, 30);
        laminaAreaText.add(areaChat);
        //-------------------
        mensaje = new JTextField(20);
        laminaCampo.add(mensaje);
        miboton = new JButton("Enviar");
        miboton.addActionListener(new EnviaTexto());
        laminaCampo.add(miboton);

        add(laminaMensaje, BorderLayout.NORTH);
        add(laminaAreaText, BorderLayout.CENTER);
        add(laminaCampo, BorderLayout.SOUTH);

    }
    /*
     Método que nos retorna el valor de cada recuadro
     */

    public String dameTexto(JTextField campo) {
        return campo.getText();
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
                
                DatosEnvio paqueteEnvio=new DatosEnvio(dameTexto(nick), dameTexto(ip), dameTexto(mensaje));
                /*
                 Especificamos por donde sircularan los datos
                 */
                /*  DataOutputStream flujo_salida = new DataOutputStream(misocket.getOutputStream());
                 flujo_salida.writeUTF(campo1.getText()); //Especificamos que en nuetro flujo de datos viajara el texto que tengamos en el campo
                 JOptionPane.showMessageDialog(null, "Mensaje enviado con exito", "Mensaje enviado", 1);
                 flujo_salida.close();*/
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(mensaje, ex.getMessage(), "Error", 0);
            }
        }

    }

    private JTextField mensaje, nick, ip;
    private JTextArea areaChat;
    private JButton miboton;
}
