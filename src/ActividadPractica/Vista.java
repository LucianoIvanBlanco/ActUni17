/****************************************************************************************************************************************************************
 * Actividad practica Unidad 17 "JDBC".
 * Autor: Luciano Ivan Blanco
 * Fecha:26/05/2023
 * Versión: 1 Clase "Vista" 3/8
 * Utilidad: Este programa es un conjunto de clases que interactuan entre si, brindando al usuario una seria de ventanas para que
 * este pueda crear, modificar y logear un usuario. Los datos del usuario se guardan en una base de datos en MySQL".
 * Objetivo: Ventana perfil.
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

public class Vista extends JFrame {
	private Controlador miControlador;
	private Modelo miModelo;

	private JPanel contentPane;
	private JLabel lblUser;
	private JLabel lblPass;
	private JTextField txtUsr;
	private JTextField txtPwd;
	protected JLabel lblUserCre;
	protected JLabel lblPassMod;
	private JButton btnLogin;
	private JButton btnRegistro;

	public Vista() { // CONSTRUCTOR

		setTitle("Login");
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

		txtUsr = new JTextField();
		txtUsr.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) { // Comprobamos el contenido
				activaBoton(miModelo.comprobarCont(txtUsr, txtPwd));
			}
		});
		txtUsr.setBounds(163, 48, 140, 21);
		contentPane.add(txtUsr);
		txtUsr.setColumns(10);

		txtPwd = new JPasswordField();
		txtPwd.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) { // Comprobamos el contenido
				// llamamos al metodo
				activaBoton(miModelo.comprobarCont(txtUsr, txtPwd));

			}
		});
		txtPwd.setColumns(10);
		txtPwd.setBounds(163, 96, 140, 21);
		contentPane.add(txtPwd);

		btnLogin = new JButton("Login");
		btnLogin.setEnabled(false);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.login(txtUsr, txtPwd);

			}
		});

		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.setBounds(163, 173, 140, 25);
		contentPane.add(btnLogin);

		btnRegistro = new JButton("Registro");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.registro();
			}
		});

		btnRegistro.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistro.setBounds(163, 208, 140, 25);
		contentPane.add(btnRegistro);

		lblUserCre = new JLabel("Usuario creado");
		lblUserCre.setVisible(false);
		lblUserCre.setForeground(new Color(255, 0, 0));
		lblUserCre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUserCre.setBounds(48, 138, 110, 25);
		contentPane.add(lblUserCre);

		lblPassMod = new JLabel("Contraseña modificada");
		lblPassMod.setVisible(false);
		lblPassMod.setForeground(new Color(255, 0, 0));
		lblPassMod.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassMod.setBounds(48, 138, 146, 25);
		contentPane.add(lblPassMod);
	}
	
	public void actualizar(String resultado) { // METODO PARA ACTUALZAR LA ENTRADA DE DATOS DE LA VENTANA LOGIN

		if (resultado == "admin") {
			miControlador.usuarioAdmin();
		} else if (resultado == "Correcto") {
			miControlador.perfil();
		} else if (resultado == "Incorrecto") {
			miControlador.error();

		} else {
			System.exit(0);
		}

	}

	public void activaBoton(boolean e) { // METODO PARA COMPROBAR SI LOS CAMPOS TENEN CPONTENIDO
		if (e == true) {
			btnLogin.setEnabled(true);
		} else {
			btnLogin.setEnabled(false);
		}
	}

	public void setMiControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public void setMiModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	public String getUsr() {
		return txtUsr.getText();
	}

	public String getPwd() {
		return String.valueOf(((JPasswordField) txtPwd).getPassword());

	}

	public JTextField getTxtUsr() {
		return txtUsr;
	}

	public JTextField getTxtPwd() {
		return txtPwd;
	}

	public void setTxtUsr(String a) {
		txtUsr.setText(a);
	}

	public void setTxtPwd(String a) {
		txtPwd.setText(a);
	}

}
