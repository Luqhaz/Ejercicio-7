package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Persona;
import entities.Rol;

public class DataRol_Persona {   // En este modelo de capas es recomendable tener una clase por cada tabla de la BD + DbConnector
	
	public void add(Persona p, int id) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into rol_persona(id_persona,id_rol) values(?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, p.getId());
			stmt.setInt(2, id);
			stmt.executeUpdate();
			

			
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}

	}
	
	public void remove(Persona p) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from rol_persona where id_persona=?");
			stmt.setInt(1, p.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
	
	public void update(Persona p , int id) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update rol_persona set id_rol=? where id_persona=?");
			stmt.setInt(1, id);
			stmt.setInt(2, p.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
	
}


