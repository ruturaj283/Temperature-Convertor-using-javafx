package com.internshala.javafxapp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class MyMain extends Application {
	public static void main(String[] args){

		launch(args);
	}

	@Override
	public void init() throws Exception {
		System.out.println("init");
		super.init();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("start");

		FXMLLoader loader = new FXMLLoader(getClass().getResource("app_layout.fxml"));
		VBox rootNode = loader.load();
		MenuBar menuBar=createMenu();
		rootNode.getChildren().add(0,menuBar);

		Scene scene = new Scene(rootNode);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Temperature Converter Tool");
		primaryStage.show();
	}
	private MenuBar createMenu(){

		Menu fileMenu = new Menu("File");
		MenuItem newMenuItem=new MenuItem("New");
		newMenuItem.setOnAction(event -> {
			System.out.println("new menue item clicked");
		});

		SeparatorMenuItem separatorMenuItem=new SeparatorMenuItem();

		MenuItem quitMenuItem=new MenuItem("Quit");
		quitMenuItem.setOnAction(event -> {
			Platform.exit();
			System.exit(0);
		});

		fileMenu.getItems().addAll(newMenuItem,separatorMenuItem,quitMenuItem);

		Menu helpMenu = new Menu("Help");
		MenuItem aboutApp=new MenuItem("About");
		aboutApp.setOnAction(event -> aboutApp());//(lambda) for below code
//		aboutApp.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				aboutApp();
//			}
//		});
		helpMenu.getItems().addAll(aboutApp);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu,helpMenu);
		return menuBar;
	}

	private void aboutApp() {
		Alert alertDilog = new Alert(Alert.AlertType.INFORMATION);
		alertDilog.setTitle("first app");
		alertDilog.setHeaderText("learning javafx");
		alertDilog.setContentText("main content or info");

		ButtonType yesBtn=new ButtonType("Yes");
		ButtonType noBtn = new ButtonType("No");
		alertDilog.getButtonTypes().setAll(yesBtn,noBtn);

		Optional<ButtonType> clickedBtn=alertDilog.showAndWait();

		if (clickedBtn.isPresent() && clickedBtn.get()==yesBtn){
			System.out.println("yes button clicked");
		}
		else{
			System.out.println("no button clicked");
		}
	}

	@Override
	public void stop() throws Exception {
		System.out.println("stop");
		super.stop();
	}
}
