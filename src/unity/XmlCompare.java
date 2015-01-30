package unity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.security.auth.login.FailedLoginException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import frame.CompareFrame;
import function.funImple;

public class XmlCompare {
	public  static ArrayList<String> uuid1,uuid2=new ArrayList<String>();
	public static ArrayList<String> failedSetNameArrayListOfSet1=new ArrayList<String>();
	public static ArrayList<String> failedSetNameArrayListOfSet2=new ArrayList<String>();
	private static XmlCompare xmlInstance=null;
	private XmlCompare(){
		
	}
	public static XmlCompare getInstance(ArrayList<String> id1,ArrayList<String> id2){
		if(xmlInstance==null)
			{
				xmlInstance=new XmlCompare();
			}
		uuid1=id1;
		uuid2=id2;
		return xmlInstance;
	}
//	public XmlCompare(ArrayList<String> uuid1,ArrayList<String> uuid2)
//	{
//		this.uuid1=uuid1;
//		this.uuid2=uuid2;		
//	}
	public void Check()
	{
		new Thread(
				new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						ConcurrentHashMap<String,String> xmlData1=new ConcurrentHashMap<String,String>();
						ConcurrentHashMap<String,String> xmlData2=new ConcurrentHashMap<String,String>();
						ConcurrentHashMap<String,String> xmlDataA=new ConcurrentHashMap<String,String>();
						ConcurrentHashMap<String,String> xmlDataB=new ConcurrentHashMap<String,String>();			
						try {	
							funImple f=new funImple();
							for (String id : uuid1) {
								String x=f.GetXml(id);
								xmlData1.put(id,x);
								xmlDataA.put(id, x);
							}			
							for (String id : uuid2) {
								xmlData2.put(id,f.GetXml(id));
								xmlDataB.put(id, f.GetXml(id));
							}				
							Iterator it1=xmlData1.entrySet().iterator();			
							//正序
							int i=1;
							while(it1.hasNext())
							{
								System.out.println("第"+i+++"次");
								Map.Entry<String, String> e1=(Entry<String, String>) it1.next();
								String xml1=e1.getValue();
								String id1=e1.getKey();
								Boolean flag=false;
								Iterator it2=xmlData2.entrySet().iterator();
								while(it2.hasNext())
								{
									Map.Entry<String, String> e2=(Entry<String, String>) it2.next();
									String xml2=e2.getValue();
									String key=e2.getKey();
									if(xml1.equals(xml2))
									{
										flag=true;	
										System.out.println("Pass");
										xmlData2.remove(key);
										break;																
									}
									
								}
								if(flag==false)
								{
									System.out.println("Faile"+f.GetSetName(id1));
									failedSetNameArrayListOfSet1.add(f.GetSetName(id1));
								}
							}
							
							//倒序
							Iterator secondIterator=xmlDataB.entrySet().iterator();
							int j=1;
							while(secondIterator.hasNext())
							{
								System.out.println("第"+j+++"次");
								Map.Entry<String, String> e2=(Entry<String, String>) secondIterator.next();
								String xml2=e2.getValue();
								String id2=e2.getKey();
								Boolean flag=false;
								Iterator it11=xmlDataA.entrySet().iterator();
								while(it11.hasNext())
								{
									Map.Entry<String, String> e1=(Entry<String, String>) it11.next();
									String xml1=e1.getValue();
									String key=e1.getKey();
									if(xml2.equals(xml1))
									{
										System.out.println("Pass");
										flag=true;
										xmlDataA.remove(key);
										break;
									}
								}
								if(flag==false)
								{
									//System.out.println(f.GetSetName(id2));
									failedSetNameArrayListOfSet2.add(f.GetSetName(id2));
								}
							}		
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						if(failedSetNameArrayListOfSet1.size()==0&&failedSetNameArrayListOfSet2.size()==0)
						{
							//success
							CompareFrame.getLabelResult().setText("Result:Success!");
						}else {
							//remove before
							CompareFrame.getSet1ToCompare().removeAllItems();
						    CompareFrame.getResult().setText("");
							//
							CompareFrame.getLabelResult().setText("Result:"+(failedSetNameArrayListOfSet1.size()+failedSetNameArrayListOfSet2.size())+" Failed!");
							CompareFrame.getResult().append(CompareFrame.getCongig1().getSelectedItem().toString()+"\n\n");
							for (String s : failedSetNameArrayListOfSet1) {
								CompareFrame.getResult().append(s+"\n");
								CompareFrame.getSet1ToCompare().addItem(s);
							}
							
							CompareFrame.getResult().append("\n"+CompareFrame.getConfig2().getSelectedItem().toString()+"\n\n");
							for (String s : failedSetNameArrayListOfSet2) {
								System.out.println(s);
								CompareFrame.getResult().append(s+"\n");
								//CompareFrame.getSet2ToCompare().addItem(s);
							}
							
							
					}
				}
				}
				).start();
		
		
	}
}
