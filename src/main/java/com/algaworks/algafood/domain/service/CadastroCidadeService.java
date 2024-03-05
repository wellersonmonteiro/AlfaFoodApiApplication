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

@Repository
public class CadastroCidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade salvar(Cidade cidade){
        Long cidadeId = cidade.getEstado().getId();

        Estado estado = estadoRepository.buscar(cidadeId);
        if (estado== null){
            throw new EntidadeNaoEncontradaExecption(
                    String.format("Nãi existe cozinha com código %d", cidadeId));
        }
        return cidadeRepository.salvar(cidade);
    }
    public void excluir(Long cidadeId){
        try {
            cidadeRepository.remover(cidadeId);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaExecption(
                    String.format("Não existe um cadastro com código %d", cidadeId));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeNaoEncontradaExecption(String.format("Cidade  de código %d não pode ser" +
                    " removida, pois está em uso", cidadeId));
        }
    }
}
