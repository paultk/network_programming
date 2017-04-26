package task_5;

/**
 * Created by paul on 19.03.17.
 */
public class Utstyr {
    public static final int bestillingsfaktor = 5;
    private int nr;  // entydig identifikasjon
    private String betegnelse;
    private String leverandør;
    private int påLager;     // mengde på lager
    private int nedreGrense;

    public Utstyr(int startNr, String startBetegnelse, String startLeverandør,
                  int startPåLager, int startNedreGrense) {
        nr = startNr;
        betegnelse = startBetegnelse;
        leverandør = startLeverandør;
        påLager = startPåLager;
        nedreGrense = startNedreGrense;
    }

    public int finnNr() {
        return nr;
    }

    public String finnBetegnelse() {
        return betegnelse;
    }

    public String finnLeverandør() {
        return leverandør;
    }

    public int finnPåLager() {
        return påLager;
    }

    public int finnNedreGrense() {
        return nedreGrense;
    }

    public int finnBestKvantum() {
        if (påLager < nedreGrense) return bestillingsfaktor * nedreGrense;
        else return 0;
    }

    /*
     * Endringen kan være positiv eller negativ. Men det er ikke
     * mulig å ta ut mer enn det som fins på lager. Hvis klienten
     * prøver på det, vil metoden returnere false, og intet uttak gjøres.
     */
    public boolean endreLagerbeholdning(int endring) {
        System.out.println("Endrer lagerbeholdning, utstyr nr " + nr + ", endring: " + endring);
        if (påLager + endring < 0) return false;
        else {
            påLager += endring;
            return true;
        }
    }

    public void settNedreGrense(int nyNedreGrense) {
        nedreGrense = nyNedreGrense;
    }

    public String toString() {
        String resultat = "Nr: " + nr + ", " +
                "Betegnelse: " + betegnelse + ", " + "Leverandør: " +
                leverandør + ", " + "På lager: " + påLager + ", " +
                "Nedre grense: " + nedreGrense;
        return resultat;
    }
}
