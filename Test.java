import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Test {
	public static void main(String[] args) {


		Unis[] Uni = new Unis[1001];

		try {
		Scanner S = new Scanner(new FileReader("Universities.txt")).useDelimiter(", ");
		int rank;
		double lat;
		double lon;
		String name;
		String country;

		// filling array with data
		for(int i=1;i<1001;i++) {
			rank = S.nextInt();
			name = S.next();
			country = S.next();
			lat = S.nextDouble();
			lon = S.nextDouble();
			Uni[i] = new Unis(rank, name, lat, lon);
		}

		S.close(); }
		catch (IOException e) {
        	e.printStackTrace();
    	}

    	// TO SHOW UNIS ARRAY
		/*for(int i=1;i<1001;i++) {
			Uni[i].show();
		}*/

		// Building graph
		double[][] graph = new double[1001][1001];
		for(int i=1;i<1001;i++) {
			graph[i][i] = Double.MAX_VALUE;
			for(int j=i+1;j<1001;j++) {
				if (Math.abs(j-i)<=100) {	
					graph[i][j] = Haversin(Uni[i].lat, Uni[i].lon, Uni[j].lat, Uni[j].lon);
					graph[j][i] = graph[i][j];
				}
				else {
					graph[i][j] = Double.MAX_VALUE;
					graph[j][i] = Double.MAX_VALUE;
				}
			}
		}


		// USING CUSTOM PATH from File
		Path CP = new Path();
		try {
		Scanner S2 = new Scanner(new FileReader("check.txt"));
		int i=0;
		int j=0;
		int k=0;
		CP.order[1] = S2.nextInt();
		for (i=2; i<1002; i++) {
			CP.order[i] = S2.nextInt();
			j=CP.order[i-1];
			k=CP.order[i];
			CP.totalDist += Haversin(Uni[j].lat, Uni[j].lon, Uni[k].lat, Uni[k].lon);
		}
		// CP.totalDist += Haversin(Math.toRadians(Uni[551].lat), Math.toRadians(Uni[551].lon), Math.toRadians(Uni[608].lat), Math.toRadians(Uni[608].lon));
		S2.close(); }
		catch (IOException e) {
        	e.printStackTrace();
    	}

    	CP.show();
    	CP.coordinates(Uni);
    	System.out.println(CP.totalDist);

		/*try {
		Scanner S = new Scanner(new FileReader("Universitie.txt")).useDelimiter(", ");
		Unis[] Uni = new Unis[11];
		int rank;
		double lat;
		double lon;
		String name;
		String country;

			System.out.println(S.nextInt());
			System.out.println(S.next());
			System.out.println(S.next());
			System.out.println(S.nextDouble());
			System.out.println(S.nextDouble());
		
		int i = 1;
		//Uni[i] = new Unis(rank, name, lat, lon);
		//Uni[i].show();
			/*Uni[1].rank = S.nextInt();
			Uni[1].name = S.next();
			country = S.next(); // Could add to string but I dont think I need it
			Uni[1].lat = S.nextDouble();
			Uni[1].lon = S.nextDouble();
			Uni[1].vis = false;


			

		S.close();}

		catch (IOException e) {
        	e.printStackTrace();
    	}*/

	}

	public static double Haversin(double a, double b, double c, double d) {
		a = Math.toRadians(a);
		b = Math.toRadians(b);
		c = Math.toRadians(c);
		d = Math.toRadians(d);
		double rr = 6371.0; // in km
		double s2 = (c-a) / 2.0;
		double ccs2 = (d-b) / 2.0;
		s2 = Math.sin(s2);
		s2 = s2*s2;
		ccs2 = Math.sin(ccs2);
		ccs2 = ccs2 * ccs2 * Math.cos(a) * Math.cos(c);
		double ans = Math.sqrt(s2 + ccs2);
		ans = 2 * rr * Math.asin(ans);
		return ans;
	}
}



