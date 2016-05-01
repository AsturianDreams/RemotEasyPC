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
			p1.setSocket(cliente);
			String aux=cliente.getInetAddress().toString();
			new Entrante(cliente, p1, aux);		
			servidor.close();
			}
		
			 catch (IOException e) {
					p1.setText("ERRORAL INICIAR EL SERVIDOR... "
				     + "COMPRUEBA QUE EL PUERTO 9098 ESTA LIBRE Y REINICIE LA APLICACION");
					p1.setText("SI EL ERROR PERSISTE ABRA EL ADMINISTRADOR DE TAREAS, CIERRE LOS PROCESOS JAVAW.EXE Y VUELVA A INTENTARLO ");
					p1.botonIniciar.setEnabled(true);
				}
		
	}
	
	
	
	
}
