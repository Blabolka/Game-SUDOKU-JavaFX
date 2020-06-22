import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Main extends Application {

    private Controller controller = new Controller();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("mainAreaSudoku.fxml"));

        Scene scene = new Scene(root, 620,715);

        stage.setScene(scene);
        stage.setTitle("Sudoku");
        stage.setResizable(false);
        stage.show();

        stage.getScene().setOnKeyPressed(key-> {
            if(key.getCode() == KeyCode.BACK_SPACE || key.getCode() == KeyCode.DELETE){
                controller.clearLabel();
            }else if(key.getCode() == KeyCode.DIGIT1 || key.getCode() == KeyCode.NUMPAD1){
                controller.setLabelOnGridPane("1");
            }else if(key.getCode() == KeyCode.DIGIT2 || key.getCode() == KeyCode.NUMPAD2){
                controller.setLabelOnGridPane("2");
            }else if(key.getCode() == KeyCode.DIGIT3 || key.getCode() == KeyCode.NUMPAD3){
                controller.setLabelOnGridPane("3");
            }else if(key.getCode() == KeyCode.DIGIT4 || key.getCode() == KeyCode.NUMPAD4){
                controller.setLabelOnGridPane("4");
            }else if(key.getCode() == KeyCode.DIGIT5 || key.getCode() == KeyCode.NUMPAD5){
                controller.setLabelOnGridPane("5");
            }else if(key.getCode() == KeyCode.DIGIT6 || key.getCode() == KeyCode.NUMPAD6){
                controller.setLabelOnGridPane("6");
            }else if(key.getCode() == KeyCode.DIGIT7 || key.getCode() == KeyCode.NUMPAD7){
                controller.setLabelOnGridPane("7");
            }else if(key.getCode() == KeyCode.DIGIT8 || key.getCode() == KeyCode.NUMPAD8){
                controller.setLabelOnGridPane("8");
            }else if(key.getCode() == KeyCode.DIGIT9 || key.getCode() == KeyCode.NUMPAD9){
                controller.setLabelOnGridPane("9");
            }
        });
    }
}
