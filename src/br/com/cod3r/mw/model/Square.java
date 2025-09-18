package br.com.cod3r.mw.model;

import java.util.ArrayList;
import java.util.List;

public class Square {

	private final int row;
	private final int column;

	private boolean isOpen = false;
	private boolean isAMine = false;
	private boolean isFlagged = false;

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

	public boolean isOpen() {
		return isOpen;
	}

	public boolean isAMine() {
		return isAMine;
	}

	public boolean isFlagged() {
		return isFlagged;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

}
