/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import datos.c.DatosEnvio;
import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author: CxrlosMX
 * @Git-Hub: https://github.com/CxrlosMX
 * @Phone: 953-212-97-27
 * @Email: LuisCRendon131@gmail.com
 * @Date: 05-oct-2021
 *
 */
public class MarcoServidor extends JFrame implements Runnable {

    private JTextArea areatexto;
    private DatosEnvio datosEnvio;

    public MarcoServidor() {
        Thread hilo = new Thread(this);
        hilo.start();
        setBounds(1200, 300, 280, 350);

        JPanel milamina = new JPanel();

        milamina.setLayout(new BorderLayout());

        areatexto = new JTextArea();

        milamina.add(areatexto, BorderLayout.CENTER);

        add(milamina);

        setVisible(true);

    }

    @Override
    public void run() {
        try {
            //Abrimos el puerto para que este a la escucha
            /*
             public class ServerSocket
             extends Object
             implements Closeable
            
             This class implements server sockets. A server socket waits for requests to come in over the network.
             It performs some operation based on that request, and then possibly returns a result to the requester.
             */
            ServerSocket servidor = new ServerSocket(9999); //Abrimos el puerto, esta a la escucha

            String mensaje;
            String nick;
            String ip;
            datosEnvio = new DatosEnvio();
            while (true) {
                //Le decimos a nuestra aplicación que acepte cualquier petición que venga del exterior
                Socket miSo = servidor.accept(); //Le decimos que acepte todo tipo de entrada

                ObjectInputStream entradaDatos = new ObjectInputStream(miSo.getInputStream());//Leemos el flujo de datos
                datosEnvio = (DatosEnvio) entradaDatos.readObject();
                mensaje = datosEnvio.getMensaje();
                nick = datosEnvio.getNick();
                ip = datosEnvio.getNick();

                if (mensaje.equals("Online")) {

                    areatexto.append("\n" + nick + ": " + mensaje + "para " + ip);

                    //Creamos otro socket, es el que enviara el mensaje a otro usuario
                    Socket enviarDestinatario = new Socket(ip, 9090); //Especificamos la ip y el puerto

                    ObjectOutputStream paqueteEnvio = new ObjectOutputStream(enviarDestinatario.getOutputStream());

                    //Agregamos el mensaje u objecto que se enviara
                    paqueteEnvio.writeObject(new DatosEnvio(nick, ip, mensaje));
                    paqueteEnvio.close();
                    enviarDestinatario.close();
                    miSo.close();
                } else {
                    //---------------------DETECTA ONLINE
                    InetAddress localizacion = miSo.getInetAddress(); //Almacenamos la direccion con el cliente con el que nos estamos conectando
                    String ipRemoto = localizacion.getHostAddress(); //Nos devuelve la ip del cliente
                    System.out.println("Online: " + ipRemoto);
                    //-----------------------------
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", 0);
        }
    }
}
