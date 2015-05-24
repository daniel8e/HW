package ex3.math;

import java.util.Scanner;

public class Vec4d {

    /**
     * Vector data. Allowed to be accessed publicly for performance reasons
     */
    public double x, y, z, w;

    /**
     * Initialize vector to given coordinates as string "x y z"
     * */
    public Vec4d(String v){
        Scanner s = new Scanner(v);
        x = s.nextDouble();
        y = s.nextDouble();
        z = s.nextDouble();
        w = s.nextDouble();
        s.close();
    }
    /**
     * Initialize vector to (0,0,0)
     */
    public Vec4d() {
        x = 0;
        y = 0;
        z = 0;
        w = 0;
    }

    /**
     * Initialize vector to given coordinates
     *
     * @param x
     *            Scalar
     * @param y
     *            Scalar
     * @param z
     *            Scalar
     */
    public Vec4d(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * Initialize vector values to given vector (copy by value)
     *
     * @param v
     *            Vector
     */
    public Vec4d(Vec4d v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.w = v.w;
    }

    /**
     * Initialize vector values to given vector (copy by value)
     *
     * @param v
     *            Vector
     */
    public Vec4d(Vec v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.w = 0;
    }
}
