package sudoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Solver {

	private Grid grid;
	
	public Solver() {
		this.grid = new Grid();
	}
	
	public Solver(Grid grid) {
		try {
			this.grid = grid;
		}
		catch (Exception e) {
			e.printStackTrace();
			this.grid = new Grid();
		}
	}
	
	public Solver(String gridfile) {

		BufferedReader br;
		StringBuilder sb = new StringBuilder();
		String line;
		
		try {
			br = Files.newBufferedReader(Paths.get(gridfile));
			
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.grid = new Grid();
			return;
		}
		
		this.grid = new Grid(sb.toString());
	}
	
	// takes the unfinished grid and fills enumerates missing spaces
	public boolean solve() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				if (this.grid.getGrid()[i][j] == 0) {
					for (int k = 1; k < 10; k++) {
						if (validate(i, j, k)) {

							this.grid.getGrid()[i][j] = k;

							if (solve()) {
								return true;
							}
							else {
								this.grid.getGrid()[i][j] = 0;
							}
						}

					}
					return false;
				}
			}
		}
		return true;
	}
	
	// checks validity of digit k at spot (i, j) in grid
	private boolean validate(final int i, final int j, final int k) {
		return (
				isRowValid(i, k) && 
				isColValid(j, k) && 
				isSquareValid(i, j, k)
		);
	}
	
	// for the row
	private boolean isRowValid(final int i, final int k) {
		for (int p = 0; p < grid.getGrid().length; p++) {
			if (grid.getGrid()[i][p] == k) return false;
		}
		return true;
	}
	
	// for the column
	private boolean isColValid(final int j, final int k) {
		for (int q = 0; q < grid.getGrid().length; q++) {
			if (grid.getGrid()[q][j] == k) return false;
		}
		return true;
		
	}
	
	// for the square
	private boolean isSquareValid(final int i, final int j, final int k) {
		final int squareStartRow = (i / 3) * 3;
		final int squareStartColumn = (j / 3) * 3;
		
		for (int x = squareStartRow; x < (squareStartRow + 3); x++) {
			for (int y = squareStartColumn; y < (squareStartColumn + 3); y++) {
				if (grid.getGrid()[x][y] == k) return false;
			}
		}
		return true;
	}
	
	public Grid getGrid() {
		return this.grid;
	}
}
