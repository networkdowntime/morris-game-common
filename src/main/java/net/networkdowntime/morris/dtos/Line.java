package net.networkdowntime.morris.dtos;

public class Line {
    public int x1 = 0;
    public int y1 = 0;
    public int x2 = 0;
    public int y2 = 0;

    public Line() {
    }

    public Line(int x1, int y1, int x2, int y2) {
        if (x1 < x2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        } else if (x1 > x2) {
            this.x1 = x2;
            this.y1 = y2;
            this.x2 = x1;
            this.y2 = y1;
        } else if (y1 < y2) { // x1 == x2
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        } else if (y1 > y2) { // x1 == x2
            this.x1 = x2;
            this.y1 = y2;
            this.x2 = x1;
            this.y2 = y1;
        } else { // x1 == x2 && y1 == y2
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    public boolean linesConnectedAndStraight(Line l2) {
        boolean connected = ((this.x1 == l2.x1) && (this.y1 == l2.y1)) //
                || ((this.x1 == l2.x2) && (this.y1 == l2.y2)) //
                || ((this.x2 == l2.x1) && (this.y2 == l2.y1)) //
                || ((this.x2 == l2.x2) && (this.y2 == l2.y2));
        float l1Slope = (this.y2 - this.y1) / (float) (this.x2 - this.x1);
        float l2Slope = (l2.y2 - l2.y1) / (float) (l2.x2 - l2.x1);
        return connected && (l1Slope == l2Slope);
    }

    @Override
    public String toString() {
        return "(" + x1 + ", " + y1 + ")<-->(" + x2 + ", " + y2 + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x1;
        result = prime * result + x2;
        result = prime * result + y1;
        result = prime * result + y2;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Line other = (Line) obj;
        if (x1 != other.x1) return false;
        if (x2 != other.x2) return false;
        if (y1 != other.y1) return false;
        if (y2 != other.y2) return false;
        return true;
    }

}
