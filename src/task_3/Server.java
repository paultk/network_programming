package task_3;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * Created by paul on 10.03.17.
 */
public class Server {
    final static int PORTNUMBER = 1250;
    public static ServerSocket server;



    public static void newClient(Socket connection){
        try
                (
                        InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        PrintWriter printWriter = new PrintWriter(connection.getOutputStream(), true)
                )
        {
//            paul-Surface-Pro-3
            System.out.println("lets wait");

            printWriter.println("your online!");

            printWriter.println("first operand");

            String firstOperand = bufferedReader.readLine();

            printWriter.println("second operand");
            String secondOperand =  bufferedReader.readLine();
            printWriter.println("operator");
            String operator = bufferedReader.readLine();
            double answer = (operator.equals("+")) ? (Double.parseDouble(firstOperand) + Double.parseDouble(secondOperand)) : Double.parseDouble(firstOperand) - Double.parseDouble(secondOperand);
            printWriter.println(("answer: " + answer));
        }catch (Exception e){e.printStackTrace();}
    }

    public static void task1() {
        try {
            server = new ServerSocket(PORTNUMBER);
            while (true){
                System.out.println("ping");
                Socket connection = server.accept();
                System.out.println("ping");
                Runnable runnable = () -> newClient(connection);
                runnable.run();
            }
        }catch (Exception e){e.printStackTrace();}
    }


    public static void task2() {
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);
            httpServer.createContext("/test", new MyHandler());
            httpServer.setExecutor(null);
            httpServer.start();

        }catch (Exception e){e.printStackTrace();}
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            final String[] response = {"<h1>yo</h1>\n<ul>"};
            t.getRequestHeaders().entrySet().forEach(e -> response[0] +="<li>" + e +"</li>\n");
            response[0]+= "</ul>";
            t.sendResponseHeaders(200, response[0].length());
            OutputStream os = t.getResponseBody();
            os.write(response[0].getBytes());
            os.close();
        }
    }

    public static void udpCalculatorServer() {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(PORTNUMBER);
            byte[] buffer = new byte[256];
            System.out.println("yo");

            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(datagramPacket);
            System.out.println("yo");
            System.out.println(buffer);

            System.out.println(new String(buffer));
            String req = new String(buffer);
            /*for (byte b : buffer) {
                System.out.print((char)b+"");
                req += ((char)b+"");
            }*/

            String[] arr= req.split("( )");
            System.out.println(Arrays.toString(arr));
            double d1 =Double.parseDouble(arr[0]);
            double d2 =Double.parseDouble(arr[1]);

            System.out.println("d1 " + d1);
            System.out.println("d2 " + d2);
            System.out.println("arr[2] " + arr[2]);

            double answer = (arr[2].contains("+")) ? d1 + d2 : d1 - d2;

            String plus = "";
            plus += arr[2];
            System.out.println("hereitcomse ");
            System.out.println(arr[2].toCharArray());


            if(arr[2].equals("+")){
                System.out.println("dsfgsdgsdfgdfgdfgd");
            }

            System.out.println("answer " + answer);
            DecimalFormat df = new DecimalFormat("#.##");
            String newDouble1 = df.format(answer);
            byte[] returnAnswer = newDouble1.getBytes();

//            datagramSocket.send(datagramPacket);

/*
            for (int i = 0; i < buffer.length; ++i) {
                if(buffer[i] == 0) {

                }
                buffer[i] = 3;
                System.out.println("what up homie");
            }*/
            DatagramPacket returnPacket = new DatagramPacket(buffer, buffer.length);

            datagramPacket.setData(returnAnswer);

//            System.out.println(Arrays.toString(datagramPacket.getData()));

//            double[] arr = new double[]
            for (byte b : datagramPacket.getData()) {
                System.out.print((char)b+"");
            }
            datagramSocket.send(datagramPacket);
        } catch (Exception e){e.printStackTrace();}
    }
    public static void main(String[] args) {
//        task1();
//        task2();

        udpCalculatorServer();

    }
}
