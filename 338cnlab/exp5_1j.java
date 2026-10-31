import java.net.*;
import java.io.*;
import java.util.*;
public class Main {
    static List<Socket> clients = Collections.synchronizedList(new ArrayList<>());
    static void handleClient(Socket clientSocket, SocketAddress clientAddress) {
        System.out.println("New connection from " + clientAddress);
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Received from " + clientAddress + ": " + message);
                synchronized (clients) {
                    for (Socket client : clients) {
                        if (client != clientSocket) {
                            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                            out.println(message);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error with " + clientAddress + ": " + e);
        }
        clients.remove(clientSocket);
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(clientAddress + " disconnected");
    }
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(55555);
        System.out.println("Server listening on port 55555...");
        while (true) {
            Socket clientSocket = serverSocket.accept();
            clients.add(clientSocket);
            Thread thread = new Thread(() -> handleClient(clientSocket, clientSocket.getRemoteSocketAddress()));
            thread.setDaemon(true);
            thread.start();
        }
    }
}
