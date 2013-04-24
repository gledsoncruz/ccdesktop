package com.util;

import java.util.Date;
import java.text.DateFormat;
import java.util.Calendar;

public class Util {
	
	public static String formataDataDefault(java.util.Date data){
		
		if (data!=null){
			DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT);
			return df.format(data);
		}else{
			return "";
		}
		
	}
	
	public static String ajustaString(String s){
		if (s==null){
			s="";
		}
		return s;
	}
	public static java.sql.Date dateUtilParaSQL(java.util.Date dataUtil){
		java.sql.Date dataSQL = new java.sql.Date(dataUtil.getTime());
		return dataSQL;
	}

	public static java.util.Date dateSQLParaUtil(java.sql.Date dataSQL){
		java.util.Date dataUtil = new Date(dataSQL.getTime());
		return dataUtil;
	}
	public static Date criaData(String data){
		
		Calendar calendar = Calendar.getInstance();
		String dia = data.substring(0, 2);
		String mes = data.substring(3, 5);
		String ano = data.substring(6,10);
				
		calendar.set(Integer.parseInt(ano), (Integer.parseInt(mes)-1), Integer.parseInt(dia));
		
		return calendar.getTime();
	}
	
	static public String preencheZero(int i, int tam) {
		String s = String.valueOf(i);
		String sComZero = s;
		String zeros = "00000000000000000000";
		if ((s != null) && (!s.trim().equals("")) && (s.length() < tam)) {
			sComZero = zeros.substring(0, tam - s.length()) + s;
		}
		return sComZero;
	}

	static public String preencheZero(float i, int tam) {
		String s = String.valueOf(i);
		String zeros = "00000000000000000000";
		String sComZero = s;
		if ((s != null) && (!s.trim().equals("")) && (s.length() < tam)) {
			sComZero = zeros.substring(0, tam - s.length()) + s;
		}
		return sComZero;
	}

	static public String preencheZero(String s, int tam) {
		String zeros = "00000000000000000000";
		String sComZero = s;
		if ((s != null) && (!s.trim().equals("")) && (s.length() < tam)) {
			sComZero = zeros.substring(0, tam - s.length()) + s;
		}
		return sComZero;
	}
}
