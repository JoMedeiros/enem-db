import java.util.Scanner;

/**
 * @brief Terminal User Interface.
 */
public class TUI implements Runnable {
    private static final TUI INSTANCE = new TUI();

    private TUI(){}

    public static TUI getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void run() {
        char opt = ' ';
        Scanner sc = new Scanner(System.in);
        while (opt != 'q'){
            System.out.println("Bem-vindo aos Dados Enem 2018!");
            System.out.println("Opções:");
            System.out.println("f - aplicar filtros");
            System.out.println("q - sair");
            opt = sc.next().charAt(0);
            this.handleInput(opt);
        }
    }

    private void handleInput(char opt) {
        if (opt == 'f'){
            System.out.println("Aplicando filtros...");
        }
    }
}
