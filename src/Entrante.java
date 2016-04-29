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
		Thread hilo = new Thread(this);
		hilo.start();	
	}
	
	
	public void run(){
		
	try{
		DataInputStream flujoEntrada = new DataInputStream (conexion.getInputStream());
		queHacer= flujoEntrada.readUTF();
			while(true){
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
		p1.setText("La conexion con: "+ Ip + " ha sido cerrada");
	//	p1.botonIniciar.setEnabled(true); activar si no queremos que el servidor se ponga a escuchar automatico
		new HiloServidor(p1);  // desactrivar si no queremos que se reinicie el servidor al acabar con un cliente (manual)
		try {
			conexion.close();
		} catch (IOException e1) {
			p1.setText("fallo cerrando la conexion!!!");			
		}
		
	} catch (InterruptedException e) {
		//error del sleep
	}

	
	

		
	}//fin run
	
	
}