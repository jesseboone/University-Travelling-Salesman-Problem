
public class Unis {
	public int rank;
	public String name;
	public double lat;
	public double lon;
	public boolean vis;

	public Unis(int r, String n, double la, double lo) {
		rank = r;
		name = n;
		lat = la;
		lon = lo;
		vis = false;
	}

	public void show() {
		System.out.println(rank + " " + name + " " + lat + " " + lon + " " + vis);
	}
}


