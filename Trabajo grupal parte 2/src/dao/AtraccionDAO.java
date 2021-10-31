package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.MissingDataException;
import jdbc.ConnectionProvider;
import trabajo_grupal.Atraccion;
import trabajo_grupal.TipoAtraccion;

public class AtraccionDAO {

	private Atraccion toAtraccion(ResultSet result) {	
		try {
			return new Atraccion(result.getString(1), result.getDouble(2), result.getDouble(3), result.getInt(4), TipoAtraccion.valueOf(result.getString(5)));
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public ArrayList<Atraccion> getAll() {
		try {
			String query = "SELECT nombre,costo,tiempo, personas,tipo_atraccion FROM Atraccion";
			Connection conn = ConnectionProvider.getConnection();
			
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			
			ArrayList<Atraccion> Atracciones = new ArrayList<Atraccion>();
			while(results.next()) {
				Atracciones.add(toAtraccion(results));
			}
			
			return Atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}		
	}
}