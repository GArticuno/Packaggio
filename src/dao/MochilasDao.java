package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import connection.ConnectionFactory;
import mochilas.Mochilas; //mude o producaoAutomovel para o nome da classe e o Producao

public class MochilasDao {
	ConnectionFactory connectionFactory = new ConnectionFactory();
	Connection con = connectionFactory.getConnection();
	
	public int insereProducao(Mochilas producao) throws SQLException {
		

        // cria um preparedStatement
        String sql = "insert into pedido" +
                " (nome_cliente, tipo , modelo , valor , quantidade, tipo_pagamento, data_venda)" +
                " values (?,?,?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setString(1, producao.getNome());
        stmt.setString(2, producao.getTipo());
        stmt.setString(3, producao.getModelo());
        stmt.setFloat(4, (float) producao.getValor());
        stmt.setInt(5, producao.getQuantidade());
        stmt.setString(6, producao.getPagamento());
        stmt.setDate(7, new java.sql.Date(
                Calendar.getInstance().getTimeInMillis()));
        // executa
        int qtdeRegistros = stmt.executeUpdate();
        stmt.close();
        
        connectionFactory.closeConnection();

        System.out.println("Confirmado");
        
		return qtdeRegistros;
	}
		
	public List<Mochilas> consultaProducao(String nome) throws SQLException {
		List<Mochilas> mochilas = new ArrayList<Mochilas>();
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM pedido " + "WHERE nome_cliente like '%" + nome + "%'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Mochilas m = new Mochilas();
				m.setNome(rs.getString("nome_cliente"));
				m.setTipo(rs.getString("tipo"));
				m.setModelo(rs.getString("modelo"));
			    m.setValor(rs.getFloat("valor"));
			    m.setQuantidade(rs.getInt("quantidade"));
			    m.setPagamento(rs.getString("tipo_pagamento"));
			    m.setData(rs.getString("data_venda"));
			 mochilas.add(m);
			}
		} finally {
			connectionFactory.closeConnection();
			
		}
		return mochilas;
	}


}