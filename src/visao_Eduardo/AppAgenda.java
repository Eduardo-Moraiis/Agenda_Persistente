package visao_Eduardo;

import negocio_Eduardo.Agenda;
import negocio_Eduardo.Contato;
import negocio_Eduardo.ContatoInexistenteException;
import servicos_Eduardo.ManipulaArquivoTexto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AppAgenda {
    public static void main(String[] args) throws ContatoInexistenteException, IOException {
        Scanner input = new Scanner(System.in);
        Agenda agenda = new Agenda();
        agenda.lerAgenda();

        System.out.println("-----MENU-----\r\n1) Adicionar contato \r\n2) Remover contato \r\n3)Consultar contato \r\n4)Listar contatos \r\n5)Sair\n");
        System.out.printf("Informe a opção desejada: ");
        int opcao = Integer.parseInt(input.nextLine());

        while (opcao != 5){
            System.out.println();
            switch (opcao){
                case 1:
                    Contato contato = new Contato();
                    System.out.printf("Nome: ");
                    contato.setNome(input.nextLine());

                    System.out.printf("Telefone: ");
                    contato.setTelefone(input.nextLine());

                    System.out.printf("Endereço: ");
                    contato.setEndereco(input.nextLine());

                    System.out.printf("E-mail: ");
                    contato.setEmail(input.nextLine());

                    if(agenda.incluirContato(contato))
                        System.out.println("Contato inserido com êxito!\r\n");
                    else
                        System.out.println("Não foi possível inserir o contato.");
                    break;

                case 2:
                    System.out.println("Nome do contato a ser removido: ");
                    if(agenda.removerContato(input.nextLine()))
                        System.out.println("Contato removido com êxito!");
                    else
                        System.out.println("Não foi possível remover o contato.");
                    break;

                case 3:
                    System.out.println("Nome do contato a ser consultado: ");
                    Contato aux = agenda.consultarContato(input.nextLine());
                    System.out.printf("Nome: %s\r\nTelefone: %s\r\nEndereço: %s\r\nE-mail: %s\r\n", aux.getNome(), aux.getTelefone(), aux.getEndereco(), aux.getEmail());
                    break;

                case 4:
                    agenda.persistirAgenda();
                    ArrayList<Contato> contatos = ManipulaArquivoTexto.leituraDeArquivos();

                    System.out.println("--\r\nCONTATOS");
                    for(Contato auxContato: contatos) {
                        System.out.printf("Nome: %s\r\nTelefone: %s\r\nEndereço: %s\r\nE-mail: %s\r\n\r\n", auxContato.getNome(), auxContato.getTelefone(), auxContato.getEndereco(), auxContato.getEmail());
                    }
                    break;
            }

            System.out.printf("\r\nInforme a opção desejada: ");
            opcao = Integer.parseInt(input.nextLine());
            System.out.println();

        }
        agenda.persistirAgenda();
        System.out.println("Obrigado por utilizar o programa!");
    }
}