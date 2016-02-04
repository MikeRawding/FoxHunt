package code;
/**
 * Extends {@link Coordinate} to include a bearing to target.
 * This enables triangulation to a {@link Coordinate} given
 * two instances of {@link DataPoint}.
 * <p>
 * Formulas used in this class are from Chris Veness at
 * <a href="http://www.movable-type.co.uk/scripts/latlong.html">
 * http://www.movable-type.co.uk/scripts/latlong.html</a>
 * @author Mike Rawding
 * @author MRawding@gmail.com
 * @see {@link Coordinate}
 */
public class DataPoint extends Coordinate{
	/**
	 * Compass bearing stored in degrees in range [0, 360]
	 */
	private double bearing;
	/**
	 * Constructs a DataPoint from pure decimal notation coordinates
	 * and a bearing. Use values less than 0 for south or west respectively.
	 * @param lat latitude with valid range [-90, 90]
	 * @param lon longitude with valid range [-180, 180]
	 * @param bearing compass bearing with valid range [0,360]
	 * @throws OutOfRangeException if a parameter is out of range
	 * @see Coordinate#Coordinate(double, double)
	 */
	public DataPoint(double lat, double lon, double bearing) throws OutOfRangeException{
		super(lat, lon);
		if(bearing < 0 || bearing > 360)
			throw new OutOfRangeException("Bearing must be between 0 and 360 degrees inclusive.");
		this.bearing = bearing;
	}
	/**
	 * Constructs a DataPoint from Degree, Minute, Second notation coordinates and a bearing.
	 * @param latCard latitude cardinal direction in {north, south} {@link Coordinate.Cardinal}
	 * @param latDeg latitude degrees with valid range [0, 90]
	 * @param latMin latitude minutes with valid range [0, 60]
	 * @param latSec latitude seconds with valid range [0, 60]
	 * @param lonCard longitude cardinal direction in {east, west} {@link Coordinate.Cardinal}
	 * @param lonDeg longitude degrees with valid range [0, 180]
	 * @param lonMin longitude minutes with valid range [0, 60]
	 * @param lonSec longitude seconds with valid range [0, 60]
	 * @param bearing compass bearing with valid range [0,360]
	 * @throws OutOfRangeException if a parameter is out of range
	 * @see Coordinate#Coordinate(Cardinal, int, int, double, Cardinal, int, int, double)
	 */
	public DataPoint(Cardinal latCard, int latDeg, int latMin, double latSec, 
			         Cardinal lonCard, int lonDeg, int lonMin, double lonSec, 
			         double bearing) throws OutOfRangeException {
		super(latCard, latDeg, latMin, latSec, lonCard, lonDeg, lonMin, lonSec);
		if(bearing < 0 || bearing > 360)
			throw new OutOfRangeException("Bearing must be between 0 and 360 degrees inclusive.");
		this.bearing = bearing;
	}
	/**
	 * Calculates the latitude and longitude of the intersection of two DataPoints.
	 * 
	 * @param pointA a {@link DataPoint}
	 * @param pointB a {@link DataPoint}
	 * @return a {@link Coordinate} of the intersection of pointA and pointB
	 * @throws OutOfRangeException if pointA and point B are the same location or if their
	 * bearings do not intersect.
	 */
	public static Coordinate triangulate(DataPoint pointA, DataPoint pointB) throws OutOfRangeException{
		//Formula from http://www.movable-type.co.uk/scripts/latlong.html
		if(pointA.equals(pointB)) throw new OutOfRangeException("Invalid input");
		
		double latA = Math.toRadians(pointA.getLatitude());
		double lonA = Math.toRadians(pointA.getLongitude());
		double bearAC = Math.toRadians(pointA.getBearing());
		double latB = Math.toRadians(pointB.getLatitude());
		double lonB = Math.toRadians(pointB.getLongitude());
		double bearBC = Math.toRadians(pointB.getBearing());
		double deltaLat = latB - latA;
		double deltaLon = lonB - lonA;
		
		double deltaAB = 2 * Math.asin( Math.sqrt( (Math.sin(deltaLat/2) *
			             Math.sin(deltaLat/2) + Math.cos(latA) *
			             Math.cos(latB) * Math.sin(deltaLon/2) *
			             Math.sin(deltaLon/2)) ) );
		
		double bearA = Math.acos( (Math.sin(latB) - Math.sin(latA) * Math.cos(deltaAB)) /
				                  (Math.sin(deltaAB) * Math.cos(latA)) );
		
		double bearB = Math.acos( (Math.sin(latA) - Math.sin(latB) * Math.cos(deltaAB)) /
                                  (Math.sin(deltaAB) * Math.cos(latB)) );
	
		double bearAB, bearBA;
		if(Math.sin(lonB - lonA) > 0){
			bearAB = bearA;
			bearBA = 2 * Math.PI - bearB;
		}else{
			bearAB = 2 * Math.PI - bearA;
			bearBA = bearB;
		}
		
		double alphaA = (bearAC - bearAB + Math.PI) % (2 * Math.PI) - Math.PI;
		double alphaB = (bearBA - bearBC + Math.PI) % (2 * Math.PI) - Math.PI;
		
		if( (Math.sin(alphaA) == 0 && Math.sin(alphaB) == 0) ||
		    (Math.sin(alphaA) * Math.sin(alphaB) < 0))
			throw new OutOfRangeException("Invalid input");
		
		double alphaC = Math.acos( -1 * Math.cos(alphaA) * Math.cos(alphaB) + 
				  				   Math.sin(alphaA) * Math.sin(alphaB) * Math.cos(deltaAB) );
		double deltaAC = Math.atan2( Math.sin(deltaAB) * Math.sin(alphaA) * Math.sin(alphaB),
									 Math.cos(alphaB) + Math.cos(alphaA) * Math.cos(alphaC) );
		double latC = Math.asin( Math.sin(latA) * Math.cos(deltaAC) + Math.cos(latA) * 
				                 Math.sin(deltaAC) * Math.cos(bearAC) );
		double deltaLonAC = Math.atan2( Math.sin(bearAC) * Math.sin(deltaAC) * Math.cos(latA), 
				                        Math.cos(deltaAC) - Math.sin(latA) * Math.sin(latC) );
		double lonC = lonA + deltaLonAC;
		
		double latCDeg = Math.toDegrees(latC);
		double lonCDeg = (lonC > 0) ? Math.toDegrees(lonC) : Math.toDegrees(lonC) + 360;
		
		return new Coordinate(latCDeg, lonCDeg); 
	}
	/**
	 * 
	 * @return {@link DataPoint#bearing}
	 */
	public double getBearing(){
		return bearing;
	}
}