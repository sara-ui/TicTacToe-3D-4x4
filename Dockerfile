FROM hseeberger/scala-sbt:8u212_1.2.8_2.13.0
WORKDIR /TicTacToe-3D-4x4
ADD . /TicTacToe-3D-4x4
CMD sbt run
docker build -t TicTacToe4D:v1 .
docker run TicTacToe4D:v1
