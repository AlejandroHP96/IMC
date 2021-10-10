package dad.imc;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class Imc extends Application {

    private TextField pesoText,alturaText;
    private Label resultadoLabel,saludLabel;

    private SimpleDoubleProperty peso;
    private SimpleDoubleProperty altura;
    private SimpleDoubleProperty resultado;
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        pesoText = new TextField("Peso:");

        alturaText = new TextField("Altura:");

        saludLabel = new Label();
        resultadoLabel = new Label();

        VBox root = new VBox(5);
        root.setAlignment(Pos.CENTER);
        root.setFillWidth(false);
        root.getChildren().addAll(pesoText,alturaText,resultadoLabel,saludLabel);

        Scene scene = new Scene(root,320,200);

        primaryStage.setTitle("Calculador IMC");
        primaryStage.setScene(scene);
        primaryStage.show();

        //bindeos

        peso = new SimpleDoubleProperty();
        Bindings.bindBidirectional(pesoText.textProperty(),peso, new NumberStringConverter());

        altura = new SimpleDoubleProperty();
        Bindings.bindBidirectional(alturaText.textProperty(), altura,new NumberStringConverter());

        DoubleBinding operaciones = altura.divide(100);
        operaciones = peso.divide(operaciones.multiply(operaciones));

        resultado = new SimpleDoubleProperty();
        resultado.bind(operaciones);

        resultadoLabel.textProperty().bind(Bindings.concat("IMC: ").concat(resultado.asString("%.0f")));

        resultado.addListener((o,ov,nv) -> {
            double valorIMC = nv.doubleValue();
            if (valorIMC < 18.5) {
                saludLabel.setText("Bajo peso");
            } else if (valorIMC >= 18.5 && valorIMC < 25) {
                saludLabel.setText("Saludable");
            } else if (valorIMC >= 25 && valorIMC < 30) {
                saludLabel.setText("Sobrepeso");
            } else {
                saludLabel.setText("Obeso");
                
            }{
                
            }
        });
        
    }
    public static void main(String[] args) {
        
        launch(args);
    }
}
