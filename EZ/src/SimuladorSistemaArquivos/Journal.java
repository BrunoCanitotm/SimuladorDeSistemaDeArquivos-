package SimuladorSistemaArquivos;

import java.io.*;
import java.time.LocalDateTime;

public class Journal {
    private final String caminhoLog;

    public Journal(String caminhoLog) {
        this.caminhoLog = caminhoLog;
    }

    public void registrar(String operacao) {
        String entrada = "[" + LocalDateTime.now() + "] " + operacao;

        try (FileWriter writer = new FileWriter(caminhoLog, true)) {
            writer.write(entrada + "\n");
        } catch (IOException e) {
            System.out.println("❌ Erro ao registrar no journal: " + e.getMessage());
        }

        System.out.println("📝 [JOURNAL] " + entrada);
    }

    // 🔽 Este método está dentro da classe Journal, e chama o método interpretar
    public void reproduzirOperacoes(FileSystemTree fs) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoLog))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                int pos = linha.indexOf("] ");
                if (pos != -1 && pos + 2 < linha.length()) {
                    String comando = linha.substring(pos + 2).trim();
                    interpretar(fs, comando);
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Erro ao ler journal: " + e.getMessage());
        }
    }

    // 🔽 Este método precisa estar dentro da mesma classe!
    private void interpretar(FileSystemTree fs, String comando) {
        String[] partes = comando.split(" ");
        if (partes.length < 2) return;

        String op = partes[0];

        switch (op) {
            case "mkdir" -> fs.criarDiretorio(obterPai(partes[1]), obterNome(partes[1]));
            case "touch" -> fs.criarArquivo(obterPai(partes[1]), obterNome(partes[1]));
            case "rm" -> {
                if (partes[1].endsWith("/")) {
                    fs.removerDiretorio(obterPai(partes[1]), obterNome(partes[1]));
                } else {
                    fs.removerArquivo(obterPai(partes[1]), obterNome(partes[1]));
                }
            }
            case "mv" -> {
                if (partes.length < 3) return;
                if (partes[1].endsWith("/")) {
                    fs.renomearDiretorio(obterPai(partes[1]), obterNome(partes[1]), partes[2]);
                } else {
                    fs.renomearArquivo(obterPai(partes[1]), obterNome(partes[1]), partes[2]);
                }
            }
            case "cp" -> {
                if (partes.length < 3) return;
                fs.copiarArquivo(obterPai(partes[1]), obterNome(partes[1]), partes[2]);
            }
        }
    }

    private String obterPai(String caminho) {
        int pos = caminho.lastIndexOf('/');
        return (pos == -1) ? "root" : "root/" + caminho.substring(0, pos);
    }

    private String obterNome(String caminho) {
        int pos = caminho.lastIndexOf('/');
        return (pos == -1) ? caminho : caminho.substring(pos + 1).replace("/", "");
    }
}
