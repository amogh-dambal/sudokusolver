package sudoku;

import java.security.InvalidParameterException;
import java.util.HashSet;

public class Grid {
	
	private Integer[][] grid;
	
	public Grid() {
		this.grid = new Integer[9][9];
	}
	
	public Grid(Integer[][] grid) {
		if (grid.length * grid[0].length != 81) {
			throw new InvalidParameterException("grid array size is incorrect!");
		}
		else {
			this.setGrid(grid);
		}
	}
	
	// parameter string: 81 digits separated by commas
	// 0 if empty, 1-9 for sudoku
	public Grid(String grid) {
		String[] input = grid.split(",");
		
		this.setGrid(new Integer[9][9]);
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.grid[i][j] = Integer.parseInt(input[(i * 9) + j].trim());
			}
		}
	}

	// brute force check
	// sum of rows, columns, squares
	// uniqueness of rows, columns, squares
	// assumes numbers 1-9 only
	// call only when checking finished solution
	public boolean isValid() {
		
		Integer sum;
		HashSet<Integer> unq = null;
		
		// rows
		for (int i = 0; i < grid.length; i++) {
			sum = 0;
			unq = new HashSet<>();
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] < 1 || grid[i][j] > 9) {
					System.err.print("Invalid digit at location (" + i + ", " + j + ")");
					return false;
				}
				sum += grid[i][j];
				unq.add(grid[i][j]);
			}
			if (sum != 45 || unq.size() < 9) return false;
		}
		
		// columns
		for (int j = 0; j < grid.length; j++) {
			sum = 0;
			unq = new HashSet<>();
			for (int i = 0; i < grid.length; i++) {
				if (grid[i][j] < 1 || grid[i][j] > 9) {
					System.err.print("Invalid digit at location (" + i + ", " + j + ")");
					return false;
				}
				sum += grid[i][j];
				unq.add(grid[i][j]);
			}
			if (sum != 45 || unq.size() < 9) return false;
		}
		
		// squares
		// will iterate over each starting point of each square
		// leftmost corner
		for (int i = 0; i <= 6; i += 3) { 
			for (int j = 0; j <= 6; j += 3) {
				if (grid[i][j] < 1 || grid[i][j] > 9) {
					System.err.print("Invalid digit at location (" + i + ", " + j + ")");
					return false;
				}
				sum = 0;
				unq = new HashSet<>();
				for (int x = i; x < (i + 2); x++) {
					for (int y = j; y < (j + 2); y++) {
						sum += grid[x][y];
						unq.add(grid[x][y]);
					}
				}
				if (sum != 45 || unq.size() < 9) return false;
			}
		}
		return true;
		
	}

	public Integer[][] getGrid() {
		return grid;
	}

	public void setGrid(Integer[][] grid) {
		this.grid = grid;
	}
	
	public String toString() {
		StringBuilder out = new StringBuilder();
		for (final Integer[] row : this.grid) {
			for (final Integer i : row) {
				out.append(i + "\t");
			}
			out.append("\n");
		}
		
		return out.toString();
	}
}
