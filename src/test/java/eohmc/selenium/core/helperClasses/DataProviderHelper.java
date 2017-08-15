package eohmc.selenium.core.helperClasses;

import org.testng.annotations.DataProvider;

/**
 * Created by XY40224 on 07/08/2017.
 */
public class DataProviderHelper extends BaseClassHelper
{

    ExcelDataConsumerHelper  excelDataConsumer = null;/*Object To Read Data From Excel*/

    //Call the inputTestDataFile method to access the mastersuitefile
    String xlFilePath = currentPath +getConfig().getInputDataFile();
    String sheetName = "TestData";


    @DataProvider(name = "taxCalculatorData")
    public Object[][] taxCalculatorData() throws Exception {
        Object[][] data = testData(xlFilePath,sheetName);

        intRowCount = (data.length);/*Get number of rows*/
        return data;
    }



    //Create a constructor method to load the workbook(Generic method)
    public Object[][] testData(String xlFilePath, String sheetName) throws Exception {

        //Create & Initialize an Object of the Object array
        Object[][] dataFromXL = null;
        excelDataConsumer = new ExcelDataConsumerHelper (xlFilePath);

        /*Get rows and column on the sheet*/
        int rows = excelDataConsumer.getRowCount(sheetName);
        int columns = excelDataConsumer.getColumnCount(sheetName);

        dataFromXL = new Object[rows-1][columns];/*Rows-1 is to skip the header row in the sheet*/

        for(int i=1; i<rows; i++)/*start index from the second row*/
        {
            for(int j=0; j<columns; j++)
            {
                dataFromXL[i-1][j] = excelDataConsumer.getCellData(sheetName,j,i);
            }
        }
        return dataFromXL;
    }
}
