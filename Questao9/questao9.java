package Questao9;

import java.util.*;

class Celula {
    public int elemento;
    public Celula inf, sup, esq, dir;

    public Celula() {
        this(0);
    }

    public Celula(int elemento) {
        this(elemento, null, null, null, null);
    }

    public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir) {
        this.elemento = elemento;
        this.inf = inf;
        this.sup = sup;
        this.esq = esq;
        this.dir = dir;
    }
}

class Matriz {
    private Celula inicio;
    private int linha, coluna;

    public Matriz() {
        this(2, 2);
    }

    public Matriz(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        criarMat();
    }

    public void criarLinhas() {
        Celula tmp = inicio;
        for (int i = 1; i < linha; i++) {
            Celula nova = new Celula(0);
            tmp.inf = nova;
            nova.sup = tmp;
            tmp = nova;
        }
    }

    public void criarColunas(Celula c1, Celula c2) {
        for (int i = 1; i < coluna; i++) {
            Celula proxC1;
            Celula proxC2 = new Celula(0);

            // set proxC1 a direita de c1
            if (c1.dir != null) { // elem. a direita de c1 ja existe
                proxC1 = c1.dir;
            } else { // elem. a direita de c1 ainda não existe
                proxC1 = new Celula(0);
                c1.dir = proxC1;
                proxC1.esq = c1; 
            }

            // set proxC2 a direita de C2 e abaixo de proxC1
            proxC1.inf = proxC2;
            c2.dir = proxC2;
            proxC2.esq = c2;
            proxC2.sup = proxC1;
            
            // caminhamento para os elementos a direita
            c1 = proxC1;
            c2 = proxC2;
        }
    }

    // aloca espaços para a matriz
    public void criarMat() {
        if (inicio == null) {
            inicio = new Celula(); // cria o primeiro elemento
        }

        criarLinhas(); // cria linhas

        // criar colunas
        Celula c1 = inicio; // celula na linha acima
        Celula c2 = inicio.inf; // linha abaixo
        for (int i = 1; i < linha; i++) {
            c1.inf = c2;
            c2.sup = c1;
            criarColunas(c1, c2);
            c1 = c2;
            c2 = c2.inf;
        }
    }
    

    // insere elemento com base na posicao
    public void setPos(int l, int c, int x) {
        Celula tmp = inicio;

        for (int i = 0; i < l; i++) {
            tmp = tmp.inf;
        }

        for (int i = 0; i < c; i++) {
            tmp = tmp.dir;
        }

        tmp.elemento = x;

    }

    // retorna elemento com base na posicao
    public int getPos(int l, int c) {
        Celula tmp = inicio;

        for (int i = 0; i < l; i++) {
            tmp = tmp.inf;
        }

        for (int i = 0; i < c; i++) {
            tmp = tmp.dir;
        }

        return tmp.elemento;
    }

    // preenche os elementos
    public static Matriz inputMatriz(Matriz mat, Scanner sc) {
        int l, c;

        l = sc.nextInt();
        sc.nextLine();
        c = sc.nextInt();
        sc.nextLine();

        mat = new Matriz(l, c);
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < c; j++) {
                int valor = sc.nextInt();
                mat.setPos(i, j, valor);
            }
            sc.nextLine();
        }

        return mat;
    }

    // imprime a matriz completa
    public void imprimeMatriz() {
        for (Celula l = inicio; l != null; l = l.inf) { // caminha linhas
            for (Celula c = l; c != null; c = c.dir) { // caminha colunas
                System.out.print(c.elemento + " ");
            }
            System.out.println();
        }
    }

    public void mostrarDiagonal() {
        Celula tmp = inicio;

        while (tmp != null) {
            System.out.print(tmp.elemento + " ");
            tmp = tmp.inf;

            if (tmp == null) {
                break;
            }

            tmp = tmp.dir;
        }
        System.out.println();
    }

    public void mostrarDiagonalInv() {
        Celula tmp = inicio;

        // caminha o maximo para a direita
        while (tmp.dir != null) {
            tmp = tmp.dir;
        }

        while (tmp != null) {
            System.out.print(tmp.elemento + " ");
            if(tmp.inf != null) {
                tmp = tmp.inf.esq;
            } else {
                tmp = null;
            }
        }
        System.out.println();
    }

    public static void soma(Matriz m1, Matriz m2) {
        Matriz resp = new Matriz(m1.linha, m1.coluna);

        for (int i = 0; i < m1.linha; i++) {
            for (int j = 0; j < m1.coluna; j++) {
                int soma = (m1.getPos(i, j) + m2.getPos(i, j));
                resp.setPos(i, j, soma);
            }
        }

        resp.imprimeMatriz();
    }

    public static void multiplicacao(Matriz m1, Matriz m2) {
        Matriz resp = new Matriz(m1.linha, m1.coluna);

        for (int i = 0; i < m1.linha; i++) {
            for (int j = 0; j < m2.coluna; j++) {
                int mult = 0;
                for (int k = 0; k < m1.coluna; k++) {
                    mult += m1.getPos(i, k) * m2.getPos(k, j);
                }
                resp.setPos(i, j, mult);
            }
        }

        resp.imprimeMatriz();
    }
}

public class questao9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int casos = sc.nextInt();

        // leitura de todos os casos
        while(casos > 0) {
            Matriz[] array = new Matriz[2];

            for (int i = 0; i < 2; i++) {
                array[i] = Matriz.inputMatriz(array[i], sc);
            }

            array[0].mostrarDiagonal();
            array[0].mostrarDiagonalInv();
            Matriz.soma(array[0], array[1]);
            Matriz.multiplicacao(array[0], array[1]);
            casos--;
        }

        sc.close();
    }
}
