# **Teste de API**

Um sistema completo para consulta de operadoras de saúde, com **backend em Python (Flask)** e **frontend em Vue.js**, que permite buscar informações em um arquivo CSV (operadoras.csv) e visualizar resultados de forma intuitiva.

---

##  **Tecnologias Utilizadas**

- Backend
    - Python 3
    - Flask (microframework)
    - Flask-CORS (segurança)
- Frontend
    - Vue.js 3
    - Axios (HTTP requests)
    - ES6 JavaScript
- Ferramentas
    - Postman (testes de API)
    - Pandas (manipulação CSV)
    - Visual Studio Code

---

## **Como Executar**

### **Backend (Python)**
1. Instale as dependências:
   ```bash
   pip install flask flask-cors pandas
   ```
2. Execute o servidor:
   ```bash
   python server.py
   ```
   → A API estará disponível em: `http://localhost:5000`

### **Frontend (Vue.js)**
1. Instale as dependências:
   ```bash
   npm install
   ```
2. Inicie o aplicativo:
   ```bash
   npm run dev
   ```
   → Acesse no navegador: `http://localhost:5173`

---

## **Documentação da API**
### **Rotas Disponíveis**
| **Endpoint**          | **Método** | **Parâmetro** | **Descrição**                     |
|-----------------------|------------|---------------|-----------------------------------|
| `/buscar`             | GET        | `termo`       | Busca operadoras por termo        |
| `/debug-dados`        | GET        | -             | Mostra metadados do CSV           |

### **Exemplo de Requisição**
```bash
curl "http://localhost:5000/buscar?termo=saude"
```

### **Resposta de Sucesso**
```json
[
  {
    "Registro_ANS": "421421",
    "CNPJ": "27452545000195",
    "Razao_Social": "2CARE OPERADORA DE SAÚDE LTDA.",
    "Cidade": "Campinas",
    "UF": "SP"
  }
]
```

---

## **Aprendizados do Projeto**
1. **Integração frontend/backend** com Vue.js e Flask
2. **Manipulação de CSV** em Python com Pandas
3. **Tratamento de erros** em APIs REST
4. **Boas práticas** de organização de código