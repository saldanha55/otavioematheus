<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 class="tit">Cadastro Medicamento</h1>
        <div class="cadastro">
            <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/MedicamentoControlador">
                <input type="hidden" name="opcao" value="${opcao}">
                <input type="hidden" name="id" value="${id}">
                <p> <label>Nome:</label>
                    <input type="text" name="nome" value="${nome}" size="40" required class="form"/>
                </p>
                <p> <label>DESCRIÇÃO:</label>
                    <input type="text" name="desc" value="${desc}" size="40" class="form"/>
                </p>
                <p> <label>QUANTIDADE:</label>
                    <input type="number" name="quantidade" max="999999999" oninput="this.value = this.value.slice(0, 9)" value="${quantidade}" size="40" required class="form"/>
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

                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/MedicamentoControlador">
                    <input type="hidden" name="opcao" value="cancelar">
                    <td>
                        <input class="butaoCancelar" type="submit" name="Cancelar" value="Cancelar" />
                    </td>
                </form>
        </div>

        <div class="tabela">
            <table border="1">
                <thead>   
                    <c:if test="${not empty medicamentos}">
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>DESCRIÇÃO</th>
                            <th>QUANTIDADE</th>
                            <th>PREÇO</th>
                            <th>Alterar</th>
                            <th>Excluir</th>
                        </tr>
                    </c:if>   
                <thead>

                    <c:forEach var="medicamento" items="${medicamentos}">
                    <tbody class="tbody">
                        <tr>
                            <td class="td">${medicamento.id}</td>
                            <td class="td">${medicamento.nome}</td>
                            <td class="td">${medicamento.desc}</td>
                            <td class="td">${medicamento.quantidade}</td>
                            <td class="td">${medicamento.preco}</td>
                            <td class="td">
                                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/MedicamentoControlador">
                                    <input type="hidden" name="id" value="${medicamento.id}">
                                    <input type="hidden" name="nome" value="${medicamento.nome}">
                                    <input type="hidden" name="desc" value="${medicamento.desc}">
                                    <input type="hidden" name="quantidade" value="${medicamento.quantidade}">
                                    <input type="hidden" name="preco" value="${medicamento.preco}">
                                    <input type="hidden" name="opcao" value="editar">
                                    <button class="butaoEditar" type="submit">Editar</button>
                                </form>
                            </td>
                            <td class="td">
                                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/MedicamentoControlador">
                                    <input type="hidden" name="id" value="${medicamento.id}">
                                    <input type="hidden" name="nome" value="${medicamento.nome}">
                                    <input type="hidden" name="desc" value="${medicamento.desc}">
                                    <input type="hidden" name="quantidade" value="${medicamento.quantidade}">
                                    <input type="hidden" name="preco" value="${medicamento.preco}">
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
