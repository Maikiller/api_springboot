package io.github.maikiller.clientes.exception;

public class UsuarioCadastradoException extends RuntimeException {

    public UsuarioCadastradoException(String login){
        super("Usuario já cadastrado parao login " + login);
    }
}
