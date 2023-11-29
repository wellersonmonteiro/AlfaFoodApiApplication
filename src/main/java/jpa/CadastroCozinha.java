package jpa;

import com.algaworks.algafood.domain.model.Cozinha;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CadastroCozinha {
    @PersistenceContext
    private EntityManager manager;
    public List<Cozinha> listar(){
     return manager.createQuery("from cozinha", Cozinha.class).getResultList();
    }
}
