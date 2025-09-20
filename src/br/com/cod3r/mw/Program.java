package br.com.cod3r.mw;

import br.com.cod3r.mw.model.Board;

public class Program {

	public static void main(String[] args) {

		Board board = new Board(6, 6, 6);

		board.changeMarking(4, 4);
		board.changeMarking(4, 5);
		board.open(3, 3);
		
		System.out.println(board);

	}

}
