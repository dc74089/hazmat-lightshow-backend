package net.hazmatrobotics.lightshow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShowRunner implements Runnable { //TODO: More gracefully handle show stops.
    private ShowClient client;
    private Integer grade = 0;
    private long lastSend = 0;
    private Long startTime;
    private Boolean shouldStop = false;
    private Integer i, j, k;
    private Float f, g, h;
    private boolean direction;
    private int minute = 0;
    private Boolean d;
    private Random r;

    private Color c1, c2, c3, c4, c5;
    private final Color white = new Color(255, 255, 255);
    private final Color black = new Color(20, 20, 20);
    private final List<Color> colors = new ArrayList<>();
    private Thread t;

    public ShowRunner(ShowClient client, long startTime, long seed) {
        this.client = client;
        this.grade = client.getGrade();
        this.startTime = startTime;

        allWhite();
        colors.add(c1);
        colors.add(c2);
        colors.add(c3);
        colors.add(c4);
        colors.add(c5);

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

    private void ice() {
        c1 = new Color(184, 223, 231);
        c2 = new Color(95, 212, 197);
        c3 = new Color(99, 128, 229);
        c4 = new Color(64, 59, 189);
        c5 = new Color(22, 16, 111);
    }

    private void crayons() {
        c1 = new Color(255, 0, 0);
        c2 = new Color(0, 255, 0);
        c3 = new Color(0, 0, 255);
        c4 = new Color(255, 255, 0);
        c5 = new Color(0, 255, 255);
    }

    private void bright() {
        c1 = new Color(170, 36, 206);
        c2 = new Color(255, 210, 46);
        c3 = new Color(16, 119, 232);
        c4 = new Color(16, 194, 255);
        c5 = new Color(72, 254, 255);
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
            p(1000);
            off();
            Thread.sleep(0);
            w(0);
            //System.out.println("Show Start");
            intro();
            ohFade();
            verse1();
            prechorus1();
            chorus1();
            breakdown();
            twinkle2();
            end();
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
            i -= 2;
            s(colorScale(c5, i));
            p(10);
        });
        i = 255;
        dw(5698, () -> {
            i--;
            s(colorScale(c5, i));
            p(10);
        });
        i = 255;
        dw(6787, () -> {
            i--;
            s(colorScale(c5, i));
            p(10);
        });
        i = 255;
        dw(7857, () -> {
            i--;
            s(colorScale(c5, i));
            p(10);
        });
        i = 255;
        dw(8952, () -> {
            i--;
            s(colorScale(c5, i));
            p(10);
        });
        i = 255;
        dw(10071, () -> {
            i--;
            s(colorScale(c5, i));
            p(10);
        });
        off();
        //Victorious
        i = split(4);
        w(10314);
        if (i == 0) s(c1);
        w(10540);
        if (i == 1) s(c5);
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
        w(20000);
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
            sb(i, 0, 0);

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
        ice();
        w(29118);
        //Double bubble disco queen
        if (grade == 10) { /* *************************************************************************************** */
            i = 0;
            off();
            if (split(4) == 0) s(c1);
            else s(black);
            w(29400);
            if (split(4) == 0) s(c1);
            else s(black);
            w(29671);
            if (split(4) == 0) s(c1);
            else s(black);
            w(29957);
            if (split(4) == 0) s(c1);
            else s(black);
            w(30215);
            if (split(4) == 0) s(c1);
            else s(black);
            w(30495);
            if (split(4) == 0) s(c1);
            else s(black);
            w(30726);
            if (split(4) == 0) s(c1);
            else s(black);
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
            if (split(4) == 0) s(c2);
            else s(black);
            w(31589);
            if (split(4) == 0) s(c2);
            else s(black);
            w(31859);
            if (split(4) == 0) s(c2);
            else s(black);
            w(32154);
            if (split(4) == 0) s(c2);
            else s(black);
            w(32388);
            if (split(4) == 0) s(c2);
            else s(black);
            w(32711);
            if (split(4) == 0) s(c2);
            else s(black);
            w(32952);
            if (split(4) == 0) s(c2);
            else s(black);
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
            if (split(4) == 0) s(c3);
            else s(black);
            w(33791);
            if (split(4) == 0) s(c3);
            else s(black);
            w(34042);
            if (split(4) == 0) s(c3);
            else s(black);
            w(34308);
            if (split(4) == 0) s(c3);
            else s(black);
            w(34478);
            if (split(4) == 0) s(c3);
            else s(black);
            w(34852);
            if (split(4) == 0) s(c3);
            else s(black);
            w(35107);
            if (split(4) == 0) s(c3);
            else s(black);
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
            if (split(4) == 0) s(c4);
            else s(black);
            w(38222);
            if (split(4) == 0) s(c4);
            else s(black);
            w(38447);
            if (split(4) == 0) s(c4);
            else s(black);
            w(38735);
            if (split(4) == 0) s(c4);
            else s(black);
            w(38982);
            if (split(4) == 0) s(c4);
            else s(black);
            w(39233);
            off();
            w(39497);
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
            if (split(4) == 0) s(c5);
            else s(black);
            w(39758);
            if (split(4) == 0) s(c5);
            else s(black);
            w(40072);
            if (split(4) == 0) s(c5);
            else s(black);
            w(40316);
            if (split(4) == 0) s(c5);
            else s(black);
            w(40580);
            if (split(4) == 0) s(c5);
            else s(black);
            w(40867);
            if (split(4) == 0) s(c5);
            else s(black);
            w(41099);
            if (split(4) == 0) s(c5);
            else s(black);
            w(41401);
            off();
            w(41671);
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
            if (split(4) == 0) s(c1);
            else s(black);
            w(42025);
            if (split(4) == 0) s(c1);
            else s(black);
            w(42217);
            if (split(4) == 0) s(c1);
            else s(black);
            w(42472);
            if (split(4) == 0) s(c1);
            else s(black);
            w(42643);
            if (split(4) == 0) s(c1);
            else s(black);
            w(43000);
            if (split(4) == 0) s(c1);
            else s(black);
            w(43230);
            if (split(4) == 0) s(c1);
            else s(black);
            w(43607);
            off();
            w(43828);
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
        fire();
        //My touch is black and poisonous
        w(46263); //My
        if (grade == 9) fd(c1, 0.8f);
        w(46561); //touch
        if (grade == 12) fd(c1, 0.8f);
        w(47397); //is
        if (grade == 10) fd(c2, 0.8f);
        w(47643); //black
        if (grade == 11) fd(c2, 0.8f);
        w(47961); //and
        if (grade == 9) fd(c5, 0.8f);
        w(48212); //poi-
        if (grade == 12) fd(c5, 0.8f);
        w(48509); //-son-
        if (grade == 10) fd(c4, 0.8f);
        w(48766); //-ous
        if (grade == 11) fd(c4, 0.8f);
        //And nothing like my
        w(49591); //and
        if (grade == 12) fd(c3, 0.8f);
        w(49843); //no-
        if (grade == 10) fd(c3, 0.8f);
        w(50130); //-thing
        if (grade == 11) fd(c3, 0.8f);
        w(50318); //like
        if (grade == 9) fd(c2, 0.8f);
        w(50679); //my
        if (grade == 12) fd(c2, 0.8f);
        w(50940); //Punch
        if (grade == 10 || grade == 11) fd(white, 0.8f);
        w(51478); //Drunk
        if (grade == 9 || grade == 12) s(white);
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
        crayons();
        i = split(3);
        w(55319);
        if (i == 0) fd(c1, 12);
        w(59683);
        if (i == 1) fd(c2, 8);
        setMinute(1);
        w(4055);
        if (i == 2) fd(c3, 6);
    }

    private void breakdown() throws InterruptedException {
        allWhite();
        setMinute(1);
        w(11173);
        off();
        i = 0;
        if (grade == 12) dw(12684, () -> {
            s(i, i, i);
            i++;
            p(10);
        });
        off();
        i = 6; //TODO: Check wheel speed here
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
        if (grade == 12) dw(21580, () -> {
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
            w(21580);
        }
        off();
        /* ********************************************************************************************************* */
        allWhite();
        k = 0;
        w(21607);
        if (grade == 10 || grade == 11) dw(23532, () -> {
            s(wheel(Math.round(z() / i + (85 * k))));
            p(10);
        });
        off();
        w(23694);
        if (grade == 12 || grade == 9) dw(25735, () -> {
            s(wheel(Math.round(z() / i + (85 * k))));
            p(10);
        });
        off();
        w(25933);
        if (grade == 10 || grade == 11) dw(27916, () -> {
            s(wheel(Math.round(z() / i + (85 * k))));
            p(10);
        });
        off();
        w(28105);
        if (grade == 12) dw(30286, () -> {
            s(wheel(Math.round(z() / i + (85 * k))));
            p(10);
        });
        else {
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
            w(30286);
            off();
        }
        off();
    }

    private void twinkle() throws InterruptedException {
        off();
        i = j = k = 0;
        k = 1600;
        c1 = new Color(0, 0, 0);
        w(30295);
        dw(39569, () -> {
            if (split(k) == 0) i = 255;
            c1 = new Color(i, i, i);
            s(colorMax(black, c1));

            i-= 2;
            p(10);
        });
        j = 128;
        dw(40657, () -> {
            if (split(k) == 0) i = 255;
            c1 = new Color(i, i, i);
            c2 = new Color(j, j, j);
            s(colorMax(colorMax(black, c1), c2));

            i-= 2;
            j-= 2;
            p(10);
        });
        j = 128;
        dw(41736, () -> {
            if (split(k) == 0) i = 255;
            c1 = new Color(i, i, i);
            c2 = new Color(j, j, j);
            s(colorMax(colorMax(black, c1), c2));

            i-= 2;
            j-= 2;
            p(10);
        });
        j = 128;
        dw(42807, () -> {
            if (split(k) == 0) i = 255;
            c1 = new Color(i, i, i);
            c2 = new Color(j, j, j);
            s(colorMax(colorMax(black, c1), c2));

            i-= 2;
            j-= 2;
            p(10);
        });
        j = 128;
        dw(43918, () -> {
            if (split(k) == 0) i = 255;
            c1 = new Color(i, i, i);
            c2 = new Color(j, j, j);
            s(colorMax(colorMax(black, c1), c2));

            i-= 2;
            j-= 2;
            p(10);
        });
        j = 128;
        dw(45019, () -> {
            if (split(k) == 0) i = 255;
            c1 = new Color(i, i, i);
            c2 = new Color(j, j, j);
            s(colorMax(colorMax(black, c1), c2));

            i-= 2;
            j-= 2;
            p(10);
        });
        j = 128;
        dw(45577, () -> {
            if (split(k) == 0) i = 255;
            c1 = new Color(i, i, i);
            c2 = new Color(j, j, j);
            s(colorMax(colorMax(black, c1), c2));

            i-= 2;
            j-= 2;
            p(10);
        });
        off();
        allWhite();
        i = split(6);
        w(45784);
        if (i == 0) s(c1);
        w(46027);
        if (i == 1) s(c2);
        w(46356);
        if (i == 2) s(c3);
        w(46629);
        if (i == 3) s(c4);
        w(46935);
        if (i == 5) s(c5);
        w(47158);
        s(white);
        w(47686);
        off();
    }

    private void twinkle2() throws InterruptedException {
        System.out.println("Twinkle2");
        off();
        ice();

        i = j = k = 0;
        i = split(5);
        j = split(255);
        final Color myColor = colors.get(i);
        direction = split(2) == 0;

        w(30295);
        dw(39569, () -> {
            if (i < 4 && z() < 94680) return;
            if (i < 2) return;

            if (j >= 255) direction = false;
            if (j <= 0) direction = true;

            if (direction) j++;
            else j--;

            sb(colorMax(black, colorScale(myColor, j)));

            p(10);
        });
        k = 128;
        dw(40657, () -> {
            if (j >= 255) direction = false;
            if (j <= 0) direction = true;

            if (direction) j++;
            else j--;

            s(colorMax(new Color(k, k, k), colorScale(myColor, j)));

            k--;
            p(10);
        });
        k = 128;
        dw(41736, () -> {
            if (j >= 255) direction = false;
            if (j <= 0) direction = true;

            if (direction) j++;
            else j--;

            s(colorMax(new Color(k, k, k), colorScale(myColor, j)));

            k--;
            p(10);
        });
        k = 128;
        dw(42807, () -> {
            if (j >= 255) direction = false;
            if (j <= 0) direction = true;

            if (direction) j++;
            else j--;

            s(colorMax(new Color(k, k, k), colorScale(myColor, j)));

            k--;
            p(10);
        });
        k = 128;
        dw(43918, () -> {
            if (j >= 255) direction = false;
            if (j <= 0) direction = true;

            if (direction) j++;
            else j--;

            s(colorMax(new Color(k, k, k), colorScale(myColor, j)));

            k--;
            p(10);
        });
        k = 128;
        dw(45019, () -> {
            if (j >= 255) direction = false;
            if (j <= 0) direction = true;

            if (direction) j++;
            else j--;

            s(colorMax(new Color(k, k, k), colorScale(myColor, j)));

            k--;
            p(10);
        });
        k = 128;
        dw(45577, () -> {
            if (j >= 255) direction = false;
            if (j <= 0) direction = true;

            if (direction) j++;
            else j--;

            s(colorMax(new Color(k, k, k), colorScale(myColor, j)));

            k--;
            p(10);
        });
        off();
        allWhite();
        i = split(6);
        w(45784);
        if (i == 0) s(c1);
        w(46027);
        if (i == 1) s(c2);
        w(46356);
        if (i == 2) s(c3);
        w(46629);
        if (i == 3) s(c4);
        w(46935);
        if (i == 5) s(c5);
        w(47158);
        s(white);
        w(47686);
        off();
    }

    private void end() throws InterruptedException {
        w(47745);

        i = 64 * split(4);
        j = split(255);
        direction = j % 2 == 0;
        dw(63831, () -> { //TODO: Something to the words
            if (direction) j++;
            else j--;
            i ++;

            if (j >= 254) direction = false;
            if (j <= 100) direction = true;

            s(colorMax(colorScale(wheel(i), j), black));

            p(10);
        });

        w(63831);
        off();
        setMinute(2);
        i = split(4);

        if (i == 0) s(white);
        w(4061);
        if (i == 1) s(white);
        w(4560);
        if (i == 2) s(white);
        w(4938);
        fd(white, 3);
        off();
    }

    private void off() {
        s(black);
    }

    private void s(Integer r, Integer g, Integer b) {
        String out = "#" + componentToHex(constrain(r)) + componentToHex(constrain(g)) + componentToHex(constrain(b));
        client.send(out);
    }

    private void s(Color c) {
        client.send(c.getHex());
    }

    private void sb(Color c) {
        long ctm = System.currentTimeMillis();

        if(ctm - lastSend > 50) {
            client.send(c.getHex());
            lastSend = ctm;
        }
    }

    private void sb(Integer r, Integer g, Integer b) { //Send Buffered
        long ctm = System.currentTimeMillis();

        if(ctm - lastSend > 50) {
            String out = "#" + componentToHex(constrain(r)) + componentToHex(constrain(g)) + componentToHex(constrain(b));
            client.send(out);
            lastSend = ctm;
        }
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

    public static Color wheel(int pos) {
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

    private Color colorMax(Color c1, Color c2) {
        int r, g, b;
        r = Math.max(c1.r, c2.r);
        g = Math.max(c1.g, c2.g);
        b = Math.max(c1.b, c2.b);
        return new Color(r, g, b);
    }

    private Color colorScale(Color c, int scale) {
        if (scale < 0) scale = 0;
        float s = scale / 255f;
        int r, g, b;
        r = Math.round(s * c.r);
        g = Math.round(s * c.g);
        b = Math.round(s * c.b);

        return new Color(r, g, b);
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
