package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.MissingDataException;
import jdbc.ConnectionProvider;
import trabajo_grupal.Atraccion;
import trabajo_grupal.Promocion;
import trabajo_grupal.Promocion_Descuento;
import trabajo_grupal.Promocion_Gratis;
import trabajo_grupal.Promocion_Total;

public class PromocionDAO {


	
	public static ArrayList<Promocion> leerPromociones(ArrayList<Atraccion> atracciones) {
		ArrayList<Promocion> promociones= new ArrayList<Promocion>();
		try {
		String query = "SELECT nombre,tipo,total,descuento, gratis FROM Promociones";
		Connection conn = ConnectionProvider.getConnection();
		
		PreparedStatement statement = conn.prepareStatement(query);
		ResultSet results = statement.executeQuery();
		
		while(results.next()) {
		String nombre_Promocion = results.getString(1);
		String tipo_Promocion = results.getString(2);
		
				ArrayList<Atraccion> atracciones_Promocion= new ArrayList<Atraccion>();
					
				// buscar las atracciones de la promocion dentro del listado de las atracciones de sistema
				String query1 = "SELECT atraccion  FROM Atraccion_Promocion where promocion= '"+nombre_Promocion+"'";
				Connection conn1 = ConnectionProvider.getConnection();
				
				PreparedStatement statement1 = conn1.prepareStatement(query1);
				ResultSet results1 = statement1.executeQuery();
				
				while(results1.next()) {
					
					for(Atraccion a : atracciones) {
						if (a.getNombre().equals(results1.getString(1))) {
							atracciones_Promocion.add(a);
							
						}
						
						
					}
				}
				
				
				
		//		crear las promociones segun el tipo y agregarlos al sistema
				
				switch (tipo_Promocion) 
		        {
		            case "total": 
		            	promociones.add(new Promocion_Total(nombre_Promocion,atracciones_Promocion,results.getDouble(3)));
		                     break;
		            case "gratis": 
		            	Atraccion atraccion_Gratis = new Atraccion();
		            	for(Atraccion a : atracciones) {
							if (a.getNombre().equals(results.getString(5))) {
								atraccion_Gratis = a;
							}
							
						}
	          	promociones.add(new Promocion_Gratis(nombre_Promocion,atracciones_Promocion,atraccion_Gratis));
	                     break;
		            case "descuento": 
		            	promociones.add(new Promocion_Descuento(nombre_Promocion,atracciones_Promocion,results.getDouble(4)));
	                     break;
		        }
		
				
				
				
		}
		
		return promociones;
	} catch (Exception e) {
		throw new MissingDataException(e);
	}		
}
	}
	

