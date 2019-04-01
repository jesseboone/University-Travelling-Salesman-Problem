import java.util.*;
import java.lang.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class TSP {
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
		double d = 500000;
	while (d > 474850) {d = Rand(Uni, graph);}
	} // END OF MAIN


	public static double Rand(Unis[] Uni, double[][] graph) {
		// USING CUSTOM PATH from File
		Path CP = new Path();
		try {
		Scanner S2 = new Scanner(new FileReader("Universitie.txt"));
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
		S2.close(); }
		catch (IOException e) {
        	e.printStackTrace();
    	}

    	// CP.show();
    	// CP.coordinates(Uni);


		// FOR THE 2-OPT SWAP
		int count = 0;
		int wrongswaps = 0;
		int swaps = 0;
		double rand = 0.0;
    	while (count < 38) {
    		for (int j=1001; j>1; j--) {
    			for (int k=2; k<1000; k++) {
    				// IF the front and back are within 100 of the head and tail respectively
    				// AND IF making the swap results in a shorter route... call SWAP
    				if ( (k != j) && (j != k+1) && (k != j-1) && (j != j-1) && (k != k+1)) {
    				if ((Math.abs(CP.order[j]-CP.order[k+1])<=100) && (Math.abs(CP.order[k]-CP.order[j-1])<=100)) {
    					double dHead = graph[CP.order[j-1]][CP.order[j]];
    					double dTail = graph[CP.order[k]][CP.order[k+1]];
    					double dHnew = graph[CP.order[j-1]][CP.order[k]];
    					double dTnew = graph[CP.order[j]][CP.order[k+1]];
    					rand = Math.random()*150;
    					if ((dHead+Math.random()*rand+dTail+Math.random()*rand) > (dHnew+Math.random()*rand+dTnew+Math.random()*rand)) {
    						// CP.totalDist = CP.totalDist + dHnew + dTnew - dHead - dTail;
							CP.reverse(j,k);
    						swaps++;
    					}
    					else if ( (wrongswaps < 29876) && (Math.exp((dHnew+dTnew)-(dHead+dTail)) < j) ) {CP.reverse(j,k); wrongswaps++;}
    				}
    				}
    			}
    		}
    		count++;

    	} // END OF WHILE


	CP.totalDist = tD(CP, graph);
    	if (CP.totalDist < 478143.0) CP.show();
    if (CP.totalDist<490000) {	
    	System.out.println(CP.totalDist);
    	// System.out.println("Total number of swaps was " + swaps);
    	// System.out.println("Total number of wrongswaps was " + wrongswaps);
    	System.out.println("Rand is: " + rand);
	}


    return CP.totalDist;
	} // END OF RAND

	public static double tD(Path CP, double[][] graph) {
		// TO RECALCULATE THE RIGHT totalDist of CP
    	int i=0;
		int j=0;
		int k=0;
		double totalDist = 0.0;
		for (i=2; i<1002; i++) {
			j=CP.order[i-1];
			k=CP.order[i];
			totalDist += graph[j][k];
		}
		return totalDist;	
	}

	public static int findClosest(int here, Unis[] Uni, double[][] graph, Path P1) {
		int closest = here;
		double min = Double.MAX_VALUE;
		int i = 0;
		int j = 0;
		if (here <= 100) { i = 1; j = here+100; }
		else if (here >= 901) { j = 1000; i = here-100; }
		else { i=here-100; j=here+100;}

		for (i=i; i<j; i++) {
			if ((!Uni[i].vis) && (graph[i][here] < min)) {
				closest = i; 
				min = graph[i][here];
			}
		}
		Uni[closest].vis = true;
		P1.totalDist += min;
		return closest;
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

} // END OF TSP					