package net.hazmatrobotics.lightshow.show;

import net.hazmatrobotics.lightshow.ClientObject;

public class ShowRunner implements Runnable {
    private ClientObject client;
    private Integer grade;
    private Long startTime;
    private Boolean shouldStop = false;
    private Integer i, j, k;
    Thread t;

    public ShowRunner(ClientObject client, long startTime) {
        this.client = client;
        this.grade = client.getGrade();
        this.startTime = startTime;
        t = new Thread(this, client.getId());
    }

    @Override
    public void run() {
        try { //TODO: Show here.
            Thread.sleep(0);
            w(0);
            System.out.println("Show Start");
            intro();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void intro() throws InterruptedException {
        s(0, 0, 0);
        w(3500);
        i = 255;
        dw(4591, () -> {
            i--;
            s(0, i, i);
            p(10);
        });
        i = 255;
        dw(5698, () -> {
            i--;
            s(0, i, i);
            p(10);
        });
        i = 255;
        dw(6787, () -> {
            i--;
            s(0, i, i);
            p(10);
        });
        i = 255;
        dw(7857, () -> {
            i--;
            s(0, i, i);
            p(10);
        });
        i = 255;
        dw(8952, () -> {
            i--;
            s(0, i, i);
            p(10);
        });
        i = 255;
        dw(9870, () -> {
            i--;
            s(0, i, i);
            p(10);
        });
        i = 255;
        dw(10071, () -> {
            i--;
            s(0, i, i);
            p(10);
        });
    }

    private void s(Integer r, Integer g, Integer b) {
        String out = "#" + componentToHex(r) + componentToHex(g) + componentToHex(b );
        client.send(out);
    }

    private long z() { //Current show time
        return System.currentTimeMillis() - startTime;
    }

    private void p(int millis) { //Pause for millis
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void w(long targetTime) { //Wait until show time
        while (z() < targetTime);
    }

    private void dw(long stopTime, Runnable doThis) throws InterruptedException { //Do until show time
        while (z() < stopTime) {
            doThis.run();
        }
    }

    private String componentToHex(Integer component) {
        if(component > 0) return Integer.toHexString(component);
        return "00";
    }

    public void stop() {
        shouldStop = true;
        t.interrupt();
    }
}
