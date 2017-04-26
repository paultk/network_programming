package task_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.Scanner;

import static task_3.Server.PORTNUMBER;

/**
 * Created by paul on 10.03.17.
 */
public class Client {


    public static void udpClient(){


    }
    public static void task1(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("paste servermachine name");
        String machineName = scanner.nextLine();

        try (
                Socket connection = new Socket(machineName, PORTNUMBER);
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                PrintWriter printWriter = new PrintWriter(connection.getOutputStream(),true)
        ) {
            String uno = bufferedReader.readLine();
            System.out.println(uno);

            String dos = bufferedReader.readLine();
            System.out.println(dos);
            printWriter.println(scanner.nextLine());
            System.out.println(bufferedReader.readLine());
            printWriter.println(scanner.nextLine());
            System.out.println(bufferedReader.readLine());
            printWriter.println(scanner.nextLine());
            System.out.println(bufferedReader.readLine());



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try {

            Scanner scanner = new Scanner(System.in);
            System.out.println("first operand");
            double firstOperand = Double.parseDouble(scanner.nextLine());
            System.out.println("second operand");
            double secondOperand = Double.parseDouble(scanner.nextLine());
            System.out.println("operator");
            String operator = scanner.nextLine();

            byte[] buf = new byte[256];


            DecimalFormat df = new DecimalFormat("#.##");
            String newDouble1 = df.format(firstOperand);
            String newDouble2 = df.format(secondOperand);
            byte[] byteArray1 = (newDouble1.replace(",", "") + " " + newDouble2 + " " + operator).getBytes();

            for (int i = 0; i < byteArray1.length; ++i) {
                buf[i] = byteArray1[i];
            }

            for (byte b : buf) {
                System.out.print((char)b+"");
            }

            InetAddress address = InetAddress.getByName("paul-Surface-Pro-3");
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(buf, buf.length,
                    address, PORTNUMBER);
            socket.send(packet);
            byte[] buffer2 = new byte[256];
            DatagramPacket datagramPacket2 = new DatagramPacket(buffer2, buffer2.length);
            socket.receive(datagramPacket2);

            System.out.print("Answer: ");
            for (byte b : datagramPacket2.getData()) {
                System.out.print((char)b+"");
            }
//            task1();

        }catch (Exception e){e.printStackTrace();}
    }
}
