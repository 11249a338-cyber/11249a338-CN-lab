import java.net.*;
import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        Socket clientSocket = new Socket("localhost", 12345);
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the filename to send: ");
        String filename = sc.nextLine();
        OutputStream out = clientSocket.getOutputStream();
        out.write((filename + "\n").getBytes());
        try {
            FileInputStream file = new FileInputStream(filename);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = file.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            file.close();
            clientSocket.shutdownOutput();
            System.out.println("File " + filename + " sent successfully");
        } catch (FileNotFoundException e) {
            System.out.println("File " + filename + " not found");
        }
        clientSocket.close();
    }
}
