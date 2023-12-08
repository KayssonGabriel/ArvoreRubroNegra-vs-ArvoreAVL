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

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws Exception {
        Path entrada = Path.of(args.length > 0 ? args[0] : "src/arquivoDados/dados100_mil.txt");
        Path saida = Path.of(args.length > 1 ? args[1] : "resultados");
        Arquivo arquivo = new Arquivo();
        int[] vetor = arquivo.lerArquivo(entrada.toString());
        Files.createDirectories(saida);

        Random random = new Random();
        int[] vetorSorteio = random.ints(50_000, -9_999, 10_000).toArray();
        ArvoreAVL arvoreAvl = new ArvoreAVL();
        List<String> contagensAVL = new ArrayList<>();

        long inicioAVL = System.nanoTime();
        for (int numero : vetor) {
            arvoreAvl.raiz = arvoreAvl.insertNo(arvoreAvl.raiz, numero);
        }
        long fimPreenchimentoAVL = System.nanoTime();

        for (int numero : vetorSorteio) {
            if (numero % 3 == 0) {
                arvoreAvl.raiz = arvoreAvl.insertNo(arvoreAvl.raiz, numero);
            } else if (numero % 5 == 0) {
                arvoreAvl.raiz = arvoreAvl.deleteNo(arvoreAvl.raiz, numero);
            } else {
                int contagem = arvoreAvl.contarOcorrencias(arvoreAvl.raiz, numero);
                contagensAVL.add("O número " + numero + " aparece " + contagem + " vezes na árvore AVL");
            }
        }
        long fimAVL = System.nanoTime();

        ArvoreRubroNegra arvoreRubroNegra = new ArvoreRubroNegra();
        List<String> contagensRB = new ArrayList<>();

        long inicioRB = System.nanoTime();
        for (int numero : vetor) {
            arvoreRubroNegra.insert(numero);
        }
        long fimPreenchimentoRB = System.nanoTime();

        for (int numero : vetorSorteio) {
            if (numero % 3 == 0) {
                arvoreRubroNegra.insert(numero);
            } else if (numero % 5 == 0) {
                arvoreRubroNegra.deleteNo(numero);
            } else {
                int contagem = arvoreRubroNegra.contarOcorrencias(arvoreRubroNegra.getRoot(), numero);
                contagensRB.add("O número " + numero + " aparece " + contagem + " vezes na árvore Rubro Negra.");
            }
        }
        long fimRB = System.nanoTime();

        imprimirTempo("AVL", fimPreenchimentoAVL - inicioAVL, fimAVL - inicioAVL);
        imprimirTempo("Rubro Negra", fimPreenchimentoRB - inicioRB, fimRB - inicioRB);
        arquivo.salvarImpressoes(saida.resolve("Contador de vezes que o número aparece na AVL.txt"), contagensAVL);
        arquivo.salvarImpressoes(saida.resolve("Contador de vezes que o número aparece na Rubro Negra.txt"), contagensRB);
    }

    private static void imprimirTempo(String arvore, long preenchimento, long total) {
        System.out.println("Tempo de preenchimento da árvore " + arvore + ": "
                + converterTempo(TimeUnit.NANOSECONDS.toMillis(preenchimento)));
        System.out.println("Tempo total da árvore " + arvore + ": " + converterTempo(TimeUnit.NANOSECONDS.toMillis(total)));
    }

    private static String converterTempo(long tempo) {
        int miliSeg = (int) (tempo % 1000);
        int seg = (int) ((tempo / 1000) % 60);
        int min = (int) ((tempo / 60000) % 60);
        int hr = (int) (tempo / 3600000);

        return String.format("%02d:%02d:%02d:%03d", hr, min, seg, miliSeg);
    }
}
