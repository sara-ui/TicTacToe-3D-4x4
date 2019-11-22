package de.htwg.se.ticTacToe3D.view

import de.htwg.se.ticTacToe3D.aview.Tui
import de.htwg.se.ticTacToe3D.controller.Controller
import de.htwg.se.ticTacToe3D.model.Game
import org.scalatest.{Matchers, WordSpec}

class TuiSpec extends WordSpec with Matchers{

  "A Sudoku Tui" should {
    val controller = new Controller(new Game())
    val tui = new Tui(controller)
    "do nothing on input 'q'" in {
      tui.processInputLine("q")
    }
    "create new players 'player1-player2'" in {
      tui.processInputLine("player1-player2")
      controller.game.players(0).name should be("player1")
      controller.game.players(1).name should be("player2")
    }
    "set a cell on input '303'" in {
      tui.processInputLine("303")
      controller.game.sellIsSet(3, 0, 3) should be(true)
      controller.game.grids(3).cell(3, 0).value should not equal ""
    }
    "reset the game on input 'r'" in {
      tui.processInputLine("r")
      controller.game.sellIsSet(3, 0, 3) should be(false)
      controller.game.grids(3).cell(3, 0).value should be("")
    }
    "solve a Sudoku on input 's'" in {
      tui.processInputLine("n")
      tui.processInputLine("s")
      controller.grid.solved should be(true)
    }
    "do nothing on bad input like'99999'" in {
      val old = controller.gridToString
      tui.processInputLine("99999")
      controller.gridToString should be(old)
    }
  }

}
