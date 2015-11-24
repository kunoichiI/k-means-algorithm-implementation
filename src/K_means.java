



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class K_means {
	public final ArrayList<Point> points = new ArrayList<Point>();
	private static Random random = new Random();
	private Clusters pointClusters;


	
	public void read_txt(String fileName) {
		
		try {
			Scanner sc = new Scanner(new File(fileName));
			while (sc.hasNextLine()){
				sc.nextLine();
				int i = sc.nextInt();
				//System.out.println(i);
				Float x = sc.nextFloat();
				//System.out.println(x);
				Float y = sc.nextFloat();
				//System.out.println(y);
				Point p = new Point(x,y);
				p.setId(i);
				//System.out.println("id:" + i + " " + "(" + p.x + "," + p.y +")");
				points.add(p); // Store every point into an arrayList named points.
			}
			sc.close();
			//printArrayList(points);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void printArrayList(ArrayList<Point> list) { // helper method for printing any list in the format of ArrayList<Point> type
		for (Point point:list) {
			System.out.println(point.toString());
		}
	}
	
	/*
	 * Step 1: get random K seeds as initial centroids of K clusters
	 */
	private void getInitialKRandomSeeds(int k){
		pointClusters = new Clusters(points);
		ArrayList<Point> kPoints = initialize(k);
		for (int i = 0; i < k; i++) {
			kPoints.get(i).setGroup(i+1);
			pointClusters.add(new Cluster(kPoints.get(i)));
		}
	}
	
	private ArrayList<Point> initialize(int k){ // Choose K centroids randomly
		ArrayList<Point> kPoints = new ArrayList<Point>();
		int size = points.size();
		boolean[] alreadyChosen = new boolean[size];
		for (int i = 0; i < k; i++){
			int index = -1, r = random.nextInt(size--) +1;
			for(int j = 0; j < r; j++) {
				index++;
				while(alreadyChosen[index]){
					index++;
				}
			}
			kPoints.add(points.get(index));
			alreadyChosen[index] = true;
		}
		//printArrayList(kPoints);
		return kPoints;
	}
	
	/*
	 * Step 2: assign points to initial clusters
	 */
	public void getInitialClusters(){
		pointClusters.assignPointsToClusters();
	}
	
	/*
	 * Step 3: update the k clusters until no changes in their members occur
	 */
	public void updateClustersUntilNoChange(){
		boolean isChanged = pointClusters.updateClusters();
		int i = 0;
		while(isChanged && i < 25){
			isChanged = pointClusters.updateClusters();
			i++;
		}
		System.out.println("Updating Clusters takes in all" + " " + i + " " + "iterations.");
	}
	
	public ArrayList<Cluster> getPointClusters(int k) {
		if (pointClusters == null) {
			getInitialKRandomSeeds(k);
			getInitialClusters();
			updateClustersUntilNoChange();
		}
		return pointClusters;
	}
	
	public void calculate_SSE() {
		//boolean isChanged = pointClusters.updateClusters();
		float sum = 0f;
		for (Cluster cluster:pointClusters){
				sum += cluster.calculateSSE();
			}
		System.out.println("The SSE of Clustering is:" + sum);
		}
	
	public static void startClustering(ArrayList<Cluster> pointClusters) {
		for(int i = 0; i < pointClusters.size() ; i++) {
			System.out.println("Cluster" + (i+1) + ":" + pointClusters.get(i).toString());
		}
		
	}
	
	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		K_means kmeans = new K_means();
		
		kmeans.read_txt("/Users/Stephanie/Documents/workspace/K_means/src/test_data.txt");
		ArrayList<Cluster> pointClusters = kmeans.getPointClusters(k);
		
		PrintStream out;
		try {
			out = new PrintStream(new FileOutputStream("output.txt"));
			System.setOut(out);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		startClustering(pointClusters);
		kmeans.calculate_SSE();	
			
		}
	
		
		

}




