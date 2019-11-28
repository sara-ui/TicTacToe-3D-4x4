package de.htwg.se.ticTacToe3D.model

class OneDGridsStateStrategyFactory extends AbstractWinStateStrategyFactory {
  override def getInstance(): WinStateStrategyTemplate = new OneDGridsStateStrategy()
}
