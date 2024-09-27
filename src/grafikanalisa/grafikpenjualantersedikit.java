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
import java.text.*;
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
public class grafikpenjualantersedikit extends JDialog {
    private static PreparedStatement ps;
    private static ResultSet rs;
    private static final Connection koneksi=koneksiDB.condb();
        /**
           * Creates a dataset.
           *
           * @return A dataset.
           */

    public static CategoryDataset createDataset1(String symbol) { //data grafik nilai K dan D

          DefaultCategoryDataset result = new DefaultCategoryDataset();
          String series1 = "Barang Terjual";
          String series2 = "Nilai Barang(Rp)";
          DecimalFormat df2 = new DecimalFormat("###,###,###,###,###,###,###"); 

            try {
                ps=koneksi.prepareStatement("SELECT  databarang.nama_brng,sum(detailjual.jumlah),sum(detailjual.total) from penjualan inner join detailjual on penjualan.nota_jual=detailjual.nota_jual "+
                        "inner join databarang on detailjual.kode_brng=databarang.kode_brng where penjualan.status='Sudah Dibayar' and "+symbol+" group by databarang.nama_brng order by sum(detailjual.jumlah) asc limit 10");
                rs = ps.executeQuery();
                try{
                    while (rs.next()) {
                        String tksbr=rs.getString(1)+"("+df2.format(rs.getDouble(2))+"; Rp"+df2.format(rs.getDouble(3))+")";
                        double njop=rs.getDouble(2);
                        double jml=rs.getDouble(3);

                        //result.addValue(njop, series1,tksbr);
                        result.addValue(jml, series2,tksbr);
                    }
                } catch (Exception e) {
                    System.out.println("Notif : "+e);
                } finally{
                    if(rs!=null){
                        rs.close();
                    }
                    if(ps!=null){
                        ps.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Notifikasi : " + e);
            }
            return result;
       }

       /**
          * Creates a dataset.
          *
     * @param symbol
          * @return A dataset.
          */
        public static CategoryDataset createDataset2(String symbol) {//grafik volume
            DefaultCategoryDataset result = new DefaultCategoryDataset();

             String series1 = "Barang Terjual";
             String series2 = "Nilai Barang(Rp)";
             DecimalFormat df2 = new DecimalFormat("###,###,###,###,###,###,###");

             try {
                ps=koneksi.prepareStatement("SELECT  databarang.nama_brng,sum(detailjual.jumlah),sum(detailjual.total) from penjualan inner join detailjual on penjualan.nota_jual=detailjual.nota_jual  "+
                        "inner join databarang on detailjual.kode_brng=databarang.kode_brng where penjualan.status='Sudah Dibayar' and "+symbol+" group by databarang.nama_brng order by sum(detailjual.jumlah) asc limit 10");
                try {
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        String tksbr=rs.getString(1)+"("+df2.format(rs.getDouble(2))+"; Rp"+df2.format(rs.getDouble(3))+")";
                    double njop=rs.getDouble(2);
                    double jml=rs.getDouble(3);

                    result.addValue(njop, series1,tksbr);
                    }
                } catch (Exception e) {
                    System.out.println("Notif : "+e);
                } finally{
                    if(rs!=null){
                        rs.close();
                    }
                    if(ps!=null){
                        ps.close();
                    }
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
             NumberAxis rangeAxis1 = new NumberAxis("Nilai Barang(Rp)");
             rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
             LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
             renderer1.setBaseToolTipGenerator(
                     new StandardCategoryToolTipGenerator());
             CategoryPlot subplot1 = new CategoryPlot(dataset1, null, rangeAxis1,
                     renderer1);
             subplot1.setDomainGridlinesVisible(true);

             CategoryDataset dataset2 = createDataset2(symbol);
             NumberAxis rangeAxis2 = new NumberAxis("Barang Terjual");
             rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
             BarRenderer renderer2 = new BarRenderer();
             renderer2.setBaseToolTipGenerator(
                     new StandardCategoryToolTipGenerator());
             CategoryPlot subplot2 = new CategoryPlot(dataset2, null, rangeAxis2,
                     renderer2);
             subplot2.setDomainGridlinesVisible(true);

             CategoryAxis domainAxis = new CategoryAxis("Grafik 10 Barang Penjualan Tersedikit");
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
     * @param symbol
          * @return A panel.
          */

         public static JPanel createDemoPanel(String symbol) {
             JFreeChart chart = createChart(symbol);
             return new ChartPanel(chart);
         }
         private final Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
         /**
          * Creates a new demo instance.
          *
          * @param title  the frame title.
     * @param symbol
          */
         public grafikpenjualantersedikit(String title,String symbol) {
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

