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
      }
      "notify its Observer after solving" in {
        controller.reset
        observer.updated should be(true)
        controller.game.grids(1).cell(1, 1) should be(Cell(""))
      }
    }
  }
}