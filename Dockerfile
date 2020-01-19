FROM hseeberger/scala-sbt
WORKDIR /TicTacToe-3D-4x4
ADD . /TicTacToe-3D-4x4
CMD sbt run
