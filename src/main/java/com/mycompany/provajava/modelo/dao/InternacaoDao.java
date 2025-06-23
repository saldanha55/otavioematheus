package com.mycompany.provajava.modelo.dao;

import com.mycompany.provajava.modelo.entidade.Atendimento;
import com.mycompany.provajava.modelo.entidade.Internacao;
import com.mycompany.provajava.modelo.entidade.Paciente;
import com.mycompany.provajava.modelo.entidade.Quarto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO para a entidade Internacao.
 *
 * @author 11997803674
 */
public class InternacaoDao extends GenericoDAO<Internacao> {

    public void salvar(Internacao obj) {
        String sql = "INSERT INTO INTERNACAO (dataEntrada, dataSaida, idAtendimento, idQuarto) VALUES (?, ?, ?, ?)";
        save(sql, obj.getDataEntrada(), obj.getDataSaida(), obj.getAtendimento().getId(), obj.getQuarto().getId());
    }

    public void alterar(Internacao obj) {
        String sql = "UPDATE INTERNACAO SET dataEntrada = ?, dataSaida = ?, idAtendimento = ?, idQuarto = ? WHERE id = ?";
        save(sql, obj.getDataEntrada(), obj.getDataSaida(), obj.getAtendimento().getId(), obj.getQuarto().getId(), obj.getId());
    }

    public void excluir(Internacao obj) {
        String sql = "DELETE FROM INTERNACAO WHERE id = ?";
        save(sql, obj.getId());
    }

    private static class InternacaoRowMapper implements RowMapper<Internacao> {
        @Override
        public Internacao mapRow(ResultSet rs) throws SQLException {
            Internacao internacao = new Internacao();
            internacao.setId(rs.getInt("id"));
            internacao.setDataEntrada(rs.getTimestamp("dataEntrada"));
            internacao.setDataSaida(rs.getTimestamp("dataSaida"));

            Atendimento atendimento = new Atendimento();
            atendimento.setId(rs.getInt("idAtendimento"));
            atendimento.setDescricao(rs.getString("descAtendimento"));
            
            Paciente paciente = new Paciente();
            paciente.setId(rs.getInt("idPaciente"));
            paciente.setNome(rs.getString("nomePaciente"));
            atendimento.setPaciente(paciente);
            internacao.setAtendimento(atendimento);

            Quarto quarto = new Quarto();
            quarto.setId(rs.getInt("idQuarto"));
            quarto.setNome(rs.getString("nomeQuarto"));
            internacao.setQuarto(quarto);

            return internacao;
        }
    }

    public List<Internacao> buscarTodos() {
        String sql = "SELECT i.*, a.id as idAtendimento, a.descricao as descAtendimento, " +
                     "p.id as idPaciente, p.nome as nomePaciente, " +
                     "q.id as idQuarto, q.nome as nomeQuarto " +
                     "FROM INTERNACAO i " +
                     "JOIN ATENDIMENTO a ON i.idAtendimento = a.id " +
                     "JOIN PACIENTE p ON a.idPaciente = p.id " +
                     "JOIN QUARTO q ON i.idQuarto = q.id";
        return buscarTodos(sql, new InternacaoRowMapper());
    }

    public Internacao buscarPorId(int id) {
        String sql = "SELECT i.*, a.id as idAtendimento, a.descricao as descAtendimento, " +
                     "p.id as idPaciente, p.nome as nomePaciente, " +
                     "q.id as idQuarto, q.nome as nomeQuarto " +
                     "FROM INTERNACAO i " +
                     "JOIN ATENDIMENTO a ON i.idAtendimento = a.id " +
                     "JOIN PACIENTE p ON a.idPaciente = p.id " +
                     "JOIN QUARTO q ON i.idQuarto = q.id " +
                     "WHERE i.id = ?";
        return buscarPorId(sql, new InternacaoRowMapper(), id);
    }
}
