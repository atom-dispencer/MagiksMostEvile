package genelectrovise.magiksmostevile.tileentity.inscription_table;

import java.awt.Point;
import genelectrovise.magiksmostevile.item.equipment.tabulae.Signum;

public class SignumButtonData {
  private Point point;
  private Signum signum;

  public SignumButtonData(Point point, Signum signum) {
    this.point = point;
    this.signum = signum;
  }

  public Point getPoint() {
    return point;
  }

  public Signum getSignum() {
    return signum;
  }
}
