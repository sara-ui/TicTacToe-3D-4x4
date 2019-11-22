package de.htwg.se.ticTacToe3D.controller

import de.htwg.se.ticTacToe3D.model.{Cell, Game}
import de.htwg.se.ticTacToe3D.util.Observer

import scala.language.reflectiveCalls
import org.scalatest.{Matchers, WordSpec}

class ControllerSpec extends WordSpec with Matchers {

  "A Controller" when {
    "observed by an Observer" should {
      val game = new Game()
      val controller = new Controller(game)
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Boolean = {updated = true; updated}
      }
      controller.add(observer)
      "notify its Observer after players creation" in {
        controller.setValue(1, 1, 1)
        observer.updated should be(true)
        controller.setPlayers("player1", "player2")
        observer.updated should be(true)
        controller.game.players(0).name should be("player1")
        controller.game.players(1).name should be("player2")
      }
      "notify its Observer after setting value" in {
        val oldGame = controller.game
        controller.setValue(1, 1, 1)
        observer.updated should be(true)
        controller.game.grids(1).cell(1, 1) should be(Cell("X"))
        oldGame.grids(1).cell(1, 1) should be(Cell(""))
        controller.setValue(1, 1, 1)
        controller.setValue(1, 2, 1)
        observer.updated should be(true)
        controller.game.grids(1).cell(1, 2) should be(Cell("O"))
        oldGame.grids(1).cell(1, 2) should be(Cell(""))
      }
      "toString should not be empty" in {
        controller.toString should not equal ""
      }
      "notify its Observer after playing 4,4,4" in {
        controller.setValue(4, 4, 4)
        observer.updated should be(true)
      }
      "notify its Observer after winning" in {
        controller.setValue(3, 0, 3)
        controller.setValue(2, 0, 3)
        controller.setValue(3, 1, 3)
        controller.setValue(2, 1, 3)
        controller.setValue(3, 2, 3)
        controller.setValue(2, 2, 3)
        controller.setValue(3, 3, 3)
        controller.setValue(2, 3, 3)
        observer.updated should be(true)
      }
      "notify its Observer after resetting" in {
        controller.reset
        observer.updated should be(true)
        controller.game.grids(1).cell(1, 1) should be(Cell(""))
      }
    }
  }
}