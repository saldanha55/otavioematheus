/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provajava.controlador;

import com.mycompany.provajava.modelo.dao.PacienteDao;
import com.mycompany.provajava.modelo.entidade.Paciente;
import com.mycompany.provajava.servicos.WebConstante;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author 11997803674
 */
@WebServlet(WebConstante.BASE_PATH+"/PacienteControlador")
public class PacienteControlador extends HttpServlet{
    private Paciente obj;
    private PacienteDao dao;
    String id, nome, rg, cpf, dataNascimento;
    
    @Override
    public void init() throws ServletException {
        dao = new PacienteDao();
        obj = new Paciente();
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
            rg= request.getParameter("rg");
            cpf= request.getParameter("cpf");
            dataNascimento= request.getParameter("dataNascimento");
            
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
        obj.setRg(rg);
        obj.setCpf(cpf);
        obj.setDataNascimento(dataNascimento);
        dao.salvar(obj);
        encaminharParaPagina(request, response);
    }
    
    private void editar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("id", id);
        request.setAttribute("nome", nome);
        request.setAttribute("cpf", cpf);
        request.setAttribute("rg", rg);
        request.setAttribute("dataNascimento", dataNascimento);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("mensagem", "Edite os dados e clique no botão 'salvar'.");
        encaminharParaPagina(request, response);
    }
    
    private void excluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("id", id);
        request.setAttribute("nome", nome);
        request.setAttribute("cpf", cpf);
        request.setAttribute("rg", rg);
        request.setAttribute("dataNascimento", dataNascimento);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("mensagem", "Clique no botão 'salvar' para excluir os dados.");
        encaminharParaPagina(request, response);
    }
    
    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        obj.setId(Integer.valueOf(id));
        obj.setNome(nome);
        obj.setCpf(cpf);
        obj.setRg(rg);
        obj.setDataNascimento(dataNascimento);
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
        request.setAttribute("cpf", "");
        request.setAttribute("rg", "");
        request.setAttribute("dataNascimento", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }
    
    protected void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Paciente> pacientes = dao.buscarTodas();
        request.setAttribute("pacientes", pacientes);
        RequestDispatcher enviar = request.getRequestDispatcher("/CadastroPaciente.jsp");
        enviar.forward(request, response);
    }
    }