package com.algaworks.algafood.domain.execptin;

public class EntidadeEmUsoException extends RuntimeException {
    private static final long serialVersionUID = 1L;


    public EntidadeEmUsoException(String mensagem){
        super(mensagem);
    }
}
