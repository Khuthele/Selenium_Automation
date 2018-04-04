package eohmc.selenium.core.helperClasses;
import org.testng.annotations.DataProvider;

/**
 * Created by XY40224 on 07/08/2017.
 */
public class DataProviderHelper extends BaseClassHelper
{
	/*Object To Read Data From Excel*/
    ExcelDataConsumerHelper  excelDataConsumer = null;

    //Call the inputTestDataFile method to access the data provider file
    String xlFilePath = currentPath + getConfig().getInputDataFile();
    String sheetName =  getConfig().getTestDataSheet();
	
	//Create & Initialize an Object of the Object array
	Object[][] data = null;
    Object[][] dataFromXL = null;

    @DataProvider(name = "taxCalculatorData")
    public Object[][] taxCalculatorData()
	{
		try
		{
          data = testData(xlFilePath,sheetName);
		  
		  /*Get number of rows*/
          intRowCount = (data.length);
		}
		catch(Exception ex)
		{
		  System.out.println("[ERROR] - Data Exception test data: " + ex.getMessage());
		}
		return data;
	}

	@DataProvider(name = "registerForAccountData")
	public Object[][] registerForAccountData()
	{
		try
		{
			data = testData(xlFilePath,sheetName);

		  /*Get number of rows*/
			intRowCount = (data.length);
		}
		catch(Exception ex)
		{
			System.out.println("[ERROR] - Data Exception test data: " + ex.getMessage());
		}
		return data;
	}

	@DataProvider(name = "CDPLoginDetailsData")
	public Object[][] CDPLoginDetailsData()
	{
		try
		{
			data = testData(xlFilePath,sheetName);

		  /*Get number of rows*/
			intRowCount = (data.length);
		}
		catch(Exception ex)
		{
			System.out.println("[ERROR] - Data Exception test data: " + ex.getMessage());
		}
		return data;
	}


	//Create a constructor method to load the workbook(Generic method)
    public Object[][] testData(String xlFilePath, String sheetName) 
    {
		try
		{
			excelDataConsumer = new ExcelDataConsumerHelper (xlFilePath);

			/*Get rows and column on the sheet*/
			int rows = excelDataConsumer.getRowCount(sheetName);
			int columns = excelDataConsumer.getColumnCount(sheetName);

		   /*Rows-1 is to skip the header row in the sheet*/
			dataFromXL = new Object[rows-1][columns];
			
		   /*start index from the second row*/
		   for(int i=1; i<rows; i++)
		   {
			  /*start index from the first row in order to count the column headers*/
			  for(int j=0; j<columns; j++)
			  {
				dataFromXL[i-1][j] = excelDataConsumer.getCellData(sheetName,j,i);
			  }
		   }
	   }
	   catch(Exception ex)
	   {
		  System.out.println("[ERROR] - Data from workbook Exception: " + ex.getMessage());
	   }
        return dataFromXL;
   }
}
