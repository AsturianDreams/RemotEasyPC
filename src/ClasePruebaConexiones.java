import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ClasePruebaConexiones {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client = new Socket("81.9.241.59", 9097);
		DataInputStream flujoEntrada = new DataInputStream (client.getInputStream());
		String numero= flujoEntrada.readUTF();
		JOptionPane.showMessageDialog(null, numero);
	}

}
