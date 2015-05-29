package ex5;

import java.awt.Point;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

//import com.sun.javafx.geom.Vec4d;
import com.sun.opengl.util.FPSAnimator;

import ex5.models.IRenderable;

/**
 * An OpenGL model viewer 
 *
 */
public class Viewer implements GLEventListener {

	private double zoom = 0.0; //How much to zoom in? >0 mean magnify, <0 means shrink
	private Point mouseFrom, mouseTo; //From where to where was the mouse dragged between the last redraws?
	private boolean isWireframe = false; //Should we display wireframe or not?
	private boolean isAxes = true; //Should we display axes or not?
	private IRenderable model; //Model to display
	private FPSAnimator ani; //This object is responsible to redraw the model with a constant FPS
	private GLAutoDrawable m_drawable = null; //We store the drawable OpenGL object to refresh the scene
	private boolean isModelCamera = false; //Whether the camera is relative to the model, rather than the world (ex6)
	private boolean isModelInitialized = false; //Whether model.init() was called.
	double oldRotationMatrix[] =
			{
					1,0,0,0,
					0,1,0,0,
					0,0,1,0,
					0,0,0,1
			};

	// remember width and height for canvas
	private int width;
	private int height;
	

	@Override
	public void display(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();
		if (!isModelInitialized) {
			model.init(gl);
			isModelInitialized = true;
		}
		//TODO: uncomment the following line to clear the window before drawing
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL.GL_MODELVIEW);

		setupCamera(gl);
		if (isAxes)
			renderAxes(gl);
		
		// Wireframes
		if (isWireframe) {
			gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_LINE);
		} else {
			gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_FILL);
		}

		model.render(gl);
	}



	private void setupCamera(GL gl) {
		if (!isModelCamera) { //Camera is in an absolute location
			//TODO: place the camera. You should use mouseFrom, mouseTo, canvas width and
			//      height (reshape function), zoom etc. This should actually implement the trackball
			//		and zoom. You might want to store the rotation matrix in an array for next time.
			//		Relevant functions: glGetDoublev, glMultMatrixd
			//      Example: gl.glGetDoublev(GL.GL_MODELVIEW_MATRIX, rotationMatrix, 0);


			if (mouseFrom != null && mouseTo != null) {
				double[] rotationMatrix;

				// Appendix A - page 8
				ex3.math.Vec4d trackball = Trackball.trackball(
						2 * mouseFrom.getX() / m_drawable.getWidth() - 1.0,
						1.0 - 2 * mouseFrom.getY() / m_drawable.getHeight(),
						2 * mouseTo.getX() / m_drawable.getWidth() - 1.0,
						1.0 - 2 * mouseTo.getY() / m_drawable.getHeight());
				rotationMatrix = Trackball.buildRotmatrix(trackball);

				gl.glLoadIdentity();
				gl.glMultMatrixd(rotationMatrix, 0);
				gl.glMultMatrixd(oldRotationMatrix, 0);
				gl.glGetDoublev(GL.GL_MODELVIEW_MATRIX, oldRotationMatrix, 0);
			}

			// No zoom
			gl.glLoadIdentity();

			// Do the zoom while not rotated
			gl.glTranslated(0, 0, -zoom);

			// get out of the module
			gl.glTranslated(0, 0, -3);

			// rotate back
			gl.glMultMatrixd(oldRotationMatrix, 0);

			//By this point, we should have already changed the point of view, now set these to null
			//so we don't change it again on the next redraw.
			mouseFrom = null;
			mouseTo = null;
		} else { //Camera is relative to the model
			gl.glLoadIdentity();
			model.setCamera(gl);
		}
	}
	
	@Override
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
			boolean deviceChanged) {
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();
		drawable.setGL(new javax.media.opengl.DebugGL(gl));

		//TODO: light model, normal normalization, depth test, back face culling, ...
		
		// Rec6, slide 35+, and followed the todo line above.
		gl.glCullFace(GL.GL_BACK);    // Set Culling Face To Back Face
        gl.glEnable(GL.GL_CULL_FACE); // Enable back face culling <- this also makes one of the edges black.
		gl.glEnable(GL.GL_NORMALIZE); // Normal Normalize
		gl.glEnable(GL.GL_DEPTH_TEST); // Enable depth test
		
		//https://msdn.microsoft.com/en-us/library/windows/desktop/dd373581%28v=vs.85%29.aspx
		//also possible: GL_LIGHT_MODEL_LOCAL_VIEWER
		gl.glLightModelf(GL.GL_LIGHT_MODEL_TWO_SIDE, 1);
		gl.glEnable(GL.GL_LIGHTING);
		
		// Initialize display callback timer
		ani = new FPSAnimator(30, true);
		ani.add(drawable);
		
		m_drawable = drawable;
		
		if (model.isAnimated()) //Start animation (for ex6)
			startAnimation();
		else
			stopAnimation();


	}
	
	// Recitation 9, starting at slide 12 and on.
	// Frustum API: https://www.opengl.org/sdk/docs/man2/xhtml/glFrustum.xml
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL gl = drawable.getGL();

		// Fix the texture once maximizing screen
		isModelInitialized = false;

		// Save the width and height of this canvas
		this.width = width;
		this.height = height;

		// Select The Projection Matrix
		gl.glMatrixMode(GL.GL_PROJECTION);

		// Reset The Projection Matrix
		gl.glLoadIdentity();
		
		// Set the projection to perspective. (Apply zoom using the projection matrix)
		// Change near_val to 0.05 or 0.2 to get different views, or any other parameter
		gl.glFrustum(-0.1, 0.1, -0.1D * height / width, 0.1D * height / width, 0.1D, 1000.0D);
	}

	/**
	 * Stores the mouse coordinates for trackball rotation.
	 * 
	 * @param from
	 *            2D canvas point of drag beginning
	 * @param to
	 *            2D canvas point of drag ending
	 */
	public void storeTrackball(Point from, Point to) {
		//The following lines store the rotation for use when next displaying the model.
		//After you redraw the model, you should set these variables back to null.
		if (!isModelCamera) {
			if (null == mouseFrom)
				mouseFrom = from;
			mouseTo = to;
			m_drawable.repaint();
		}
	}

	/**
	 * Zoom in or out of object. s<0 - zoom out. s>0 zoom in.
	 * 
	 * @param s
	 *            Scalar
	 */
	public void zoom(double s) {
		if (!isModelCamera) {
			zoom += s * 0.1;
			m_drawable.repaint();
		}
	}

	/**
	 * Toggle rendering method. Either wireframes (lines) or fully shaded
	 */
	public void toggleRenderMode() {
		isWireframe = !isWireframe;
		m_drawable.repaint();
	}
	
	/**
	 * Toggle whether little spheres are shown at the location of the light sources.
	 */
	public void toggleLightSpheres() {
		model.control(IRenderable.TOGGLE_LIGHT_SPHERES, null);
		m_drawable.repaint();
	}

	/**
	 * Toggle whether axes are shown.
	 */
	public void toggleAxes() {
		isAxes = !isAxes;
		model.control(IRenderable.TOGGLE_AXES, isAxes);
		m_drawable.repaint();
	}
	
	public void toggleModelCamera() {
		isModelCamera =! isModelCamera;
		m_drawable.repaint();
	}
	
	/**
	 * Start redrawing the scene with 60 FPS
	 */
	public void startAnimation() {
		if (!ani.isAnimating())
			ani.start();
	}
	
	/**
	 * Stop redrawing the scene with 60 FPS
	 */
	public void stopAnimation() {
		if (ani.isAnimating())
			ani.stop();
	}
	
	private void renderAxes(GL gl) {
		gl.glLineWidth(2);
		boolean flag = gl.glIsEnabled(GL.GL_LIGHTING);
		gl.glDisable(GL.GL_LIGHTING);
		gl.glBegin(GL.GL_LINES);
		gl.glColor3d(1, 0, 0);
		gl.glVertex3d(0, 0, 0);
		gl.glVertex3d(10, 0, 0);
		
		gl.glColor3d(0, 1, 0);
		gl.glVertex3d(0, 0, 0);
		gl.glVertex3d(0, 10, 0);
		
		gl.glColor3d(0, 0, 1);
		gl.glVertex3d(0, 0, 0);
		gl.glVertex3d(0, 0, 10);
		
		gl.glEnd();
		if(flag)
			gl.glEnable(GL.GL_LIGHTING);
	}

	public void setModel(IRenderable model) {
		this.model = model;
		isModelInitialized = false;
	}
	
}
