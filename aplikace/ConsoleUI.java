package aplikace;

import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner;
    private SpravaPojistencu spravaPojistencu;

    public ConsoleUI(SpravaPojistencu spravaPojistencu) {
        this.spravaPojistencu = spravaPojistencu;
        this.scanner = new Scanner(System.in);
    }

    // Komunikace s uživatelem
    public void spustit() {
        boolean konec = false;

        while (!konec) {
            zobrazMenu();
            int volba = zadejCislo("Vyberte možnost: ");

            switch (volba) {
                case 1:
                    vytvoritPojistence();
                    break;
                case 2:
                    zobrazitVsechnyPojistence();
                    break;
                case 3:
                    vyhledatPojistence();
                    break;
                case 4:
                    konec = true;
                    System.out.println("Program byl ukončen.");
                    break;
                default:
                    System.out.println("Neplatná volba.");
            }
        }
    }

    // Zobrazení menu
    private void zobrazMenu() {
        System.out.println("-----------------------------");
        System.out.println("Evidence pojištěných");
        System.out.println("-----------------------------");
        System.out.println();
        System.out.println("1 - Přidat pojištěného");
        System.out.println("2 - Zobrazit všechny pojištěné");
        System.out.println("3 - Vyhledat pojištěného");
        System.out.println("4 - Konec");
    }

    // Vytvoření pojištěného
    private void vytvoritPojistence() {
        String jmeno = zadejValidniText("Zadejte jméno: ");
        String prijmeni = zadejValidniText("Zadejte příjmení: ");
        int vek = zadejValidniVek("Zadejte věk: ");
        String telefon = zadejValidniTelefon("Zadejte telefonní číslo: ");

        spravaPojistencu.pridatPojistence(jmeno, prijmeni, vek, telefon);
        System.out.println("Pojištěný byl úspěšně přidán.\n");
    }


    // Zobrazení všech pojištěných
    private void zobrazitVsechnyPojistence() {
        System.out.println("Seznam všech pojištěných:");
        for (Pojistenec p : spravaPojistencu.zobrazVsechnyPojistence()) {
            System.out.println(p);
        }
        System.out.println();
    }

    // Vyhledání pojištěnce pomocí jména a příjmení
    private void vyhledatPojistence() {
        String jmeno = zadejValidniText("Zadejte jméno: ");
        String prijmeni = zadejValidniText("Zadejte příjmení: ");
        Pojistenec nalezeny = spravaPojistencu.najdiPojistence(jmeno, prijmeni);

        if (nalezeny != null) {
            System.out.println("Nalezený pojištěný: " + nalezeny + "\n");
        } else {
            System.out.println("Pojištěný nebyl nalezen.\n");
        }
    }

    // Pomocné metody pro validaci vstupů
    private int zadejCislo(String vyzva) {
        int cislo = -1;
        while (cislo < 0) {
            System.out.print(vyzva);
            if (scanner.hasNextInt()) {
                cislo = scanner.nextInt();
            } else {
                System.out.println("Neplatný vstup. Zadejte celé číslo.");
            }
            scanner.nextLine();
        }
        return cislo;
    }

    private String zadejValidniText(String vyzva) {
        String vstup = "";
        while (vstup.isEmpty() || !vstup.matches("^[a-zA-Zá-žÁ-Ž]+$")) {
            System.out.print(vyzva);
            vstup = scanner.nextLine().trim();
            if (vstup.isEmpty()) {
                System.out.println("Tento údaj nemůže být prázdný.");
            } else if (!vstup.matches("^[a-zA-Zá-žÁ-Ž]+$")) {
                System.out.println("Jméno nebo příjmení nesmí obsahovat čísla nebo mezery.");
            }
        }
        return vstup;
    }

    private int zadejValidniVek(String vyzva) {
        int vek = -1;
        while (vek < 0 || vek > 120) {
            System.out.print(vyzva);
            if (scanner.hasNextInt()) {
                vek = scanner.nextInt();
                if (vek < 0 || vek > 120) {
                    System.out.println("Věk musí být v rozmezí od 0 do 120 let.");
                }
            } else {
                System.out.println("Neplatný vstup. Zadejte platný věk (číslo).");
            }
            scanner.nextLine();
        }
        return vek;
    }

    private String zadejValidniTelefon(String vyzva) {
        String telefon = "";
        while (!telefon.matches("^\\+?[0-9]+$")) {
            System.out.print(vyzva);
            telefon = scanner.nextLine().trim();
            if (!telefon.matches("^\\+?[0-9]+$")) {
                System.out.println("Telefonní číslo musí obsahovat pouze číslice a případně znak '+'.");
            }
        }
        return telefon;
    }
}
