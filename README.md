# Jogo de Sudoku - Terminal em Java

Este é um projeto de implementação de um jogo de **Sudoku** no terminal, desenvolvido em **Java**. O código permite carregar um tabuleiro a partir de argumentos passados na linha de comando, interagir com o tabuleiro, fazer movimentos e validar se o Sudoku está completo ou se as jogadas são válidas.

## Funcionalidades

- **Carregar Tabuleiro**: O tabuleiro é carregado a partir de uma string de argumentos no formato `x,y;valor,fixo`, onde:
  - `x,y`: coordenadas da célula no tabuleiro (0 a 8).
  - `valor`: número a ser inserido (de 1 a 9) ou 0 para células vazias.
  - `fixo`: indica se a célula é fixa (`true`) ou se pode ser alterada (`false`).

- **Exibição do Tabuleiro**: O tabuleiro é exibido no formato tradicional de Sudoku, com divisões visuais a cada 3x3 células.

- **Validação das Jogadas**: O programa valida se o número inserido é permitido conforme as regras do Sudoku: o número não pode se repetir na mesma linha, coluna ou bloco 3x3.

- **Conclusão**: O jogo continua até que todas as células estejam preenchidas corretamente. Quando o Sudoku é completado, uma mensagem de sucesso é exibida.

## Como Executar

Para rodar o projeto, siga as instruções abaixo.

### Passo 1: Configuração do Projeto

1. Clone o repositório para sua máquina local:
   ```bash
   https://github.com/FilipeMaciel9000/Sudoku.git
