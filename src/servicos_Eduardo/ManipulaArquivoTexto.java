package servicos_Eduardo;

import negocio_Eduardo.Contato;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Scanner;

public class ManipulaArquivoTexto {
    private static File file;
    private static Formatter formatter;
    private static Scanner scanner;

    public static ArrayList<Contato> leituraDeArquivos() {
        ArrayList<Contato> contatos = new ArrayList<>();

        try {
            file = new File("agendaPessoal.txt");
            file.createNewFile(); //Cria o arquivo caso ele não exista
            scanner = new Scanner(Paths.get(file.getAbsolutePath()));

            while (scanner.hasNext()) { //lê os registros do arquivo
                String linha = scanner.nextLine();

                //Estrutura das linhas: Nome; Telefone; Endereço; E-mail
                String[] dados = linha.split("; "); //Separa o conteúdo das linhas
                contatos.add(new Contato(dados[0], dados[1], dados[2], dados[3])); //Adiciona o contato na lista a ser retornada
            }

        } catch (FileNotFoundException | SecurityException e) {
            System.err.println("Arquivo não encontrado!");
            System.exit(1);

        } catch (IOException e) {
            System.err.println("Erro ao abrir o arquivo!");
            System.exit(1);
        }

        if (scanner != null)
            scanner.close(); //fecha o leitor
        return contatos;
    }

    public static void gravarContato(ArrayList<Contato> contatos) {
        try {
            formatter = new Formatter("agendaPessoal.txt");

            for(Contato contato : contatos)
                formatter.format("%s; %s; %s; %s\r\n", contato.getNome(), contato.getTelefone(), contato.getEndereco(), contato.getEmail());

        } catch (FileNotFoundException | FormatterClosedException e) {
            System.err.println("Não foi possível gravar o contato");
            System.exit(1);
        }

        if (formatter != null)
            formatter.close(); //fecha o gravador
    }

}
