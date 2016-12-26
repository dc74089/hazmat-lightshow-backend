package net.hazmatrobotics.lightshow;

import java.util.Random;

public class ShowRunner implements Runnable {
    private ShowClient client;
    private Integer grade = 0;
    private Long startTime;
    private Boolean shouldStop = false;
    private Integer i, j, k;
    private Boolean d;
    private Random r;
    Thread t;

    public ShowRunner(ShowClient client, long startTime, long seed) {
        this.client = client;
        this.grade = client.getGrade();
        this.startTime = startTime;
        r = new Random(seed);
        t = new Thread(this, client.getId());
        t.start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(0);
            w(0);
            //System.out.println("Show Start");
            intro();
        } catch (InterruptedException | RuntimeException e) {
            e.printStackTrace();
        }
    }

    private void intro() throws InterruptedException {
        off();
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
        dw(10071, () -> {
            i--;
            s(0, i, i);
            p(10);
        });
        off();
        //Victorious
        i = split(4);
        w(10314);
        if (i == 0) s(255, 141, 0);
        w(10540);
        if (i == 1) s(255, 231, 0);
        w(11124);
        if (i == 2) s(255, 180, 0);
        w(11505);
        if (i == 3) s(255, 0, 0);
        //Oh oh oh oh
        w(11926);
        s(20, 20, 20);
        if (grade == 12) s(255, 0, 0);
        w(12527);
        if (grade == 10) s(255, 0, 0);
        w(13050);
        if (grade == 11) s(255, 0, 0);
        w(13567);
        if (grade == 9) s(255, 0, 0);
        //Victorious
        i = split(4);
        w(14687);
        if (i == 0) off();
        w(14953);
        if (i == 1) off();
        w(15500);
        if (i == 2) off();
        w(15877);
        if (i == 3) off();
        //Oh oh oh oh
        w(16318);
        s(20, 20, 20);
        if (grade == 12) s(255, 0, 0);
        w(16869);
        if (grade == 10) s(255, 0, 0);
        w(17412);
        if (grade == 11) s(255, 0, 0);
        w(17957);
        if (grade == 9) s(255, 0, 0);
        //Sustained oh
        i = 255 + split(128);
        d = false;
        w(20415);
        dw(27239, () -> {
            if (d) i++;
            else i--;
            if (i < 128) d = true;
            if (i > 254) d = false;
            s(i, 0, 0);
            sleep(5);
        });
        dw(28590, () -> {
            if (i > 20) i--;
            s(i, 20, 20);
            sleep(5);
        });
        off();
    }

    private void s(Integer r, Integer g, Integer b) {
        String out = "#" + componentToHex(constrain(r)) + componentToHex(constrain(g)) + componentToHex(constrain(b));
        client.send(out);
    }

    private void off() {
        s(20, 20, 20);
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

    private void w(long targetTime) throws InterruptedException { //Wait until show time
        while (z() < targetTime) {
            Thread.sleep(5);
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void dw(long stopTime, Runnable doThis) throws InterruptedException { //Do until show time
        while (z() < stopTime) {
            doThis.run();
        }
    }

    private int split(int groups) {
        return Math.abs(r.nextInt()) % groups;
    }

    private int constrain(int i) {
        if (i > 255) return 255;
        if (i < 0) return 0;
        return i;
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
