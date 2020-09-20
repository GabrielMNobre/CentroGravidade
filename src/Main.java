import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
	
	public static void main(String args[]) throws Exception {

		FileReader entrada = new FileReader("matrizEntrada.txt");
		BufferedReader ler = new BufferedReader(entrada);
		int linha = 0;
		int coluna = 0;
		boolean validaValores;
		String linhaTxt;
		String dado = "";

		String LinhaColunaTxt = ler.readLine();
		String[] LinhaColuna = LinhaColunaTxt.split(" ");
		
		for (int i = 0; i < LinhaColuna.length; i++) {
			System.out.println("["+i+"] => "+LinhaColuna[i]);
		}

		linha = Integer.parseInt(LinhaColuna[0]);
		coluna = Integer.parseInt(LinhaColuna[1]);
		validaValores = confereValores(linha, coluna);
		
		if (validaValores) { 
			double[][] dadosTratados = new double[linha][coluna];
			double[] somaLinhas = new double[linha];
			double[] somaColunas = new double[coluna];
			//double[] somaColunas = new double[coluna];
			//comeca o código do cálculo do centro de gravidade da matriz 
			System.out.println("A matriz inserida possui tamanho de => "+linha +"x"+coluna);
			//ler as linhas da matriz
			while((linhaTxt = ler.readLine()) != null) {
				dado += linhaTxt + " ";
			}
			ler.close();
			String[] dados  = dado.split(" ");
			insereMatriz(dadosTratados, dados);
			imprimeMatriz(dadosTratados);
			
			somaLinhas = somaLinha(dadosTratados, somaLinhas);
			System.out.println("\n Soma das linhas");
			imprimeVetor(somaLinhas);
			
			somaColunas = somaColuna(dadosTratados, somaColunas);
			System.out.println("\n Soma das colunas");
			imprimeVetor(somaColunas);
			
		} else {
			System.out.println("O centro desta matriz não pode ser calculado! "
							 + "\nA matriz precisa ter no mínimo as dimensões 3x3"
							 + "\nInsira outra matriz com tamanho válido!"); 
		}

	}
	
	public static boolean confereValores(int linha, int coluna) {
		return (linha >= 3 && coluna >= 3);
	}
	
	public static void insereMatriz(double[][] matriz, String[] vetor) {
		
		int posicao = 0;
		
		for(int i = 0; i < matriz.length; i++) {
			for(int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = Double.parseDouble(vetor[posicao]);
				posicao++;
			}
		}
		
	}
	
	public static double[] somaLinha(double[][] matriz, double[] linhas) {
		
		int posicao = 0;
		double somaLinha = 0;
		
		for(int i = 0; i < matriz.length; i++) {
			for(int j = 0; j < matriz[0].length; j++) {
				somaLinha += matriz[i][j];
			}
			linhas[posicao] = Math.round(somaLinha * 10);
			posicao++;
			somaLinha = 0;
		}
		return linhas;
	}
	
	public static double[] somaColuna(double[][] matriz, double[] colunas) {
		
		int posicao = 0;
		double somaColuna = 0;
		
		for(int i = 0; i < matriz.length; i++) {
			for(int j = 0; j < matriz[0].length; j++) {
				somaColuna += matriz[j][i];
			}
			colunas[posicao] = Math.round(somaColuna * 10);
			posicao++;
			somaColuna = 0;
		}
		return colunas;
	}
	
	public static void imprimeMatriz(double[][] matriz) {
		for(int i = 0; i < matriz.length; i++) {
			System.out.println("\n");
			for(int j = 0; j < matriz[0].length; j++) {
				System.out.print("["+i+"]["+j+"] => " + matriz[i][j]+"\t");
			}
		}
	}
	
	public static void imprimeVetor(double[] vetor) {
		for(int i = 0; i < vetor.length; i++) {
			System.out.println("["+i+"] => " + vetor[i]);
		}
	}

}
