package com.mycompany.provajava.controlador;

import com.mycompany.provajava.modelo.dao.AtendimentoDao;
import com.mycompany.provajava.modelo.dao.FuncionarioDao;
import com.mycompany.provajava.modelo.dao.PacienteDao;
import com.mycompany.provajava.modelo.entidade.Atendimento;
import com.mycompany.provajava.modelo.entidade.Funcionario;
import com.mycompany.provajava.modelo.entidade.Paciente;
import com.mycompany.provajava.servicos.WebConstante;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Controlador para as operações CRUD da entidade Atendimento.
 *
 * @author 11997803674
 */
@WebServlet(WebConstante.BASE_PATH + "/AtendimentoControlador")
public class AtendimentoControlador extends HttpServlet {

    private AtendimentoDao atendimentoDao;
    private PacienteDao pacienteDao;
    private FuncionarioDao funcionarioDao;
    private Atendimento atendimento;

    @Override
    public void init() throws ServletException {
        atendimentoDao = new AtendimentoDao();
        pacienteDao = new PacienteDao();
        funcionarioDao = new FuncionarioDao();
        atendimento = new Atendimento();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) {
                request.setAttribute("opcao", "cadastrar");
                encaminharParaPagina(request, response);
                return;
            }

            String id = request.getParameter("id");
            
            switch (opcao) {
                case "cadastrar":
                    cadastrar(request, response);
                    break;
                case "editar":
                    editar(request, response, id);
                    break;
                case "excluir":
                    excluir(request, response, id);
                    break;
                case "confirmarEditar":
                    confirmarEditar(request, response, id);
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
        } catch (Exception e) {
            response.getWriter().println("Erro: " + e.getMessage());
            e.printStackTrace(response.getWriter());
        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dataStr = request.getParameter("dataAtendimento");
        if (dataStr != null && !dataStr.isEmpty()) {
            atendimento.setDataAtendimento(Timestamp.valueOf(LocalDateTime.parse(dataStr)));
            atendimento.setDescricao(request.getParameter("descricao"));
            
            Paciente p = new Paciente();
            p.setId(Integer.valueOf(request.getParameter("idPaciente")));
            atendimento.setPaciente(p);

            Funcionario f = new Funcionario();
            f.setId(Integer.valueOf(request.getParameter("idFuncionario")));
            atendimento.setFuncionario(f);
            
            atendimentoDao.salvar(atendimento);
        }
        encaminharParaPagina(request, response);
    }
    
    private void editar(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {
        Atendimento atendimentoParaEditar = atendimentoDao.buscarPorId(Integer.parseInt(id));
        request.setAttribute("id", atendimentoParaEditar.getId());
        request.setAttribute("dataAtendimento", atendimentoParaEditar.getDataAtendimento().toLocalDateTime());
        request.setAttribute("descricao", atendimentoParaEditar.getDescricao());
        request.setAttribute("idPaciente", atendimentoParaEditar.getPaciente().getId());
        request.setAttribute("idFuncionario", atendimentoParaEditar.getFuncionario().getId());
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("mensagem", "Edite os dados e clique no botão 'salvar'.");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {
        Atendimento atendimentoParaExcluir = atendimentoDao.buscarPorId(Integer.parseInt(id));
        request.setAttribute("id", atendimentoParaExcluir.getId());
        request.setAttribute("dataAtendimento", atendimentoParaExcluir.getDataAtendimento().toLocalDateTime());
        request.setAttribute("descricao", atendimentoParaExcluir.getDescricao());
        request.setAttribute("idPaciente", atendimentoParaExcluir.getPaciente().getId());
        request.setAttribute("idFuncionario", atendimentoParaExcluir.getFuncionario().getId());
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("mensagem", "Clique no botão 'salvar' para excluir os dados.");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {
        atendimento.setId(Integer.valueOf(id));
        atendimento.setDataAtendimento(Timestamp.valueOf(request.getParameter("dataAtendimento")));
        atendimento.setDescricao(request.getParameter("descricao"));

        Paciente p = new Paciente();
        p.setId(Integer.valueOf(request.getParameter("idPaciente")));
        atendimento.setPaciente(p);

        Funcionario f = new Funcionario();
        f.setId(Integer.valueOf(request.getParameter("idFuncionario")));
        atendimento.setFuncionario(f);

        atendimentoDao.alterar(atendimento);
        encaminharParaPagina(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {
        atendimento.setId(Integer.valueOf(id));
        atendimentoDao.excluir(atendimento);
        encaminharParaPagina(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Atendimento> atendimentos = atendimentoDao.buscarTodos();
        List<Paciente> pacientes = pacienteDao.buscarTodas();
        List<Funcionario> funcionarios = funcionarioDao.buscarTodas();

        request.setAttribute("atendimentos", atendimentos);
        request.setAttribute("pacientes", pacientes);
        request.setAttribute("funcionarios", funcionarios);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroAtendimento.jsp");
        dispatcher.forward(request, response);
    }
}
