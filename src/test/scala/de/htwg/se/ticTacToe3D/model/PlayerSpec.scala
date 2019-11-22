package de.htwg.se.ticTacToe3D.model

import org.scalatest._

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = new Player("Your Name", "X")
    "have a name"  in {
      player.name should be("Your Name")
    }
    "have a symbol"  in {
      player.symbol should be("X")
    }
    "have a nice String representation" in {
      player.toString should be("name: Your Name symbol: X")
    }
  }}

}