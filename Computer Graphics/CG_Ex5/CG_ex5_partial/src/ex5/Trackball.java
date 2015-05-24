package ex5;

import ex3.math.Vec;
import ex3.math.Vec4d;

// http://web.cse.ohio-state.edu/~hwshen/781/Site/Slides_files/trackball.pdf
// https://github.com/sanko/fltk-2.0.x/blob/master/test/trackball.c
// http://www.csee.umbc.edu/~squire/download/trackball.c
// Thant Tessman and the August '88 issue of Siggraph's "Computer Graphics," pp. 121-129.
// Ex.05, Appendix A - page 8
public class Trackball {
    /*
     * Project an x,y pair onto a sphere of radius r OR a hyperbolic sheet
     * if we are away from the center of the sphere.
     */
    private static double projectToSphere(double x, double y) {
        double z;

        z = 2 - x *  - y * y;

        if (z < 0) return 0;

        return Math.sqrt(z);
    }

    /*
     * Simulate a track-ball.  Project the points onto the virtual
     * trackball, then figure out the axis of rotation, which is the cross
     * product of P1 P2 and O P1 (O is the center of the ball, 0,0,0)
     * Note:  This is a deformed trackball-- is a trackball in the center,
     * but is deformed into a hyperbolic sheet of rotation away from the
     * center.
     *
     * It is assumed that the arguments to this routine are in the range
     * (-1.0 ... 1.0)
     */
    public static Vec4d trackball(double p1x, double p1y, double p2x, double p2y) {
        Vec axis; /* Axis of rotation */
        double phi;  /* how much to rotate about axis */
        Vec p1, p2, d;
        double t;

        if (p1x == p2x && p1y == p2y) {
            // Zero rotation
            return new Vec4d();
        }

        // First, figure out z-coordinates for projection of P1 and P2 to
        // deformed sphere
        p1 = new Vec(p1x, p1y, projectToSphere(p1x, p1y));
        p2 = new Vec(p2x, p2y, projectToSphere(p2x, p2y));

        // Now, we want the cross product of P1 and P2
        axis = Vec.crossProd(p1, p2);

        // Figure out how much to rotate around that axis.
        d = Vec.sub(p1, p2);
        t = d.length() / (2.0);

        // Avoid problems with out-of-control values...
        if (t > 1.0) t = 1.0;
        if (t < -1.0) t = -1.0;
        phi = 2.0 * Math.asin(t);

        return axisToQuat(axis, phi);
    }

    /*
     *  Given an axis and angle, compute quaternion.
     */
    private static Vec4d axisToQuat(Vec axis, double phi) {
        axis.normalize();
        Vec q = new Vec(axis);
        q.scale(Math.sin(phi / 2.0));
        Vec4d result = new Vec4d(q);
        result.w = Math.cos(phi / 2.0);
        return result;
    }

    /*
     * Build a rotation matrix, given a quaternion rotation.
     *
     */
    public static double[] buildRotmatrix(Vec4d q)
    {
        double m[] = new double[16];
        m[0] = 1.0 - 2.0 * (q.y * q.y + q.z * q.z);
        m[1] = 2.0 * (q.x * q.y - q.z * q.w);
        m[2] = 2.0 * (q.z * q.x + q.y * q.w);
        m[3] = 0.0;

        m[4] = 2.0 * (q.x * q.y + q.z * q.w);
        m[5]= 1.0 - 2.0 * (q.z * q.z + q.x * q.x);
        m[6] = 2.0 * (q.y * q.z - q.x * q.w);
        m[7] = 0.0;

        m[8] = 2.0 * (q.z * q.x - q.y * q.w);
        m[9] = 2.0 * (q.y * q.z + q.x * q.w);
        m[10] = 1.0 - 2.0 * (q.y * q.y + q.x * q.x);
        m[11] = 0.0;

        m[12] = 0.0;
        m[13] = 0.0;
        m[14] = 0.0;
        m[15] = 1.0;

        return m;
    }
}
