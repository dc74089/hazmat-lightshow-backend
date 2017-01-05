package net.hazmatrobotics.lightshow;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.framing.Framedata;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;

public class FakeSocket implements WebSocket {

    @Override
    public void close(int i, String s) {

    }

    @Override
    public void close(int i) {

    }

    @Override
    public void close() {

    }

    @Override
    public void closeConnection(int i, String s) {

    }

    @Override
    public void send(String s) throws NotYetConnectedException {

    }

    @Override
    public void send(ByteBuffer byteBuffer) throws IllegalArgumentException, NotYetConnectedException {

    }

    @Override
    public void send(byte[] bytes) throws IllegalArgumentException, NotYetConnectedException {

    }

    @Override
    public void sendFrame(Framedata framedata) {

    }

    @Override
    public void sendFragmentedFrame(Framedata.Opcode opcode, ByteBuffer byteBuffer, boolean b) {

    }

    @Override
    public boolean hasBufferedData() {
        return false;
    }

    @Override
    public InetSocketAddress getRemoteSocketAddress() {
        return null;
    }

    @Override
    public InetSocketAddress getLocalSocketAddress() {
        return null;
    }

    @Override
    public boolean isConnecting() {
        return false;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public boolean isClosing() {
        return false;
    }

    @Override
    public boolean isFlushAndClose() {
        return false;
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public Draft getDraft() {
        return null;
    }

    @Override
    public READYSTATE getReadyState() {
        return null;
    }

    @Override
    public String getResourceDescriptor() {
        return null;
    }
}
