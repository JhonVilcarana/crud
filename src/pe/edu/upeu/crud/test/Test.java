package pe.edu.upeu.crud.test;



import com.google.gson.Gson;

import pe.edu.upeu.crud.dao.AlumnoDao;
import pe.edu.upeu.crud.dao.EscuelaDao;
import pe.edu.upeu.crud.daolmp.AlumnoDaolmp;
import pe.edu.upeu.crud.daolmp.EscuelaDaolmp;

import pe.edu.upeu.crud.util.Conexion;



public class Test {
	private static EscuelaDao es = new EscuelaDaolmp();
	private static AlumnoDao al = new AlumnoDaolmp(); 
	private static Gson g = new Gson();
	

	
	public static void main(String[] args) {
		conex();
		listares();
		
	}
	static void conex() {
		if (Conexion.getConexion()!= null) {
			System.out.println("Conectado");
		} else {
			System.out.println("Desconectado");

		}
	}
	static void listares() {
		System.out.println(g.toJson(es.readAll()));
	}
	static void listaral() {
		System.out.println(g.toJson(al.readAll()));
	}

	

}
