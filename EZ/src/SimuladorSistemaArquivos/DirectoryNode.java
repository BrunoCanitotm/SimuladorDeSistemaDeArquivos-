package SimuladorSistemaArquivos;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;


public class DirectoryNode implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private DirectoryNode parent;
    private List<FileEntry> files;
    private List<DirectoryNode> subdirectories;

    public DirectoryNode(String name, DirectoryNode parent) {
        this.name = name;
        this.parent = parent;
        this.files = new ArrayList<>();
        this.subdirectories = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public DirectoryNode getParent() {
        return parent;
    }

    public DirectoryNode getSubdirectory(String name) {
        for (DirectoryNode dir : subdirectories) {
            if (dir.getName().equals(name)) return dir;
        }
        return null;
    }
    public void renameSubdirectory(String oldName, String newName) {
        for (DirectoryNode dir : subdirectories) {
            if (dir.getName().equals(oldName)) {
                dir.name = newName;
                System.out.println("🔧 Subdiretório renomeado de '" + oldName + "' para '" + newName + "'");
                return;
            }
        }
        System.out.println("❌ Subdiretório '" + oldName + "' não encontrado para renomear.");
    }
    public void renameFile(String oldName, String newName) {
        for (FileEntry file : files) {
            if (file.getName().equals(oldName)) {
                file.rename(newName);
                System.out.println("🔧 Arquivo renomeado de '" + oldName + "' para '" + newName + "'");
                return;
            }
        }
        System.out.println("❌ Arquivo '" + oldName + "' não encontrado para renomear.");
    }
    public void copyFile(String sourceName, String targetName) {
        for (FileEntry file : files) {
            if (file.getName().equals(sourceName)) {
                files.add(new FileEntry(targetName));
                System.out.println("📄 Arquivo copiado: '" + sourceName + "' → '" + targetName + "'");
                return;
            }
        }
        System.out.println("❌ Arquivo '" + sourceName + "' não encontrado para cópia.");
    }




    public void addSubdirectory(DirectoryNode dir) {
        subdirectories.add(dir);
    }

    public void addFile(FileEntry file) {
        files.add(file);
    }

    public void removeFile(String fileName) {
        files.removeIf(f -> f.getName().equals(fileName));
    }

    public void removeSubdirectory(String name) {
        subdirectories.removeIf(d -> d.getName().equals(name));
    }

    public void listContents() {
        System.out.println("📁 Diretório: " + getFullPath());
        for (DirectoryNode d : subdirectories) {
            System.out.println("  [DIR] " + d.getName());
        }
        for (FileEntry f : files) {
            System.out.println("  " + f);
        }
    }

    public String getFullPath() {
        if (parent == null) return name;
        return parent.getFullPath() + "/" + name;
    }
}