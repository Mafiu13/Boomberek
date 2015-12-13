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

public class Boomek extends Application {

    private GameMenu gameMenu;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane root = new Pane();
        root.setPrefSize(800, 600);

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

            menu0.setTranslateX(100);
            menu0.setTranslateY(200);

            menu1.setTranslateX(100);
            menu1.setTranslateY(200);

            menu2.setTranslateX(100);
            menu2.setTranslateY(200);

            final int offset = 400;

            menu1.setTranslateX(offset);

            MenuButton btnStart = new MenuButton("START");
            btnStart.setOnMouseClicked(event -> {

                primaryStage.hide();
                // new Thread() {
                //    public void run() {

                Bomberman bomberman = new Bomberman(2);
                bomberman.game();
                //  }
                //    }.start();
                primaryStage.show();
                //System.exit(0);
                //root.setVisible(false);

            });

            MenuButton btnOptions = new MenuButton("PLAY");
            btnOptions.setOnMouseClicked(event -> {
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

            MenuButton btnExit = new MenuButton("EXIT");
            btnExit.setOnMouseClicked(event -> {
                System.exit(0);
            });

            MenuButton btnBack = new MenuButton("BACK");
            btnBack.setOnMouseClicked(event -> {
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

                boolean fl10 = false;

                primaryStage.hide();

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

//                    Thread t2 = new Thread() {
//                        public void run() {
//                            int y = 1;
//                            int x = 2;
//                            boolean fl = true;
//
//                            try {
//
//                                while (fl) {
//
//                                    cl.ReceiveMessageS(y, x);
//                                }
//
//                            } catch (Exception e) {
//                            }
//                        }
//                    };
//                    t2.start();
//
//                    Thread r = new Thread() {
//                        public void run() {
//                            try {
//                                cl.SendMessage(1, 2);
//                            } catch (Exception e) {
//                            }
//                        }
//
//                    };
//                    r.start();
            });

            MenuButton btnJoinG = new MenuButton("JOIN GAME");
            btnJoinG.setOnMouseClicked(event -> {

                boolean fl11 = false;
                primaryStage.hide();

                Client cl = new Client();
                try {
                    fl11 = cl.CreateClient();

                } catch (IOException e) {

                }

                if (fl11) {

//                    
                    Bomberman bomberman = new Bomberman(2, cl);
                    bomberman.game();
                } else {
                    primaryStage.show();
                }

//                getChildren().add(menu2);
//
//                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
//                tt.setToX(menu1.getTranslateX() - offset);
//
//                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu2);
//                tt1.setToX(menu1.getTranslateX());
//
//                tt.play();
//                tt1.play();
//
//                tt.setOnFinished(evt -> {
//                    getChildren().remove(menu1);
//                });
            });

            MenuButton btnConnect = new MenuButton("Connecting");
            btnConnect.setOnMouseClicked(event -> {

//                getChildren().add(menu1);
//
//                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu2);
//                tt.setToX(menu2.getTranslateX() + offset);
//
//                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
//                tt1.setToX(menu2.getTranslateX());
//
//                tt.play();
//                tt1.play();
//
//                tt.setOnFinished(evt -> {
//                    getChildren().remove(menu2);
//                });
            });
            MenuButton btnCreate = new MenuButton("Creating");
            btnCreate.setOnMouseClicked(event -> {

//                getChildren().add(menu1);
//
//                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu2);
//                tt.setToX(menu2.getTranslateX() + offset);
//
//                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
//                tt1.setToX(menu2.getTranslateX());
//
//                tt.play();
//                tt1.play();
//
//                tt.setOnFinished(evt -> {
//                    getChildren().remove(menu2);
//                });
            });

            menu0.getChildren().addAll(btnStart, btnOptions, btnExit);

            menu1.getChildren().addAll(btnBack, btnCreateS, btnJoinG);
            menu2.getChildren().addAll(btnConnect, btnCreate);

            Rectangle bg = new Rectangle(800, 600);
            bg.setFill(Color.GREY);
            bg.setOpacity(0.4);

            getChildren().addAll(bg, menu0);
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
