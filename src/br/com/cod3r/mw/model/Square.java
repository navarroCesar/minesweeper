package br.com.cod3r.mw.model;

import java.util.ArrayList;
import java.util.List;

import br.com.cod3r.mw.exception.ExplosionException;

public class Square {

	private final int row;
	private final int column;

	private boolean open = false;
	private boolean mine = false;
	private boolean flagged = false;

	private List<Square> adjacentSquares = new ArrayList<>();

	Square(int row, int column) {
		this.row = row;
		this.column = column;
	}

	boolean addAdjacentSquare(Square adjacent) {
		boolean differentRow = row != adjacent.row;
		boolean differentColumn = column != adjacent.column;
		boolean diagonal = differentRow && differentColumn;

		int deltaRow = Math.abs(row - adjacent.row);
		int deltaColumn = Math.abs(column - adjacent.column);
		int finalDelta = deltaRow + deltaColumn;

		if (finalDelta == 1 && !diagonal) {
			adjacentSquares.add(adjacent);
			return true;
		} else if (finalDelta == 2 && diagonal) {
			adjacentSquares.add(adjacent);
			return true;
		} else {
			return false;
		}

	}

	void changeMarking() {
		if (!open) {
			flagged = !flagged;
		}
	}

	boolean openSquare() {

		if (!open && !flagged) {
			open = true;
			if (mine) {
				throw new ExplosionException();
			}
			if (safeNeighborhood()) {
				adjacentSquares.forEach(a -> a.openSquare());
			}

			return true;
		}

		return false;
	}

	boolean safeNeighborhood() {
		return adjacentSquares.stream().noneMatch(a -> a.mine);
	}

	void markAsMine() {
		if (!mine) {
			mine = true;
		}
	}

	public boolean isOpen() {
		return open;
	}

	public boolean isAMine() {
		return mine;
	}

	public boolean isFlagged() {
		return flagged;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

}
