package unity;
import java.io.File;
import java.io.IOException;

import javax.swing.SwingUtilities;

import frame.CompareFrame;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
public class Excel {
	private static File hasFile;
	String [] str1;
	String fileName;
	String url;
	public Excel(String [] str1,String fileName,String url)
	{
		this.str1=str1;
		this.fileName=fileName;
		this.url=url;
	}

	/**
	 * 同步操作，防止并发。
	 * 
	 * @param args
	 * @return
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public synchronized static String[] write(String[] args)
			throws IOException, RowsExceededException, WriteException {

		// 文件路径
		// 判断文件是否存在，如果存在就不创建，追加，如果不存在则创建文件并追加。
		WritableWorkbook book = Workbook.createWorkbook(new File("c://FailedResults.xls"));
		//book.setProtected(true);
		// -- 第一个参数是Sheet名，第二个参数是Sheet下标
		// -- 下标是整数，只起标识作用，建立的时候会以create顺序建立，本例生成的EXCEL文件第一个Sheet是sheet1
		WritableSheet sheet = book.createSheet("Sheet1", 1);
		//sheet.setColumnView(0, 20);
		//sheet.setColumnView(1, 20);
		//sheet.setColumnView(2, 5);
		//sheet.setColumnView(3, 20);
		//sheet.setColumnView(4, 20);		
		//Id，Title, Author, Keywords, Abstract

		String[] title = { "FieldName",CompareFrame.getCongig1().getSelectedItem().toString(),CompareFrame.getConfig2().getSelectedItem().toString()};
		//String[] title={"1","2","3"};
		//写标题
		for (int i = 0; i < title.length; i++) {
			Label lable = new Label(i, 0, title[i]);
			sheet.addCell(lable);
		}
		//写第一次传过来的数据
		for (int i = 0; i < args.length; i++) {
			Label lable = new Label(i, 1, args[i]);
			sheet.addCell(lable);
		}
		// 每次写入数据时，写到最后一行。
		book.write();
		book.close();
		return null;
	}

	/**
	 * 追加excel
	 * 
	 * @param args
	 * @throws IOException
	 * @throws BiffException
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	public static void addExcel(File file, String[] args) throws BiffException,
			IOException, RowsExceededException, WriteException {
		Workbook book = Workbook.getWorkbook(file);
		Sheet sheet = book.getSheet(0);
		// 获取行
		int length = sheet.getRows();
		WritableWorkbook wbook = Workbook.createWorkbook(file, book); // 根据book创建一个操作对象
		WritableSheet sh = wbook.getSheet(0);// 得到一个工作对象
		// 从最后一行开始加
		for (int i = 0; i < args.length; i++) {
			Label label = new Label(i, length, args[i]);
			sh.addCell(label);
		}
		int k=sheet.getRows();;
		System.out.println("第--"+k+"--条记录");
		//MainThread.setCurrentCount(MainThread.getCurrentCount()+1);	
		wbook.write();
		wbook.close();
	}

	/**
	 * 判断文件是否已经写入
	 * 
	 * @param filename
	 * @return
	 */
	public static boolean filecheck(String filename) {
		boolean flag = false;
		File file = new File(filename);
		if (file.exists()) {
			flag = true;
		}
		setHasFile(file);
		return flag;
	}

	/**
	 * 不管神马类型，都转换成string
	 * 
	 * @param obj
	 * @return
	 */
	public static String converToString(Object obj) {
		return "";
	}
	public static void makeDir(File dir) {   
		        if(! dir.exists()) {   
		            dir.mkdir(); 
		       }   	    
		    }   

	public static void main(String [] args)
	{
		String [] strings={"1","0","3"};
		String filenameString="file";
		try {
			pushData(strings);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static synchronized  void pushData(String [] str1) throws RowsExceededException,
			WriteException, IOException, BiffException, InterruptedException {
		
		//makeDir(new File("d://Data/Excel/"));
		String filepath ="c://FailedResults.xls";
		String[] str = str1;
		File file=new File(filepath);
		if(!file.exists())
		{
			file.createNewFile();
			write(str);
		}else {
			addExcel(file, str);
		}
		// 如果存在
		//if (has)
			//addExcel(getHasFile(), str);
			
		//else {
			
		//}
	}
	/**
	 * @return the hasFile
	 */
	public static File getHasFile() {
		return hasFile;
	}

	/**
	 * @param hasFile
	 *            the hasFile to set
	 */
	public static void setHasFile(File hasFile) {
		Excel.hasFile = hasFile;
	}

}