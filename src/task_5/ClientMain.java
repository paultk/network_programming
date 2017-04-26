package task_5;

import java.rmi.Naming;
import java.util.Scanner;

import static task_5.ServerMain.hostname;

/**
 * Created by paul on 20.03.17.
 */
public class ClientMain {
    public static void main(String[] args) {
        try {
            UtstyrInterface remoteObj = (UtstyrInterface) Naming.lookup(hostname);
            remoteObj.endreLagerbeholdning(0, 2);


            Scanner scanner = new Scanner(System.in);
            boolean fin = false;
            while (!fin) {
                System.out.println(remoteObj.lagDatabeskrivelse());
                System.out.println("change more?\tY/N");
                if (scanner.nextLine().toLowerCase().equals("n")) {
                    fin = true;
                    continue;
                }
                System.out.println("index?");
                int index = Integer.parseInt(scanner.nextLine());
                System.out.println("increase?");
                int incr = Integer.parseInt(scanner.nextLine());
                System.out.println("index?");
                remoteObj.endreLagerbeholdning(index, incr);
            }
        } catch (Exception e) {e.printStackTrace();}
    }
}
