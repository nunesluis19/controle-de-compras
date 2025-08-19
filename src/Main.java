import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Scanner para leitura de entradas do usuário

        ArrayList<Produtos> listaProdutos = new ArrayList<>();
        // Lista que vai armazenar todos os produtos comprados

        int saida = 1;
        // Variável de controle do loop principal (1 = continuar, 0 = parar)

        // Lê o limite do cartão apenas uma vez no início
        System.out.println("Digite o limite do cartão:");
        double saldoDoCartao = scanner.nextDouble();
        scanner.nextLine(); // limpa quebra de linha deixada pelo nextDouble

        // Loop principal, continua enquanto saida for 1
        while (saida == 1) {
            Produtos produtos = new Produtos();
            // Cria um novo objeto Produto a cada iteração

            // Lê nome do produto
            System.out.println("Insira a descrição da compra: ");
            produtos.setNomeProduto(scanner.nextLine());

            // Validação do valor da compra
            double valor;
            do {
                System.out.println("Digite o valor da compra: ");

                while (!scanner.hasNextDouble()) {
                    System.out.println("Valor inválido. Digite um número positivo.");
                    scanner.next(); // descarta entrada inválida
                }

                valor = scanner.nextDouble();
                scanner.nextLine(); // limpa buffer

                if (valor <= 0) {
                    System.out.println("O valor da compra deve ser maior que zero.");
                }
            } while (valor <= 0);

            produtos.setValorProduto(valor);

            // Verifica se há saldo suficiente no cartão
            if (produtos.getValorProduto() <= saldoDoCartao) {
                saldoDoCartao -= produtos.getValorProduto(); // atualiza saldo
                listaProdutos.add(produtos); // adiciona produto na lista
                System.out.println("COMPRA REALIZADA, limite disponível: R$" + saldoDoCartao);

                // Validação da entrada do usuário (somente 0 ou 1)
                do {
                    System.out.println("Você deseja realizar outra compra? (digite 1 para continuar ou 0 para finalizar)");

                    while (!scanner.hasNextInt()) { // impede que letras/símbolos sejam aceitos
                        System.out.println("Opção inválida. Digite apenas 1 ou 0.");
                        scanner.next(); // descarta entrada inválida
                    }

                    saida = scanner.nextInt();
                    scanner.nextLine(); // limpar o buffer

                    if (saida != 0 && saida != 1) { // verifica se é diferente de 0 e 1
                        System.out.println("Opção inválida. Digite apenas 1 para continuar ou 0 para finalizar.");
                    }

                } while (saida != 0 && saida != 1);

            } else {
                // Caso não tenha saldo suficiente, exibe a lista e finaliza
                System.out.println("Limite insuficiente");
                System.out.println("==== LISTA DE COMPRAS ====");
                for (Produtos p : listaProdutos) {
                    System.out.println(p);
                }
                break; // encerra o loop
            }

            // Caso o usuário escolha finalizar (saida == 0)
            if (saida == 0) {
                // Ordena a lista por valor do produto (decrescente)
                Collections.sort(listaProdutos, Comparator.comparingDouble(Produtos::getValorProduto).reversed());

                // Exibe a lista ordenada
                System.out.println("==== LISTA DE COMPRAS ====");
                for (Produtos p : listaProdutos) {
                    System.out.println(p);
                }
            }
        }
    }
}
