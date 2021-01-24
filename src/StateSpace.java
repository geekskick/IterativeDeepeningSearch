import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

public class StateSpace {

	public SearchNode getGoal() {
		return goal;
	}

	public int[][] getGrid() {
		return grid;
	}

	private SearchNode goal;
	private SearchNode init;
	private int[][] grid;

	public StateSpace(final int[][] grid) {
		this.goal = findGoal(grid);
		this.init = findInitial(grid);
		this.grid = grid;
	}

	public SearchNode findInitial(final int[][] grid) {
		for (int row = 0; row < grid.length; ++row) {
			for (int col = 0; col < grid[row].length; ++col) {
				if (grid[row][col] == 2) {
					return new SearchNode(col, row, 0);
				}
			}
		}
		throw new NoSuchElementException("Unable to find start node");
	}

	public SearchNode findGoal(final int[][] grid) {
		for (int row = 0; row < grid.length; ++row) {
			for (int col = 0; col < grid[row].length; ++col) {
				if (grid[row][col] == 3) {
					return new SearchNode(col, row, 0);
				}
			}
		}
		throw new NoSuchElementException("Unable to find goal node");
	}

	public boolean canMoveTo(final SearchNode s) {
		return grid[s.getY()][s.getX()] != 1;
	}

	public boolean isGoal(final SearchNode node) {
		return node.equals(goal);
	}

	public SearchNode getInit() {
		return init;
	}

	public String serialise(final Deque<SearchNode> solution) {
		String str = new String();
		for (int r = 0; r < grid.length; ++r) {
			for (int c = 0; c < grid[r].length; ++c) {
				SearchNode s = new SearchNode(c, r, 0);
				// not the current
				str += getGoal().equals(s) ? "G"
						: getInit().equals(s) ? "I"
								: solution.contains(s)
										&& !solution.peekLast().equals(s)
												? "s"
												: solution.peekLast().equals(s)
														? "+"
														: getGrid()[r][c] == 1
																? " "
																: ".";
			}
			str += "\n";
		}
		return str;
	}

	public List<SearchNode> expand(final SearchNode node) {
		final List<SearchNode> neighbours = new ArrayList<SearchNode>();
		if (node.getX() > 0) {
			final SearchNode candidate = new SearchNode(node.getX() - 1,
					node.getY(), node.getDepth() + 1);
			if (canMoveTo(candidate)) {
				neighbours.add(candidate);
			}
		}
		if (node.getY() > 0) {
			final SearchNode candidate = new SearchNode(node.getX(),
					node.getY() - 1, node.getDepth() + 1);
			if (canMoveTo(candidate)) {
				neighbours.add(candidate);
			}
		}
		if (node.getY() < grid.length - 1) {
			final SearchNode candidate = new SearchNode(node.getX(),
					node.getY() + 1, node.getDepth() + 1);
			if (canMoveTo(candidate)) {
				neighbours.add(candidate);
			}
		}
		if (node.getX() < grid.length - 1) {
			final SearchNode candidate = new SearchNode(node.getX() + 1,
					node.getY(), node.getDepth() + 1);
			if (canMoveTo(candidate)) {
				neighbours.add(candidate);
			}
		}
		return neighbours;
	}

}
