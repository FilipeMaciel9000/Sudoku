/***
 * Para configurar o tabuleiro de Sudoku, siga as instruções abaixo (IDE IntelliJ):
 *
 * 1. Clique nos três pontos ao lado do botão de depuração (debug).
 * 2. Selecione a opção "Edit Configurations" (Editar Configurações).
 * 2. Selecione a opção "Edit Configurations" (Editar Configurações).
 * 3. No campo "Program Arguments" (Argumentos do Programa), insira a seguinte argumento:
 *
 * 0,0;4,false 1,0;7,false 2,0;9,true 3,0;5,false 4,0;8,true 5,0;6,true 6,0;2,true 7,0;3,false 8,0;1,false 0,1;1,false 1,1;3,true 2,1;5,false 3,1;4,false 4,1;7,true 5,1;2,false 6,1;8,false 7,1;9,true 8,1;6,true 0,2;2,false 1,2;6,true 2,2;8,false 3,2;9,false 4,2;1,true 5,2;3,false 6,2;7,false 7,2;4,false 8,2;5,true 0,3;5,true 1,3;1,false 2,3;3,true 3,3;7,false 4,3;6,false 5,3;4,false 6,3;9,false 7,3;8,true 8,3;2,false 0,4;8,false 1,4;9,true 2,4;7,false 3,4;1,true 4,4;2,true 5,4;5,true 6,4;3,false 7,4;6,true 8,4;4,false 0,5;6,false 1,5;4,true 2,5;2,false 3,5;3,false 4,5;9,false 5,5;8,false 6,5;1,true 7,5;5,false 8,5;7,true 0,6;7,true 1,6;5,false 2,6;4,false 3,6;2,false 4,6;3,true 5,6;9,false 6,6;6,false 7,6;1,true 8,6;8,false 0,7;9,true 1,7;8,true 2,7;1,false 3,7;6,false 4,7;4,true 5,7;7,false 6,7;5,false 7,7;2,true 8,7;3,false 0,8;3,false 1,8;2,false 2,8;6,true 3,8;8,true 4,8;5,true 5,8;1,false 6,8;4,true 7,8;7,false 8,8;9,false
 *
 * Certifique-se de que o argumento esteja corretamente formatada e que os valores para cada célula sejam inseridos de acordo com o formato:
 * "x,y;valor,fixo", onde:
 *  - x,y representam as coordenadas (de 0 a 8).
 *  - valor é o número a ser colocado na célula (de 1 a 9), ou 0 se estiver vazio.
 *  - fixo indica se a célula está fixada (true) ou não (false).
 *
 * Após inserir os argumentos, clique em "OK" para salvar as configurações e iniciar a execução do programa.
 */

import java.util.Scanner;

public class Sudoku {

    // Classe para representar cada célula no tabuleiro
    static class Cell {
        int value;
        boolean fixed;

        Cell(int value, boolean fixed) {
            this.value = value;
            this.fixed = fixed;
        }

        @Override
        public String toString() {
            return value == 0 ? "." : Integer.toString(value);
        }
    }

    // Classe que representa o tabuleiro do Sudoku
    static class Board {
        Cell[][] grid = new Cell[9][9];

        // Construtor do tabuleiro, inicializa as células com valor 0 e não fixadas
        Board() {
            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < 9; x++) {
                    grid[y][x] = new Cell(0, false);
                }
            }
        }

        // Carrega os dados a partir da string de argumentos
        void loadFromArgs(String argString) {
            if (argString == null || argString.trim().isEmpty()) {
                System.out.println("❌ Nenhum argumento foi fornecido!");
                return;
            }

            String[] entries = argString.split(" ");
            for (String entry : entries) {
                try {
                    String[] parts = entry.split(";");
                    String[] pos = parts[0].split(",");
                    int x = Integer.parseInt(pos[0].trim());
                    int y = Integer.parseInt(pos[1].trim());

                    String[] data = parts[1].split(",");
                    int value = Integer.parseInt(data[0].trim());
                    boolean fixed = Boolean.parseBoolean(data[1].trim());

                    grid[y][x] = new Cell(value, fixed);
                } catch (Exception e) {
                    System.out.println("❌ Erro ao processar o argumento: " + entry);
                    e.printStackTrace();
                }
            }
        }

        // Exibe o tabuleiro no formato de Sudoku
        void display() {
            System.out.println("    0 1 2   3 4 5   6 7 8");
            System.out.println("  +-------+-------+-------+");
            for (int y = 0; y < 9; y++) {
                System.out.print(y + " | ");
                for (int x = 0; x < 9; x++) {
                    System.out.print(grid[y][x] + " ");
                    if ((x + 1) % 3 == 0) System.out.print("| ");
                }
                System.out.println();
                if ((y + 1) % 3 == 0) System.out.println("  +-------+-------+-------+");
            }
        }

        // Verifica se o movimento é válido
        void setValue(int x, int y, int value) {
            if (grid[y][x].fixed) {
                System.out.println("❌ Célula fixa! Não pode ser alterada.");
            } else if (!isValidMove(x, y, value)) {
                System.out.println("❌ Movimento inválido! O número já existe na linha, coluna ou bloco.");
            } else {
                grid[y][x].value = value;
            }
        }

        // Valida se o movimento segue as regras do Sudoku
        private boolean isValidMove(int x, int y, int value) {
            // Verifica linha
            for (int col = 0; col < 9; col++) {
                if (grid[y][col].value == value) return false;
            }

            // Verifica coluna
            for (int row = 0; row < 9; row++) {
                if (grid[row][x].value == value) return false;
            }

            // Verifica bloco 3x3
            int startRow = (y / 3) * 3;
            int startCol = (x / 3) * 3;
            for (int row = startRow; row < startRow + 3; row++) {
                for (int col = startCol; col < startCol + 3; col++) {
                    if (grid[row][col].value == value) return false;
                }
            }

            return true;
        }

        // Verifica se o Sudoku está completo
        boolean isComplete() {
            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < 9; x++) {
                    if (grid[y][x].value == 0) return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        // Verifica se os argumentos foram passados corretamente
        if (args.length == 0) {
            System.out.println("❌ Nenhum argumento foi fornecido para o Sudoku.");
            return;
        }

        // Junta todos os argumentos passados na linha de comando
        String input = String.join(" ", args);
        Board board = new Board();
        board.loadFromArgs(input);

        Scanner scanner = new Scanner(System.in);

        // Loop até o Sudoku estar completo
        while (!board.isComplete()) {
            board.display();
            System.out.print("Digite coordenadas e valor (x y valor): ");
            // Validação da entrada
            if (!scanner.hasNextInt()) {
                System.out.println("❌ Entrada inválida! Por favor, digite três números inteiros.");
                scanner.nextLine();  // Limpa o buffer
                continue;
            }
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int value = scanner.nextInt();

            if (x >= 0 && x < 9 && y >= 0 && y < 9 && value >= 1 && value <= 9) {
                board.setValue(x, y, value);
            } else {
                System.out.println("❌ Entrada inválida! Certifique-se de que x, y estão entre 0 e 8 e o valor entre 1 e 9.");
            }
        }

        // Exibe o tabuleiro completo
        board.display();
        System.out.println("🎉 Sudoku completo!");
        scanner.close();
    }
}
