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
public class WarnaTableIGD extends DefaultTableCellRenderer {
    
    /**
     *
     */
    public int kolom = 20;

    /**
     *
     */
    public int statusrawat = 18;
    
    /**
     *
     */
    public int statuslanjut = 21;
    
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
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (row % 2 == 1){
            component.setBackground(new Color(255,246,244));
        }else{
            component.setBackground(new Color(255,255,255));
        }
        if (table.getValueAt(row, kolom).toString().equals("Sudah Bayar")) {
            component.setBackground(new Color(153, 255, 153));
        }
        if (table.getValueAt(row, statusrawat).toString().equals("Batal")) {
            component.setBackground(new Color(255, 102, 102));
        }
        if (table.getValueAt(row, statuslanjut).toString().equals("Ranap")) {
            component.setBackground(new Color(102, 255, 255));
        }
        return component;
    }

}
