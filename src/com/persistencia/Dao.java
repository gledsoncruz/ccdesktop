package com.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;

import com.exception.ClassNotImplemented;

public abstract class Dao {

	public static Dao fabrica(String objeto) {

		if (objeto == "PROFESSOR") {
			return ProfessorImpl.getInstance();
		} else if (objeto == "ALUNO") {
			// return AlunoImpl.getInstance();
		} else if (objeto == "CURSO") {
			// return CursoImpl.getInstance();
		} else {
			throw new ClassNotImplemented("Classe não implementada");
		}

		return null;

	}
	
	protected Connection conectar() throws ClassNotFoundException, SQLException{
		
		Connection connection = null;
		
		/*//Oracle
		String driver = "oracle.jdbc.driver.OracleDriver";
		String dbURL = "jdbc:oracle:thin:@labserver05:1521:ACAD";
		String usuario = "scott";
		String senha = "tiger";*/
		
		/*//MySQL
		String driver = "org.gjt.mm.mysql.Driver";
		String dbURL = "jdbc:mysql://localhost/exemplo?autoReconnet=true";
		String usuario = "";
		String senha = "";*/
		
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		String dbURL = "jdbc:derby:derby/CC_DB;create=true";
		String usuario = "";
		String senha = "";
		
		//registra o driver
		Class.forName(driver);
		//cria a conexao
		connection = DriverManager.getConnection(dbURL, usuario, senha);
		//retorna a conexao
		return connection;
		
	}
	

	public abstract void incluir(Object o) throws ClassNotFoundException,
			SQLException;

	public abstract void alterar(Object o) throws ClassNotFoundException,
			SQLException;

	public abstract void excluir(Object o) throws ClassNotFoundException,
			SQLException;

	public abstract Object consultar(int id) throws ClassNotFoundException,
			SQLException;

	public abstract Set<?> listar(String nome) throws ClassNotFoundException,
			SQLException;

}
