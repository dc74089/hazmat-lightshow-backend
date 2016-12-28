package net.hazmatrobotics.lightshow;

public class Color {
    String hex;
    public final int r, g, b;

    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;

        hex = "#" + componentToHex(r) + componentToHex(g) + componentToHex(b);
    }

    public String getHex() {
        return hex;
    }

    private String componentToHex(Integer component) {
        if (component > 0) return Integer.toHexString(component);
        return "00";
    }
}
