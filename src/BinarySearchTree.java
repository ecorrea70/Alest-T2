/**
 * CLASSE BinarySearchTree
 * Trabalhando com árvore binária de pesquisa
 * */

class BinarySearchTree {

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void add(Integer v) {

        Node prev, current;

        // cria um novo nodo
        Node node = new Node();

        // atribui o valor recebido ao item de dados do nodo
        node.element = v;
        node.right = null;
        node.left = null;

        // se a raiz está nula, a árvore está vazia
        if (root == null) {
            root = node;
        } else {
            current = root;
            // percorre a árvore
            while(true) {
                prev = current;
                // ir para esquerda
                if (v <= current.element) {
                    current = current.left;
                    if (current == null) {
                        // insere na subárvore da esquerda
                        prev.left = node;
                        return;
                    }
                }
                // ir para direita
                else {
                    current = current.right;
                    if (current == null) {
                        // insere na subárvore da direita
                        prev.right = node;
                        return;
                    }
                }
            }
        }
    }

    public Node contains(Integer v) {
        // se arvore vazia
        if (root == null)
            return null;

        // começa a procurar desde raiz
        Node current = root;
        // enquanto nao encontrou
        while (current.element != v) {
            if (v < current.element)
                current = current.left; // caminha para esquerda
            else
                current = current.right; // caminha para direita

            // encontrou uma folha -> sai
            if (current == null)
                return null;
        }

        // terminou o laço while e chegou aqui é pq encontrou item
        return current;
    }


    public boolean remove(Integer v) {
        // se arvore vazia
        if (root == null)
            return false;

        Node current = root;
        Node father = root;
        boolean child_left = true;

        // buscando o valor
        while(current.element != v) {
            // enquanto nao encontrou
            father = current;
            // caminha para esquerda
            if (v < current.element) {
                current = current.left;
                // é filho a esquerda? sim
                child_left = true;
            }
            // caminha para direita
            else {
                current = current.right;
                // é filho a esquerda? NAO
                child_left = false;
            }
            // encontrou uma folha -> sai
            if (current == null)
                return false;
        }
        // Se nao possui nenhum filho (é uma folha), elimine-o
        if (current.left == null && current.right == null) {
            // se raiz
            if (current == root)
                root = null;
            // se for filho a esquerda do pai
            else if (child_left)
                father.left = null;
            // se for filho a direita do pai
            else
                father.right = null;
        }
        // Se é pai e nao possui um filho a direita, substitui pela subarvore a direita
        else if (current.right == null) {
            // se raiz
            if (current == root)
                root = current.left;
            // se for filho a esquerda do pai
            else if (child_left)
                father.left = current.left;
            // se for filho a direita do pai
            else
                father.right = current.left;
        }
        // Se é pai e nao possui um filho a esquerda, substitui pela subarvore a esquerda
        else if (current.left == null) {
            // se raiz
            if (current == root)
                root = current.right;
            // se for filho a esquerda do pai
            else if (child_left)
                father.left = current.right;
            // se for  filho a direita do pai
            else
                father.right = current.right;
        }
        // Se possui mais de um filho, se for um avô ou outro grau maior de parentesco
        else {
            Node successor = node_successor(current);
            // Usando sucessor que seria o Nó mais a esquerda da subarvore a direita do No que deseja-se remover
            // se raiz
            if (current == root)
                root = successor;
            // se for filho a esquerda do pai
            else if (child_left)
                father.left = successor;
            // se for filho a direita do pai
            else
                father.right = successor;
            // acertando o ponteiro a esquerda do sucessor agora que ele assumiu
            successor.left = current.left;
            // a posição correta na arvore
        }
        return true;
    }

    // O sucessor é o nodo mais a esquerda da subarvore a direita do nodo que foi passado como parâmetro do método
    public Node node_successor(Node node) {
        Node father_successor = node;
        Node successor = node;
        Node current = node.left;

        // enquanto nao chegar no nodo mais a esquerda
        while (current != null) {
            father_successor = successor;
            successor = current;
            // caminha para a esquerda
            current = current.left;
        }
        // se sucessor nao é o filho a direita do Nó que deverá ser eliminado
        if (successor != node.right) {
            // pai herda os filhos do sucessor que sempre serão a direita
            father_successor.left = successor.right;
            successor.right = node.right;
        }
        return successor;
    }

    void clearTree() {
        root = null;
    }

    public void inOrder(Node current) {
        if (current != null) {
            inOrder(current.left);
            System.out.print(current.element + " ");
            inOrder(current.right);
        }
    }

    public void preOrder(Node current) {
        if (current != null) {
            System.out.print(current.element + " ");
            preOrder(current.left);
            preOrder(current.right);
        }
    }

    public void posOrder(Node current) {
        if (current != null) {
            posOrder(current.left);
            posOrder(current.right);
            System.out.print(current.element + " ");
        }
    }

    public int height(Node current) {
        if (current == null || (current.left == null && current.right == null)) {
            return 0;
        } else {
            if (height(current.left) > height(current.right))
                return (1 + height(current.left));
            else
                return (1 + height(current.right));
        }
    }

    public int countNodes(Node current) {
        if (current == null)
            return 0;
        else
            return (1 + countNodes(current.left) + countNodes(current.right));
    }

    public Node getRoot() {
        return root;
    }

    public void caminhamentos() {
        System.out.print("\n Caminhamento Central ou Em or (Interfixado): ");
        inOrder(root);
        System.out.print("\n Exibindo em Pós-ordem (Pós-fixado): ");
        posOrder(root);
        System.out.print("\n Exibindo em Pré-ordem (Pré-fixado): ");
        preOrder(root);
    }

    public void treeInfo() {
        System.out.println("Altura da arvore: " + height(root));
        System.out.println("Quantidade de Nós: " + countNodes(root));
        //if (root != null) {
            // System.out.println("Valor minimo: " + minNode());
            // System.out.println("Valor maximo: " + maxNode());
        // }
        // System.out.println("Quantidade de folhas: " + leaveNodes(root));
    }

    public void printTree() {
        if(root != null) {
            TreeFormatter formatter = new TreeFormatter();
            System.out.println(formatter.topDown(root));
        } else {
            System.out.println("Árvore vazia!");
        }
    }


    /**
     * Método minNode()
     * método que busca o menor valor existente na árvore
     * @param defina a necessidade de parâmetros de acordo com a sua implementação
     * @return valor do menor nodo da árvore
     */
    public int minNode() {
        int min = 0;
        Node current = root;
        if(current.left == null){
            return root.element;
        }

        while (current.left != null){
            current = current.left;
            min = current.element;
        }
        return min;
    }


    /**
     * Método maxNode()
     * método que busca o maior valor existente na árvore
     * @param defina a necessidade de parâmetros de acordo com a sua implementação
     * @return valor do maior nodo da árvore
     */
    public int maxNode() {
        int max = 0;
        Node current = root;
        if(current.right == null){
            return root.element;
        }

        while (current.right != null){
            current = current.right;
            max = current.element;
        }
        return max;
    }


    /**
     * Método countLeaves()
     * método que conta os nodos folha de uma árvore binária
     * @param defina a necessidade de parâmetros de acordo com a sua implementação
     * @return valor inteiro correspondente a quantidade de nodos folha
     */
    public int countLeaves(Node current) {
        int folhas = 0;
        if (current.left == null && current.right == null) {
            folhas++;
        }
        if (current.left != null) {
            folhas += countLeaves(current.left);
        }
        if (current.right != null) {
            folhas += countLeaves(current.right);
        }

        return folhas;
    }


    /**
     * Método sumBetween()
     * método soma os valores de uma sequência de nodos (não incluindo os valores dos nodos de início e fim)
     * @param start valor que corresponde ao nodo de início
     * @param end valor que corresponde ao nodo de fim
     * @param defina outros caso haja necessidade na sua implementação
     * @return valor inteiro correspondente a quantidade de nodos folha
     */
    public int sumBetween(Node current, int start, int end) {
        int sum = 0;

        if (current.element > start && current.element < end) {
            sum += current.element;
        }
        if (current.left != null) {
            sum += sumBetween(current.left, start, end);
        }
        if (current.right != null) {
            sum += sumBetween(current.right, start, end);
        }

        return sum;
    }
    }
