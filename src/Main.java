import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        BinarySearchTree tree = new BinarySearchTree();
        int op;
        int value;

        do {
            System.out.println("\nÁRVORE BINÁRIA DE PESQUISA");
            System.out.println("---------------------------------------");
            System.out.println("1 | Adicionar nodo");
            System.out.println("2 | Remover nodo");
            System.out.println("3 | Pesquisar nodo");
            System.out.println("4 | Exibir a árvore");
            System.out.println("5 | Mostrar informações");
            System.out.println("6 | Mostrar caminhamentos");
            System.out.println("7 | Soma de valores entre nodos");
            System.out.println("8 | Esvaziar árvore");
            System.out.println("9 | Menor valor");
            System.out.println("10 | Maior valor");
            System.out.println("11 | Quantidade de folhas");
            System.out.println("0 | Sair do programa");
            System.out.println("---------------------------------------");
            System.out.println("Digite a opção desejada: ");

            op = scan.nextInt();

            switch (op) {
                case 1 -> {
                    System.out.println("Informe um valor inteiro:");
                    value = scan.nextInt();
                    tree.add(value);
                }
                case 2 -> {
                    System.out.println("Informe um valor inteiro:");
                    value = scan.nextInt();
                    if (!tree.remove(value))
                        System.out.println("Valor não encontrado!");
                }
                case 3 -> {
                    System.out.println("Informe um valor inteiro:");
                    value = scan.nextInt();
                    if (tree.contains(value) != null)
                        System.out.print("Valor encontrado!");
                    else
                        System.out.println("Valor não encontrado!");
                }
                case 4 -> {
                    tree.printTree();
                }
                case 5 -> {
                    tree.treeInfo();
                }
                case 6 -> {
                    tree.caminhamentos();
                }
                case 7 -> {
                    System.out.println("Digite o valor do nodo de inicio: ");
                    int start = scan.nextInt();
                    System.out.println("Digite o valor do nodo final: ");
                    int end = scan.nextInt();
                    System.out.println("A soma dos valores é: "+tree.sumBetween(tree.getRoot(), start, end));
                }
                case 8 -> {
                    tree.clearTree();
                }
                case 9 -> {
                    System.out.println("O menor valor encontrado na árvore é: "+tree.minNode());
                }
                case 10 -> {
                    System.out.println("O maior valor encontrado na árvore é: "+tree.maxNode());
                }
                case 11 -> {
                    System.out.println("Quantidade de folhas na árvore: "+tree.countLeaves(tree.getRoot()));
                }
            }
        } while (op != 0);

    }
}