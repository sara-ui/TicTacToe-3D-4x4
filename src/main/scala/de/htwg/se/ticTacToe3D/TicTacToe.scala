package de.htwg.se.ticTacToe3D

import de.htwg.se.ticTacToe3D.aview.Tui
import de.htwg.se.ticTacToe3D.controller.Controller
import de.htwg.se.ticTacToe3D.model.Game

import scala.io.StdIn.readLine

object TicTacToe3D {
  var controller = new Controller(new Game())

  val tui = new Tui(controller)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {
    var input: String = ""

    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}