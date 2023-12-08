package arquivoDados;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Arquivo {
    public int[] lerArquivo(String url) {
        Path caminho = Paths.get(url); // Vai receber o caminho do arquivo

        String leitura;
        try {
            leitura = Files.readString(caminho).trim();
        } catch (IOException e) {
            throw new UncheckedIOException("Não foi possível ler " + caminho, e);
        }

        ArrayList<Integer> protoVetor = new ArrayList<>();

        StringBuilder num = new StringBuilder();

        for (int i = 0; i < leitura.length(); i++) {
            char caractere = leitura.charAt(i);
            if (caractere != '[' && caractere != ']' && caractere != ',' && !Character.isWhitespace(caractere)) {
                num.append(caractere);
            } else if (num.length() > 0) {
                protoVetor.add(Integer.parseInt(num.toString()));
                num.setLength(0);
            }
        }
        if (num.length() > 0) {
            protoVetor.add(Integer.parseInt(num.toString()));
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

    public void salvarImpressoes(Path caminho, List<String> impressoes) throws IOException {
        Files.write(caminho, impressoes);
    }
}
