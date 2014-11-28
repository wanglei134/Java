package org.lq.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



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
		//StringBuffer buffer=readTxt.readTxtFile("d://1.txt");
		//System.out.println(buffer);
		
		System.out.println(0%1500);
	}
	public static int getCount(String html)
	{
		//int a=0;
		String str=html.toString();
		int start=html.toString().indexOf("Ìõ¼ÇÂ¼");
		if(start==-1)
			return 0;
		else
			{
				str=str.substring(start+7, start+15);
				int i=str.indexOf("/");
				int j=str.indexOf("Ò³");
				str=str.substring(i+1,j);
				System.out.println(str);
			}
		return Integer.parseInt(str);
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
				for(int j = 1 ; j < table.getRowCount(); j++)
				{
				data=new information();
				TableRow row = table.getRow(j);
				TableColumn[] columns = row.getColumns();				
				for (int k = 1; k < columns.length; k++) {
					System.out.println(k);					
					data.setSchool(columns[1].toPlainTextString().trim());	
					data.setMajor(columns[2].toPlainTextString().trim());
					data.setRank(columns[3].toPlainTextString().trim());
					data.setScore(columns[4].toPlainTextString().trim());
					data.setTotal(columns[5].toPlainTextString().trim());
					data.setWenliType(columns[6].toPlainTextString().trim());
					data.setBzType(columns[7].toPlainTextString().trim());
					data.setPici(columns[8].toPlainTextString().trim());
					data.setYear(columns[9].toPlainTextString().trim());
					
				}	
				info.add(data);
				System.out.println();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(info.size());
		Iterator<information> irIterator=info.iterator();
		while(irIterator.hasNext())
		{
			data=irIterator.next();
			System.out.println(data.getSchool()+"--"+data.getScore());
		}
		return info;
	}

}
