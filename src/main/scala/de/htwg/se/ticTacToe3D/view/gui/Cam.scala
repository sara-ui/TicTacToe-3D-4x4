package de.htwg.se.ticTacToe3D.view.gui

import scalafx.scene.Group
import scalafx.scene.transform.{Rotate, Scale, Translate}


class Cam extends Group{

  val t: Translate = new Translate()
  val p: Translate = new Translate()
  val ip: Translate = new Translate()
  val rx: Rotate = new Rotate()
  val ry: Rotate = new Rotate()
  val rz: Rotate = new Rotate()
  val s: Scale = new Scale()

  def init = {
    rx.setAxis(Rotate.XAxis)
    ry.setAxis(Rotate.YAxis)
    rz.setAxis(Rotate.ZAxis)
    this.transforms.addAll(t, p, rx, rz, ry, s, ip)
  }

}
