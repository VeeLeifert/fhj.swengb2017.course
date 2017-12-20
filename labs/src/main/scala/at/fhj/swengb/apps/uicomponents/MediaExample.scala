package at.fhj.swengb.apps.uicomponents

import javafx.application.Application
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.scene.media.{Media, MediaPlayer}
import javafx.stage.Stage

import at.fhj.utils.CanLog

/**
  * An example to play a sound with JavaFX
  */
object MediaExample {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[MediaExampleApp], args: _*)
  }

}

class MediaExampleApp extends Application with CanLog {

  val musicFile = "/at/fhj/swengb/apps/uicomponents/Firework-sparkle-sound-effect.mp3"

  override def start(primaryStage: Stage): Unit = {
    primaryStage.setTitle("A mediaplayer example")
    val button = new Button("click")
    button.setOnAction(new EventHandler[ActionEvent] {
      override def handle(event: ActionEvent): Unit = {
        new MediaPlayer(new Media(getClass.getResource(musicFile).toExternalForm)).play()
        println("played")
      }
    })
    val vBox = new VBox()
    vBox.getChildren.addAll(button)

    primaryStage.setScene(new Scene(vBox, 300, 50))
    primaryStage.show()

  }

}
