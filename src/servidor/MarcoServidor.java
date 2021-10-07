/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.IOException;
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

            while (true) {
                //Le decimos a nuestra aplicación que acepte cualquier petición que venga del exterior
                Socket miSo = servidor.accept(); //Le decimos que acepte todo tipo de entrada
                //------
                DataInputStream entradaDatos = new DataInputStream(miSo.getInputStream());//Leemos el flujo de datos

                String cadena = entradaDatos.readUTF();
                JOptionPane.showMessageDialog(this, "Usted Tiene un nuevo mensaje", "Nuevo Mensaje", 1);
                //areatexto.setText(cadena); Eliminar el texto y agrega el nuevo
                areatexto.append(cadena + "\n");//Agrega un texto nuevo,sin eliminar el contenido
                miSo.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", 0);
        }
    }
}
