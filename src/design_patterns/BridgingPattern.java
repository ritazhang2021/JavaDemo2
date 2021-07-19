package design_patterns;

/**
 * @Author: Rita
 * @Date:5/26/2021 10:41 PM
 */
public class BridgingPattern {
    public static void main(String[] args) {
        Shape3 redCircle = new Circle3(100,100, 10, new ColorPrinter());
        Shape3 blackCircle = new Circle3(100,100, 10, new BlackPrinter());

        redCircle.draw();
        blackCircle.draw();
    }
}

interface Printer2 {
    public void print(int radius, int x, int y);
}
class ColorPrinter implements Printer2 {
    @Override
    public void print(int radius, int x, int y) {
        System.out.println("Color: " + radius +", x: " +x+", "+ y +"]");
    }
}
class BlackPrinter implements Printer2 {
    @Override
    public void print(int radius, int x, int y) {
        System.out.println("Black: " + radius +", x: " +x+", "+ y +"]");
    }
}
abstract class Shape3 {
    protected Printer2 print;
    protected Shape3(Printer2 p){
        this.print = p;
    }
    public abstract void draw();
}

class Circle3 extends Shape3 {
    private int x, y, radius;

    public Circle3(int x, int y, int radius, Printer2 draw) {
        super(draw);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }


    @Override
    public void draw() {
        print.print(radius,x,y);
    }
}

