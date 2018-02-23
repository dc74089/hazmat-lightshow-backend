package net.hazmatrobotics.lightshow.coordinators;

import net.hazmatrobotics.lightshow.ShowServer;
import net.hazmatrobotics.lightshow.loadtest.LoadRunner;
import net.hazmatrobotics.lightshow.loadtest.LoadServer;

public class MainCoordinator {
    private static ShowServer server;
    private static LoadServer loadServer;
    private static ShowCoordinator sc;
    public static final Boolean LOAD_TEST = false;
    public static final Integer LOAD_TEST_AMNT = 1000;
    public static LoadRunner lr;

    public static void main(String... args) throws InterruptedException {
        server = new ShowServer(8000);
        server.start();

        System.out.println("Version 2018.1");
        System.out.println("Boot.");

        if(LOAD_TEST) {
            loadServer = new LoadServer(8080);
            loadServer.start();
            lr = new LoadRunner();
            lr.start();
        }

        //noinspection InfiniteLoopStatement
        while (true) {
            Thread.sleep(100);
        }
    }

    public static void setAccepting(Boolean shouldAccept) {
        server.setAccepting(shouldAccept);
    }

    public static void start() {
        System.out.println("Starting");
        sc = new ShowCoordinator(server.getClientList());
        sc.start();
    }

    public static void stop() {
        if (sc != null)
            sc.stop();
    }

    public static void cleanup() {
        server.cleanup();
    }
}
