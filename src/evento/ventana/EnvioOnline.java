/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evento.ventana;

import datos.c.DatosEnvio;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author: CxrlosMX
 * @Git-Hub: https://github.com/CxrlosMX
 * @Phone: 953-212-97-27
 * @Email: LuisCRendon131@gmail.com
 * @Date: 11-oct-2021
 *
 */
/*
 Creamos un evento de ventana que informara a nuestro servidor 
 que ha sido ejecutada
 */
public class EnvioOnline extends WindowAdapter {

    @Override
    public void windowOpened(WindowEvent we) {//Invoked when a window has been opened.
        try {
            Socket miSocket = new Socket("192.168.0.3", 9999);
            DatosEnvio datos = new DatosEnvio();
            datos.setMensaje("Online");
            ObjectOutputStream paqueteDatos = new ObjectOutputStream(miSocket.getOutputStream());
            paqueteDatos.writeObject(datos);
            miSocket.close();
        } catch (Exception e) {
        }
    }

}
