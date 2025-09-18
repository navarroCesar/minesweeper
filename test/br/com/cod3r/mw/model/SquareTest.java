package br.com.cod3r.mw.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SquareTest {

	private Square square;

	@BeforeEach
	void instantiateSquare() {
		square = new Square(3, 3);
	}

	@Test
	void testLeftAdjacentSquare() {
		Square adjacent = new Square(3, 2);
		boolean result = square.addAdjacentSquare(adjacent);
		assertTrue(result);
	}

	@Test
	void testRightAdjacentSquare() {
		Square adjacent = new Square(3, 4);
		boolean result = square.addAdjacentSquare(adjacent);
		assertTrue(result);
	}

	@Test
	void testAdjacentSquareAbove() {
		Square adjacent = new Square(2, 3);
		boolean result = square.addAdjacentSquare(adjacent);
		assertTrue(result);
	}
	@Test
	void testAdjacentSquareUnderneath() {
		Square adjacent = new Square(4, 3);
		boolean result = square.addAdjacentSquare(adjacent);
		assertTrue(result);
	}
	@Test
	void testDiagonalAdjacentSquare() {
		Square adjacent = new Square(2, 2);
		boolean result = square.addAdjacentSquare(adjacent);
		assertTrue(result);
	}
	@Test
	void testNoAdjacentSquare() {
		Square adjacent = new Square(1, 1);
		boolean result = square.addAdjacentSquare(adjacent);
		assertFalse(result);
	}
}
