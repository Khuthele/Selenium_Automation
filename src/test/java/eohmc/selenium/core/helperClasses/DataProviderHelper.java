package eohmc.selenium.core.helperClasses;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static eohmc.selenium.core.helperClasses.BaseClassHelper.currentPath;

/**
 * Created by XY40224 on 07/08/2017.
 */
public class DataProviderHelper extends BaseClassHelper
{

    ExcelDataConsumerHelper  excelDataConsumer = null;/*Object To Read Data From Excel*/
    String xlFilePath = currentPath +getConfig().getInputDataFile();
    String sheetName = "TestData";

    @DataProvider(name = "taxCalculator")
    public Object[][] userFirstTest() throws Exception {
        Object[][] data = testData(xlFilePath,sheetName);
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

        for(int i=1; i<rows; i++)
        {
            for(int j=0; j<columns; j++)
            {
                dataFromXL[i-1][j] = excelDataConsumer.getCellData(sheetName,j,i);
            }
        }
        return dataFromXL;
    }
}
