package br.ufc.imc.net.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;

import br.ufc.imc.calculadora.CalculadoraIMC;
import br.ufc.redes.imc.modelo.Pessoa;
import br.ufc.redes.imc.modelo.Resultado;

public class ServidorIMCListener implements Runnable{

	private ObjectInputStream entrada;
	private PrintStream saida;
	
	public ServidorIMCListener(ObjectInputStream ois, PrintStream ps) {
		this.entrada = ois;
		this.saida = ps;
	}

	@Override
	public void run() {
		
		try {
			
			Pessoa pessoa;
			while((pessoa = (Pessoa) entrada.readObject()) != null){
				Resultado resultado = CalculadoraIMC.obterIMC(pessoa.getPeso(), pessoa.getAltura());
				saida.println("O SEU IMC: " + String.format("%.2f", resultado.getValorIMC()) + " - VOCÊ POSSUI/ESTÁ: " + resultado.getSituacao());
				saida.println("\nDIGITE O SEU PESO (Ex: 70) E EM SEGUIDA A SUA ALTURA (Ex: 1,78)");
			}
			
			saida.close();
		} catch (Exception e) {}
		
	}

}
