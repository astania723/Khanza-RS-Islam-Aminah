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
public class WarnaTablePEWSM extends DefaultTableCellRenderer {

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
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (row % 2 == 1) {
            component.setBackground(new Color(255, 246, 244));
            component.setForeground(new Color(50, 50, 50));
        } else {
            component.setBackground(new Color(255, 255, 255));
            component.setForeground(new Color(50, 50, 50));
        }

        if ((column == 6) || (column == 7)) {
            if (Integer.parseInt(table.getValueAt(row, 7).toString()) == 0) {
                component.setBackground(Color.WHITE);
                component.setForeground(new Color(50, 50, 50));
            } else if (Integer.parseInt(table.getValueAt(row, 7).toString()) == 1) {
                component.setBackground(Color.YELLOW);
                component.setForeground(Color.BLACK);
            } else if (Integer.parseInt(table.getValueAt(row, 7).toString()) == 2) {
                component.setBackground(Color.PINK);
                component.setForeground(Color.BLACK);
            }
        }

        if ((column == 8) || (column == 9)) {
            if (Integer.parseInt(table.getValueAt(row, 9).toString()) == 0) {
                component.setBackground(Color.WHITE);
                component.setForeground(new Color(50, 50, 50));
            } else if (Integer.parseInt(table.getValueAt(row, 9).toString()) == 1) {
                component.setBackground(Color.YELLOW);
                component.setForeground(Color.BLACK);
            } else if (Integer.parseInt(table.getValueAt(row, 9).toString()) == 2) {
                component.setBackground(Color.PINK);
                component.setForeground(Color.BLACK);
            }
        }

        if ((column == 10) || (column == 11)) {
            if (Integer.parseInt(table.getValueAt(row, 11).toString()) == 0) {
                component.setBackground(Color.WHITE);
                component.setForeground(new Color(50, 50, 50));
            } else if (Integer.parseInt(table.getValueAt(row, 11).toString()) == 1) {
                component.setBackground(Color.YELLOW);
                component.setForeground(Color.BLACK);
            } else if (Integer.parseInt(table.getValueAt(row, 11).toString()) == 2) {
                component.setBackground(Color.PINK);
                component.setForeground(Color.BLACK);
            }
        }

        if ((column == 12) || (column == 13)) {
            if (Integer.parseInt(table.getValueAt(row, 13).toString()) == 0) {
                component.setBackground(Color.WHITE);
                component.setForeground(new Color(50, 50, 50));
            } else if (Integer.parseInt(table.getValueAt(row, 13).toString()) == 1) {
                component.setBackground(Color.YELLOW);
                component.setForeground(Color.BLACK);
            } else if (Integer.parseInt(table.getValueAt(row, 13).toString()) == 2) {
                component.setBackground(Color.PINK);
                component.setForeground(Color.BLACK);
            }
        }

        if ((column == 14) || (column == 15)) {
            if (Integer.parseInt(table.getValueAt(row, 15).toString()) == 0) {
                component.setBackground(Color.WHITE);
                component.setForeground(new Color(50, 50, 50));
            } else if (Integer.parseInt(table.getValueAt(row, 15).toString()) == 1) {
                component.setBackground(Color.YELLOW);
                component.setForeground(Color.BLACK);
            } else if (Integer.parseInt(table.getValueAt(row, 15).toString()) == 2) {
                component.setBackground(Color.PINK);
                component.setForeground(Color.BLACK);
            }
        }

        if ((column == 16) || (column == 17)) {
            if (Integer.parseInt(table.getValueAt(row, 17).toString()) == 0) {
                component.setBackground(Color.WHITE);
                component.setForeground(new Color(50, 50, 50));
            } else if (Integer.parseInt(table.getValueAt(row, 17).toString()) == 1) {
                component.setBackground(Color.YELLOW);
                component.setForeground(Color.BLACK);
            } else if (Integer.parseInt(table.getValueAt(row, 17).toString()) == 2) {
                component.setBackground(Color.PINK);
                component.setForeground(Color.BLACK);
            }
        }

        if ((column == 18) || (column == 19)) {
            if (Integer.parseInt(table.getValueAt(row, 19).toString()) == 0) {
                component.setBackground(Color.WHITE);
                component.setForeground(new Color(50, 50, 50));
            } else if (Integer.parseInt(table.getValueAt(row, 19).toString()) == 1) {
                component.setBackground(Color.YELLOW);
                component.setForeground(Color.BLACK);
            } else if (Integer.parseInt(table.getValueAt(row, 19).toString()) == 2) {
                component.setBackground(Color.PINK);
                component.setForeground(Color.BLACK);
            }
        }

        if ((column == 20) || (column == 21)) {
            if (Integer.parseInt(table.getValueAt(row, 21).toString()) == 0) {
                component.setBackground(Color.WHITE);
                component.setForeground(new Color(50, 50, 50));
            } else if (Integer.parseInt(table.getValueAt(row, 21).toString()) == 1) {
                component.setBackground(Color.YELLOW);
                component.setForeground(Color.BLACK);
            } else if (Integer.parseInt(table.getValueAt(row, 21).toString()) == 2) {
                component.setBackground(Color.PINK);
                component.setForeground(Color.BLACK);
            }
        }

        if ((column == 22)) {
            if (Integer.parseInt(table.getValueAt(row, 22).toString()) == 0) {
                component.setBackground(Color.WHITE);
                component.setForeground(new Color(50, 50, 50));
            } else if (Integer.parseInt(table.getValueAt(row, 22).toString()) > 0) {
                component.setBackground(Color.PINK);
                component.setForeground(Color.BLACK);
            }
        }

        if ((column == 23)) {
            if (Integer.parseInt(table.getValueAt(row, 23).toString()) == 0) {
                component.setBackground(Color.WHITE);
                component.setForeground(new Color(50, 50, 50));
            } else if (Integer.parseInt(table.getValueAt(row, 23).toString()) > 0) {
                component.setBackground(Color.YELLOW);
                component.setForeground(Color.BLACK);
            }
        }
        return component;
    }

}
