package net.hazmatrobotics.lightshow;

import net.hazmatrobotics.lightshow.coordinators.MainCoordinator;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebsocketServer extends WebSocketServer {
    private Boolean accepting = true;
    private List<ShowClient> clientList = new ArrayList<>();
    private Map<WebSocket, ShowClient> socketClientMap = new HashMap<>();
    public static WebSocket console;

    public WebsocketServer(int port) {
        super(new InetSocketAddress(port));
    }

    public WebsocketServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        //System.out.println("New socket connection! ");

        if (!accepting) {
            System.out.println("Ignoring new connection because we are not accepting. ");
            conn.send("We are not accepting");
            conn.close();
        }

        ShowClient client = new ShowClient();
        client.setSocket(conn);
        clientList.add(client);
        socketClientMap.put(conn, client);
        if (console != null) console.send("" + clientList.size() + " clients");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {

    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        if (message.equals("-542696465")) {
            System.out.println("Authenticated Console");
            console = conn;
            clientList.remove(conn);
            socketClientMap.remove(conn);
            conn.send("Authenticated.");
            return;
        }

        if (conn.equals(console)) {
            switch (message) {
                case "startAccepting":
                    MainCoordinator.setAccepting(true);
                    break;
                case "stopAccepting":
                    MainCoordinator.setAccepting(false);
                    break;
                case "start":
                    MainCoordinator.start();
                    break;
                case "stop":
                    MainCoordinator.stop();
                    break;
                case "cleanup":
                    MainCoordinator.cleanup();
            }
            return;
        }

        try {
            Integer grade = Integer.parseInt(message);
            if (9 <= grade && grade <= 12) {
                socketClientMap.get(conn).setGrade(grade);
                conn.send(message);
            } else {
                conn.send("Invalid Grade... Disconnecting");
                clientList.remove(conn);
                socketClientMap.remove(conn);
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    public void setAccepting(Boolean accepting) {
        this.accepting = accepting;
    }

    public List<ShowClient> getClientList() {
        return new ArrayList<>(clientList);
    }

    public void cleanup() {
        for (WebSocket s : socketClientMap.keySet()) {
            try {
                s.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        clientList.clear();
        socketClientMap.clear();
    }
}
