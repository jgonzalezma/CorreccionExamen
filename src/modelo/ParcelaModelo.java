package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ParcelaModelo extends Conector{
	public ArrayList<Parcela> selectAll() {
		ArrayList<Parcela> parcelas = new ArrayList<Parcela>();
		try {
			Statement st = super.conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM parcelas");
			
			while(rs.next()){
				//crear parcela
				Parcela parcela = new Parcela();
				parcela.setId(rs.getInt("id"));
				parcela.setNumero(rs.getString("numero"));
				parcela.setM_cuadrados(rs.getInt("m_cuadrados"));
				parcela.setPrecio_dia(rs.getDouble("precio_dia"));
				
				//anadir a la lista
				parcelas.add(parcela);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parcelas;
		
	}

	public Parcela selectPorId(int idParcela) {
		try {
			PreparedStatement pst = super.conexion.prepareStatement("SELECT * FROM parcelar WHERE id = ?");
			pst.setInt(1, idParcela);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				Parcela parcela = new Parcela();
				parcela.setId(idParcela);
				parcela.setNumero(rs.getString("numero"));
				parcela.setM_cuadrados(rs.getInt("m_cuadrados"));
				parcela.setPrecio_dia(rs.getDouble("precio_dia"));
				
				return parcela;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
