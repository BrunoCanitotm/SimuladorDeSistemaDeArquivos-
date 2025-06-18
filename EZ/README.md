# Simulador de Sistema de Arquivos com Journaling

## 👨‍💻 Autores

* Bruno Canito Teixeira Matos
* Felipe Moura do Nascimento



---

## 📄 Resumo

Este trabalho apresenta o desenvolvimento de um simulador de sistema de arquivos com suporte a journaling, implementado em Java. O objetivo é compreender a organização interna de arquivos e diretórios, além de explorar o uso de log de operações para garantir a integridade dos dados.

---

## 🧠 Introdução

O gerenciamento de arquivos é essencial para o funcionamento de sistemas operacionais. Entender como um sistema de arquivos é estruturado é fundamental para compreender os princípios de organização de dados, segurança e recuperação em caso de falhas.

---

## 🌟 Objetivo

Desenvolver um simulador de sistema de arquivos em Java com as seguintes funcionalidades:

* Copiar arquivos
* Apagar arquivos
* Renomear arquivos
* Criar diretórios
* Apagar diretórios
* Renomear diretórios
* Listar arquivos de um diretório
* Suporte a journaling para registrar todas as operações

---

## 🔧 Metodologia

O simulador foi criado usando programação orientada a objetos em Java. Foram definidas classes para representar arquivos, diretórios e o próprio sistema de arquivos, além de uma classe específica para o journaling, que registra as operações realizadas em um arquivo de log (`journal.log`).

O programa é executado via linha de comando (modo Shell), recebendo instruções interativas como `mkdir`, `touch`, `ls`, `mv`, `rm`, `cp` e `exit`.

---

## 📁 Parte 1: Introdução ao Sistema de Arquivos com Journaling

### O que é um Sistema de Arquivos?

Um sistema de arquivos é responsável pela organização, armazenamento e acesso a dados em dispositivos de armazenamento, como HDs e SSDs.

### O que é Journaling?

Journaling é um mecanismo utilizado em sistemas de arquivos modernos (como EXT4, NTFS) para registrar operações em um log antes de aplicá-las de fato. Isso garante integridade em caso de falhas, permitindo recuperação parcial ou total.

#### Tipos de Journaling:

* **Write-ahead Logging (WAL)**: Escreve primeiro no log, depois aplica no sistema.
* **Log-Structured File System**: Todas as mudanças são tratadas como entradas sequenciais em um log.

---

## 🏛️ Parte 2: Arquitetura do Simulador

### Estrutura de Dados:

* `FileEntry`: representa arquivos.
* `DirectoryNode`: representa diretórios e contém arquivos e subdiretórios.
* `FileSystemTree`: gerencia a árvore de arquivos e diretórios.
* `Shell`: interpreta comandos do usuário.
* `Journal`: gerencia o log de operações (`journal.log`).

### Journaling:

Cada operação de criação, exclusão, renomeação ou listagem é registrada com data e hora em um arquivo de log.

---

## 💻 Parte 3: Implementação em Java

### Classes implementadas:

* **`FileEntry.java`**: representa um arquivo com nome e método de renomear.
* **`DirectoryNode.java`**: gerencia arquivos e subdiretórios, permite renomear e copiar arquivos.
* **`Journal.java`**: registra ações no log com timestamp.
* **`FileSystemTree.java`**: lógica de manipulação do sistema de arquivos.
* **`Shell.java`**: interpreta comandos no modo shell.
* **`Main.java`**: inicia o programa.

---

## ⚙️ Parte 4: Instalação e Funcionamento

### Requisitos:

* Java JDK 8 ou superior
* Terminal / Prompt de comando

### Como executar:

1. Clone o repositório:

   ```bash
   git clone "bota teu github aq"
   cd simulador-sistema-arquivos
   ```
2. Compile o projeto:

   ```bash
   javac SimuladorSistemaArquivos/*.java
   ```
3. Execute:

   ```bash
   java SimuladorSistemaArquivos.Main
   ```

### Exemplos de comandos:

```bash
mkdir root/projetos
mkdir root/projetos/java
touch root/projetos/java/arquivo.txt
ls root/projetos/java
mv root/projetos/java/arquivo.txt relatorio.txt
cp root/projetos/java/relatorio.txt copia.txt
rm root/projetos/java/copia.txt
rm root/projetos/java/
exit
```

---

## 🚀 Resultados Esperados

* Simular operações reais de um sistema de arquivos
* Compreensão prática dos conceitos de journaling
* Registro completo e seguro das operações em log
* Interface simples e interativa por linha de comando

---

## 🔗 Link para o Repositório
"BOTA O LINK DO GITHUB AQ TB"

---

