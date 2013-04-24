package com.visao.pedagogia.professor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.exception.ObjetoInexistente;
import com.modelo.entidade.Professor;
import com.modelo.facade.CursoFacade;
import com.util.Util;
import com.util.Validador;
import com.visao.Painel;

public class FormProfessor extends Painel {

	// Variáveis
	JPanel cadastro, controle;
	JScrollPane pesquisa;
	JButton incluir, alterar, excluir, dual, pesquisar;
	JTextField nome, email, nascimento;

	JTable tabela;

	Set<Professor> professores;

	Professor professor;

	int index;

	public FormProfessor() {

		// Criando os painéis
		cadastro = new JPanel();
		pesquisa = new JScrollPane();
		controle = new JPanel();

		// Setando o layout manager
		this.setLayout(new GridLayout(2, 1));
		cadastro.setLayout(null);

		// setando as bordas
		cadastro.setBorder(BorderFactory
				.createTitledBorder("Cadastro de Professor"));
		controle.setBorder(BorderFactory.createEtchedBorder());
		pesquisa.setBorder(BorderFactory.createTitledBorder("Pesquisa"));

		// Criando os label´s
		JLabel nomeLabel = new JLabel("Nomeaaaa:");
		JLabel emailLabel = new JLabel("Email:");
		JLabel nascimentoLabel = new JLabel("Nascimento:");

		// Criando os campos de textos

		nome = new JTextField();
		email = new JTextField();
		nascimento = new JTextField();

		// criando os botões das ações
		pesquisar = new JButton(new ImageIcon(this.getClass().getResource(
				"/imagem/t16x16/filefind.png")));
		// Faz aparecer um label na passagem do mouse
		pesquisar.setToolTipText("Pesquisar nome");

		incluir = new JButton("Incluir", new ImageIcon(this.getClass()
				.getResource("/imagem/t16x16/filesave.png")));
		alterar = new JButton("Alterar", new ImageIcon(this.getClass()
				.getResource("/imagem/t16x16/edit.png")));
		excluir = new JButton("Excluir", new ImageIcon(this.getClass()
				.getResource("/imagem/t16x16/editdelete.png")));
		dual = new JButton("Limpar", new ImageIcon(this.getClass().getResource(
				"/imagem/t16x16/cancel.png")));

		// Desabilitando os botões
		alterar.setEnabled(false);
		excluir.setEnabled(false);

		// Posicionando os botões (COLUNA, LINHA, LARGURA, ALTURA)

		nomeLabel.setBounds(10, 30, 100, 20);
		emailLabel.setBounds(10, 60, 100, 20);
		nascimentoLabel.setBounds(10, 90, 100, 20);

		nome.setBounds(130, 30, 400, 20);
		email.setBounds(130, 60, 400, 20);
		nascimento.setBounds(130, 90, 90, 20);

		pesquisar.setBounds(530, 30, 20, 20);
		controle.setBounds(10, 200, 500, 40);

		// Preparando os listener

		// A classe (inner class) PesquisarHandler será o ouvinte do botão
		// pesquisar
		pesquisar.addActionListener(new PesquisarHandler());
		// A classe (inner class) IncluirHandler será o ouvinte do botão incluir
		incluir.addActionListener(new IncluirHandler());
		// A classe (inner class) AlterarHandler será o ouvinte do botão alterar
		alterar.addActionListener(new AlterarHandler());
		// A classe (inner class) ExcluirHandler será o ouvinte do botão excluir
		excluir.addActionListener(new ExcluirHandler());
		// A classe (inner class) DualHandler será o ouvinte do botão dual
		dual.addActionListener(new DualHandler());

		// Adicionar os componentes em cada Panel
		cadastro.add(nomeLabel);
		cadastro.add(emailLabel);
		cadastro.add(nascimentoLabel);
		cadastro.add(nome);
		cadastro.add(email);
		cadastro.add(nascimento);
		cadastro.add(pesquisar);

		controle.add(incluir);
		controle.add(alterar);
		controle.add(excluir);
		controle.add(dual);

		cadastro.add(controle);

		this.add(cadastro);
		this.add(pesquisa);

	}

	// Região dos métodos
	private boolean validator() {

		// pegando os dados digitados
		String n = nome.getText();
		String e = email.getText();
		String nasc = nascimento.getText();

		// Validação do nome
		if (n == null || n.equals("") || !Validador.checkNome(n)) {
			JOptionPane.showMessageDialog(this, "Nome obrigatório ou inválido",
					"Validação", JOptionPane.ERROR_MESSAGE);
			nome.requestFocus();
			return false;
		}
		// Validação do email
		if (e == null || e.equals("") || !Validador.checkMail(e)) {
			JOptionPane.showMessageDialog(this,
					"Email obrigatório ou inválido", "Validação",
					JOptionPane.ERROR_MESSAGE);
			email.requestFocus();
			return false;
		}
		
		
		// Validação do nascimento
		if (nasc == null || nasc.equals("") || !Validador.checkData(nasc)) {
			JOptionPane.showMessageDialog(this,
					"Nascimento obrigatório ou inválido", "Validação",
					JOptionPane.ERROR_MESSAGE);
			nascimento.requestFocus();
			return false;
		}
		return true;
	}

	/**
	 * Limpa os campos da tela
	 */
	private void limpar() {
		nome.setText("");
		email.setText("");
		nascimento.setText("");
		nome.requestFocus();
	}

	/*
	 * classe ouvinte do botão dual
	 */

	class DualHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			// Verificando o caption
			if (ae.getActionCommand().equals("Limpar")) {
				limpar();
			} else {
				limpar();
				incluir.setEnabled(true);
				alterar.setEnabled(false);
				excluir.setEnabled(false);
				dual.setText("Limpar");
			}
		}

	}

	/*
	 * classe ouvinte do botão excluir
	 */
	class ExcluirHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			//Carregar uma instância do Facade
			CursoFacade facade = CursoFacade.getInstance();
			
			//Solicita uma confirmação
			
			int confirma = JOptionPane.showConfirmDialog(FormProfessor.this, "Deseja excluir o registro ?");
			if (confirma==0){
				
				
				try {
				
					//manda o facade exluir um professor pelo ID
					facade.excluirProfessor(professor.getId());
					//limpando a tela
					limpar();
					
					//habilitar e desabilitar os botões
					incluir.setEnabled(true);
					alterar.setEnabled(false);
					excluir.setEnabled(false);
					dual.setText("Limpar");
					
					//torna invisível os componentes da tela
					setVisible(false);
					
					//remove a tabela (pesquisa)
					remove(pesquisa);
					
					//O professor removido do banco de dados também é removido
					//da coleção ativa no formulário corrente
					professores.remove(professor);
					
					//cria um novo modelo de tabela sem o professor excluído
					ProfessorTableModel ctm = new ProfessorTableModel(professores.toArray());
					
					//Cria a nova tabela sem o professort excluido
					tabela = new JTable(ctm);
					
					//Determina o inner class ouvinte - TabelaHandler
					tabela.addMouseListener(new TabelaHandler());
					
					//permite seleção simples na tabela
					tabela.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					
					//Recria um painel com Scroll e borda
					pesquisa = new JScrollPane(tabela);
					pesquisa.setBorder(BorderFactory.createTitledBorder("Pesquisa"));
					
					//Adcionar o painel (que contem a tabela) na tela
					add(pesquisa);
					
					//Torna visível os componentes
					setVisible(true);
					
					
				} catch (ClassNotFoundException erro) {
					//Mensagem de erro para o usuário
					JOptionPane.showMessageDialog(FormProfessor.this, erro
							.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException erro) {
					//Mensagem de erro para o usuário
					JOptionPane.showMessageDialog(FormProfessor.this, erro
							.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
				} catch (ObjetoInexistente erro) {
					//Mensagem de erro para o usuário
					JOptionPane.showMessageDialog(FormProfessor.this, erro
							.getMensagem(), "ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}

		}

	}

	/*
	 * Classe ouvinte do botão Pesquisar (lupa de pesquisa)
	 */
	class PesquisarHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// Pega uma instância do Facade
			CursoFacade facade = CursoFacade.getInstance();
			
			try {
				//Solicitar uma pesquisa de professores pelo nome ou parte do nome
				professores = (Set<Professor>) facade.listarProfessor(nome.getText());
				
				//torna invisível os componentes da tela
				setVisible(false);
				
				//remove a tabela de pesquisa
				remove(pesquisa);
				
				//cria o modelo da tabela
				ProfessorTableModel atm = new  ProfessorTableModel(professores.toArray());
				
				//cria uma nova tabela com o seu modelo particular
				tabela = new JTable(atm);
				
				//criar um evento para ov click do mouse
				tabela.addMouseListener(new TabelaHandler());
				
				//configurando seleção simples de linha
				tabela.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
				//recriando o painel de Scrool
				pesquisa = new JScrollPane(tabela);
				
				//recolocando a borda de enfeite
				pesquisa.setBorder(BorderFactory.createTitledBorder("Pesquisa"));
				
				//adciona no painel a nova tabela
				add(pesquisa);
				
				//Torna todos os componentes visíveis
				setVisible(true);
				
			} catch (ClassNotFoundException erro) {
				//Mensagem de erro para o usuário
				JOptionPane.showMessageDialog(FormProfessor.this, erro
						.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
	
			} catch (SQLException erro) {
				//Mensagem de erro para o usuário
				JOptionPane.showMessageDialog(FormProfessor.this, erro
						.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
	
			}

		}

	}

	/**
	 * @author Angelo Classe ouvinte do botão incluir
	 */
	class IncluirHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (validator()) {
				// Pega os campos digitados
				String n = nome.getText();
				String e = email.getText();
				String nasc = nascimento.getText();
				// Pega uma instância do facade
				CursoFacade facade = CursoFacade.getInstance();
				// manda o facade incluir um professor
				try {
					facade.incluirProfessor(n, e, Util.criaData(nasc));
					// Mensagem de sucesso
					JOptionPane.showMessageDialog(FormProfessor.this,
							"Dados gravados com sucesso!", "Confirmação",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (ClassNotFoundException erro) {
					//Mensagem de erro para o usuário
					JOptionPane.showMessageDialog(FormProfessor.this, erro
							.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException erro) {
					//Mensagem de erro para o usuário
					JOptionPane.showMessageDialog(FormProfessor.this, erro
							.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
				}

			}
		}

	}

	/**
	 * Classe ouvinte do botão (button) Alterar Profesor
	 */
	class AlterarHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			// Pegar uma instancia do controlador (Facade)
			CursoFacade facade = CursoFacade.getInstance();
			// pegar as variaveis dos campos textos
			String n = nome.getText();
			String e = email.getText();
			String nasc = nascimento.getText();

		}

	}

	/**
	 * Listener (ouvinte) da acao clicar na tabela de dados
	 * 
	 */
	class TabelaHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			//cada linha do JTable equivale a um índice (0,1,2...)
			//descobrir o indice da linha clicada
			index = tabela.getSelectedRow();
			
			//criar um array temporário, isso é necessário porque o Set NAO é
			//possível retirar objetos usando um index. Lembre-se, isso só é 
			//possível através de um List
			Object[] temp = professores.toArray();
			
			//Agora sim podemos acessar um objeto, pois trata-se de um simples array
			
			professor = (Professor) temp[index];
			
			//carragar os campos no formulário conforme a linha clicada
			//na tabela
			
			nome.setText(professor.getNome());
			email.setText(professor.getEmail());
			nascimento.setText(Util.formataDataDefault(professor.getNascimento()));
			
			//Habiltar / desabilitar os botões na tela
			incluir.setEnabled(false);
			alterar.setEnabled(true);
			excluir.setEnabled(true);
			dual.setText("Cancelar");
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	@Override
	public void manterEstado() {
		// setar o botao incluir como botao principal
		getRootPane().setDefaultButton(incluir);
		// setar o focus no campo nome
		nome.requestFocus();

	}

}
