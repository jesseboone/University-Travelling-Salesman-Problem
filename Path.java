
public class Path {
	public int[] order;
	public double totalDist;

	public Path() {
		order = new int[1002];
		totalDist = 0.0;
	}
	
	public void reverse(int j, int k) {
		// System.out.println("Reversing " + order[j] + " through " + order[k]);
		int temp;
		int loop = (j+k)/2;
		for (int i=j; i<=loop; i++) {
			temp = order[i];
			order[i] = order[k];
			order[k--] = temp;
		}
	}

	public void show() {
		for (int i=1; i<1001; i++) {
			System.out.print(order[i] + ", ");
		}
		System.out.println(order[1001]);
		System.out.println(totalDist);
	}

	public void show(int j, int k) {
		for (int i=j; i<=k; i++) {
			System.out.print(order[i]);
			System.out.print(" ");
		}
		System.out.println();
	}

	public void coordinates(Unis[] Uni) {
		int j;
		for (int i=1; i<1002; i++) {
			j = order[i];
			System.out.println(Uni[j].lat + ", " + Uni[j].lon);
		}
	}
}

