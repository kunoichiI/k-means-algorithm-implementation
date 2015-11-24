

import java.util.ArrayList;

public class Clusters extends ArrayList<Cluster>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ArrayList<Point> points;
	private boolean isChanged;
	
	public Clusters(ArrayList<Point> points) {
		this.points = points;
	}
	
	/*
	 * @param point
	 * @return the index of the Cluster closest to the point
	 */
	public int getNearestCluster(Point point) {
		Float minSquareOfDistance = Float.MAX_VALUE;
		int itsIndex = -1;
		for (int i = 0; i < this.size(); i++){
			float squareOfDistance = point.getSquareOfDistance(this.get(i).getCentroid());
			if (squareOfDistance < minSquareOfDistance)	{
				minSquareOfDistance = squareOfDistance;
				itsIndex = i;
			}
		}
		return itsIndex;
	}
	
	public boolean updateClusters(){
		for(Cluster cluster:this) {
			cluster.updateCentroid();
			cluster.getPoints().clear();
		}
		isChanged = false;
		assignPointsToClusters();
		return isChanged;
	}
	
	public void assignPointsToClusters() {
		for (Point point:points) {
			int previousGroup = point.getGroup();
			int newGroup = getNearestCluster(point);
			if (previousGroup != newGroup) {
				isChanged = true;
			}
			Cluster target = this.get(newGroup);
			point.setGroup(newGroup);
			target.getPoints().add(point);
		}
	}
}
