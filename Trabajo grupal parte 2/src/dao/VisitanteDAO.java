package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.MissingDataException;
import jdbc.ConnectionProvider;
import trabajo_grupal.Visitante;
import trabajo_grupal.TipoAtraccion;

public class VisitanteDAO {

	private Visitante toVisitante(ResultSet result) {	
		try {System.out.println(result.getString(2));
			return new Visitante(result.getInt(1), result.getString(2), result.getDouble(3), result.getDouble(4), TipoAtraccion.valueOf(result.getString(5)));
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public ArrayList<Visitante> getAll() {
		try {
			String query = "SELECT id_visitante,nombre,presupuesto,tiempo,tipo_atraccion FROM Visitante";
			Connection conn = ConnectionProvider.getConnection();
			
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			
			ArrayList<Visitante> Visitantes = new ArrayList<Visitante>();
			while(results.next()) {
				Visitantes.add(toVisitante(results));
			}
			
			return Visitantes;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}		
	}
}
