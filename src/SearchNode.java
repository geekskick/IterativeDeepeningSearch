public class SearchNode implements Cloneable{
	private int x = 0;
	private int y = 0;
	private int depth = 0;
	
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
