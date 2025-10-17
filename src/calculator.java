import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.awt.*;


public class calculator extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Calculator");
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        TextField tf = new TextField();



        vbox.getStyleClass().add("vbox");
        vbox.setAlignment(Pos.CENTER);

        HBox hbox0 = new HBox(5);
        HBox hbox1 = new HBox(5);
        HBox hbox2 = new HBox(5);
        HBox hbox3 = new HBox(5);

        for (int i = 0; i < 10; i++) {
            Button button = new Button(Integer.toString(i));
            button.setPrefWidth(50);
            if (i < 3) {
                hbox0.getChildren().add(button);
            } else if (i < 6) {
                hbox1.getChildren().add(button);
            } else if (i < 9) {
                hbox2.getChildren().add(button);
            } else {
                hbox3.getChildren().add(button);
            }
            button.setOnAction(e -> {
                tf.appendText(button.getText());
            });
        }

        Button add = new Button("+");
        Button sub = new Button("-");
        Button mul = new Button("*");
        Button div = new Button("/");
        Button mod = new Button("%");
        Button clear = new Button("C");
        Button equal = new Button("=");
        Button[] btn= new Button[]{add,sub,mul,div,mod};
        for(Button b : btn) {
            b.setOnAction(e -> {
                tf.appendText(b.getText());
            });
        }
        clear.setOnAction(e -> {
            String y=tf.getText();
           y= y.substring(0,y.length()-1);
            tf.setText(y);
        });
        equal.setOnAction(e -> {
            String y=tf.getText();
            Calcu x=new Calcu();
            x.toPostfix(y);
            tf.setText(Integer.toString(x.evaluate()));


        });
tf.addEventFilter(KeyEvent.ANY, e -> e.consume());
        hbox0.getChildren().add(clear);
        hbox1.getChildren().add(add);
        hbox2.getChildren().add(sub);
        hbox3.getChildren().addAll(mul, div, mod,equal);

        HBox[] hbox={hbox0,hbox1,hbox2,hbox3};
        for(HBox h:hbox){
            h.getStyleClass().add("hbox");
        }
        tf.setStyle("-fx-background-color: rgb(159,58,58,0.32)");
        tf.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(tf,hbox0, hbox1, hbox2, hbox3);



        Scene scene = new Scene(vbox, 300, 300);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
