package sudoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	// launch solver with sudoku filename
	// command line argument
	public static void main(String[] args) {
		
		String filename = new String();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		if (args.length > 0) {
			filename = args[0];
		}
		else {
			System.out.print("enter sudoku filename: ");
			try {
				filename = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				filename = null;
			}
		}
		
		Solver s;
		if (filename != null) {
			s = new Solver(filename);
		}
		else {
			System.err.println("Error: File is null");
			return;
		}
		
		s.solve();
		System.out.println(s.getGrid());
	}
}
