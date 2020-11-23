package graph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GraphData {	
	private TitleData title;
	private Map<String, ArrayList> values;

	GraphData() {
		this.title = null;
		this.values = null;
	}
	GraphData(TitleData title, ArrayList<Float> X, ArrayList<Float> Y) {
		if(!(title.getName().equals("") || title.getName()==null) && !X.isEmpty() && !Y.isEmpty() && X.size() == Y.size()) {
			this.title = title;
			this.values = new HashMap<>();
			values.put("X", X);
			values.put("Y", Y);
			genTitle();
		}
	}
	
	public TitleData getTitle() {
		return this.title;
	}
	public ArrayList<Float> getX() {
		return values.get("X");
	}
	public ArrayList<Float> getY() {
		return values.get("Y");
	}
	public Map<String, ArrayList> getValues() {
		return values;
	}
	
	private void genTitle() {
		ArrayList<Float> X = values.get("X");
		ArrayList<Float> Y = values.get("Y");
		
		float min = X.get(0);
		float max = X.get(X.size()-1);
		
		for(float x : X) {
			if(x < min)
				min = x;
			if(x > max)
				max = x;
		}
		
		title.setMaxX(max);
		title.setMinX(min);

		min = Y.get(0);
		max = Y.get(Y.size()-1);
		
		for(float y : Y) {
			if(y < min) min = y;
			if(y > max) max = y;
		}		

		title.setMaxY(max);
		title.setMinY(min);		
	}
}
