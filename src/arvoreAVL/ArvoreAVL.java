package arvoreAVL;

import java.util.ArrayList;

public class ArvoreAVL {
    public No raiz;

    int altura(No n) {
        if (n == null)
            return 0;
        return n.altura;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    No rightRotate(No y) {
        No x = y.left;
        No t2 = x.right;
        x.right = y;
        y.left = t2;
        y.altura = max(altura(y.left), altura(y.right)) + 1;
        x.altura = max(altura(x.left), altura(x.right)) + 1;
        return x;
    }

    No leftRotate(No x) {
        No y = x.right;
        No t2 = y.left;
        y.left = x;
        x.right = t2;
        x.altura = max(altura(x.left), altura(x.right)) + 1;
        y.altura = max(altura(y.left), altura(y.right)) + 1;
        return y;
    }

    // Retorna o fator de balanceamento do No n
    int getBalanceFactor(No n) {
        if (n == null)
            return 0;
        return altura(n.left) - altura(n.right);
    }

    // Insere um novo No
    // Insere um novo No
    public No insertNo(No no, int item) {
        // Encontra a posição correta e insere o No (até o no folha apropriado)
        if (no == null)
            return (new No(item));

        if (item <= no.item)
            no.left = insertNo(no.left, item);
        else
            no.right = insertNo(no.right, item);

        // Atualiza o fator de balanceamento de cada No e faz o balanceamento da árvore
        no.altura = 1 + max(altura(no.left), altura(no.right));
        int balanceFactor = getBalanceFactor(no);

        if (balanceFactor > 1) {
            if (item <= no.left.item) {
                return rightRotate(no);
            } else {
                no.left = leftRotate(no.left);
                return rightRotate(no);
            }
        }

        if (balanceFactor < -1) {
            if (item > no.right.item) {
                return leftRotate(no);
            } else {
                no.right = rightRotate(no.right);
                return leftRotate(no);
            }
        }

        return no;
    }

    // Encontra o No com o valor mínimo
    No noWithMimumValue(No no) {
        No atual = no;
        while (atual.left != null)
            atual = atual.left;
        return atual;
    }

    // Deletar o No
    public No deleteNo(No raiz, int item) {
        // Encontrar o No e deletar
        if (raiz == null)
            return raiz;

        if (item < raiz.item)
            raiz.left = deleteNo(raiz.left, item);
        else if (item > raiz.item)
            raiz.right = deleteNo(raiz.right, item);
        else {
            if ((raiz.left == null) || (raiz.right == null)) {
                No temp = null;
                if (temp == raiz.left)
                    temp = raiz.right;
                else
                    temp = raiz.left;
                if (temp == null) {
                    temp = raiz;
                    raiz = null;
                } else
                    raiz = temp;
            } else {
                No temp = noWithMimumValue(raiz.right);
                raiz.item = temp.item;
                raiz.right = deleteNo(raiz.right, temp.item);
            }
        }
        if (raiz == null)
            return raiz;

        // Atualiza o fator de balanceamento de cada No e faz o balanceamento da árvore
        raiz.altura = max(altura(raiz.left), altura(raiz.right)) + 1;
        int balanceFactor = getBalanceFactor(raiz);
        if (balanceFactor > 1) {
            if (getBalanceFactor(raiz.left) >= 0) {
                return rightRotate(raiz);
            } else {
                raiz.left = leftRotate(raiz.left);
                return rightRotate(raiz);
            }
        }
        if (balanceFactor < -1) {
            if (getBalanceFactor(raiz.right) <= 0) {
                return leftRotate(raiz);
            } else {
                raiz.right = rightRotate(raiz.right);
                return leftRotate(raiz);
            }
        }
        return raiz;
    }

    public void preOrder(No no) {
        if (no != null) {
            System.out.print(no.item + " ");
            preOrder(no.left);
            preOrder(no.right);
        }
    }

    public void inOrder(No no) {
        if (no != null) {
            inOrder(no.left);
            System.out.print(no.item + " ");
            inOrder(no.right);
        }
    }

    public void posOrder(No no) {
        if (no != null) {
            posOrder(no.left);
            posOrder(no.right);
            System.out.print(no.item + " ");
        }
    }

    public int[] inOrderToArray(No raiz) {
        ArrayList<Integer> resultList = new ArrayList<>();
        inOrderCollect(raiz, resultList);

        // Convert ArrayList to an array
        int[] resultArray = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            resultArray[i] = resultList.get(i);
        }

        return resultArray;
    }

    private void inOrderCollect(No no, ArrayList<Integer> resultList) {
        if (no != null) {
            inOrderCollect(no.left, resultList);
            resultList.add(no.item);
            inOrderCollect(no.right, resultList);
        }
    }


    // Imprime a árvore
    public void printTree(No currPtr, String indent, boolean last) {
        if (currPtr != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }
            System.out.println(currPtr.item);
            printTree(currPtr.left, indent, false);
            printTree(currPtr.right, indent, true);
        }
    }

    public void search(No raiz, int numero) {
        if (raiz != null) {
            if (raiz.item == numero) {
                System.out.println("O número " + numero + " foi encontrado na árvore AVL!");
            } else if (raiz.item > numero) {
                search(raiz.left, numero);
            } else {
                search(raiz.right, numero);
            }
        } else {
            System.out.println("O número " + numero + " não foi encontrado na árvore AVL!");
        }
    }

    public int contarOcorrencias(No no, int numero) {
        if (no == null) {
            return 0;
        }

        if (no.item == numero) {
            return 1 + contarOcorrencias(no.left, numero) + contarOcorrencias(no.right, numero);
        } else if (numero < no.item) {
            return contarOcorrencias(no.left, numero);
        } else {
            return contarOcorrencias(no.right, numero);
        }
    }
}