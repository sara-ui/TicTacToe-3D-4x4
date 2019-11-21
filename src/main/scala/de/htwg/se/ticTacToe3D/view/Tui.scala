package de.htwg.se.ticTacToe3D.aview

import de.htwg.se.ticTacToe3D.controller.Controller
import de.htwg.se.ticTacToe3D.util.Observer


class Tui(controller: Controller) extends Observer{

  controller.add(this)

  val PlayersPattern = "(^[a-zA-Z0-9_-]*$)".r
  val MovePattern = "(^[0-3][0-3][0-3]$)".r

  def processInputLine(input: String):Unit = {
    input match {
      case "q" =>
      case "r"=> controller.rest()
      case MovePattern(input) => checkIfMove(input)
      case PlayersPattern(input) => checkIfPlayers(input)
    }
  }
  def checkIfPlayers (input: String): Unit = {
    if (input.contains("-")){
      val names: Array[String] = input.split("-")
      if(names.length == 2 ){
        controller.setPlayers(names(0), names(1))
      }
    }
  }
  def checkIfMove (input: String): Unit = {
    input.toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
      case row :: column :: grid :: Nil => controller.setValue(row, column, grid)
    }
  }

  override def update: Boolean =  {
    println(controller.toString)
    println(controller.statusMessage)
    true
  }
}