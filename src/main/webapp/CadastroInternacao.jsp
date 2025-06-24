<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cadastro de Internações</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/estilo/estilo.css">
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
    
    <h1>Cadastro de Internações</h1>
    
    <c:if test="${mensagem != null}">
        <p style="color: blue;">${mensagem}</p>
    </c:if>

    <form action="${baseURL}" method="GET">
        <input type="hidden" name="id" value="${id != null ? id : '0'}">
        <input type="hidden" name="opcao" value="${opcao != null ? opcao : 'cadastrar'}">

        <label for="dataEntrada">Data de Entrada:</label><br>
        <input type="datetime-local" id="dataEntrada" name="dataEntrada" value="${dataEntrada}" required><br>

        <label for="dataSaida">Data de Saída:</label><br>
        <input type="datetime-local" id="dataSaida" name="dataSaida" value="${dataSaida}"><br>

        <label for="idAtendimento">Atendimento (Paciente):</label><br>
        <select id="idAtendimento" name="idAtendimento" required>
            <c:forEach var="atend" items="${atendimentos}">
                <option value="${atend.id}" ${atend.id == idAtendimento ? 'selected' : ''}>
                    Atendimento #${atend.id} - ${atend.paciente.nome}
                </option>
            </c:forEach>
        </select><br>

        <label for="idQuarto">Quarto:</label><br>
        <select id="idQuarto" name="idQuarto" required>
            <c:forEach var="quarto" items="${quartos}">
                <option value="${quarto.id}" ${quarto.id == idQuarto ? 'selected' : ''}>
                    ${quarto.nome}
                </option>
            </c:forEach>
        </select><br><br>

        <button type="submit">Salvar</button>
        <a href="${baseURL}?opcao=cancelar"><button type="button">Cancelar</button></a>
    </form>
    
    <hr>
    
    <h2>Internações Cadastradas</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Data Entrada</th>
                <th>Data Saída</th>
                <th>Paciente</th>
                <th>Quarto</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="inter" items="${internacoes}">
                <tr>
                    <td>${inter.id}</td>
                    <td><fmt:formatDate value="${inter.dataEntrada}" pattern="dd/MM/yyyy HH:mm" /></td>
                    <td><c:if test="${inter.dataSaida != null}"><fmt:formatDate value="${inter.dataSaida}" pattern="dd/MM/yyyy HH:mm" /></c:if></td>
                    <td>${inter.atendimento.paciente.nome}</td>
                    <td>${inter.quarto.nome}</td>
                    <td>
                        <a href="${baseURL}?opcao=editar&id=${inter.id}">Editar</a>
                        <a href="${baseURL}?opcao=excluir&id=${inter.id}">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
