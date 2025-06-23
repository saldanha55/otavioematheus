package com.mycompany.provajava.modelo.entidade;

import java.sql.Timestamp;

/**
 * Representa a entidade Internacao.
 * Uma internação está associada a um atendimento e a um quarto.
 *
 * @author 11997803674
 */
public class Internacao {

    private Integer id;
    private Timestamp dataEntrada;
    private Timestamp dataSaida;
    private Atendimento atendimento; // Relacionamento com Atendimento
    private Quarto quarto; // Relacionamento com Quarto

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Timestamp dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Timestamp getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Timestamp dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }
}
