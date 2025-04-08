public class Ship {

    private double x;
    private double y;
    private double angle;
    private Extent extent;







    public Ship(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.extent = new Extent(x, y, angle);


    }

    public Extent getExtent() {
        return new Extent(x, y, angle);
    }

    public double getAngle() {
        return this.angle;
    }

    public void rotate(double v) {
        angle += v;
    }

    public void accelerate(double v) {
        this.x = v * Math.cos(this.angle);
        this.y = v * Math.cos(this.angle);

    }

    public void drift() {
        this.extent.move(this.x,this.y);


    }
}
