package net.hazmatrobotics.lightshow;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import net.hazmatrobotics.lightshow.show.ShowCoordinator;

import java.util.Map;

public class WaitingRoomCoordinator {
    private static FirebaseDatabase db;
    private static DatabaseReference queueRef, showRef;
    private static NewDeviceListener newDeviceListener;
    private static ShowCoordinator showCoordinator;
    private static Map<String, Integer> deviceMap;

    public static void main(String... args) {
        FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                .setDatabaseUrl("https://hazmatlightshow.firebaseio.com")
                .setServiceAccount(WaitingRoomCoordinator.class.getResourceAsStream("/firebase-pkey.json"))
                .build();
        FirebaseApp.initializeApp(firebaseOptions);
        db = FirebaseDatabase.getInstance();

        showRef = db.getReference();
        queueRef = db.getReference().child("devices").child("queue");
        newDeviceListener = new NewDeviceListener();
        showRef.child("accepting").addValueEventListener(stopAcceptingListener);
        db.getReference().child("cleanup").addValueEventListener(cleanupListener);
        db.getReference().child("start").addValueEventListener(startListener);

        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static BlankValueListener stopAcceptingListener = new BlankValueListener(){
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(!(Boolean) dataSnapshot.getValue()) {
                System.out.println("Committing newDeviceListener and not accepting new entries.");
                queueRef.removeEventListener(newDeviceListener);
                deviceMap = newDeviceListener.commit();
                showCoordinator = new ShowCoordinator(deviceMap);
            } else {
                System.out.println("Resetting event listener and cleaning up");
                cleanup();
                queueRef.removeEventListener(newDeviceListener);
                newDeviceListener = new NewDeviceListener();
                queueRef.addChildEventListener(newDeviceListener);
            }
        }
    };

    static BlankValueListener cleanupListener = new BlankValueListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if((Boolean) dataSnapshot.getValue()) {
                cleanup();
            }
        }
    };

    static BlankValueListener startListener = new BlankValueListener(){
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(dataSnapshot.getValue().equals(true)) {
                try {
                    showCoordinator.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //TODO: Cancel show
            }
        }
    };

    private static void cleanup() {
        deviceMap = null;
        showRef.child("devices").removeValue();
        showRef.child("cleanup").setValue(false);
        showRef.child("start").setValue(false);
    }
}
