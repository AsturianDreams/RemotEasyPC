import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Pantalla extends JFrame implements ActionListener {

	JTextArea pant;
	JPanel panelGeneral;
	JPanel panel1;
	JPanel panel2;
	JPanel subPanel1;
	JPanel subPanel2;
	JButton botonIniciar;
	JButton botonParar;
	JScrollPane scrolito;
	JMenuBar mainMenuBar;
	JMenu menuConfig, menuInformacion;
	JMenuItem menuConfigPuerto, subInformacion, menuConfigOtro;
	Socket conexion;

	public Pantalla() {
		super(Servidor.APLICACION);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(750, 500);

		panelGeneral = new JPanel(new BorderLayout(20, 20));
		panel1 = new JPanel();
		panel2 = new JPanel();
		BoxLayout a = new BoxLayout(panel2, BoxLayout.PAGE_AXIS);

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
		subPanel1 = new JPanel();
		subPanel2 = new JPanel();

		pant = new JTextArea(0, 0); // no importa tamaño el layout lo ajusta al
									// panel
		pant.setEditable(false);
		botonIniciar = new JButton("Arrancar");
		botonParar = new JButton("Detener");

		panelGeneral.add(panel1, BorderLayout.WEST);
		panelGeneral.add(panel2, BorderLayout.EAST);
		panel1.add(pant);

		panelGeneral.setBorder(new EmptyBorder(20, 20, 20, 20));
		subPanel1.setBorder(new EmptyBorder(100, 0, 0, 0));
		subPanel2.setBorder(new EmptyBorder(0, 0, 0, 0));
		botonIniciar.addActionListener(this);
		botonParar.addActionListener(this);

		scrolito = new JScrollPane(pant);
		panelGeneral.add(scrolito);
		scrolito.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// menu start
		mainMenuBar = new JMenuBar();
		menuConfig = new JMenu("Configuracion");
		menuConfigPuerto = new JMenuItem("Puerto");
		menuConfigOtro = new JMenuItem("Otros");
		menuConfig.add(menuConfigPuerto);
		menuConfig.add(menuConfigOtro);
		menuConfigPuerto.addActionListener(this);
		menuConfigOtro.addActionListener(this);

		menuInformacion = new JMenu("Informacion");
		subInformacion = new JMenuItem("Acerca De");
		subInformacion.addActionListener(this);
		menuInformacion.add(subInformacion);

		mainMenuBar.add(menuConfig);
		mainMenuBar.add(menuInformacion);

		menuConfig.addActionListener(this);

		setJMenuBar(mainMenuBar);
		// fin menu

		subPanel1.add(botonIniciar);
		subPanel2.add(botonParar);
		panel2.add(subPanel1);
		panel2.add(subPanel2);
		add(panelGeneral);
		setVisible(true);
		botonParar.setEnabled(false);

	}

	public void setSocket(Socket s) {
		this.conexion = s;
	}

	public void setText(String texto) {
		pant.append("\n" + texto);
	}
    /**
     * metodo que cierra el socket de conexion y vuelve a poner el servidor en escucha
     */
	public void cerrarConexion() {
		if (conexion != null) {			
			try {
				conexion.close();
				setText("La conexion  ha sido cerrada");
			} catch (IOException e1) {
				setText("fallo cerrando la conexion!!!");

			}
			setSocket(null);
			// p1.botonIniciar.setEnabled(true); activar si no queremos que el
			// servidor se ponga a escuchar automatico
			Servidor.IniciarServidor(); // desactrivar si no queremos que se
										// reinicie el servidor al acabar con un
										// cliente (manual)
			//botonParar.setEnabled(false);
		}
		else{
			setText("No hay Ninguna conexion que puedas reiniciar");
		}
	}
	
	/**
     * metodo que cierra el socket de conexion y cierra la escucha del servidor
     */
	public void detenerConexion(){
		
		if (conexion != null) {			
			try {
				conexion.close();
				setSocket(null);
			} catch (IOException e1) {				
			}
		}		
				
		if(Servidor.Servicio!= null){
		Servidor.Servicio.pararServerSocket(); // paramos el servidor por si esta corriendo
		Servidor.Servicio=null;		
		}
		Servidor.enviandoVideo=false; //cambiando esta variable la clase enviarVideo sale del bucle 
		botonIniciar.setEnabled(true);
		botonParar.setEnabled(false);
		setText("Exito al detener la escucha");
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == botonIniciar) {

			Servidor.IniciarServidor();
			botonIniciar.setEnabled(false);
			botonParar.setEnabled(true);
		}

		if (e.getSource() == subInformacion) {

			JOptionPane.showMessageDialog(null,
					"Aplicacion Creada Por Saul Blanco Y Eros Tamargo \n para Proyecto del Grado superior de Desarrollo de aplicaciones multiplataforma",
					"RemotEasy", 1);
		}
		if (e.getSource() == menuConfigPuerto){
			String a=JOptionPane.showInputDialog(null, "Inserte el puerto por el que escuchara el servidor");
			if(conexion==null){
			Servidor.PUERTO = Integer.parseInt(a);
			setText("Puerto cambiado al: " + Servidor.PUERTO);	
			}
			else{
				setText("ERROR, debes parar el servidor antes de cambiar el puerto");	
			}
		}
		if (e.getSource() == botonParar) {

			detenerConexion();
		}

	}// fin actionperform

}
