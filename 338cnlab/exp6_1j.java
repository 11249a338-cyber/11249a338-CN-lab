import java.net.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Server listening on port 12345...");
        Socket connection = serverSocket.accept();
        System.out.println("Connected by " + connection.getInetAddress());
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String filename = in.readLine();
        System.out.println("Receiving file: " + filename);
        FileOutputStream file = new FileOutputStream("received_" + filename);
        InputStream input = connection.getInputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            file.write(buffer, 0, bytesRead);
        }
        file.close();
        System.out.println("File received and saved as received_" + filename);
        connection.close();
        serverSocket.close();
    }
}
