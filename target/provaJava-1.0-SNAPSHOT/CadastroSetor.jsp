<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="baseURL" value="${pageContext.request.contextPath}${applicationScope.URL_BASE}/InternacaoControlador" />

    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/index.html">Home</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/PacienteControlador">Pacientes</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/MedicamentoControlador">Medicamentos</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/ProcedimentoControlador">Procedimentos</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/QuartoControlador">Quartos</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/SetorControlador">Setores</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/FuncionarioControlador">Funcionários</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/AtendimentoControlador">Atendimentos</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/InternacaoControlador">Internações</a></li>
        </ul>
    </nav>
        <h1 class="tit">Cadastro Setor</h1>
        <div class="cadastro">
            <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/SetorControlador">
                <input type="hidden" name="opcao" value="${opcao}">
                <input type="hidden" name="id" value="${id}">
                <p> <label>Nome:</label>
                    <input type="text" name="nome" value="${nome}" size="40" required class="form"/>
                </p>
                <p> <label>REFERENCIA:</label>
                    <input type="text" name="ref" value="${ref}" size="40" required class="form"/>
                </p>
                <input type="submit" value="Salvar" name="btnSalvar">
                <p><label> ${mensagem}</label></p>

                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/SetorControlador">
                    <input type="hidden" name="opcao" value="cancelar">
                    <td>
                        <input class="butaoCancelar" type="submit" name="Cancelar" value="Cancelar" />
                    </td>
                </form>
        </div>

        <div class="tabela">
            <table border="1">
                <thead>   
                    <c:if test="${not empty setors}">
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>REFERÊNCIA</th>
                            <th>Alterar</th>
                            <th>Excluir</th>
                        </tr>
                    </c:if>   
                <thead>

                    <c:forEach var="setor" items="${setors}">
                    <tbody class="tbody">
                        <tr>
                            <td class="td">${setor.id}</td>
                            <td class="td">${setor.nome}</td>
                            <td class="td">${setor.ref}</td>
                            <td class="td">
                                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/SetorControlador">
                                    <input type="hidden" name="id" value="${setor.id}">
                                    <input type="hidden" name="nome" value="${setor.nome}">
                                    <input type="hidden" name="ref" value="${setor.ref}">
                                    <input type="hidden" name="opcao" value="editar">
                                    <button class="butaoEditar" type="submit">Editar</button>
                                </form>
                            </td>
                            <td class="td">
                                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/SetorControlador">
                                    <input type="hidden" name="id" value="${setor.id}">
                                    <input type="hidden" name="nome" value="${setor.nome}">
                                    <input type="hidden" name="ref" value="${setor.ref}">
                                    <input type="hidden" name="opcao" value="excluir">
                                    <button class="butaoExcluir" type="submit">Excluir</button>
                                </form>
                            </td>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
