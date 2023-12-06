import java.util.Random;

public class ListaDuplamEncad {
    public static void main(String[] args) {
        int[] vetor = new int[1000];
        Random random = new Random();

        int limiteInferior = -9999;
        int limiteSuperior = 9999;
        int intervalo =limiteSuperior - limiteInferior +1;

        for (int i=0; i<vetor.length; i++) {
            vetor[i] = random.nextInt(intervalo) + limiteInferior;
            System.out.println("Vetor: "+i+" = "+vetor[i]);
        }

        ListaDuplamenteEncadeada lista =new ListaDuplamenteEncadeada();
        for (int valor : vetor) {
            lista.inserirOrdenado(valor);
        }
        System.out.println("Lista em ordem crescente:");
        lista.imprimir();

        System.out.println("Lista em ordem decrecente:");
        lista.imprimirReverso();
        lista.removerPrimos();
        
        System.out.println("Lista apos remover prmos:");
        lista.imprimir();
    }
}
class NoListaDupla {
    int valor;
    NoListaDupla anterior;
    NoListaDupla proximo;

    public NoListaDupla(int valor) {
        this.valor = valor;
    }
}

class ListaDuplamenteEncadeada {
    private NoListaDupla frente;
    private NoListaDupla tras;

    public void inserirOrdenado(int valor) {
        NoListaDupla novoNo = new NoListaDupla(valor);
        if (frente == null) {
            frente = tras = novoNo;
        } else {
            NoListaDupla atual = frente;
            while (atual!= null && valor>atual.valor) {
                atual = atual.proximo;
            }
            if (atual == frente) {
                novoNo.proximo = frente;
                frente.anterior = novoNo;
                frente = novoNo;
            } else if (atual == null) {
                tras.proximo =novoNo;
                novoNo.anterior = tras;
                tras = novoNo;
            } else {
                novoNo.anterior =atual.anterior;
                novoNo.proximo = atual;
                atual.anterior.proximo = novoNo;
                atual.anterior = novoNo;
            }
        }
    }

     /*vou deixar a parte de impressão dos número sem organização pois pelo menos aqui
    no vscode o buffer do console não suporta muitas linhas impressas, então ele desconsidera
    muitas linhas no começo da execução, ou seja, pra realizar os testes fica péssimo
     */

    public void imprimir() {                                           
        NoListaDupla atual = frente;
        while (atual != null) {
            System.out.print(atual.valor+" ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    public void imprimirReverso() {
        NoListaDupla atual = tras;
        while (atual != null) {
            System.out.print(atual.valor+" ");
            atual = atual.anterior;
        }
        System.out.println();
    }

    public void removerPrimos() {
        NoListaDupla atual = frente;
        while (atual !=null) {
            if (Primo(atual.valor)) {
                if (atual == frente) {
                    frente = atual.proximo;
                    if (frente != null) frente.anterior = null;
                } else if (atual== tras) {
                    tras = atual.anterior;
                    if (tras !=null) tras.proximo = null;
                } else {
                    atual.anterior.proximo = atual.proximo;
                    if (atual.proximo != null) atual.proximo.anterior = atual.anterior;
                }
            }
            atual = atual.proximo;
        }
    }

    private boolean Primo(int numero) {
        if (numero<= 1) return false;
        for (int i=2; i<=Math.sqrt(numero); i++) {
            if (numero % i==0) return false;
        }
        return true;
    }
}

