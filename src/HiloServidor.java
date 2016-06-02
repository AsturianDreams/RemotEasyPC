import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class HiloServidor extends Thread{

	Pantalla p1;
	ServerSocket servidor;
	public HiloServidor(Pantalla p1){
		this.p1 = p1;
		
	}
	public void pararServerSocket(){	
			 try {
				servidor.close();
			} catch (IOException e) {
				p1.setText("no se puedo cerrar el servidor");
			}
				
	}
	
	
	public void run() {
		try {
			servidor = new ServerSocket(Servidor.PUERTO);
			Socket cliente;
			p1.setText("Esperando conexion entrante por el puerto: " + Servidor.PUERTO);
			cliente= servidor.accept();
			p1.setText("Accediendo desde: "+ cliente.getInetAddress());
			p1.setSocket(cliente);
			String aux=cliente.getInetAddress().toString();
			new Entrante(cliente, p1, aux);		
			servidor.close();
			}
		
			 catch (IOException e) {
				 
				  if(e.toString().equals("java.net.SocketException: socket closed")){
					  //aqui filtramos para que no saque el mensaje si falla porque cerramos con el metodo pararServerSocket
				  }
				  else{
					p1.setText("ERRORAL INICIAR EL SERVIDOR... "
				     + "COMPRUEBA QUE EL PUERTO 9098 ESTA LIBRE Y REINICIE LA APLICACION");
					p1.setText("SI EL ERROR PERSISTE ABRA EL ADMINISTRADOR DE TAREAS, CIERRE LOS PROCESOS JAVAW.EXE Y VUELVA A INTENTARLO ");
					p1.botonIniciar.setEnabled(true);
				  }
				}
			
		
	}
	
	
	
	
}
