package org.lq.util;

import java.util.HashMap;

public class paras {
	public static HashMap<String, String> syd=new HashMap<String, String>();
	public static HashMap<String, String> getSyd() {
		return syd;
	}
	public static HashMap<String, String> getKl() {
		return kl;
	}
	public static HashMap<String, String> getXl() {
		return xl;
	}
	public static HashMap<String, String> getNf() {
		return nf;
	}
	public static HashMap<String, String> kl=new HashMap<String, String>();
	public static HashMap<String, String> xl=new HashMap<String, String>();
	public static HashMap<String, String> nf=new HashMap<String, String>();
	static {
		// TODO Auto-generated constructor stub
		syd.put("北京", "110000");
		syd.put("天津", "120000");
		syd.put("河北", "130000");
		syd.put("山西", "140000");
		syd.put("内蒙古", "150000");
		syd.put("辽宁", "210000");
		syd.put("吉林", "220000");
		syd.put("黑龙江", "230000");
		syd.put("上海", "310000");
		syd.put("江苏", "320000");
		syd.put("浙江", "330000");
		syd.put("安徽", "340000");
		syd.put("福建", "350000");
		syd.put("江西", "360000");
		syd.put("山东", "370000");
		syd.put("河南", "410000");
		syd.put("湖北", "420000");
		syd.put("湖南", "430000");
		syd.put("广东", "440000");
		syd.put("广西", "450000");
		syd.put("海南", "460000");
		syd.put("重庆", "500000");
		syd.put("四川", "510000");
		syd.put("贵州", "520000");
		syd.put("云南", "530000");
		syd.put("西藏", "540000");
		syd.put("陕西", "610000");
		syd.put("甘肃", "620000");
		syd.put("青海", "630000");
		syd.put("宁夏", "640000");
		syd.put("新疆", "650000");
		
		kl.put("全部", "0");
		kl.put("文科", "1");
		kl.put("理科", "5");
		
		xl.put("全部", "0");
		xl.put("本科", "1");
		xl.put("专科", "2");
		
		nf.put("全部", "0");
		nf.put("2013", "2013");
		nf.put("2012", "2012");
		nf.put("2011", "2011");
		nf.put("2010", "2010");
		
	}
}
