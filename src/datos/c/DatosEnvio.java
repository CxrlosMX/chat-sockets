 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.c;

import java.io.Serializable;

/**
 *
 * @author: CxrlosMX
 * @Git-Hub: https://github.com/CxrlosMX
 * @Phone: 953-212-97-27
 * @Email: LuisCRendon131@gmail.com
 * @Date: 06-oct-2021
 *
 */
public class DatosEnvio implements Serializable { //Implement la interface Serializable para especificar que nuestra clase podra convertirse en bits

    private String nick;
    private String ip;
    private String mensaje;

    public DatosEnvio(String nick, String ip, String mensaje) {
        this.nick = nick;
        this.ip = ip;
        this.mensaje = mensaje;
    }

    public DatosEnvio() {

    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
