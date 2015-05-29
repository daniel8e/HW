package ex5.models.GalaxyModels;

import javax.media.opengl.glu.GLU;

public class Saturn extends Planet {
    private String PATH_TEX_SATURN_RINGS = "saturnringcolor.png";

    public Saturn(GLU glu, String pathTex, float sizeRelativeToTheSun, float inclination, float distancesFromMainGravity) {
        super(glu, pathTex, sizeRelativeToTheSun, inclination, distancesFromMainGravity);
    }
}
