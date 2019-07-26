package sudoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;


// TODO: add javafx features
// TODO: logging
public class Main {
	
	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
	
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
		
		// timing for logging purposes
		long start = System.nanoTime();
		s.solve();
		long end = System.nanoTime();
		
		System.out.println(s.getGrid());

		double runtime = ((end - start) / 1000000000.0);
		System.out.println("Sudoku solved in: " + runtime + " seconds");
	}
}
