package ex3.math;

import java.util.Scanner;

/**
 * 3D vector class that contains three doubles. Could be used to represent
 * Vectors but also Points and Colors.
 *
 */
public class Vec implements I3DInterface {
    /**
     * Vector data. Allowed to be accessed publicly for performance reasons
     */
    public double x, y, z;

    /**
     * Initialize vector to given coordinates as string "x y z"
     * */
    public Vec(String v){
        Scanner s = new Scanner(v);
        x = s.nextDouble();
        y = s.nextDouble();
        z = s.nextDouble();
        s.close();
    }
    /**
     * Initialize vector to (0,0,0)
     */
    public Vec() {
        x = 0;
        y = 0;
        z = 0;
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
    public Vec(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Initialize vector values to given vector (copy by value)
     *
     * @param v
     *            Vector
     */
    public Vec(Vec v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public Vec(Point3D v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    /**
     * Calculates the reflection of the vector in relation to a given surface
     * normal. The vector points at the surface and the result points away.
     *
     * @return The reflected vector
     */
    public Vec reflect(Vec normal) {
        Vec normalizedNormal = Vec.normalize(normal);
        return add(this, Vec.scale((-2) * this.dotProd(normalizedNormal), normalizedNormal));
    }

    /**
     * Adds a to vector
     *
     * @param a
     *            Vector
     */
    public void add(Vec a) {
        this.x = this.x + a.x;
        this.y = this.y + a.y;
        this.z = this.z + a.z;
    }

    /**
     * Subtracts from vector
     *
     * @param a
     *            Vector
     */
    public void sub(Vec a) {
        this.x = this.x - a.x;
        this.y = this.y - a.y;
        this.z = this.z - a.z;
    }

    /**
     * Multiplies & Accumulates vector with given vector and a. v := v + s*a
     *
     * @param s
     *            Scalar
     * @param a
     *            Vector
     */
    public void mac(double s, Vec a) {
        this.x = this.x + s * a.x;
        this.y = this.y + s * a.y;
        this.z = this.z + s * a.z;
    }

    /**
     * Multiplies vector with scalar. v := s*v
     *
     * @param s
     *            Scalar
     */
    public void scale(double s) {
        this.x = this.x * s;
        this.y = this.y * s;
        this.z = this.z * s;
    }

    /**
     * Pairwise multiplies with another vector
     *
     * @param a
     *            Vector
     */
    public void scale(Vec a) {
        this.x = this.x * a.x;
        this.y = this.y * a.y;
        this.z = this.z * a.z;
    }

    /**
     * Inverses vector
     *
     * @return Vector
     */
    public void negate() {
        this.scale(-1);
    }

    /**
     * Computes the vector's magnitude
     *
     * @return Scalar
     */
    public double length() {
        return Math.sqrt(this.x * this.x +
                this.y * this.y +
                this.z * this.z);
    }

    /**
     * Computes the vector's magnitude squared. Used for performance gain.
     *
     * @return Scalar
     */
    public double lengthSquared() {
        return this.x * this.x +
                this.y * this.y +
                this.z * this.z;
    }

    /**
     * Computes the dot product between two vectors
     *
     * @param a
     *            Vector
     * @return Scalar
     */
    public double dotProd(Vec a) {
        return this.x * a.x +
                this.y * a.y +
                this.z * a.z;
    }

    /**
     * Normalizes the vector to have length 1. Throws exception if magnitude is zero.
     *
     * @throws ArithmeticException
     */
    public void normalize() throws ArithmeticException {
        double length = this.length();

        if (length == 0) throw new ArithmeticException("divide by zero");

        this.x = this.x / length;
        this.y = this.y / length;
        this.z = this.z / length;
    }

    /**
     * Compares to a given vector
     *
     * @param a
     *            Vector
     * @return True if have same values, false otherwise
     */
    public boolean equals(Vec a) {
        return ((a.x == x) && (a.y == y) && (a.z == z));
    }

    /**
     * Returns the angle in radians between this vector and the vector
     * parameter; the return value is constrained to the range [0,PI].
     *
     * @param v1
     *            the other vector
     * @return the angle in radians in the range [0,PI]
     */
    public final double angle(Vec v1) {
        return Math.acos(dotProd(v1)/(this.length() * v1.length()));
    }

    /**
     * Normalizes the vector to have length 1. Throws exception if magnitude is zero.
     *
     * @throws ArithmeticException
     */
    public static Vec normalize(Vec vec) throws ArithmeticException {
        double length = vec.length();

        if (length == 0) throw new ArithmeticException("divide by zero");
        return new Vec(vec.x / length, vec.y / length, vec.z / length);
    }

    /**
     * Computes the Euclidean distance between two points
     *
     * @param a
     *            Point1
     * @param b
     *            Point2
     * @return Scalar
     */
    static public double distance(Vec a, Vec b) {
        return sub(b, a).length();
    }

    /**
     * Computes the cross product between two vectors using the right hand rule
     *
     * @param a
     *            Vector1
     * @param b
     *            Vector2
     * @return Vector1 x Vector2
     */
    public static Vec crossProd(Vec a, Vec b) {
        return new Vec(a.y*b.z - a.z*b.y,
                a.z*b.x - a.x*b.z,
                a.x*b.y - a.y*b.x);
    }

    /**
     * Adds vectors a and b
     *
     * @param a
     *            Vector
     * @param b
     *            Vector
     * @return a+b
     */
    public static Vec add(Vec a, Vec b) {
        return new Vec(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    /**
     * Subtracts vector b from a
     *
     * @param a
     *            Vector
     * @param b
     *            Vector
     * @return a-b
     */
    public static Vec sub(Vec a, Vec b) {
        return new Vec(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    /**
     * Inverses vector's direction
     *
     * @param a
     *            Vector
     * @return -1*a
     */
    public static Vec negate(Vec a) {
        return scale(-1, a);
    }

    /**
     * Scales vector a by scalar s
     *
     * @param s
     *            Scalar
     * @param a
     *            Vector
     * @return s*a
     */
    public static Vec scale(double s, Vec a) {
        return new Vec(s * a.x, s * a.y, s * a.z);
    }

    /**
     * Pair-wise scales vector a by vector b
     *
     * @param a
     *            Vector
     * @param b
     *            Vector
     * @return a.*b
     */
    public static Vec scale(Vec a, Vec b) {
        return new Vec(a.x * b.x,
                a.y * b.y,
                a.z * b.z);
    }

    /**
     * Compares vector a to vector b
     *
     * @param a
     *            Vector
     * @param b
     *            Vector
     * @return a==b
     */
    public static boolean equals(Vec a, Vec b) {
        return ((a.x == b.x) && (a.y == b.y) && (a.z == b.z));
    }

    /**
     * Dot product of a and b
     *
     * @param a
     *            Vector
     * @param b
     *            Vector
     * @return a.b
     */
    public static double dotProd(Vec a, Vec b) {
        return (a.x * b.x +
                a.y * b.y +
                a.z * b.z);
    }

    /**
     * Returns a string that contains the values of this vector. The form is
     * (x,y,z).
     *
     * @return the String representation
     */
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

    @Override
    public Vec clone() {
        return new Vec(this);
    }
}
