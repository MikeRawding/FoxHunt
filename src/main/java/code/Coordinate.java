package code;
/**
 * Geographical latitude and longitude coordinate stored in pure
 * decimal notation.  Pure decimal notation or Degree, Minute, Second
 * notation are excepted by constructors.  Use {@link Coordinate#Cardinal} enum 
 * type for Degree, Minute, Second notation.
 * <p>
 * Formulas used in this class are from Chris Veness at
 * <a href="http://www.movable-type.co.uk/scripts/latlong.html">
 * http://www.movable-type.co.uk/scripts/latlong.html</a>
 * @author Mike Rawding
 * @author MRawding@gmail.com
 */
public class Coordinate {
	/**
	 * Stored in pure decimal degrees.  Value less than 0
	 * indicates south.  Valid range [-90, 90].
	 */
	private double latitude;
	/**
	 * Stored in pure decimal degrees.  Value less than 0
	 * indicates west.  Valid range [-180, 180].
	 */
	private double longitude;
	/**
	 * Used for Degree, Minute, Second format constructor.<br>
	 * Latitude must be NORTH or SOUTH.<br>
	 * Longitude must be EAST or WEST.
	 */
	public static enum Cardinal{NORTH, SOUTH, EAST, WEST};
	/**
	 * Radius of the earth in meters
	 */
	private final static double EARTH_RADIUS = 6371000;
	/**
	 * Constructs a Coordinate from pure decimal notation.
	 * Use values less than 0 for south or west respectively.
	 * @param lat latitude with valid range [-90, 90]
	 * @param lon longitude with valid range [-180, 180]
	 * @throws OutOfRangeException if a parameter is out of range
	 */
	public Coordinate(double lat, double lon) throws OutOfRangeException{
		if(lat < -90 || lat > 90) 
			throw new OutOfRangeException("Latitude must be between -90 and 90 degrees inclusive.");
		if(lon < -180 || lon > 180)
			throw new OutOfRangeException("Longitude must be between -180 and 180 degrees inclusive.");
		latitude = lat;
		longitude = lon;
	}
	/**
	 * Constructs a Coordinate from Degree, Minute, Second notation.
	 * @param latCard latitude cardinal direction in {north, south} {@link Coordinate.Cardinal}
	 * @param latDeg latitude degrees with valid range [0, 90]
	 * @param latMin latitude minutes with valid range [0, 60]
	 * @param latSec latitude seconds with valid range [0, 60]
	 * @param lonCard longitude cardinal direction in {east, west} {@link Coordinate.Cardinal}
	 * @param lonDeg longitude degrees with valid range [0, 180]
	 * @param lonMin longitude minutes with valid range [0, 60]
	 * @param lonSec longitude seconds with valid range [0, 60]
	 * @throws OutOfRangeException if a parameter is out of range
	 */
	public Coordinate(Cardinal latCard, int latDeg, int latMin, double latSec,
					  Cardinal lonCard, int lonDeg, int lonMin, double lonSec)
	                  throws OutOfRangeException{
		if(latCard != Cardinal.NORTH && latCard != Cardinal.SOUTH)
			throw new OutOfRangeException("Latitude must be north or south.");
		if(lonCard != Cardinal.EAST && lonCard != Cardinal.WEST)
			throw new OutOfRangeException("Longitude must be east or west.");
		if(latDeg < 0 || latDeg > 90) 
			throw new OutOfRangeException("Latitude must be between 0 and 90 degrees inclusive.");
		if(lonDeg < 0 || lonDeg > 180)
			throw new OutOfRangeException("Longitude must be between -0 and 180 degrees inclusive");
		if(latMin < 0 || latMin > 60 ||
		   latSec < 0 || latSec > 60 ||
		   lonMin < 0 || lonMin > 60 ||
		   lonSec < 0 || lonSec > 60)
			throw new OutOfRangeException("Minutes and Seconds must be bewteen 0 and 60 inclusive.");
		
		latitude = latDeg + (latMin/60.0) + (latSec/3600);
		if(latCard == Cardinal.SOUTH) latitude *= -1;
		
		longitude = lonDeg + (lonMin/60.0) + (lonSec/3600);
		if(lonCard == Cardinal.WEST) longitude *= -1;
	}
	/**
	 * Calculates the distance to another {@link Coordinate} in meters.
	 * @param destination the {@link Coordinate} to calculate distance to
	 * @return distance to destination in meters
	 */
	public double distanceTo(Coordinate destination){
		//Formula from http://www.movable-type.co.uk/scripts/latlong.html
		double thisLat = Math.toRadians(latitude);
		double desLat = Math.toRadians(destination.getLatitude());
		double deltaLat = desLat - thisLat;
		double deltaLon = Math.toRadians(destination.getLongitude() - longitude);
		double temp1 = Math.sin(deltaLat/2) * Math.sin(deltaLat/2) + Math.cos(thisLat) *
				       Math.cos(desLat) * Math.sin(deltaLon/2) * Math.sin(deltaLon/2);
		double temp2 = 2 * Math.atan2(Math.sqrt(temp1), Math.sqrt(1-temp1));
		return EARTH_RADIUS * temp2;
	}
	/**
	 * Calculates the compass bearing  to another {@link Coordinate} in degrees.
	 * @param destination the {@link Coordinate} to calculate bearing to
	 * @return bearing to destination in degrees in range [0, 360]
	 */
	public double bearingTo(Coordinate  destination){
		//Formula from from http://www.movable-type.co.uk/scripts/latlong.html
		double thisLat = Math.toRadians(latitude);
		double desLat = Math.toRadians(destination.getLatitude());
		double thisLon = Math.toRadians(longitude);
		double desLon = Math.toRadians(destination.getLongitude());
		double y = Math.sin(desLon - thisLon) * Math.cos(desLat);
		double x = Math.cos(thisLat) * Math.sin(desLat) -
				   Math.sin(thisLat) * Math.cos(desLat) *
				   Math.cos(desLon - thisLon);
		double bearing = Math.toDegrees(Math.atan2(y, x));
		return (bearing > 0) ? bearing : bearing + 360;
	}
	/**
	 * @param c {@link Coordinate} to compare with
	 * @return true if latitude and longitude match c within 0.0001 degrees
	 */
	public boolean equals(Coordinate c){
		double tolerance = 0.0001;
		if(Math.abs(this.latitude - c.getLatitude()) < tolerance &&
		   Math.abs(this.longitude - c.getLongitude()) < tolerance)
			return true;
		return false;
	}
	/**
	 * @return {@link Coordinate#latitude}
	 */
	public double getLatitude(){
		return latitude;
	}
	/**
	 * @return {@link Coordinate#longitude}
	 */
	public double getLongitude(){
		return longitude;
	}
}