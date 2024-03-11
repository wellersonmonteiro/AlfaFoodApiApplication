package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.execptin.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public class CadastroCidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade salvar(Cidade cidade){
        Long cidadeId = cidade.getEstado().getId();

        Optional<Estado> estado = estadoRepository.findById(cidadeId);
        if (estado.isPresent()){
            throw new EntidadeNaoEncontradaExecption(
                    String.format("Nãi existe cozinha com código %d", cidadeId));
        }
        return cidadeRepository.save(cidade);
    }
    public void excluir(Long cidadeId){
        try {
            cidadeRepository.deleteById(cidadeId);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaExecption(
                    String.format("Não existe um cadastro com código %d", cidadeId));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeNaoEncontradaExecption(String.format("Cidade  de código %d não pode ser" +
                    " removida, pois está em uso", cidadeId));
        }
    }
}
