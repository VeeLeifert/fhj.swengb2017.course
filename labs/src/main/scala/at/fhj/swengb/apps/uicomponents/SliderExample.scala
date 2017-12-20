package at.fhj.swengb.apps.uicomponents

import java.nio.file.{Files, Paths}
import javafx.application.Application
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.Scene
import javafx.scene.control.{Button, Slider}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.layout.{StackPane, VBox}
import javafx.stage.Stage

import at.fhj.utils.CanLog

import scala.util.Random

/**
  * Shows a simple example to use a Slider ui component
  *
  * see https://docs.oracle.com/javafx/2/ui_controls/slider.htm
  */
object SliderExample {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[SliderExampleApp], args: _*)
  }

}

class SliderExampleApp extends Application with CanLog {

  override def start(primaryStage: Stage): Unit = {
    primaryStage.setTitle("A Slider example")
    val slider = new Slider(0, 1, 0.5)
    val button = new Button("click")
    button.setOnAction(new EventHandler[ActionEvent] {
      override def handle(event: ActionEvent): Unit = {
        slider.setValue(Random.nextDouble())
      }
    })
    val vBox = new VBox()
    vBox.getChildren.addAll(slider, button)

    primaryStage.setScene(new Scene(vBox, 300, 50))
    primaryStage.show()

  }

}
