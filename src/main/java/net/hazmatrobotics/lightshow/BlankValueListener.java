package net.hazmatrobotics.lightshow;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class BlankValueListener implements ValueEventListener {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        System.out.println("Error in value listener: " + databaseError.getMessage());
    }
}
