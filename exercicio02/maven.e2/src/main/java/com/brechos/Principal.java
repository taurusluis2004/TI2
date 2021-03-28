package com.brechos;

import java.io.*;
import java.nio.charset.*;

public class Principal {
	
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
	   private static String charset = "ISO-8859-1";

	   public static void setCharset(String charset_){
	      charset = charset_;
	      in = new BufferedReader(new InputStreamReader(System.in, Charset.forName(charset)));
	   }
	
	public static String readString(){
	      String s = "";
	      char tmp;
	      try{
	         do{
	            tmp = (char)in.read();
	            if(tmp != '\n' && tmp != ' ' && tmp != 13){
	               s += tmp;
	            }
	         }while(tmp != '\n' && tmp != ' ');
	      }catch(IOException ioe){
	         System.out.println("lerPalavra: " + ioe.getMessage());
	      }
	      return s;
	   }
	
	public static int readInt(){
	      int i = -1;
	      try{
	         i = Integer.parseInt(readString().trim());
	      }catch(Exception e){}
	      return i;
	   }
	
	public static String readLine(){
	      String s = "";
	      char tmp;
	      try{
	         do{
	            tmp = (char)in.read();
	            if(tmp != '\n' && tmp != 13){
	               s += tmp;
	            }
	         }while(tmp != '\n');
	      }catch(IOException ioe){
	         System.out.println("lerPalavra: " + ioe.getMessage());
	      }
	      return s;
	   }
	
	public static void Listar(DAO dao) {
		//Mostrar brechos
		Brecho[] brechos = dao.getBrechos();
		System.out.println("==== Mostrar Brechós === ");		
		for(int i = 0; i < brechos.length; i++) {
			System.out.println(brechos[i].toString());
		}
	}
	
	public static void Inserir(DAO dao, int id, String nome, String instagram, String cidade, String estilo) {		
		//Inserir um elemento na tabela
		System.out.print("Id: ");
		id = readInt();
		System.out.print("Nome: ");
		nome = readLine();
		System.out.print("Instagram: ");
		instagram = readLine();
		System.out.print("Cidade: ");
		cidade = readLine();
		System.out.print("Estilo: ");
		estilo = readLine();
		Brecho brecho = new Brecho(id, nome, instagram, cidade, estilo);
		if(dao.inserirBrecho(brecho) == true) {
			System.out.println("InserÃ§Ã£o com sucesso -> " + brecho.toString());
		}
	}
	
	public static void Excluir(DAO dao, int id) {
		System.out.print("Id: ");
		id = readInt();
		//Excluir brecho
		dao.excluirBrecho(id);
	}
	
	public static void Atualizar(DAO dao, int id, String nome, String instagram, String cidade, String estilo) {
		//Atualizar usuÃ¡rio
		System.out.print("Id: ");
		id = readInt();
		System.out.print("Nome: ");
		nome = readLine();
		System.out.print("Instagram: ");
		instagram = readLine();
		System.out.print("Cidade: ");
		cidade = readLine();
		System.out.print("Estilo: ");
		estilo = readLine();
		Brecho brecho = new Brecho(id, nome, instagram, cidade, estilo);
		
		dao.atualizarBrecho(brecho);	
	
	}
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		dao.conectar();
		
		int opcao = 0;
		boolean op = true;
		
		String nome = "", instagram = "", cidade = "", estilo = ""; 
		int id = 0;
		
		while(op) {
			System.out.println("Menu");
			System.out.println("1 - Listar");
			System.out.println("2 - Inserir");
			System.out.println("3 - Excluir");
			System.out.println("4 - Atualizar");
			System.out.println("5 - Sair");
			System.out.println("Selecione uma opção: ");
			opcao = readInt();
			
			switch(opcao) {
			case 1:
				Listar(dao);
				break;
			case 2:
				Inserir(dao, id, nome, instagram, cidade, estilo);
				break;
			case 3:
				Excluir(dao, id);
				break;
			case 4:
				Atualizar(dao, id, nome, instagram, cidade, estilo);
				break;
			case 5: 
				op = false;
				break;
			default:
				System.out.println("Opção Inválida.");
			
			}
			
		}
		
		dao.close();
	}
}
