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
        queueRef.addChildEventListener(newDeviceListener);
        showRef.child("accepting").addValueEventListener(stopAcceptingListener);
        showRef.child("cleanup").addValueEventListener(cleanupListener);

        while (true) ;
    }

    static BlankValueListener stopAcceptingListener = new BlankValueListener(){
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(!(Boolean) dataSnapshot.getValue()) {
                queueRef.removeEventListener(newDeviceListener);
                newDeviceListener.commit();

                showRef.child("accepting").removeEventListener(this);
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
