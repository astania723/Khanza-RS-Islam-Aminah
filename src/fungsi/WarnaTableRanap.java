/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi;

import java.awt.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Owner
 */
public class WarnaTableRanap extends DefaultTableCellRenderer {
    
    /**
     *
     */
    public int kolom = 25;
    
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
         try {
            String dateString = table.getValueAt(row, kolom).toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateToCompare = LocalDate.parse(dateString, formatter);
            LocalDate currentDate = LocalDate.now();
            long daysBetween = Math.abs(ChronoUnit.DAYS.between(currentDate, dateToCompare));

            if (daysBetween >= 2) {
                component.setBackground(new Color(255, 255, 204));
            }
            if (daysBetween >= 6) {
                component.setBackground(new Color(255, 153, 153));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return component;
    }

}
