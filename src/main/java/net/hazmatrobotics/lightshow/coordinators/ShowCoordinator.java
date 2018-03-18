package net.hazmatrobotics.lightshow.coordinators;

import net.hazmatrobotics.lightshow.FakeSocket;
import net.hazmatrobotics.lightshow.ShowClient;
import net.hazmatrobotics.lightshow.ShowRunner;
import net.hazmatrobotics.lightshow.ShowServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShowCoordinator {
    List<ShowClient> clientList;
    List<ShowRunner> runnerList;
    Long startTime;
    Random r = new Random();

    private long OFFSET;

    public ShowCoordinator(List<ShowClient> clients) {
        this.clientList = clients;
        try {
            OFFSET = Long.parseLong(System.getenv("OFFSET"));
        } catch (NumberFormatException ignored) {
            OFFSET = 0;
        }

        if (MainCoordinator.LOAD_TEST) {
            for (int i = 0; i < MainCoordinator.LOAD_TEST_AMNT; i++) {
                this.clientList.add(new ShowClient().setSocket(new FakeSocket()));
            }
        }

        runnerList = new ArrayList<>(clientList.size());
    }

    public void start() {
        startTime = System.currentTimeMillis() + 3000;
        //ShowServer.console.send("##" + startTime);
        for (ShowClient c : clientList) {
            ShowRunner runner = new ShowRunner(c, startTime + OFFSET, r.nextLong());
            runnerList.add(runner);
        }
        System.out.println("Starting show with " + runnerList.size() + " clients");

        while (startTime > System.currentTimeMillis()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ShowServer.console.send("#GO#");
    }

    public void stop() {
        for (ShowRunner r : runnerList) {
            r.stop();
        }
    }

}
