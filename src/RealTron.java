import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RealTron extends Application {
    Pane pane = new Pane();

    //Image yellowRight = new Image("YellowRight.gif", 150, 150, true, true);
    Image green = new Image("Green.png", 96, 96, true, true);
    Image thanos = new Image("Thanos.png", 96, 96, true, true);

    ImageView p1 = new ImageView(thanos);
    ImageView p2 = new ImageView(green);

    BorderPane end = new BorderPane();
    Text winner = new Text("");

    ArrayList<Rectangle> paths = new ArrayList<Rectangle>();

    int p1Dx = 0;
    int p1Dy = 0;
    int p2Dy = 0;
    int p2Dx = 0;

    String[] keys = {"w", "a", "s", "d", "Up", "Left", "Down", "Right"};
    Boolean[] keyDown = {false, false, false, false, false, false, false, false};

    public void start(Stage ps) {

        int up = 270;
        int left = 180;
        int down = 90;
        int right = 0;

        p1.setX(0);
        p1.setY(0);

        p2.setX(650);
        p2.setY(650);

        pane.getChildren().addAll(p1, p2);

        pane.setOnKeyPressed(e -> move(e));
        pane.setOnKeyReleased(e -> released(e));

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (int i = 0; i < keys.length; i++) {
                    if (keyDown[i]) {
                        switch (keys[i]) {
                            case "w":
                                p1Dx = 0;
                                p1Dy = -5;
                                p1.setRotate(up);
                                break;

                            case "a":
                                p1Dx = -5;
                                p1Dy = 0;
                                p1.setRotate(left);
                                break;

                            case "s":
                                p1Dx = 0;
                                p1Dy = 5;
                                p1.setRotate(down);
                                break;

                            case "d":
                                p1Dx = 5;
                                p1Dy = 0;
                                p1.setRotate(right);
                                break;

                            case "Up":
                                p2Dx = 0;
                                p2Dy = -5;
                                p2.setRotate(up);
                                break;

                            case "Left":
                                p2Dx = -5;
                                p2Dy = 0;
                                p2.setRotate(left);
                                break;

                            case "Down":
                                p2Dx = 0;
                                p2Dy = 5;
                                p2.setRotate(down);
                                break;

                            case "Right":
                                p2Dx = 5;
                                p2Dy = 0;
                                p2.setRotate(right);
                                break;

                            default:
                                break;
                        }
                        //System.out.println(keys[i]);
                        //keyDown[i] = false;
                    }
                }
                p1.setX(p1.getX()+p1Dx);
                p1.setY(p1.getY()+p1Dy);
                p2.setX(p2.getX()+p2Dx);
                p2.setY(p2.getY()+p2Dy);
                drawPath(p1, Color.INDIGO);
                drawPath(p2, Color.LIGHTGREEN);

                if ((p1.getRotate() == up && p1.getY() + 9 <= 0)
                        || (p1.getRotate() == left && p1.getX() + 9 <= 0)
                        || (p1.getRotate() == down && p1.getY() + 66 >= pane.getHeight())
                        || (p1.getRotate() == right && p1.getX() + 66 >= pane.getWidth())) {
                    pane.getChildren().remove(p1);
                }

                if ((p2.getRotate() == up && p2.getY() + 9 <= 0)
                        || (p2.getRotate() == left && p2.getX() + 9 <= 0)
                        || (p2.getRotate() == down && p2.getY() + 66 >= pane.getHeight())
                        || (p2.getRotate() == right && p2.getX() + 66 >= pane.getWidth())) {
                    pane.getChildren().remove(p2);
                }

                if (hits(p1, p2)) {
                    winner.setTextAlignment(TextAlignment.CENTER);
                    if (p1.getRotate() == 0 && p2.getRotate() == 180 ||
                        p1.getRotate() == 180 && p2.getRotate() == 0 ||
                        p1.getRotate() == 90 && p2.getRotate() == 270 ||
                        p1.getRotate() == 270 && p2.getRotate() == 90) {
                        winner.setText("YOU BOTH LOSE!");
                    }
                    else if (p1.getRotate() == 0 && p2.getRotate() == 90 && p1.getX() + 9 < p2.getX() + 27 && p2.getX() + 66 < p1.getX() + 84 ||
                            p1.getRotate() == 0 && p2.getRotate() == 270 && p1.getX() + 9 < p2.getX() + 27 && p2.getX() + 66 < p1.getX() + 84 ||
                            p1.getRotate() == 180 && p2.getRotate() == 90 && p1.getX() + 9 < p2.getX() + 27 && p2.getX() + 66 < p1.getX() + 84 ||
                            p1.getRotate() == 180 && p2.getRotate() == 270 && p1.getX() + 9 < p2.getX() + 27 && p2.getX() + 66 < p1.getX() + 84) {
                        winner.setText("ThanosTron WINS!!!\nHALF YOUR RAM IS GONE!!!\nCLICK HERE TO DOWNLOAD MORE RAM");
                    }
                    else if (p2.getRotate() == 0 && p1.getRotate() == 90 && p2.getX() + 9< p1.getX() + 27 && p1.getX() + 66 < p2.getX() + 84 ||
                            p2.getRotate() == 0 && p1.getRotate() == 270 && p2.getX() + 9 < p1.getX() + 27 && p1.getX() + 66 < p2.getX() + 84 ||
                            p2.getRotate() == 180 && p1.getRotate() == 90 && p2.getX() + 9 < p1.getX() + 27 && p1.getX() + 66 < p2.getX() + 84 ||
                            p2.getRotate() == 180 && p1.getRotate() == 270 && p2.getX() + 9 < p1.getX() + 27 && p1.getX() + 66 < p2.getX() + 84) {
                        winner.setText("YOU HAVE SUCCESSFULLY SAVED YOUR RAM");
                    }
                    winner.xProperty().bind(end.widthProperty().divide(2));
                    winner.yProperty().bind(end.heightProperty().divide(2));

                    end.setCenter(winner);

                    Scene endGame = new Scene(end, 1000, 1000);
                    ps.setScene(endGame);
                }
            }
        }.start();

        Scene scene = new Scene(pane, 1000, 1000);
        ps.setScene(scene);
        ps.show();
        pane.requestFocus();
    }

    public void move(KeyEvent e) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equals(e.getText()) || keys[i].equals(e.getCode().getName())) {
                keyDown[i] = true;
            }
        }
    }

    public void released(KeyEvent e) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equals(e.getText()) || keys[i].equals(e.getCode().getName())) {
                keyDown[i] = false;
            }
        }
    }

    public Rectangle2D getBoundary(ImageView player, Image p) {
        if (player.getRotate() == 0 || player.getRotate() == 180) {
            return new Rectangle2D(player.getX() + 9, player.getY() + 30, p.getRequestedWidth() - 18, p.getRequestedHeight() - 51);
        }

        else if (player.getRotate() == 90 || player.getRotate() == 270) {
            return new Rectangle2D(player.getX() + 30, player.getY() + 9, p.getRequestedWidth() - 51, p.getRequestedHeight() - 18);

        }
        return new Rectangle2D(0, 0, 1, 1);
    }

    public Rectangle2D getBoundary(Rectangle r) {
        return new Rectangle2D(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    public void drawPath(ImageView player, Color color) {
        Rectangle path = new Rectangle();

        if (player.getRotate() == 0) {
            path.setX(player.getX() + 6);
            path.setY(player.getY() + 27);
            path.setWidth(5);
            path.setHeight(42);
        }

        else if (player.getRotate() == 180) {
            path.setX(player.getX() + 87);
            path.setY(player.getY() + 27);
            path.setWidth(5);
            path.setHeight(42);
        }

        else if (player.getRotate() == 90) {
            path.setX(player.getX() + 27);
            path.setY(player.getY() + 6);
            path.setWidth(42);
            path.setHeight(5);
        }

        else if (player.getRotate() == 270) {
            path.setX(player.getX() + 27);
            path.setY(player.getY() + 87);
            path.setWidth(42);
            path.setHeight(5);
        }

        paths.add(path);
        path.setFill(color);
        path.setStroke(color);
        pane.getChildren().add(path);
    }

    public boolean hits(ImageView p1, ImageView p2) {
        return getBoundary(p1, p1.getImage()).intersects(getBoundary(p2, p2.getImage()));
    }
    public boolean hits(ImageView player, Rectangle r) {
        return getBoundary(player, player.getImage()).intersects(getBoundary(r));
    }
}
//