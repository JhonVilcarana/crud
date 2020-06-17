package pe.edu.upeu.crud.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pe.edu.upeu.crud.dao.AlumnoDao;
import pe.edu.upeu.crud.dao.EscuelaDao;
import pe.edu.upeu.crud.daolmp.AlumnoDaolmp;
import pe.edu.upeu.crud.daolmp.EscuelaDaolmp;
import pe.edu.upeu.crud.entity.Alumno;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/hc")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AlumnoDao al = new AlumnoDaolmp();
    private EscuelaDao es = new EscuelaDaolmp();
    private Gson g = new Gson();
    int idal, ides; String apelnombres, correo, telefono;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int op = Integer.parseInt(request.getParameter("opc"));
		switch (op) {
		//listar escuelas
		case 1: out.println(g.toJson(es.readAll()));
		break;
		//listar alumnos
		case 2: out.println(g.toJson(al.readAll()));
			
			break;
			//registrar alumnos
		case 3:	al.create(new Alumno(0,Integer.parseInt(request.getParameter("es")), request.getParameter("apelnombres"),
				request.getParameter("correo"),request.getParameter("telefono")));
		out.println(g.toJson("Registro guardado correctamente"));
		break;
		//buscar alumno por id
		case 4: out.println(g.toJson(al.read(Integer.parseInt(request.getParameter("id")))));
		break;
		//eliminar alumno
		case 5:
			int x = al.delete(Integer.parseInt(request.getParameter("id")));
			out.println(g.toJson(x));
			break;
			//modificar alumno
		case 6:
			idal=Integer.parseInt(request.getParameter("idal"));
			ides = Integer.parseInt(request.getParameter("ides"));
			apelnombres = request.getParameter("apelnombres");
			correo = request.getParameter("correo");
			telefono = request.getParameter("telefono");
			out.println(g.toJson(al.update(new Alumno(idal,ides, apelnombres, correo, telefono))));
			break;		
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
