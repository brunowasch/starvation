import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        if (args.length == 0) {

            System.out.println("Uso:");
            System.out.println("  java Main aging");
            System.out.println("  java Main starvation");

            return;
        }

        boolean aging;

        if (args[0].equalsIgnoreCase("aging")) {

            aging = true;

        } else if (args[0].equalsIgnoreCase("starvation")) {

            aging = false;

        } else {

            System.out.println("Parâmetro inválido.");
            System.out.println("Use: aging ou starvation");

            return;
        }

        System.out.println("======================================");
        System.out.println("       SIMULAÇÃO DE STARVATION");
        System.out.println("======================================");

        if (aging) {
            System.out.println("\n========== UTILIZANDO AGING ==========");
        } else {
            System.out.println("\n========== STARVATION ==========");
        }

        simular(aging);
    }

    public static void simular(boolean aging) {

        Processo A = new Processo("Processo A", 5);
        Processo B = new Processo("Processo B", 10);
        Processo C = new Processo("Processo C", 1);

        Processo[] processos = { A, B, C };
        System.out.println("\n---------- PROCESSOS ANTES DA EXECUÇÃO ----------");
        for (Processo p : processos) {

            System.out.println(
                    p.nome +
                            " | Execuções: " + p.execucoes +
                            " | Tempo de espera: " + p.tempoEspera +
                            " | Prioridade final: " + p.prioridade);
        }
        System.out.print("\nEnter para começar: ");
        scanner.nextLine();

        System.out.println("\n---------- EXECUÇÃO ----------");

        for (int tempo = 1; tempo <= 20; tempo++) {

            if (aging) {

                for (Processo p : processos) {

                    if (p.tempoEspera > 0) {

                        p.prioridade++;

                        if (p.prioridade > 10) {
                            p.prioridade = 10;
                        }
                    }
                }
            }

            Processo escolhido = processos[0];

            for (Processo p : processos) {

                if (p.prioridade > escolhido.prioridade) {

                    escolhido = p;

                } else if (p.prioridade == escolhido.prioridade &&
                        p.tempoEspera > escolhido.tempoEspera) {

                    escolhido = p;
                }
            }

            System.out.println(
                    "Tempo: " + tempo +
                            " | CPU executou " + escolhido.nome +
                            " | Prioridade: " + escolhido.prioridade);

            escolhido.execucoes++;

            for (Processo p : processos) {

                if (p != escolhido) {
                    p.tempoEspera++;
                } else {
                    p.tempoEspera = 0;
                }
            }

            System.out.println("\n---------- ESTADO DOS PROCESSOS ----------");

            for (Processo p : processos) {
                System.out.println(
                        p.nome +
                                " | Prioridade: " + p.prioridade +
                                " | Tempo de espera: " + p.tempoEspera +
                                " | Execuções: " + p.execucoes);
            }

            System.out.print("\nEnter para próxima rodada: ");
            scanner.nextLine();
            try {

                Thread.sleep(300);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\n---------- PROCESSOS APÓS A EXECUÇÃO ----------");

        for (Processo p : processos) {

            System.out.println(
                    p.nome +
                            " | Execuções: " + p.execucoes +
                            " | Tempo de espera: " + p.tempoEspera +
                            " | Prioridade final: " + p.prioridade);
        }
    }
}