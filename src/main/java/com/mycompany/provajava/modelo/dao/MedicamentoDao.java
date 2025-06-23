/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provajava.modelo.dao;

import com.mycompany.provajava.modelo.entidade.Medicamento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 11997803674
 */
public class MedicamentoDao extends GenericoDAO<Medicamento>{

    public void salvar (Medicamento obj) {
        String sql = "INSERT INTO `pj_hospital_otavio_matheus`.`MEDICAMENTO` (`nome`, `preco`, `quantidade`, `desc`) VALUES (?, ?, ?, ?);";
        save (sql, obj.getNome(), obj.getPreco(), obj.getQuantidade(),obj.getDesc());
    }
    
    public void alterar (Medicamento obj) {
        String sql = "UPDATE `pj_hospital_otavio_matheus`.`MEDICAMENTO` SET `nome`=?, `preco`=?, `quantidade`=?, `desc`=? WHERE  `id`=?";
        save (sql, obj.getNome(), obj.getPreco(), obj.getDesc(), obj.getQuantidade(), obj.getId());
    }
    
    public void excluir (Medicamento obj){
        String sql="DELETE FROM MEDICAMENTO WHERE ID=?;";
        save(sql, obj.getId());
    }
    
    private static class MedicamentoRowMapper implements RowMapper<Medicamento>{
        
        @Override
        public Medicamento mapRow(ResultSet rs) throws SQLException {
            Medicamento obj = new Medicamento();
            obj.setId(rs.getInt("id"));
            obj.setQuantidade(rs.getInt("quantidade"));
            obj.setNome(rs.getString("nome"));
            obj.setDesc(rs.getString("desc"));
            obj.setPreco(rs.getBigDecimal("preco"));
            return obj;
        }
    }
    
    public List<Medicamento> buscarTodas(){
        String sql="SELECT * FROM MEDICAMENTO";
        return buscarTodos(sql, new MedicamentoRowMapper());
    }
    
    public  Medicamento buscarPorId (int id) {
        String sql = "SELECT * FROM MEDICAMENTO WHERE ID=?;";
        return buscarPorId(sql, new MedicamentoRowMapper(), id);
    }
}
