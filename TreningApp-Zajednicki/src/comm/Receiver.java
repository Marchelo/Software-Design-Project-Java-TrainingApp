package comm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Receiver {

    private Socket socket;
    private ObjectInputStream in;

    public Receiver(Socket socket) throws IOException {
        this.socket = socket;
        in = new ObjectInputStream(socket.getInputStream());
    }

    public Object receive() throws Exception {
        return in.readObject();
    }
}