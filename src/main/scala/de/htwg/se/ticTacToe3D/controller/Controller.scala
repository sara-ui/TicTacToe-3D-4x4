package de.htwg.se.ticTacToe3D.controller

import de.htwg.se.ticTacToe3D.model.Game
import de.htwg.se.ticTacToe3D.model.Cell
import de.htwg.se.ticTacToe3D.util.Observable

class Controller(var game: Game) extends Observable {

  var won: Array[Boolean] = Array(false, false)
  var myTurn: Boolean = true
  var statusMessage: String = Messages.WELCOME_MESSAGE
  def checkData(row: Int, column: Int, grid: Int): Boolean = {
    if(row > 3 || column > 3 || grid > 3){
      statusMessage =  Messages.ERROR_MOVE
      notifyObservers
      return false
    }
    true
  }
  def checkForWinGrid(symbol: String, i: Int, row: Boolean = true): Boolean = {
    var valid = true
    for (a <- 0 to 3) {
      valid = true
      for (b <- 0 to 3) {
        if (row) {
          valid = valid && game.grids(i).cell(a, b).value == symbol
        } else {
          valid = valid && game.grids(i).cell(b, a).value == symbol
        }
      }
      if (valid) {
        return true
      }
    }
    valid
  }
  def checkForWinDiagonalGrids(symbol: String): Boolean = {
    var valid = true
    for (a <- 0 to 3) {
      valid = true
      for (b <- 0 to 3) {
        valid = true
        for (i <- 0 to 3) {
          valid = valid && game.grids(i).cell(a, b).value == symbol
        }
        if (valid) {
          return true
        }
      }
    }
    valid
  }
  def checkForWinDiagonal(symbol: String, grid: Int): Boolean = {
    var valid = true
    for (index <- 0 to 3) {
      valid = valid && game.grids(grid).cell(index, index).value == symbol
    }
    if (!valid) {
      valid = true
      for (index <- 0 to 3) {
        valid = valid && game.grids(grid).cell(3 - index, index).value == symbol
      }
    }
    valid
  }
  def checkForWin(value: Int): Unit = {
    val symbol = game.players(value).symbol

    for (i <- 0 to 3) {
      if (!won(value)) {
        // One Grid Checks
        won(value) = checkForWinGrid(symbol, i) || checkForWinGrid(symbol, i, row = false) || checkForWinDiagonal(symbol, i)
        // @TODO: All Grids checks
        // won(value) = won(value) || checkForWinDiagonalGrids(symbol)
      }
    }
    if(won(value)) {
      this.statusMessage = game.players(value).name + Messages.WIN_MESSAGE
      notifyObservers
    }
  }
  def setValue(row: Int, column: Int, grid: Int): Boolean = {
    if (game.players.contains(null) || "".equals(game.players(0).name)) {
      statusMessage = Messages.ERROR_GIVE_PLAYERS_START
      notifyObservers
      return false
    }
    if (checkData(row, column, grid)) {
      if(myTurn){
        tryToMove(0, row, column, grid)
        checkForWin(0)
      }else{
        tryToMove(1, row, column, grid)
        checkForWin(1)
      }
      myTurn = !myTurn
    }
    true
  }
  override def toString: String = game.customToString
  def getNextPlayer(index: Int): String = {
    if (index == 0) {
      game.players(1).name
    } else {
      game.players(0).name
    }
  }
  def tryToMove(playerIndex: Int, row: Int, column: Int, grid: Int): Boolean = {
    if (game.sellIsSet(row, column, grid)) {
      this.myTurn = !this.myTurn
      this.statusMessage = Messages.CELL_IS_SET
    } else {
      game = game.set(row, column, grid, playerIndex)
      this.statusMessage = Messages.playerMoveToString(game.players(playerIndex).name, row, column, grid) + getNextPlayer(playerIndex) + Messages.NEXT
    }
    notifyObservers
    true
  }
  def reset: Boolean = {
    if (game.players.contains(null) || "".equals(game.players(0).name)) {
      this.statusMessage = Messages.ERROR_GIVE_PLAYERS_RESET
    } else {
      game = new Game(game.players(0).name, game.players(1).name, "X", "O")
      myTurn = true
      won = Array(false, false)
      this.statusMessage = Messages.GAME_RESET_MESSAGE + game.players(0).name + Messages.INFO_ABOUT_THE_GAME
    }
    notifyObservers
    true
  }
  def setPlayers (player1: String, player2: String): Boolean = {
    if ("".equals(player1) || "".equals(player2)) {
      this.statusMessage = Messages.PLAYER_NAME
      notifyObservers
    } else {
      game = new Game(player1, player2, "X", "O")
    }
    statusMessage = Messages.PLAYER_DEFINED_MESSAGE + player1 + Messages.INFO_ABOUT_THE_GAME
    notifyObservers
    true
  }
}

object Messages {
  val CELL_IS_SET: String = "This Cell is Already been setted, please try another one \n"
  val ENTER_PLAYERS: String = "please enter the two players name with - between then (don't forget no spacing)"
  val WELCOME_MESSAGE: String = "Welcome to HTWG TicTacToe 4x4x4! \n" + ENTER_PLAYERS
  val PLAYER_NAME: String = "please enter players name again"
  val PLAYER_DEFINED_MESSAGE: String = "Players are defined!!!! \n"
  val INFO_ABOUT_THE_GAME: String = " you can start, the grids with the number in them are\n" +
    " an example of what you should give here if you want to player in one of the cells,\n" +
    " the 3 numbers are the row, column, grid. Now, MAKE YOU FIRST MOVE :) !!\n"
  val ERROR_GIVE_PLAYERS_START: String = "you can't start the Game without giving the name of the players\n" + ENTER_PLAYERS
  val ERROR_MOVE: String = "you are out of the limit of the grid!! please retry with correct move"
  val NEXT: String = " you are next!! \n"
  val GAME_RESET_MESSAGE: String = "Game was reseted!!!! \n"
  val ERROR_GIVE_PLAYERS_RESET: String = "you can't reset the Game without giving the name of the players\n" + ENTER_PLAYERS
  val WIN_MESSAGE: String = " you won !! congratulation \n "+ " if you want to start again press r + enter, if not press q + enter to quit"
  def playerMoveToString(player: String, row: Int, column: Int, grid: Int): String = player + " played : (" + row + "," + column + ") in Grid " + grid + "\n"
}
