package arvoreRubroNegra;


public class ArvoreRubroNegra {

    static final int NEGRO = 0;
    static final int RUBRO = 1;
    private No raiz;
    private No TNULL;

    // Procurar na árvore
    private No searchTreeHelper(No no, int key) {
        if (no == TNULL || key == no.item) {
            return no;
        }

        if (key <= no.item) {
            return searchTreeHelper(no.left, key);
        }
        return searchTreeHelper(no.right, key);
    }

    // equilibra a árvore após a exclusão de um nó
    private void fixDelete(No x) {
        No s;
        while (x != raiz && x.cor == NEGRO) {
            if (x == x.pai.left) {
                s = x.pai.right;
                if (s.cor == RUBRO) { // caso 1: irmão é rubro!!!
                    s.cor = NEGRO;    // irmão fica negro
                    x.pai.cor = RUBRO; // pai fica rubro
                    leftRotate(x.pai); // rotaciona o pai à esquerda
                    s = x.pai.right;  // atualiza o irmão
                }

                if (s.left.cor == NEGRO && s.right.cor == NEGRO) {  // caso 2: irmão é negro e filhos do irmão são negros
                    s.cor = RUBRO; // irmão fica rubro
                    x = x.pai; // no passa apontar para o pai
                } else {
                    if (s.right.cor == NEGRO) {  // caso 3 : irmão é negro e o filho direito do irmão é negro
                        s.left.cor = NEGRO; // filho esquerdo do irmão fica negro
                        s.cor = RUBRO; // irmão fica rubro
                        rightRotate(s); // rotaciona o irmão à direita
                        s = x.pai.right; // atualiza o irmão
                    }
                    // caso 4: irmão é negro e filho direito do irmão é rubro
                    s.cor = x.pai.cor;  // irmão copia a cor do pai
                    x.pai.cor = NEGRO; // pai fica negro
                    s.right.cor = NEGRO; // filho direito do irmão fica negro
                    leftRotate(x.pai); // rotaciona o pai à esquerda
                    x = raiz; // no aponta para a raiz
                }
            } else {  // no é filho direito
                s = x.pai.left;
                if (s.cor == RUBRO) {
                    s.cor = NEGRO;
                    x.pai.cor = RUBRO;
                    rightRotate(x.pai);
                    s = x.pai.left;
                }

                if (s.right.cor == NEGRO && s.right.cor == NEGRO) {
                    s.cor = RUBRO;
                    x = x.pai;
                } else {
                    if (s.left.cor == NEGRO) {
                        s.right.cor = NEGRO;
                        s.cor = RUBRO;
                        leftRotate(s);
                        s = x.pai.left;
                    }

                    s.cor = x.pai.cor;
                    x.pai.cor = NEGRO;
                    s.left.cor = NEGRO;
                    rightRotate(x.pai);
                    x = raiz;
                }
            }
        }
        x.cor = NEGRO; // no fica negro
    }

    private void rbTransplant(No u, No v) {
        if (u.pai == null) {
            raiz = v;
        } else if (u == u.pai.left) {
            u.pai.left = v;
        } else {
            u.pai.right = v;
        }
        v.pai = u.pai;
    }

    private void deleteNoHelper(No no, int key) {
        No z = TNULL;
        No x, y;
        while (no != TNULL) {
            if (no.item == key) {
                z = no;
            }

            if (no.item <= key) {
                no = no.right;
            } else {
                no = no.left;
            }
        }

        if (z == TNULL) {
            System.out.println("Não foi possível encontrar a chave na árvore");
            return;
        }

        y = z;
        int yOriginalColor = y.cor;
        if (z.left == TNULL) {
            x = z.right;
            rbTransplant(z, z.right);
        } else if (z.right == TNULL) {
            x = z.left;
            rbTransplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.cor;
            x = y.right;
            if (y.pai == z) {
                x.pai = y;
            } else {
                rbTransplant(y, y.right);
                y.right = z.right;
                y.right.pai = y;
            }

            rbTransplant(z, y);
            y.left = z.left;
            y.left.pai = y;
            y.cor = z.cor;
        }
        if (yOriginalColor == NEGRO) {
            fixDelete(x);
        }
    }

    // equilibra a árvore após a inserção de um nó
    private void fixInsert(No k) {
        No u;
        while (k.pai.cor == RUBRO) {  // pai do no inserido é rubro
            if (k.pai == k.pai.pai.right) { // se o pai for o filho a direita do avô
                u = k.pai.pai.left;  // tio = filho à esquerda do avô
                if (u.cor == RUBRO) {
                    u.cor = NEGRO; // tio fica negro
                    k.pai.cor = NEGRO; // pai fica negro
                    k.pai.pai.cor = RUBRO; // avô fica rubro
                    k = k.pai.pai;  // novo no = avô
                } else {
                    if (k == k.pai.left) { // novo no é o filho esquerdo do pai
                        k = k.pai; // novo no = pai
                        rightRotate(k); // rotaciona o no à direita
                    }
                    k.pai.cor = NEGRO; // pai fica negro
                    k.pai.pai.cor = RUBRO; // avô fica rubro
                    leftRotate(k.pai.pai); // rotaciona o avô para esquerda
                }
            } else { // se o pai for o filho a esquerda do avô
                u = k.pai.pai.right;

                if (u.cor == RUBRO) {
                    u.cor = NEGRO;
                    k.pai.cor = NEGRO;
                    k.pai.pai.cor = RUBRO;
                    k = k.pai.pai;
                } else {
                    if (k == k.pai.right) {
                        k = k.pai;
                        leftRotate(k);
                    }
                    k.pai.cor = NEGRO;
                    k.pai.pai.cor = RUBRO;
                    rightRotate(k.pai.pai);
                }
            }
            if (k == raiz) {
                break;
            }
        }
        raiz.cor = NEGRO;
    }

    private void printHelper(No raiz, String indent, boolean last) {
        if (raiz != TNULL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            String sColor = raiz.cor == RUBRO ? "RUBRO" : "NEGRA";
            System.out.println(raiz.item + "(" + sColor + ")");
            printHelper(raiz.left, indent, false);
            printHelper(raiz.right, indent, true);
        }
    }

    public ArvoreRubroNegra() { // no nulo/ no folha
        TNULL = new No();
        TNULL.cor = NEGRO;
        TNULL.left = null;
        TNULL.right = null;
        raiz = TNULL;
    }

    // Preorder
    private void preOrderHelper(No no) {
        if (no != TNULL) {
            System.out.print(no.item + " ");
            preOrderHelper(no.left);
            preOrderHelper(no.right);
        }
    }

    // Inorder
    private void inOrderHelper(No no) {
        if (no != TNULL) {
            inOrderHelper(no.left);
            System.out.print(no.item + " ");
            inOrderHelper(no.right);
        }
    }

    // Post order
    private void postOrderHelper(No no) {
        if (no != TNULL) {
            postOrderHelper(no.left);
            postOrderHelper(no.right);
            System.out.print(no.item + " ");
        }
    }

    public void preorder() {
        preOrderHelper(this.raiz);
    }

    public void inorder() {
        inOrderHelper(this.raiz);
    }

    public void postorder() {
        postOrderHelper(this.raiz);
    }

    public No searchTree(int k) {
        return searchTreeHelper(this.raiz, k);
    }

    public No minimum(No no) {
        while (no.left != TNULL) {
            no = no.left;
        }
        return no;
    }

    public No maximum(No no) {
        while (no.right != TNULL) {
            no = no.right;
        }
        return no;
    }

    public No successor(No x) {
        if (x.right != TNULL) {
            return minimum(x.right);
        }

        No y = x.pai;
        while (y != TNULL && x == y.right) {
            x = y;
            y = y.pai;
        }
        return y;
    }

    public No predecessor(No x) {
        if (x.left != TNULL) {
            return maximum(x.left);
        }

        No y = x.pai;
        while (y != TNULL && x == y.left) {
            x = y;
            y = y.pai;
        }

        return y;
    }

    public void leftRotate(No x) {
        No y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.pai = x;
        }
        y.pai = x.pai;
        if (x.pai == null) {
            this.raiz = y;
        } else if (x == x.pai.left) {
            x.pai.left = y;
        } else {
            x.pai.right = y;
        }
        y.left = x;
        x.pai = y;
    }

    public void rightRotate(No x) {
        No y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.pai = x;
        }
        y.pai = x.pai;
        if (x.pai == null) {
            this.raiz = y;
        } else if (x == x.pai.right) {
            x.pai.right = y;
        } else {
            x.pai.left = y;
        }
        y.right = x;
        x.pai = y;
    }

    // insere o nó na árvore
    public void insert(int key) {
        No no = new No();
        no.pai = null;
        no.item = key;
        no.left = TNULL;
        no.right = TNULL;
        no.cor = RUBRO;

        No y = null;
        No x = this.raiz;

        while (x != TNULL) {
            y = x;
            if (no.item <= x.item) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        no.pai = y;
        if (y == null) {
            raiz = no;
        } else if (no.item <= y.item) {
            y.left = no;
        } else {
            y.right = no;
        }

        if (no.pai == null) {
            no.cor = NEGRO;
            return;
        }

        if (no.pai.pai == null) {
            return;
        }

        fixInsert(no);
    }

    public No getRoot() {
        return this.raiz;
    }

    public void deleteNo(int item) {
        deleteNoHelper(this.raiz, item);
    }

    public void printTree() {
        printHelper(this.raiz, "", true);
    }

    public int contarOcorrencias(No no, int key) {
        if (no == TNULL) {
            return 0;
        }

        int count = 0;

        if (key == no.item) {
            count++;
        }

        count += contarOcorrencias(no.left, key);
        count += contarOcorrencias(no.right, key);

        return count;
    }

}
