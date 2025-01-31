package _03_Conways_Game_of_Life;
import java.awt.Color;
import java.awt.Graphics;

public class Cell implements Drawable{
	public boolean isAlive = false;
	
	private int x;
	private int y;

	private int cellSize;
	
	
	public Cell(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.cellSize = size;
	}
	
	//11. Complete the liveOrDie method
	//    It sets isAlive to true or false based on the neighbors and 
	//the rules of the game
	/*
	 * 1. Any live cell with fewer than two live nieghbours dies, as if caused by underpopulation.
	 * 2. Any live cell with two or three live neighbours lives on to the next generation.
	 * 3. Any live cell with more than three live neighbours dies, as if by overpopulation.
	 * 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
	 * (source: Wikipedia)
	 * */
	public void liveOrDie(int numNeighbors) {
		if(numNeighbors<2&&isAlive==true) {
			isAlive=false;
		}
		if((numNeighbors==2||numNeighbors==3)&&isAlive==true) {
			isAlive=true;
		}
		if(numNeighbors>3&&isAlive==true) {
			isAlive=false;
		}
		if(numNeighbors==3&&isAlive==false) {
			isAlive=true;
		}
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	//12. Complete the draw method.
	//    It draws a colored square if cell is alive
	//    draws empty square if cell is dead
	@Override
	public void draw(Graphics g) {
		if(isAlive==true) {
	g.setColor(Color.blue);
	g.fillRect(x*cellSize,y*cellSize,cellSize,cellSize);
		}
		
		if(isAlive==false) {
			g.setColor(Color.red);
			g.fillRect(x*cellSize,y*cellSize,cellSize,cellSize);
				}
		
				g.setColor(Color.black);
				g.drawRect(x*cellSize,y*cellSize,cellSize,cellSize);
		
	}
}
