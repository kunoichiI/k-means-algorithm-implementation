


import java.util.ArrayList;
import java.util.List;

public class Cluster {
	private final ArrayList<Point> points;
	private Point centroid;
	
	public Cluster(Point firstPoint) {
		// Class Constructor
		points = new ArrayList<Point>();
		centroid = firstPoint;
	}
	
	public Point getCentroid() {
		return centroid;
	}
	
	public void updateCentroid() {
		float newX = 0f, newY = 0f;
		for (Point point: points) {
			newX += point.x;
			newY += point.y;	
		}
		centroid = new Point(newX/points.size(), newY/points.size());  //Calculate new centroid using mean value of all data points within this cluster
	}
	
	public List<Point> getPoints() {
		return points;
	}
	
	public float calculateSSE(){
		float value = 0f;
		for (Point point: points) {
			value += point.getSquareOfDistance(centroid);
		}
		return value;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder("\n");
		for (Point point:points) {
			builder.append(point.toString() + ",");
		}
		return builder.toString();
	}
	
}

