import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;


public class Entrante extends Thread{

	private Socket conexion;
	private Pantalla p1; //pantalla traida de la clase servidor general del programa
	private String queHacer;
	String Ip;
	private robotControl robot;
	
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
		DataInputStream flujoEntrada = new DataInputStream (conexion.getInputStream());
		
			while(!conexion.isClosed()){
				queHacer= flujoEntrada.readUTF();
					sleep(1000);
				//verificamos si se cerro la conexion
				switch (queHacer){
				case "moverRaton":
					robot.clickEn(50, 50, robot.BOTON_IZQUIERDO);
					p1.setText("raton movio");
					queHacer="";
					break;
				case "probar":
					
					break;
				}
							

			}//while 
		
	
	
	}//fin try
	catch (IOException e) {			
		p1.cerrarConexion();
	}
	catch (InterruptedException e) {
		//error del sleep
	}
	
		
	

		
	}//fin run
	
	
}