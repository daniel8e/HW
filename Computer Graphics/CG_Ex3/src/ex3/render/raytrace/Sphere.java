package ex3.render.raytrace;

import java.util.Map;

public class Sphere extends Surface{

	private double radius;
	private Point3D center;
	
	public Sphere(){}
	
	public Sphere(Map<String, String> attributes){
		originInit(attributes);
		init(attributes);
	}
	
	@Override
	public Vec getNormalAtLocation(Point3D p) {
		
		Vec normal;
		normal = Point3D.vectorBetweenTwoPoints(center, p);
		normal.normalize();
		return normal;
	}

	@Override
	public void init(Map<String, String> attributes)
			throws IllegalArgumentException {
		
		if (!attributes.containsKey("center")){
			throw new IllegalArgumentException("missing center point");
		}else{ 
			center = new Point3D(attributes.get("center"));
		}
		if (!attributes.containsKey("radius")){
			throw new IllegalArgumentException("missing radius");
		}else{ 
			radius = Double.parseDouble(attributes.get("radius"));
		}
		
		
	}

	public Point3D getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}
}
