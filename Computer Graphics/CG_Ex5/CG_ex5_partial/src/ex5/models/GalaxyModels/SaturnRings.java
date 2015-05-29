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
        //super.render(gl);
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

        gl.glRotated(inclination, 1.0D, 0.0D, 0.0D);
//        gl.glRotated(0, 0.0D, 1.0D, 0.0D);
//        gl.glRotated(-90.0D, 1.0D, 0.0D, 0.0D);
//
//        // Move to Saturn
//        gl.glTranslated(distanceOfSaturnFromTheSun, 0D, 0D);
//        gl.glRotated(0, 0.0D, 1.0D, 0.0D);

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
