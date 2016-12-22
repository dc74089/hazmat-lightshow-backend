package net.hazmatrobotics.lightshow;

import com.google.firebase.database.*;

import java.util.HashMap;
import java.util.Map;

public class NewDeviceListener implements ChildEventListener {
    private DatabaseReference showDevicesRef;
    private Map<String, Object> freshmanDeviceIDs, sophomoreDeviceIDs, juniorDeviceIDs, seniorDeviceIDs, allDevices;
    private Map<Integer, Map<String, Object>> gradeIdMapMap = new HashMap<>();
    private static final Object write = new Object();

    public NewDeviceListener() {
        showDevicesRef = FirebaseDatabase.getInstance().getReference().child("devices");

        freshmanDeviceIDs = new HashMap<>();
        sophomoreDeviceIDs = new HashMap<>();
        juniorDeviceIDs = new HashMap<>();
        seniorDeviceIDs = new HashMap<>();
        allDevices = new HashMap<>();

        gradeIdMapMap.put(9, freshmanDeviceIDs);
        gradeIdMapMap.put(10, sophomoreDeviceIDs);
        gradeIdMapMap.put(11, juniorDeviceIDs);
        gradeIdMapMap.put(12, seniorDeviceIDs);
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        try {
            synchronized (write) {
                Integer grade = Math.round((Long) dataSnapshot.getValue());
                gradeIdMapMap.get(grade).put(dataSnapshot.getKey(), true);
                allDevices.put(dataSnapshot.getKey(), grade);
            }
        } catch (Exception e) {
            System.out.println("Class cast exception in NewDeviceListener: Expected grade as integer, got "
                    + dataSnapshot.getValue());
            e.printStackTrace();
        }
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        System.out.println(databaseError.getMessage());
    }

    public void commit() {
        showDevicesRef.child("all").updateChildren(allDevices);
        showDevicesRef.child("9").updateChildren(freshmanDeviceIDs);
        showDevicesRef.child("10").updateChildren(sophomoreDeviceIDs);
        showDevicesRef.child("11").updateChildren(juniorDeviceIDs);
        showDevicesRef.child("12").updateChildren(seniorDeviceIDs);
        showDevicesRef.child("queue").removeValue();
    }
}
