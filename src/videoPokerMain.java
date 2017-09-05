import java.text.DecimalFormat;
import java.util.*;

public class videoPokerMain {
		static Fila sequencia = new Fila();
	    static Cartas[] card = new Cartas[52];
	    static Scanner in = new Scanner(System.in);
	    static int posicao;
	    static cartasDaMao maoTempTrocado[] = new cartasDaMao[5];
	    static Carteira carteira = new Carteira();
	    static DecimalFormat df = new DecimalFormat("0.##");
	    static double betInicial;
	    
	    static ArrayList mostrarCarta = new ArrayList<>();
	    
	public static void main(String[] args) {

        int cartas[] = new int[52];
        int reserva = 0;
        int cont = 1;
        boolean mudarBet = false;
        for (int i = 0; i < cartas.length; i++) {
            if (cont < 14) {
                reserva = cont;
                card[i] = new Cartas("âœ–", reserva);
            }
            if (cont > 13 && cont < 27) {
                reserva = cont - 13;
                card[i] = new Cartas("â–¼", reserva);
            }
            if (cont > 26 && cont < 40) {
                reserva = cont - 26;
                card[i] = new Cartas("â�‚", reserva);
            }
            if (cont > 39 && cont < 53) {
                reserva = cont - 39;
                card[i] = new Cartas("â˜‚", reserva);
            }
            cont++;
        }
        System.out.println("informe bet");
        betInicial = in.nextDouble();
        //   for (int j = 0; j < card.length; j++) {
        //       System.out.print(card[j].getNaipe() + ":" + card[j].getValor() + " ");
        //   }
        //   System.out.println("\n");
        do {
            System.out.println("New Game:");
            int newGame = in.nextInt();

            switch (newGame) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    carteira.setBet(betInicial);
                    carteira.setDinheiro(carteira.getDinheiro() - carteira.getBet());
                    sequencia.goEmpyt();
                    mostrarCarta.clear();
                    embaralhar(card);
                    for (int j = 0; j < card.length; j++) {
                        sequencia.enqueue(card[j]);
                   //     System.out.print(card[j].getNaipe() + ":" + card[j].getValor() + " ");
                    }
                    System.out.println("\n");
                    //System.out.println("\n");System.out.println("\n");System.out.println("\n");System.out.println("\n");
                    System.out.println("Dinheiro:" + df.format(carteira.getDinheiro()));
                    posicao = 0;
                    mao();
                    System.out.println("\n");
                    // sequencia.mostrarCartaFila();
                    //       trava(posicao);
                    poker();
                    break;
                case 6:
                    System.out.println("informe bet");
                    betInicial = in.nextDouble();

            }
        } while (carteira.getDinheiro() > 0);

    }

    public static void embaralhar(Object v[]) {
        Random random = new Random();
        for (int i = 0; i < (v.length - 1); i++) {
            int j = random.nextInt(v.length);
            Object temp = v[i];
            v[i] = v[j];
            v[j] = temp;
        }
    }

    public static void mao() {

        for (int i = 0; i < maoTempTrocado.length; i++) {
            maoTempTrocado[i] = new cartasDaMao(card[i].getNaipe(), card[i].getValor());
            mostrarCarta.add(sequencia.dequeue());
            posicao++;
        }
        System.out.print("MÃƒO INICIAL: ");
        for (int j = 0; j < maoTempTrocado.length; j++) {
            System.out.print(maoTempTrocado[j].getNaipe() + "" + maoTempTrocado[j].getValor() + " " + " ");
        }
        System.out.print("\n");

        int z;
        System.out.println("Informe quais posicoes quer trocar:");
        z = in.nextInt();
        System.out.println("");
        String quebra = "" + z;
        int holda[] = new int[quebra.length()];
        if (z != 0) {
            for (int u = 0; u < quebra.length(); u++) {
                holda[u] = Integer.parseInt(quebra.charAt(u) + "");
            }
            int cont = 5;
            for (int y = 0; y < maoTempTrocado.length; y++) {
                for (int t = 0; t < holda.length; t++) {
                    if (y == holda[t] - 1) {
                        maoTempTrocado[y] = new cartasDaMao(card[cont].getNaipe(), card[cont].getValor());
                        cont++;
                        posicao++;
                    }
                }
            }
            for (int r = 0; r < holda.length; r++) {
                mostrarCarta.add(sequencia.dequeue());
            }
        }

        System.out.print("NOVA MÃƒO:");
      //  System.out.println("");
        System.out.println("");

        for (int j = 0; j < maoTempTrocado.length; j++) {
            System.out.print(maoTempTrocado[j].getNaipe() + "" + maoTempTrocado[j].getValor() + " " + " ");
        }
     //   System.out.println("");
        System.out.println("");

    }

    public static void insertionSort(int quantDeNumeros[]) {

        for (int i = 0; i < quantDeNumeros.length; i++) {
            int temp = quantDeNumeros[i];
            for (int j = i - 1; j >= 0 && temp < quantDeNumeros[j]; j--) {
                quantDeNumeros[j + 1] = quantDeNumeros[j];
                quantDeNumeros[j] = temp;
            }
        }
    }

    public static void poker() {
        int dupla = 0;//duas dupla
        int trinca = 0;
        int quadra = 0;
        int sequen = 0;
        int flush = 0;
        int sequenc113 = 0;
        int valor[] = new int[5];
        Object naipe[] = new Object[5];

        for (int i = 0; i < maoTempTrocado.length; i++) {
            valor[i] = maoTempTrocado[i].getValor();
            for (int z = i + 1; z < valor.length; z++) {
                //dois pares
                if (valor[i] == maoTempTrocado[z].getValor()) {
                    dupla++;
                    for (int y = z + 1; y < valor.length; y++) {
                        if (valor[i] == maoTempTrocado[y].getValor()) {
                            trinca++;
                            for (int t = y + 1; t < valor.length; t++) {
                                if (valor[i] == maoTempTrocado[t].getValor()) {
                                    quadra++;
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int k = 0; k < naipe.length; k++) {
            naipe[k] = maoTempTrocado[k].getNaipe();
            if (naipe[0].equals(naipe[k])) {
                flush++;
            }
        }
        
        int valorTemp[] = new int[5];
		for (int i = 0; i < valor.length; i++) {
			valorTemp[i] = valor[i];
		}
		for (int j = 0; j < valorTemp.length; j++) {
			if (valorTemp[j] == 13) {
				for (int i = 0; i < valorTemp.length; i++) {
					if (1 == valorTemp[i]) {
						valorTemp[i] = 14;
					}
				}
			}
		}
		
        insertionSort(valor);
        for (int h = 0; h < valor.length; h++) {
            if (1 == valor[h]) {
                for (int i = 0; i < valor.length; i++) {
                    if (10 == valor[i]) {
                        for (int j = 0; j < valor.length; j++) {
                            if (11 == valor[j]) {
                                for (int k = 0; k < valor.length; k++) {
                                    if (12 == valor[k]) {
                                        for (int l = 0; l < valor.length; l++) {
                                            if (13 == valor[l]) {
                                                sequenc113++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int j = 1; j < valor.length; j++) {
            int temp = valor[j - 1];
            if (temp + 1 == valor[j]) {
                sequen++;
            }
        }
        if (sequenc113 == 1 && flush == 5) {
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("ROYAL STRAIGHT FLUSH");//x1000
            carteira.setBet(carteira.getBet() * 1000);
            System.out.println("Game R$: " + df.format(carteira.getBet()));
            System.out.println("");
            for (int j = 0; j < mostrarCarta.size(); j++) {
                System.out.print(card[j].getNaipe() + ":" + card[j].getValor() + " ");
            }
            System.out.println("");
            trava(posicao);
        } else if (flush == 5 && sequen == 4) {
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");

            System.out.println("ROYAL FLUSH");//x150
            carteira.setBet(carteira.getBet() * 150);
            System.out.println("Game R$: " + df.format(carteira.getBet()));
            System.out.println("");
            for (int j = 0; j < mostrarCarta.size(); j++) {
                System.out.print(card[j].getNaipe() + ":" + card[j].getValor() + " ");
            }
            System.out.println("");
            trava(posicao);
        } else if (flush == 5) {
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");

            System.out.println("FLUSH");//x7
            carteira.setBet(carteira.getBet() * 7);
            System.out.println("Game R$: " + df.format(carteira.getBet()));
            System.out.println("");
            for (int j = 0; j < mostrarCarta.size(); j++) {
                System.out.print(card[j].getNaipe() + ":" + card[j].getValor() + " ");
            }
            System.out.println("");
            trava(posicao);
        } else if (sequen == 4 || sequenc113 == 1) {
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");

            System.out.println("STRAIGHT");//x5
            carteira.setBet(carteira.getBet() * 5);
            System.out.println("Game R$: " + df.format(carteira.getBet()));
            System.out.println("");
            for (int j = 0; j < mostrarCarta.size(); j++) {
                System.out.print(card[j].getNaipe() + ":" + card[j].getValor() + " ");
            }
            System.out.println("");
            trava(posicao);
        } else if (quadra == 1) {
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");

            System.out.println("FOUR OF A KIND");//x60
            carteira.setBet(carteira.getBet() * 60);
            System.out.println("Game R$: " + df.format(carteira.getBet()));
            System.out.println("");
            for (int j = 0; j < mostrarCarta.size(); j++) {
                System.out.print(card[j].getNaipe() + ":" + card[j].getValor() + " ");
            }
            System.out.println("");
            trava(posicao);
        } else if (trinca == 1 && dupla > 3) {
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");

            System.out.println("FULL HOUSE");//x10
            carteira.setBet(carteira.getBet() * 10);
            System.out.println("Game R$: " + df.format(carteira.getBet()));
            System.out.println("");
            for (int j = 0; j < mostrarCarta.size(); j++) {
                System.out.print(card[j].getNaipe() + ":" + card[j].getValor() + " ");
            }
            System.out.println("");
            trava(posicao);
        } else if (trinca == 1) {
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");

            System.out.println("THREE OF A KIND");//x3
            carteira.setBet(carteira.getBet() * 3);
            System.out.println("Game R$: " + df.format(carteira.getBet()));
            System.out.println("");
            for (int j = 0; j < mostrarCarta.size(); j++) {
                System.out.print(card[j].getNaipe() + ":" + card[j].getValor() + " ");
            }
            System.out.println("");
            trava(posicao);
        } else if (dupla == 2) {
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");

            System.out.println("TWO PAIR");//x2
            carteira.setBet(carteira.getBet() * 2);
            System.out.println("Game R$: " + df.format(carteira.getBet()));
            System.out.println("");
            for (int j = 0; j < mostrarCarta.size(); j++) {
                System.out.print(card[j].getNaipe() + ":" + card[j].getValor() + " ");
            }
            System.out.println("");
            trava(posicao);
        } else {
            //System.out.println("PERDEU"+"\n");
        }

    }

    public static void trava(int bigMini) {
        int cont = bigMini;
        boolean acertou = true;
        do {
            System.out.println("BIG-1, MINI-2, CREDITAR-3, CHEIA-4:");
            int vamosVer = in.nextInt();
         /*   System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");*/
            if (vamosVer == 1) {
                if (card[cont].getValor() > 7) {
                    System.out.println("");
                    mostrarCarta.add(sequencia.dequeue());
                    System.out.println("Game R$: " + df.format(carteira.getBet()));
                    System.out.println("");
                    for (int j = 0; j < mostrarCarta.size(); j++) {
                        System.out.print(card[j].getNaipe() + ":" + card[j].getValor() + " ");
                    }
                    System.out.println("");
                    System.out.println("ACERTOU, BIG:" + card[cont].getNaipe() + ":" + card[cont].getValor() + "\n");
                    carteira.setBet(carteira.getBet() * 2);
                    cont++;

                } else if (card[cont].getValor() < 7) {
                    System.out.println("");
                    for (int j = 0; j < mostrarCarta.size(); j++) {
                        System.out.print(card[j].getNaipe() + ":" + card[j].getValor() + " ");
                    }
                    System.out.println("");
                    System.out.println("ERROU, MINI:" + card[cont].getNaipe() + ":" + card[cont].getValor() + "\n");
                    int grid = in.nextInt();
                    if (grid == 6) {
                        carteira.setDinheiro(carteira.getDinheiro() - (betInicial * 0.1));
                        for (int g = 0; g < card.length; g++) {
                            System.out.print(card[g].getNaipe() + ":" + card[g].getValor() + " ");
                        }
                        System.out.println("");
                    } else {
                        System.out.println("");
                    }
                    break;
                } else if (card[cont].getValor() == 7) {
                    System.out.println("");
                    mostrarCarta.add(sequencia.dequeue());
                    System.out.println("Game R$: " + df.format(carteira.getBet()));
                    System.out.println("");

                    for (int j = 0; j < mostrarCarta.size(); j++) {
                        System.out.print(card[j].getNaipe() + ":" + card[j].getValor() + " ");
                    }
                    System.out.println("");
                    System.out.println("DRAW:" + card[cont].getNaipe() + ":" + card[cont].getValor() + "\n");
                    cont++;
                }
            } else if (vamosVer == 2) {
                if (card[cont].getValor() > 7) {
                    System.out.println("");
                    for (int j = 0; j < mostrarCarta.size(); j++) {
                        System.out.print(card[j].getNaipe() + ":" + card[j].getValor() + " ");
                    }
                    System.out.println("");
                    System.out.println("ERROU, BIG:" + card[cont].getNaipe() + ":" + card[cont].getValor() + "\n");
                    int grid = in.nextInt();
                    if (grid == 6) {
                        carteira.setDinheiro(carteira.getDinheiro() - (betInicial * 0.1));
                        for (int g = 0; g < card.length; g++) {
                            System.out.print(card[g].getNaipe() + ":" + card[g].getValor() + " ");
                        }
                        System.out.println("");
                    } else {
                        System.out.println("");
                    }
                    break;
                } else if (card[cont].getValor() < 7) {
                    System.out.println("");
                    mostrarCarta.add(sequencia.dequeue());
                    System.out.println("Game R$: " + df.format(carteira.getBet()));
                    System.out.println("");

                    for (int j = 0; j < mostrarCarta.size(); j++) {
                        System.out.print(card[j].getNaipe() + ":" + card[j].getValor() + " ");
                    }
                    System.out.println("");
                    System.out.println("ACERTOU, MINI:" + card[cont].getNaipe() + ":" + card[cont].getValor() + "\n");
                    carteira.setBet(carteira.getBet() * 2);
                    cont++;
                } else if (card[cont].getValor() == 7) {
                    System.out.println("");
                    mostrarCarta.add(sequencia.dequeue());
                    System.out.println("Game R$: " + df.format(carteira.getBet()));
                    System.out.println("");

                    for (int j = 0; j < mostrarCarta.size(); j++) {
                        System.out.print(card[j].getNaipe() + ":" + card[j].getValor() + " ");
                    }
                    System.out.println("");
                    System.out.println("DRAW:" + card[cont].getNaipe() + ":" + card[cont].getValor() + "\n");
                    cont++;
                }
            } else if (vamosVer == 3) {
                System.out.println("");
                System.out.println(card[cont].getNaipe() + ":" + card[cont].getValor() + "\n");
                System.out.println("Ganhou: " + "R$ " + df.format(carteira.getBet()));
                carteira.setDinheiro(carteira.getDinheiro() + carteira.getBet());
                int grid = in.nextInt();
                if (grid == 6) {
                    carteira.setDinheiro(carteira.getDinheiro() - (betInicial * 0.1));
                    for (int g = 0; g < card.length; g++) {
                        System.out.print(card[g].getNaipe() + ":" + card[g].getValor() + " ");
                    }
                    System.out.println("");
                } else {
                    System.out.println("");
                }
                break;
            } else if (vamosVer == 4) {
                System.out.println("Valor da carta:");
                int kvalor = in.nextInt();
                Object kNaipao = 0;
                boolean validaoao = true;
                do {
                    System.out.println("Naipe: 1-â™£, 2-â™¥, 3-â™ , 4-â™¦");
                    int kNaipe = in.nextInt();
                    if (kNaipe == 1) {
                        kNaipao = "â™£";
                    } else if (kNaipe == 2) {
                        kNaipao = "â™¥";
                    } else if (kNaipe == 3) {
                        kNaipao = "â™ ";
                    } else if (kNaipe == 4) {
                        kNaipao = "â™¦";
                    } else {
                        validaoao = false;
                    }
                } while (validaoao = false);
                if (card[cont].getValor() == kvalor && card[cont].getNaipe() == kNaipao) {
                    System.out.println("");
                    mostrarCarta.add(sequencia.dequeue());
                    System.out.println("Game R$: " + df.format(carteira.getBet()));
                    System.out.println("");
                    for (int j = 0; j < mostrarCarta.size(); j++) {
                        System.out.print(card[j].getNaipe() + ":" + card[j].getValor() + " ");
                    }
                    System.out.println("");
                    System.out.println("ACERTOU CHEIA:" + card[cont].getNaipe() + ":" + card[cont].getValor() + "\n");
                    carteira.setBet(carteira.getBet() * 10);
                    cont++;
                } else if (card[cont].getValor() == kvalor) {
                    System.out.println("");
                    mostrarCarta.add(sequencia.dequeue());
                    System.out.println("Game R$: " + df.format(carteira.getBet()));
                    System.out.println("");
                    
                    for (int j = 0; j < mostrarCarta.size(); j++) {
                        System.out.print(card[j].getNaipe() + ":" + card[j].getValor() + " ");
                    }
                    carteira.setBet(carteira.getBet() * 5);
                    System.out.println("ACERTOU, VALOR:" + card[cont].getNaipe() + ":" + card[cont].getValor() + "\n");
                    cont++;
                } else if (card[cont].getNaipe() == kNaipao && card[cont].getValor() == kvalor - 1
                        || card[cont].getNaipe() == kNaipao && card[cont].getValor() == kvalor + 1) {
                    System.out.println("");
                    System.out.println("Game R$: " + df.format(carteira.getBet()));
                    System.out.println("");
                    
                    mostrarCarta.add(sequencia.dequeue());
                    for (int j = 0; j < mostrarCarta.size(); j++) {
                        System.out.print(card[j].getNaipe() + ":" + card[j].getValor() + " ");
                    }
                    System.out.println("DRAW:" + card[cont].getNaipe() + ":" + card[cont].getValor() + "\n");
                    cont++;
                } else {
                    System.out.println("");
                    for (int j = 0; j < mostrarCarta.size(); j++) {
                        System.out.print(card[j].getNaipe() + ":" + card[j].getValor() + " ");
                    }
                    System.out.println("ERROU CHEIA:" + card[cont].getNaipe() + ":" + card[cont].getValor() + "\n");
                    int grid = in.nextInt();
                    if (grid == 6) {
                        carteira.setDinheiro(carteira.getDinheiro() - (betInicial * 0.1));
                        for (int g = 0; g < card.length; g++) {
                            System.out.print(card[g].getNaipe() + ":" + card[g].getValor() + " ");
                        }
                        System.out.println("");
                    } else {
                        System.out.println("");
                    }
                    break;
                }
            } else {
                System.out.println("INVALIDO" + "\n");
            }
        } while (acertou = true);

    }

	

}
