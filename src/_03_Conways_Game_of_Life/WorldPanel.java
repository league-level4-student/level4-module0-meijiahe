package _03_Conways_Game_of_Life;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import _02_Pixel_Art.Pixel;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;
	
	private Timer timer;
	
	//1. Create a 2D array of Cells. Do not initialize it.
	 Cell[][] cells;
	
	
	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;
	
		//2. Calculate the cell size.
		cellSize = w/cpr;
		
		//3. Initialize the cell array to the appropriate size.
		cells= new Cell [cellsPerRow][cellsPerRow];
		//3. Iterate through the array and initialize each cell.
		//   Don't forget to consider the cell's dimensions when 
		//   passing in the location.
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cell (i,j,cellSize);
			}
		}
	}
	
	public void randomizeCells() {
		//4. Iterate through each cell and randomly set each
		//   cell's isAlive member to true or false
		Random r = new Random();
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j].isAlive=r.nextBoolean();
			}
		}
		repaint();
	}
	
	public void clearCells() {
		//5. Iterate through the cells and set them all to dead.
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j].isAlive=false;
			}
		}
		repaint();
	}
	
	public void startAnimation() {
		timer.start();
	}
	
	public void stopAnimation() {
		timer.stop();
	}
	
	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//6. Iterate through the cells and draw them all
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j].draw(g);
			}
		}
		
		
		// draws grid
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}
	
	//advances world one step
	public void step() {
		//7. iterate through cells and fill in the livingNeighbors array
		// . using the getLivingNeighbors method.
		int[][] livingNeighbors = new int[cellsPerRow][cellsPerRow];
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				livingNeighbors[i][j]=getLivingNeighbors(i,j);
				cells[i][j].liveOrDie(livingNeighbors[i][j]);
			}
		}
		//8. check if each cell should live or die
	
		
		
		
		repaint();
		
	}
	
	//9. Complete the method.
	//   It returns an int of 8 or less based on how many
	//   living neighbors there are of the 
	//   cell identified by x and y
	public int getLivingNeighbors(int x, int y){
		int count=0;
		if(x+1<cells.length&&cells[x+1][y].isAlive) {
			count++;
		}
		if(x-1>=0&&cells[x-1][y].isAlive) {
			count++;
		}
		if(y+1<cells.length&&cells[x][y+1].isAlive) {
			count++;
		}
		if(y-1>=0&&cells[x][y-1].isAlive) {
			count++;
		}
		if(x+1<cells.length&&y+1<cells.length&&cells[x+1][y+1].isAlive) {
			count++;
		}
		if(x+1<cells.length&&y-1>=0&&cells[x+1][y-1].isAlive) {
			count++;
		}
		if(x-1>=0&&y+1<cells.length&&cells[x-1][y+1].isAlive) {
			count++;
		}
		if(x-1>=0&&y-1>=0&&cells[x-1][y-1].isAlive) {
			count++;
		}
		return count;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//10. Use e.getX() and e.getY() to determine
		//    which cell is clicked. Then toggle
		//    the isAlive variable for that cell.
		int x=e.getX()/cellSize;
		int y=e.getY()/cellSize;
		if(x<cells.length&&y<cells.length) {
			cells[x][y].isAlive= !cells[x][y].isAlive;
		}
	
		
		
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();		
	}
}
