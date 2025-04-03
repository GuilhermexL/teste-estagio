<template>
  <!-- Área geral - O que aparece na tela -->
  <div class="app-container">
    <!-- Título da Barra -->
    <h1 class="app-title">Busca de Operadoras de Saúde</h1>
    
    <!-- Campo de busca -->
    <div class="search-box">
      <input
        type="text"
        v-model="termoBusca"
        @input="buscarOperadoras"
        placeholder="Digite nome, cidade ou registro..."
        class="search-input"
      />
      <span v-if="carregando" class="loading-text">Buscando...</span>
    </div>

    <!-- Mensagem quando não tem resultados -->
    <div v-if="termoBusca && !carregando && resultados.length === 0" class="no-results">
      Nenhuma operadora encontrada para "{{ termoBusca }}"
    </div>

    <!-- Lista de resultados -->
    <div v-else class="results-container">
      <div v-for="operadora in resultados" :key="operadora.Registro_ANS" class="card">

        <!-- Nome Fantasia ou Razão Social -->
        <h2 class="card-title">
          {{ operadora.Nome_Fantasia || operadora.Razao_Social }}
        </h2>
        
        <!-- Informações das Operadoras -->
        <div class="card-details">
          <p><strong>Registro ANS:</strong> {{ operadora.Registro_ANS }}</p>
          <p><strong>CNPJ:</strong> {{ formatarCNPJ(operadora.CNPJ) }}</p>
          <p><strong>Modalidade:</strong> {{ operadora.Modalidade }}</p>
          <p>
            <strong>Localização:</strong> 
            {{ operadora.Cidade }} <span v-if="operadora.UF">/ {{ operadora.UF }}</span>
          </p>
          <p v-if="operadora.Telefone">
            <strong>Contato:</strong> 
            <span v-if="operadora.DDD">({{ operadora.DDD }}) </span>
            {{ formatarTelefone(operadora.Telefone) }}
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

// Axios para fazer requisições HTTP
import axios from 'axios';

export default {
  data() {
    return {
      termoBusca: '',
      resultados: [],
      carregando: false
    };
  },

  // Métodos do componente
  methods: {
    async buscarOperadoras() {
      // Se o campo estiver vazio, limpa os resultados
      if (!this.termoBusca.trim()) {
        this.resultados = [];
        return;
      }

      this.carregando = true;
      
      try {
        // Fazendo requisição para o backend
        const response = await axios.get('http://localhost:5000/buscar', {
          params: { termo: this.termoBusca }
        });
        
        // Atualiza os resultados com a resposta
        this.resultados = response.data;
      } catch (error) {
        console.error('Erro ao buscar operadoras:', error);
        alert('Ocorreu um erro na busca. Veja o console para detalhes.');
      } finally {
        this.carregando = false;
      }
    },

    formatarCNPJ(cnpj) {
      if (!cnpj) return 'Não informado';
      // Remove caracteres não numéricos
      const numeros = cnpj.toString().replace(/\D/g, '');
      // Aplica a formatação
      return numeros.replace(
        /^(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})$/,
        '$1.$2.$3/$4-$5'
      );
    },

    formatarTelefone(telefone) {
      if (!telefone) return '';
      const num = telefone.toString().replace(/\D/g, '');
      return num.length === 8 
        ? num.replace(/^(\d{4})(\d{4})$/, '$1-$2') 
        : num.replace(/^(\d{5})(\d{4})$/, '$1-$2');
    }
  }
};
</script>

<style>

/* Página completa */
.app-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  font-family: 'Arial', sans-serif;
}

.app-title {
  color: #2c3e50;
  text-align: center;
  margin-bottom: 30px;
}

/* Buscar */
.search-box {
  margin-bottom: 20px;
  position: relative;
}

.search-input {
  width: 100%;
  padding: 12px 15px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-sizing: border-box;
}

.loading-text {
  position: absolute;
  right: 15px;
  top: 50%;
  transform: translateY(-50%);
  color: #666;
  font-size: 14px;
}

/* Cards de resultado */
.results-container {
  margin-top: 20px;
}

.card {
  background: #f9f9f9;
  color: #000;
  border-left: 4px solid #42b983;
  padding: 20px;
  margin-bottom: 15px;
  border-radius: 0 4px 4px 0;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.card-title {
  margin-top: 0;
  color: #2c3e50;
  border-bottom: 1px solid #eee;
  padding-bottom: 10px;
}

.card-details p {
  margin: 8px 0;
  line-height: 1.5;
}

/* Mensagem de vazio */
.no-results {
  text-align: center;
  padding: 20px;
  color: #666;
  font-size: 18px;
  background: #f5f5f5;
  border-radius: 4px;
}
</style>