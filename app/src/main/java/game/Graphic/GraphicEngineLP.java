package game.graphic;

// import javafx.scene.Parent;
// import java.util.ArrayList;
// import java.util.List;
// import javafx.event.EventHandler;
// import game.GameObject;
// import game.Physic.Coordinate;
// import game.Physic.Physical;
// import game.Physic.PhysicalEngine;
// import javafx.animation.AnimationTimer;
// import javafx.application.Application;
// import javafx.event.ActionEvent;
// import javafx.geometry.Insets;
// import javafx.scene.Group;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.TextArea;
// import javafx.scene.image.ImageView;
// import javafx.scene.input.MouseEvent;
// import javafx.stage.Stage;

public class GraphicEngineLP {
//     private final int Windowwidth = 700;
//     private final int Windowheight = 700;
//     private double speed = 10;
//     static PhysicalEngine pe;
    

//     static ImageView i_shrek = new ImageView("shrek.jpeg");
//     static GameObject shrek = new GameObject(
//         new Physical(100, 100, 50, 50),
//         new Displayable(i_shrek, 100, 100, 50, 50)
//         );
    
//     static ImageView i_mur_sud = new ImageView("shrek.jpeg");
//     static GameObject mur_sud = new GameObject(
//         new Physical(20, 600, 700, 50),
//         new Displayable(i_mur_sud, 20, 600, 50, 700)
//         );

//     static ImageView i_mur_nord = new ImageView("shrek.jpeg");
//     static GameObject mur_nord = new GameObject(
//         new Physical(20, 0, 700, 50),
//         new Displayable(i_mur_nord, 20, 0, 50, 700)
//         );

//     static ImageView i_mur_est = new ImageView("shrek.jpeg");
//     static GameObject mur_est = new GameObject(
//         new Physical(650, 20, 50, 700),
//         new Displayable(i_mur_est, 650, 20, 700, 50)
//         );

//     static ImageView i_mur_ouest = new ImageView("shrek.jpeg");
//     static GameObject mur_ouest = new GameObject(
//         new Physical(0, 20, 50, 700),
//         new Displayable(i_mur_ouest, 0, 20, 700, 50)
//         );




//     public static void main(String[] args) {
//         launch(args);
//     }

//     @Override
//     public void start(Stage stage) throws Exception {
        
//         var root = new Group();
//         root.setOnMouseClicked(new EventHandler<MouseEvent>() {
//             @Override
//             public void handle(MouseEvent event) {
//                 //shrek.getComponent(Physical.class).setDirection(new Coordinate(event.getSceneX(), event.getSceneY()));
//                 shrek.getComponent(Physical.class).setDestinationCoord(new Coordinate(event.getSceneX(), event.getSceneY()));
//                 shrek.getComponent(Physical.class).setUseDestination(true);
//                 shrek.getComponent(Physical.class).setSpeed(speed);
//             }
//         });


//         TextArea textArea = new TextArea();
//         textArea.setText("200");
//         textArea.setPrefSize(30, 30);


//         var animation = new AnimationTimer() {
//             private long lastUpdate = 0 ;
//             @Override
//             public void handle(long now) {
//                 if (now - lastUpdate >= 1000) {
//                     lastUpdate = now ;
//                     do_it();
//                 }
//             }
//             private void do_it(){
//                 pe.computeAll();
//                 i_shrek.relocate(shrek.getComponent(Physical.class).getX(), shrek.getComponent(Physical.class).getY());
//             }
//         };
        
        
//         Button btn_up = new Button();
//         btn_up.setPadding(new Insets(5, 5, 5, 5));
//         btn_up.setText("up");
//         btn_up.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent event) {shrek.getComponent(Physical.class).setDirection(0);shrek.getComponent(Physical.class).setSpeed(speed);shrek.getComponent(Physical.class).setUseDestination(false); }});
//         btn_up.setLayoutX(250);

//         Button btn_down = new Button();
//         btn_down.setPadding(new Insets(5, 5, 5, 5));
//         btn_down.setText("down");
//         btn_down.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent event) {shrek.getComponent(Physical.class).setDirection(180);shrek.getComponent(Physical.class).setSpeed(speed);shrek.getComponent(Physical.class).setUseDestination(false);}});
//         btn_down.setLayoutX(50);

//         Button btn_droite = new Button();
//         btn_droite.setPadding(new Insets(5, 5, 5, 5));
//         btn_droite.setText("droite");
//         btn_droite.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent event) {shrek.getComponent(Physical.class).setDirection(270);shrek.getComponent(Physical.class).setSpeed(speed);shrek.getComponent(Physical.class).setUseDestination(false);}});
//         btn_droite.setLayoutX(100);

//         Button btn_gauche = new Button();
//         btn_gauche.setPadding(new Insets(5, 5, 5, 5));
//         btn_gauche.setText("gauche");
//         btn_gauche.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent event) {shrek.getComponent(Physical.class).setDirection(90);shrek.getComponent(Physical.class).setSpeed(speed);shrek.getComponent(Physical.class).setUseDestination(false);}});
//         btn_gauche.setLayoutX(150);


//         Button btn_update = new Button();
//         btn_update.setPadding(new Insets(5, 5, 5, 5));
//         btn_update.setText("start");
//         btn_update.setOnAction(new EventHandler<ActionEvent>() {
//             @Override
//             public void handle(ActionEvent event) {
//                 animation.start();
//                 pe = new PhysicalEngine();
//                 pe.addPhysicalObject(mur_sud.getComponent(Physical.class));
//                 pe.addPhysicalObject(mur_nord.getComponent(Physical.class));
//                 pe.addPhysicalObject(mur_est.getComponent(Physical.class));
//                 pe.addPhysicalObject(mur_ouest.getComponent(Physical.class));
//                 pe.addPhysicalObject(shrek.getComponent(Physical.class));
//             }
//         });
//         btn_update.setLayoutX(200);
        
        
        
//         ImageView fond = new ImageView("shrek.jpeg");
//         fond.setX(0);
//         fond.setY(0);
//         fond.setFitHeight(Windowheight);
//         fond.setFitWidth(Windowwidth);

//         root.getChildren().add(fond);
//         root.getChildren().add(shrek.getComponent(Displayable.class).getAsset());
//         root.getChildren().add(mur_sud.getComponent(Displayable.class).getAsset());
//         root.getChildren().add(mur_nord.getComponent(Displayable.class).getAsset());
//         root.getChildren().add(mur_est.getComponent(Displayable.class).getAsset());
//         root.getChildren().add(mur_ouest.getComponent(Displayable.class).getAsset());
//         root.getChildren().add(btn_up);
//         root.getChildren().add(btn_down);
//         root.getChildren().add(btn_droite);
//         root.getChildren().add(btn_gauche);
//         root.getChildren().add(btn_update);
//         root.getChildren().add(textArea);
//         stage.setScene(new Scene(root, Windowwidth, Windowheight));
//         stage.show();
//     }







//     public void setVisibility(Displayable displayable, boolean value) {
//         displayable.getAsset().setVisible(value);
//     }

}
