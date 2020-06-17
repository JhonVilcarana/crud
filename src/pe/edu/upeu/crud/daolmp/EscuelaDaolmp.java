package pe.edu.upeu.crud.daolmp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pe.edu.upeu.crud.dao.EscuelaDao;
import pe.edu.upeu.crud.entity.Escuela;
import pe.edu.upeu.crud.util.Conexion;

public class EscuelaDaolmp implements EscuelaDao {
	private PreparedStatement ps;
	private ResultSet rs;
	private Connection cx = null;
	
	@Override
	public int create(Escuela u) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int update(Escuela u) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Escuela read(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Escuela> readAll() {
		// TODO Auto-generated method stub
		List<Escuela> list = new ArrayList<>();
		String sql = "select * from escuela";
		try {
			cx = Conexion.getConexion();
			ps = cx.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Escuela a = new Escuela();
				a.setIdescuela(rs.getInt("idescuela"));
				a.setNombrecat(rs.getString("nombrecat"));
				list.add(a);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return list;
	}

}
