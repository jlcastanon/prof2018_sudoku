package es.upm.grise.profundizacion2018.sudokuverifier;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class SudokuVerifierTest {

	String OKSudoku = 	"963174258"
			+ 			"178325649"
			+ 			"254689731"
			+ 			"821437596"
			+ 			"496852317"
			+ 			"735961824"
			+ 			"589713462"
			+ 			"317246985"
			+ 			"642598173";

	String FAILSudokuRule1 = "963174258"
			+ 			"178325649"
			+ 			"254609731"
			+ 			"821437596"
			+ 			"496852317"
			+ 			"735961824"
			+ 			"589713462"
			+ 			"317246985"
			+ 			"642598173";

	String FAILSudokuRule2 = "9963174258"
			+ 			"178325649"
			+ 			"254689731"
			+ 			"821437596"
			+ 			"496852317"
			+ 			"735961824"
			+ 			"589713462"
			+ 			"317246985"
			+ 			"64259817";

	String FAILSudokuRule3 = "174417471"
			+ "285528582"
			+ "396639693"
			+ "417174714"
			+ "528285825"
			+ "639396936"
			+ "741741147"
			+ "852852258"
			+ "963963369";

	String FAILSudokuRule4 = "123456789"
			+ "789123456"
			+ "456789123"
			+ "789456123"
			+ "123789456"
			+ "456123789"
			+ "456789123"
			+ "789123456"
			+ "123456789";

	String FAILSudokuNot81Numbers = "963174258"
			+ 			"178325649"
			+ 			"254689731"
			+ 			"821437596"
			+ 			"496852317"
			+ 			"735961824"
			+ 			"589713462"
			+ 			"317246985"
			+ 			"6425981733";

	SudokuVerifier toTest;

	@Before
	public void setUp(){
		toTest = new SudokuVerifier();
	}

	@Test
	public void OK() throws Not81NumbersExceptions {
		assertEquals(0, toTest.verify(OKSudoku));
	}

	@Test
	public void FAILRule1() throws Not81NumbersExceptions {
		assertEquals(-1, toTest.verify(FAILSudokuRule1));
	}

	@Test
	public void FAILRule2() throws Not81NumbersExceptions {
		assertEquals(-2, toTest.verify(FAILSudokuRule2));
	}

	@Test
	public void FAILRule3() throws Not81NumbersExceptions {
		assertEquals(-3, toTest.verify(FAILSudokuRule3));
	}

	@Test
	public void FAILRule4() throws Not81NumbersExceptions {
		assertEquals(-4, toTest.verify(FAILSudokuRule4));
	}

	@Test(expected = Not81NumbersExceptions.class)
	public void FAILNot81Numbers() throws Not81NumbersExceptions {
		toTest.verify(FAILSudokuNot81Numbers);
	}
}
