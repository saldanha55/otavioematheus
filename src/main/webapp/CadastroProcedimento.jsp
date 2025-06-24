<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="baseURL" value="${pageContext.request.contextPath}${applicationScope.URL_BASE}/ProcedimentoControlador" />

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
        <h1 class="tit">Cadastro Procedimento</h1>
        <div class="cadastro">
            <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/ProcedimentoControlador">
                <input type="hidden" name="opcao" value="${opcao}">
                <input type="hidden" name="id" value="${id}">
                <p> <label>Nome:</label>
                    <input type="text" name="nome" value="${nome}" size="40" required class="form"/>
                </p>
                <p> <label>DESCRIÇÃO:</label>
                    <input type="text" name="desc" value="${desc}" size="40" class="form"/>
                </p>
                <p> <label>PREÇO</label>
                    <input type="text" name="preco" oninput="
                           this.value = this.value
                           .replace(/[^0-9.,]/g, '')            // só números, ponto ou vírgula
                           .replace(',', '.')                   // converte vírgula pra ponto
                           .replace(/^(\d{0,5})(\d*)(\.?\d*)$/, '$1$3') // só permite até 5 antes do ponto
                           " value="${preco}" size="40" class="form"/>
                </p>
                <input type="submit" value="Salvar" name="btnSalvar">
                <p><label> ${mensagem}</label></p>

                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/ProcedimentoControlador">
                    <input type="hidden" name="opcao" value="cancelar">
                    <td>
                        <input class="butaoCancelar" type="submit" name="Cancelar" value="Cancelar" />
                    </td>
                </form>
        </div>

        <div class="tabela">
            <table border="1">
                <thead>   
                    <c:if test="${not empty procedimentos}">
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>DESCRIÇÃO</th>
                            <th>PREÇO</th>
                            <th>Alterar</th>
                            <th>Excluir</th>
                        </tr>
                    </c:if>   
                <thead>

                    <c:forEach var="procedimento" items="${procedimentos}">
                    <tbody class="tbody">
                        <tr>
                            <td class="td">${procedimento.id}</td>
                            <td class="td">${procedimento.nome}</td>
                            <td class="td">${procedimento.desc}</td>
                            <td class="td">${procedimento.preco}</td>
                            <td class="td">
                                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/ProcedimentoControlador">
                                    <input type="hidden" name="id" value="${procedimento.id}">
                                    <input type="hidden" name="nome" value="${procedimento.nome}">
                                    <input type="hidden" name="desc" value="${procedimento.desc}">
                                    <input type="hidden" name="preco" value="${procedimento.preco}">
                                    <input type="hidden" name="opcao" value="editar">
                                    <button class="butaoEditar" type="submit">Editar</button>
                                </form>
                            </td>
                            <td class="td">
                                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/ProcedimentoControlador">
                                    <input type="hidden" name="id" value="${procedimento.id}">
                                    <input type="hidden" name="nome" value="${procedimento.nome}">
                                    <input type="hidden" name="desc" value="${procedimento.desc}">
                                    <input type="hidden" name="preco" value="${procedimento.preco}">
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
