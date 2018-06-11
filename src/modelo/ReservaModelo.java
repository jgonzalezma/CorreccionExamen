package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.Conector;
import modelo.Reserva;

public class ReservaModelo extends Conector{

	public void insert(Reserva reserva) {
		try {
			PreparedStatement pst = super.conexion.prepareStatement("INSERT INTO reservas(nombre_usuario, apellido_usuario, dni_usuario, numero_usuarios, inicio_reserva, fin_reserva, fecha_reserva, luz, id_parcela) values (?,?,?,?,?,?,?,?,?)");
			pst.setString(1, reserva.getNombre_usuario());
			pst.setString(2, reserva.getApellido_usuario());
			pst.setString(3, reserva.getDni_usuario());
			pst.setInt(4, reserva.getNumero_usuarios());
			pst.setDate(5, new java.sql.Date(reserva.getInicio_reserva().getTime()));
			pst.setDate(6, new java.sql.Date(reserva.getFin_reserva().getTime()));
			pst.setBoolean(7, reserva.getLuz());
			pst.setInt(8, reserva.getParcela().getId());
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
