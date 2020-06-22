import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Controller{

    private static int metkaIndexX = -1, metkaIndexY = -1; //Позиции выбранные мышью(индексы строки и столбца в GridPane)

    private static int[][] sudokuArr; //Содержит полностью решенное судоку
    private static int[][] usersSudokuArr = new int[9][9]; //Массив 9 на 9, который будет менять пользователь, и будет сравниваться с массивом sudokuArr
    private static Label[][] labels = new Label[9][9]; //Нужно для того, чтобы менять текст в ячейках GridPane в которых уже есть Label
    private static boolean[][] startingNumbers = new boolean[9][9];

    private static int difficult = 30; //Количество показуемых цифр на поле(сложность)
    private static int defaultValueOfCounterOfHelps = 3; //Стандартное значение количества подсказок
    private static int counterOfHelps = defaultValueOfCounterOfHelps; //Количество оставшихся подсказок

    @FXML
    GridPane gridPane;
    @FXML
    MenuItem menuItemHelps;

    @FXML
    public void handleOnMouseClicked(MouseEvent event){
        if(!startingNumbers[(int)event.getY()/70][(int)event.getX()/70]){
            metkaIndexX = (int)event.getX()/70;
            metkaIndexY = (int)event.getY()/70;
            setBackgroundWhiteColor();
            labels[metkaIndexY][metkaIndexX].setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    public void initialize(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                labels[i][j] = new Label();
                labels[i][j].setMinHeight(68);
                labels[i][j].setMinWidth(68);
                labels[i][j].setFont(new Font("Arial", 30));
                labels[i][j].setAlignment(Pos.CENTER);
                GridPane.setHalignment(labels[i][j], HPos.CENTER);
                gridPane.add(labels[i][j], j, i);
            }
        }
        startNewGame();
    }

    public void handleOnMouseClickedButton1(){ setLabelOnGridPane("1"); }
    public void handleOnMouseClickedButton2(){ setLabelOnGridPane("2"); }
    public void handleOnMouseClickedButton3(){ setLabelOnGridPane("3"); }
    public void handleOnMouseClickedButton4(){ setLabelOnGridPane("4"); }
    public void handleOnMouseClickedButton5(){ setLabelOnGridPane("5"); }
    public void handleOnMouseClickedButton6(){ setLabelOnGridPane("6"); }
    public void handleOnMouseClickedButton7(){ setLabelOnGridPane("7"); }
    public void handleOnMouseClickedButton8(){ setLabelOnGridPane("8"); }
    public void handleOnMouseClickedButton9(){ setLabelOnGridPane("9"); }

    public void handleOnMouseClickedItemEasy(){ difficult = 40;}
    public void handleOnMouseClickedItemMedium(){ difficult = 30;}
    public void handleOnMouseClickedItemHard(){ difficult = 20;}

    public void setLabelOnGridPane(String text){
        if(metkaIndexX != -1 && metkaIndexY != -1){
            labels[metkaIndexY][metkaIndexX].setText(text);
            usersSudokuArr[metkaIndexY][metkaIndexX] = Integer.parseInt(text);
            metkaIndexX = -1;
            metkaIndexY = -1;
            setBackgroundWhiteColor();
            if(equalArrays()){
                System.out.println("Ты выиграл!"); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!ДОРАБОТАТЬ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }
//            printingSudokuArr();
//            printingUsersSudokuArr();
        }
    }

    private void setBackgroundWhiteColor(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                labels[i][j].setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
    }

    private boolean equalArrays(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(sudokuArr[i][j] != usersSudokuArr[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public void clearLabel(){
        if(metkaIndexX != -1 && metkaIndexY != -1){
            setBackgroundWhiteColor();
            usersSudokuArr[metkaIndexY][metkaIndexX] = 0;
            labels[metkaIndexY][metkaIndexX].setText("");

            metkaIndexX = -1;
            metkaIndexY = -1;
        }
    }

    private void clearUsersSudokuArr(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                usersSudokuArr[i][j] = 0;
            }
        }
    }

    private void clearAllLabels(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                labels[i][j].setText("");
                labels[i][j].setFont(new Font("Arial", 30));
            }
        }
    }

    private void clearStartingNumbersArr(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                startingNumbers[i][j] = false;
            }
        }
    }


    private void showNumbersOnStartingGame(){
        for (int i = 0; i < difficult; i++) {
            int randNumberX = (int)(Math.random()*9);
            int randNumberY = (int)(Math.random()*9);

            if(startingNumbers[randNumberY][randNumberX]){
                i--;
                continue;
            }

            usersSudokuArr[randNumberY][randNumberX] = sudokuArr[randNumberY][randNumberX];
            labels[randNumberY][randNumberX].setText(String.valueOf(sudokuArr[randNumberY][randNumberX]));
            labels[randNumberY][randNumberX].setFont(new Font("Arial Bold", 35));
            startingNumbers[randNumberY][randNumberX] = true;
        }

    }

    @FXML
    private void startNewGame(){
        metkaIndexX = -1;
        metkaIndexY = -1;
        setBackgroundWhiteColor();
        counterOfHelps = defaultValueOfCounterOfHelps;

        SudokuGeneration sudokuGeneration = new SudokuGeneration();
        sudokuArr = sudokuGeneration.getSudokuArr();


        clearUsersSudokuArr();
        clearAllLabels();
        clearStartingNumbersArr();

        menuItemHelps.setText("Подсказка(Осталось: "+ defaultValueOfCounterOfHelps +")");

        showNumbersOnStartingGame();
    }

    @FXML
    private void restartGame(){
        metkaIndexX = -1;
        metkaIndexY = -1;
        setBackgroundWhiteColor();
        counterOfHelps = defaultValueOfCounterOfHelps;
        menuItemHelps.setText("Подсказка(Осталось: "+ defaultValueOfCounterOfHelps +")");

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(!startingNumbers[i][j]){
                    usersSudokuArr[i][j] = 0;
                    labels[i][j].setText("");
                }
            }
        }
    }

    @FXML
    private void showHelp(){
        if(counterOfHelps != 0){
            while (true) {
                int randNumberX = (int) (Math.random() * 9);
                int randNumberY = (int) (Math.random() * 9);
                if (labels[randNumberY][randNumberX].getText().equals("")){
                    setBackgroundWhiteColor();
                    metkaIndexY = -1;
                    metkaIndexX = -1;
                    labels[randNumberY][randNumberX].setText(String.valueOf(sudokuArr[randNumberY][randNumberX]));
                    usersSudokuArr[randNumberY][randNumberX] = sudokuArr[randNumberY][randNumberX];
                    counterOfHelps--;
                    break;
                }
            }
            menuItemHelps.setText("Подсказка(Осталось: "+ counterOfHelps +")");
        }
    }

    private void printingSudokuArr(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudokuArr[i][j] +"  ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    private void printingUsersSudokuArr(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(usersSudokuArr[i][j] +"  ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }
}
