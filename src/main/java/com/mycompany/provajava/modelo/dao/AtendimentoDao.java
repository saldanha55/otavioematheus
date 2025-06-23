package com.mycompany.provajava.modelo.dao;

import com.mycompany.provajava.modelo.entidade.Atendimento;
import com.mycompany.provajava.modelo.entidade.Funcionario;
import com.mycompany.provajava.modelo.entidade.Paciente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO para a entidade Atendimento.
 *
 * @author 11997803674
 */
public class AtendimentoDao extends GenericoDAO<Atendimento> {

    public void salvar(Atendimento obj) {
        String sql = "INSERT INTO ATENDIMENTO (dataAtendimento, descricao, idPaciente, idFuncionario) VALUES (?, ?, ?, ?)";
        save(sql, obj.getDataAtendimento(), obj.getDescricao(), obj.getPaciente().getId(), obj.getFuncionario().getId());
    }

    public void alterar(Atendimento obj) {
        String sql = "UPDATE ATENDIMENTO SET dataAtendimento = ?, descricao = ?, idPaciente = ?, idFuncionario = ? WHERE id = ?";
        save(sql, obj.getDataAtendimento(), obj.getDescricao(), obj.getPaciente().getId(), obj.getFuncionario().getId(), obj.getId());
    }

    public void excluir(Atendimento obj) {
        String sql = "DELETE FROM ATENDIMENTO WHERE id = ?";
        save(sql, obj.getId());
    }

    private static class AtendimentoRowMapper implements RowMapper<Atendimento> {
        @Override
        public Atendimento mapRow(ResultSet rs) throws SQLException {
            Atendimento atendimento = new Atendimento();
            atendimento.setId(rs.getInt("id"));
            atendimento.setDataAtendimento(rs.getTimestamp("dataAtendimento"));
            atendimento.setDescricao(rs.getString("descricao"));

            Paciente paciente = new Paciente();
            paciente.setId(rs.getInt("idPaciente"));
            paciente.setNome(rs.getString("nomePaciente"));
            atendimento.setPaciente(paciente);

            Funcionario funcionario = new Funcionario();
            funcionario.setId(rs.getInt("idFuncionario"));
            funcionario.setNome(rs.getString("nomeFuncionario"));
            atendimento.setFuncionario(funcionario);

            return atendimento;
        }
    }

    public List<Atendimento> buscarTodos() {
        String sql = "SELECT a.*, p.nome as nomePaciente, f.nome as nomeFuncionario " +
                     "FROM ATENDIMENTO a " +
                     "JOIN PACIENTE p ON a.idPaciente = p.id " +
                     "JOIN FUNCIONARIO f ON a.idFuncionario = f.id";
        return buscarTodos(sql, new AtendimentoRowMapper());
    }

    public Atendimento buscarPorId(int id) {
        String sql = "SELECT a.*, p.nome as nomePaciente, f.nome as nomeFuncionario " +
                     "FROM ATENDIMENTO a " +
                     "JOIN PACIENTE p ON a.idPaciente = p.id " +
                     "JOIN FUNCIONARIO f ON a.idFuncionario = f.id " +
                     "WHERE a.id = ?";
        return buscarPorId(sql, new AtendimentoRowMapper(), id);
    }
}
