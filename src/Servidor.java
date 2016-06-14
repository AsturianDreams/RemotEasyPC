import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class  Servidor {
	static Pantalla p1; //pantalla Swing
	public static int PUERTO = 9097;
	public static final String APLICACION="RemotEasy";
	public static boolean enviandoVideo;
	
	static HiloServidor Servicio;
	
	public Servidor(){
		 p1=new Pantalla();
		 enviandoVideo=false;
	}



	public static void IniciarServidor() {
	
			Servicio = new HiloServidor(p1);
				Servicio.start();
				
	
	}//fin iniciar servidor


	
public static void main(String[] args) {
		
		new Servidor();
	}
	
}	
