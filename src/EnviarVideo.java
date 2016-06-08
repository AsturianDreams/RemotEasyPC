import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.example.eros.remoteasy.Paquete;


public class EnviarVideo extends Thread{
	Socket sokete;
	boolean error;
	Pantalla p1;
	
public EnviarVideo(Socket a, Pantalla p1){
	sokete=a;
	error=false;
	this.p1= p1;
}

	public void run(){
		while(error==false){
			capturarPantalla(sokete);
			try {
				sleep(100); 
			} catch (InterruptedException e) {
				
			}
			
		}
	}
	
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
			p1.setText("La conexion de video ha terminado");
		} catch (AWTException e) {
			p1.setText("La conexion de video ha terminado");
			error=true;
		}
	}
}
