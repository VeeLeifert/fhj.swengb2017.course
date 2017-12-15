package at.fhj.swengb.apps.maze

import org.scalatest.WordSpecLike


/**
  * Specification for Maze marshaller.
  */
class MazeMarshallerSpec extends WordSpecLike {

  "Mazemarshaller" should {
    ".convert(pos : Pos)" in {
      val expectedPos = Pos(4711, 815)
      val actualPos: MazeProtobuf.Pos = MazeProtocol.convert(expectedPos)

      assert(actualPos.getX == expectedPos.x)
      assert(actualPos.getY == expectedPos.y)
    }

    ".convert(pos : Rect)" in {
      val rect = Rect(4711.0, 815.0)
      val actual: MazeProtobuf.Rect = MazeProtocol.convert(rect)

      assert(rect.height == actual.getHeight)
      assert(rect.width == actual.getWidth)
    }

    ".convert(cell : Cell)" in {

      // zutaten:
      // cell instanz
      // convert methode
      // assertions!
      val cell: Cell = Cell(Pos(1, 2), Coord(3, 4), Rect(5, 6))

      // mit diesen values will ich vergleichen
      val expectedRegion = cell.region // ok
      val expectedPos = cell.pos // ok
      val expectedCoord = cell.topLeft // ok

      val expectedSomeUp: Option[Pos] = cell.up
      val expectedSomeLeft: Option[Pos] = cell.left


      val actualPCell: MazeProtobuf.Cell = MazeProtocol.convert(cell)

      val actualRegion: Rect = MazeProtocol.convert(actualPCell.getRegion)
      val actualPos = MazeProtocol.convert(actualPCell.getPos)
      val actualCoord = MazeProtocol.convert(actualPCell.getTopLeft)

      val actualSomeUp: Option[Pos] = {
        if (actualPCell.getNoneUp)
          None
        else
          Option(MazeProtocol.convert(actualPCell.getUp))
      }

      assert(actualSomeUp == expectedSomeUp)
      assert(actualCoord == expectedCoord)
      assert(actualPos == expectedPos)
      assert(actualRegion == expectedRegion)

    }
  }
}
