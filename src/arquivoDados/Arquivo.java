package arquivoDados;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Arquivo {
    public int[] lerArquivo(String url) {
        Path caminho = Paths.get(url); // Vai receber o caminho do arquivo

        String leitura = "";

        try {
            byte[] texto = Files.readAllBytes(caminho);

            leitura = new String(texto);

        } catch (Exception e) {
            System.out.println("Não foi possível ler o arquivo!");
        }

        ArrayList<Integer> protoVetor = new ArrayList<>();

        String num = "";

        for (int i = 0; i < leitura.length(); i++) {
            if (leitura.charAt(i) != '[' && leitura.charAt(i) != ']' && leitura.charAt(i) != ','
                    && leitura.charAt(i) != ' ') {
                num += leitura.charAt(i);
            } else {
                if (num != "") {
                    int numero = Integer.parseInt(num);
                    protoVetor.add(numero);
                    num = "";
                }
            }
        }

        int[] vetor = new int[protoVetor.size()];

        for (int i = 0; i < protoVetor.size(); i++) {
            vetor[i] = protoVetor.get(i);
        }

        return vetor;
    }

    public void gravarArquivo(String url, int[] conteudo, String nomeAlg, long tempoExe) {
        Path caminho = Paths.get(url);

        int miliSeg = (int) (tempoExe % 1000);
        int seg = (int) (tempoExe / 1000) % 60;
        int min = (int) (tempoExe / 1000) / 60;
        int hr = (int) (tempoExe / 3600000);

        String tempo = String.format("%02d:%02d:%02d:%03d", hr, min, seg, miliSeg);

        String cabecalho = "Nome do Aluno: Kaysson Gabriel Inocêncio de Jesus" + "\nNome do algoritmo: " + nomeAlg + "\nTempo de execução: "
                + tempo + "\n\n";

        String vetor = cabecalho + Arrays.toString(conteudo);

        byte[] textoEmBytes = vetor.getBytes();

        try {
            Files.write(caminho, textoEmBytes);
        } catch (Exception e) {
            System.out.println("Erro!!!");
        }
    }

    public void salvarImpressao(String url, String impressao) {
        Path caminho = Paths.get(url);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho.toFile(), true))) {
            writer.write(impressao + System.lineSeparator());
            writer.flush();
        } catch (IOException e) {
            System.out.println("Erro ao salvar a impressão no arquivo!");
            e.printStackTrace();
        }
    }
}





