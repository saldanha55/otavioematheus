/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provajava.modelo.dao;

import com.mycompany.provajava.modelo.entidade.Paciente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 11997803674
 */
public class PacienteDao extends GenericoDAO<Paciente>{

    public void salvar (Paciente obj) {
        String sql = "INSERT INTO `pj_hospital_otavio_matheus`.`paciente` (`nome`, `rg`, `cpf`, `dataNascimento`) VALUES (?, ?, ?, ?);";
        save (sql, obj.getNome(), obj.getRg(), obj.getCpf(), obj.getDataNascimento());
    }
    
    public void alterar (Paciente obj) {
        String sql = "UPDATE `pj_hospital_otavio_matheus`.`paciente` SET `nome`=?, `rg`=?, `cpf`=?, `dataNascimento`=? WHERE  `id`=?";
        save (sql, obj.getNome(), obj.getRg(), obj.getCpf(), obj.getDataNascimento(), obj.getId());
    }
    
    public void excluir (Paciente obj){
        String sql="DELETE FROM PACIENTE WHERE ID=?;";
        save(sql, obj.getId());
    }
    
    private static class PacienteRowMapper implements RowMapper<Paciente>{
        
        @Override
        public Paciente mapRow(ResultSet rs) throws SQLException {
            Paciente obj = new Paciente();
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
            obj.setRg(rs.getString("rg"));
            obj.setCpf(rs.getString("cpf"));
            obj.setDataNascimento(rs.getString("dataNascimento"));
            return obj;
        }
    }
    
    public List<Paciente> buscarTodas(){
        String sql="SELECT * FROM PACIENTE";
        return buscarTodos(sql, new PacienteRowMapper());
    }
    
    public  Paciente buscarPorId (int id) {
        String sql = "SELECT * FROM PACIENTE WHERE ID=?;";
        return buscarPorId(sql, new PacienteRowMapper(), id);
    }
}
