package negocio_Eduardo;

import servicos_Eduardo.ManipulaArquivoTexto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Agenda {
    private HashMap<String, Contato> contatos;


    public boolean incluirContato(Contato contato){
        contatos.put(contato.getNome().toLowerCase(), contato);
        if (existeContato(contato.getNome())) {
            return true;
        }
        return false;
    }

    public boolean existeContato(String nome){
        for(String controle : this.contatos.keySet()){
            if(nome.equals(controle)){
                return true;
            }
        }
        return false;
    }

    public Contato consultarContato(String nome) throws ContatoInexistenteException{
        if(contatos.containsKey(nome)){
            return contatos.get(nome);
        }else{
            throw new ContatoInexistenteException();
        }
    }

    public boolean removerContato(String nome){
        if (!existeContato(nome.toLowerCase()))
            return false;

        contatos.remove(nome.toLowerCase());

        //Após remover o contato é preciso atualizar o arquivo txt
        persistirAgenda();

        return true;
    }

    public void persistirAgenda(){
        ArrayList<Contato> aux = new ArrayList<>();
        for (String chave : contatos.keySet()) {
            aux.add(contatos.get(chave));
        }
        ManipulaArquivoTexto.gravarContato(aux);
    }

    public void lerAgenda(){
        for (Contato contato : ManipulaArquivoTexto.leituraDeArquivos()) {
            this.incluirContato(contato);
        }
    }

    //contrutor
    public Agenda(){
        this.contatos = new HashMap<>();
    }

    //GETERS
    public HashMap<String, Contato> getContatos() {
        return contatos;
    }

    public void setContatos(HashMap<String, Contato> contatos) {
        this.contatos = contatos;
    }
}
