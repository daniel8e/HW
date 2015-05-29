package ex5.models.GalaxyModels;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class Saturn extends Planet {
    private String PATH_TEX_SATURN_RINGS = "saturnringcolor.png";

    public Saturn(GL gl, GLU glu, String pathTex, float sizeRelativeToTheSun, float inclination, float distancesFromMainGravity) {
        super(gl, glu, pathTex, sizeRelativeToTheSun, inclination, distancesFromMainGravity);
    }

    @Override
    protected void renderOrbitalObjects(GL gl, float radius, float inclination, float distanceFromSun) {
        super.renderOrbitalObjects(gl, radius, inclination, distanceFromSun);

    }
}
