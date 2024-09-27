/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package widget;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.border.*;

/**
 *
 * @author khanzasoft
 */
public class RoundedCornerBorder extends AbstractBorder{

    /**
     *
     * @param c
     * @param g
     * @param x
     * @param y
     * @param width
     * @param height
     */
    @Override public void paintBorder(
      Component c, Graphics g, int x, int y, int width, int height) {
    Graphics2D g2 = (Graphics2D)g.create();
    g2.setRenderingHint(
        RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    int r = 12;
    int w = width  - 1;
    int h = height - 1;
    Area round = new Area(new RoundRectangle2D.Float(x, y, w, h, r, r));
    Container parent = c.getParent();
    if(parent!=null) {
      g2.setColor(parent.getBackground());
      Area corner = new Area(new Rectangle2D.Float(x, y, width, height));
      corner.subtract(round);
      g2.fill(corner);
    }
    g2.setPaint(c.getForeground());
    g2.draw(round);
    g2.dispose();
  }
  @Override public Insets getBorderInsets(Component c) {
    return new Insets(4, 8, 4, 8);
  }

    /**
     *
     * @param c
     * @param insets
     * @return
     */
    @Override public Insets getBorderInsets(Component c, Insets insets) {
    insets.left = insets.right = 8;
    insets.top = insets.bottom = 4;
    return insets;
  }
    
}
