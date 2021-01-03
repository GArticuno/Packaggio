package telas;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import dao.MochilasDao;
import mochilas.Mochilas;

public class Consulta extends JFrame {

	/**
	 * Esse construtor é responsável por 
	 * iniciar a matrix do JTable
	 * 
	 * 
	 * Vai rodar esse programa maravilhoso!
	 */
	private static final long serialVersionUID = 1153830580411552576L;

	public Consulta() throws SQLException {
		MochilasDao mochilasDao = new MochilasDao();
		
		String[] columns = new String[] {"Nome", "Modelo", "Tipo", "Valor","Quantidade","Pagamento","Data"};
		List<Mochilas> lista;
		Object[][] data = null;
		DefaultTableModel model = new DefaultTableModel(data,columns) {
			 /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
			    {
			      return false;
			    }
		};
		
		try {
			String op =JOptionPane.showInputDialog(null, 
					"Insira o nome :");
			lista = mochilasDao
					.consultaProducao(op);
			String m = (String) op;

			data = new Object[lista.size()][columns.length];
			for (Mochilas mochila : lista) {
				DecimalFormat tm= new DecimalFormat();
		        System.out.println("Nome: " + mochila.getNome());
				System.out.println("Modelo: " + mochila.getModelo());
		        System.out.println("Tipo: " + mochila.getTipo());
		        System.out.println("Valor: " + tm.format(mochila.getValor()));
		        System.out.println("Quantidade: " + mochila.getQuantidade());
		        System.out.println("Forma de Pagamento: " + mochila.getPagamento());
		        System.out.println("Data de venda: " + mochila.getData()+"\n");
		      
				int i=0;

				model.insertRow(i,new Object[]{mochila.getNome(), mochila.getModelo(), mochila.getTipo(), tm.format(mochila.getValor()), 
						mochila.getQuantidade(), mochila.getPagamento(), mochila.getData()});
				
				i++;
				
		    }
			
			JTable table = new JTable(model);
			this.add(new JScrollPane(table));
			this.setTitle("Consulta");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.pack();
			this.setVisible(true);
			
			
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new Consulta();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
