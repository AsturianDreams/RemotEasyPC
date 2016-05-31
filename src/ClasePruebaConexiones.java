import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ClasePruebaConexiones {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client = new Socket("81.9.241.59", 9097);
		Paquete a = new Paquete(Paquete.RATON, 10, 10, Paquete.BOTON_IZQ);
		Paquete b = new Paquete(Paquete.RATON, 250, 250, Paquete.BOTON_IZQ);
		
		
			ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
	
					oos.writeObject(a);
					oos.flush();
	
			oos.close();			
			
		
		client.close();
	}

}
