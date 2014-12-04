package org.lq.util;

import java.io.UnsupportedEncodingException;
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
	public static void main(String [] args) throws UnsupportedEncodingException
	{
//		StringBuffer buffer=readTxt.readTxtFile("e://1.txt");	
//		System.out.println(getCount(buffer.toString()));
		//parse(buffer);
	}
	public static int getCount(String html) throws UnsupportedEncodingException
	{
		//int a=0;
		String str=html;
		int start=html.toString().indexOf("µ±Ç°Îª");
		str=html.substring(start,start+40);
		start=str.indexOf(">");
		int end=str.lastIndexOf("<");
		str=str.substring(start, end);
		start=str.indexOf("/");
		str=str.substring(start+1);
		return Integer.parseInt(str);
	}
	public static List<information> parse(String string)
	{
		List<information> info=new ArrayList<information>();
		information data=null;
		Parser parser=null;
		try {
		    parser = new Parser(string.toString());
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
					//System.out.println(k);	
					data.setMajor(columns[0].toPlainTextString().trim());
					data.setSchool(columns[1].toPlainTextString().trim());
					data.setZypjscore(columns[2].toPlainTextString().trim());
					data.setZyzgf(columns[3].toPlainTextString().trim());
					data.setLocation(columns[4].toPlainTextString().trim());
					data.setWenliType(columns[5].toPlainTextString().trim());	
					data.setYear(columns[6].toPlainTextString().trim());
					data.setPici(columns[7].toPlainTextString().trim());										
				}	
				info.add(data);
				//System.out.println();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		//System.out.println(info.size());
		return info;
	}
}
