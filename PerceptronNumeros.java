import java.util.Scanner;

public class PerceptronNumeros {
    
        public static Scanner sc = new Scanner(System.in);
        
        public static String mensagem;
        
	public static void processa(double n_taxaDeAprendizado, double limiar, boolean[] teste) {
            
        
		
	double w_pesos[][], entrada[][], matrizT[][], yEnt;
	int contador, epocas = 0;
	boolean flag = true;

	w_pesos = new double[][] { 
                    { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, 
		    { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, 
                    { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, 
                    { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

        // Estas entradas são usadas no processo matemático de aprendizagem no neurônio.
	entrada = new double[][] {    
                                { 1 , 1, 1, 1,-1, 1, 1,-1, 1, 1,-1, 1, 1, 1, 1, 1 },
				{ -1, 1,-1, 1, 1,-1,-1, 1,-1,-1, 1,-1, 1, 1, 1, 1 },
				{ 1 , 1, 1,-1,-1, 1, 1, 1, 1, 1,-1,-1, 1, 1, 1, 1 },
				{ 1 , 1, 1,-1,-1, 1,-1, 1, 1,-1,-1, 1, 1, 1, 1, 1 },
				{ 1 ,-1, 1, 1,-1, 1, 1, 1, 1,-1,-1, 1,-1,-1, 1, 1 },
				{ 1 , 1, 1, 1,-1,-1, 1, 1, 1,-1,-1, 1, 1, 1, 1, 1 },
                                { 1 ,-1,-1, 1,-1,-1, 1, 1, 1, 1,-1, 1, 1, 1, 1, 1 },
                                { 1 , 1, 1,-1,-1, 1,-1,-1, 1,-1,-1, 1,-1,-1, 1, 1 },
				{ 1 , 1, 1, 1,-1, 1, 1, 1, 1, 1,-1, 1, 1, 1, 1, 1 },
				{ 1 , 1, 1, 1,-1, 1, 1, 1, 1,-1,-1, 1, 1, 1, 1, 1 } };

	matrizT = new double[][] { 
                    { 1, -1, -1, -1 }, { -1, 1, -1, -1 }, { -1, -1, 1, -1 }, { -1, -1, -1, 1 }, { 1, 1, -1, -1 }, 
                    { -1, 1, 1, -1 }, { -1, -1, 1, 1 }, { 1, 1, 1, -1 }, { -1, 1, 1, 1 }, { 1, -1, -1, 1 } };

	int vetorF[] = new int[4];

	while (flag) {
		contador = 0;
		for (int i = 0; i < entrada.length; i++) {
			for (int a = 0; a < 4; a++) {
				yEnt = 0;
				for (int j = 0; j < 16; j++) {
					yEnt += entrada[i][j] * w_pesos[j][a];
				}
				if (yEnt > limiar) {
					vetorF[a] = 1;
				} else if ((-1 * limiar) <= yEnt && yEnt <= limiar) {
					vetorF[a] = 0;
				} else {
					vetorF[a] = -1;
				}
			}
			if (vetorF[0] == matrizT[i][0] && vetorF[1] == matrizT[i][1] && vetorF[2] == matrizT[i][2]
					&& vetorF[3] == matrizT[i][3]) {
				contador++;
			} else {
				for (int t = 0; t < 4; t++) {
					for (int x = 0; x < 16; x++) {
						w_pesos[x][t] += n_taxaDeAprendizado * (matrizT[i][t] - vetorF[t])
								* entrada[i][x];
					}
				}
			}
		}
			if (contador == 10) {
			flag = false;
		}
			epocas++;
	}
		for (int q = 0; q < 16; q++) {
                    for (int t = 0; t < 4; t++) {
			System.out.print(w_pesos[q][t] + " ");
		}
		System.out.println();
	}
	System.out.println("\n "+ epocas +" epocas)");
	System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
                + "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
	System.out.println("Terminou o Aprendizado ");
        
	double vetorEntradaTeste[], yentTeste = 0;
	vetorEntradaTeste = new double[16];
        
        int vetorEntradaTesteInterface[] = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        
        for(int i=0; i< teste.length; i++){
            if(!teste[i]){
                vetorEntradaTesteInterface[i] = -1;
            }
            System.out.print(vetorEntradaTesteInterface[i] + " ");
        }
        
	int vetorFTeste[] = new int[4];
	for (int i = 0; i < vetorEntradaTeste.length; i++) {
		vetorEntradaTeste[i] = vetorEntradaTesteInterface[i];
	}
        
	for (int j = 0; j < 4; j++) {
            yentTeste = 0;
            for (int r = 0; r < 16; r++) {
		yentTeste += vetorEntradaTeste[r] * w_pesos[r][j];
            }
            if (yentTeste > limiar) {
            	vetorFTeste[j] = 1;
            } else if ((-1 * limiar) <= yentTeste && yentTeste <= limiar) {
		vetorFTeste[j] = 0;
            } else {
		vetorFTeste[j] = -1;
            }
	}
	imprimeResultado(vetorFTeste);

}
                
        
	public static void imprimeResultado(int vetorSaida[]) {
	if (vetorSaida[0] == 1 && vetorSaida[1] == -1 && vetorSaida[2] == -1 && vetorSaida[3] == -1) {
            System.out.println("É o 0.");
            mensagem = "O numero identificado foi o 0.";
	} else if (vetorSaida[0] == -1 && vetorSaida[1] == 1 && vetorSaida[2] == -1 && vetorSaida[3] == -1) {
            System.out.println("É o 1.");
            mensagem = "O numero identificado foi o 1.";
	} else if (vetorSaida[0] == -1 && vetorSaida[1] == -1 && vetorSaida[2] == 1 && vetorSaida[3] == -1) {
            System.out.println("É o 2.");
            mensagem = "O numero identificado foi o 2.";
	} else if (vetorSaida[0] == -1 && vetorSaida[1] == -1 && vetorSaida[2] == -1 && vetorSaida[3] == 1) {
            System.out.println("É o 3.");
            mensagem = "O numero identificado foi o 3.";
	} else if (vetorSaida[0] == 1 && vetorSaida[1] == 1 && vetorSaida[2] == -1 && vetorSaida[3] == -1) {
	    System.out.println("É o 4.");
            mensagem = "O numero identificado foi o 4.";
	} else if (vetorSaida[0] == -1 && vetorSaida[1] == 1 && vetorSaida[2] == 1 && vetorSaida[3] == -1) {
	    System.out.println("É o 5.");
            mensagem = "O numero identificado foi o 5.";
	} else if (vetorSaida[0] == -1 && vetorSaida[1] == -1 && vetorSaida[2] == 1 && vetorSaida[3] == 1) {
	    System.out.println("É o 6.");
            mensagem = "O numero identificado foi o 6.";
	} else if (vetorSaida[0] == 1 && vetorSaida[1] == 1 && vetorSaida[2] == 1 && vetorSaida[3] == -1) {
	    System.out.println("É o 7.");
            mensagem = "O numero identificado foi o 7.";
	} else if (vetorSaida[0] == -1 && vetorSaida[1] == 1 && vetorSaida[2] == 1 && vetorSaida[3] == 1) {
            System.out.println("É o 8.");
            mensagem = "O numero identificado foi o 8.";
	} else if (vetorSaida[0] == 1 && vetorSaida[1] == -1 && vetorSaida[2] == -1 && vetorSaida[3] == 1) {
            System.out.println("É o 9.");
            mensagem = "O numero identificado foi o 9.";
	} else {
            System.out.println("Não compreendeu.");
            System.out.println(vetorSaida[0] + " " + vetorSaida[1] + " " + vetorSaida[2] + " " + vetorSaida[3]);
            mensagem = "O perceptron não compreendeu o numero.";
	}
    }
        
    public static boolean[] advinhaNumero(boolean[] ativados){
        return ativados;
    }

    public static String getMensagem() {
        return mensagem;
    }
    
    
}