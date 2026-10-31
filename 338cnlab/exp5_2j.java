import java.net.*;
import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 55555);
            System.out.println("Connected to chat server.");
            Thread receiveThread = new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }
                    System.out.println("Disconnected from server.");
                } catch (Exception e) {
                    System.out.println("Error receiving message: " + e);
                }
            });
            receiveThread.setDaemon(true);
            receiveThread.start();
            Scanner sc = new Scanner(System.in);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            while (true) {
                String message = sc.nextLine();
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
                out.println(message);
            }
            clientSocket.close();
        } catch (Exception e) {
            System.out.println("Unable to connect: " + e);
        }
    }
}import java.net.*;
import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 55555);
            System.out.println("Connected to chat server.");
            Thread receiveThread = new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }
                    System.out.println("Disconnected from server.");
                } catch (Exception e) {
                    System.out.println("Error receiving message: " + e);
                }
            });
            receiveThread.setDaemon(true);
            receiveThread.start();
            Scanner sc = new Scanner(System.in);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            while (true) {
                String message = sc.nextLine();
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
                out.println(message);
            }
            clientSocket.close();
        } catch (Exception e) {
            System.out.println("Unable to connect: " + e);
        }
    }
}
