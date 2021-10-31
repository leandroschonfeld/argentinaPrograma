package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import exceptions.MissingDataException;
import jdbc.ConnectionProvider;
import trabajo_grupal.Atraccion;

public class ItinerarioDAO {
	public void  AddItinerario(int id_visitante,String atraccion_promocion) {
		try {
			String query = "INSERT INTO Itinerario VALUES ("+id_visitante+",'"+atraccion_promocion+"')";
			Connection conn = ConnectionProvider.getConnection();
			
			PreparedStatement statement = conn.prepareStatement(query);
			statement.executeUpdate();
			
			
		} catch (Exception e) {
			throw new MissingDataException(e);
		}		
	}
}
