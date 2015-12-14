package bomberman;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;

public class Boomek extends Application {

    private GameMenu gameMenu;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane root = new Pane();
        root.setPrefSize(800, 600);
        root.setMaxSize(800, 600);

        InputStream is = Files.newInputStream(Paths.get("./src/boom.jpg"));
        Image img = new Image(is);
        is.close();

        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(800);
        imgView.setFitHeight(600);

        gameMenu = new GameMenu(primaryStage);
        gameMenu.setVisible(false);

        root.getChildren().addAll(imgView, gameMenu);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                if (!gameMenu.isVisible()) {
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
                    ft.setFromValue(0);
                    ft.setToValue(1);

                    gameMenu.setVisible(true);
                    ft.play();
                } else {
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.setOnFinished(evt -> gameMenu.setVisible(false));
                    ft.play();
                }
            }
        });

        primaryStage.setOnCloseRequest(ev -> {
            System.exit(0);
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class GameMenu extends Parent {

        public GameMenu(Stage primaryStage) throws IOException {

            VBox menu0 = new VBox(10);
            VBox menu1 = new VBox(10);
            VBox menu2 = new VBox(10);
            VBox menu3 = new VBox(10);

            Text te = new Text(260, 70, "Boomberman");
            te.setFont(Font.font("Arial", 60));
            te.setFill(Color.WHITE);

            Text con = new Text(180,400, "Could not connect to server");
           con.setFont(Font.font("Arial",40));
            con.setFill(Color.WHITE);
//            
//            Text cre = new Text(220,400, "Creating Server");
//           cre.setFont(Font.font("Arial",60));
//            cre.setFill(Color.WHITE);
            Text inf0 = new Text(70, 120, "Control your Bomberman and try to kill enemy Bomberman");
            Text inf1 = new Text(70, 160, "Use arrows to move Bomberman and SPACE to set the bomb");
            Text inf2 = new Text(70, 190, "Range of bomb's explosion will increase with time");
            Text inf3 = new Text(70, 230, "Good Luck!");

            inf0.setFont(inf0.getFont().font(25));
            inf0.setFill(Color.BLACK);
            inf1.setFont(inf1.getFont().font(25));
            inf1.setFill(Color.BLACK);
            inf2.setFont(inf2.getFont().font(25));
            inf2.setFill(Color.BLACK);
            inf3.setFont(inf3.getFont().font(40));
            inf3.setFill(Color.BLACK);

            menu0.setTranslateX(100);
            menu0.setTranslateY(200);

            menu1.setTranslateX(100);
            menu1.setTranslateY(200);

            menu2.setTranslateX(100);
            menu2.setTranslateY(200);

            menu3.setTranslateX(100);
            menu3.setTranslateY(200);

            final int offset = 400;

            menu1.setTranslateX(offset);

            MenuButton btnStart = new MenuButton("START");
            btnStart.setOnMouseClicked(event -> {

                primaryStage.hide();

                Bomberman bomberman = new Bomberman(2);
                bomberman.game();

                primaryStage.show();

            });

            MenuButton btnPlay = new MenuButton("PLAY");
            btnPlay.setOnMouseClicked(event -> {
                getChildren().add(menu1);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
                tt.setToX(menu0.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
                tt1.setToX(menu0.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu0);
                });
            });

            MenuButton btnHelp = new MenuButton("HOW TO PLAY");
            btnHelp.setOnMouseClicked(event -> {
                getChildren().add(menu3);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
                tt.setToX(menu0.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu3);
                tt1.setToX(menu0.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu0);
                });
            });

            MenuButton btnBack2 = new MenuButton("BACK");
            btnBack2.setOnMouseClicked(event -> {
                getChildren().add(menu0);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu3);
                tt.setToX(menu3.getTranslateX() + offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
                tt1.setToX(menu3.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu3);
                });
            });

            MenuButton btnExit = new MenuButton("EXIT");
            btnExit.setOnMouseClicked(event -> {
                System.exit(0);
            });

            MenuButton btnBack = new MenuButton("BACK");
            btnBack.setOnMouseClicked(event -> {
                getChildren().remove(con);
                getChildren().add(menu0);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt.setToX(menu1.getTranslateX() + offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
                tt1.setToX(menu1.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu1);
                });
            });

            MenuButton btnCreateS = new MenuButton("CREATE SERVER");
            btnCreateS.setOnMouseClicked(event -> {
                getChildren().remove(con);
                primaryStage.hide();
                boolean fl10 = false;

                Server ser = new Server();
                try {
                    ser.CreateServer();
                } catch (IOException e) {

                }

                try {
                    fl10 = ser.Connected();
                } catch (Exception e) {
                }

                if (fl10 == true) {

                    Bomberman bomberman = new Bomberman(1, ser);
                    bomberman.game();

                    try {
                        ser.ServerClose();
                    } catch (IOException e) {

                    }

                    primaryStage.show();

                } else {
                    primaryStage.show();
                }

            });

            MenuButton btnJoinG = new MenuButton("JOIN GAME");
            btnJoinG.setOnMouseClicked(event -> {
                boolean conect = false;
                getChildren().remove(con);

                boolean fl11 = false;

                Client cl = new Client();
                try {
                    fl11 = cl.CreateClient();

                } catch (IOException e) {

                }

                if (fl11) {
                    primaryStage.hide();
//                    
                    Bomberman bomberman = new Bomberman(2, cl);
                    bomberman.game();

                    try {
                        cl.ClientClose();
                    } catch (IOException e) {

                    }

                    primaryStage.show();

                } else {
                    primaryStage.show();
                    getChildren().add(con);
                    
                    
                }

            });

            menu0.getChildren().addAll(btnStart, btnPlay, btnHelp, btnExit);

            menu1.getChildren().addAll(btnBack, btnCreateS, btnJoinG);
            menu3.getChildren().addAll(btnBack2, inf0, inf1, inf2, inf3);

            Rectangle bg = new Rectangle(800, 600);
            bg.setFill(Color.GREY);
            bg.setOpacity(0.4);

            getChildren().addAll(bg, menu0, te);
        }
    }

    private static class MenuButton extends StackPane {

        private Text text;

        public MenuButton(String name) {
            text = new Text(name);
            text.setFont(text.getFont().font(20));
            text.setFill(Color.WHITE);

            Rectangle bg = new Rectangle(250, 30);
            bg.setOpacity(0.6);
            bg.setFill(Color.BLACK);
            bg.setEffect(new GaussianBlur(3.5));

            setAlignment(Pos.CENTER_LEFT);
            setRotate(-0.5);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event -> {
                bg.setTranslateX(10);
                text.setTranslateX(10);
                bg.setFill(Color.WHITE);
                text.setFill(Color.BLACK);
            });

            setOnMouseExited(event -> {
                bg.setTranslateX(0);
                text.setTranslateX(0);
                bg.setFill(Color.BLACK);
                text.setFill(Color.WHITE);
            });

            DropShadow drop = new DropShadow(50, Color.WHITE);
            drop.setInput(new Glow());

            setOnMousePressed(event -> setEffect(drop));
            setOnMouseReleased(event -> setEffect(null));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
