package at.fhj.swengb.apps.uicomponents

import javafx.application.Application
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.scene.Scene
import javafx.scene.control.ListView
import javafx.scene.layout.StackPane
import javafx.stage.Stage

import at.fhj.utils.CanLog

/**
  * Shows a simple example to fill a listview with some strings.
  *
  * If an element is selected via mouseclick, write something to the console.
  *
  * see https://docs.oracle.com/javafx/2/ui_controls/list-view.htm
  */
object ListviewExample {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[ListviewExampleApp], args: _*)
  }

}

class ListviewExampleApp extends Application with CanLog {

  override def start(primaryStage: Stage): Unit = {
    primaryStage.setTitle("A listview example")
    val lv = new ListView[String]()
    lv.getItems.add("a")
    lv.getItems.add("b")
    lv.getItems.add("c")
    lv.getItems.add("d")
    lv.getItems.add("e")

    lv.getSelectionModel.selectedItemProperty.addListener( new ChangeListener[String]() {
      override def changed(observable: ObservableValue[_ <: String], oldValue: String, newValue: String): Unit = {
        println("Selected " + newValue)
      }
    })

    val stackPane = new StackPane()
    stackPane.getChildren.add(lv)
    primaryStage.setScene(new Scene(stackPane, 300, 400))
    primaryStage.show()

  }

}
