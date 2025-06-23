/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provajava.controlador;

import com.mycompany.provajava.modelo.dao.MedicamentoDao;
import com.mycompany.provajava.modelo.entidade.Medicamento;
import com.mycompany.provajava.servicos.WebConstante;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author 11997803674
 */
@WebServlet(WebConstante.BASE_PATH+"/MedicamentoControlador")
public class MedicamentoControlador extends HttpServlet{
    private Medicamento obj;
    private MedicamentoDao dao;
    String id, nome, desc, quantidade;
    BigDecimal  preco;
    
    @Override
    public void init() throws ServletException {
        dao = new MedicamentoDao();
        obj = new Medicamento();
    }
    
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String opcao = request.getParameter("opcao");
            if(opcao==null||opcao.isEmpty()){
                opcao="cadastrar";
            }
            id= request.getParameter("id");
            nome= request.getParameter("nome");
            desc= request.getParameter("desc");
            quantidade= request.getParameter("quantidade");
            preco= new BigDecimal(request.getParameter("preco"));
            
            switch(opcao) {
                case "cadastrar":
                    cadastrar(request, response);
                break;
                case "editar":
                    editar(request, response);
                break;
                case "excluir":
                    excluir(request, response);
                break;
                case "confirmarEditar":
                    confirmarEditar(request, response);
                break;
                case "confirmarExcluir":
                    confirmarExcluir(request, response);
                break;
                case "cancelar":
                    cancelar(request, response);
                break;
                default:
                    throw new IllegalArgumentException("Opção inválida"+opcao);
                }
        }
        catch(NumberFormatException e){
            response.getWriter().println("Erro: um ou mais parâmetros não são números válidos"+e.getMessage());
        }catch(IllegalArgumentException e){
            response.getWriter().println("Erro: Parâmetros ausentes"+e.getMessage());
        }
        }
    protected void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        obj.setNome(nome);
        obj.setDesc(desc);
        obj.setQuantidade(Integer.valueOf(quantidade));
        obj.setPreco(preco);
        dao.salvar(obj);
        encaminharParaPagina(request, response);
    }
    
    private void editar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("id", id);
        request.setAttribute("nome", nome);
        request.setAttribute("quantidade", quantidade);
        request.setAttribute("desc", desc);
        request.setAttribute("preco", preco);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("mensagem", "Edite os dados e clique no botão 'salvar'.");
        encaminharParaPagina(request, response);
    }
    
    private void excluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("id", id);
        request.setAttribute("nome", nome);
        request.setAttribute("quantidade", quantidade);
        request.setAttribute("desc", desc);
        request.setAttribute("preco", preco);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("mensagem", "Clique no botão 'salvar' para excluir os dados.");
        encaminharParaPagina(request, response);
    }
    
    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        obj.setId(Integer.valueOf(id));
        obj.setNome(nome);
        obj.setQuantidade(Integer.valueOf(quantidade));
        obj.setDesc(desc);
        obj.setPreco(preco);
        dao.alterar(obj);
        encaminharParaPagina(request, response);
    }
    
    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        obj.setId(Integer.valueOf(id));
        dao.excluir(obj);
        encaminharParaPagina(request, response);
    }
    
    private void cancelar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("id", 0);
        request.setAttribute("nome", "");
        request.setAttribute("quantidade", "");
        request.setAttribute("desc", "");
        request.setAttribute("preco", "");
        request.setAttribute("opcao", "cancelar");
        encaminharParaPagina(request, response);
    }
    
    protected void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Medicamento> medicamentos = dao.buscarTodas();
        request.setAttribute("medicamentos", medicamentos);
        RequestDispatcher enviar = request.getRequestDispatcher("/CadastroMedicamento.jsp");
        enviar.forward(request, response);
    }
    }