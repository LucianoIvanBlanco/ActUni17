/****************************************************************************************************************************************************************
 * Actividad practica Unidad 17 "JDBC".
 * Autor: Luciano Ivan Blanco
 * Fecha:26/05/2023
 * Versión: 1 Clase "Modelo" 2/8
 * Utilidad: Este programa es un conjunto de clases que interactuan entre si, brindando al usuario una seria de ventanas para que
 * este pueda crear, modificar y logear un usuario. Los datos del usuario se guardan en una base de datos en MySQL".
 * Objetivo: Almacena algunos datos y los metodos del programa.
 *****************************************************************************************************************************************************************/
package ActividadPractica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Modelo {
	private Vista miVista;
	private Perfil miPerfil;
	private Usuario miUsuario;
	private Registro miRegistro;
	private Controlador miControlador;
	private Error miError;

	private DefaultTableModel miTabla;
	private final String USERADMIN; // NOMBRE ADMINISTRADOR
	private String pwdAdmin; // PASS ADMINISTRADOR
	private int fallos;
	private String resultado;

	// Atributos para la conexion con la bd
	private String db = "damd";
	private String login = "root";
	private String pwd = "";
	private String url = "jdbc:mysql://localhost/" + db
			+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	private Connection conexion;
	private String select = "SELECT * FROM users";

	public Modelo() {
		USERADMIN = "admin";
		pwdAdmin = "admin";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, login, pwd);
			System.out.println("Conexion creada"); // prueba
		} catch (SQLException e) {
			System.err.println("Error BD: " + e.getMessage());
			System.exit(1);
		} catch (Exception e) {
			System.err.println("Error General: " + e.getMessage());
			System.exit(2);
		}
		cargarTabla();
	}

	public void terminar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private int getNumColumnas(String sql) {
		int num = 0;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			num = rsmd.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	private int getNumFilas(String sql) {
		int numFilas = 0;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();
			while (rset.next())
				numFilas++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numFilas;
	}

	public DefaultTableModel getTabla() {
		return getMiTabla();
	}

	public void cargarTabla() {
		int numColumnas = getNumColumnas(select);
		int numFilas = getNumFilas(select);
		String[] cabecera = new String[numColumnas];
		Object[][] contenido = new Object[numFilas][numColumnas];
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(select);
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			for (int i = 0; i < numColumnas; i++) {
				cabecera[i] = rsmd.getColumnName(i + 1);
			}
			int fila = 0;
			while (rset.next()) {
				for (int col = 1; col <= numColumnas; col++) {
					contenido[fila][col - 1] = rset.getString(col);
				}
				fila++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setMiTabla(new DefaultTableModel(contenido, cabecera));
	}

	public void login(String user, String pass) {
		miControlador.setUsr(user);
		String consultaUser = "SELECT * FROM users WHERE usr = ?";
		try {
			PreparedStatement stmt = conexion.prepareStatement(consultaUser);
			stmt.setString(1, user);
			ResultSet rset = stmt.executeQuery();

			boolean usuarioEncontrado = false;

			if (USERADMIN.equals(user) && pwdAdmin.equals(pass)) {
				fallos = 0;
				resultado = "admin";
				usuarioEncontrado = true;
			} else {
				while (rset.next()) {
					if (user.equals(rset.getString("usr")) && pass.equals(rset.getString("pwd"))) {
						resultado = "Correcto";
						fallos = 0;
						usuarioEncontrado = true;

						break; // Se encontró el usuario correcto, no es necesario seguir buscando
					}
				}

				if (!usuarioEncontrado) {
					fallos++;
					resultado = "Incorrecto";
				}

				if (fallos == 3) {
					resultado = "Cerrar";
				}
			}

			rset.close();
			stmt.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		miError.setFallos(fallos);					// metemos los fallos en la ventana error en cada iteracion
		miVista.actualizar(resultado);
		miUsuario.actualizarTabla();
	}

	public boolean comprobarCont(JTextField a, JTextField b) { // METODO PARA COMPROBAR SI LOS CAMPOS DE TEXTO TIENEN
																// CONteNIDO Y ASI ACTIVAR
																// DIFERENTES VENTANAS
		if (!a.getText().isEmpty() && !b.getText().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public void newUser(JTextField txtUsr, JTextField txtPwd) {				//CREAMOS NUEVO USUARIO
		String usr = txtUsr.getText();
		String pwd = txtPwd.getText();
		String newUser = "INSERT INTO users (usr, pwd) VALUES (?, ?)";
		try {
			PreparedStatement stmt = conexion.prepareStatement(newUser);
			stmt.setString(1, usr);
			stmt.setString(2, pwd);

			int rowCount = stmt.executeUpdate();
			if (rowCount > 0) {
			System.out.println("Usuario creado con exito");
			}
			

			stmt.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}

		miUsuario.setTxtUsr("");
		miUsuario.setTxtPwd("");
		miUsuario.actualizarTabla();

	}

	public void eliminar(JTextField txtUsr, JTextField txtPwd) { // ELIMINAMOS DATOS DESDE LA VENTANA USUSARIO
		String usr = txtUsr.getText();
		String pwd = txtPwd.getText();
		String deletUser = "DELETE FROM users WHERE usr ='" + usr + "'";
		try {
			Statement stmt = conexion.createStatement();
			int rset = stmt.executeUpdate(deletUser);
			System.out.println("Se ha eliminado " + rset + " usuario con exito");
			stmt.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		miUsuario.setTxtUsr(""); // borramos los campos
		miUsuario.setTxtPwd("");
		miUsuario.actualizarTabla(); // actualizamos la tabla

	}

	public void modificarUser(JTextField txtUsr, JTextField txtPwd) { // MODIFICAMOS USUARIO DESDE LA VENTANA USUARIO
		String usr = txtUsr.getText();
		String pwd = txtPwd.getText();
		int fila = miUsuario.getTabla().getSelectedRow();
		getMiTabla().setValueAt(txtUsr.getText(), fila, 0); // con este metodo le decimos que en la columna
															// seleccionada (ultimo parametro) queremos
															// meter el primer parametro (dato a insertar)
		getMiTabla().setValueAt(txtPwd.getText(), fila, 1);
		String modUser = "UPDATE users SET usr ='" + usr + "',pwd = '" + pwd + "'" + "WHERE usr = '" + usr + "'";
		try {
			Statement stmt = conexion.createStatement();
			int rset = stmt.executeUpdate(modUser);
			System.out.println("Se ha modificado " + rset + " usuario con exito");
			stmt.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		miUsuario.setTxtUsr(""); // limpiamos los campos
		miUsuario.setTxtPwd("");
		miUsuario.actualizarTabla(); // actualizamos la tabla

	}

	public void modificarUser2(String usr, JTextField txtPwd) { // MODIFICAMOS PASSWORD DESDE LA VENTANA PERFIL
		String aux = usr;
		String pwd = txtPwd.getText();
		String modUser = "UPDATE users SET pwd = '" + pwd + "' " + "WHERE usr ='" + aux + "'";
		try {
			Statement stmt = conexion.createStatement();
			int rset = stmt.executeUpdate(modUser);
			System.out.println("Se modifico el password de  " + rset + " registro");
			stmt.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		miUsuario.setTxtUsr(""); // limpiamos los campos
		miUsuario.setTxtPwd("");
		miUsuario.actualizarTabla(); // actualizamos la tabla

	}

	public void setMiControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public void setMiPerfil(Perfil miPerfil) {
		this.miPerfil = miPerfil;
	}

	public void setMiUsuario(Usuario miUsuario) {
		this.miUsuario = miUsuario;
	}

	public void setMiError(Error miError) {
		this.miError = miError;
	}

	public Error getMiError() {
		return miError;

	}

	public void setMiRegistro(Registro miRegistro) {
		this.miRegistro = miRegistro;
	}

	public void setMiVista(Vista miVista) {
		this.miVista = miVista;
	}

	public String getPwdadmin() {
		return pwdAdmin;

	}

	public void setPwdadmin(String pwdadmin) {
		this.pwdAdmin = pwdadmin;
	}

	public String getResultado() { // nos devuelve el resultado
		return this.resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public int getFallos() {
		return fallos;
	}

	public void setFallos(int fallos) { // SE USA??
		this.fallos = fallos;
	}

	public DefaultTableModel getMiTabla() {
		return miTabla;
	}

	public void setMiTabla(DefaultTableModel miTabla) {
		this.miTabla = miTabla;
	}

}
