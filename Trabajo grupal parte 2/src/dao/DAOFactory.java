package dao;

public class DAOFactory {

	public static VisitanteDAO getVisitanteDAO() {
		return new VisitanteDAO();
	}

	public static AtraccionDAO getAtraccionDAO() {
		return new AtraccionDAO();
	}
	
	public static PromocionDAO getPromocionDAO() {
		return new PromocionDAO();
	}

	public static ItinerarioDAO getItinerarioDAO() {
		return new ItinerarioDAO();
	}

}
