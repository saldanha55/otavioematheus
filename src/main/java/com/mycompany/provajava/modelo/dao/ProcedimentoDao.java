/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provajava.modelo.dao;

import com.mycompany.provajava.modelo.entidade.Procedimento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 11997803674
 */
public class ProcedimentoDao extends GenericoDAO<Procedimento>{

    public void salvar (Procedimento obj) {
        String sql = "INSERT INTO `pj_hospital_otavio_matheus`.`PROCEDIMENTO` (`nome`, `preco`, `desc`) VALUES (?, ?, ?);";
        save (sql, obj.getNome(), obj.getPreco(), obj.getDesc());
    }
    
    public void alterar (Procedimento obj) {
        String sql = "UPDATE `pj_hospital_otavio_matheus`.`PROCEDIMENTO` SET `nome`=?, `preco`=?, `desc`=? WHERE  `id`=?";
        save (sql, obj.getNome(), obj.getPreco(), obj.getDesc(), obj.getId());
    }
    
    public void excluir (Procedimento obj){
        String sql="DELETE FROM PROCEDIMENTO WHERE ID=?;";
        save(sql, obj.getId());
    }
    
    private static class ProcedimentoRowMapper implements RowMapper<Procedimento>{
        
        @Override
        public Procedimento mapRow(ResultSet rs) throws SQLException {
            Procedimento obj = new Procedimento();
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
            obj.setDesc(rs.getString("desc"));
            obj.setPreco(rs.getBigDecimal("preco"));
            return obj;
        }
    }
    
    public List<Procedimento> buscarTodas(){
        String sql="SELECT * FROM PROCEDIMENTO";
        return buscarTodos(sql, new ProcedimentoRowMapper());
    }
    
    public  Procedimento buscarPorId (int id) {
        String sql = "SELECT * FROM PROCEDIMENTO WHERE ID=?;";
        return buscarPorId(sql, new ProcedimentoRowMapper(), id);
    }
}
