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
	boolean error;
	Pantalla p1;
	ServerSocket servidorVideo;
	
public EnviarVideo(Socket a, Pantalla p1){
	error=false;
	this.p1= p1;
	
}

	public void run(){
	
			try {
				p1.setText("Servidor envio de video iniciado...");
				servidorVideo = new ServerSocket(Servidor.PUERTO+1);
			} catch (IOException e1) {
				error=true;
				 p1.setText("error iniciando el video");
			}
			while(true){
				try {
					sokete= servidorVideo.accept();	
					capturarPantalla(sokete);
					sokete.close();				
					}	
				catch (IOException e) {				 
				} 		 
		}//while
			
	}//run
	
	private void capturarPantalla(Socket a){
		try {
			p1.setText("video mandao");
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
			error=true;
			p1.setText("error en el capturarPantalla");
			//p1.setText("La conexion de video ha terminado");
		} catch (AWTException e) {
			//p1.setText("La conexion de video ha terminado");
			error=true;
		}
	}
}
