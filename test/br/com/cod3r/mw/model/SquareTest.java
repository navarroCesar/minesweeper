package br.com.cod3r.mw.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.cod3r.mw.exception.ExplosionException;

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

	@Test
	void testDefaultFlagged() {
		assertFalse(square.isFlagged());
	}

	@Test
	void testChangeMarking() {
		square.changeMarking();
		assertTrue(square.isFlagged());
	}

	@Test
	void testDoubleCallToChangeMarking() {
		square.changeMarking();
		square.changeMarking();
		assertFalse(square.isFlagged());
	}

	@Test
	void testOpenSafeAndUnmarkedSquare() {
		assertTrue(square.openSquare());
	}

	@Test
	void testOpenSafeAndMarkedSquare() {
		square.changeMarking();
		assertFalse(square.openSquare());
	}

	@Test
	void testOpenMineAndMarkedSquare() {
		square.changeMarking();
		square.markAsMine();
		assertFalse(square.openSquare());
	}

	@Test
	void testOpenMineAndUnmarkedSquare() {
		square.markAsMine();

		assertThrows(ExplosionException.class, () -> {
			square.openSquare();
		});
	}

	@Test
	void testOpenSafeNeighborhood() {
		Square adjacentSquare11 = new Square(1, 1);

		Square adjacentSquare22 = new Square(2, 2);
		adjacentSquare22.addAdjacentSquare(adjacentSquare11);

		square.addAdjacentSquare(adjacentSquare22);
		square.openSquare();

		assertTrue(adjacentSquare22.isOpen() && adjacentSquare11.isOpen());

	}

	@Test
	void testOpenSafeNeighborhood2() {
		Square adjacentSquare11 = new Square(1, 1);
		Square adjacentSquare12 = new Square(1, 1);
		adjacentSquare12.markAsMine();

		Square adjacentSquare22 = new Square(2, 2);
		adjacentSquare22.addAdjacentSquare(adjacentSquare11);
		adjacentSquare22.addAdjacentSquare(adjacentSquare12);

		square.addAdjacentSquare(adjacentSquare22);
		square.openSquare();

		assertTrue(adjacentSquare22.isOpen() && !adjacentSquare11.isOpen());

	}
}
