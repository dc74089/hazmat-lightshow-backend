package net.hazmatrobotics.lightshow;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RunMe {
    static FirebaseDatabase db;
    static DatabaseReference queueRef, showRef;
    static NewDeviceListener newDeviceListener;

    public static void main(String... args) {
        FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                .setDatabaseUrl("https://hazmatlightshow.firebaseio.com")
                .setServiceAccount(RunMe.class.getResourceAsStream("/firebase-pkey.json"))
                .build();
        FirebaseApp.initializeApp(firebaseOptions);
        db = FirebaseDatabase.getInstance();

        showRef = db.getReference();
        queueRef = db.getReference().child("devices").child("queue");
        newDeviceListener = new NewDeviceListener();
        showRef.child("accepting").addValueEventListener(stopAcceptingListener);

        while (true);
    }

    static BlankValueListener stopAcceptingListener = new BlankValueListener(){
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(!(Boolean) dataSnapshot.getValue()) {
                System.out.println("Committing newDeviceListener and not accepting new entries.");
                queueRef.removeEventListener(newDeviceListener);
                newDeviceListener.commit();
            } else {
                System.out.println("Resetting event listener and cleaning up");
                showRef.child("devices").removeValue();
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
                showRef.child("devices").removeValue();
                showRef.child("cleanup").setValue(false);
            }
        }
    };
}
