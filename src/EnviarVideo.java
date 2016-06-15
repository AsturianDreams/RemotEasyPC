import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.example.eros.remoteasy.Paquete;


public class EnviarVideo extends Thread{
	Socket sokete;
	Pantalla p1;
	ServerSocket servidorVideo;
	
public EnviarVideo(Socket a, Pantalla p1){
	this.p1= p1;
	Servidor.enviandoVideo = true;
}

	public void run(){
	
			try {			
				servidorVideo = new ServerSocket(Servidor.PUERTO+1);
				p1.setText("Servidor envio de video iniciado...");
			} catch (IOException e1) {
				Servidor.enviandoVideo = false;
				 p1.setText("Servidor de video no pudo iniciarse correctamente");
			}
			while(Servidor.enviandoVideo== true){
				try {
					sokete= servidorVideo.accept();	
					capturarPantalla(sokete);
					sokete.close();				
					}	
				catch (IOException e) {	
					 p1.setText("Cerrando Envio de Video");
					 Servidor.enviandoVideo = false;
				} 		 
		}//while
			try {
				servidorVideo.close(); //cerramos el socket por si acaso
			} catch (IOException e) {

			}
			
	}//run
	
	private void capturarPantalla(Socket a){
		try {
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRectangle = new Rectangle(screenSize);
		Robot robot = new Robot();
		BufferedImage image = robot.createScreenCapture(screenRectangle);
		ByteArrayOutputStream salidaImagen = new ByteArrayOutputStream();
		 ImageIO.write(image, "jpg", salidaImagen);
	     byte[] bytesImagen = salidaImagen.toByteArray();
	     Paquete paquete = new Paquete(Paquete.VIDEO, bytesImagen);
	     ObjectOutputStream salida = new ObjectOutputStream( a.getOutputStream() );
	     salida.writeObject( paquete );
	     salida.flush();
			
			
		} catch (IOException e) {
		
			p1.setText("error en el capturarPantalla");
			//p1.setText("La conexion de video ha terminado");
		} catch (AWTException e) {

		}
	}
}
