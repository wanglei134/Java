
import java.io.File;
import java.io.IOException;
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
	 * ͬ����������ֹ������
	 * 
	 * @param args
	 * @return
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public synchronized static String[] write(String[] args)
			throws IOException, RowsExceededException, WriteException {

		// �ļ�·��
		// �ж��ļ��Ƿ���ڣ������ھͲ�������׷�ӣ��������򴴽��ļ���׷�ӡ�
		WritableWorkbook book = Workbook.createWorkbook(getHasFile());
		//book.setProtected(true);
		// -- ��һ��������Sheet��ڶ���������Sheet�±�
		// -- �±�������ֻ���ʶ���ã�������ʱ�����create˳������������ɵ�EXCEL�ļ���һ��Sheet��sheet1
		WritableSheet sheet = book.createSheet("Sheet1", 1);
		//sheet.setColumnView(0, 20);
		//sheet.setColumnView(1, 20);
		//sheet.setColumnView(2, 5);
		//sheet.setColumnView(3, 20);
		//sheet.setColumnView(4, 20);		
		//Id��Title, Author, Keywords, Abstract

		String[] title = {"序号", "院校名称", "专业名称", "生源地", "文理科", "年份","批次","平均分"};
		//д����
		for (int i = 0; i < title.length; i++) {
			Label lable = new Label(i, 0, title[i]);
			sheet.addCell(lable);
		}
		//д��һ�δ����������
		for (int i = 0; i < args.length; i++) {
			Label lable = new Label(i, 1, args[i]);
			sheet.addCell(lable);
		}
		// ÿ��д�����ʱ��д�����һ�С�
		book.write();
		book.close();
		return null;
	}

	/**
	 * ׷��excel
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
		// ��ȡ��
		int length = sheet.getRows();
		WritableWorkbook wbook = Workbook.createWorkbook(file, book); // ���book����һ����������
		WritableSheet sh = wbook.getSheet(0);// �õ�һ����������
		// �����һ�п�ʼ��
		for (int i = 0; i < args.length; i++) {
			Label label = new Label(i, length, args[i]);
			sh.addCell(label);
		}
		int k=sheet.getRows();;
		System.out.println("第--"+k+"--条数据");
		
		//MainThread.setCurrentCount(MainThread.getCurrentCount()+1);	
		wbook.write();
		wbook.close();
	}

	/**
	 * �ж��ļ��Ƿ��Ѿ�д��
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
	 * �����������ͣ���ת����string
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
		String [] strings={"1","2","3","4","5"};
		String filenameString="file";
		try {
			pushData(strings, filenameString);
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
	public static synchronized  void pushData(String [] str1,String fileName) throws RowsExceededException,
			WriteException, IOException, BiffException, InterruptedException {
		
		makeDir(new File("d://data/"));
		String filepath ="d://data/"+fileName+".xls";
		String[] str = str1;
		boolean has = Excel.filecheck(filepath);
		// ������
		if (has)
			addExcel(getHasFile(), str);
		else {
			write(str);
		}
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