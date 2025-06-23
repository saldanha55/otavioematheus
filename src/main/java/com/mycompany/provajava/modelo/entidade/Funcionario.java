/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provajava.modelo.entidade;

import java.math.BigDecimal;

/**
 * Representa a entidade Funcionario.
 * Esta classe contém os atributos de um funcionário, incluindo um objeto Setor
 * para representar o relacionamento de chave estrangeira.
 *
 * @author 11997803674
 */
public class Funcionario {

    private Integer id;
    private String nome;
    private String cpf;
    private String cargo;
    private BigDecimal salario;
    private Setor setor; // Relacionamento com Setor

    // Construtor padrão
    public Funcionario() {
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}
