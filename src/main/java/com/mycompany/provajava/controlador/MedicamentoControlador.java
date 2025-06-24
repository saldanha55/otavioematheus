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

@WebServlet(WebConstante.BASE_PATH + "/MedicamentoControlador")
public class MedicamentoControlador extends HttpServlet {
    private Medicamento obj;
    private MedicamentoDao dao;

    @Override
    public void init() throws ServletException {
        dao = new MedicamentoDao();
        obj = new Medicamento();
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
                    throw new IllegalArgumentException("Opção inválida" + opcao);
            }
        } catch (Exception e) {
            response.getWriter().println("Erro: " + e.getMessage());
            e.printStackTrace(response.getWriter());
        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        obj.setNome(request.getParameter("nome"));
        obj.setDesc(request.getParameter("desc"));
        obj.setQuantidade(Integer.valueOf(request.getParameter("quantidade")));
        obj.setPreco(new BigDecimal(request.getParameter("preco")));
        dao.salvar(obj);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {
        Medicamento med = dao.buscarPorId(Integer.parseInt(id));
        request.setAttribute("id", med.getId());
        request.setAttribute("nome", med.getNome());
        request.setAttribute("quantidade", med.getQuantidade());
        request.setAttribute("desc", med.getDesc());
        request.setAttribute("preco", med.getPreco());
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("mensagem", "Edite os dados e clique no botão 'salvar'.");
        encaminharParaPagina(request, response);
    }
    
    private void excluir(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {
        Medicamento med = dao.buscarPorId(Integer.parseInt(id));
        request.setAttribute("id", med.getId());
        request.setAttribute("nome", med.getNome());
        request.setAttribute("quantidade", med.getQuantidade());
        request.setAttribute("desc", med.getDesc());
        request.setAttribute("preco", med.getPreco());
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("mensagem", "Clique no botão 'salvar' para excluir os dados.");
        encaminharParaPagina(request, response);
    }
    
    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {
        obj.setId(Integer.valueOf(id));
        obj.setNome(request.getParameter("nome"));
        obj.setQuantidade(Integer.valueOf(request.getParameter("quantidade")));
        obj.setDesc(request.getParameter("desc"));
        obj.setPreco(new BigDecimal(request.getParameter("preco")));
        dao.alterar(obj);
        encaminharParaPagina(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {
        obj.setId(Integer.valueOf(id));
        dao.excluir(obj);
        encaminharParaPagina(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id", "0");
        request.setAttribute("nome", "");
        request.setAttribute("quantidade", "");
        request.setAttribute("desc", "");
        request.setAttribute("preco", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }

    protected void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Medicamento> medicamentos = dao.buscarTodas();
        request.setAttribute("medicamentos", medicamentos);
        RequestDispatcher enviar = request.getRequestDispatcher("/CadastroMedicamento.jsp");
        enviar.forward(request, response);
    }
}
