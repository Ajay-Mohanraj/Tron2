import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RealBackUpTRON extends Application {
    double p1Dx = 5;
    Pane mainPane = new Pane();
    BorderPane mainMenuPane = new BorderPane();

    Button startGame1 = new Button();
    Text menuText = new Text();

    Image green = new Image("Green.png", 150, 150, true, true);
    Image thanos = new Image("Thanos(OLD).png", 150, 150, true, true);

    ImageView p1 = new ImageView(green);
    ImageView p2 = new ImageView(thanos);

    boolean wdown = false;

    final int p1_up = 0;
    final int p1_left = 1;
    final int p1_down = 2;
    final int p1_right = 3;

    final int p2_up = 4;
    final int p2_left = 5;
    final int p2_down = 6;
    final int p2_right = 7;

    String[] keys = {"w", "a", "s", "d", "Up", "Left", "Down", "Right"};
    Boolean[] keyDown = {false, false, false, false, false, false, false, false};

    public void start(Stage ps) {

        startGame1.setText("Start");
        mainMenuPane.setBottom(startGame1);

        menuText.setText("Evanescent");
        mainMenuPane.setCenter(menuText);

        p1.setX(750);
        p1.setY(750);

        p2.setX(150);
        p2.setY(150);

        mainPane.getChildren().addAll(p1, p2);

        mainPane.setOnKeyPressed(e -> move(e));
        //pane.setOnKeyReleased(e -> released(e));


        new AnimationTimer() {
            @Override

            public void handle(long now) {

                if (keyDown[p1_up]) {
                    p1.setY(p1.getY()-5);
                }
                if (keyDown[p1_left]) {
                    p1.setX(p1.getX()-5);
                }
                if (keyDown[p1_down]) {
                    p1.setY(p1.getY()+5);
                }
                if (keyDown[p1_right]) {
                    //p1.setX(p1.getX()+5);
                    p1Dx = 5;
                }


                //}
//                if (wdown) {
//                    p1.setY(p1.getY()-5);
//                }
                p1.setX(p1.getX() + p1Dx);
            }

        }.start();


        Scene mainMenu = new Scene(mainMenuPane, 1000, 1000);
        Scene mainScene = new Scene(mainPane, 1000, 1000);

        ps.setScene(mainMenu);
        startGame1.setOnAction(e->{
           ps.setScene(mainScene);
        });
        ps.show();
        mainPane.requestFocus();

    }

    public void move(KeyEvent e) {
    	for (int i = 0; i < keys.length; i++) {
    		if (keys[i].equals(e.getText()) || keys[i].equals(e.getCode().getName())) {
				//System.out.println(keys[i]);
				//make value true
                keyDown[i] = true;

			}
		}
//		if (e.getText().equals("w")) {
//			wdown = true;
//		}
    }

    public void released(KeyEvent e) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equals(e.getText()) || keys[i].equals(e.getCode().getName())) {
                //System.out.println(keys[i]);
                // make value true
                keyDown[i] = false;

            }
        }
    }
}
