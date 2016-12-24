package net.hazmatrobotics.lightshow;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientObject {
    private Socket socket;
    private Integer grade;
    private PrintWriter out;
    private String id;

    public ClientObject(Socket socket) throws IOException {
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void send(String colorString) {
        out.println(colorString);
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getGrade() {
        return grade;
    }

    public String getId() {
        return id;
    }
}
