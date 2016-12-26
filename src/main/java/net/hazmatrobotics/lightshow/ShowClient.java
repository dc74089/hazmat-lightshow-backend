package net.hazmatrobotics.lightshow;

import org.java_websocket.WebSocket;

public class ShowClient {
    private WebSocket socket;
    private Integer grade = 0;
    private Integer id;
    private long lastSendTime;

    public void send(String colorString) {
        if (socket != null && socket.getReadyState() == WebSocket.READYSTATE.OPEN)
            socket.send(colorString);
        System.out.println(System.currentTimeMillis() - lastSendTime);
        lastSendTime = System.currentTimeMillis();
    }

    public ShowClient setSocket(WebSocket socket) {
        this.socket = socket;
        this.id = socket.hashCode();
        this.lastSendTime = System.currentTimeMillis();
        return this;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getGrade() {
        return grade;
    }

    public String getId() {
        return id.toString();
    }
}
