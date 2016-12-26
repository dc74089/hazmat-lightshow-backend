package net.hazmatrobotics.lightshow;

import java.util.Random;

public class ShowRunner implements Runnable { //TODO: More gracefully handle show stops.
    private ShowClient client;
    private Integer grade = 0;
    private Long startTime;
    private Boolean shouldStop = false;
    private Integer i, j, k;
    private Float f, g, h;
    private Boolean d;
    private Random r;

    Color c1 = new Color("#F05500");
    Color c2 = new Color("#EE2400");
    Color c3 = new Color("#F4AC00");
    Color c4 = new Color("#FFDB00");
    Color c5 = new Color("#FF9A00");
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
            //ohFade1();
            off(); //TODO: Remove this
            verse1();
        } catch (InterruptedException | RuntimeException e) {
            e.printStackTrace();
        }

        System.out.println("Show End");
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
        if (i == 0) s(c1);
        w(10540);
        if (i == 1) s(c2);
        w(11124);
        if (i == 2) s(c3);
        w(11505);
        if (i == 3) s(c4);
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
    }

    private void ohFade1() throws InterruptedException {
        //Sustained oh
        i = 255 + split(128);
        d = false;
        w(20415);
        f = 15f;
        dw(27239, () -> {
            if (d) i++;
            else i--;
            if (i < 128) d = true;
            if (i > 254) d = false;
            s(i, 0, 0);

            f -= 0.018f;
            System.out.println(f);
            p(Math.round(f));
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
            off();
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
        } else {
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
        }
        //Headed to the guillotine
        if (grade == 11) { /* ************************************************************************************** */
            i = 0;
            off();
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
        } else {
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
        }
        //Skin as cool as Steve McQueen
        if (grade == 9) { /* *************************************************************************************** */
            i = 0;
            off();
            w(33481);
            if (split(4) == 0) s(c4);
            w(33791);
            if (split(4) == 0) s(c2);
            w(34042);
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
        } else {
            dw(34042, () -> {
                i--;
                s(i, i, i);
                p(10);
            });
            i = 128;
            dw(35107, () -> {
                i--;
                s(i, i, i);
                p(10);
            });
            i = 128;
            dw(35669, () -> {
                i--;
                s(i, i, i);
                p(10);
            });
        }
        //Let me be your killer king (all grades)
        /* ********************************************************************************************************* */
        i = split(7);
        off();
        w(35669);
        if (i == 0) s(c5);
        w(35956);
        if (i == 1) s(c4);
        w(36203);
        if (i == 2) s(c1);
        w(36487);
        if (i == 3) s(c2);
        w(36740);
        if (i == 4) s(c3);
        w(37007);
        if (i == 5) s(c2);
        w(37262);
        if (i == 6) s(c4);
        w(37634);
        off();
/* ********************************************************************************************************************
 **********************************************************************************************************************
 ******************************************************************************************************************** */
        //Hurts until it stops
        if (grade == 10) { /* *************************************************************************************** */
            i = 0;
            off();
            w(37854);
            if (split(4) == 0) s(c1);
            w(38150);
            if (split(4) == 0) s(c2);
            w(38388);
            if (split(4) == 0) s(c3);
            w(38702);
            if (split(4) == 0) s(c4);
            w(38818);
            if (split(4) == 0) s(c5);
            w(39233);
            off();
        } else {
            w(38388);
            i = 128;
            dw(39497, () -> {
                i--;
                s(i, i, i);
                p(10);
            });
        }
        //We will love until it's not
        if (grade == 11) { /* ************************************************************************************** */
            i = 0;
            off();
            w(39497);
            if (split(4) == 0) s(c1);
            w(39758);
            if (split(4) == 0) s(c4);
            w(40072);
            if (split(4) == 0) s(c3);
            w(40316);
            if (split(4) == 0) s(c2);
            w(40580);
            if (split(4) == 0) s(c5);
            w(40867);
            if (split(4) == 0) s(c2);
            w(41099);
            if (split(4) == 0) s(c1);
            w(41401);
            off();
        }
        dw(40580, () -> {
            i--;
            s(i, i, i);
            p(10);
        });
        i = 128;
        dw(41671, () -> {
            i--;
            s(i, i, i);
            p(10);
        });
        //I'm a killing spree in white
        if (grade == 9) { /* *************************************************************************************** */
            i = 0;
            off();
            w(41671);
            if (split(4) == 0) s(c4);
            w(42025);
            if (split(4) == 0) s(c2);
            w(42217);
            if (split(4) == 0) s(c1);
            w(42472);
            if (split(4) == 0) s(c3);
            w(42643);
            if (split(4) == 0) s(c5);
            w(43000);
            if (split(4) == 0) s(c4);
            w(43230);
            if (split(4) == 0) s(c3);
            w(43607);
            off();
        }
        i = 128;
        dw(42643, () -> {
            i--;
            s(i, i, i);
            p(10);
        });
        i = 128;
        dw(43828, () -> {
            i--;
            s(i, i, i);
            p(10);
        });
        //Ahh
        /* ********************************************************************************************************* */
        w(43828);
        s(255, 255, 255);
        w(44368);
        off();
        //Eyes like broken christmas lights (all grades)
        /* ********************************************************************************************************* */
        i = split(7);
        off();
        w(44382);
        if (i == 0) s(c5);
        w(44678);
        if (i == 1) s(c4);
        w(44934);
        if (i == 2) s(c1);
        w(45212);
        if (i == 3) s(c2);
        w(45430);
        if (i == 4) s(c3);
        w(45755);
        if (i == 5) s(c2);
        w(46031);
        if (i == 6) s(c4);
        w(46277);
        off();
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
        if (shouldStop) return Long.MAX_VALUE;
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
            System.out.println("Interrupted.");
            shouldStop = true;
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
    }
}
