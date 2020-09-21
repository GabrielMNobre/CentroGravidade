import java.io.BufferedReader;
import java.io.FileReader;

/*Entrega de trabalho

N�s, 

Ayrton Pereira Fernandes

Bruna Cruz Nogueira

Gabriel Marcelino Nobre

declaramos que todas as respostas s�o fruto de nosso pr�prio trabalho,
n�o copiamos respostas de colegas externos � equipe,
n�o disponibilizamos nossas respostas para colegas externos � equipe e
n�o realizamos quaisquer outras atividades desonestas para nos beneficiar ou prejudicar outros.*/

public class Main {
	
	public static void main(String args[]) throws Exception {
		
		FileReader entrada = new FileReader("matrizEntrada.txt");
		BufferedReader ler = new BufferedReader(entrada);
		int linha = 0;
		int coluna = 0;
		boolean validaValores;
		String linhaTxt;
		String dado = "";
		int centroLinha = 0;
		int centroColuna = 0;
		double menorDiferenca = 0; 
		double diferencaAtual = 0;
		
		// Realiza a leitura da primeira linha do arquivo
		String LinhaColunaTxt = ler.readLine();
		String[] LinhaColuna = LinhaColunaTxt.split(" ");
		linha = Integer.parseInt(LinhaColuna[0]);
		coluna = Integer.parseInt(LinhaColuna[1]);
		validaValores = confereValores(linha, coluna);
		
		// Verifica o retorno da fun��o confereValores
		if (validaValores) { 
			double[][] dadosTratados = new double[linha][coluna];
			double[] somaLinhas = new double[linha];
			double[] somaColunas = new double[coluna];
			
			//ler as linhas da matriz
			while((linhaTxt = ler.readLine()) != null) {
				dado += linhaTxt + " ";
			}
			
			ler.close();
			String[] dados  = dado.split(" ");
			insereMatriz(dadosTratados, dados);
						
			somaLinhas = somaLinha(dadosTratados, somaLinhas);
			somaColunas = somaColuna(dadosTratados, somaColunas);
						
			// Os dois blocos de for realizam a verifica��o do centro gravitacional 
			for (int i = 1; i <= somaLinhas.length-1; i++) {
				
				diferencaAtual = Math.abs(encontraCentro(i, somaLinhas));
								
				if (diferencaAtual < menorDiferenca || menorDiferenca == 0) {
					menorDiferenca = diferencaAtual;
					centroLinha = i;
				}
			}
			
			menorDiferenca = 0;
			diferencaAtual = 0;
			
			for (int i = 1; i < somaColunas.length-1; i++) {
				
				diferencaAtual = Math.abs(encontraCentro(i, somaColunas));
								
				if (diferencaAtual < menorDiferenca || menorDiferenca == 0) {
					menorDiferenca = diferencaAtual;
					centroColuna = i;
				}
			}
			
			// Ajusta para o index que o usu�rio entenda, por conta da linha 0 do vetor.
			centroLinha += 1;
			centroColuna += 1;
			
			System.out.println("O centro de gravidade �: ("+centroLinha+", "+centroColuna+")");
						
		} else {
			System.out.println("O centro desta matriz n�o pode ser calculado! "
							 + "\nA matriz precisa ter no m�ximo as dimens�es 3x3"
							 + "\nInsira outra matriz com tamanho v�lido!"); 
		}

	}
	
	// Verifica se o arquivo de entrada possui no m�nimo 3 linhas e 3 colunas
	public static boolean confereValores(int linha, int coluna) {
		return (linha >= 3 && coluna >= 3);
	}
	
	// Insere na matriz os valores lidos no arquivo
	public static void insereMatriz(double[][] matriz, String[] vetor) {
		
		int posicao = 0;
		
		for (int i = 0; i < matriz.length; i++) {
			for(int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = Double.parseDouble(vetor[posicao]);
				posicao++;
			}
		}
	}
	
	// Realiza a soma das linhas e guarda em um vetor
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
	
	// Realiza a soma dos valores das colunas e guarda em um vetor
	public static double[] somaColuna(double[][] matriz, double[] colunas) {
		
		int posicao = 0;
		double somaColuna = 0;
		
		for(int i = 0; i <= matriz[0].length-1; i++) {
			for(int j = 0; j <= matriz.length-1; j++) {
				somaColuna += matriz[j][i];
			}
			colunas[posicao] = Math.round(somaColuna * 10);
			posicao++;
			somaColuna = 0;
		}
		return colunas;
	}
	
	// Fun��o utiliza para imprimir as matrizes no console
	public static void imprimeMatriz(double[][] matriz) {
		for(int i = 0; i < matriz.length; i++) {
			System.out.println("\n");
			for(int j = 0; j < matriz[0].length; j++) {
				System.out.print("["+i+"]["+j+"] => " + matriz[i][j]+"\t");
			}
		}
	}
	
	// Fun��o utiliza para imprimir os vetores no console
	public static void imprimeVetor(double[] vetor) {
		for(int i = 0; i < vetor.length; i++) {
			System.out.println("["+i+"] => " + vetor[i]);
		}
	}
	
	// Fun��o respons�vel por encontrar o valor de diferen�a de cada linha / coluna
	public static double encontraCentro(int index, double[] valoresTotais) {
		double valorAcima = 0, valorAbaixo = 0;
		int indexAbaixo = index + 1;
		
		for (int i = 0; i < index; i++) {
			valorAcima += valoresTotais[i];
		}
		
		for (int i = indexAbaixo; i < valoresTotais.length; i++ ) {
			valorAbaixo += valoresTotais[i];
		}
		
		double diferenca = valorAbaixo - valorAcima;
		return diferenca;
		
	}
	
}
