import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;


public class robotControl {

	private Robot robot;
	private int anchoPantalla ;
	private int altoPantalla ;
	private Rectangle dimensionPantalla;
	Pantalla p1;
	
	
	public static final int BOTON_IZQUIERDO =0;
	public static final int BOTON_DERECHO=1;
	public static final int BOTON_CENTRAL=2;
	
	public robotControl(Pantalla p1){
		
		this.p1=p1;
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		dimensionPantalla = new Rectangle(screenSize);
		try {
			robot=new Robot();
		} catch (AWTException e) {
			p1.setText("error al recoger datos de la pantalla");
		}
		
	}
	
	
	public void clickEn(int x, int y, int boton)  {
		
		robot.mouseMove(x,y);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		
		
	}
	
	
	
	
}
