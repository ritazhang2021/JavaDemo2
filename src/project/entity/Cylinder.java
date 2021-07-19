package project.entity;


/**
 * @Author: Rita
 */
class Cylinder extends Circle {
    private double diameter = 0;
    public double area(double length, double width, double diameter){
        return length * width * diameter;
    }
}
