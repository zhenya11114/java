package graph;

public class TitleData {
	private String name = "";
	private String descr = "";
	private float minX = 0;
	private float minY = 0;
	private float maxX = 0;
	private float maxY = 0;
	private String meansX = "";
	private String meansY = "";
	
	TitleData(String name, 
			  String descr, 
			  float minX, 
			  float maxX, 
			  float minY, 
			  float maxY, 
			  String meansX, 
			  String meansY) {
		
		this.setName(name);
		this.setDescr(descr);
		this.setMinX(minX);
		this.setMaxX(maxX);
		this.setMinY(minY);
		this.setMaxY(maxY);
		this.setMeansX(meansX);
		this.setMeansY(meansY);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}	
	public void setMaxX(float maxX) {
		this.maxX = maxX;
	}
	public void setMaxY(float maxY) {
		this.maxY = maxY;
	}
	public void setMinX(float minX) {
		this.minX = minX;
	}
	public void setMinY(float minY) {
		this.minY = minY;
	}	
	public void setMeansX(String meansX) {
		this.meansX = meansX;
	}
	public void setMeansY(String meansY) {
		this.meansY = meansY;
	}

	public String getName() {
		return name;
	}
	public String getDescr() {
		return descr;
	}	
	public float getMaxX() {
		return maxX;
	}
	public float getMaxY() {
		return maxY;
	}
	public float getMinX() {
		return minX;
	}
	public float getMinY() {
		return minY;
	}
	public String getMeansX() {
		return meansX;
	}
	public String getMeansY() {
		return meansY;
	}
}
