package podeApagar;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TabelaModelo extends AbstractTableModel {

	List<Professor> dados;
	List<String> titulo;

	public TabelaModelo(List dados, List titulo){
		this.dados = dados;
		this.titulo = titulo;
	}
	
	@Override
	public int getColumnCount() {
		
		return titulo.size();
		
	}

	@Override
	public int getRowCount() {
		if (dados==null){
			return 0;
		}else{
			return dados.size();
		}		
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		
		Professor professor = (Professor) dados.get(arg0);
		
		if (arg1==0){
			return new Integer(professor.getMatricula());
		}
		else if (arg1==1){
			return professor.getNome();
		}else if (arg1==2){
			return professor.getEmail();
		}else{
			return professor.getTeste();
		}
		
	}
	
	public String getColumnName(int c){
		
		if (c==0){
			return "Matrícula";
			
		}else if (c==1){
			return "Nome";
		}else if (c==2) {
			return "Email";
		}
		else{
			return "teste";
		}
			
	}

}
