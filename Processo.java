public class Processo {
    String nome;
    int prioridade;
    int prioridadeOriginal;
    int tempoEspera;
    int execucoes;

    Processo(String nome, int prioridade) {
        this.nome = nome;
        this.prioridade = prioridade;
        this.prioridadeOriginal = prioridade;
        this.tempoEspera = 0;
        this.execucoes = 0;
    }
}