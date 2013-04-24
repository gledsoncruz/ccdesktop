package com.visao.pedagogia.professor;

import javax.swing.table.AbstractTableModel;

import com.modelo.entidade.Professor;
import com.util.Util;

public class ProfessorTableModel extends AbstractTableModel {

	Object[] professores;

	public ProfessorTableModel(Object[] professores) {
		super();
		this.professores = professores;
	}

	public int getColumnCount() {

		return 3;
	}

	public int getRowCount() {

		return professores.length;
	}

	public Object getValueAt(int objeto, int atributo) {

		Professor c = (Professor) professores[objeto];

		if (atributo == 0) {
			return c.getNome();
		} else if (atributo == 1) {
			return c.getEmail();
		} else {
			return Util.formataDataDefault(c.getNascimento());
		}
	}

	public String getColumnName(int coluna) {

		if (coluna == 0) {
			return "Nome";
		} else if (coluna == 1) {
			return "Email";
		}else {
			return "Nascimento";
		}
	}
}
