//Implemente a árvore Rubro Negra e uma árvore AVL.
//
//        1) Para isso será utilizado o mesmo arquivo de dados da aula anterior (100 mil números), marque o tempo necessário para cada árvore
//        ser completamente preenchida com os dados do arquivo.
//
//        2) Após a inserir todos os dados, faça o sorteio aleatório de outros 50.000 números entre -9999 e 9999. Caso o número sorteado seja
//        múltiplo de 3, inserir esse número na árvore, caso o número sorteado seja multiplo de 5, remover esse número da árvore.
//        Caso não seja nem multiplo de 3 ou de 5, contar quantas vezes esse número aparece na árvore.
//
//
//        Obs 1..: Os conjuntos de números devem ser os mesmos utilizados em ambas as ávores
//
//        Obs 2..: Marque o tempo de execução para uma árvore Rubro Negra e para uma árvore AVL e faça um comparativo explicando entre esses
//        dois tipos de árvores.


package app;

import arquivoDados.Arquivo;
import arvoreAVL.ArvoreAVL;
import arvoreRubroNegra.ArvoreRubroNegra;

import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        ArvoreAVL arvoreAvl = new ArvoreAVL();
        ArvoreRubroNegra arvoreRubroNegra = new ArvoreRubroNegra();
        Random random = new Random();
        Arquivo arquivo = new Arquivo();

        int[] vetor = arquivo.lerArquivo("C:\\Users\\kayss\\Documents\\ED2\\ArvoreAVL\\src\\arquivoDados\\dados100_mil.txt");


        // Arvore AVL
        long inicioAVL = System.currentTimeMillis(); // tempo necessário para a árvore AVL ser completamente preenchida com os dados do arquivo
        for (int i = 0; i < vetor.length; i++) {
            arvoreAvl.raiz = arvoreAvl.insertNo(arvoreAvl.raiz, vetor[i]);
        }
        long fimAVL = System.currentTimeMillis();
        long tempoAVL = fimAVL - inicioAVL;

        String formatTempoAVL = converterTempo(tempoAVL);

        System.out.println("Tempo necessário para a árvore AVL ser completamente preenchida com os dados do arquivo(100 mil): " + formatTempoAVL);


        // Arvore Rubro Negra
        long inicioRubroNegra = System.currentTimeMillis(); // tempo necessário para a árvore Rubro Negra ser completamente preenchida com os dados do arquivo
        for (int i = 0; i < vetor.length; i++) {
            arvoreRubroNegra.insert(vetor[i]);
        }
        long fimRubroNegra = System.currentTimeMillis();
        long tempoRubroNegra = fimRubroNegra - inicioRubroNegra;

        String formatTempoRubroNegra = converterTempo(tempoRubroNegra);

        System.out.println("Tempo necessário para a árvore Rubro Negra ser completamente preenchida com os dados do arquivo(100 mil): " + formatTempoRubroNegra);


        //sorteio aleatório de outros 50.000 números entre -9999 e 9999
        int numeroDeSorteios = 50000;

        for (int i = 0; i < numeroDeSorteios; i++) {
            int numeroSorteado = random.nextInt((9999 - -9999) + 1) + -9999;

            if (numeroSorteado % 3 == 0) {
                arvoreAvl.raiz = arvoreAvl.insertNo(arvoreAvl.raiz, numeroSorteado);
                arvoreRubroNegra.insert(numeroSorteado);
            } else if (numeroSorteado % 5 == 0) {
                arvoreAvl.raiz = arvoreAvl.deleteNo(arvoreAvl.raiz, numeroSorteado);
                //arvoreRubroNegra.deleteNo(numeroSorteado);
            } else {
                int countAVL = arvoreAvl.contarOcorrencias(arvoreAvl.raiz, numeroSorteado);
                int countRubroNegra = arvoreRubroNegra.contarOcorrencias(arvoreRubroNegra.getRoot(), numeroSorteado);
                String impressao = "O número " + numeroSorteado + " aparece " + countAVL + " vezes na árvore AVL e  " + countRubroNegra + " vezes na árvore Rubro Negra.";
                arquivo.salvarImpressao("C:\\Users\\kayss\\Documents\\ED2\\Árvore Rubro Negra vs Árvore AVL\\src\\arquivoDados\\Gravados\\Contador de vezes que o número aparece.txt", impressao);
            }
        }

    }

    private static String converterTempo(long tempo) {
        int miliSeg = (int) (tempo % 1000);
        int seg = (int) ((tempo / 1000) % 60);
        int min = (int) ((tempo / 1000) / 60);
        int hr = (int) (tempo / 3600000);

        return String.format("%02d:%02d:%02d:%03d", hr, min, seg, miliSeg);
    }
}
