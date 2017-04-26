package task_5;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by paul on 19.03.17.
 */
public class Register extends UnicastRemoteObject implements UtstyrInterface {


    /*
     *
     * Et register holder orden på en mengde Utstyrsobjekter. En klient kan legge inn nye
     * Utstyr-objekter i registeret, og også endre varebeholdningen for et
     * allerede registrert objekt. Bestillingsliste for alle varene kan lages.
     */
    public static final int ok = -1;
    public static final int ugyldigNr = -2;
    public static final int ikkeNokPåLager = -3;

    private ArrayList<Utstyr> registeret = new ArrayList<Utstyr>();

    public Register() throws RemoteException{
        super(0);
    }

    /*protected Register() throws RemoteException {
    }*/


    protected Register(int port) throws RemoteException {
        super(port);
    }

    protected Register(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    public synchronized boolean regNyttUtstyr(int startNr, String startBetegnelse,
                                 String startLeverandør, int startPåLager, int startNedreGrense) throws RemoteException{
        if (finnUtstyrindeks(startNr) < 0) { // fins ikke fra før
            Utstyr nytt = new Utstyr(startNr, startBetegnelse, startLeverandør,
                    startPåLager, startNedreGrense);
            registeret.add(nytt);
            return true;
        } else return false;
    }

    public synchronized int  endreLagerbeholdning(int nr, int mengde) throws RemoteException{
        int indeks = finnUtstyrindeks(nr);
        if (indeks < 0) return ugyldigNr;
        else {
            if (!(registeret.get(indeks)).endreLagerbeholdning(mengde)) {
                return ikkeNokPåLager;
            } else return ok;
        }
    }

    private int finnUtstyrindeks(int nr) throws RemoteException{
        for (int i = 0; i < registeret.size(); i++) {
            int funnetNr = (registeret.get(i)).finnNr();
            if (funnetNr == nr) return i;
        }
        return -1;
    }

    public String lagBestillingsliste() throws RemoteException{
        String resultat = "\n\nBestillingsliste:\n";
        for (int i = 0; i < registeret.size(); i++) {
            Utstyr u = registeret.get(i);
            resultat += u.finnNr() + ", " + u.finnBetegnelse() + ": " +
                    u.finnBestKvantum() + "\n";
        }
        return resultat;
    }

    public String lagDatabeskrivelse() throws RemoteException {
        String resultat = "Alle data:\n";
        for (int i = 0; i < registeret.size(); i++) {
            resultat += (registeret.get(i)).toString() + "\n";
        }
        return resultat;
    }
}
