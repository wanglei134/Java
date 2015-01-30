package unity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JTextArea;

import frame.CompareFrame;
import function.funImple;
public class ScheduleJob {
	
	private static ArrayList<String> uuidlist1,uuidlist2=new ArrayList<String>();
	@SuppressWarnings("unchecked")
	public void InitConfigName()
	{
		@SuppressWarnings("rawtypes")
		JComboBox Config1,Config2;
		Config1=CompareFrame.getCongig1();
		Config2=CompareFrame.getConfig2();
		funImple funImple=new funImple();
		try {
			ArrayList<String> config=funImple.GetConfigName();
			for (String c : config) {
				Config1.addItem(c);
				Config2.addItem(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void InitSets() {
		new Thread(
				new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						String config1=CompareFrame.getCongig1().getSelectedItem().toString();
						String config2=CompareFrame.getConfig2().getSelectedItem().toString();
						JTextArea s1,s2=new JTextArea();
						s1=CompareFrame.getSet1();
						s2=CompareFrame.getSet2();
						funImple funImple=new funImple();
						try {
							String uuid1=funImple.GetUUid(config1);
							String uuid2=funImple.GetUUid(config2);			
							uuidlist1=funImple.GetAllSetUUid(uuid1);
							uuidlist2=funImple.GetAllSetUUid(uuid2);
							CompareFrame.getCount1().setText(uuidlist1.size()+"");
							CompareFrame.getCount2().setText(uuidlist2.size()+"");
							for (String id1 : uuidlist1) {
								s1.append(funImple.GetSetName(id1));
								s1.append("\n");
							}
							for (String id2 : uuidlist2) {
								s2.append(funImple.GetSetName(id2));
								s2.append("\n");
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				).start();
		
	}

	@SuppressWarnings("rawtypes")
	public void CompareXmlData() {		
		XmlCompare.getInstance(uuidlist1, uuidlist2).Check();
	}
}
