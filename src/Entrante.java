import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;


public class Entrante extends Thread{

	private Socket conexion;
	private Pantalla p1; //pantalla traida de la clase servidor general del programa
	private int queHacer;
	private boolean cerrarConexion;
	String Ip;
	
	public Entrante(Socket c, Pantalla p, String ip){
		Ip=ip;
		cerrarConexion=true;
		queHacer=0;
		conexion=c;
		p1=p;
		Thread hilo = new Thread(this);
		hilo.start();	
	}
	
	
	public void run(){
		try {
		DataOutputStream flujoEntrada = new DataOutputStream (conexion.getOutputStream());
		flujoEntrada.writeUTF("asdsadsds");
		
			flujoEntrada.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	try{
		
			while(cerrarConexion){
				
				while(queHacer==0){
					
					
					sleep(1000);
					//verificamos si se cerro la conexion
					if(conexion.isClosed()){
						
						cerrarConexion=false;
						JOptionPane.showMessageDialog(null, "La conexion fue cerrada por inactividad ");
					}
					
			
					if(queHacer==1){
						DataOutputStream flujoSalida = new DataOutputStream (conexion.getOutputStream());
						flujoSalida.writeUTF("1");
						flujoSalida.flush();
					
						queHacer=0;
						sleep(2000);
						
						}
				
					
					}	
				
				
			}//while cerrarconexion
			
			p1.setText("la Conexion ha sido cerrada con " + Ip);
			conexion.close();
			
			}catch (IOException e) {
				
				e.printStackTrace();
				p1.setText("error en los envios de datos"+ Ip);
			}//fin try
			 catch (InterruptedException e) {
				 p1.setText("fallo al esperar el hilo"+ Ip);
					e.printStackTrace();
				}
	
	}//fin run
	
	
	
		
		
	
	
	
}