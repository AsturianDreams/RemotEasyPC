import java.io.DataInputStream;
import com.example.eros.remoteasy.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import com.example.eros.remoteasy.Paquete;

import javax.swing.JOptionPane;

import com.example.eros.remoteasy.Paquete;

public class ClasePruebaConexiones {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client = new Socket("81.9.241.59", 9097);
		Paquete a = new Paquete(Paquete.RATON, 10, 10, Paquete.BOTON_IZQ);
			
			ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
	
					oos.writeObject(a);
					oos.flush();
	
			oos.close();			
				//	asd
		client.close();
	}

}
