package application;

public class Piece {

	private int[] coord = new int[2];
	private final int[] coordFinal = new int[2];
	private boolean vide;
	private int id;
	
	public Piece(int num, int x, int y, boolean est_vide) {
		this.id = num;
		this.coord[0] = x;
		this.coord[1] = y;
		this.coordFinal[0] = x;
		this.coordFinal[1] = y;
		this.vide = est_vide;
	}
	
	public void setCoord(int[] tabCoord) {
		this.coord[0] = tabCoord[0];
		this.coord[1] = tabCoord[1];
	}
	
	public int[] getCoord() {
		return this.coord;
	}
	
	public int[] getCoordFinal() {
		return this.coordFinal;
	}
	
	public int getId() {
		return this.id;
	}
	
	/**
	 * Determine si une piece est a son emplacement final
	 * @return vrai si elle est a son emplacement final, non sinon
	 */
	public boolean estEnPlace() {
		return this.coord[0] == this.coordFinal[0] && this.coord[1] == this.coordFinal[1];
	}
	
	@Override
	public String toString() {
		if(this.vide) {
			return "__";
		}
		if(this.id<10) {
			return "0"+String.valueOf(this.id);
		}
		return String.valueOf(this.id);
	}

}
