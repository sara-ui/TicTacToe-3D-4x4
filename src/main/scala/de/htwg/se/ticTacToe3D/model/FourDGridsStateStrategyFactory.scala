package de.htwg.se.ticTacToe3D.model

class FourDGridsStateStrategyFactory extends AbstractWinStateStrategyFactory {
  override def getInstance(): WinStateStrategyTemplate = new FourDGridsStateStrategy()
}
