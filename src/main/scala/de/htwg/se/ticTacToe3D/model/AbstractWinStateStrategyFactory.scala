package de.htwg.se.ticTacToe3D.model

trait AbstractWinStateStrategyFactory {
  def getInstance(): WinStateStrategyTemplate
}
