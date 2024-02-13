package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.FormaPagamento;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FormaPagamentoRepository {

	List<FormaPagamento> todas();
	FormaPagamento buscar(Long id);


	@Transactional
	FormaPagamento salvar(FormaPagamento formaPagamento);



	@Transactional
	void remover(FormaPagamento formaPagamento);
}
