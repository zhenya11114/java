package graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

class GraphComponent extends JComponent {
	private static final long serialVersionUID = 1L;
	
	private int defWidth = 700;
	private int defHeight = 500;
	private float scaleXW = 1;
	private float scaleYH = 1;
	private boolean logScale = false;
	private GraphData graphData;
	private boolean noAxes = false;
	private Color color = Color.BLACK;
	private Color defColor = Color.BLACK;

	private static int prevTitleY = 0;

	GraphComponent() {
		super();
	}
	GraphComponent(int w, int h) {
		super();
		this.defHeight = h;
		this.defWidth = w;
	}
	GraphComponent(int w, int h, GraphData gd) {
		super();
		this.defHeight = h;
		this.defWidth = w;
		this.graphData = gd;
	}   
	GraphComponent(GraphData gd) {
		super();
		this.graphData = gd;
	}
	GraphComponent(GraphData gd, boolean log) {
		super();
		this.graphData = gd;
		logScale= log;
	}    
	GraphComponent(GraphData gd, boolean log, boolean noAxes, Color color) {
		super();
		this.graphData = gd;
		logScale= log;
		this.noAxes = noAxes;
		this.color = color;
	}
	
	public void setGrafData(GraphData gd, boolean log, boolean noAxes, Color color) {
		this.graphData = gd;
		logScale= log;
		this.noAxes = noAxes;
		this.color = color;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		double leftX = 50;
		double topY = 100;
		double width = 400;
		double height = 300;
		
		if(prevTitleY == 0) {
			prevTitleY = (int)topY;
		}
		prevTitleY += 100;

		if(!noAxes) {
			Rectangle2D rect = new Rectangle2D.Double(leftX, topY, width + 200, height);
			g2.draw(rect);
		}

		Font font14 = new Font("Arial", Font.PLAIN, 14);
		Font font10 = new Font("Arial", Font.PLAIN, 10);
		
		g2.setFont(font14);
		g2.setColor(color);
		g2.drawString(graphData.getTitle().getName(), (int) (leftX+width+50), prevTitleY);       
		g2.setColor(defColor);
		
		g2.setFont(font10);
		g2.drawString(graphData.getTitle().getMeansX(), (int) (leftX+width), (int) (topY+height+30));
		g2.drawString(graphData.getTitle().getMeansY(), (int) (leftX-35), (int) (topY-15));

		if(logScale) {
			if(graphData.getTitle().getMinX() != 0)
				scaleXW = (float) ((Math.log10(graphData.getTitle().getMaxX()) - Math.log10(graphData.getTitle().getMinX()))/width);
			else
				scaleXW = (float) ((Math.log10(graphData.getTitle().getMaxX()))/width);
		}
		else {
			scaleXW = (float) ((graphData.getTitle().getMaxX() - graphData.getTitle().getMinX())/width);
		}
		scaleYH = (float) ((graphData.getTitle().getMaxY() - graphData.getTitle().getMinY())/height);

		for(int i = 0; i < graphData.getX().size()-1; i++) {
			double firstX;	    	  
			double secondX;	    	  

			if(logScale) {
				if(graphData.getX().get(i) != 0)
					firstX = Math.round(Math.log10(graphData.getX().get(i))/scaleXW+leftX);
				else
					firstX = leftX;
				
				secondX = Math.round(Math.log10(graphData.getX().get(i+1))/scaleXW+leftX);
			}
			else {
				firstX = Math.round(graphData.getX().get(i)/scaleXW+leftX);
				secondX = Math.round(graphData.getX().get(i+1)/scaleXW+leftX);
			}

			double firstY = Math.round(height-graphData.getY().get(i)/scaleYH+topY);	    	  
			double secondY = Math.round(height-graphData.getY().get(i+1)/scaleYH+topY);

			g2.draw(new Line2D.Double(firstX, height+topY-10, firstX, height+topY+10));
			g2.draw(new Line2D.Double(leftX-10, firstY, leftX+10, firstY));

			String messageX = graphData.getX().get(i).toString();
			g2.drawString(messageX, (int) firstX-5, (int) ((int) height+topY+20));
			
			String messageY = graphData.getY().get(i).toString();
			g2.drawString(messageY, (int) ((int) leftX-35), (int) firstY);

			g2.setColor(color);

			Rectangle2D rec = new Rectangle2D.Double(firstX-3, firstY-3, 6, 6);
			g2.draw(rec);
			g2.fill(rec);

			g2.draw(new Line2D.Double(firstX, firstY, secondX, secondY));

			if(i == graphData.getX().size()-2) {
				rec = new Rectangle2D.Double(secondX-3, secondY-3, 6, 6);
				
				g2.draw(rec);
				g2.fill(rec);
				
				g2.setColor(defColor);
				
				g2.draw(new Line2D.Double(secondX, height+topY-10, secondX, height+topY+10));
				g2.draw(new Line2D.Double(leftX-10, secondY, leftX+10, secondY));
				
				messageX = graphData.getX().get(i+1).toString();
				messageY = graphData.getY().get(i+1).toString();
				
				g2.drawString(messageX, (int) secondX-5, (int) ((int) height+topY+20));
				g2.drawString(messageY, (int) ((int) leftX-35), (int) secondY);
			}
			
			g2.setColor(defColor);
		}
	}

	public void setLogScale(boolean log) {
		logScale = log; 
	}
	public Dimension getPreferredSize() {
		return new Dimension(defWidth, defHeight); 
	}
}