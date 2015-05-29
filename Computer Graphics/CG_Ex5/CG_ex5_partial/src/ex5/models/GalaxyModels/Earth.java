package ex5.models.GalaxyModels;

import ex5.models.Galaxy;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class Earth extends Planet {
    private float INCLINATION_MOON = 5.1f;
    private float DISTANCE_MOON = 1f; // ? should be against sun or proportional to around earth
    private float SIZE_MOON = 0.154f * Galaxy.SIZE_SUN;
    private String PATH_TEX_MOON = "moon.png";


    public Earth(GL gl,GLU glu, String pathTex, float sizeRelativeToTheSun, float inclination, float distancesFromMainGravity) {
        super(gl, glu, pathTex, sizeRelativeToTheSun, inclination, distancesFromMainGravity);
    }

    @Override
    protected void renderOrbitalObjects(GL gl, float radius, float inclination, float distanceFromSun) {
        super.renderOrbitalObjects(gl, radius, inclination, distanceFromSun);

    }
}
