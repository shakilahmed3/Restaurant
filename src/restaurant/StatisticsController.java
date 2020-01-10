
package restaurant;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Shakil Ahmed
 */
public class StatisticsController implements Initializable {

    @FXML
    private LineChart<?, ?> LineChart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;

    /**
     * Initializes the controller class.
     */
    
    DBAction dbaction = new DBAction();
    @FXML
    private DatePicker FirstDate;
    @FXML
    private DatePicker SecondDate;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
            // TODO
       
            getAllValue();
        } catch (SQLException ex) {
            Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    
    void getAllValue() throws SQLException{
        
        
        Connection con = dbaction.getConnection();
        Statement sellstate = con.createStatement();
        
        String SellQuery = "SELECT sum(Sub_Total) as Total FROM purchase GROUP by Selldate";
        ResultSet sellresult = sellstate.executeQuery(SellQuery);
        
        //Create Selling Series
        XYChart.Series sellseries = new XYChart.Series();
        int i = 1;
        while(sellresult.next())
        {           
            sellseries.getData().add(new XYChart.Data<>(""+i,sellresult.getDouble("Total")));
            i++;
        }      
        
        //Create Profite Series
        XYChart.Series profitseries = new XYChart.Series();
        
        Statement profitstate = con.createStatement();                      
        String ProfitQuery = "SELECT sum(Sub_Profit) as Total FROM purchase GROUP by Selldate";
        ResultSet profitresult = profitstate.executeQuery(ProfitQuery);
        
        int j = 1;
        while(profitresult.next())
        {
             profitseries.getData().add(new XYChart.Data<>(""+j,profitresult.getDouble("Total")));
             j++;
        }
        
        LineChart.getData().setAll(sellseries, profitseries);
        con.close();
        sellresult.close();
        profitresult.close();
        
    }

  

    @FXML
    private void DateAction(ActionEvent event) throws SQLException {
        Connection con = dbaction.getConnection();
        Statement sellstate = con.createStatement();
        
        
        
        Date fdate = Date.valueOf(""+FirstDate.getValue());
        Date Sdate = Date.valueOf(""+SecondDate.getValue());
        
        if(fdate.equals("") && Sdate.equals(""))return;
        
        String SellQuery = "SELECT sum(Sub_Total) as Total FROM purchase WHERE Selldate BETWEEN '"+fdate+"' and '"+Sdate+"' GROUP by Selldate";
        ResultSet sellresult = sellstate.executeQuery(SellQuery);
        
        //Create Selling Series
        XYChart.Series sellseries = new XYChart.Series();
        int i = 1;
        while(sellresult.next())
        {           
            sellseries.getData().add(new XYChart.Data<>(""+i,sellresult.getDouble("Total")));
            i++;
        }      
        
        //Create Profite Series
        XYChart.Series profitseries = new XYChart.Series();
        
        Statement profitstate = con.createStatement();                      
        String ProfitQuery = "SELECT sum(Sub_Profit) as Total FROM purchase WHERE Selldate BETWEEN '"+fdate+"' and '"+Sdate+"' GROUP by Selldate";
        ResultSet profitresult = profitstate.executeQuery(ProfitQuery);
        
        int j = 1;
        while(profitresult.next())
        {
             profitseries.getData().add(new XYChart.Data<>(""+j,profitresult.getDouble("Total")));
             j++;
        }
        
        LineChart.getData().setAll(sellseries, profitseries);
        
        con.close();
        sellresult.close();
        profitresult.close();
    }
    
}
