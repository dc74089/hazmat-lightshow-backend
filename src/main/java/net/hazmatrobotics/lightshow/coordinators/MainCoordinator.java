package net.hazmatrobotics.lightshow.coordinators;

import net.hazmatrobotics.lightshow.LightShowServer;
import net.hazmatrobotics.lightshow.WebServerTest;

public class MainCoordinator {
    private static LightShowServer server;
    private static ShowCoordinator sc;
    public static final Boolean LOAD_TEST = false;
    public static final Integer LOAD_TEST_AMNT = 1000;
    public static final Integer PORT = 8000;

    public static void main(String... args) throws InterruptedException {
        System.out.println("Version 1");
        server = new LightShowServer(PORT);
        server.start();

        System.out.println("Boot on port " + server.getPort());
/*
        try {
            WebServerTest.main(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        //noinspection InfiniteLoopStatement
        while (true) {
            Thread.sleep(100);
        }
    }

    public static void setAccepting(Boolean shouldAccept) {
        if (shouldAccept) {
            server.setAccepting(true);
        } else {
            server.setAccepting(false);
        }
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
