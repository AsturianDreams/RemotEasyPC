import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

import com.example.eros.remoteasy.Paquete;
import com.example.eros.remoteasy.*;

public class Entrante extends Thread{

	private Socket conexion;
	private Pantalla p1; //pantalla traida de la clase servidor general del programa
	private String queHacer;
	String Ip;
	private robotControl robot;
	private EnviarVideo video;
	private ObjectInputStream ois;
	
	public Entrante(Socket c, Pantalla p, String ip){
		Ip=ip;
		queHacer="";
		conexion=c;
		robot= new robotControl(p);
		p1=p;
		p1.setSocket(c);
		Thread hilo = new Thread(this);
		hilo.start();	
	
	}
	
	
	public void run(){
		
	try{		
			 
			Paquete paquete;
		
			while(!conexion.isClosed()){										
				ois = new ObjectInputStream(conexion.getInputStream());
				paquete = (Paquete)ois.readObject();	
				switch (paquete.getQueHacer()){
				case Paquete.RATON:
					robot.clickEn(paquete.getMoverX(), paquete.getMoverY(), paquete.getBoton());
					queHacer="";
					break;
				case Paquete.VIDEO:
					if(video==null){
						video = new EnviarVideo(conexion, p1);
						video.start();
					}
					else{
						video=null;
						//video.stop();  deboi de parar la transmision del video
					}
					break;
				
				case Paquete.CERRAR:
				
				break;
				
				}		
				
			}//while 
		
	
	
	}//fin try
	catch (IOException e) {	
		p1.setText(e.toString());
		p1.cerrarConexion();
	}
	catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
	

		
	}//fin run
	
	
}
