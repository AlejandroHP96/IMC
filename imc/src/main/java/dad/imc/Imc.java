package dad.imc;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Imc extends Application {

    private TextField pesoText,alturaText;
    private Label resultadoLabel,saludLabel;

    private DoubleProperty peso;
    private DoubleProperty altura;
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        pesoText = new TextField();

        alturaText = new TextField();

        resultadoLabel = new Label();

        VBox root = new VBox(5);
        root.setAlignment(Pos.CENTER);
        root.setFillWidth(false);
        root.getChildren().addAll(pesoText,alturaText,resultadoLabel);

        Scene scene = new Scene(root,320,200);

        primaryStage.setTitle("Calculador IMC");
        primaryStage.setScene(scene);
        primaryStage.show();

        //bindeos

        resultadoLabel.textProperty().bind(pesoText.textProperty().concat(" ").concat(alturaText.textProperty()));
        
        
    }
    public static void main(String[] args) {
        
        launch(args);
    }
}
