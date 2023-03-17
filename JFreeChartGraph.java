import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * https://www.codejava.net/java-se/graphics/using-jfreechart-to-draw-line-chart-with-categorydataset
 */

public class JFreeChartGraph extends JFrame {

    public JFreeChartGraph(ArrayList<Integer> northCount, ArrayList<Integer> northEmployee, ArrayList<Integer> northFNCD, ArrayList<Integer> southCount, ArrayList<Integer> southEmployee, ArrayList<Integer> southFNCD) {
        super("Line Graph for FNCDs");

        CategoryDataset dataset = createDataset(northCount, northEmployee, northFNCD, southCount, southEmployee, southFNCD);

        JPanel chartPanel = createChartPanel(dataset);
        add(chartPanel, BorderLayout.CENTER);

        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public JFreeChartGraph(ArrayList<Integer> northCount, ArrayList<Integer> southCount){
        super("Line Graph for FNCD car sale count");

        CategoryDataset dataset = createDataset(northCount, southCount);

        JPanel chartPanel = createChartPanel(dataset);
        add(chartPanel, BorderLayout.CENTER);

        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    private JPanel createChartPanel(CategoryDataset dataset) {
        String chartTitle = "FNCD Sale Count and Money Made";
        String categoryAxisLabel = "Days";
        String valueAxisLabel = "Count and Dollar Value";

        JFreeChart chart = ChartFactory.createLineChart(chartTitle,
                categoryAxisLabel, valueAxisLabel, dataset, PlotOrientation.VERTICAL, true, false, false);

        return new ChartPanel(chart);
    }



    private CategoryDataset createDataset(ArrayList<Integer> northCount, ArrayList<Integer> northEmployee, ArrayList<Integer> northFNCD, ArrayList<Integer> southCount, ArrayList<Integer> southEmployee, ArrayList<Integer> southFNCD) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String series1 = "N count";
        String series2 = "N employee";
        String series3 = "N FNCD";
        String series4 = "S count";
        String series5 = "S employee";
        String series6 = "S FNCD";

        for(int i = 0; i < northCount.size(); i++){
            System.out.println(northCount.get(i));
            dataset.addValue((int)northCount.get(i), series1, String.valueOf(i));
            dataset.addValue((int)northEmployee.get(i), series2, String.valueOf(i));
            dataset.addValue((int)northFNCD.get(i), series3, String.valueOf(i));

            dataset.addValue((int)southCount.get(i), series4, String.valueOf(i));
            dataset.addValue((int)southEmployee.get(i), series5, String.valueOf(i));
            dataset.addValue((int)southFNCD.get(i), series6, String.valueOf(i));
        }

        return dataset;
    }

    private CategoryDataset createDataset(ArrayList<Integer> northCount, ArrayList<Integer> southCount) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String series1 = "N count";
        String series4 = "S count";

        for(int i = 0; i < northCount.size(); i++){
            System.out.println(northCount.get(i));
            dataset.addValue((int)northCount.get(i), series1, String.valueOf(i));

            dataset.addValue((int)southCount.get(i), series4, String.valueOf(i));
        }

        return dataset;
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new JFreeChartGraph().setVisible(true);
//            }
//        });
//    }
}
