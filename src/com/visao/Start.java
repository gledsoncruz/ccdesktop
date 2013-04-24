package com.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import com.visao.doscencia.frequencia.FormFrequencia;
import com.visao.doscencia.nota.FormNota;
import com.visao.pedagogia.curso.FormCurso;
import com.visao.pedagogia.professor.FormProfessor;
import com.visao.pedagogia.turma.FormTurma;
import com.visao.secretaria.aluno.FormAluno;
import com.visao.secretaria.certificado.FormCertificado;
import com.visao.secretaria.matricula.FormMatricula;

/**
 * @author Angelo Classe que inicia o sistema. Cont�m menu e barra de ferramenta
 *         para realizar chamadas as formul�rios
 * 
 */
public class Start extends JFrame {

	/*************************************
	 ******DECLARA��O DAS VARI�VEIS*******
	 *************************************/

	// Declarando a barra de Menu
	JMenuBar barraDeMenu;

	// Declarando o Menu
	JMenu menuArquivo, menuPedagogia, menuDoscencia, menuSecretaria;

	// Declarando os itens do menu Arquivo
	JMenuItem sair;

	// Declarando os itens do menu Pedagogia
	JMenuItem manterCurso, manterTurma, manterProfessor;

	// Declarando os itens do menu Doscencia
	JMenuItem lancarFrequencia, lancarNota;

	// Declarando os itens do menu Secretaria
	JMenuItem manterAluno, efetuarMatricula, gerarCertificado;
	
	//Declarando a barra de ferramentes
	JToolBar barraFerramentas;
	
	//Declarando os bot�es da barra de ferramenta (somente os mais usados)
	JButton buttonAluno, buttonMatricula, buttonTurma, buttonLancarFrequencia, buttonLancarNota, buttonSair;
	
		// Declarando o Container que receber� os formul�rios do sistema
	Container c;

	// Declarando o formul�rio corrente
	Painel formCorrente;

	public Start() {

		/*************************************
		 ******CRIA��O DAS VARI�VEIS**********
		 *************************************/

		// Criando o container para receber os formul�rios
		c = this.getContentPane();

		// criando a barra de menu
		barraDeMenu = new JMenuBar();

		// criando o menu arquivo
		menuArquivo = new JMenu("Arquivo");
		// Ativando a tecla - ALT A
		menuArquivo.setMnemonic(KeyEvent.VK_A);

		// criando o menu Pedagogia
		menuPedagogia = new JMenu("Pedagogia");
		// Ativando a tecla - ALT P
		menuPedagogia.setMnemonic(KeyEvent.VK_P);

		// criando o menu Secretaria
		menuSecretaria = new JMenu("Secretaria");
		// Ativando a tecla - ALT S
		menuSecretaria.setMnemonic(KeyEvent.VK_S);

		// criando o menu Doscencia
		menuDoscencia = new JMenu("Doscencia");
		// Ativando a tecla - ALT D
		menuDoscencia.setMnemonic(KeyEvent.VK_D);

		// Cria��o dos itens dos menus Arquivo, Pedagogia, Secretaria e
		// Doscencia
		/*********************************************************************
		 * OBSERVA��O*********************************************************
		 * this.getClass().getResource("/imagem/t16x16/exit.png"))************
		 * trata-se de um localizador de recursos de excelente funcionamento**
		 * Uso aconselh�vel, por�m n�o obrigat�rio****************************
		 **************/

		/**************************
		 ***********SAIR***********
		 **************************/

		// Criando o item SAIR do menu arquivo
		sair = new JMenuItem("Sair", new ImageIcon(this.getClass().getResource(
				"/imagem/t16x16/exit.png")));
		// Ativando a tecla de atalho ALT X
		sair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.ALT_MASK));
		// Ativando o listener (Ouvinte) para o Inner class (Classe interna)
		// SairHAndler
		sair.addActionListener(new SairHandler());

		/**************************
		 *******MANTER CURSO*******
		 **************************/

		// Criando o item criar curso do menu Pedagogia
		manterCurso = new JMenuItem("Curso", new ImageIcon(this.getClass()
				.getResource("/imagem/t16x16/curso.png")));
		// Ativando a tecla de atalho ALT C
		manterCurso.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.ALT_MASK));
		// Ativando o listener (Ouvinte) para o Inner class (Classe interna)
		// VisualizadorFormulario passando o formulario que dever� aparecer como
		// formul�rio corrente
		manterCurso.addActionListener(new VisualizadorFormularioHandler(
				new FormCurso()));

		/**************************
		 ****MANTER PROFESSOR******
		 **************************/

		// Criando o item criar Professor do menu Pedagogia
		manterProfessor = new JMenuItem("Professor", new ImageIcon(this
				.getClass().getResource("/imagem/t16x16/professor.png")));
		// Ativando a tecla de atalho ALT P
		manterProfessor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.ALT_MASK));
		// Ativando o listener (Ouvinte) para o Inner class (Classe interna)
		// ShowPanelHandler passando o formulario que dever� aparecer como
		// formul�rio corrente
		manterProfessor.addActionListener(new VisualizadorFormularioHandler(
				new FormProfessor()));

		/**************************
		 ******MANTER TURMA********
		 **************************/

		// Criando o item criar Turma do menu Pedagogia
		manterTurma = new JMenuItem("Turma", new ImageIcon(this.getClass()
				.getResource("/imagem/t16x16/contents.png")));
		// Ativando a tecla de atalho ALT T
		manterTurma.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
				ActionEvent.ALT_MASK));
		// Ativando o listener (Ouvinte) para o Inner class (Classe interna)
		// ShowPanelHandler passando o formulario que dever� aparecer como
		// formul�rio corrente
		manterTurma.addActionListener(new VisualizadorFormularioHandler(
				new FormTurma()));

		/**************************
		 *******LAN�AR NOTA********
		 **************************/

		// Criando o item Lancar Nota do menu Doscencia
		lancarNota = new JMenuItem("Nota", new ImageIcon(this.getClass()
				.getResource("/imagem/t16x16/nota.png")));
		// Ativando a tecla de atalho ALT N
		lancarNota.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.ALT_MASK));
		// Ativando o listener (Ouvinte) para o Inner class (Classe interna)
		// ShowPanelHandler passando o formulario que dever� aparecer como
		// formul�rio corrente
		lancarNota.addActionListener(new VisualizadorFormularioHandler(
				new FormNota()));

		/**************************
		 ****LAN�AR FREQUENCIA*****
		 **************************/

		// Criando o item Lancar Frequencia do menu Doscencia
		lancarFrequencia = new JMenuItem("Frequencia", new ImageIcon(this
				.getClass().getResource("/imagem/t16x16/frequencia.png")));
		// Ativando a tecla de atalho ALT F
		lancarFrequencia.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				ActionEvent.ALT_MASK));
		// Ativando o listener (Ouvinte) para o Inner class (Classe interna)
		// ShowPanelHandler passando o formulario que dever� aparecer como
		// formul�rio corrente
		lancarFrequencia.addActionListener(new VisualizadorFormularioHandler(
				new FormFrequencia()));

		/**************************
		 ****MANTER PROFESSOR******
		 **************************/
		// Criando o item Manter Aluno do menu Secretaria
		manterAluno = new JMenuItem("Aluno", new ImageIcon(this.getClass()
				.getResource("/imagem/t16x16/aluno.png")));
		// Ativando a tecla de atalho ALT L (Al)
		manterAluno.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				ActionEvent.ALT_MASK));
		// Ativando o listener (Ouvinte) para o Inner class (Classe interna)
		// ShowPanelHandler passando o formulario que dever� aparecer como
		// formul�rio corrente
		manterAluno.addActionListener(new VisualizadorFormularioHandler(
				new FormAluno()));

		/**************************
		 ****EFETUAR MATRICULA*****
		 **************************/

		// Criando o item Efetuar matricula do menu Secretaria
		efetuarMatricula = new JMenuItem("Matricula", new ImageIcon(this
				.getClass().getResource("/imagem/t16x16/attach.png")));
		// Ativando a tecla de atalho ALT M
		efetuarMatricula.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
				ActionEvent.ALT_MASK));
		// Ativando o listener (Ouvinte) para o Inner class (Classe interna)
		// ShowPanelHandler passando o formulario que dever� aparecer como
		// formul�rio corrente
		efetuarMatricula.addActionListener(new VisualizadorFormularioHandler(
				new FormMatricula()));

		/**************************
		 ****GERAR CERTIFICADO*****
		 **************************/

		// Criando o item gerar certificado do menu Secretaria
		gerarCertificado = new JMenuItem("Certificado", new ImageIcon(this
				.getClass().getResource("/imagem/t16x16/certificado.png")));
		// Ativando a tecla de atalho ALT C
		gerarCertificado.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.ALT_MASK));
		// Ativando o listener (Ouvinte) para o Inner class (Classe interna)
		// ShowPanelHandler passando o formulario que dever� aparecer como
		// formul�rio corrente
		gerarCertificado.addActionListener(new VisualizadorFormularioHandler(
				new FormCertificado()));

		// Adicionando os itens do menu ao menu arquivo
		menuArquivo.add(sair);

		// Adicionando os itens do menu Pedagogia
		menuPedagogia.add(manterCurso);
		menuPedagogia.add(manterProfessor);
		menuPedagogia.add(manterTurma);

		// Adicionando os itens do menu Secretaria
		menuSecretaria.add(efetuarMatricula);
		menuSecretaria.add(manterAluno);
		menuSecretaria.add(gerarCertificado);

		// Adicionando os itens do menu Doscencia
		menuDoscencia.add(lancarFrequencia);
		menuDoscencia.add(lancarNota);

		// Adicionando os menu na barra de de menu
		barraDeMenu.add(menuArquivo);
		barraDeMenu.add(menuPedagogia);
		barraDeMenu.add(menuSecretaria);
		barraDeMenu.add(menuDoscencia);

		// Adicionando na barra de menu na posi��o reservado pelo JFRAME para
		// funcionar o menu
		this.setJMenuBar(barraDeMenu);
		
		/********************************************************
		 * ***************Barra de Ferramentas*******************
		 ********************************************************/
		barraFerramentas = new JToolBar();
		barraFerramentas.setBackground(Color.WHITE);
		
		/********************************************************
		 * ****************Barra de Ferramenta aluno*************
		 ********************************************************/
		
		//Criando o bot�o para carregar o formul�rio para mantar aluno
		buttonAluno = new JButton(new ImageIcon(this.getClass().getResource("/imagem/t32x32/aluno.png")));
		//Aparecer� um label quando o mouse estiver posicionado em cima
		buttonAluno.setToolTipText("Aluno");
		/*Ativando o listener (Ouvinte) para o inner class (Classe interna)
		ShowPanelHandler passando o formulario que dever� aparecer como
		formul�rio corrente*/
		buttonAluno.addActionListener(new VisualizadorFormularioHandler(new FormAluno()));
		
		
		/********************************************************
		 * ****************Barra de Ferramenta matricula*************
		 ********************************************************/
		
		//Criando o bot�o para carregar o formul�rio para manter matricula
		buttonMatricula = new JButton(new ImageIcon(this.getClass().getResource("/imagem/t32x32/attach.png")));
		//Aparecer� um label quando o mouse estiver posicionado em cima
		buttonMatricula.setToolTipText("Matricula");
		/*Ativando o listener (Ouvinte) para o inner class (Classe interna)
		ShowPanelHandler passando o formulario que dever� aparecer como
		formul�rio corrente*/
		buttonMatricula.addActionListener(new VisualizadorFormularioHandler(new FormMatricula()));
		
		
		/********************************************************
		 * ****************Barra de Ferramenta turma*************
		 ********************************************************/
		
		//Criando o bot�o para carregar o formul�rio para mantar aluno
		buttonTurma = new JButton(new ImageIcon(this.getClass().getResource("/imagem/t32x32/contents.png")));
		//Aparecer� um label quando o mouse estiver posicionado em cima
		buttonTurma.setToolTipText("Turma");
		/*Ativando o listener (Ouvinte) para o inner class (Classe interna)
		ShowPanelHandler passando o formulario que dever� aparecer como
		formul�rio corrente*/
		buttonTurma.addActionListener(new VisualizadorFormularioHandler(new FormTurma()));
		
		/******************************************************
		 *******Barra de ferramentas lan�ar frequencia*********
		 *****************************************************/

		// criando o bot�o para carregar o formulario para lan�amento da
		// frequ�ncia
		buttonLancarFrequencia = new JButton(new ImageIcon(this.getClass()
				.getResource("/imagem/t32x32/frequencia.png")));
		// Aparecer� um label quando o mouse estiver posicionado em cima
		buttonLancarFrequencia.setToolTipText("Frequencia");
		// Ativando o listener (Ouvinte) para o Inner class (Classe interna)
		// ShowPanelHandler passando o formulario que dever� aparecer como
		// formul�rio corrente
		buttonLancarFrequencia
				.addActionListener(new VisualizadorFormularioHandler(
						new FormFrequencia()));

		/**************************************************
		 *******Barra de ferramentas lan�ar nota***********
		 *************************************************/

		// criando o bot�o para carregar o formulario para lan�amento da nota
		buttonLancarNota = new JButton(new ImageIcon(this.getClass()
				.getResource("/imagem/t32x32/nota.png")));
		// Aparecer� um label quando o mouse estiver posicionado em cima
		buttonLancarNota.setToolTipText("Nota");
		// Ativando o listener (Ouvinte) para o Inner class (Classe interna)
		// ShowPanelHandler passando o formulario que dever� aparecer como
		// formul�rio corrente
		buttonLancarNota.addActionListener(new VisualizadorFormularioHandler(
				new FormNota()));

		/********************************************
		 *******Barra de ferramentas Sair************
		 ********************************************/

		// criando o bot�o para carregar o formulario para sair do sistema
		buttonSair = new JButton(new ImageIcon(this.getClass().getResource(
				"/imagem/t32x32/exit.png")));
		// Aparecer� um label quando o mouse estiver posicionado em cima
		buttonSair.setToolTipText("Sair");
		// Ativando o listener (Ouvinte) para o Inner class (Classe interna)
		// SairHandler que providenciar� a saida do sistema
		buttonSair.addActionListener(new SairHandler());

		//adicionando os bot�es na barra de ferramentas
		barraFerramentas.add(buttonAluno);
		barraFerramentas.add(buttonTurma);
		barraFerramentas.add(buttonMatricula);
		barraFerramentas.add(buttonLancarFrequencia);
		barraFerramentas.add(buttonLancarNota);
		barraFerramentas.add(buttonSair);
		
		// formul�rio de aprewsenta��o
		formCorrente = new FormBemVindo();
		//Adicionando a barra de ferramentas ao container principal (JFrame)
		c.add(barraFerramentas, BorderLayout.NORTH);		
		// Adicionando o formul�rio corrente ao container principal (JFrame)
		c.add(formCorrente, BorderLayout.CENTER);

		/****************************************************
		 * UFAAAAAAAAAAAAAA acabou***************************
		 ****************************************************/
	}

	/*
	 * @author Angelo - Classe respons�vel em tratar (ouvinte) as a��es para
	 * trocar os formul�rios
	 */
	class VisualizadorFormularioHandler implements ActionListener {

		// declara��o da vari�vel que representa o formul�rio que dever aparecer
		// na tela
		Painel formDesejado;

		/**
		 * @param Painel
		 *            Formul�rio desejado
		 */

		public VisualizadorFormularioHandler(Painel p) {
			formDesejado = p;
		}

		public void actionPerformed(ActionEvent e) {

			// Desabilita a visualiza��o do componente
			c.setVisible(false);
			// remove o formul�rio corrente
			c.remove(formCorrente);
			// Adiciona o formul�rio desejado no centro do JFrame
			c.add(formDesejado, BorderLayout.CENTER);
			// Torna o formul�rio desejado como corrente
			formCorrente = formDesejado;
			// Habilita a visualiza��o do formul�rio
			c.setVisible(true);
			// dispara o m�todo manterEstado de uso gen�rico
			formCorrente.manterEstado();

		}

	}

	class SairHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			int confirma = JOptionPane.showConfirmDialog(null,
					"Deseja sair do sistema ?", "Confirma��o",
					JOptionPane.INFORMATION_MESSAGE);
			if (confirma == 0) {
				// Finaliza o programa
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) {

		// Kit de ferramentas de uso geral
		Toolkit tk = Toolkit.getDefaultToolkit();
		// Obtendo as dimens�es da tela
		Dimension screenSize = tk.getScreenSize();
		// Carregando a Tela principal
		Start s = new Start();
		// Determinando o tamanho de 800x600
		s.setSize(800, 600);
		// Centralinzando o frame no centro da tela
		s.setLocation((screenSize.width - 800) / 2,
				(screenSize.height - 600) / 2);
		// setando um t�tulo no canto superior esquerdo
		s.setTitle("Extens�o Unifoa");
		// Determina que todos objetos sejam varridos da mem�ria ao encerramento
		// do programa
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Torna o conte�do visible
		s.setVisible(true);

	}

}
