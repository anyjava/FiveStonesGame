package gui;

class FindCrossPoint {
	public static int[] find(int x, int y) {
		return new CrossPointLogic(x, y).getResult();
	}
}

class CrossPointLogic {
	
	private int x, y;
	
	private final int xStart = 20, yStart = 20, cellSize = 29;
	
	private int[] points = new int[2];
	
	public CrossPointLogic(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	private void logic(int point, int index) {
		int start;
		
		if (index == 0) start = xStart;
		else start = yStart;
		
		if (((point + start) % cellSize) < cellSize/2)
			points[index] = (int)((point + start-10) / cellSize) * cellSize - 9;
		else points[index] = (int)((point + start-10) / cellSize) * cellSize - 9;
	}

	public int[] getResult() {
		logic(x, 0);
		logic(y, 1);
		return points;
	}

}
