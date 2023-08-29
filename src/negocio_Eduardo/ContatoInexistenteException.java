package negocio_Eduardo;

public class ContatoInexistenteException extends Exception{

    public ContatoInexistenteException(){
        super("Contato nao localizado");
    }
}
