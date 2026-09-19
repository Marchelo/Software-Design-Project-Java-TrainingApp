package server.threads;

import domm.Trener;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerThread extends Thread {
    List<ClientThread> clients = new ArrayList<>();
    ServerSocket serverSocket;
    boolean running = true;

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(9000);

            while (running) {
                Socket socket = serverSocket.accept();

                ClientThread client = new ClientThread(this, socket);
                clients.add(client);
                client.start();
            }
        } catch (Exception e) {
            System.out.println("ServerThread run(): " + e.getMessage());
        }
    }

    public boolean isLogged(Trener trener) {
        for (ClientThread cli : clients) {
            if (cli.getTrener() != null && cli.getTrener().getId() == trener.getId()) {
                System.out.println("Trener je vec ulogovan!");
                return true;
            }
        }
        return false;
    }

    public void stopServer() {
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (Exception e) {
            System.out.println("ServerThread stopServer(): " + e.getMessage());
        }
    }
}
