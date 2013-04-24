package com.visao;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FormBemVindo extends Painel {

	public FormBemVindo() {

		this.setBackground(Color.WHITE);
		Font big = new Font("Sans Serif", Font.BOLD, 50);
		JLabel mensagem = new JLabel("CURSOS DE EXTENSÃO");
		mensagem.setFont(big);
		JLabel imagem = new JLabel(new ImageIcon(this.getClass().getResource(
				"/imagem/unifoa.gif")));
		this.add(mensagem);
		this.add(imagem);

	}

	@Override
	public void manterEstado() {
		// TODO Auto-generated method stub
		
	}

}
