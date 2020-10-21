package application;

public class Piece {

	int[] coord = new int[2];
	final int[] coordFinal = new int[2];
	boolean vide;
	private int id;
	
	public Piece(int num, int x, int y) {
		this.id = num;
		this.coord[0] =x;
		this.coord[1] =y;
		this.coordFinal[0] = x;
		this.coordFinal[1] = y;
		if (num == 15) {
			vide = true;
		} else {
			vide = false;
		}
		
	}
	
	public void setCoord(int[] tabCoord) {
		coord[0] = tabCoord[0];
		coord[1] = tabCoord[1];
	}
	
	public int[] getCoord() {
		return coord;
	}
	
	public boolean estEnPlace() {
		//System.out.println("id:"+id+"||"+coord[0] +","+ coord[1] +"|final "+ coordFinal[0] +", "+ coordFinal[1]);
		if (coord[0] == coordFinal[0] && coord[1] == coordFinal[1]) {
			return true;
			}
		return false;
	}
	
	@Override
	public String toString() {
		if(id==15) {
			return ("__");
		}
		if(id<10) {
			return "0"+String.valueOf(this.id);
		}
		return String.valueOf(this.id);
	}

	public int getID() {
		return(this.id);
	}
	

}
