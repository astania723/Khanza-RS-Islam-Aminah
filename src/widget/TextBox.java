
package widget;

import java.awt.*;
import usu.widget.glass.*;

/**
 *
 * @author usu
 */
public class TextBox extends TextBoxGlass {

    /**
     *
     */
    public TextBox() {
        super();
        setFont(new java.awt.Font("Tahoma", 0, 11));        
        setSelectionColor(new Color(255,252,252));
        setSelectedTextColor(new Color(255,0,0));
        setForeground(new Color(50,50,50));
        setBackground(new Color(255,255,255));
        setHorizontalAlignment(LEFT);
        setSize(WIDTH,23);
    }
}
