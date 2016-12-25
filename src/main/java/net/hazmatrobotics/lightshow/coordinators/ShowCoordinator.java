package net.hazmatrobotics.lightshow.coordinators;

import net.hazmatrobotics.lightshow.ShowClient;
import net.hazmatrobotics.lightshow.ShowRunner;
import net.hazmatrobotics.lightshow.WebsocketServer;

import java.util.ArrayList;
import java.util.List;

public class ShowCoordinator {
    List<ShowClient> clientList;
    List<ShowRunner> runnerList;
    Long startTime;

    public ShowCoordinator(List<ShowClient> clients) {
        this.clientList = clients;
        runnerList = new ArrayList<>(clientList.size());
    }

    public void start() {
        startTime = System.currentTimeMillis() + 3000;
        WebsocketServer.console.send("##" + startTime);
        for (ShowClient c : clientList) {
            ShowRunner runner = new ShowRunner(c, startTime);
            runnerList.add(runner);
        }
    }

    public void stop() {
        for (ShowRunner r : runnerList) {
            r.stop();
        }
    }

}
