package code;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTriangulation {
	
	private final double TRIANGULATE_TOLERANCE = 0.001; //Degrees

	@Test
	public void triangulate1() throws OutOfRangeException{
		DataPoint a = new DataPoint(51, 0.25, 108);
		DataPoint b = new DataPoint(49, 2.5, 32);
		Coordinate intersection = DataPoint.triangulate(a, b);
		
		double expectedLat = 50.23;
		double expectedLon = 3.7075;
		double calculatedLat = intersection.getLatitude();
		double calculatedLon = intersection.getLongitude();
		
		double faultLat = Math.abs(expectedLat - calculatedLat);
		double faultLon = Math.abs(expectedLon - calculatedLon);
		
		assertTrue(faultLat < TRIANGULATE_TOLERANCE && faultLon < TRIANGULATE_TOLERANCE);
	}
	
	@Test
	public void triangulate2() throws OutOfRangeException{
		DataPoint a = new DataPoint(43.074553, -75.759592, 62.583);
		DataPoint b = new DataPoint(43.134420, -75.228998, 302.2);
		Coordinate intersection = DataPoint.triangulate(a, b);
		
		double expectedLat = 43.211699;
		double expectedLon = -75.397193;
		double calculatedLat = intersection.getLatitude();
		double calculatedLon = intersection.getLongitude();
		
		double faultLat = Math.abs(expectedLat - calculatedLat);
		double faultLon = Math.abs(expectedLon - calculatedLon);
		
		assertTrue(faultLat < TRIANGULATE_TOLERANCE && faultLon < TRIANGULATE_TOLERANCE);
	}
	
	@Test (expected = OutOfRangeException.class)//same point
	public void samePoint() throws OutOfRangeException{
		DataPoint a = new DataPoint(51, 0.25, 108);
		DataPoint b = new DataPoint(51, 0.25, 32);
		
		@SuppressWarnings("unused")
		Coordinate intersection = DataPoint.triangulate(a, b);
	}
	
	@Test (expected = OutOfRangeException.class)//no intersection
	public void noIntersetction() throws OutOfRangeException{
		DataPoint a = new DataPoint(51, 0.25, 270);
		DataPoint b = new DataPoint(49, 2.5, 90);
		
		@SuppressWarnings("unused")
		Coordinate intersection = DataPoint.triangulate(a, b);
	}
}