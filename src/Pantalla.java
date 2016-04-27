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
	
	
	private static final long serialVersionUID = 1L;
	static JTextArea pant;
	JPanel panel;
	JButton botonIniciar;
	 
	public Pantalla(){
		super("RemotEasy");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(750,500);
		
		panel= new JPanel();
		pant= new JTextArea(35,55);
		pant.setEditable(false);
		botonIniciar = new JButton("Arrancar");
		
		panel.add(pant);
		panel.add(botonIniciar);
		botonIniciar.addActionListener(this);
		
		
		
		
	
		
		add(panel);
		setVisible(true);
		
	}
	
	
	public void setText(String texto){
		pant.append("\n" + texto);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==botonIniciar){
			
			Servidor.IniciarServidor();
		}
	}
	

}