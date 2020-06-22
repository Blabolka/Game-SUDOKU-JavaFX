public class SudokuGeneration {

    private int[][] sudokuArr = new int[9][9];

    public SudokuGeneration(){
        generate();
    }

    public int[][] getSudokuArr(){
        return sudokuArr;
    }

    private void generate(){

        int counterOfInvalidNumbers = 0;
        int counterOfInvalidScramble = 0;
        int randNumber;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                randNumber = (int)((Math.random()*9)+1);
                if((checkInvalidNumberInRow(randNumber,i)) || (checkInvalidNumberInColumn(randNumber, j)) || (checkInvalidNumberInBlock(randNumber,i,j))){
                    counterOfInvalidNumbers++;
                    if(counterOfInvalidNumbers >= 20){
                        rowCancellation(i);
                        counterOfInvalidNumbers = 0;

                        counterOfInvalidScramble++;
                        if(counterOfInvalidScramble == 20){
                            sudokuCancellation();
                            counterOfInvalidScramble = 0;

                            i=0;
                            j=-1;
                            continue;
                        }
                        j=-1;
                        continue;
                    }
                    j--;
                    continue;
                }
                sudokuArr[i][j] = randNumber;
                counterOfInvalidNumbers = 0;
            }
        }
    }

    private boolean checkInvalidNumberInRow(int randNumber, int i){
        for (int k = 0; k < 9; k++) {
            if(randNumber == sudokuArr[i][k]){
                return true;
            }
        }
        return false;
    }

    private boolean checkInvalidNumberInColumn(int randNumber, int j){
        for (int k = 0; k < 9; k++) {
            if(randNumber == sudokuArr[k][j]){
                return true;
            }
        }
        return false;
    }

    private boolean checkInvalidNumberInBlock(int randNumber, int i, int j){
        if(i>=0 && i<=2){
            if(j>=0 && j<=2){

                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if(randNumber == sudokuArr[k][l]){
                            return true;
                        }
                    }
                }

            }else if(j>=3 && j<=5){

                for (int k = 0; k < 3; k++) {
                    for (int l = 3; l < 6; l++) {
                        if(randNumber == sudokuArr[k][l]){
                            return true;
                        }
                    }
                }

            }else if(j>=6 && j<=8){

                for (int k = 0; k < 3; k++) {
                    for (int l = 6; l < 9; l++) {
                        if(randNumber == sudokuArr[k][l]){
                            return true;
                        }
                    }
                }

            }

        }else if(i>=3 && i<=5){
            if(j>=0 && j<=2){

                for (int k = 3; k < 6; k++) {
                    for (int l = 0; l < 3; l++) {
                        if(randNumber == sudokuArr[k][l]){
                            return true;
                        }
                    }
                }

            }else if(j>=3 && j<=5){

                for (int k = 3; k < 6; k++) {
                    for (int l = 3; l < 6; l++) {
                        if(randNumber == sudokuArr[k][l]){
                            return true;
                        }
                    }
                }

            }else if(j>=6 && j<=8){

                for (int k = 3; k < 6; k++) {
                    for (int l = 6; l < 9; l++) {
                        if(randNumber == sudokuArr[k][l]){
                            return true;
                        }
                    }
                }

            }

        }else if(i>=6 && i<=8){
            if(j>=0 && j<=2){

                for (int k = 6; k < 9; k++) {
                    for (int l = 0; l < 3; l++) {
                        if(randNumber == sudokuArr[k][l]){
                            return true;
                        }
                    }
                }

            }else if(j>=3 && j<=5){

                for (int k = 6; k < 9; k++) {
                    for (int l = 3; l < 6; l++) {
                        if(randNumber == sudokuArr[k][l]){
                            return true;
                        }
                    }
                }

            }else if(j>=6 && j<=8){

                for (int k = 6; k < 9; k++) {
                    for (int l = 6; l < 9; l++) {
                        if(randNumber == sudokuArr[k][l]){
                            return true;
                        }
                    }
                }

            }
        }

        return false;
    }

    private void rowCancellation(int i){
        for (int j = 0; j < 9; j++) {
            sudokuArr[i][j] = 0;
        }
    }

    private void sudokuCancellation(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuArr[i][j] = 0;
            }
        }
    }
}
