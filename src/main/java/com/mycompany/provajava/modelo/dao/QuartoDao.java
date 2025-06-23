/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provajava.modelo.dao;

import com.mycompany.provajava.modelo.entidade.Quarto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 11997803674
 */
public class QuartoDao extends GenericoDAO<Quarto>{

    public void salvar (Quarto obj) {
        String sql = "INSERT INTO `pj_hospital_otavio_matheus`.`QUARTO` (`nome`, `preco`, `andar`, `plano`) VALUES (?, ?, ?, ?);";
        save (sql, obj.getNome(), obj.getPreco(), obj.getAndar(), obj.getPlano());
    }
    
    public void alterar (Quarto obj) {
        String sql = "UPDATE `pj_hospital_otavio_matheus`.`QUARTO` SET `nome`=?, `preco`=?, `andar`=?, `plano`=? WHERE  `id`=?";
        save (sql, obj.getNome(), obj.getPreco(), obj.getAndar(), obj.getPlano(), obj.getId());
    }
    
    public void excluir (Quarto obj){
        String sql="DELETE FROM QUARTO WHERE ID=?;";
        save(sql, obj.getId());
    }
    
    private static class QuartoRowMapper implements RowMapper<Quarto>{
        
        @Override
        public Quarto mapRow(ResultSet rs) throws SQLException {
            Quarto obj = new Quarto();
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
            obj.setAndar(rs.getString("andar"));
            obj.setPlano(rs.getString("plano"));
            obj.setPreco(rs.getBigDecimal("preco"));
            return obj;
        }
    }
    
    public List<Quarto> buscarTodas(){
        String sql="SELECT * FROM QUARTO";
        return buscarTodos(sql, new QuartoRowMapper());
    }
    
    public  Quarto buscarPorId (int id) {
        String sql = "SELECT * FROM QUARTO WHERE ID=?;";
        return buscarPorId(sql, new QuartoRowMapper(), id);
    }
}
