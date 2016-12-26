package net.hazmatrobotics.lightshow;

import java.util.Random;

public class ShowRunner implements Runnable { //TODO: More gracefully handle show stops.
    private ShowClient client;
    private Integer grade = 0;
    private Long startTime;
    private Boolean shouldStop = false;
    private Integer i, j, k;
    private Boolean d;
    private Random r;

    Color c1 = new Color("#4D9DE0");
    Color c2 = new Color("#E15554");
    Color c3 = new Color("#E1BC29");
    Color c4 = new Color("#3BB273");
    Color c5 = new Color("#7768AE");
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
            off();
            Thread.sleep(0);
            w(0);
            //System.out.println("Show Start");
            intro();
            verse1();
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
            p(5);
        });
        dw(28590, () -> {
            if (i > 20) i--;
            s(i, 20, 20);
            p(5);
        });
        off();
    }

    private void verse1() throws InterruptedException {
        w(29118);
        //Double bubble disco queen
        if (grade == 10) { /* *************************************************************************************** */
            i = 0;
            if (split(4) == 0) s(c1);
            w(29400);
            if (split(4) == 0) s(c2);
            w(29671);
            if (split(4) == 0) s(c3);
            w(29957);
            if (split(4) == 0) s(c4);
            w(30215);
            if (split(4) == 0) s(c5);
            w(30495);
            if (split(4) == 0) s(c3);
            w(30726);
            if (split(4) == 0) s(c1);
            w(31180);
            off();
        }
        w(29671);
        i = 128;
        dw(30726, () -> {
            i--;
            s(i, i, i);
            p(10);
        });
        i = 128;
        dw(31302, () -> {
            i--;
            s(i, i, i);
            p(10);
        });
        //Headed to the guillotine
        if (grade == 11) { /* ************************************************************************************** */
            i = 0;
            w(31302);
            if (split(4) == 0) s(c1);
            w(31589);
            if (split(4) == 0) s(c4);
            w(31859);
            if (split(4) == 0) s(c3);
            w(32154);
            if (split(4) == 0) s(c2);
            w(32388);
            if (split(4) == 0) s(c5);
            w(32711);
            if (split(4) == 0) s(c2);
            w(32952);
            if (split(4) == 0) s(c1);
            w(33379);
            off();
        }
        dw(31859, () -> {
            i--;
            s(i, i, i);
            p(10);
        });
        i = 128;
        dw(32952, () -> {
            i--;
            s(i, i, i);
            p(10);
        });
        i = 128;
        dw(33481, () -> {
            i--;
            s(i, i, i);
            p(10);
        });
        //Skin as cool as Steve McQueen
        if (grade == 9) { /* *************************************************************************************** */
            i = 0;
            w(33481);
            if (split(4) == 0) s(c4);
            w(33791);
            if (split(4) == 0) s(c2);
            w(24042);
            if (split(4) == 0) s(c1);
            w(34308);
            if (split(4) == 0) s(c3);
            w(34478);
            if (split(4) == 0) s(c5);
            w(34852);
            if (split(4) == 0) s(c4);
            w(35107);
            if (split(4) == 0) s(c3);
            w(35517);
            off();
        }
        dw(34042, () -> {
            i--;
            s(i, i, i);
            p(10);
        });
    }

    private void s(Integer r, Integer g, Integer b) {
        String out = "#" + componentToHex(constrain(r)) + componentToHex(constrain(g)) + componentToHex(constrain(b));
        client.send(out);
    }

    private void s(Color c) {
        client.send(c.getHex());
    }

    private void off() {
        s(20, 20, 20);
    }

    private long z() { //Current show time
        return System.currentTimeMillis() - startTime;
    }

    private void w(long targetTime) throws InterruptedException { //Wait until show time
        while (z() < targetTime) {
            Thread.sleep(5);
        }
    }

    private void p(int millis) { //Pause for millis
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
        if (component > 0) return Integer.toHexString(component);
        return "00";
    }

    public void stop() {
        shouldStop = true;
        t.interrupt();
    }
}
