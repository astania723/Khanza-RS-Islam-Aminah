/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Owner
 */
public class WarnaTable5 extends JTextArea implements TableCellRenderer {

    /**
     *
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     * @return
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        this.setText((String) value);
        this.setWrapStyleWord(true);                    
        this.setLineWrap(true);  
        this.setBackground(new Color(255,255,255));
        this.setSelectionColor(new Color(255,255,255));
        this.setSelectedTextColor(new Color(255,0,0));
        this.setForeground(new Color(50,50,50));
        this.setMargin(new Insets(2, 3, 2, 2));
        this.setFont(new java.awt.Font("Tahoma", 0, 11));
        return this;
    }

}
