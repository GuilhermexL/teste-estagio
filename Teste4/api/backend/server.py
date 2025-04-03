from flask import Flask, request, jsonify
from flask_cors import CORS  # Permite comunicação com o frontend
import pandas as pd

# 1. Cria a aplicação Flask
app = Flask(__name__)
CORS(app)  # Importante para o frontend acessar o backend

# 2. Carrega os dados do CSV
try:
    df = pd.read_csv('./operadoras.csv', sep=';', encoding='utf-8')
    print("Dados carregados com sucesso!")
except Exception as e:
    print("Erro ao carregar CSV:", e)
    df = pd.DataFrame()  # Cria um DataFrame vazio se der erro

# 3. Rota para busca
@app.route('/buscar', methods=['GET'])
def buscar():
    termo = request.args.get('termo', '').lower()
    
    if not termo:
        return jsonify({"erro": "Digite um termo para buscar"}), 400
    
    try:
        # Filtrar e selecionar apenas colunas relevantes
        resultados = df[
            df['Razao_Social'].str.lower().str.contains(termo, na=False) |
            df['Nome_Fantasia'].str.lower().str.contains(termo, na=False)
        ][[  # Seleciona estas colunas:
            'Registro_ANS', 'CNPJ', 'Razao_Social', 
            'Nome_Fantasia', 'Modalidade', 'Cidade', 'UF', 'Telefone', 'DDD'
        ]].head(20).to_dict('records')
        
        return jsonify(resultados)
    except Exception as e:
        return jsonify({"erro": str(e)}), 500

# 4. Inicia o servidor
if __name__ == '__main__':
    app.run(debug=True, port=5000)