package net.hazmatrobotics.lightshow;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class IntakeCoordinator {
    private static FirebaseDatabase db;
    private static DatabaseReference showRef;
    private static Boolean accepting = true;
    private static ServerSocket server;

    public static void main(String... args) {
        FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                .setDatabaseUrl("https://hazmatlightshow.firebaseio.com")
                .setServiceAccount(IntakeCoordinator.class.getResourceAsStream("/firebase-pkey.json"))
                .build();
        FirebaseApp.initializeApp(firebaseOptions);
        db = FirebaseDatabase.getInstance();

        showRef = db.getReference();
        showRef.child("accepting").addValueEventListener(stopAcceptingListener);
        db.getReference().child("cleanup").addValueEventListener(cleanupListener);
        db.getReference().child("start").addValueEventListener(startListener);

        //noinspection InfiniteLoopStatement
        while (true) {
            if(server == null) {
                //System.out.println("No server");
            } else try {
                if (!server.isClosed()) {
                    Socket s = server.accept();
                    if(s != null) {
                        System.out.println("Got a client!");
                        ClientObject clientObject = new ClientObject(s);
                        clientObject.send("#23f300");
                    }
                }
                Thread.sleep(1000);
            } catch (IOException | InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }

    private static BlankValueListener stopAcceptingListener = new BlankValueListener(){
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if((Boolean) dataSnapshot.getValue()) {
                accepting = true;
                try {
                    if(server != null && !server.isClosed()) {
                        server.close();
                    }

                    server = new ServerSocket(7890);
                    System.out.println("Server created");
                    server.setSoTimeout(500);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Resetting event listener and cleaning up");
                accepting = false;

                cleanup();

                if(server == null) return;
                try {
                    if(!server.isClosed())
                        server.close();
                    server = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private static BlankValueListener cleanupListener = new BlankValueListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if((Boolean) dataSnapshot.getValue()) {
                cleanup();
            }
        }
    };

    private static BlankValueListener startListener = new BlankValueListener(){
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            //TODO: This
        }
    };

    private static void cleanup() {
        showRef.child("cleanup").setValue(false);
        showRef.child("start").setValue(false);
    }
}
