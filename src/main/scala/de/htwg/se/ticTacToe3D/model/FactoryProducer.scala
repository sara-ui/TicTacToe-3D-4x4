package de.htwg.se.ticTacToe3D.model

object FactoryProducer {
  def apply(kind: String) = kind match {
    case "oneD" =>  new OneDGridsStateStrategyFactory()
    case "fourD" =>  new FourDGridsStateStrategyFactory()
  }
}
