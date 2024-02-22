package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.execptin.EntidadeEmUsoException;
import com.algaworks.algafood.domain.execptin.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import jakarta.servlet.annotation.ServletSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha){
        return cozinhaRepository.salvar(cozinha);
    }
    public void excluir(Long cozinhaId){
        try{
            cozinhaRepository.remover(cozinhaId);

        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaExecption(String.format(
                    "Não existe um cadastro de cozinha com código %d", cozinhaId));
        }
        catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("Cozinha de código %d não pode ser removida, pois estáem uso", cozinhaId));
        }
    }


}
