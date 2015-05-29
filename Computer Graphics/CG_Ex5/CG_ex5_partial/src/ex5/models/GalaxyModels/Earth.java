package ex5.models.GalaxyModels;

import ex5.models.Galaxy;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class Earth extends Planet {
    private float INCLINATION_MOON = 5.1f;
    private float DISTANCE_MOON = size + 0.2f; // proportional to around earth
    private float SIZE_MOON = 0.08f * Galaxy.SIZE_SUN;
    private String PATH_TEX_MOON = "moon.png";
    private Planet moon;

    public Earth(GLU glu, String pathTex, float sizeRelativeToTheSun, float inclination, float distancesFromMainGravity) {
        super(glu, pathTex, sizeRelativeToTheSun, inclination, distancesFromMainGravity);
        moon = new Planet(glu, PATH_TEX_MOON, SIZE_MOON, INCLINATION_MOON, DISTANCE_MOON);
    }

    @Override
    public void init(GL gl) {
        super.init(gl);
        moon.init(gl);
    }

    @Override
    public void render(GL gl) {
        super.render(gl);

        gl.glPushMatrix();
        gl.glTranslated(distancesFromMainGravity, 0.0D, 0.0D);
        moon.render(gl);
        gl.glPopMatrix();
    }
}
