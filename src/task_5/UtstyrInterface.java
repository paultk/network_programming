package task_5;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by paul on 19.03.17.
 */
public interface UtstyrInterface extends Remote{

    boolean regNyttUtstyr(int startNr, String startBetegnelse,
                          String startLeverandør, int startPåLager, int startNedreGrense) throws RemoteException;

    int endreLagerbeholdning(int nr, int mengde) throws RemoteException;


    String lagBestillingsliste() throws RemoteException;

    String lagDatabeskrivelse() throws RemoteException;


}
