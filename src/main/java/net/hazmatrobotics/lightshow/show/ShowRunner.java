package net.hazmatrobotics.lightshow.show;

public class ShowRunner implements Runnable {
    private String id;
    private Integer grade;
    private Boolean shouldStop = false;
    Thread t;

    public ShowRunner(String id, Integer grade) {
        this.id = id;
        this.grade = grade;
        t = new Thread(this, id);
        ShowCoordinator.registerThread(t);
    }

    @Override
    public void run() {
        try { //TODO: Show here.
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        t.interrupt();
    }
}
