public class Flag {

    private double x;
    private double y;

    private boolean hitByShip1;
    private boolean hitByShip2;





    Extent extent = new Extent(x,y,DELTA);


    public Flag(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Extent getExtent() {
        return this.extent;
    }

    public boolean hasBeenHitByShip1() {
        return hitByShip1;

    }

    public boolean hasBeenHitByShip2() {
        return hitByShip2;
    }

    public void setHitByShip1() {
        this.hitByShip1 = true;


    }
    public void setHitByShip2() {
        this.hitByShip2 = true;

    }
}
