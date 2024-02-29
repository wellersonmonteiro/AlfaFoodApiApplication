package com.algaworks.algafood.api.controller;

public class Servico {
    private String tipo;
    private double preco;
    private int duracaoMinutos;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public String exibirDetalhes() {
        return String.format("Consulta Veterinária, Preço: %.2f, Duração: %d minutos", getPreco(),getDuracaoMinutos());
    }

    public static void main(String[] args) {
        Servico servico = new Servico();
        servico.setPreco(30.00);
        servico.setDuracaoMinutos(30);
        System.out.println( servico.exibirDetalhes());
    }
}

