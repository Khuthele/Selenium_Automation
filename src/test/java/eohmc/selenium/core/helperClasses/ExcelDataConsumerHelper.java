package eohmc.selenium.core.helperClasses;


import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;/*import com.microsoft.schemas.office.visio.x2012.main.CellType;*/
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by XY40224 on 07/08/2017.
 */
public class ExcelDataConsumerHelper
{

    public FileInputStream fis = null;
    public FileOutputStream fos = null;
    public XSSFWorkbook workbook = null;
    public XSSFSheet sheet = null;
    public XSSFRow row = null ;
    public XSSFCell cell = null;
    String xlFilePath;

    /* Load the excel Workbook*/
    public ExcelDataConsumerHelper(String xlFilePath) throws Exception{

        this.xlFilePath = xlFilePath;
        fis = new FileInputStream(xlFilePath);
        workbook = new XSSFWorkbook(fis);
        fis.close();
    }


    public String getCellData(String sheetName,int columnNumber,int rowNumber)
    {
        try
        {
         sheet = workbook.getSheet(sheetName);
         row = sheet.getRow(rowNumber);
         cell = row.getCell(columnNumber);

         //GET DATA IN THE CORRECT FORMAT
            /*Get Strings from cell*/
         if(cell.getCellTypeEnum()==CellType.STRING)
             return cell.getStringCellValue();
         else if(cell.getCellTypeEnum()==CellType.NUMERIC || cell.getCellTypeEnum()==CellType.FORMULA) /*If number and/or formula*/
         {
             /*Get Numbers from cell*/
             //String cellValue = String.valueOf(cell.getNumericCellValue());
             String cellValue = String.valueOf((int)cell.getNumericCellValue());

             /*Get Dates in current format from cell*/
             if (HSSFDateUtil.isCellDateFormatted(cell)) {
                 DateFormat df = new SimpleDateFormat("dd/MM/yy");
                 Date date = cell.getDateCellValue();
                 cellValue = df.format(date);
             }
             return cellValue;

         }else if (cell.getCellTypeEnum() == CellType.BLANK)/*Return blanks*/
             return "";

         else /*TODO: Add more formats if any  e.g boolean etc...*/
             return String.valueOf(cell.getBooleanCellValue());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "row "+rowNumber+" or column "+columnNumber +" does not exist in excel";
        }
    }

    public int getRowCount(String sheetName)
    {
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum()+1;
        return rowCount;
    }

    public int getColumnCount(String sheetName)
    {
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);
        int colCount = row.getLastCellNum();
        return colCount;
    }
}
