package at.fhj.swengb.apps.maze

import java.lang

import at.fhj.swengb.apps.maze.MazeProtobuf.Pos

import scala.collection.JavaConverters._

/**
  * Encodes conversions between business models and protocol encodings
  */
object MazeProtocol {

  def convert(coord: MazeProtobuf.Coord): Coord = Coord(coord.getX, coord.getY)


  def convert(pos: MazeProtobuf.Pos): Pos = Pos(pos.getX, pos.getY)

  def convert(pos: Pos): MazeProtobuf.Pos = {
    MazeProtobuf.Pos.newBuilder()
      .setX(pos.x).setY(pos.y).build()
  }

  def convert(rect: MazeProtobuf.Rect): Rect = Rect(rect.getWidth, rect.getHeight)

  def convert(rect: Rect): MazeProtobuf.Rect = {
    MazeProtobuf.Rect.newBuilder().setHeight(rect.height).setWidth(rect.width).build()
  }

  def convert(topLeft: Coord): MazeProtobuf.Coord =
    MazeProtobuf.Coord.newBuilder().setX(topLeft.x).setY(topLeft.y).build()

  def convert(cell: Cell): MazeProtobuf.Cell = {
    // enthält alle attribute ausser die Options!
    val baseBuilder = MazeProtobuf.Cell.newBuilder()
      .setTopLeft(convert(cell.topLeft))
      .setPos(convert(cell.pos))
      .setRegion(convert(cell.region))

    val builder2 =
      cell.up match {
        case Some(pos) =>
        // pos ist gesetzt, ich muss das ins protobuf reinkriege
        // basebuilder benützten
          baseBuilder.setUp(convert(pos))
        case None =>
        // pos ist nict gesettzt, ist NONE!!!
        // basebuilder benützten
          baseBuilder.setNoneUp(true)
      }


    builder2
      .build
  }

  /**
    * Provided a protobuf encoded maze, create a business model class 'maze' again
    */
  // TODO implement missing functionality
  def convert(protoMaze: MazeProtobuf.Maze): Maze = {
    println(protoMaze.getSizeX)
    println(protoMaze.getSizeY)
    Maze(
      protoMaze.getSizeX,
      protoMaze.getSizeY,
      Pos(0, 0), // TODO read from proto
      Pos(1, 0), // TODO read from proto
      Array.fill(protoMaze.getSizeX * protoMaze.getSizeY)(Cell(Pos(0, 0), Coord(0, 0), Rect(0, 0))), // TODO read from proto
      Rect(100, 100) // TODO read from proto
    )
  }

  /**
    * Convert a business model maze to a protocol encoded maze
    */
  def convert(maze: Maze): MazeProtobuf.Maze = {
    val pCoord = MazeProtobuf.Coord.newBuilder().build

    val cells: lang.Iterable[MazeProtobuf.Cell] = maze.grid.map(convert).toIterable.asJava

    val pMaze = MazeProtobuf.Maze.newBuilder()
      .setSizeX(maze.sizeX)
      .setSizeY(maze.sizeY)
      .setStart(convert(maze.start))
      .setEnd(convert(maze.end))
      .addAllGrid(cells)
      .setCellRect(convert(maze.cellRect))
      .build()
    pMaze
  }


}
