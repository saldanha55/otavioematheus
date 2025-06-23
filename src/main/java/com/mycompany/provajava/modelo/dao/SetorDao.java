/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provajava.modelo.dao;

import com.mycompany.provajava.modelo.entidade.Setor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 11997803674
 */
public class SetorDao extends GenericoDAO<Setor>{

    public void salvar (Setor obj) {
        String sql = "INSERT INTO `pj_hospital_otavio_matheus`.`SETOR` (`nome`, `ref`) VALUES (?, ?);";
        save (sql, obj.getNome(), obj.getRef());
    }
    
    public void alterar (Setor obj) {
        String sql = "UPDATE `pj_hospital_otavio_matheus`.`SETOR` SET `nome`=?, `ref`=? WHERE  `id`=?";
        save (sql, obj.getNome(), obj.getRef(), obj.getId());
    }
    
    public void excluir (Setor obj){
        String sql="DELETE FROM SETOR WHERE ID=?;";
        save(sql, obj.getId());
    }
    
    private static class SetorRowMapper implements RowMapper<Setor>{
        
        @Override
        public Setor mapRow(ResultSet rs) throws SQLException {
            Setor obj = new Setor();
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
            obj.setRef(rs.getString("ref"));
            return obj;
        }
    }
    
    public List<Setor> buscarTodas(){
        String sql="SELECT * FROM SETOR";
        return buscarTodos(sql, new SetorRowMapper());
    }
    
    public  Setor buscarPorId (int id) {
        String sql = "SELECT * FROM SETOR WHERE ID=?;";
        return buscarPorId(sql, new SetorRowMapper(), id);
    }
}
