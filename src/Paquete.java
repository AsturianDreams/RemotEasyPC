import java.io.Serializable;

public class Paquete implements Serializable {

	private byte[] Imagen;
	private int queHacer;
	private int moverX;
	private int moverY;
	private int boton;  //boton izq o derecho del raton
	 
	
	 static final int CERRAR = 1;
	 static final int VIDEO = 2;  //valores que puede tomar la variable queHacer
	 static final int RATON = 3;
	 static final int BOTON_IZQ = 0;
	 static final int BOTON_DER = 1;
	 
	 public Paquete(int queHacer){
		setImagen(null);
		setQueHacer(queHacer);
		setMoverX(0);
		setMoverY(0);
	 }
	 
	 public Paquete(int queHacer, byte[] imagen){
		setImagen(imagen);
		setQueHacer(queHacer);
		setMoverX(0);
		setMoverY(0);
	 }
	 public Paquete(int queHacer, int x, int y, int boton){
		setImagen(null);
		setQueHacer(queHacer);
		setMoverX(x);
		setMoverY(y);
		this.boton= boton;
	 }

	public byte[] getImagen() {
		return Imagen;
	}


	public void setImagen(byte[] imagen) {
		Imagen = imagen;
	}


	public int getQueHacer() {
		return queHacer;
	}


	public void setQueHacer(int queHacer) {
		this.queHacer = queHacer;
	}


	public int getMoverX() {
		return moverX;
	}


	public void setMoverX(int moverX) {
		this.moverX = moverX;
	}


	public int getMoverY() {
		return moverY;
	}

	public int getBoton() {
		return boton;
	}
	
	public void setMoverY(int moverY) {
		this.moverY = moverY;
	}
	 
	 
	
	 
}
