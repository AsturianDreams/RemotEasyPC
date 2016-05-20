

public class Paquete {

	private byte[] Imagen;
	private int queHacer;
	private int moverX;
	private int moverY;
	 
	
	 static final int CERRAR = 1;
	 static final int VIDEO = 2;  //valores que puede tomar la variable queHacer
	 static final int RATON = 3;
	 
	 
	 public Paquete(){
		setImagen(null);
		setQueHacer(0);
		setMoverX(0);
		setMoverY(0);
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


	public void setMoverY(int moverY) {
		this.moverY = moverY;
	}
	 
	 
	
	 
}
