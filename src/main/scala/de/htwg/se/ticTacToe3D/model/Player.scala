package de.htwg.se.ticTacToe3D.model

case class Player(name: String, symbol: String) {
  override def toString:String = "name: " + name +" symbol: " + symbol
}