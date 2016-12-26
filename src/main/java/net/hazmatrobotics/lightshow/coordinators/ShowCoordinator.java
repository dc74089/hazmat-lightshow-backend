package net.hazmatrobotics.lightshow.coordinators;

import net.hazmatrobotics.lightshow.FakeSocket;
import net.hazmatrobotics.lightshow.ShowClient;
import net.hazmatrobotics.lightshow.ShowRunner;
import net.hazmatrobotics.lightshow.WebsocketServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShowCoordinator {
    List<ShowClient> clientList;
    List<ShowRunner> runnerList;
    Long startTime;
    Random r = new Random();

    public ShowCoordinator(List<ShowClient> clients) {
        this.clientList = clients;

        if (MainCoordinator.LOAD_TEST) {
            for (int i = 0; i < MainCoordinator.LOAD_TEST_AMNT; i++) {
                this.clientList.add(new ShowClient().setSocket(new FakeSocket()));
            }
        }

        runnerList = new ArrayList<>(clientList.size());
    }

    public void start() {
        startTime = System.currentTimeMillis() + 3000;
        WebsocketServer.console.send("##" + startTime);
        for (ShowClient c : clientList) {
            ShowRunner runner = new ShowRunner(c, startTime, r.nextLong());
            runnerList.add(runner);
        }
        System.out.println("Starting show with " + runnerList.size() + " clients");
    }

    public void stop() {
        for (ShowRunner r : runnerList) {
            r.stop();
        }
    }

}
