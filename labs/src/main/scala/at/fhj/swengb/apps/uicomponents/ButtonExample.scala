package at.fhj.swengb.apps.uicomponents

import java.nio.file.{Files, Paths}
import javafx.application.Application
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.image.{Image, ImageView}
import javafx.scene.layout.{StackPane, VBox}
import javafx.stage.Stage

import at.fhj.utils.CanLog

/**
  * Shows a simple example to fill a Button with some strings.
  *
  * If an element is selected via mouseclick, write something to the console.
  *
  * see https://docs.oracle.com/javafx/2/ui_controls/list-view.htm
  */
object ButtonExample {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[ButtonExampleApp], args: _*)
  }

}

class ButtonExampleApp extends Application with CanLog {

  val buttonEventHandler = new EventHandler[ActionEvent] {
    override def handle(event: ActionEvent): Unit = {
      println(event.getSource.asInstanceOf[Button].getText + " was clicked")
    }
  }

  override def start(primaryStage: Stage): Unit = {
    primaryStage.setTitle("A Button example")
    val btn1 = new Button("a button")
    btn1.setOnAction(buttonEventHandler)
    val iv = new ImageView()
    val is = Files.newInputStream(Paths.get("src/main/resources/at/fhj/swengb/apps/uicomponents/happybutton.png"))
    iv.setImage(new Image(is))
    val btn2 = new Button("button 2", iv)
    btn2.setOnAction(buttonEventHandler)
    val stackPane = new VBox()
    stackPane.getChildren.addAll(btn1, btn2)
    primaryStage.setScene(new Scene(stackPane, 300, 400))
    primaryStage.show()

  }

}
