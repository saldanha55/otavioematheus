<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cadastro de Procedimentos</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/estilo/estilo.css">
</head>
<body>
    <c:set var="baseURL" value="${pageContext.request.contextPath}${applicationScope.URL_BASE}/ProcedimentoControlador" />

    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/PacienteControlador">Pacientes</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/MedicamentoControlador">Medicamentos</a></li>
            <li><a href="${baseURL}">Procedimentos</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/QuartoControlador">Quartos</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/SetorControlador">Setores</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/FuncionarioControlador">Funcionários</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/AtendimentoControlador">Atendimentos</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/InternacaoControlador">Internações</a></li>
        </ul>
    </nav>
    
    <h1>Cadastro de Procedimentos</h1>
    
    <c:if test="${mensagem != null}">
        <p style="color: blue;">${mensagem}</p>
    </c:if>

    <form action="${baseURL}" method="GET">
        <input type="hidden" name="id" value="${id != null ? id : '0'}">
        <input type="hidden" name="opcao" value="${opcao != null ? opcao : 'cadastrar'}">

        <label for="nome">Nome:</label><br>
        <input type="text" id="nome" name="nome" value="${nome}" required><br>

        <label for="desc">Descrição:</label><br>
        <input type="text" id="desc" name="desc" value="${desc}"><br>

        <label for="preco">Preço:</label><br>
        <input type="number" step="0.01" id="preco" name="preco" value="${preco}" required><br><br>

        <button type="submit">Salvar</button>
        <a href="${baseURL}?opcao=cancelar"><button type="button">Cancelar</button></a>
    </form>
    
    <hr>
    
    <h2>Procedimentos Cadastrados</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Descrição</th>
                <th>Preço</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="proc" items="${procedimentos}">
                <tr>
                    <td>${proc.id}</td>
                    <td>${proc.nome}</td>
                    <td>${proc.desc}</td>
                    <td>${proc.preco}</td>
                    <td>
                        <a href="${baseURL}?opcao=editar&id=${proc.id}">Editar</a>
                        <a href="${baseURL}?opcao=excluir&id=${proc.id}">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
