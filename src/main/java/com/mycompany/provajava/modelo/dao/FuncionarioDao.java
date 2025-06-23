package com.mycompany.provajava.modelo.dao;

import com.mycompany.provajava.modelo.entidade.Funcionario;
import com.mycompany.provajava.modelo.entidade.Setor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO para a entidade Funcionario.
 * Realiza as operações de CRUD e outras consultas relacionadas a funcionários.
 *
 * @author 11997803674
 */
public class FuncionarioDao extends GenericoDAO<Funcionario> {

    public void salvar(Funcionario obj) {
        // A SQL para inserir um novo funcionário, incluindo a chave estrangeira idSetor.
        String sql = "INSERT INTO `pj_hospital_otavio_matheus`.`FUNCIONARIO` (`nome`, `cpf`, `cargo`, `salario`, `idSetor`) VALUES (?, ?, ?, ?, ?);";
        save(sql, obj.getNome(), obj.getCpf(), obj.getCargo(), obj.getSalario(), obj.getSetor().getId());
    }

    public void alterar(Funcionario obj) {
        // A SQL para atualizar um funcionário existente.
        String sql = "UPDATE `pj_hospital_otavio_matheus`.`FUNCIONARIO` SET `nome`=?, `cpf`=?, `cargo`=?, `salario`=?, `idSetor`=? WHERE `id`=?;";
        save(sql, obj.getNome(), obj.getCpf(), obj.getCargo(), obj.getSalario(), obj.getSetor().getId(), obj.getId());
    }

    public void excluir(Funcionario obj) {
        // A SQL para deletar um funcionário pelo ID.
        String sql = "DELETE FROM FUNCIONARIO WHERE ID = ?;";
        save(sql, obj.getId());
    }

    /**
     * RowMapper para mapear o resultado de uma consulta para um objeto Funcionario.
     * Inclui o mapeamento do objeto Setor relacionado.
     */
    private static class FuncionarioRowMapper implements RowMapper<Funcionario> {
        @Override
        public Funcionario mapRow(ResultSet rs) throws SQLException {
            Funcionario obj = new Funcionario();
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
            obj.setCpf(rs.getString("cpf"));
            obj.setCargo(rs.getString("cargo"));
            obj.setSalario(rs.getBigDecimal("salario"));

            // Mapeia o objeto Setor aninhado
            Setor setor = new Setor();
            setor.setId(rs.getInt("idSetor"));
            // Busca o nome do setor da coluna com alias 'nomeSetor'
            setor.setNome(rs.getString("nomeSetor"));
            obj.setSetor(setor);

            return obj;
        }
    }

    public List<Funcionario> buscarTodas() {
        // SQL com JOIN para buscar todos os funcionários e os nomes dos seus respectivos setores.
        String sql = "SELECT f.*, s.nome as nomeSetor FROM FUNCIONARIO f JOIN SETOR s ON f.idSetor = s.id;";
        return buscarTodos(sql, new FuncionarioRowMapper());
    }

    public Funcionario buscarPorId(int id) {
        // SQL com JOIN para buscar um funcionário específico pelo seu ID.
        String sql = "SELECT f.*, s.nome as nomeSetor FROM FUNCIONARIO f JOIN SETOR s ON f.idSetor = s.id WHERE f.id = ?;";
        return buscarPorId(sql, new FuncionarioRowMapper(), id);
    }
}
