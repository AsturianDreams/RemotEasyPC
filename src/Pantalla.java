import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;
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
		
	
		panel2.add(botonIniciar);
		panel2.add(botonParar);
		
		botonIniciar.addActionListener(this);
		
		scrolito = new JScrollPane(pant); 
		panelGeneral.add(scrolito);		
		scrolito.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	
		
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
	}
	

}