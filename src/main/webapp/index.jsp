<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestão Hospitalar</title>
    <!-- Tailwind CSS -->
    <script src="https://cdn.tailwindcss.com"></script>
    <!-- Font Awesome para Ícones -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <!-- Fonte Inter do Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <style>
        /* Define a fonte Inter como padrão */
        body {
            font-family: 'Inter', sans-serif;
        }
        /* Efeito de hover sutil nos cards */
        .dashboard-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 15px -3px rgb(0 0 0 / 0.1), 0 4px 6px -4px rgb(0 0 0 / 0.1);
        }
    </style>
</head>
<body class="bg-gray-100">

    <!-- 
      NOTA: Os caminhos para os controladores abaixo são baseados na estrutura do seu projeto.
      Como esta é uma página HTML estática, não podemos usar a variável "URL_BASE" do JSP.
      O caminho completo é formado por: [contexto_da_app] + [base_path_do_servlet] + [nome_do_controlador]
      Ex: /provaJava-1.0-SNAPSHOT/com/mycompany/provajava/controlador/PacienteControlador
      Ajuste o caminho do contexto se o nome do seu .war for diferente.
    -->
    <c:set var="contexto" value="/provaJava-1.0-SNAPSHOT" />
    <c:set var="basePath" value="/com/mycompany/provajava/controlador" />

    <div class="flex flex-col h-screen">
        <!-- Cabeçalho -->
        <header class="bg-white shadow-md w-full p-4 flex justify-between items-center">
            <h1 class="text-2xl font-bold text-gray-800">
                <i class="fas fa-hospital-user text-blue-600"></i>
                Gestão Hospitalar
            </h1>
            <p class="text-sm text-gray-500">Bem-vindo ao sistema!</p>
        </header>

        <!-- Conteúdo Principal -->
        <main class="flex-grow p-6 md:p-8 lg:p-10">
            <div class="container mx-auto">
                <h2 class="text-3xl font-semibold text-gray-700 mb-6">Painel de Controle</h2>
                
                <!-- Grade de Navegação Principal -->
                <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">

                    <!-- Card de Pacientes -->
                    <a href="${contexto}${basePath}/PacienteControlador" class="dashboard-card bg-white p-6 rounded-lg shadow-sm transition-transform duration-300">
                        <div class="flex items-center">
                            <i class="fas fa-users fa-2x text-blue-500"></i>
                            <div class="ml-4">
                                <h3 class="text-lg font-semibold text-gray-800">Pacientes</h3>
                                <p class="text-gray-500 text-sm">Gerenciar cadastros</p>
                            </div>
                        </div>
                    </a>

                    <!-- Card de Funcionários -->
                    <a href="${contexto}${basePath}/FuncionarioControlador" class="dashboard-card bg-white p-6 rounded-lg shadow-sm transition-transform duration-300">
                        <div class="flex items-center">
                            <i class="fas fa-user-doctor fa-2x text-green-500"></i>
                            <div class="ml-4">
                                <h3 class="text-lg font-semibold text-gray-800">Funcionários</h3>
                                <p class="text-gray-500 text-sm">Gerenciar equipe</p>
                            </div>
                        </div>
                    </a>

                    <!-- Card de Atendimentos -->
                    <a href="${contexto}${basePath}/AtendimentoControlador" class="dashboard-card bg-white p-6 rounded-lg shadow-sm transition-transform duration-300">
                        <div class="flex items-center">
                            <i class="fas fa-file-medical fa-2x text-yellow-500"></i>
                            <div class="ml-4">
                                <h3 class="text-lg font-semibold text-gray-800">Atendimentos</h3>
                                <p class="text-gray-500 text-sm">Registrar consultas</p>
                            </div>
                        </div>
                    </a>
                    
                    <!-- Card de Internações -->
                    <a href="${contexto}${basePath}/InternacaoControlador" class="dashboard-card bg-white p-6 rounded-lg shadow-sm transition-transform duration-300">
                        <div class="flex items-center">
                            <i class="fas fa-bed-pulse fa-2x text-red-500"></i>
                            <div class="ml-4">
                                <h3 class="text-lg font-semibold text-gray-800">Internações</h3>
                                <p class="text-gray-500 text-sm">Gerenciar internações</p>
                            </div>
                        </div>
                    </a>

                    <!-- Card de Cadastros Auxiliares -->
                    <div class="sm:col-span-2 md:col-span-3 lg:col-span-4 mt-4 p-6 bg-white rounded-lg shadow-sm">
                        <h3 class="text-xl font-semibold text-gray-800 mb-4">Cadastros Auxiliares</h3>
                        <div class="grid grid-cols-2 sm:grid-cols-2 md:grid-cols-4 gap-4">
                            <a href="${contexto}${basePath}/SetorControlador" class="flex items-center p-3 text-gray-600 hover:bg-gray-100 rounded-md transition-colors">
                                <i class="fas fa-building fa-fw mr-2 text-indigo-500"></i> Setores
                            </a>
                             <a href="${contexto}${basePath}/QuartoControlador" class="flex items-center p-3 text-gray-600 hover:bg-gray-100 rounded-md transition-colors">
                                <i class="fas fa-door-open fa-fw mr-2 text-indigo-500"></i> Quartos
                            </a>
                            <a href="${contexto}${basePath}/ProcedimentoControlador" class="flex items-center p-3 text-gray-600 hover:bg-gray-100 rounded-md transition-colors">
                                <i class="fas fa-stethoscope fa-fw mr-2 text-indigo-500"></i> Procedimentos
                            </a>
                            <a href="${contexto}${basePath}/MedicamentoControlador" class="flex items-center p-3 text-gray-600 hover:bg-gray-100 rounded-md transition-colors">
                                <i class="fas fa-pills fa-fw mr-2 text-indigo-500"></i> Medicamentos
                            </a>
                        </div>
                    </div>

                </div>
            </div>
        </main>

        <!-- Rodapé -->
        <footer class="text-center p-4 bg-white text-sm text-gray-500 border-t">
            <p>&copy; 2024 Sistema de Gestão Hospitalar. Todos os direitos reservados.</p>
        </footer>
    </div>
</body>
</html>
