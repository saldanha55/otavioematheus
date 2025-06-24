<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="baseURL" value="${pageContext.request.contextPath}${applicationScope.URL_BASE}/PacienteControlador" />

    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
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
        <h1 class="tit">Cadastro Paciente</h1>
        <div class="cadastro">
            <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/PacienteControlador">
                
                <p> <label>Nome:</label>
                    <input type="text" name="nome" value="${nome}" size="40" required class="form"/>
                </p>
                <p> <label>CPF:</label>
                    <input type="text" name="cpf" value="${cpf}" size="40" class="form"/>
                </p>
                <p> <label>RG:</label>
                    <input type="text" name="rg" value="${rg}" size="40" required class="form"/>
                </p>
                <p> <label>Data de Nascimento</label>
                    <input type="text" name="dataNascimento" value="${dataNascimento}" size="40" class="form"/>
                </p>
                <input type="hidden" name="opcao" value="${opcao}">
                <input type="hidden" name="id" value="${id}">
                <input type="submit" value="Salvar" name="btnSalvar">
                <p><label> ${mensagem}</label></p>

                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/PacienteControlador" method="get">
                    <input type="hidden" name="opcao" value="cancelar">
                    <td>
                        <input class="butaoCancelar" type="submit" name="Cancelar" value="Cancelar" />
                    </td>
                </form>
        </div>

        <div class="tabela">
            <table border="1">
                <thead>   
                    <c:if test="${not empty pacientes}">
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>CPF</th>
                            <th>RG</th>
                            <th>Data de nascimento</th>
                            <th>Alterar</th>
                            <th>Excluir</th>
                        </tr>
                    </c:if>   
                <thead>

                    <c:forEach var="paciente" items="${pacientes}">
                    <tbody class="tbody">
                        <tr>
                            <td class="td">${paciente.id}</td>
                            <td class="td">${paciente.nome}</td>
                            <td class="td">${paciente.cpf}</td>
                            <td class="td">${paciente.rg}</td>
                            <td class="td">${paciente.dataNascimento}</td>
                            <td class="td">
                                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/PacienteControlador">
                                    <input type="hidden" name="id" value="${paciente.id}">
                                    <input type="hidden" name="nome" value="${paciente.nome}">
                                    <input type="hidden" name="cpf" value="${paciente.cpf}">
                                    <input type="hidden" name="rg" value="${paciente.rg}">
                                    <input type="hidden" name="dataNascimento" value="${paciente.dataNascimento}">
                                    <input type="hidden" name="opcao" value="editar">
                                    <button class="butaoEditar" type="submit">Editar</button>
                                </form>
                            </td>
                            <td class="td">
                                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/PacienteControlador">
                                    <input type="hidden" name="id" value="${paciente.id}">
                                    <input type="hidden" name="nome" value="${paciente.nome}">
                                    <input type="hidden" name="cpf" value="${paciente.cpf}">
                                    <input type="hidden" name="rg" value="${paciente.rg}">
                                    <input type="hidden" name="dataNascimento" value="${paciente.dataNascimento}">
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
