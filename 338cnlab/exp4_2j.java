import java.net.*;
import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        Socket clientSocket = new Socket("localhost", 65432);
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        while (true) {
            System.out.print("Enter a message: ");
            String message = sc.nextLine();
            out.println(message);
            String data = in.readLine();
            System.out.println("Echoed from server: " + data);
            if (message.equalsIgnoreCase("exit")) {
                break;
            }
        }
        clientSocket.close();
    }
}
