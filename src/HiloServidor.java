import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class HiloServidor extends Thread{

	Pantalla p1;
	public HiloServidor(Pantalla p1){
		this.p1 = p1;
		
	}
	
	
	
	public void run() {
		try {
			ServerSocket servidor = new ServerSocket(Servidor.PUERTO);
			Socket cliente;
			p1.setText("SERVIDOR INICIADO CORRECTAMENTE...");
			cliente= servidor.accept();
			p1.setText("Accediendo desde: "+ cliente.getInetAddress());
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
