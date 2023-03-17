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
 * This class extends from JFrame and draw a line graph using JFreeChart
 * The code was taken/modified from
 * https://www.codejava.net/java-se/graphics/using-jfreechart-to-draw-line-chart-with-categorydataset
 */

public class JFreeChartGraph extends JFrame {
    /**
     * constructor for this class
     * This class creates the JFrame and draws the line graph
     * @param northCount: the sold car count for north FNCD
     * @param northEmployee: money earned by employee for north FNCD
     * @param northFNCD: money earned by FNCD for north FNCD
     * @param southCount: the sold car count for south FNCD
     * @param southEmployee: money earned by employee for south FNCD
     * @param southFNCD: money earned by FNCD for south FNCD
     */
    public JFreeChartGraph(ArrayList<Integer> northCount, ArrayList<Integer> northEmployee, ArrayList<Integer> northFNCD, ArrayList<Integer> southCount, ArrayList<Integer> southEmployee, ArrayList<Integer> southFNCD) {
        super("Line Graph for FNCDs");

        // create the dateset
        CategoryDataset dataset = createDataset(northCount, northEmployee, northFNCD, southCount, southEmployee, southFNCD);

        //creates the jPanel
        JPanel chartPanel = createChartPanel(dataset);
        add(chartPanel, BorderLayout.CENTER);

        // decide on the size
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * second constructor to map the sold car count, so it is more visible, everything is same as above
     * @param northCount: the sold car count for north FNCD
     * @param southCount: the sold car count for south FNCD
     */
    public JFreeChartGraph(ArrayList<Integer> northCount, ArrayList<Integer> southCount){
        super("Line Graph for FNCD car sale count");

        CategoryDataset dataset = createDataset(northCount, southCount);

        JPanel chartPanel = createChartPanel(dataset);
        add(chartPanel, BorderLayout.CENTER);

        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * creates the chart using the data
     * @param dataset: the dataset
     * @return: chartpanel
     */
    private JPanel createChartPanel(CategoryDataset dataset) {
        // title and axis
        String chartTitle = "FNCD Sale Count and Money Made";
        String categoryAxisLabel = "Days";
        String valueAxisLabel = "Count and Dollar Value";

        // create teh chart,
        JFreeChart chart = ChartFactory.createLineChart(chartTitle,
                categoryAxisLabel, valueAxisLabel, dataset, PlotOrientation.VERTICAL, true, false, false);

        return new ChartPanel(chart);
    }

    /**
     * create the dataset used for graphing
     * @param northCount: the sold car count for north FNCD
     * @param northEmployee: money earned by employee for north FNCD
     * @param northFNCD: money earned by FNCD for north FNCD
     * @param southCount: the sold car count for south FNCD
     * @param southEmployee: money earned by employee for south FNCD
     * @param southFNCD: money earned by FNCD for south FNCD
     * @return the dataset
     */
    private CategoryDataset createDataset(ArrayList<Integer> northCount, ArrayList<Integer> northEmployee, ArrayList<Integer> northFNCD, ArrayList<Integer> southCount, ArrayList<Integer> southEmployee, ArrayList<Integer> southFNCD) {
        // start the dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // name for each series
        String series1 = "N count";
        String series2 = "N employee";
        String series3 = "N FNCD";
        String series4 = "S count";
        String series5 = "S employee";
        String series6 = "S FNCD";

        // add each value in the dataset
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

    /**
     * This creates the dataset for the second graph, all logic is the same as above
     * @param northCount: the sold car count for north FNCD
     * @param southCount: the sold car count for south FNCD
     * @return dataset
     */
    private CategoryDataset createDataset(ArrayList<Integer> northCount, ArrayList<Integer> southCount) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String series1 = "N count";
        String series4 = "S count";

        for(int i = 0; i < northCount.size(); i++){
            dataset.addValue((int)northCount.get(i), series1, String.valueOf(i));
            dataset.addValue((int)southCount.get(i), series4, String.valueOf(i));
        }

        return dataset;
    }
}
