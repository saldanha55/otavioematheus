package com.mycompany.provajava.controlador;

import com.mycompany.provajava.modelo.dao.FuncionarioDao;
import com.mycompany.provajava.modelo.dao.SetorDao;
import com.mycompany.provajava.modelo.entidade.Funcionario;
import com.mycompany.provajava.modelo.entidade.Setor;
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
 * Controlador para as operações CRUD da entidade Funcionario.
 *
 * @author 11997803674
 */
@WebServlet(WebConstante.BASE_PATH + "/FuncionarioControlador")
public class FuncionarioControlador extends HttpServlet {
    private FuncionarioDao funcionarioDao;
    private SetorDao setorDao;
    private Funcionario funcionario;

    @Override
    public void init() throws ServletException {
        funcionarioDao = new FuncionarioDao();
        setorDao = new SetorDao();
        funcionario = new Funcionario();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) {
                opcao = "cadastrar"; // Opção padrão
            }

            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String cargo = request.getParameter("cargo");
            String salarioStr = request.getParameter("salario");
            String idSetorStr = request.getParameter("idSetor");

            switch (opcao) {
                case "cadastrar":
                    cadastrar(request, response, nome, cpf, cargo, salarioStr, idSetorStr);
                    break;
                case "editar":
                    editar(request, response, id, nome, cpf, cargo, salarioStr, idSetorStr);
                    break;
                case "excluir":
                    excluir(request, response, id, nome, cpf, cargo, salarioStr, idSetorStr);
                    break;
                case "confirmarEditar":
                    confirmarEditar(request, response, id, nome, cpf, cargo, salarioStr, idSetorStr);
                    break;
                case "confirmarExcluir":
                    confirmarExcluir(request, response, id);
                    break;
                case "cancelar":
                    cancelar(request, response);
                    break;
                default:
                    throw new IllegalArgumentException("Opção inválida: " + opcao);
            }
        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: Parâmetro numérico inválido. " + e.getMessage());
        } catch (Exception e) {
            response.getWriter().println("Erro: " + e.getMessage());
            e.printStackTrace(response.getWriter());
        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response, String nome, String cpf, String cargo, String salarioStr, String idSetorStr) throws ServletException, IOException {
        if (nome != null && !nome.isEmpty()) {
            funcionario.setNome(nome);
            funcionario.setCpf(cpf);
            funcionario.setCargo(cargo);
            funcionario.setSalario(new BigDecimal(salarioStr));
            Setor setor = new Setor();
            setor.setId(Integer.valueOf(idSetorStr));
            funcionario.setSetor(setor);
            funcionarioDao.salvar(funcionario);
        }
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response, String id, String nome, String cpf, String cargo, String salarioStr, String idSetorStr) throws ServletException, IOException {
        request.setAttribute("id", id);
        request.setAttribute("nome", nome);
        request.setAttribute("cpf", cpf);
        request.setAttribute("cargo", cargo);
        request.setAttribute("salario", new BigDecimal(salarioStr));
        request.setAttribute("idSetor", Integer.valueOf(idSetorStr));
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("mensagem", "Edite os dados e clique no botão 'salvar'.");
        encaminharParaPagina(request, response);
    }
    
    private void excluir(HttpServletRequest request, HttpServletResponse response, String id, String nome, String cpf, String cargo, String salarioStr, String idSetorStr) throws ServletException, IOException {
        request.setAttribute("id", id);
        request.setAttribute("nome", nome);
        request.setAttribute("cpf", cpf);
        request.setAttribute("cargo", cargo);
        request.setAttribute("salario", new BigDecimal(salarioStr));
        request.setAttribute("idSetor", Integer.valueOf(idSetorStr));
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("mensagem", "Clique no botão 'salvar' para excluir os dados.");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response, String id, String nome, String cpf, String cargo, String salarioStr, String idSetorStr) throws ServletException, IOException {
        funcionario.setId(Integer.valueOf(id));
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setCargo(cargo);
        funcionario.setSalario(new BigDecimal(salarioStr));
        Setor setor = new Setor();
        setor.setId(Integer.valueOf(idSetorStr));
        funcionario.setSetor(setor);
        funcionarioDao.alterar(funcionario);
        encaminharParaPagina(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {
        funcionario.setId(Integer.valueOf(id));
        funcionarioDao.excluir(funcionario);
        encaminharParaPagina(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id", "0");
        request.setAttribute("nome", "");
        request.setAttribute("cpf", "");
        request.setAttribute("cargo", "");
        request.setAttribute("salario", "");
        request.setAttribute("idSetor", "0");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }

    protected void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Busca a lista de todos os funcionários
        List<Funcionario> funcionarios = funcionarioDao.buscarTodas();
        // Busca a lista de todos os setores para o dropdown
        List<Setor> setores = setorDao.buscarTodas();

        request.setAttribute("funcionarios", funcionarios);
        request.setAttribute("setores", setores);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroFuncionario.jsp");
        dispatcher.forward(request, response);
    }
}
