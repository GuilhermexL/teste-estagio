# Transformação de Dados

## Descrição
Este projeto foi desenvolvido como um teste técnico para processar arquivos PDF da ANS (Agência Nacional de Saúde Suplementar). Atualmente, o sistema realiza o download do arquivo PDF e sua compactação em formato ZIP, mas **não consegue extrair os dados do PDF para CSV** conforme o objetivo original.

## Funcionalidades Implementadas
✅ **Download do PDF**:
- Baixa automaticamente o Anexo I do Rol de Procedimentos da ANS
- Salva na pasta `/downloads` que cria após a execução

✅ **Compactação para ZIP**:
- Gera arquivo `Teste_Guilherme.zip` na pasta `/output`
- Mantém a estrutura original do PDF

❌ **Conversão para CSV**:
- A extração dos dados tabulares do PDF não foi implementada com sucesso
- O PDF mantém um formato complexo que dificulta a extração

## Pré-requisitos
- Java JDK 11+
- Apache Maven
- Conexão com internet para download do arquivo

## Limitações Conhecidas
- A extração de dados tabulares do PDF não está funcionando
- O PDF da ANS possui layout complexo com:
    - Tabelas multi-página
    - Formatação irregular
    - Células mescladas
- A solução atual apenas compacta o PDF original