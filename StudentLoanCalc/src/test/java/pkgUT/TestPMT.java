package pkgUT;

import static org.junit.Assert.*;
import org.apache.poi.ss.formula.functions.*;
import org.junit.Test;

public class TestPMT {

	@Test
	public void test1() {
		double PMT;
		double r = 0.03 / 12;
		double n = 50 * 12;
		double p = 100000;
		double f = 0;
		boolean t = false;
		PMT = Math.abs(FinanceLib.pmt(r, n, p, f, t));
		
		double PMTExpected = 321.98;
		
		assertEquals(PMTExpected, PMT, 0.01);
		
		
		
	}

	@Test
	public void test2() {
		double PMT;
		double r = 0.06 / 12;
		double n = 25 * 12;
		double p = 500000;
		double f = 0;
		boolean t = false;
		PMT = Math.abs(FinanceLib.pmt(r, n, p, f, t));
		
		double PMTExpected = 3221.51;
		
		assertEquals(PMTExpected, PMT, 0.01);
		
		
		
	}
}

 

