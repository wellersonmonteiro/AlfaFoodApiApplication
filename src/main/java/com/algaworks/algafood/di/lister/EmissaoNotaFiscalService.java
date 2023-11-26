package com.algaworks.algafood.di.lister;

import com.algaworks.algafood.di.service.ClienteAtivadoEvent;
import jakarta.servlet.ServletOutputStream;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmissaoNotaFiscalService {
    @EventListener
    public void clenteAtivadoListener(ClienteAtivadoEvent event){
        System.out.println("Emitindo nota fical para cliente" + event.getCliente().getNome());
    }
}
