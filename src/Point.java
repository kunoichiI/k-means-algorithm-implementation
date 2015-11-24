

public class Point {
	float x;
	float y;
	int id;
	int group = -1;// To show in which cluster this point is.

	
	Point(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int i) {
		this.id = i;
	}
	public void setGroup(int i){
		this.group = i;
	}
	
	public float getSquareOfDistance(Point anotherPoint) {
		return (x - anotherPoint.x) * (x - anotherPoint.x) + (y - anotherPoint.y) * (y - anotherPoint.y);
	}
	
	public int getGroup() {
		return group;
	}
	
	public String toString() {
		return Integer.toString(id);
	}
}

