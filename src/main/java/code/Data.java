package code;

/**
 * Used to store data.
 * */
public class Data {
	private DataPoint pointA;
	private DataPoint pointB;
	private Coordinate target;
	private Coordinate current;
	/**
	 * Creates and sets {@link Data#pointA}
	 * */	
	public void setPointA(double lat, double lon, double bear) throws OutOfRangeException{
		pointA = new DataPoint(lat, lon, bear);
	}
	/**
	 * Creates and set {@link Data#pointB}
	 * */
	public void setPointB(double lat, double lon, double bear) throws OutOfRangeException{
		pointB = new DataPoint(lat, lon, bear);
	}
	/**
	 * Creates and sets {@link Data#current}
	 * */
	public void setCurrent(double lat, double lon)throws OutOfRangeException{
		current = new Coordinate(lat, lon);
	}
	/**
	 * Calculates and sets {@link Data#target}
	 * */
	public void calcTarget() throws OutOfRangeException{
		if(pointA == null || pointB == null)
			throw new OutOfRangeException("Point A and Point B must be set before calculating the target.");
		target = DataPoint.triangulate(pointA, pointB);
	}
	/**@return {@link Data#target}*/
	public Coordinate getTarget(){
		return target;
	}
	/**@return {@link Data#current}*/
	public Coordinate getCurrent(){
		return current;
	}
}
