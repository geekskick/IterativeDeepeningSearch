import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class IDSearch implements Runnable {
	/* A method called iterativeDeepening that contains the main loop of the
		iterative deepening algorithm (i.e. the loop that calls depthLimitedSearch
		repeatedly with increasing depth limits).
	 * A method called depthLimitedSearch that implements a depth-first graph
		search with a depth limit.
	 * A main method to start the program (or the equivalent in your chosen
		language). */

	private StateSpace problem;
	private final int[][] grid1 = new int[][] {
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	private final int[][] grid2 = new int[][] {
			{ 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 3, 0, 1, 0 },
			{ 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	private final int[][] grid3 = new int[][] {
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 2, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 3, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	private final int[][] grid4 = new int[][] {
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 2, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 3, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	private final int[][] grid5 = new int[][] {
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 3, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 2, 0, 1, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0 } };

	// 0 == clear
	// 1 == jam
	// 2 == start
	// 3 == goal

	private ArrayDeque<SearchNode> solution = new ArrayDeque<SearchNode>();
	private List<SearchNode> explored = new ArrayList<SearchNode>();
	private Queue<String> printQueue;
	private final int[][][] grids = new int[][][] {grid1,grid2,grid3,grid4,grid5};

	public IDSearch(Queue<String> solutionQueue) {
		this.printQueue = solutionQueue;
	}

	public Deque<SearchNode> recurDepthLimitedSearch(final SearchNode n,
			final int depthLimit) {

		explored.add(n);
		solution.addLast(n);
		printQueue.add(problem.serialise(solution));
		if (problem.isGoal(n)) {
			return solution;
		} else if (depthLimit == 0) {
			solution.removeLast();
			return null;
		}

		for (SearchNode neighbour : n.expand()) {
			if (problem.canMoveTo(neighbour) && !solution.contains(neighbour)) {
				if (explored.contains(neighbour)) {
					// The depth isn't part of the searchnode equality
					final int idx = explored.indexOf(neighbour);
					final SearchNode fc = explored.get(idx);
					final int previousDistance = fc.getDepth();
					final int currentDistance = neighbour.getDepth();
					// If the already explored one is closer this time I should
					// go back there
					// depth is lower closer to the centre
					if (previousDistance > currentDistance) {
						explored.remove(idx);
						//printQueue.add("Reexploring " + neighbour + "(prev = " + previousDistance + ")");
						if (null != recurDepthLimitedSearch(neighbour,
								depthLimit - 1)) {
							return solution;
						}
					}
				} else {
					if (null != recurDepthLimitedSearch(neighbour,
							depthLimit - 1)) {
						return solution;
					}
				}
			}
		}
		solution.removeLast();
		return null;
	}

	public void iterativeDeepening() {
		for (int depth = 0; depth < 40; ++depth) {
			solution.clear();
			explored.clear();
			printQueue.add("Depth = " + depth);
			Deque<SearchNode> result = recurDepthLimitedSearch(
					problem.getInit(), depth);
			if (result != null) {

				ArrayDeque<SearchNode> path = new ArrayDeque<SearchNode>();
				while (!solution.isEmpty()) {
					path.addLast(solution.removeFirst());
					printQueue.add(problem.serialise(path));
				}
				printQueue.add("Shorted path is " + path.size() + " steps");

				return;
			}

		}

	}

	public static void main(String[] args) throws InterruptedException {
		
		int grid = 0;
		if(args.length == 1) {
			final String idx = args[0];
			grid = Integer.parseInt(String.valueOf(idx.charAt(idx.length()-1)));
		}
		
		final Queue<String> solutionQueue = new LinkedBlockingQueue<String>();
		final IDSearch search = new IDSearch(solutionQueue);
		search.problem = new StateSpace(search.grids[grid]);

		final Thread t = new Thread(search);
		t.start();

		while (!solutionQueue.isEmpty() || t.isAlive()) {
			try {
				final String toPrint = solutionQueue.remove();
				System.out.println("------------");
				System.out.println(toPrint);
			} catch (NoSuchElementException e) {
			}
			Thread.sleep(50);
		}
		t.join();

		System.out.println("Done");
		
	}

	@Override
	public void run() {
		iterativeDeepening();
	}
}
