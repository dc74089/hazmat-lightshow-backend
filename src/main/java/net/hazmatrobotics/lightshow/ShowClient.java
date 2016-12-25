package net.hazmatrobotics.lightshow;

import org.java_websocket.WebSocket;

public class ShowClient {
    private WebSocket socket;
    private Integer grade;
    private Integer id;

    public void send(String colorString) {
        if (socket != null)
            socket.send(colorString);
    }

    public void setSocket(WebSocket socket) {
        this.socket = socket;
        this.id = socket.hashCode();
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
