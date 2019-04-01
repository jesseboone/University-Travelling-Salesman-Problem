import java.util.*;
import java.io.File;

public class PathMaker {
	public static void main(String[] args) {

		for (int i = 608; i<1000; i++) {
			if(i%100 != 0) System.out.println(i);
		}
		for (int i = 1000; i>0; i-=100) {
			System.out.println(i);
		}
		for (int i = 1; i<608; i++) {
			if(i%100 != 0) System.out.println(i);
		}

	}
}



