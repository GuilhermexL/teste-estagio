<p align="center">
  <img src="images/logo.png" alt="IntuitiveCare">
</p>

Este repositório contém a resolução do teste de estágio para a vaga de [Nome da Vaga]. O objetivo do teste é implementar soluções para web scraping, transformação de dados, manipulação de banco de dados e desenvolvimento de uma API.

### Tecnologias Utilizadas

- Java (para Web Scraping e Transformação de Dados);

- Python (FastAPI) (para desenvolvimento da API);

- PostgreSQL/MySQL (para manipulação de banco de dados);

- Postman (para testar a API);

### Funcionalidades Pedidas para o Teste

1. Web Scraping (Java)

    - Acessa o site da ANS e baixa os PDFs dos Anexos I e II.

    - Compacta os arquivos baixados em .zip.

2. Transformação de Dados (Java)

    - Extrai a tabela Rol de Procedimentos e Eventos em Saúde do Anexo I.

    - Salva os dados em um arquivo .csv.

    - Substitui abreviações por descrições completas.

    - Compacta o CSV gerado.

3. Banco de Dados

    - Cria tabelas para armazenar os dados extraídos.

    - Importa arquivos CSV para o banco de dados.

    - Executa consultas analíticas para encontrar as operadoras com maiores despesas.

4. API (FastAPI)

    - Exponibiliza uma rota para busca textual na lista de operadoras de saúde.

    - Retorna os resultados mais relevantes.

    - Testes e Validação

    - Testes de API podem ser realizados via Postman.

    - Scripts Java foram testados localmente.

    - Banco de dados foi validado com queries SQL.

### Autor

- Guilherme Santos:
[Linkedin](https://www.linkedin.com/in/guilhermee-santos/)
[GitHub](https://github.com/GuilhermexL)