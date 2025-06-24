<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cadastro de Funcionários</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/estilo/estilo.css">
</head>
<body>
    <%-- Monta a URL base para todos os links do controlador --%>
    <c:set var="baseURL" value="${pageContext.request.contextPath}${applicationScope.URL_BASE}/FuncionarioControlador" />

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
            <li><a href="${baseURL}">Internações</a></li>
        </ul>
    </nav>
    
    <h1>Cadastro de Funcionários</h1>
    
    <c:if test="${mensagem != null}">
        <p style="color: blue;">${mensagem}</p>
    </c:if>

    <%-- CORREÇÃO: Usando a URL base no 'action' do formulário --%>
    <form action="${baseURL}" method="GET">
        <input type="hidden" name="id" value="${id != null ? id : '0'}">
        <input type="hidden" name="opcao" value="${opcao != null ? opcao : 'cadastrar'}">

        <label for="nome">Nome:</label><br>
        <input type="text" id="nome" name="nome" value="${nome}" required><br>

        <label for="cpf">CPF:</label><br>
        <input type="text" id="cpf" name="cpf" value="${cpf}" required><br>

        <label for="cargo">Cargo:</label><br>
        <input type="text" id="cargo" name="cargo" value="${cargo}" required><br>

        <label for="salario">Salário:</label><br>
        <input type="number" step="0.01" id="salario" name="salario" value="${salario}" required><br>

        <label for="idSetor">Setor:</label><br>
        <select id="idSetor" name="idSetor" required>
            <c:forEach var="setor" items="${setores}">
                <option value="${setor.id}" ${setor.id == idSetor ? 'selected' : ''}>
                    ${setor.nome}
                </option>
            </c:forEach>
        </select><br><br>

        <button type="submit">Salvar</button>
        <%-- CORREÇÃO: Usando a URL base no link do botão Cancelar --%>
        <a href="${baseURL}?opcao=cancelar">
            <button type="button">Cancelar</button>
        </a>
    </form>
    
    <hr>
    
    <h2>Funcionários Cadastrados</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>CPF</th>
                <th>Cargo</th>
                <th>Salário</th>
                <th>Setor</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="func" items="${funcionarios}">
                <tr>
                    <td>${func.id}</td>
                    <td>${func.nome}</td>
                    <td>${func.cpf}</td>
                    <td>${func.cargo}</td>
                    <td>${func.salario}</td>
                    <td>${func.setor.nome}</td>
                    <td>
                        <%-- CORREÇÃO: Usando a URL base nos links de Ações --%>
                        <a href="${baseURL}?opcao=editar&id=${func.id}&nome=${func.nome}&cpf=${func.cpf}&cargo=${func.cargo}&salario=${func.salario}&idSetor=${func.setor.id}">Editar</a>
                        <a href="${baseURL}?opcao=excluir&id=${func.id}&nome=${func.nome}&cpf=${func.cpf}&cargo=${func.cargo}&salario=${func.salario}&idSetor=${func.setor.id}">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
