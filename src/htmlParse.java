

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;

import org.htmlparser.filters.NodeClassFilter;


import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;


public class htmlParse {
	public static void main(String [] args)
	{
		StringBuffer html=readTxt.readTxtFile("data/1.txt");
		int start=html.indexOf("/table");
		String xString=html.substring(start+7);		
		List<information> info=parse(new StringBuffer(xString));
		String [] excel=null;
		information data=null;
		Iterator<information> iterator=info.iterator();
		while(iterator.hasNext())
		{
			data=iterator.next();
			excel=new String[9];
			excel[0]=data.getSchool();
			excel[1]=data.getMajor();
			excel[2]=data.getSyd();
			excel[3]=data.getWenliType();
			excel[4]=data.getYear();
			excel[5]=data.getPici();
			excel[6]=data.getPjf();
			try {
				Excel.pushData(excel, "1");
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
	public static List<information> parse(StringBuffer html)
	{
		List<information> info=new ArrayList<information>();
		information data=null;
		Parser parser=null;
		try {
		    parser = new Parser(html.toString());
			parser.setEncoding("utf-8");
			NodeFilter filter = new NodeClassFilter(TableTag.class);
			NodeList list = parser.extractAllNodesThatMatch(filter);
			for(int i=0;i<list.size();i++)
			{
				TableTag table = (TableTag) list.elementAt(i);
				for(int j = 2 ; j < table.getRowCount(); j++)
				{
				data=new information();
				TableRow row = table.getRow(j);
				TableColumn[] columns = row.getColumns();				
				for (int k = 0; k < columns.length-1; k++) {
					System.out.println(k+"-----"+columns[k].toPlainTextString().trim());
					data.setId(columns[0].toPlainTextString().trim());
					data.setSchool(columns[1].toPlainTextString().trim());
					data.setMajor(columns[2].toPlainTextString().trim());
					data.setSyd(columns[3].toPlainTextString().trim());
					data.setWenliType(columns[4].toPlainTextString().trim());
					data.setYear(columns[5].toPlainTextString().trim());
					data.setPici(columns[6].toPlainTextString().trim());
					data.setPjf(columns[7].toPlainTextString().trim());
				}	
				info.add(data);
				System.out.println("------------");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		System.out.println(info.size());
		return info;
	}

}
