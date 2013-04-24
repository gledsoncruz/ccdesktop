package com.util;

public class Validador {

	public static boolean checkNome(String s) {
		if (s==null || s.equals("")){
			return false;
		}		
		char a;
		for (int i = 0; i < s.length(); i++) {
			a = s.charAt(i);
			if (!Character.isLetter(a) && !String.valueOf(a).equals(" ")) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkMail(String email) {
		
		if (email==null || email.equals("")){
			return false;
		}	
		
		boolean find_ponto = false;
		boolean find_arroba = false;
		boolean find_caracter = true;
		boolean find_colon = true;
		boolean find_ponto_seguido = true;
		int x = 0;
		boolean find_branco = true;
		char[] denied_char =
			{
				' ',
				'!',
				'#',
				'$',
				'%',
				'¨',
				'&',
				'*',
				'(',
				')',
				'|',
				'\\',
				'/',
				'?',
				':',
				';',
				'+',
				'=',
				'{',
				'}',
				'[',
				']' };

		//Teste de campo em branco
		if (email.length() < 1)
			find_branco = false;

		// Teste de presença de caracteres
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@') {
				x++;
				find_arroba = true;
			} else if (email.charAt(i) == '.') {
				find_ponto = true; // Verifica a presença de ponto
				if ((i > 0) && (email.charAt(i - 1) == '.')) {
					find_ponto_seguido = false;
				}
			} else if (email.charAt(i) == ',') {
				find_colon = false; // Verifica a presença de false
			}
			for (int j = 0; j < denied_char.length; j++) {
				if (email.charAt(i) == denied_char[j])
					find_caracter = false; //Teste de caracteres proibido
			}

		}
		if ((find_branco) && (email.charAt(0) == '@')) {
			find_arroba = false;
		}
		if (email.charAt(0) == '.') {
			find_ponto = false;
		}
		if (email.charAt(email.length() - 1) == '.') {
			find_ponto = false;
		}
		if (x > 1) {
			find_arroba = false;
		}
		return (
			find_ponto_seguido
				&& find_ponto
				&& find_arroba
				&& find_caracter
				&& find_colon
				&& find_branco);
	}

	public static boolean checkData(String data) {
		
		if (data==null || data.equals("")){
			return false;
		}
		if (data.length()<10){
			return false;
		}
		
		String dia = data.substring(0, 2);
		String mes = data.substring(3, 5);
		String ano = data.substring(6,10);
		
		int qtdDiasFev;
		String numeros = "0123456789";

		if ((dia == null)
			|| (dia.trim().equals(""))
			|| (mes == null)
			|| (mes.trim().equals(""))
			|| (ano == null)
			|| (ano.trim().equals(""))) {
			return false;
		} else if (ano.length() < 4) {
			return false;
		}

		String newdata =
			Util.preencheZero(dia.trim(), 2)
				+ Util.preencheZero(mes.trim(), 2)
				+ ano.trim();

		int s;
		for (int x = 0; x < 8; x++) {
			s = numeros.indexOf(newdata.substring(x, x + 1));
			if (s == -1) {
				return false;
			}
		}

		if (((Integer.parseInt(mes) == 1)
			|| (Integer.parseInt(mes) == 3)
			|| (Integer.parseInt(mes) == 5)
			|| (Integer.parseInt(mes) == 7)
			|| (Integer.parseInt(mes) == 8)
			|| (Integer.parseInt(mes) == 10)
			|| (Integer.parseInt(mes) == 12))
			&& (Integer.parseInt(dia) > 31)) {
			return false;
		} else if (
			((Integer.parseInt(mes) == 4)
				|| (Integer.parseInt(mes) == 6)
				|| (Integer.parseInt(mes) == 9)
				|| (Integer.parseInt(mes) == 11))
				&& (Integer.parseInt(dia) > 30)) {
			return false;
		} else {
			if ((Integer.parseInt(ano) % 4) == 0) {
				if ((Integer.parseInt(ano) % 100) == 0) {
					if ((Integer.parseInt(ano) % 400) == 0) {
						qtdDiasFev = 29;
					} else {
						qtdDiasFev = 28;
					}

				} else {
					qtdDiasFev = 29;
				}
			} else {
				qtdDiasFev = 28;
			}

			if ((Integer.parseInt(mes) == 2)
				&& (Integer.parseInt(dia.trim()) > qtdDiasFev)) {
				return false;
			} else if ((Integer.parseInt(mes) < 1) || (Integer.parseInt(mes) > 12)) {
				return false;
			} else if ((Integer.parseInt(dia.trim()) < 1)) {
				return false;
			}
		}
		return true;
	}
	

}
