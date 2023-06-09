/****************************************************************************************************************************************************************
 * Actividad practica Unidad 17 "JDBC".
 * Autor: Luciano Ivan Blanco
 * Fecha:26/05/2023
 * Versión: 1 Clase "Error" 6/8
 * Utilidad: Este programa es un conjunto de clases que interactuan entre si, brindando al usuario una seria de ventanas para que
 * este pueda crear, modificar y logear un usuario. Los datos del usuario se guardan en una base de datos en MySQL".
 * Objetivo: Ventana que se activa cuando hay un error de login.
 *****************************************************************************************************************************************************************/
package ActividadPractica;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Error extends JFrame {

	private Controlador miControlador;
	private Modelo miModelo;

	private JPanel contentPane;
	private JButton btnVolver;
	private JLabel lblContador;
	private int fallos;

	public Error() {
		
		setTitle("Error");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.volver();
			}
		});

		btnVolver.setBounds(53, 214, 83, 23);
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(btnVolver);
		
		lblContador = new JLabel("Número de errores: " + fallos);   // sigo sin poder obtener el numero de fallos. No se que estoy haciendo mal.
		lblContador.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		lblContador.setBounds(53, 105, 191, 31);
		contentPane.add(lblContador);
	}

	public JLabel getLblContador() {
		return lblContador;
	}

	public void setLblContador(JLabel lblContador) {
		this.lblContador = lblContador;
	}

	public void setMiControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public void setMiModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	public Modelo getMiModelo() {
		return miModelo;
	}

	public void setFallos(int fallos) {
		this.fallos = fallos;

	}

	public int getFallos() {
		return fallos;
	}
}
