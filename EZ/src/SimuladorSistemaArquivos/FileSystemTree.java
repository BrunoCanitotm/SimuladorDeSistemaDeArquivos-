package SimuladorSistemaArquivos;

import java.io.Serializable;

public class FileSystemTree implements Serializable {
    private static final long serialVersionUID = 1L;
    private DirectoryNode root;
    private Journal journal;

    public FileSystemTree() {
        this.root = new DirectoryNode("root", null);
        this.journal = new Journal("journal.log");
    }

    public void criarDiretorio(String caminho, String nome) {
        DirectoryNode dir = buscarDiretorio(caminho);
        if (dir != null && dir.getSubdirectory(nome) == null) {
            DirectoryNode novo = new DirectoryNode(nome, dir);
            dir.addSubdirectory(novo);
            journal.registrar("Criado diretório: " + novo.getFullPath());
        } else {
            System.out.println("❌ Caminho inválido ou diretório já existe.");
        }
    }

    public void criarArquivo(String caminho, String nome) {
        DirectoryNode dir = buscarDiretorio(caminho);
        if (dir != null) {
            dir.addFile(new FileEntry(nome));
            journal.registrar("Criado arquivo: " + dir.getFullPath() + "/" + nome);
        } else {
            System.out.println("❌ Caminho inválido.");
        }
    }

    public void listar(String caminho) {
        DirectoryNode dir = buscarDiretorio(caminho);
        if (dir != null) {
            dir.listContents();
            journal.registrar("Listagem de: " + dir.getFullPath());
        } else {
            System.out.println("❌ Diretório não encontrado.");
        }
    }

    public void removerArquivo(String caminho, String nome) {
        DirectoryNode dir = buscarDiretorio(caminho);
        if (dir != null) {
            dir.removeFile(nome);
            journal.registrar("Arquivo removido: " + dir.getFullPath() + "/" + nome);
        } else {
            System.out.println("❌ Caminho inválido.");
        }
    }

    public void removerDiretorio(String caminho, String nome) {
        DirectoryNode dir = buscarDiretorio(caminho);
        if (dir != null && dir.getSubdirectory(nome) != null) {
            dir.removeSubdirectory(nome);
            journal.registrar("Diretório removido: " + dir.getFullPath() + "/" + nome);
        } else {
            System.out.println("❌ Diretório não encontrado.");
        }
    }



    public void renomearArquivo(String caminho, String antigo, String novo) {
        DirectoryNode dir = buscarDiretorio(caminho);
        if (dir != null) {
            dir.renameFile(antigo, novo);  // Este método deve estar em DirectoryNode
            journal.registrar("Arquivo renomeado: " + dir.getFullPath() + "/" + antigo + " → " + novo);
        } else {
            System.out.println("❌ Caminho inválido.");
        }
    }

    public void renomearDiretorio(String caminho, String antigo, String novo) {
        DirectoryNode dir = buscarDiretorio(caminho);
        if (dir != null) {
            dir.renameSubdirectory(antigo, novo);
            journal.registrar("Diretório renomeado: " + antigo + " → " + novo);
        } else {
            System.out.println("❌ Caminho inválido.");
        }
    }
    public void copiarArquivo(String caminho, String nomeOrigem, String nomeDestino) {
        DirectoryNode dir = buscarDiretorio(caminho);
        if (dir != null) {
            dir.copyFile(nomeOrigem, nomeDestino);
            journal.registrar("Arquivo copiado: " + dir.getFullPath() + "/" + nomeOrigem + " → " + nomeDestino);
        } else {
            System.out.println("❌ Caminho inválido.");
        }
    }


    private DirectoryNode buscarDiretorio(String caminho) {
        if (caminho.equals("/") || caminho.equals("root")) return root;

        String[] partes = caminho.split("/");
        DirectoryNode atual = root;

        for (String parte : partes) {
            if (parte.isEmpty() || parte.equals("root")) continue;
            atual = atual.getSubdirectory(parte);
            if (atual == null) return null;
        }

        return atual;
    }
}