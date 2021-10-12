/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import datos.c.DatosEnvio;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class LaminaMarcoCliente extends JPanel implements Runnable {

    private final JPanel laminaMensaje, laminaAreaText, laminaCampo;
    private JTextField mensaje;
    private JComboBox ips;
    private JLabel nick, menNick;
    private JTextArea areaChat;
    private JButton miboton;

    public LaminaMarcoCliente() {
        /*
         Metodo que pregunta el nombre del usuario
         */
        String nick_usuario = preguntaNombre();
        Thread hilo = new Thread(this);
        hilo.start();
        laminaMensaje = new JPanel();
        laminaMensaje.setLayout(new GridLayout(1, 2));
        laminaAreaText = new JPanel();
        laminaCampo = new JPanel();
        //--------------------------
        setLayout(new BorderLayout());
        JLabel texto = new JLabel("Online: ");
        nick = new JLabel();
        menNick = new JLabel("Nick: ");
        ips = new JComboBox();
        ips.addItem("Usuario 1");
        ips.addItem("Usuario 2");
        ips.addItem("Usuario 3");
        nick.setText(nick_usuario);
        //texto.setFont(new Font("Serif", Font.ITALIC, 20));
        laminaMensaje.add(menNick);
        laminaMensaje.add(nick);
        laminaMensaje.add(texto);
        laminaMensaje.add(ips);
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

    public String preguntaNombre() {
        return JOptionPane.showInputDialog(this, "Nick: ", "Introduciendo Nombre de usuario", 1);
    }
    /*
     Método que nos retorna el valor de cada recuadro
     */

    public String dameTexto(JTextField campo) {
        return campo.getText();
    }

    @Override
    public void run() {
        try {
            ServerSocket servidorCliente = new ServerSocket(9090);
            Socket cliente;
            DatosEnvio paqueteRecibido;
            while (true) {
                cliente = servidorCliente.accept(); //Abrimos nuestro socket para aceptar todos los mensajes
                ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());
                paqueteRecibido = (DatosEnvio) flujoEntrada.readObject();
                areaChat.append("\n" + paqueteRecibido.getNick() + ": " + paqueteRecibido.getMensaje() + ". para: " + paqueteRecibido.getIp());

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
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
            areaChat.append("\n" + mensaje.getText());
            try {
                Socket misocket = new Socket("192.168.0.7", 9999); //Creamos el puente
                //Creamos un objeto donde almacenamos los datos
                DatosEnvio paqueteEnvio = new DatosEnvio(nick.getText(),(String) ips.getSelectedItem(), dameTexto(mensaje));

                ObjectOutputStream paqueteDatos = new ObjectOutputStream(misocket.getOutputStream());

                paqueteDatos.writeObject(paqueteEnvio);
                misocket.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(mensaje, ex.getMessage(), "Error", 0);
            }
        }

    }

}
