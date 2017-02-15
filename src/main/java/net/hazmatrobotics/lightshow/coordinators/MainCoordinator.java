package net.hazmatrobotics.lightshow.coordinators;

import net.hazmatrobotics.lightshow.Secret;
import net.hazmatrobotics.lightshow.ShowServer;
import okhttp3.*;

import java.io.IOException;

public class MainCoordinator {
    private static ShowServer server;
    private static ShowCoordinator sc;
    public static final Boolean LOAD_TEST = false;
    public static final Integer LOAD_TEST_AMNT = 1000;

    public static void main(String... args) throws InterruptedException {
        updateDNS();

        server = new ShowServer(8000);
        server.start();

        System.out.println("Version 4");
        System.out.println("Boot.");

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

    private static void updateDNS() {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("www.duckdns.org")
                .addPathSegment("update")
                .addQueryParameter("domains", Secret.duckDomain)
                .addQueryParameter("token", Secret.duckKey)
                .addQueryParameter("verbose", "true")
                .build();

        Request r = new Request.Builder()
                .url(url)
                .build();

        new OkHttpClient().newCall(r).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.print("Updated DNS: ");
                System.out.println(response.body().string().replace("\n", " "));
            }
        });
    }
}
