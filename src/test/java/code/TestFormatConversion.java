package code;

import static org.junit.Assert.*;
import org.junit.Test;
import code.Coordinate.Cardinal;

public class TestFormatConversion {
	
	private final double CONVERSION_TOLERANCE = 0.001; //Degrees
	
	@Test
	public void conversion1() throws OutOfRangeException{
		Coordinate a = new Coordinate(Cardinal.NORTH, 43, 35, 12.5,
				                      Cardinal.WEST, 75, 42, 54.35);
		double expectedLat = 43.5868;
		double calculatedLat = a.getLatitude();
		double latFault = Math.abs(expectedLat - calculatedLat);
		
		double expectedLon = -75.7151;
		double calculatedLon = a.getLongitude();
		double lonFault = Math.abs(expectedLon - calculatedLon);
		
		assertTrue(latFault < CONVERSION_TOLERANCE && 
				   lonFault < CONVERSION_TOLERANCE);
	}
	
	@Test
	public void conversion2() throws OutOfRangeException{
		Coordinate a = new Coordinate(Cardinal.NORTH, 0, 0, 0,
				                      Cardinal.WEST, 0, 0, 0);
		double expectedLat = 0;
		double calculatedLat = a.getLatitude();
		double latFault = Math.abs(expectedLat - calculatedLat);
		
		double expectedLon = 0;
		double calculatedLon = a.getLongitude();
		double lonFault = Math.abs(expectedLon - calculatedLon);
		
		assertTrue(latFault < CONVERSION_TOLERANCE && 
				   lonFault < CONVERSION_TOLERANCE);
	}
	
	@Test
	public void conversion3() throws OutOfRangeException{
		Coordinate a = new Coordinate(Cardinal.SOUTH, 43, 35, 12.5,
				                      Cardinal.EAST, 75, 42, 54.35);
		double expectedLat = -43.5868;
		double calculatedLat = a.getLatitude();
		double latFault = Math.abs(expectedLat - calculatedLat);
		
		double expectedLon = 75.7151;
		double calculatedLon = a.getLongitude();
		double lonFault = Math.abs(expectedLon - calculatedLon);
		
		assertTrue(latFault < CONVERSION_TOLERANCE && 
				   lonFault < CONVERSION_TOLERANCE);
	}
	
	@Test
	public void conversion4() throws OutOfRangeException{
		Coordinate a = new Coordinate(Cardinal.NORTH, 80, 10, 4.5,
				                      Cardinal.EAST, 2, 22, 2.2);
		double expectedLat = 80.1679;
		double calculatedLat = a.getLatitude();
		double latFault = Math.abs(expectedLat - calculatedLat);
		
		double expectedLon = 2.3673;
		double calculatedLon = a.getLongitude();
		double lonFault = Math.abs(expectedLon - calculatedLon);
		
		assertTrue(latFault < CONVERSION_TOLERANCE && 
				   lonFault < CONVERSION_TOLERANCE);
	}
	
	@Test(expected = OutOfRangeException.class)
	public void conversion5() throws OutOfRangeException{
		@SuppressWarnings("unused")
		Coordinate a = new Coordinate(Cardinal.NORTH, 200, 10, 4.5,
				                      Cardinal.EAST, 2, 22, 2.2);
	}
	
	@Test(expected = OutOfRangeException.class)
	public void conversion6() throws OutOfRangeException{
		@SuppressWarnings("unused")
		Coordinate a = new Coordinate(Cardinal.NORTH, 40, 61, 4.5,
				                      Cardinal.EAST, 2, 22, 2.2);
	}
	
	@Test(expected = OutOfRangeException.class)
	public void CoordConst() throws OutOfRangeException{
		@SuppressWarnings("unused")
		Coordinate a = new Coordinate(-81.215, 184.256);
	}
}