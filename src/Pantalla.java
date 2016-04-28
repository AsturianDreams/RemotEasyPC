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
public class Pantalla extends JFrame implements ActionListener{
	
	
	
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
	JMenu menuAyuda, menuInformacion;
	JMenuItem menuItemHelp,  subInformacion, menuItemHelp2;
	
	public Pantalla(){
		super("RemotEasy");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(750,500);
		
		panelGeneral= new JPanel(new BorderLayout(20,20));
		panel1= new JPanel();
		panel2= new JPanel();
		BoxLayout a=new BoxLayout(panel2, BoxLayout.PAGE_AXIS);
		
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
		subPanel1= new JPanel();
		subPanel2= new JPanel();
		
		pant= new JTextArea(0,0); //no importa tamaño el layout lo ajusta al panel
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
		
		scrolito = new JScrollPane(pant); 
		panelGeneral.add(scrolito);		
		scrolito.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		//menu start
		mainMenuBar = new JMenuBar();
		menuAyuda = new JMenu("Ayuda");
			 menuItemHelp = new JMenuItem("Informacion1");
			 menuItemHelp2 = new JMenuItem("Informacion2");
			 menuAyuda.add( menuItemHelp);
			 menuAyuda.add( menuItemHelp2);
			 menuItemHelp.addActionListener(this);
			 menuItemHelp2.addActionListener(this);
			 
		menuInformacion = new JMenu("Informacion");	
			subInformacion = new JMenuItem("Acerca De");
			subInformacion.addActionListener(this);
			menuInformacion.add(subInformacion);
		
		 
		mainMenuBar.add(menuAyuda);
		mainMenuBar.add(menuInformacion);
		
		
		menuItemHelp.addActionListener(this);
		
		
		
		setJMenuBar(mainMenuBar);
		//fin menu
		
		subPanel1.add(botonIniciar);
		subPanel2.add(botonParar);
		panel2.add(subPanel1);
		panel2.add(subPanel2);
		add(panelGeneral);
		setVisible(true);
		
	}
	
	
	public void setText(String texto){
		pant.append("\n" + texto);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==botonIniciar){
			
			Servidor.IniciarServidor();
			botonIniciar.setEnabled(false);
		}
		
		if(e.getSource()==subInformacion){
			
			JOptionPane.showMessageDialog(null, "Aplicacion Creada Por Saúl Blanco Y Eros Tamargo \n para Proyecto del Grado superior de Desarrollo de aplicaciones multiplataforma", "RemotEasy", 1);
		}
	}
	

}