package com.mycompany.provajava.modelo.entidade;

import java.sql.Timestamp;

/**
 * Representa a entidade Atendimento.
 * Um atendimento é realizado por um funcionário para um paciente.
 *
 * @author 11997803674
 */
public class Atendimento {

    private Integer id;
    private Timestamp dataAtendimento;
    private String descricao;
    private Paciente paciente; // Relacionamento com Paciente
    private Funcionario funcionario; // Relacionamento com Funcionario

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Timestamp dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
