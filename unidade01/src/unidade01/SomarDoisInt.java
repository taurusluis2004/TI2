package unidade01;
import java.util.Scanner;

class SomarDoisInt {
	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String args[]) {
		//declarar variaveis
		int a, b, soma;
		
		//leitura
		System.out.println("digite um número: ");
		a = scan.nextInt();
		System.out.println("digite outro número: ");
		b = scan.nextInt();
		
		//soma
		soma = a + b;
		
		//mostrar resultado
		System.out.println("Soma = " + soma);
	}
}