package podeApagar;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Exemplo1 extends JFrame {

	JPanel painel;
	JTable tabela;
	JScrollPane scroll;

	ArrayList<String> titulo;
	ArrayList<Professor> dados;

	public Exemplo1() {
		
		painel = new JPanel();
		titulo = new ArrayList<String>();
		titulo.add("Matricula");
		titulo.add("Nome");
		titulo.add("Email");
		titulo.add("Teste");

		Professor linha1 = new Professor();
		linha1.setMatricula(111111);
		linha1.setNome("Angelo Márcio de Paula");
		linha1.setEmail("angelo.paula@foa.org.br");
		linha1.setTeste("Unifoa--1");
		
		Professor linha2 = new Professor();
		linha2.setMatricula(22222);
		linha2.setNome("Priscila BBB");
		linha2.setEmail("pbbbb@foa.org.br");
		linha2.setTeste("Unifoa--2");
		

		Professor linha3 = new Professor();
		linha3.setMatricula(3333333);
		linha3.setNome("PÂmela de Paula");
		linha3.setEmail("pp@foa.org.br");
		linha3.setTeste("Unifoa--3");

		Professor linha4 = new Professor();
		linha4.setMatricula(44444444);
		linha4.setNome("Juliana Cruz");
		linha4.setEmail("jc@foa.org.br");
		linha4.setTeste("Unifoa--4");
		
		
		dados = new ArrayList<Professor>();
		dados.add(linha1);
		dados.add(linha2);
		dados.add(linha3);
		dados.add(linha4);
		
		TabelaModelo modelo = new TabelaModelo(dados, titulo);
		tabela = new JTable(modelo);
		scroll = new JScrollPane(tabela);
		painel.add(scroll);
		setContentPane(painel);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Exemplo1 teste = new Exemplo1();
		teste.setTitle("Exemplo");
		teste.setSize(500,300);
		teste.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		teste.setVisible(true);

	}

}
