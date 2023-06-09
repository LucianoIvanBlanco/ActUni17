/****************************************************************************************************************************************************************
 * Actividad practica Unidad 17 "JDBC".
 * Autor: Luciano Ivan Blanco
 * Fecha:26/05/2023
 * Versión: 1 Clase "Controlador" 4/8
 * Utilidad: Este programa es un conjunto de clases que interactuan entre si, brindando al usuario una seria de ventanas para que
 * este pueda crear, modificar y logear un usuario. Los datos del usuario se guardan en una base de datos en MySQL".
 * Objetivo: Desde aqui controlamos el cambio de ventanas de nustro programa, tambien tenemos algunos metodos que interactuan con el.
 *****************************************************************************************************************************************************************/
package ActividadPractica;

import javax.swing.JTextField;


public class Controlador {
	private Modelo miModelo;
	private Vista miVista;
	private Registro miRegistro;
	private Usuario miUsuario;
	private Error miError;
	private Perfil miPerfil;
	private String usr; 							// variable donde almacenamos el nombre del usuario a modificar el pwd
	private int contador;


	public void login(JTextField txtUsr, JTextField txtPwd) { 					//METODO PARA HACER LOGIN
		miVista.lblUserCre.setVisible(false);
		String aux = txtUsr.getText();
		String pwd = txtPwd.getText();
		miModelo.login(aux, pwd);
		miVista.setTxtUsr(""); 														// borramos los campos
		miVista.setTxtPwd("");
		miUsuario.actualizarTabla();

	}
	
	public void perfil() {

		miPerfil.setVisible(true);
		miVista.setVisible(false);

	}
	
	public void error() {										//ACTIVA LA VENTANA ERROR
		miVista.setTxtUsr("");
		miVista.setTxtPwd("");
//		contador ++;											// Tambien probe darle los fallos asi pero tampoco me funciona
//		miError.setFallos(contador);
		miError.setVisible(true);
		miVista.setVisible(false);
		
	}
	
	public void cambiarPwd(JTextField txtPass, JTextField txtRep) { // COMPARA LAS PWD Y SI COINCIDEN LAS CAMBIA
		miVista.lblUserCre.setVisible(false);
		String Pass = txtPass.getText();
		String Rep = txtRep.getText();
		String aux = usr;
		if (Pass.contentEquals(Rep)) { // si son iguales las contraseñas lo modificamos
			miModelo.modificarUser2(aux, txtRep);
			miModelo.setFallos(0); // contador a
			miPerfil.setTxtPass(""); // borramos los campos
			miPerfil.setTxtRep("");
			miVista.setVisible(true); // vamos a la ventana LOGIN y mostramos mensaje de cambio de pwd ok
			miVista.lblPassMod.setVisible(true);
			miPerfil.setVisible(false);
		} else {
			miPerfil.lblError.setVisible(true); // si no son iguales, mostramos el mensaje de error.
			
			miPerfil.setTxtPass(""); // borramos los campos
			miPerfil.setTxtRep("");
		}
		miUsuario.actualizarTabla();
	}
	
	public void volverRegistro() {							// PARA VOLVER A LOGIN DESDE EL REGISTRO
		miRegistro.setTxtUser(""); // borramos los campos
		miRegistro.setTxtPass("");
		miModelo.setFallos(0); // volvemos el contador de errores a 0
		miVista.setVisible(true); // vamos vamos a la ventana de login
		miRegistro.setVisible(false);
	}
	
	public void volver() {							// PARA USAR BOTON VOLVEr EN VENTANA PERFIL, USUARIO Y ERROR

		if (miPerfil.isVisible()) { // si la ventana miPerfil esta visible
			miPerfil.setTxtPass(""); // borramos los campos
			miPerfil.setTxtRep("");
			miVista.setVisible(true); // abrimos la ventana miVista
			miPerfil.setVisible(false);// cerramos la ventana miPerfil
		} else { // si no esta visible
			miVista.setTxtUsr(""); // borramos los campos
			miVista.setTxtPwd("");
			miVista.setVisible(true); // abrimos la ventana miVista
			miError.setVisible(false); // cerramos la ventana miError
			miUsuario.setVisible(false);
		}

	}
	
	public void nuevoUsuario() {								// PARA CREAR NUEVO USUARIO DESDE REGISTRO
		miVista.lblPassMod.setVisible(false);
		JTextField txtUser = miRegistro.getTxtUser();
		JTextField txtPass = miRegistro.getTxtPass();
		miModelo.newUser(txtUser, txtPass); // creamos el nuevo usuario
		miModelo.setFallos(0); // volvemos el contador de errores a 0
		miRegistro.setTxtUser(""); // borramos los campos
		miRegistro.setTxtPass("");
		miVista.setVisible(true); // vamos vamos a la ventana de login
		miRegistro.setVisible(false);
		miVista.lblUserCre.setVisible(true); // avisamos que se creo el usuario
	}
	
	public void registro() {													// ACTIVA LA VENTANA REGISTRO
		miVista.lblUserCre.setVisible(false);
		miRegistro.setVisible(true);
		miVista.setVisible(false);
		miVista.setTxtUsr(""); // borramos los campos
		miVista.setTxtPwd("");

	}
	
	public void usuarioAdmin() {												// ACTIVA LA VENTANA USUARIO
		miVista.setTxtUsr(""); // borramos los campos
		miVista.setTxtPwd("");
		miUsuario.setVisible(true);
		miVista.setVisible(false);

	}
	
	public void setMiModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	public void setMiVista(Vista miVista) {
		this.miVista = miVista;
	}

	public void setMiRegistro(Registro miRegistro) {
		this.miRegistro = miRegistro;
	}

	public void setMiUsuario(Usuario miUsuario) {
		this.miUsuario = miUsuario;
	}

	public void setMiError(Error miError) {
		this.miError = miError;
	}

	public void setMiPerfil(Perfil miPerfil) {
		this.miPerfil = miPerfil;

	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String user) {
		this.usr = user;
	}
	
	
	
	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

}
