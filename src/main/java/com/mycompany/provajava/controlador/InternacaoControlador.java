package com.mycompany.provajava.controlador;

import com.mycompany.provajava.modelo.dao.AtendimentoDao;
import com.mycompany.provajava.modelo.dao.InternacaoDao;
import com.mycompany.provajava.modelo.dao.QuartoDao;
import com.mycompany.provajava.modelo.entidade.Atendimento;
import com.mycompany.provajava.modelo.entidade.Internacao;
import com.mycompany.provajava.modelo.entidade.Quarto;
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
 * Controlador para as operações CRUD da entidade Internacao.
 *
 * @author 11997803674
 */
@WebServlet(WebConstante.BASE_PATH + "/InternacaoControlador")
public class InternacaoControlador extends HttpServlet {

    private InternacaoDao internacaoDao;
    private AtendimentoDao atendimentoDao;
    private QuartoDao quartoDao;
    private Internacao internacao;

    @Override
    public void init() throws ServletException {
        internacaoDao = new InternacaoDao();
        atendimentoDao = new AtendimentoDao();
        quartoDao = new QuartoDao();
        internacao = new Internacao();
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
            String dataEntradaStr = request.getParameter("dataEntrada");
            String dataSaidaStr = request.getParameter("dataSaida");
            String idAtendimentoStr = request.getParameter("idAtendimento");
            String idQuartoStr = request.getParameter("idQuarto");

            switch (opcao) {
                case "cadastrar":
                    cadastrar(request, response, dataEntradaStr, dataSaidaStr, idAtendimentoStr, idQuartoStr);
                    break;
                case "editar":
                    editar(request, response, id);
                    break;
                case "excluir":
                    excluir(request, response, id);
                    break;
                case "confirmarEditar":
                    confirmarEditar(request, response, id, dataEntradaStr, dataSaidaStr, idAtendimentoStr, idQuartoStr);
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

    private void cadastrar(HttpServletRequest request, HttpServletResponse response, String dataEntradaStr, String dataSaidaStr, String idAtendimentoStr, String idQuartoStr) throws ServletException, IOException {
        if (idAtendimentoStr != null) {
            internacao.setDataEntrada(Timestamp.valueOf(LocalDateTime.parse(dataEntradaStr)));
            internacao.setDataSaida((dataSaidaStr == null || dataSaidaStr.isEmpty()) ? null : Timestamp.valueOf(LocalDateTime.parse(dataSaidaStr)));
            
            Atendimento a = new Atendimento();
            a.setId(Integer.valueOf(idAtendimentoStr));
            internacao.setAtendimento(a);

            Quarto q = new Quarto();
            q.setId(Integer.valueOf(idQuartoStr));
            internacao.setQuarto(q);
            
            internacaoDao.salvar(internacao);
        }
        encaminharParaPagina(request, response);
    }
    
    private void editar(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {
        Internacao internacaoParaEditar = internacaoDao.buscarPorId(Integer.parseInt(id));
        request.setAttribute("id", internacaoParaEditar.getId());
        request.setAttribute("dataEntrada", internacaoParaEditar.getDataEntrada().toLocalDateTime());
        if (internacaoParaEditar.getDataSaida() != null) {
            request.setAttribute("dataSaida", internacaoParaEditar.getDataSaida().toLocalDateTime());
        }
        request.setAttribute("idAtendimento", internacaoParaEditar.getAtendimento().getId());
        request.setAttribute("idQuarto", internacaoParaEditar.getQuarto().getId());
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("mensagem", "Edite os dados e clique no botão 'salvar'.");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {
        Internacao internacaoParaExcluir = internacaoDao.buscarPorId(Integer.parseInt(id));
        request.setAttribute("id", internacaoParaExcluir.getId());
        request.setAttribute("dataEntrada", internacaoParaExcluir.getDataEntrada().toLocalDateTime());
         if (internacaoParaExcluir.getDataSaida() != null) {
            request.setAttribute("dataSaida", internacaoParaExcluir.getDataSaida().toLocalDateTime());
        }
        request.setAttribute("idAtendimento", internacaoParaExcluir.getAtendimento().getId());
        request.setAttribute("idQuarto", internacaoParaExcluir.getQuarto().getId());
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("mensagem", "Clique no botão 'salvar' para excluir os dados.");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response, String id, String dataEntradaStr, String dataSaidaStr, String idAtendimentoStr, String idQuartoStr) throws ServletException, IOException {
        internacao.setId(Integer.valueOf(id));
        internacao.setDataEntrada(Timestamp.valueOf(LocalDateTime.parse(dataEntradaStr)));
        internacao.setDataSaida((dataSaidaStr == null || dataSaidaStr.isEmpty()) ? null : Timestamp.valueOf(LocalDateTime.parse(dataSaidaStr)));

        Atendimento a = new Atendimento();
        a.setId(Integer.valueOf(idAtendimentoStr));
        internacao.setAtendimento(a);

        Quarto q = new Quarto();
        q.setId(Integer.valueOf(idQuartoStr));
        internacao.setQuarto(q);

        internacaoDao.alterar(internacao);
        encaminharParaPagina(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {
        internacao.setId(Integer.valueOf(id));
        internacaoDao.excluir(internacao);
        encaminharParaPagina(request, response);
    }
    
    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Internacao> internacoes = internacaoDao.buscarTodos();
        List<Atendimento> atendimentos = atendimentoDao.buscarTodos();
        List<Quarto> quartos = quartoDao.buscarTodas();

        request.setAttribute("internacoes", internacoes);
        request.setAttribute("atendimentos", atendimentos);
        request.setAttribute("quartos", quartos);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroInternacao.jsp");
        dispatcher.forward(request, response);
    }
}
