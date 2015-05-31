/*
 * Exercise 5 - Computer Graphics
 * Matan Gidnian	200846905
 * Nitsan Bracha 	300590155
 */
package ex5.models.GalaxyModels;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class SaturnRings extends Planet {

    private final float distanceOfSaturnFromTheSun;

    public SaturnRings(GLU glu, String pathTex, float size, float inclination, float distancesFromMainGravity,
                       float distanceOfSaturnFromTheSun) {
        super(glu, pathTex, size, inclination, distancesFromMainGravity);
        this.distanceOfSaturnFromTheSun = distanceOfSaturnFromTheSun;
    }

    @Override
    public void render(GL gl) {
        gl.glEnable(GL.GL_LIGHTING);
        gl.glPushMatrix();

        materializer(gl, new float[1]);

        gl.glPushMatrix();

        GLUquadric planet = glu.gluNewQuadric();
        glu.gluQuadricTexture(planet, true);
        glu.gluQuadricDrawStyle(planet, GLU.GLU_FILL);
        glu.gluQuadricNormals(planet, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(planet, GLU.GLU_OUTSIDE);
        gl.glEnable(GL.GL_TEXTURE_2D);

        if (tex != null) {
            tex.enable();
            tex.bind();
        }

        // on the side so it's look cool :)
        gl.glRotated(inclination, 1.0D, 0.0D, 0.0D);

        // creating the rings and the back face of the rings
        glu.gluDisk(planet, size, 0.65, 48, 48);
        gl.glRotated(180.0D, 0.0D, 1.0D, 0.0D);
        glu.gluDisk(planet, size, 0.65, 48, 48);

        glu.gluDeleteQuadric(planet);

        if (tex != null) {
            tex.disable();
        }

        gl.glPopMatrix();
        gl.glPopMatrix();
    }
}
