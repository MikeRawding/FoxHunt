package code;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestBearing {
	
	private final double BEARING_TOLERANCE = 0.001; //Degrees

	@Test
	public void bearing1() throws OutOfRangeException{
		Coordinate a = new Coordinate(42.593365, -76.109395);
		Coordinate b = new Coordinate(41.726878, -77.926130);
		
		double calculatedBearingAB = a.bearingTo(b);
		double calculatedBearingBA = b.bearingTo(a);
	    double expectedBearingAB = 237.854;
	    double expectedBearingBA = 56.6347;
	    
	    double faultAB = Math.abs(calculatedBearingAB - expectedBearingAB);
	    double faultBA = Math.abs(calculatedBearingBA - expectedBearingBA);
	    
	    assertTrue(faultAB < BEARING_TOLERANCE && faultBA < BEARING_TOLERANCE);
	}

	@Test
	public void samePoint() throws OutOfRangeException{
		Coordinate a = new Coordinate(42.593365, -76.109395);
		Coordinate b = new Coordinate(42.593365, -76.109395);
		
		double calculatedBearingAB = a.bearingTo(b);
		double calculatedBearingBA = b.bearingTo(a);
	    double expectedBearingAB = 360; //0 also valid
	    double expectedBearingBA = 360; //0 also valid
	    
	    double faultAB = Math.abs(calculatedBearingAB - expectedBearingAB);
	    double faultBA = Math.abs(calculatedBearingBA - expectedBearingBA);
	    
	    System.out.println(calculatedBearingBA);
	    
	    assertTrue(faultAB < BEARING_TOLERANCE && faultBA < BEARING_TOLERANCE);
	}
}