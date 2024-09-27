/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafikanalisa;

/**
 *
 * @author Via
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import fungsi.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.data.category.*;


/**
 *
 * @author Via
 */
public class grafikperiksaralanibutahun extends JDialog {

    public static CategoryDataset createDataset1(String symbol) { //data grafik nilai K dan D

          DefaultCategoryDataset result = new DefaultCategoryDataset();
          String series1 = "Registrasi Pertahun";
          
            try {
                Statement stat = koneksiDB.condb().createStatement();
                ResultSet rs = stat.executeQuery(symbol);
                while (rs.next()) {
                    String tksbr=rs.getString(1)+"("+rs.getString(2)+")";
                    double field1=rs.getDouble(2);
                    double field2=rs.getDouble(2);
                    result.addValue(field2, series1,tksbr);
                }
            } catch (SQLException e) {
                System.out.println("Notifikasi : " + e);
            }
            return result;
       }

    /**
     *
     * @param symbol
     * @return
     */
    public static CategoryDataset createDataset2(String symbol) {//grafik volume
            DefaultCategoryDataset result = new DefaultCategoryDataset();
            String series1 = "Registrasi Pertahun";
            try {
                Statement stat = koneksiDB.condb().createStatement();
                ResultSet rs = stat.executeQuery(symbol);
                while (rs.next()) {
                    String tksbr=rs.getString(1)+"("+rs.getString(2)+")";
                    double field1=rs.getDouble(2);
                    double field2=rs.getDouble(2);
                    result.addValue(field1, series1,tksbr);
                }
            } catch (SQLException e) {
                System.out.println("Notifikasi : " + e);
            }
             return result;
         }

         private static JFreeChart createChart(String symbol) {

             CategoryDataset dataset1 = createDataset1(symbol);
             NumberAxis rangeAxis1 = new NumberAxis("Jumlah");
             rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
             LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
             renderer1.setBaseToolTipGenerator(
                     new StandardCategoryToolTipGenerator());
             CategoryPlot subplot1 = new CategoryPlot(dataset1, null, rangeAxis1,
                     renderer1);
             subplot1.setDomainGridlinesVisible(true);

             CategoryDataset dataset2 = createDataset2(symbol);
             NumberAxis rangeAxis2 = new NumberAxis("Jumlah");
             rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
             BarRenderer renderer2 = new BarRenderer();
             renderer2.setBaseToolTipGenerator(
                     new StandardCategoryToolTipGenerator());
             CategoryPlot subplot2 = new CategoryPlot(dataset2, null, rangeAxis2,
                     renderer2);
             subplot2.setDomainGridlinesVisible(true);

             CategoryAxis domainAxis = new CategoryAxis("Registrasi Pertahun");
             CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
             plot.add(subplot1,2 );
             plot.add(subplot2,1 );

             return new JFreeChart(
                     "",
                     new Font("SansSerif", Font.PLAIN,6 ), plot, true);

         }

    /**
     *
     * @param symbol
     * @return
     */
    public static JPanel createDemoPanel(String symbol) {
             JFreeChart chart = createChart(symbol);
             return new ChartPanel(chart);
         }
         sekuel Sequel = new sekuel();
         validasi Valid = new validasi();
         Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
         public grafikperiksaralanibutahun(String title,String symbol) {
           setTitle(title);
           JPanel chartPanel = createDemoPanel(symbol);
           
           chartPanel.setSize(screen.width,screen.height);
           setContentPane(chartPanel);
           setModal(true);
           setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
           pack();
           setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         }

}

