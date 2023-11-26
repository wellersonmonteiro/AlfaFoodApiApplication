package com.algaworks.algafood.di;

import com.algaworks.algafood.di.service.AtivacaoClienteService;

public class ServiceConfig {
    public AtivacaoClienteService ativacaoClienteService(){
        return new AtivacaoClienteService();
    }
}

