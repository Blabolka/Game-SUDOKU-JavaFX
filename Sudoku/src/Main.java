import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

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
    }
}
