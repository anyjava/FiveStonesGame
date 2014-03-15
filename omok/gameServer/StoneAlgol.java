package gameServer;

import java.awt.Point;
import java.util.ArrayList;

public class StoneAlgol {
    private final int X = 0;
    private final int Y = 1;

    private final int[] UP         = {0,-1};
    private final int[] RIGHT_UP   = {1,-1};
    private final int[] RIGHT      = {1,0};
    private final int[] RIGHT_DOWN = {1,1};
    private final int[] DOWN       = {0,1};
    private final int[] LEFT_DOWN  = {-1,1};
    private final int[] LEFT       = {-1,0};
    private final int[] LEFT_UP    = {-1,-1};

	
	private byte[][] board;
	private ArrayList<Point> pointHistory = new ArrayList<Point>();
	
	public StoneAlgol() {
		this.board = new byte[19][19];
		setting();
	}
	
	public static void main(String[] ab) {
		StoneAlgol temp = new StoneAlgol();
		int[] a = new int[2];
		a[0] = 1;
		a[1] = 1;
		temp.addStone(a, true);
		a[0] = 2;
		a[1] = 2;
		temp.addStone(a, true);
		a[0] = 3;
		a[1] = 3;
		temp.addStone(a, true);
		a[0] = 4;
		a[1] = 4;
		temp.addStone(a, true);
		a[0] = 5;
		a[1] = 5;
		temp.addStone(a, true);
		
	}
	
	private void setting() {
		for(int i=0; i<19; i++)
			for(int j=0; j<19; j++) board[i][j] = -1;

	}
	
	
	public int addStone(int[] stoneLocation, boolean isBlack) {
		int x = stoneLocation[X] / 29;   //  '29' is size of cell.
		int y = stoneLocation[Y] / 29;
		
//		int x = stoneLocation[X]; 
//		int y = stoneLocation[Y];

		pointHistory.add(new Point(x,y));
			
		if (isBlack)
			this.board[x][y] = 0;
		else this.board[x][y] = 1;
		
//		System.out.println("(" + x + "," + y + ")");
		
//		System.out.println(analysis(x, y));
		return analysis(x, y);
	}
	
	private int analysis(int xStart, int yStart) {
		int[] point = new int[2];
		point[0] = xStart;
		point[1] = yStart;
		
		int up = 0;
		int rightUp = 0;
		int right = 0;
		int rightDown = 0;
		int down = 0;
		int leftDown = 0;
		int left = 0;
		int leftUp = 0;
		
		int[] result = new int[4];

		up        = countSameStone(point, UP);
		rightUp   = countSameStone(point, RIGHT_UP);
		right     = countSameStone(point, RIGHT);
		rightDown = countSameStone(point, RIGHT_DOWN);
		down      = countSameStone(point, DOWN);
		leftDown  = countSameStone(point, LEFT_DOWN);
		left      = countSameStone(point, LEFT);
		leftUp    = countSameStone(point, LEFT_UP);
		
		result[0] = up + down;
		result[1] = rightUp + leftDown;
		result[2] = right + left;
		result[3] = rightDown + leftUp;
		
		for (int i = 0; i < result.length; i++) {
			if(result[i] == 4) return 1;
		}
		
		return -1;
	}

	private int countSameStone(int[] point, int[] direction) {
		int[] checkPoint = new int[2];
		
		checkPoint[0] = point[0];
		checkPoint[1] = point[1];
		
		int count = 0;

		while (true) {
			checkPoint[X] += direction[X];
			checkPoint[Y] += direction[Y];
			
			if (checkPoint[X] < 0 || checkPoint[Y] < 0)       // pointer reach edge of board
				break;
			else if (checkPoint[X] > 18 || checkPoint[Y] > 18)
				break;
			
//			System.out.println("x : " + checkPoint[0]);
//			System.out.println("y : " + checkPoint[1]);
//			System.out.println("count : " + count);
			
			if(checking(point, checkPoint)) 
				count++;
			else break;
		}
		return count;
	}

	private boolean checking(int[] point, int[] checkPoint) {
		int x = point[X];
		int y = point[Y];
		int x_check = checkPoint[X];
		int y_check = checkPoint[Y];
		
		try {
			if(board[x][y] == board[x_check][y_check])
				return true;
			else {
				return false;
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("x : " + x_check);
			System.out.println("y : " + y_check);
			System.out.println("ArrayIndexOutOfBoundsException!!");
			return false;
		}
		
	}

	public void subLastStone() {
		Point lastLocation = pointHistory.remove(pointHistory.size()-1);
		
		board[(int) lastLocation.getX()][(int) lastLocation.getY()] = -1;
	}

}
