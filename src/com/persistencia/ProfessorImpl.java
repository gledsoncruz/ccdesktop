package com.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

import com.modelo.entidade.Professor;
import com.util.GeradorId;
import com.util.Util;

public class ProfessorImpl extends Dao {

	//Variavel estática do tipo da própria classe
	private static ProfessorImpl professorImpl;
	
	//construtor privado exigido pelo Singleton
	private ProfessorImpl(){}
	
	//método que retorna a instância
	public static ProfessorImpl getInstance(){
		
		if (professorImpl==null){
			professorImpl = new ProfessorImpl();
		}
		return professorImpl;
	}
	
	
	
	/*
	 * Altera o objeto Professor no Banco de dados relacional
	 * @see com.persistencia.Dao#alterar(java.lang.Object)
	 */
	public void alterar(Object o) throws ClassNotFoundException, SQLException {
		
		//casting de Object para Professor
		Professor professor = (Professor) o;
		//declara a variável de conexao
		Connection c = null;
		//declara a variavel que prepara e executa o SQL
		PreparedStatement prepared = null;
		//Cria o SQL
		String sql = "update professor set nome=?, email=?, nascimento=? where id=?";
		try{
			//Pede uma conexão com o BD
			c = conectar();
			
			//Prepara o SQL para ser enviado aoBD
			prepared = c.prepareStatement(sql);
			//Associa os dados ao SQL
			prepared.setString(1, professor.getNome());
			prepared.setString(2, professor.getEmail());
			prepared.setDate(3, Util.dateUtilParaSQL(professor.getNascimento()));
			prepared.setInt(4, professor.getId());
			
			//executa de fato o SQL no BD
			prepared.executeUpdate();
			
		}finally{
			if (prepared!=null){
				prepared.close();
			}
			if(c!=null){
				c.close();
			}
		}		
	}

	/*
	 * Consultar um Professor no BD Relacional
	 * @see com.persistencia.Dao#consultar(int)
	 */
	public Object consultar(int id) throws ClassNotFoundException, SQLException {
		
		//Declarar uma variavel para ser o Professor
		Professor professor=null;
		//Declara a variavel para ser a conexao
		Connection c = null;
		//Declara a variavel que prepara e executa o SQL
		PreparedStatement prepared = null;
		//Declara a variavel que vai receber a consulta do BD
		ResultSet resultSet = null;
		//Criar o SQL
		String sql = "select * from professor where id=?";
		
		try{
			
			//conectar com BD
			c = conectar();
			//preparar o SQL para ser enviado ao BD
			prepared = c.prepareStatement(sql);
			//associar os dados ao SQL
			prepared.setInt(1, id);
			//executar de fato o SQL
			resultSet = prepared.executeQuery();
		
			//Aponta para o primeiro registro (se existir)
			if(resultSet.next()){
				//cria o objeto professor
				professor = new Professor();
				//associa o dados SQL ao professor
				professor.setId(resultSet.getInt("id"));
				professor.setNome(resultSet.getString("nome"));
				professor.setEmail(resultSet.getString("email"));
				professor.setNascimento(Util.dateSQLParaUtil(resultSet.getDate("nascimento")));
			}
			//retorna o OBJETO e nunca o resultSet (BAIXO ACOPLAMENTO)
			return professor;
					
		}
		finally{
			if(prepared!=null){
				prepared.close();	
			}
			if (resultSet!=null){
				resultSet.close();
			}
			if (c!=null){
				c.close();
			}			
		}
	}

	@Override
	public void excluir(Object o) throws ClassNotFoundException, SQLException {
		
		//casting de Object para Professor
		Professor professor = (Professor) o;
		//declara a variável de conexao
		Connection c = null;
		//declara a variavel que prepara e executa o SQL
		PreparedStatement prepared = null;
		//Cria o SQL
		String sql = "delete from professor where id = ?";
		
		try{
			//Pede uma conexão com o BD
			c = conectar();
			
			//Prepara o SQL para ser enviado aoBD
			prepared = c.prepareStatement(sql);
			//Associa os dados ao SQL
			
			prepared.setInt(1, professor.getId());
		
						
			//executa de fato o SQL no BD
			prepared.executeUpdate();
			
		}finally{
			if (prepared!=null){
				prepared.close();
			}
			if(c!=null){
				c.close();
			}
		}

	}

	/*
	 * Incluir o objeto Professor no BD relacional
	 * @see com.persistencia.Dao#incluir(java.lang.Object)
	 */
	public void incluir(Object o) throws ClassNotFoundException, SQLException {

		//casting de Object para Professor
		Professor professor = (Professor) o;
		//declara a variável de conexao
		Connection c = null;
		//declara a variavel que prepara e executa o SQL
		PreparedStatement prepared = null;
		//Cria o SQL
		String sql = "insert into professor(id, nome, email, nascimento) values (?,?,?,?)";
		//Declara e Cria a classe geradora de chave primária
		GeradorId gerador = new GeradorId();
		try{
			//Pede uma conexão com o BD
			c = conectar();
			
			//Prepara o SQL para ser enviado aoBD
			prepared = c.prepareStatement(sql);
			//Associa os dados ao SQL
			
			prepared.setInt(1, gerador.intValue());
			prepared.setString(2, professor.getNome());
			prepared.setString(3, professor.getEmail());
			prepared.setDate(4, Util.dateUtilParaSQL(professor.getNascimento()));
						
			//executa de fato o SQL no BD
			prepared.executeUpdate();
			
		}finally{
			if (prepared!=null){
				prepared.close();
			}
			if(c!=null){
				c.close();
			}
		}
	}

	@Override
	public Set<?> listar(String nome) throws ClassNotFoundException,
			SQLException {
		
		//Declara e cria a variável da coleção
		Set <Professor> professores = new TreeSet<Professor>();
		//Declarar uma variavel para ser o Professor
		Professor professor=null;
		//Declara a variavel para ser a conexao
		Connection c = null;
		//Declara a variavel que prepara e executa o SQL
		PreparedStatement prepared = null;
		//Declara a variavel que vai receber a consulta do BD
		ResultSet resultSet = null;
		//Criar o SQL
		String sql = "select * from professor where nome like ? order by nome";
		
		try{
			
			//conectar com BD
			c = conectar();
			//preparar o SQL para ser enviado ao BD
			prepared = c.prepareStatement(sql);
			//associar os dados ao SQL
			prepared.setString(1, nome + "%");
			//executar de fato o SQL
			resultSet = prepared.executeQuery();
		
			//Aponta para o primeiro registro (se existir)
			while(resultSet.next()){
				//cria o objeto professor
				professor = new Professor();
				//associa o dados SQL ao professor
				professor.setId(resultSet.getInt("id"));
				professor.setNome(resultSet.getString("nome"));
				professor.setEmail(resultSet.getString("email"));
				professor.setNascimento(Util.dateSQLParaUtil(resultSet.getDate("nascimento")));
				
				professores.add(professor);
			}
			//retorna a coleção e nunca o resultSet (BAIXO ACOPLAMENTO)
			return professores;
					
		}
		finally{
			if(prepared!=null){
				prepared.close();	
			}
			if (resultSet!=null){
				resultSet.close();
			}
			if (c!=null){
				c.close();
			}			
		}
	}

}
