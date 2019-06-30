package sudoku;


public class Main {

	// launch solver with sudoku filename
	public static void main(String[] args) {
		// launch solver
		
		Solver s = new Solver("resources/test.txt");
		
		s.solve();
		System.out.println(s.getGrid());
	}
}
