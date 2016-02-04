package code;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Online distance calculators return inconsistent results
 * making it hard to test the accuracy of the 
 * {@link Coordinate#distanceTo(Coordinate)} method.
 * This results in the loose tolerance of 100m.
 */
public class TestDistance {

	private final double DISTANCE_TOLERANCE = 100; //Meters
	
	@Test
	public void distance1() throws OutOfRangeException{
		Coordinate a = new Coordinate(42.593365, -76.109395);
		Coordinate b = new Coordinate(41.726878, -77.926130);
		
		double CalculatedDistanceAB = a.distanceTo(b);
		double CalculatedDistanceBA = b.distanceTo(a);
		double expectedDist = 178100; //meters
	    
		double faultAB = Math.abs(CalculatedDistanceAB - expectedDist);
	    double faultBA = Math.abs(CalculatedDistanceBA - expectedDist);
	    
	    assertTrue(faultAB < DISTANCE_TOLERANCE && faultBA < DISTANCE_TOLERANCE);
	}
	
	@Test
	public void distance2() throws OutOfRangeException{
		Coordinate a = new Coordinate(42, 4);
		Coordinate b = new Coordinate(-42, -80);
		
		double CalculatedDistanceAB = a.distanceTo(b);
		double CalculatedDistanceBA = b.distanceTo(a);
		double expectedDist = 12560000; //meters
		
		double faultAB = Math.abs(CalculatedDistanceAB - expectedDist);
		double faultBA = Math.abs(CalculatedDistanceBA - expectedDist);
		
		assertTrue(faultAB < DISTANCE_TOLERANCE && faultBA < DISTANCE_TOLERANCE);
	}
	
	@Test
	public void samePoint() throws OutOfRangeException{
		Coordinate a = new Coordinate(42.593365, -76.109395);
		Coordinate b = new Coordinate(42.593365, -76.109395);
		
		double CalculatedDistanceAB = a.distanceTo(b);
		double CalculatedDistanceBA = b.distanceTo(a);
		double expectedDist = 0; //meters
	    
		double faultAB = Math.abs(CalculatedDistanceAB - expectedDist);
	    double faultBA = Math.abs(CalculatedDistanceBA - expectedDist);
	    
	    assertTrue(faultAB < DISTANCE_TOLERANCE && faultBA < DISTANCE_TOLERANCE);
	}
}