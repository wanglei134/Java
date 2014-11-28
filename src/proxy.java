import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class proxy extends Thread {
	String yearString;
	String pro;
	int i;
	String fileString;
	public proxy(String yearString,String pro,int i,String fileString)
	{
		this.yearString=yearString;
		this.pro=pro;
		this.i=i;
		this.fileString=fileString;
	}
	public void run()
	{
		readHtml.getHtml(yearString,pro,i+"","data/"+i+".txt");
		StringBuffer html=readTxt.readTxtFile("data/"+i+".txt");
		int start=html.indexOf("/table");
		String xString=html.substring(start+7);		
		List<information> info=htmlParse.parse(new StringBuffer(xString));
		String [] excel=null;
		information data=null;
		Iterator<information> iterator=info.iterator();
		while(iterator.hasNext())
		{
			data=iterator.next();
			excel=new String[9];
			excel[0]=data.getId();
			excel[1]=data.getSchool();
			excel[2]=data.getMajor();
			excel[3]=data.getSyd();
			excel[4]=data.getWenliType();
			excel[5]=data.getYear();
			excel[6]=data.getPici();
			excel[7]=data.getPjf();
			try {
				Excel.pushData(excel, fileString);
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
	}

}
