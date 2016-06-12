import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class  Servidor {
	static Pantalla p1; //pantalla Swing
	static boolean ocupado;   // solo puede haber 1 conexion al mismo tiempo aqui controlamos que no pueda haber 2
	
	public static int PUERTO = 9097;
	public static final String APLICACION="RemotEasy";
	public static boolean enviandoVideo;
	
	static HiloServidor Servicio;
	
	public Servidor(){
		ocupado=false;
		 p1=new Pantalla();
		 enviandoVideo=false;
	}



	public static void IniciarServidor() {
		
		if(ocupado==false){ // si no hjay otra conexion entonces nos ponemos a escuchar
			Servicio = new HiloServidor(p1);
				Servicio.start();
				
			}
		else{
			p1.setText("Se intento hacer una conexion sin exito porque ya hay una conexion abierta actualmente");
		}
	
	}//fin iniciar servidor


	
public static void main(String[] args) {
		
		new Servidor();
	}
	
}	
