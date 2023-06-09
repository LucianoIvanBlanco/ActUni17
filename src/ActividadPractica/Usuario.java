/****************************************************************************************************************************************************************
 * Actividad practica Unidad 17 "JDBC".
 * Autor: Luciano Ivan Blanco
 * Fecha:26/05/2023
 * Versión: 1 Clase "Usuario" 7/8
 * Utilidad: Este programa es un conjunto de clases que interactuan entre si, brindando al usuario una seria de ventanas para que
 * este pueda crear, modificar y logear un usuario. Los datos del usuario se guardan en una base de datos en MySQL".
 * Objetivo: Ventana a la que unicamente tiene acceso el administrador y puede dar de alta, modificar y borrar usuarios.
 *****************************************************************************************************************************************************************/
package ActividadPractica;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Usuario extends JFrame {

	private Controlador miControlador;
	private Modelo miModelo;

	JTable tabla;
	private JScrollPane scrollPane;
	private DefaultTableModel modelo;
	private JButton btnAlta;
	private JButton btnBaja;
	private JButton btnMod;
	private JTextField txtUsr;
	private JTextField txtPwd;
	private JButton btnVolver;

	public Usuario() {
		setTitle("Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, 436, 263);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.volver();
			}
		});

		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVolver.setBounds(10, 230, 84, 23);
		contentPane.add(btnVolver);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 44, 352, 133);
		contentPane.add(scrollPane);

		tabla = new JTable();
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int fila = tabla.getSelectedRow();
				txtUsr.setText((String) miModelo.getMiTabla().getValueAt(fila, 0));
				txtPwd.setText((String) miModelo.getMiTabla().getValueAt(fila, 1));
				updateBaja();
				updateModificar();
			}
		});

		scrollPane.setViewportView(tabla);

		btnAlta = new JButton("Alta");
		btnAlta.setEnabled(false);
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miModelo.newUser(txtUsr, txtPwd);
				actualizarTabla();
			}
		});
		btnAlta.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAlta.setBounds(104, 230, 84, 23);
		contentPane.add(btnAlta);

		btnBaja = new JButton("Baja");
		btnBaja.setEnabled(false);
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBaja();
				miModelo.eliminar(txtUsr, txtPwd);
				actualizarTabla();
			}
		});
		btnBaja.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBaja.setBounds(198, 230, 84, 23);
		contentPane.add(btnBaja);

		btnMod = new JButton("Modificar");
		btnMod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miModelo.modificarUser(txtUsr, txtPwd);
				actualizarTabla();
			}
		});
		btnMod.setEnabled(false);
		btnMod.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMod.setBounds(292, 230, 134, 23);
		contentPane.add(btnMod);

		txtUsr = new JTextField();
		txtUsr.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) { // Comprobamos el contenido
				activaBoton(miModelo.comprobarCont(txtUsr, txtPwd));
			}
		});
		txtUsr.setBounds(35, 187, 168, 19);
		contentPane.add(txtUsr);
		txtUsr.setColumns(10);

		txtPwd = new JTextField();
		txtPwd.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) { // Comprobamos el contenido
				activaBoton(miModelo.comprobarCont(txtUsr, txtPwd));
			}
		});
		txtPwd.setColumns(10);
		txtPwd.setBounds(219, 187, 168, 19);
		contentPane.add(txtPwd);
	}

	private void updateModificar() { // metodo para habilitar boton
		if (txtUsr.getText().length() == 0 || txtPwd.getText().length() == 0 || tabla.getSelectedRow() == -1) {
			btnMod.setEnabled(false);
		} else {
			btnMod.setEnabled(true);
		}
	}

	public void actualizarTabla() { // metodo para actualizar la tabla con los datos del MAP
		tabla.setModel(miModelo.getTabla());
		miModelo.cargarTabla();
	}

	public void activaBoton(boolean e) { // Metodo para comprobar si los campos de textos tienen contenido y asi activar
											// boton
		if (e == true) {
			btnAlta.setEnabled(true);
			btnMod.setEnabled(true);
		} else {
			btnAlta.setEnabled(false);
			btnMod.setEnabled(false);
		}
	}

	public void setMiControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public void setMiModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	public void setTxtUsr(String a) {
		txtUsr.setText(a);
	}

	public void setTxtPwd(String a) {
		txtPwd.setText(a);
	}

	public JTable getTabla() {
		return tabla;
	}

	public void setTabla(JTable tabla) {
		this.tabla = tabla;
	}

	public DefaultTableModel getModelo() {
		return modelo;
	}

	public void setModelo(DefaultTableModel modelo) {
		this.modelo = modelo;
	}

	private void updateBaja() { // metodo para habilitar boton
		if (tabla.getSelectedRow() == -1) {
			btnBaja.setEnabled(false);
		} else {
			btnBaja.setEnabled(true);
		}
	}

}