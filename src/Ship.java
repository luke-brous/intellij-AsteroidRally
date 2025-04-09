public class Ship {

    private double x;
    private double y;

    private double dx;
    private double dy;

    private double angle;

    private Extent extent;







    public Ship(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.extent = new Extent(x, y, 0.025);
        this.dx = 0;
        this.dy = 0;

    }

    public Extent getExtent() {
        return this.extent;
    }

    public double getAngle() {
        return this.angle;
    }

    public void rotate(double num) {
        angle += num;
    }

    public void accelerate(double v) {
        this.dx = v * Math.cos(this.angle);
        this.dy = v * Math.sin(this.angle);

    }

    public void drift() {


        extent.move(dx, dy);

        // Wrap the ship around the screen
        if (extent.getX() < 0) {
            extent.move(1.0, 0);
        } else if (extent.getX() > 1.0) {
            extent.move(-1.0, 0);
        }

        if (extent.getY() < 0) {
            extent.move(0, 1.0);
        } else if (extent.getY() > 1.0) {
            extent.move(0, -1.0);
        }


        /* x += dx;
        y += dy;

        if (x < 0) x += 1.0;
        if (x > 1) x -= 1.0;
        if (y < 0) y += 1.0;
        if (y > 1) y -= 1.0;

        extent.move(x - extent.getX(), y - extent.getY());*/

    }
}
