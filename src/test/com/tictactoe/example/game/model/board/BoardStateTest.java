package com.tictactoe.example.game.model.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BoardStateTest {
    private static final int BOARD_LENGTH = 3;

    @Mock
    private Board board;

    private BoardState boardState;

    @BeforeEach
    void setUp() {
        when(board.getBoardLength()).thenReturn(BOARD_LENGTH);

        boardState = new BoardState();
    }

    @ParameterizedTest(name = "{index}. Game board - {0}. Expected Result = {1}")
    @MethodSource("provideGameBoardToExpectedBoardStateResult")
    void getCurrentBoardStateResultShouldReturnPlayer1WonWhenXValueHorizontallyFulfilled(Cell[][] gameBoard,
                                                                                         BoardStateResult expectedResult) {
        when(board.getGameBoard()).thenReturn(gameBoard);

        BoardStateResult actualResult = boardState.getCurrentBoardStateResult(board);

        assertEquals(actualResult, expectedResult);
    }

    private static Stream<Arguments> provideGameBoardToExpectedBoardStateResult() {
        Cell[][] player1WonFirstHorizontalLine = new Cell[][] {
                {Cell.X, Cell.X, Cell.X,},
                {Cell.O, Cell.X, Cell.O,},
                {Cell.X, Cell.O, Cell.O,},
        };
        Cell[][] player1WonSecondHorizontalLine = new Cell[][] {
                {Cell.O, Cell.X, Cell.O,},
                {Cell.X, Cell.X, Cell.X,},
                {Cell.X, Cell.O, Cell.O,},
        };
        Cell[][] player1WonThirdHorizontalLine = new Cell[][] {
                {Cell.O, Cell.X, Cell.O,},
                {Cell.X, Cell.O, Cell.O,},
                {Cell.X, Cell.X, Cell.X,},
        };

        return Stream.of(
                Arguments.of(player1WonFirstHorizontalLine, BoardStateResult.PLAYER_1_WON),
                Arguments.of(player1WonSecondHorizontalLine, BoardStateResult.PLAYER_1_WON),
                Arguments.of(player1WonThirdHorizontalLine, BoardStateResult.PLAYER_1_WON)
        );
    }
}