package net.hazmatrobotics.lightshow;

import java.util.Random;

public class ShowRunner implements Runnable { //TODO: More gracefully handle show stops.
    private ShowClient client;
    private Integer grade = 0;
    private Long startTime;
    private Boolean shouldStop = false;
    private Integer i, j, k;
    private Float f, g, h;
    private int minute = 0;
    private Boolean d;
    private Random r;

    private Color c1, c2, c3, c4, c5;
    final Color white = new Color(255, 255, 255);
    private Thread t;

    public ShowRunner(ShowClient client, long startTime, long seed) {
        this.client = client;
        this.grade = client.getGrade();
        this.startTime = startTime;
        r = new Random(seed);
        t = new Thread(this, client.getId());
        t.start();
    }

    private void fire() {
        c1 = new Color(240, 85, 0);
        c2 = new Color(238, 36, 0);
        c3 = new Color(244, 172, 0);
        c4 = new Color(255, 219, 0);
        c5 = new Color(255, 154, 0);
    }

    private void crayons() {
        c1 = new Color(255, 0, 0);
        c2 = new Color(0, 255, 0);
        c3 = new Color(0, 0, 255);
        c4 = new Color(255, 255, 0);
        c5 = new Color(0, 255, 255);
    }

    private void allWhite() {
        c1 = white;
        c2 = white;
        c3 = white;
        c4 = white;
        c5 = white;
    }

    @Override
    public void run() {
        try {
            off();
            Thread.sleep(0);
            w(0);
            //System.out.println("Show Start");
            intro();
            //ohFade();
            verse1();
            prechorus1();
            chorus1();
            breakdown();
        } catch (InterruptedException | RuntimeException e) {
            e.printStackTrace();
        }

        System.out.println("Show End");
    }

    private void intro() throws InterruptedException {
        fire();
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

    private void ohFade() throws InterruptedException {
        //Sustained oh
        i = 255 + split(128);
        d = false;
        w(20415);
        f = 15f;
        dw(28552, () -> {
            if (d) i++;
            else i--;
            if (i < 128) d = true;
            if (i > 254) d = false;
            s(i, 0, 0);

            f -= 0.018f;
            if (f <= 1) f = 1f;
            p(Math.round(f));
        });
        dw(29095, () -> {
            if (i > 20) i--;
            s(i, 20, 20);
            p(2);
        });
        off();
    }

    private void verse1() throws InterruptedException {
        off();
        w(29118);
        //Double bubble disco queen
        if (grade == 10) { /* *************************************************************************************** */
            i = 0;
            off();
            s(rc());
            w(29400);
            s(rc());
            w(29671);
            s(rc());
            w(29957);
            s(rc());
            w(30215);
            s(rc());
            w(30495);
            s(rc());
            w(30726);
            s(rc());
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
            s(rc());
            w(31589);
            s(rc());
            w(31859);
            s(rc());
            w(32154);
            s(rc());
            w(32388);
            s(rc());
            w(32711);
            s(rc());
            w(32952);
            s(rc());
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
            s(rc());
            w(33791);
            s(rc());
            w(34042);
            s(rc());
            w(34308);
            s(rc());
            w(34478);
            s(rc());
            w(34852);
            s(rc());
            w(35107);
            s(rc());
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
            w(37916);
            s(rc());
            w(38222);
            s(rc());
            w(38447);
            s(rc());
            w(38735);
            s(rc());
            w(38982);
            s(rc());
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
            s(rc());
            w(39758);
            s(rc());
            w(40072);
            s(rc());
            w(40316);
            s(rc());
            w(40580);
            s(rc());
            w(40867);
            s(rc());
            w(41099);
            s(rc());
            w(41401);
            off();
        } else {
            i = 128;
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
        }
        //I'm a killing spree in white
        if (grade == 9) { /* *************************************************************************************** */
            i = 0;
            off();
            w(41671);
            s(rc());
            w(42025);
            s(rc());
            w(42217);
            s(rc());
            w(42472);
            s(rc());
            w(42643);
            s(rc());
            w(43000);
            s(rc());
            w(43230);
            s(rc());
            w(43607);
            off();
        } else {
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
        }
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

    private void prechorus1() throws InterruptedException {
        //My touch is black and poisonous
        w(46263); //My
        if (grade == 9) fd(rc(), 0.8f);
        w(46561); //touch
        if (grade == 12) fd(rc(), 0.8f);
        w(47397); //is
        if (grade == 10) fd(rc(), 0.8f);
        w(47643); //black
        if (grade == 11) fd(rc(), 0.8f);
        w(47961); //and
        if (grade == 9) fd(rc(), 0.8f);
        w(48212); //poi-
        if (grade == 12) fd(rc(), 0.8f);
        w(48509); //-son-
        if (grade == 10) fd(rc(), 0.8f);
        w(48766); //-ous
        if (grade == 11) fd(rc(), 0.8f);
        //And nothing like my
        w(49591); //and
        if (grade == 12) fd(rc(), 0.8f);
        w(49843); //no-
        if (grade == 10) fd(rc(), 0.8f);
        w(50130); //-thing
        if (grade == 11) fd(rc(), 0.8f);
        w(50318); //like
        if (grade == 9) fd(rc(), 0.8f);
        w(50679); //my
        if (grade == 12) fd(rc(), 0.8f);
        w(50940); //Punch
        if (grade == 10 || grade == 11) fd(rc(), 0.8f);
        w(51478); //Drunk
        if (grade == 9 || grade == 12) s(rc());
        w(51755);
        off();
        //Kiss I know you need it till you feel it drink of water drink of wine
        w(52032);
        if (grade == 10) s(c5);
        else off();
        w(52168);
        if (grade == 9) s(c5);
        else off();
        w(52304);
        if (grade == 11) s(c5);
        else off();
        w(52440);
        if (grade == 12) s(c5);
        else off();
        w(52576);
        if (grade == 9) s(c5);
        else off();
        w(52712);
        if (grade == 11) s(c5);
        else off();
        w(52848);
        if (grade == 10) s(c5);
        else off();
        w(52984);
        if (grade == 12) s(c5);
        else off();
        w(53120);
        if (grade == 9) s(c5);
        else off();
        w(53256);
        if (grade == 12) s(c5);
        else off();
        w(53392);
        if (grade == 11) s(c5);
        else off();
        w(53528);
        if (grade == 9) s(c5);
        else off();
        w(53664);
        if (grade == 10) s(c5);
        else off();
        w(53800);
        if (grade == 11) s(c5);
        else off();
        w(53936);
        if (grade == 12) s(c5);
        else off();
        w(54072);
        if (grade == 9) s(c5);
        else off();
        w(54207);
        s(c5);
        w(54796);
        fd(c5, 0.4f);
        off();
    }

    private void chorus1() throws InterruptedException {
        i = split(4);
        w(55319);
        if (i == 0) fd(c3, 8);
        w(59683);
        if (i == 1) fd(c5, 8);
        setMinute(1);
        w(4055);
        if (i == 2) fd(c2, 6);
    }

    private void breakdown() throws InterruptedException {
        crayons();
        setMinute(1);
        w(11173);
        off();
        i = 0;
        if (grade == 12) dw(12684, () -> {
            s(i, i, i);
            i++;
            p(5);
        });
        off();
        i = 3;
        w(12842);
        if (grade == 10) dw(14789, () -> {
            s(wheel(Math.round(z() / i)));
            p(10);
        });
        off();
        w(14946);
        if (grade == 11) dw(16970, () -> {
            s(wheel(Math.round(z() / i)));
            p(10);
        });
        off();
        w(17204);
        if (grade == 9) dw(19147, () -> {
            s(wheel(Math.round(z() / i)));
            p(10);
        });
        off();
        w(19403);
        if (grade == 12) dw(20163, () -> {
            s(wheel(Math.round(z() / i)));
            p(10);
        });
        else {
            j = split(4);
            w(20163);
            if (j == 0) s(c1);
            w(20397);
            if (j == 1) s(c2);
            w(20847);
            if (j == 2) s(c3);
            w(21310);
            if (j == 3) s(c4);
        }
        off();
        /* ********************************************************************************************************* */
        allWhite();
        k = split(8);
        w(21607);
        if (grade == 10) dw(23532, () -> {
            s(wheel(Math.round(z() / i + (64 * k))));
            p(10);
        });
        off();
        w(23694);
        if (grade == 11) dw(25735, () -> {
            s(wheel(Math.round(z() / i + (64 * k))));
            p(10);
        });
        off();
        w(25933);
        if (grade == 9) dw(27916, () -> {
            s(wheel(Math.round(z() / i + (64 * k))));
            p(10);
        });
        off();
        w(28105);
        if (grade == 12) dw(28910, () -> {
            s(wheel(Math.round(z() / i + (64 * k))));
            p(10);
        });
        off();
        j = split(4);
        w(28910);
        if (j == 0) s(c1);
        w(29162);
        if (j == 1) s(c2);
        w(29544);
        if (j == 2) s(c3);
        w(30044);
        if (j == 3) s(c4);

        off();
    }

    private void off() {
        s(20, 20, 20);
    }

    private void s(Integer r, Integer g, Integer b) {
        String out = "#" + componentToHex(constrain(r)) + componentToHex(constrain(g)) + componentToHex(constrain(b));
        client.send(out);
    }

    private void s(Color c) {
        client.send(c.getHex());
    }

    private long z() { //Current show time
        if (shouldStop) return Long.MAX_VALUE;
        return System.currentTimeMillis() - startTime;
    }

    private void w(long targetTime) throws InterruptedException { //Wait until show time
        targetTime += 60000 * minute;
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
        stopTime += 60000 * minute;
        while (z() < stopTime) {
            doThis.run();
        }
    }

    private void fd(Color color, float secs) { //Fade down color over number of seconds
        int r, g, b;
        r = color.r;
        g = color.g;
        b = color.b;
        float scalar = 1;
        float step = 1 / (40 * secs);

        while (scalar >= 0) {
            s(Math.round(r * scalar), Math.round(g * scalar), Math.round(b * scalar));
            scalar -= step;
            p(25);
        }
    }

    private Color rc() { //Get random color
        int i = r.nextInt() % 5;
        if (i == 1) return c1;
        if (i == 2) return c2;
        if (i == 3) return c3;
        if (i == 4) return c4;
        if (i == 0) return c5;

        return c1;
    }

    private Color wheel(int pos) {
        pos = pos % 255;
        pos = 255 - pos;
        if (pos < 85) {
            return new Color(255 - pos * 3, 0, pos * 3);
        }
        if (pos < 170) {
            pos -= 85;
            return new Color(0, pos * 3, 255 - pos * 3);
        }
        pos -= 170;
        return new Color(pos * 3, 255 - pos * 3, 0);
    }

    private int split(int groups) {
        return Math.abs(r.nextInt()) % groups;
    }

    private int constrain(int i) {
        if (i > 255) return 255;
        if (i < 0) return 0;
        return i;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    private String componentToHex(Integer component) {
        return Integer.toHexString(component).length() == 1 ?
                "0" + Integer.toHexString(component) : Integer.toHexString(component);
    }

    public void stop() {
        shouldStop = true;
    }
}
