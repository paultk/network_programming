package task_5;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by paul on 19.03.17.
 */
public class ServerMain {
    public static final String hostname = "//localhost/RmiServer";
    public static void main(String[] args) {
        try{
            LocateRegistry.createRegistry(1099);
            Register register = new Register();
            Naming.rebind(hostname, register);
            System.out.println("up and running");

            for (int i = 0; i < 5; i++) {
                register.regNyttUtstyr(i, "startbet" + i, "startLev" + i, i * 100, i *10);
            }

            while (true){
                Thread.sleep(3000);
                System.out.println(register.lagDatabeskrivelse());

            }

        } catch (Exception e) {e.printStackTrace();}
    }
}
