package graph;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

public class GraphFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public GraphFrame(ArrayList<Float> X, ArrayList<Float> Y) {
		TitleData title = new TitleData("Graph", "", 0, 0, 0, 0, "n", "t");
						
		GraphComponent gc = new GraphComponent(new GraphData(title, X, Y), true, false, new Color(255, 0, 0));
		add(gc);
		pack();
	}
}