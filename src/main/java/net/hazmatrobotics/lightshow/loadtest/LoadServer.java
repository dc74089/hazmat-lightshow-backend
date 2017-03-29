package net.hazmatrobotics.lightshow.loadtest;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class LoadServer extends WebSocketServer {
    public static List<WebSocket> clientList = new ArrayList<>();
    private WebSocket console;
    public static String state = "0";

    public LoadServer(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        synchronized (clientList) {
            clientList.add(webSocket);
        }
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {

    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        if (message.equals("-542696465")) {
            System.out.println("Authenticated Console");
            console = conn;
            conn.send("Authenticated.");
            return;
        }

        if(conn.equals(console)) {
            state = message;
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    public static void sendAll(String colorString) {
        synchronized (clientList) {
            for (WebSocket c : clientList) {
                if (c != null && c.getReadyState() == WebSocket.READYSTATE.OPEN)
                    c.send(colorString);
            }
        }
    }
}
