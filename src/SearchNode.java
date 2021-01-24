import java.util.ArrayList;
import java.util.List;

public class SearchNode implements Cloneable{
	/* The integers x, y, and depth, as described above.
 		A suitable constructor.
		An equals method that compares the SearchNode with another object and
			returns true when they are of the same type and have the same state (the depth
			is not part of the state).
		An expand method that creates the neighbours of the SearchNode.
		You may wish to implement the toString method (or the equivalent in your
			chosen language) to allow SearchNodes to be printed out conveniently.
	 */
	private int x = 0;
	private int y = 0;
	private int depth = 0;
	private static final int YLIM = 15;
	private static final int XLIM = 15;
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDepth() {
		return depth;
	}

	public SearchNode(final int x, final int y, final int depth) {
		this.x = x;
		this.y = y;
		this.depth = depth;
	}
	
	public List<SearchNode> expand() {
		List<SearchNode> neighbours = new ArrayList<SearchNode>();
		if(x > 0) {
			neighbours.add(new SearchNode(x-1, y, depth+1));
		}
		if(y > 0) {
			neighbours.add(new SearchNode(x, y-1, depth+1));
		}
		if(y < YLIM-1) {
			neighbours.add(new SearchNode(x, y+1, depth+1));
		}
		if(x < XLIM-1) {
			neighbours.add(new SearchNode(x+1, y, depth+1));
		}
		return neighbours;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ") [depth = " + depth + "]";
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchNode other = (SearchNode) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
