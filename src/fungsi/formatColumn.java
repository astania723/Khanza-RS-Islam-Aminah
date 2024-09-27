/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi;

import java.text.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author dosen
 */
public class formatColumn {
    public static int curr = 0;
    public static int angka = 1;

    /**
     *
     */
    public static int kanan = 2;

    /**
     *
     */
    public static int tengah = 3;
    public static int tanggal = 4;
    private DefaultTableModel model;
    private TableColumnModel tcm;
    private TableColumn tc;
    
    public void  setColumnFormat(DefaultTableModel tabMode,int column, int format) {
        tc = tcm.getColumn(column);
        switch (format) {
            case 0:{
                class CurrRender extends DefaultTableCellRenderer{
                CurrRender(){
                    super();
                    setHorizontalAlignment(SwingConstants.RIGHT);
                }
                @Override
                public void setValue(Object value){
                    if ((value != null) && (value instanceof Number)){
                         Number numberValue = (Number) value;
                         NumberFormat formatter = NumberFormat.getNumberInstance();
                         value = formatter.format(numberValue.doubleValue());
                    }
                    super.setValue(value);
                }                                   
                }
            }
        }
    }

}
