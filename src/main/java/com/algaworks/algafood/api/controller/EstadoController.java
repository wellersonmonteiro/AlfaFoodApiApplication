package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estado")

public class EstadoController {


   @Autowired
   private EstadoRepository estadoRepository;

  @GetMapping
    private List<Estado> todos(){
      return estadoRepository.todos();
  }
  @GetMapping("{EstadoId}")
    private ResponseEntity<Estado> buscar(@RestController){

  }
}

