/****************************************************************************************************************************************************************
 * Actividad practica Unidad 17 "JDBC".
 * Autor: Luciano Ivan Blanco
 * Fecha:26/05/2023
 * Versión: 1 Clase "Registro" 8/8
 * Utilidad: Este programa es un conjunto de clases que interactuan entre si, brindando al usuario una seria de ventanas para que
 * este pueda crear, modificar y logear un usuario. Los datos del usuario se guardan en una base de datos en MySQL".
 * Objetivo: Ventana da acceso a un nuevo registro de usuario.
 *****************************************************************************************************************************************************************/
package ActividadPractica;

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
import javax.swing.border.EmptyBorder;;

public class Registro extends JFrame {

	private Controlador miControlador;
	private Modelo miModelo;

	private JPanel contentPane;
	private JLabel lblUser;
	private JLabel lblPass;
	private JButton btnVolver;
	private JButton btnNewUser;
	private JTextField txtUser;
	private JTextField txtPass;

	public Registro() {
		setTitle("Registro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblUser = new JLabel("Usuario");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUser.setBounds(48, 44, 72, 25);
		contentPane.add(lblUser);

		lblPass = new JLabel("Contraseña");
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPass.setBounds(48, 92, 72, 25);
		contentPane.add(lblPass);

		txtUser = new JTextField();
		txtUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				activaBoton(miModelo.comprobarCont(txtUser, txtPass));
			}
		});

		txtUser.setColumns(10);
		txtUser.setBounds(179, 47, 140, 21);
		contentPane.add(txtUser);

		txtPass = new JPasswordField();
		txtPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				activaBoton(miModelo.comprobarCont(txtUser, txtPass));
			}
		});
		txtPass.setColumns(10);
		txtPass.setBounds(179, 95, 140, 21);
		contentPane.add(txtPass);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.volverRegistro();

			}
		});

		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVolver.setBounds(48, 214, 111, 25);
		contentPane.add(btnVolver);

		btnNewUser = new JButton("Nuevo Usuario");
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.nuevoUsuario();
			}
		});
		btnNewUser.setEnabled(false);

		btnNewUser.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewUser.setBounds(174, 214, 145, 25);
		contentPane.add(btnNewUser);
	}

	public void activaBoton(boolean e) { // Metodo para comprobar si los campos de textos tienen contenido y asi activar
		// boton
		if (e == true) {
			btnNewUser.setEnabled(true);
		} else {
			btnNewUser.setEnabled(false);
		}
	}

	public void setMiControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public void setMiModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	public JTextField getTxtUser() {
		return txtUser;
	}

	public JTextField getTxtPass() {
		return txtPass;
	}

	public void setTxtPass(String a) {
		txtPass.setText(a);
	}

	public void setTxtUser(String a) {
		txtUser.setText(a);
	}

}
