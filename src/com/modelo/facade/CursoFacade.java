package com.modelo.facade;

import java.sql.SQLException;
import java.util.Date;
import java.util.Set;

import com.exception.ObjetoInexistente;
import com.modelo.entidade.Professor;
import com.persistencia.Dao;

public class CursoFacade {

	//Implementacao do Singleton
	private static CursoFacade facade;
	
	private CursoFacade(){}
	
	public static CursoFacade getInstance() {
		if (facade==null){
			facade = new CursoFacade();
		}
		return facade;
	}
	
	/*
	 * Esse método inclui cria e inclui um professor no BD
	 */
	public void incluirProfessor(String nome, String email, Date nascimento) throws ClassNotFoundException, SQLException{
		//Solicita um ProfessorImpl
		Dao dao = Dao.fabrica("PROFESSOR");
		//Cria o professor na memória
		Professor professor = new Professor(nome, email,nascimento);
		//manda gravar o professorno BD
		dao.incluir(professor);
		
	}
	
	/*
	 * Esse método altera um professor no BD
	 */
	
	public void alterarProfessor(int id, String nome, String email, Date nascimento) throws ClassNotFoundException, SQLException, ObjetoInexistente{
		
		//Solicita um ProfessorImpl
		Dao dao = Dao.fabrica("PROFESSOR");
		//Consulta o professor no BD
		Professor professor = (Professor) dao.consultar(id);
		if (professor!=null){
			//Altera os campos
			professor.setNome(nome);
			professor.setEmail(email);
			professor.setNascimento(nascimento);
			
			//manda alterar o BD
			dao.alterar(professor);
		}else{
			throw new ObjetoInexistente("O professor foi deletado por outro usuário");
		}
							
	}
	/*
	 * Esse método exclui um professor do BD
	 */
	public void excluirProfessor(int id) throws ClassNotFoundException, SQLException, ObjetoInexistente{
	
		//Solicita um ProfessorImpl
		Dao dao = Dao.fabrica("PROFESSOR");
		//consultar o professor no BD
		Professor professor = (Professor) dao.consultar(id);
		//Verificar se o professor esta no BD
		if (professor!=null){
			//manda excluir
			dao.excluir(professor);
		}else{
			//dispara erro
			throw new ObjetoInexistente("O professor foi deletado por outro usuário");
		}
		
	}
	
	/*
	 * Esse método consulta o professor pelo ID
	 */
   	public Professor consultarProfessor(int id) throws ClassNotFoundException, SQLException{
   	
   		//solicita um ProfessorImpl
   		Dao dao = Dao.fabrica("PROFESSOR");
   		//Consulta o professor
   		Professor professor = (Professor) dao.consultar(id);
   		return professor;
   		
   	}
   	
   	/*
   	 * Esse método consulta os professores do BD
   	 */
	public Set<Professor> listarProfessor(String nome) throws ClassNotFoundException, SQLException{
		
		//solicita um ProfessorImpl
   		Dao dao = Dao.fabrica("PROFESSOR");
   		Set <Professor> professores = (Set<Professor>) dao.listar(nome);
   		return professores;
   	}
}
