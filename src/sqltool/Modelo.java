package sqltool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class Modelo extends AbstractTableModel {

	ArrayList titulo;
	ArrayList dados;
	ArrayList linha;
	int qtdeColuna;

	public Modelo(ResultSet result) {

		titulo = new ArrayList();
		dados = new ArrayList();
			   

		try {

			qtdeColuna = result.getMetaData().getColumnCount();

			for (int i = 1; i <= qtdeColuna; i++) {
				titulo.add(result.getMetaData().getColumnLabel(i));
				
			}
			
			while (result.next()) {
				
				linha = new ArrayList();
				for (int i = 1; i <= qtdeColuna; i++) {
					
					linha.add(result.getString(i));
					
				}
				dados.add(linha);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getColumnCount() {

		return titulo.size();
	}

	public int getRowCount() {

		return dados.size();

	}

	public Object getValueAt(int arg0, int arg1) {
		
		ArrayList help = (ArrayList) dados.get(arg0);
		return help.get(arg1);

	}

	public String getColumnName(int c) {

		return (String) titulo.get(c);

	}

}