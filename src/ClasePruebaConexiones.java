import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ClasePruebaConexiones {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client = new Socket("81.9.241.59", 9097);
		DataOutputStream flujoEntrada = new DataOutputStream (client.getOutputStream());
		flujoEntrada.writeUTF("moverRaton");
		
	}

}
