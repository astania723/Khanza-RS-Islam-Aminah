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
public class grafikproses extends JDialog {
        /**
           * Creates a dataset.
           *
     * @param symbol
           * @return A dataset.
           */

    public static CategoryDataset createDataset1(String symbol) { //data grafik nilai K dan D

          DefaultCategoryDataset result = new DefaultCategoryDataset();
          String series1 = "Proses Lahir Bayi";
          String series2 = "Proses Lahir";

            try {
                Statement stat = koneksiDB.condb().createStatement();
                ResultSet rs = stat.executeQuery("SELECT proses_lahir,count(proses_lahir) as jumlah  FROM pasien_bayi"+
                        " inner join pasien on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis "+
                " "+symbol+" group by proses_lahir");
                while (rs.next()) {
                    String tksbr=rs.getString(1)+"("+rs.getString(2)+")";
                    double field1=rs.getDouble(2);
                    double field2=rs.getDouble(2);

                    //result.addValue(field1, series1,tksbr);
                    result.addValue(field2, series1,tksbr);
                }
            } catch (SQLException e) {
                System.out.println("Notifikasi : " + e);
            }
            return result;
       }

       /**
          * Creates a dataset.
          *
          * @return A dataset.
          */
        public static CategoryDataset createDataset2(String symbol) {//grafik volume
            DefaultCategoryDataset result = new DefaultCategoryDataset();

             String series1 = "Proses Lahir Bayi";
             String series2 = "Proses Lahir";

             try {
                Statement stat = koneksiDB.condb().createStatement();
                ResultSet rs = stat.executeQuery("SELECT proses_lahir,count(proses_lahir) as jumlah  FROM pasien_bayi "+
                        "inner join pasien on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis "+
                " "+symbol+" group by proses_lahir");
                while (rs.next()) {
                    String tksbr=rs.getString(1)+"("+rs.getString(2)+")";
                    double field1=rs.getDouble(2);
                    double field2=rs.getDouble(2);

                    result.addValue(field1, series1,tksbr);
                    //result.addValue(field2, series2,tksbr);
                }
            } catch (SQLException e) {
                System.out.println("Notifikasi : " + e);
            }
             return result;
         }

         /**
          * Creates a chart.
          *
          * @return A chart.
          */
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

             CategoryAxis domainAxis = new CategoryAxis("Proses Lahir Bayi");
             CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
//             CombinedCategoryPlot plot = new CombinedCategoryPlot(
//                     domainAxis, new NumberAxis("Range"));
             plot.add(subplot1,2 );
             plot.add(subplot2,1 );

             return new JFreeChart(
                     "",
                     new Font("SansSerif", Font.PLAIN,6 ), plot, true);

         }

         /**
          * Creates a panel for the demo (used by SuperDemo.java).
          *
          * @return A panel.
          */

         public static JPanel createDemoPanel(String symbol) {
             JFreeChart chart = createChart(symbol);
             return new ChartPanel(chart);
         }
         sekuel Sequel = new sekuel();
         validasi Valid = new validasi();
         Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();

    /**
     *
     * @param title
     * @param symbol
     */
    public grafikproses(String title,String symbol) {
           // super(title);
           setTitle(title);
           JPanel chartPanel = createDemoPanel(symbol);
           
           chartPanel.setSize(screen.width,screen.height);
           setContentPane(chartPanel);
           
           //setSize(screen.width,screen.height);
           setModal(true);
           //setUndecorated(true);
           setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
           pack();
           setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         }

         /**
          * Starting point for the demonstration application.
          *
          * @param args  ignored.
          */

//        public static void main(String args[]){
//            //        String title = "test Combined Category Plot Demo 1";
//        cocografik demo = new cocografik("aali");
//        JFrame v = new JFrame(title);
//        v.add(demo);
//        v.setBackground(Color.BLUE);
//        v.setSize(new Dimension(1200, 700));
//        v.setDefaultCloseOperation(v.EXIT_ON_CLOSE);
//        v.setVisible(true);
//        }
//           public static void main(String[] args) {
//             String title = "Combined Category Plot Demo ";
//             CombinedCategoryPlotDemo1 demo = new CombinedCategoryPlotDemo1(title);
//             demo.pack();
////             RefineryUtilities.centerFrameOnScreen(demo);
//             demo.setVisible(true);
//
//         }
}

