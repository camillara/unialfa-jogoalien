
import java.util.Scanner;

public class heroiXAlien {

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);

		int tamanho = 20;
		int[][] cenario = new int[tamanho][tamanho]; // 20 linhas e 20 colunas
		int lHeroi = 0;
		int cHeroi = 0;
		int totalAliens = 0;
		int controle = 0;
		int lAlien = 0;
		int cAlien = 0;
		int ataque;
		int raio = 3;
		double dist;
		int aliensEliminados = 0;

		// ==========Informar posição do Heroi
		System.out.println("Informe as coordenadas do heroi:");
		System.out.printf("Linha (de 1 a 20): ");
		lHeroi = entrada.nextInt();
		System.out.printf("Coluna (de 1 a 20): ");
		cHeroi = entrada.nextInt();

		if (lHeroi > 20 || cHeroi < 0) {
			do {
				System.out.println("Coordenada inválida");
				System.out.println("Informe as coordenadas do heroi:");
				System.out.printf("Linha (de 1 a 20): ");
				lHeroi = entrada.nextInt();
				System.out.printf("Coluna (de 1 a 20): ");
				cHeroi = entrada.nextInt();
			} while (cHeroi > 20 || cHeroi < 0 || lHeroi > 20 || lHeroi < 0);

		} // fim do if

		if (cHeroi < 21 && cHeroi > 0 && lHeroi < 21 && lHeroi > 0) {

			cenario[lHeroi - 1][cHeroi - 1] = 1; // esse -1 é pq a matriz começa em zero
		}

		do {// ==========Informar quantos Aliens e suas posições
			System.out.print("Quantos alienígenas deseja inserir (entre 1 e 10): ");
			totalAliens = entrada.nextInt();
		} while (totalAliens > 10 || totalAliens < 1);

		if (totalAliens > 0 && totalAliens < 11) {// Se estiver fora do intervalo de 1 a 10, não aceitar.

			while (controle < totalAliens) { // ==============Pegar posição dos Aliens

				do {
					System.out.printf("Informe as coordenadas do %dº Alien: \n", controle + 1);
					System.out.printf("Linha (de 1 a 20): ");
					lAlien = entrada.nextInt();
					System.out.printf("Coluna (de 1 a 20): ");
					cAlien = entrada.nextInt();
				} while (cAlien > 20 || cAlien < 0 || lAlien > 20 || lAlien < 0);

				if (cAlien == cHeroi && lAlien == lHeroi) { // se o Alien for colocado na mesma posição do heroi.

					System.out.println("O herói está nessa coordenada. Informe outra coordenada para o Alien.");
					controle = controle - 1;

				} // fim if posição Alien inválida

				else {

					if (cenario[lAlien - 1][cAlien - 1] != 0) { // verificar se existe Alien naquela posição.
						System.out
								.println("Já existe um Alien nessa coordenada. Informe outra coordenada para o Alien.");
						controle = controle - 1;
					} // fim if

					cenario[lAlien - 1][cAlien - 1] = 2; // esse -1 é para ajustar com o vetor que inicia em 0.

				} // fim else

				controle++; // laço While

			} // fim while

			// ==============Imprimir matriz antes do ataque para ver os personagens no
			// cenário.
			for (int linha = 0; linha < cenario.length; linha++) {

				for (int coluna = 0; coluna < cenario[linha].length; coluna++) {

					System.out.printf("%d ", cenario[linha][coluna]);

				} // fim for de coluna

				System.out.println(); // esse println é apenas para saltar para a próxima linha quando for exibir para
										// o usuário.

			} // fim do for de linha

		} // fim do if validar quantidade de aliens

		System.out.println();

		// ==========Ataque Heroi
		System.out.println(
				"Escolha qual ataque: \n 1 - Lançar energia em formato de cruz.\n 2 - Ataque de energia circular");
		ataque = entrada.nextInt();

		System.out.println();

		while (ataque > 2 || ataque < 1) {
			System.out.println(
					"Comando inválido. Digite: \n 1 - Lançar energia em formato de cruz.\n 2 - Ataque de energia circular");
			ataque = entrada.nextInt();
		}

		if (ataque == 1) {// ataque de energia em formato de cruz.
			for (int i = 0; i < cenario.length; i++) {

				if (cenario[lHeroi - 1][i] == 2) { // contar quantos aliens eliminados na linha do herói.

					aliensEliminados++;
				}

				if (cenario[i][cHeroi - 1] == 2) { // contar quantos aliens eliminados na coluna do herói.

					aliensEliminados++;
				}

				cenario[lHeroi - 1][i] = 1; // esse -1 é para ajustar pq o vetor começa em 0.
				cenario[i][cHeroi - 1] = 1; // esse -1 é para ajustar pq o vetor começa em 0.

			} // fim for

		} // fim do if ataque 1
		if (ataque == 2) {// ataque de energia circular.

			for (int i = 0; i < cenario.length; i++) {
				for (int j = 0; j < cenario[i].length; j++) {
					dist = Math.sqrt(Math.pow(lHeroi - 1 - i, 2) + Math.pow(cHeroi - 1 - j, 2));
					if (dist <= raio) {

						if (cenario[i][j] == 2) { // contar aliens atingidos

							aliensEliminados++;
						}

						cenario[i][j] = 1;
					}

				}
			}

		} // fim do if ataque 2

		// ==============Imprimir matriz antes do ataque para ver os personagens no
		// cenário.
		for (int linha = 0; linha < cenario.length; linha++) { // linha ++ soma um a linha. É equivalente a escrever
																// linha = linha +1

			for (int coluna = 0; coluna < cenario[linha].length; coluna++) { // o lenggth retorna a dimensão da linha /
																				// coluna.

				System.out.printf("%d ", cenario[linha][coluna]);

			} // fim for de coluna

			System.out.println(); // esse println é apenas para saltar para a próxima linha quando for exibir para
									// o usuário.

		} // fim do for de linha

		System.out.println();
		System.out.println("Aliens Elminiados: " + aliensEliminados); // esse println é apenas para saltar para a
																		// próxima linha quando for exibir para o
																		// usuário.

		entrada.close();

	} // fim main

} // fim class