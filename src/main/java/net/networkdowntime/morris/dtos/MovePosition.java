package net.networkdowntime.morris.dtos;

public class MovePosition implements Comparable<MovePosition> {
    public int x, y;
    public char color;

    public MovePosition() {
    }

    public MovePosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = 'x';
    }

    public MovePosition(int x, int y, char color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public MovePosition clone() {
        return new MovePosition(this.x, this.y, this.color);
    }

    public MovePosition cloneWithColor(char color) {
        return new MovePosition(this.x, this.y, color);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MovePosition other = (MovePosition) obj;
        if (x != other.x) return false;
        if (y != other.y) return false;
        return true;
    }

    @Override
    public String toString() {
        return "MovePosition(" + x + ", " + y + ")";
    }

    @Override
    public int compareTo(MovePosition mp) {
        if (this.y < mp.y) return -1;
        if (this.y > mp.y) return 1;
        if (this.x < mp.x) return -1;
        if (this.x > mp.x) return 1;
        return 0;
    }

}
