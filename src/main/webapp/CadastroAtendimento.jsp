<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cadastro de Atendimentos</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/estilo/estilo.css">
</head>
<body>
    <c:set var="baseURL" value="${pageContext.request.contextPath}${applicationScope.URL_BASE}/AtendimentoControlador" />

    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/index.html">Home</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/PacienteControlador">Pacientes</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/MedicamentoControlador">Medicamentos</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/ProcedimentoControlador">Procedimentos</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/QuartoControlador">Quartos</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/SetorControlador">Setores</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/FuncionarioControlador">Funcionários</a></li>
            <li><a href="${baseURL}">Atendimentos</a></li>
        </ul>
    </nav>
    
    <h1>Cadastro de Atendimentos</h1>
    
    <c:if test="${mensagem != null}">
        <p style="color: blue;">${mensagem}</p>
    </c:if>

    <form action="${baseURL}" method="GET">
        <input type="hidden" name="id" value="${id != null ? id : '0'}">
        <input type="hidden" name="opcao" value="${opcao != null ? opcao : 'cadastrar'}">

        <label for="dataAtendimento">Data e Hora:</label><br>
        <input type="datetime-local" id="dataAtendimento" name="dataAtendimento" value="${dataAtendimento}" required><br>

        <label for="descricao">Descrição:</label><br>
        <textarea id="descricao" name="descricao" required>${descricao}</textarea><br>

        <label for="idPaciente">Paciente:</label><br>
        <select id="idPaciente" name="idPaciente" required>
            <c:forEach var="paciente" items="${pacientes}">
                <option value="${paciente.id}" ${paciente.id == idPaciente ? 'selected' : ''}>
                    ${paciente.nome}
                </option>
            </c:forEach>
        </select><br>

        <label for="idFuncionario">Funcionário:</label><br>
        <select id="idFuncionario" name="idFuncionario" required>
            <c:forEach var="func" items="${funcionarios}">
                <option value="${func.id}" ${func.id == idFuncionario ? 'selected' : ''}>
                    ${func.nome}
                </option>
            </c:forEach>
        </select><br><br>

        <button type="submit">Salvar</button>
        <a href="${baseURL}?opcao=cancelar"><button type="button">Cancelar</button></a>
    </form>
    
    <hr>
    
    <h2>Atendimentos Cadastrados</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Data/Hora</th>
                <th>Descrição</th>
                <th>Paciente</th>
                <th>Funcionário</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="atend" items="${atendimentos}">
                <tr>
                    <td>${atend.id}</td>
                    <td><fmt:formatDate value="${atend.dataAtendimento}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
                    <td>${atend.descricao}</td>
                    <td>${atend.paciente.nome}</td>
                    <td>${atend.funcionario.nome}</td>
                    <td>
                        <a href="${baseURL}?opcao=editar&id=${atend.id}">Editar</a>
                        <a href="${baseURL}?opcao=excluir&id=${atend.id}">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
