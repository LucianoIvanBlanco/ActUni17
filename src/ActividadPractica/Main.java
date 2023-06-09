/****************************************************************************************************************************************************************
 * Actividad practica Unidad 17 "JDBC".
 * Autor: Luciano Ivan Blanco
 * Fecha:26/05/2023
 * Versión: 1 Clase "MAIN" 1/8
 * Utilidad: Este programa es un conjunto de clases que interactuan entre si, brindando al usuario una seria de ventanas para que
 * este pueda crear, modificar y logear un usuario. Los datos del usuario se guardan en una base de datos en MySQL".
 * Objetivo: Iniciar la ejecucion del programa.
 *****************************************************************************************************************************************************************/

package ActividadPractica;

public class Main {

	public static void main(String[] args) {
		Controlador miControlador = new Controlador();
		Modelo miModelo = new Modelo();
		Vista miVista = new Vista();
		Error miError = new Error();
		Registro miRegistro = new Registro();
		Usuario miUsuario = new Usuario();
		Perfil miPerfil = new Perfil();

		miControlador.setMiModelo(miModelo);
		miControlador.setMiVista(miVista);
		miControlador.setMiError(miError);
		miControlador.setMiRegistro(miRegistro);
		miControlador.setMiUsuario(miUsuario);
		miControlador.setMiPerfil(miPerfil);
		
		miModelo.setMiVista(miVista);
		miModelo.setMiError(miError);
		miModelo.setMiControlador(miControlador);
		miModelo.setMiUsuario(miUsuario);
		
		miVista.setMiControlador(miControlador);
		miVista.setMiModelo(miModelo);
		
		miError.setMiControlador(miControlador);
		miError.setMiModelo(miModelo);
		
		miPerfil.setMiControlador(miControlador);
		miPerfil.setMiModelo(miModelo);
		
		miRegistro.setMiControlador(miControlador);
		miRegistro.setMiModelo(miModelo);
		
		miUsuario.setMiModelo(miModelo);
		miUsuario.setMiControlador(miControlador);

		miVista.setVisible(true);

	}

}
