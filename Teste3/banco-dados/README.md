# Teste de Banco de Dados  

Este projeto consiste na criação e manipulação de um banco de dados MySQL utilizando dados públicos da Agência Nacional de Saúde Suplementar (ANS). O objetivo era importar informações das operadoras de saúde e suas demonstrações contábeis para realizar consultas analíticas.  

## **1. Arquivos Utilizados**  

Foram utilizados dois conjuntos de dados públicos:  

1. **Demonstrações Contábeis das Operadoras (últimos 2 anos)**

   - Disponível em: [ANS - Demonstrativos Contábeis](https://dadosabertos.ans.gov.br/FTP/PDA/demonstracoes_contabeis/)  

2. **Dados Cadastrais das Operadoras Ativas**  
   - Disponível em: [ANS - Operadoras Ativas](https://dadosabertos.ans.gov.br/FTP/PDA/operadoras_de_plano_de_saude_ativas/)  

O arquivo principal utilizado no projeto foi:  
- `operadoras.csv` (contendo os dados cadastrais das operadoras de planos de saúde)  

## 🛠 **2. Tecnologias Utilizadas**  
- **Banco de Dados:** MySQL 8+  
- **Ferramenta de Gerenciamento:** MySQL Workbench  
- **Linguagem SQL**  

## **3. Passos para Configuração**  

### **3.1 Criando o Banco de Dados e a Tabela**  
Antes de importar os dados, foi criada a estrutura da tabela no MySQL:  

```sql
CREATE DATABASE ans_operadoras;

USE ans_operadoras;

CREATE TABLE operadoras (
    registro_ans VARCHAR(20),
    cnpj VARCHAR(20),
    razao_social VARCHAR(255),
    nome_fantasia VARCHAR(255),
    modalidade VARCHAR(100),
    logradouro VARCHAR(255),
    numero VARCHAR(10),
    complemento VARCHAR(255),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    uf VARCHAR(2),
    cep VARCHAR(10),
    ddd VARCHAR(5),
    telefone VARCHAR(20),
    fax VARCHAR(20),
    endereco_eletronico VARCHAR(255),
    representante VARCHAR(255),
    cargo_representante VARCHAR(100),
    regiao_de_comercializacao VARCHAR(255),
    data_registro_ans DATE
);
```

### **3.2 Importando os Dados do CSV**  
O comando utilizado para importar os dados foi:  

```sql
LOAD DATA LOCAL INFILE 'C:/teste3/banco-dados/operadoras-ativas/operadoras.csv'
INTO TABLE operadoras
FIELDS TERMINATED BY ';'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(registro_ans, cnpj, razao_social, nome_fantasia, modalidade, logradouro, numero, complemento, bairro, cidade, uf, cep, ddd, telefone, fax, endereco_eletronico, representante, cargo_representante, regiao_de_comercializacao, @data_registro_ans)
SET data_registro_ans = STR_TO_DATE(@data_registro_ans, '%Y-%m-%d');
```

### **3.3 Problemas Encontrados e Soluções Tentadas**  
#### **Erro: `LOAD DATA LOCAL INFILE file request rejected due to restrictions on access`**
- O erro ocorre porque o MySQL bloqueia a importação de arquivos locais por padrão.  
- Foi tentado ativar `LOCAL INFILE` com:
  ```sql
  SET GLOBAL local_infile = 1;
  ```
- Mesmo assim, o MySQL Workbench bloqueou a execução.  
- Foi tentado rodar pelo terminal, mas ocorreu o erro **"mysql não é reconhecido como um comando interno"**, indicando que o MySQL não estava no PATH do Windows.  
- A tentativa de adicionar o MySQL ao PATH também não teve sucesso dentro do tempo disponível para o teste.  

---

## **5. Conclusão**
O projeto buscava analisar os dados financeiros das operadoras de saúde a partir de fontes públicas. No entanto, a importação dos arquivos CSV encontrou barreiras técnicas no MySQL Workbench, impedindo a conclusão dentro do tempo disponível.  

Apesar das dificuldades, o projeto serviu como aprendizado sobre:  
- Estruturação de tabelas no MySQL
- Uso de `LOAD DATA LOCAL INFILE` para importar dados 
- Configuração de permissões no MySQL para importação de arquivos
- Análise de dados usando SQL para consultas financeiras  