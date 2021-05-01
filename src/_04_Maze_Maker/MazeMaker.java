package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
	Cell ce =maze.cells[randGen.nextInt(maze.cells.length)][randGen.nextInt(maze.cells.length)];
		
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(ce);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList <Cell> unvisitneighbor =getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if(unvisitneighbor.size()!=0) {
			//C1. select one at random.
			Random rand=new Random();
			int random =rand.nextInt(unvisitneighbor.size());
			Cell neighbr=unvisitneighbor.get(random);
			//C2. push it to the stack
		uncheckedCells.push(neighbr);
			//C3. remove the wall between the two cells
				if(currentCell.hasNorthWall()) {
					neighbr.setNorthWall(false);
				}
				if(currentCell.hasSouthWall()) {
					neighbr.setSouthWall(false);
				}
				if(currentCell.hasEastWall()) {
					neighbr.setEastWall(false);
				}
				if(currentCell.hasWestWall()) {
					neighbr.setWestWall(false);
				}
			//C4. make the new cell the current cell and mark it as visited
		 currentCell = neighbr;
		currentCell.setBeenVisited(true);
				
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		//D. if all neighbors are visited
		else {
			if(uncheckedCells.isEmpty()==false) {
				currentCell=uncheckedCells.pop();
				selectNextPath(currentCell);
			}
		}
			//D1. if the stack is not empty
			
				// D1a. pop a cell from the stack
						
				// D1b. make that the current cell
		
				// D1c. call the selectNextPath method with the current cell
				
			
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if(c1.getX()>=c2.getX()) {
			c1.setWestWall(false);
			c2.setEastWall(false);
		}
		if(c1.getX()<=c2.getX()) {
			c2.setWestWall(false);
			c1.setEastWall(false);
		}
		if(c1.hasEastWall()) {
			c2.setEastWall(false);
		}
		if(c1.hasWestWall()) {
			c2.setWestWall(false);
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> getunvisitNeighbor=new ArrayList<Cell> ();
		if(c.getX()-1>=0&&maze.cells[c.getX()-1][c.getY()].hasBeenVisited()==false) {
			getunvisitNeighbor.add(maze.cells[c.getX()-1][c.getY()]);
		}
		if(c.getX()+1<maze.cells.length&&maze.cells[c.getX()+1][c.getY()].hasBeenVisited()==false) {
			getunvisitNeighbor.add(maze.cells[c.getX()+1][c.getY()]);
		}
		if(c.getY()-1>=0&&maze.cells[c.getX()][c.getY()-1].hasBeenVisited()==false) {
			getunvisitNeighbor.add(maze.cells[c.getX()][c.getY()-1]);
		}
		if(c.getY()+1<maze.cells.length&&maze.cells[c.getX()][c.getY()+1].hasBeenVisited()==false) {
			getunvisitNeighbor.add(maze.cells[c.getX()][c.getY()+1]);
		}
		return getunvisitNeighbor;
	}
}
