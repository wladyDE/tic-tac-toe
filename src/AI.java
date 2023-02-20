// TODO: Rewrite Furz Code!
public class AI {

    public void makeTurn(Cell[][] gameField){
        if (gameField[1][1] != Cell.EMPTY) {
            Cell[][] temp = setField(gameField);
            checkField(gameField, Cell.O);
            if (compareFields(gameField, temp)){
                Cell[][] temp2 = setField(gameField);
                checkField(gameField, Cell.X);
                if (compareFields(gameField, temp2)){
                    Cell[][] temp3 = setField(gameField);
                    renameMe(gameField);
                    if (compareFields(temp3, gameField)){
                        for (int j = 0; j < 3; j++) {
                            for (int i = 0; i < 3; i++) {
                               if (gameField[i][j] == Cell.EMPTY){
                                   gameField[i][j] = Cell.O;
                                   return;
                               }
                            }
                        }
                    }
                }
            }
        } else {
            gameField[1][1] = Cell.O;
        }
    }

    private void renameMe(Cell[][] gameField){
        if (gameField[0][0] == Cell.EMPTY){
            gameField[0][0] = Cell.O;
            return;
        }
        if (gameField[0][2] == Cell.EMPTY){
            gameField[0][2] = Cell.O;
            return;
        }
        if (gameField[2][2] == Cell.EMPTY){
            gameField[2][2] = Cell.O;
            return;
        }
        if (gameField[2][0] == Cell.EMPTY){
            gameField[2][0] = Cell.O;
        }
    }

    private boolean compareFields(Cell[][] firstField, Cell[][] secondField){
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (firstField[i][j] != secondField[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private Cell[][] setField(Cell[][] field){
        Cell[][] result = new Cell[3][3];
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                result[i][j] = field[i][j];
            }
        }
        return result;
    }

    private void checkField(Cell[][] gameField, Cell value){
        if (gameField[0][2] == value && gameField[0][1] == value && gameField[0][0] == Cell.EMPTY){
            gameField[0][0] = Cell.O;
            return;
        }
        if (gameField[0][0] == value && gameField[0][2] == value && gameField[0][1] == Cell.EMPTY){
            gameField[0][1] = Cell.O;
            return;
        }
        if (gameField[0][0] == value && gameField[0][1] == value && gameField[0][2] == Cell.EMPTY){
            gameField[0][2] = Cell.O;
            return;
        }
        if (gameField[1][2] == value && gameField[1][1] == value && gameField[1][0] == Cell.EMPTY){
            gameField[1][0] = Cell.O;
            return;
        }
        if (gameField[1][0] == value && gameField[1][2] == value && gameField[1][1] == Cell.EMPTY){
            gameField[1][1] = Cell.O;
            return;
        }
        if (gameField[1][0] == value && gameField[1][1] == value && gameField[1][2] == Cell.EMPTY){
            gameField[1][2] = Cell.O;
            return;
        }
        if (gameField[2][2] == value && gameField[2][1] == value && gameField[2][0] == Cell.EMPTY){
            gameField[2][0] = Cell.O;
            return;
        }
        if (gameField[2][0] == value && gameField[2][2] == value && gameField[2][1] == Cell.EMPTY){
            gameField[2][1] = Cell.O;
            return;
        }
        if (gameField[2][0] == value && gameField[2][1] == value && gameField[2][2] == Cell.EMPTY){
            gameField[2][2] = Cell.O;
            return;
        }

        if (gameField[0][0] == value && gameField[1][0] == value && gameField[2][0] == Cell.EMPTY){
            gameField[2][0] = Cell.O;
            return;
        }
        if (gameField[2][0] == value && gameField[1][0] == value && gameField[0][0] == Cell.EMPTY){
            gameField[0][0] = Cell.O;
            return;
        }
        if (gameField[2][0] == value && gameField[0][0] == value && gameField[1][0] == Cell.EMPTY){
            gameField[1][0] = Cell.O;
            return;
        }
        if (gameField[0][1] == value && gameField[1][1] == value && gameField[2][1] == Cell.EMPTY){
            gameField[2][1] = Cell.O;
            return;
        }
        if (gameField[2][1] == value && gameField[1][1] == value && gameField[0][1] == Cell.EMPTY){
            gameField[0][1] = Cell.O;
            return;
        }
        if (gameField[2][1] == value && gameField[0][1] == value && gameField[1][1] == Cell.EMPTY){
            gameField[1][1] = Cell.O;
            return;
        }
        if (gameField[0][2] == value && gameField[1][2] == value && gameField[2][2] == Cell.EMPTY){
            gameField[2][2] = Cell.O;
            return;
        }
        if (gameField[2][2] == value && gameField[1][2] == value && gameField[0][2] == Cell.EMPTY){
            gameField[0][2] = Cell.O;
            return;
        }
        if (gameField[2][2] == value && gameField[0][2] == value && gameField[1][2] == Cell.EMPTY){
            gameField[1][2] = Cell.O;
            return;
        }

        if(gameField[0][0] == value && gameField[1][1] == value && gameField[2][2] == Cell.EMPTY){
            gameField[2][2] = Cell.O;
            return;
         }
        if(gameField[2][2] == value && gameField[1][1] == value && gameField[0][0] == Cell.EMPTY){
            gameField[0][0] = Cell.O;
            return;
        }
        if(gameField[0][0] == value && gameField[2][2] == value && gameField[1][1] == Cell.EMPTY){
            gameField[1][1] = Cell.O;
            return;
        }

        if (gameField[2][0] == value && gameField[1][1] == value && gameField[0][2] == Cell.EMPTY){
            gameField[0][2] = Cell.O;
            return;
        }
        if (gameField[0][2] == value && gameField[1][1] == value && gameField[2][0] == Cell.EMPTY){
            gameField[2][0] = Cell.O;
            return;
        }
        if (gameField[2][0] == value && gameField[0][2] == value && gameField[1][1] == Cell.EMPTY){
            gameField[1][1] = Cell.O;
        }
    }


}
