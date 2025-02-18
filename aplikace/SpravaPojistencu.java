package aplikace;

import java.util.ArrayList;
import java.util.List;

public class SpravaPojistencu {
    private List<Pojistenec> pojistenci;

    public SpravaPojistencu() {
        pojistenci = new ArrayList<>();
    }

    // Přidání pojištěného
    public void pridatPojistence(String jmeno, String prijmeni, int vek, String telefon) {
        Pojistenec novyPojistenec = new Pojistenec(jmeno, prijmeni, vek, telefon);
        pojistenci.add(novyPojistenec);
    }

    // Zobrazení všech pojištěných
    public List<Pojistenec> zobrazVsechnyPojistence() {
        return pojistenci;
    }

    // Vyhledání pojištěného podle jména a příjmení
    public Pojistenec najdiPojistence(String jmeno, String prijmeni) {
        for (Pojistenec p : pojistenci) {
            if (p.getJmeno().equalsIgnoreCase(jmeno) && p.getPrijmeni().equalsIgnoreCase(prijmeni)) {
                return p;
            }
        }
        return null; // Pokud pojištěnec nebyl nalezen
    }
}
