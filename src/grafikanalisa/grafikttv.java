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


import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 *
 * @author Via
 */
public class grafikttv extends JDialog {
      sekuel Sequel = new sekuel();
      validasi Valid = new validasi();
      private String kolom1="" ;
      public grafikttv(String title,String symbol) {
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
      Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
        /**
           * Creates a dataset.
           *
           * @return A dataset.
           */

    public static CategoryDataset createDataset1(String symbol) { //data grafik nilai K dan D

          DefaultCategoryDataset result = new DefaultCategoryDataset();
          String series1 = "Suhu";
          String series2 = "Tensi";
          String series3 = "nadi";
          String series4 = "respirasi";
            try {
                Statement stat = koneksiDB.condb().createStatement();
                ResultSet rs = stat.executeQuery("select concat(pemeriksaan_ranap.tgl_perawatan,' ',pemeriksaan_ranap.jam_rawat) as waktu, replace(pemeriksaan_ranap.suhu_tubuh,',','.') as suhu,pemeriksaan_ranap.tensi as tensi ,pemeriksaan_ranap.nadi as nadi,pemeriksaan_ranap.respirasi as respirasi from pemeriksaan_ranap "+
                   symbol+"");
                while (rs.next()) {
                    String tksbr=rs.getString(1);
                    double field1=rs.getDouble(2);
                    double field2=rs.getDouble(3);
                    double field3=rs.getDouble(4);
                    double field4=rs.getDouble(5);
                    //result.addValue(field1, series1,tksbr);
                    result.addValue(field1, series1,tksbr);
                    result.addValue(field2, series2,tksbr);
                    result.addValue(field3, series3,tksbr);
                    result.addValue(field4, series4,tksbr);
                }
            } catch (SQLException e) {
                System.out.println("Notifikasi : " + e);
            }
            return result;
       }
         private static JFreeChart createChart(String symbol) {
             

             CategoryDataset dataset1 = createDataset1(symbol);
             NumberAxis rangeAxis1 = new NumberAxis("Ukuran");
             rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
             LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
             renderer1.setBaseToolTipGenerator(
                     new StandardCategoryToolTipGenerator());
             renderer1.setSeriesPaint(0, Color.BLUE);
             CategoryPlot subplot1 = new CategoryPlot(dataset1, null, rangeAxis1,
                     renderer1);
             subplot1.setDomainGridlinesVisible(true);
             subplot1.setRangeGridlinesVisible(true);
             subplot1.setRangeGridlinePaint(Color.BLACK);
             subplot1.setDomainGridlinesVisible(true);
             subplot1.setDomainGridlinePaint(Color.BLACK);
             subplot1.setBackgroundPaint(Color.DARK_GRAY);
             CategoryAxis domainAxis = new CategoryAxis("Tanda Tanda Vital Pasien");
             CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
             
//             CombinedCategoryPlot plot = new CombinedCategoryPlot(
//                     domainAxis, new NumberAxis("Range"));
             plot.add(subplot1,1 );
             JFreeChart result = new JFreeChart(
                     "",
                     new Font("SansSerif", Font.PLAIN,6 ), plot, true);

             return result;

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

