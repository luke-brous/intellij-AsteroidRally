public class Extent {

    private double x;

    private double y;

    private final double radius;
    
    

    public Extent(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;

    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getRadius() {
        return this.radius;
    }

    public boolean overlaps(Extent other) {
        return this.distanceTo(other) <= (this.radius + other.radius);




    }

    public double distanceTo(Extent other) {
        return Math.hypot(this.x - other.x, this.y - other.y);

    }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }
}
