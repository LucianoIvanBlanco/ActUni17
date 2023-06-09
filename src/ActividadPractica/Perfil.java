/****************************************************************************************************************************************************************
 * Actividad practica Unidad 17 "JDBC".
 * Autor: Luciano Ivan Blanco
 * Fecha:26/05/2023
 * Versión: 1 Clase "Perfil" 5/8
 * Utilidad: Este programa es un conjunto de clases que interactuan entre si, brindando al usuario una seria de ventanas para que
 * este pueda crear, modificar y logear un usuario. Los datos del usuario se guardan en una base de datos en MySQL".
 * Objetivo: Ventana que da acceso a una interfaz para modificar la contraseña.
 *****************************************************************************************************************************************************************/
package ActividadPractica;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Perfil extends JFrame {
	private Controlador miControlador;
	private Modelo miModelo;

	private JTextField txtPass;
	private JTextField txtRep;
	private JLabel lblPas;
	private JLabel lblRep;
	private JButton btnVolver;
	private JButton btnCambiar;
	protected JLabel lblError;

	public Perfil() {
		setTitle("Perfil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, 436, 263);

		lblPas = new JLabel("Contraseña");
		lblPas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPas.setBounds(48, 64, 72, 25);
		contentPane.add(lblPas);

		lblRep = new JLabel("Repite");
		lblRep.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRep.setBounds(48, 116, 72, 25);
		contentPane.add(lblRep);

		txtPass = new JPasswordField();
		txtPass.addKeyListener(new KeyAdapter() { // Comprobamos el contenido del campo de texto
			public void keyReleased(KeyEvent e) {
				activaBoton(miModelo.comprobarCont(txtPass, txtRep));
			}
		});
		txtPass.setColumns(10);
		txtPass.setBounds(176, 67, 148, 21);
		contentPane.add(txtPass);

		txtRep = new JPasswordField();
		txtRep.addKeyListener(new KeyAdapter() { // Comprobamos el contenido del campo de texto
			public void keyReleased(KeyEvent e) {
				activaBoton(miModelo.comprobarCont(txtPass, txtRep));
			}
		});
		txtRep.setColumns(10);
		txtRep.setBounds(176, 119, 148, 21);
		contentPane.add(txtRep);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.volver();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVolver.setBounds(48, 213, 109, 25);
		contentPane.add(btnVolver);

		btnCambiar = new JButton("Cambiar");
		btnCambiar.setEnabled(false);
		btnCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.cambiarPwd(txtPass, txtRep);
			}
		});
		btnCambiar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCambiar.setBounds(176, 213, 148, 25);
		contentPane.add(btnCambiar);

		lblError = new JLabel("Las contraseñas no son iguales");
		lblError.setVisible(false);
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblError.setBounds(48, 158, 202, 25);
		contentPane.add(lblError);
	}

	public void activaBoton(boolean e) { // Metodo para comprobar si los campos de textos tienen contenido y asi activar
											// boton
		if (e == true) {
			btnCambiar.setEnabled(true);
		} else {
			btnCambiar.setEnabled(false);
		}
	}

	public JTextField getTxtPass() {
		return txtPass;
	}

	public JTextField getTxtRep() {
		return txtRep;
	}

	public void setTxtPass(String a) {
		txtPass.setText(a);
	}

	public void setTxtRep(String a) {
		txtRep.setText(a);
	}

	public void setMiControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public void setMiModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}
}
