import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public final class  Servidor implements Runnable{
	static Pantalla p1; //pantalla Swing
	static boolean ocupado;   // solo puede haber 1 conexion al mismo tiempo aqui controlamos que no pueda haber 2
	
	static final int PUERTO = 9097;
	
	public Servidor(){
		ocupado=false;
		 p1=new Pantalla();
	}

	
//tiririr
	
	public static void main(String[] args) {
		
		new Servidor();
	}

	public static void IniciarServidor() {
		
		if(ocupado==false){ // si no hjay otra conexion entonces nos ponemos a escuchar
			new Thread(new Servidor()).start();
			
				
			}
		else{
			p1.setText("Se intento hacer una conexion sin exito porque ya hay una conexion abierta actualmente");
		}
	
	}//fin iniciar servidor



	@Override
	public void run() {
		try {
			p1.setText("aaaaa");
			ServerSocket servidor = new ServerSocket(PUERTO);
			Socket cliente;
			p1.setText("SERVIDOR INICIADO CORRECTAMENTE...");
			cliente= servidor.accept();
			p1.setText("nueva conexion entrante desde: "+ cliente.getInetAddress());
			String aux=cliente.getInetAddress().toString();
			new Entrante(cliente, p1, aux);		
			servidor.close();
			}
		
			 catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					p1.setText("ERRORAL INICIAR EL SERVIDOR... "
				     + "COMPRUEBA QUE EL PUERTO 9098 ESTA LIBRE Y REINICIE LA APLICACION");
				}
		
	}
	
	
}	