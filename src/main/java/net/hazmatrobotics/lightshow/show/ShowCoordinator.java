package net.hazmatrobotics.lightshow.show;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowCoordinator {
    FirebaseDatabase db;
    DatabaseReference devicesRef;
    Map<String, Integer> deviceMap;
    private static List<Thread> threadList = new ArrayList<>();

    public ShowCoordinator(Map<String, Integer> map) {
        this.deviceMap = map;
        db = FirebaseDatabase.getInstance();
        devicesRef = db.getReference().child("devices");
    }

    public void start() {
        for(String id : deviceMap.keySet()) {
            ShowRunner r = new ShowRunner(id, deviceMap.get(id));

        }
    }

    public void haltShow() {

    }

    public static void registerThread(Thread t) {
        threadList.add(t);
    }
}
