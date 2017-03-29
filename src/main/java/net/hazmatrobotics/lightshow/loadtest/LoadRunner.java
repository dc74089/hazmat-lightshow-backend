package net.hazmatrobotics.lightshow.loadtest;

import net.hazmatrobotics.lightshow.Color;
import net.hazmatrobotics.lightshow.ShowRunner;
import org.java_websocket.WebSocket;

import java.text.CollationElementIterator;

public class LoadRunner extends Thread {

    @Override
    public void run() {
        while(true) {
            switch (LoadServer.state) {
                case "1":
                    LoadServer.sendAll(System.currentTimeMillis() % 1000 < 100 ? "#27f700" : "#808080");
                    break;
                case "2":
                    LoadServer.sendAll(System.currentTimeMillis() % 500 < 100 ? "#27f700" : "#808080");
                    break;
                case "3":
                    LoadServer.sendAll(System.currentTimeMillis() % 200 < 100 ? "#27f700" : "#808080");
                    break;
                case "4":
                    int val = (int) System.currentTimeMillis() / 10 % 255;
                    LoadServer.sendAll(new Color(255 - val, 255 - val, 255 - val).getHex());
                    break;
                case "10":
                    LoadServer.sendAll(ShowRunner.wheel((int) System.currentTimeMillis()/10).getHex());
                    break;
                default:
                    LoadServer.sendAll("#000000");
                    break;
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
