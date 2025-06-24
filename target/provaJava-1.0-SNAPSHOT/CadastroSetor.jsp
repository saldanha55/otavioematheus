<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cadastro de Setores</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/estilo/estilo.css">
</head>
<body>
    <c:set var="baseURL" value="${pageContext.request.contextPath}${applicationScope.URL_BASE}/SetorControlador" />

    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/PacienteControlador">Pacientes</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/MedicamentoControlador">Medicamentos</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/ProcedimentoControlador">Procedimentos</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/QuartoControlador">Quartos</a></li>
            <li><a href="${baseURL}">Setores</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/FuncionarioControlador">Funcionários</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/AtendimentoControlador">Atendimentos</a></li>
            <li><a href="${pageContext.request.contextPath}${applicationScope.URL_BASE}/InternacaoControlador">Internações</a></li>
        </ul>
    </nav>
    
    <h1>Cadastro de Setores</h1>
    
    <c:if test="${mensagem != null}">
        <p style="color: blue;">${mensagem}</p>
    </c:if>

    <form action="${baseURL}" method="GET">
        <input type="hidden" name="id" value="${id != null ? id : '0'}">
        <input type="hidden" name="opcao" value="${opcao != null ? opcao : 'cadastrar'}">

        <label for="nome">Nome:</label><br>
        <input type="text" id="nome" name="nome" value="${nome}" required><br>

        <label for="ref">Referência:</label><br>
        <input type="text" id="ref" name="ref" value="${ref}"><br><br>

        <button type="submit">Salvar</button>
        <a href="${baseURL}?opcao=cancelar"><button type="button">Cancelar</button></a>
    </form>
    
    <hr>
    
    <h2>Setores Cadastrados</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Referência</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="setor" items="${setors}">
                <tr>
                    <td>${setor.id}</td>
                    <td>${setor.nome}</td>
                    <td>${setor.ref}</td>
                    <td>
                        <a href="${baseURL}?opcao=editar&id=${setor.id}">Editar</a>
                        <a href="${baseURL}?opcao=excluir&id=${setor.id}">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
