/*
 * Exercise 5 - Computer Graphics
 * Matan Gidnian	200846905
 * Nitsan Bracha 	300590155
 */
package ex5.models.GalaxyModels;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class Saturn extends Planet {
    private final Planet rings;
    private final String PATH_TEX_SATURN_RINGS = "saturnringcolor.png";
    private final float INCLINATION_RINGS = -43.3f;

    public Saturn(GLU glu, String pathTex, float sizeRelativeToTheSun, float inclination, float distancesFromMainGravity) {
        super(glu, pathTex, sizeRelativeToTheSun, inclination, distancesFromMainGravity);

        rings = new SaturnRings(glu, PATH_TEX_SATURN_RINGS, size * 1.2f, INCLINATION_RINGS, 0f, distancesFromMainGravity);
    }

    @Override
    protected void insertRings(GL gl) {
        rings.render(gl);
    }
}
