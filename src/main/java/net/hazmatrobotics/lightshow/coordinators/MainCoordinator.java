package net.hazmatrobotics.lightshow.coordinators;

import net.hazmatrobotics.lightshow.WebsocketServer;

public class MainCoordinator {
    private static WebsocketServer server;
    private static ShowCoordinator sc;
    private static Boolean accepting = true;
    public static final Boolean LOAD_TEST = false;
    public static final Integer LOAD_TEST_AMNT = 1000;

    public static void main(String... args) throws InterruptedException {
        server = new WebsocketServer(901);
        server.run();

        //noinspection InfiniteLoopStatement
        while (true) {
            Thread.sleep(100);
        }
    }

    public static void setAccepting(Boolean shouldAccept) {
        if (shouldAccept) {
            accepting = true;
            server.setAccepting(true);

        } else {
            accepting = false;
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
