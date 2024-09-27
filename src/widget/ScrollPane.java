
package widget;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import usu.widget.glass.*;

/**
 *
 * @author usu
 */
public class ScrollPane extends JScrollPane {

    /**
     *
     */
    public ScrollPane() {
        super();
        setViewport(new ViewPortGlass());
        setOpaque(false);
        //setBorder(new LineBorder(new Color(235,140,235)));
        //setBackground(new Color(255,235,255));
        setBorder(new LineBorder(new Color(239,244,234)));
        setBackground(new Color(255,255,255));
    }
}
