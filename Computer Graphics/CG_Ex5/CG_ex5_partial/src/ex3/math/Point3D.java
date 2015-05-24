package ex3.math;

import java.util.Scanner;

public class Point3D implements I3DInterface {
    public double x, y, z;

    public Point3D() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Point3D(String v) {
        Scanner s = new Scanner(v);
        x = s.nextDouble();
        y = s.nextDouble();
        z = s.nextDouble();
        s.close();
    }

    public Point3D(Point3D point) {
        x = point.x;
        y = point.y;
        z = point.z;
    }

    public Point3D(Vec v) {
        x = v.x;
        y = v.y;
        z = v.z;
    }

    public Vec sub(Point3D a) {
        return new Vec(this.x - a.x, this.y - a.y, this.z - a.z);
    }

    public void add(Point3D a) {
        this.x = this.x + a.x;
        this.y = this.y + a.y;
        this.z = this.z + a.z;
    }
}

