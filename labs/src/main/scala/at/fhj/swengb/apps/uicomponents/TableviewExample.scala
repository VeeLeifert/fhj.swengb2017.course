package at.fhj.swengb.apps.uicomponents

import javafx.application.Application
import javafx.beans.property.{SimpleIntegerProperty, SimpleStringProperty}
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.collections.FXCollections
import javafx.scene.Scene
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.control.{ListView, TableColumn, TableView}
import javafx.scene.layout.StackPane
import javafx.stage.Stage

import at.fhj.utils.CanLog

import scala.collection.JavaConverters._

/**
  * Shows a simple example to fill a tableview with a custom type.
  *
  * for more details see https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
  */
object TableViewExample {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[TableViewExampleApp], args: _*)
  }

}

case class TViewRow(aNumber: Int, aString: String) {

  val aNumberProperty = new SimpleIntegerProperty(aNumber)

  val aStringProperty = new SimpleStringProperty(aString)

  def getANumber(): Int = aNumberProperty.get()

  def setANumber(i: Int): Unit = aNumberProperty.set(i)

  def getAString(): String = aStringProperty.get()

  def setAString(s: String): Unit = aStringProperty.set(s)
}

class TableViewExampleApp extends Application with CanLog {

  val tv = new TableView[TViewRow]()
  val tc1: TableColumn[TViewRow, Int] = new TableColumn[TViewRow, Int]("A number")
  val tc2: TableColumn[TViewRow, String] = new TableColumn[TViewRow, String]("A string")

  // attention: names of propertys have to match getters and setters on the domain object
  // here for example, TViewRow is assumed to have a setter called setANumber and a getter named 'getANumber'
  tc1.setCellValueFactory(new PropertyValueFactory[TViewRow,Int]("aNumber"))
  tc2.setCellValueFactory(new PropertyValueFactory[TViewRow,String]("aString"))
  val data: Seq[TViewRow] = Seq(TViewRow(0, "empty"), TViewRow(1, "one"), TViewRow(2,"two"))

  override def start(primaryStage: Stage): Unit = {
    primaryStage.setTitle("A tableview example")

    tv.setItems(FXCollections.observableArrayList(data.asJava))
    tv.getColumns.addAll(tc1, tc2)

    val stackPane = new StackPane()
    stackPane.getChildren.add(tv)
    primaryStage.setScene(new Scene(stackPane, 300, 400))
    primaryStage.show()

  }

}
