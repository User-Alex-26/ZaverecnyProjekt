package aplikace;

public class Main {
    public static void main(String[] args) {
        SpravaPojistencu spravaPojistencu = new SpravaPojistencu();
        ConsoleUI ui = new ConsoleUI(spravaPojistencu);
        ui.spustit();
    }
}
