package progress;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class readTxt {
	public static Integer [] readTxtFile(String filePath){ 
		 Integer [] lineTxt=new Integer[17000];
        try { 
                String encoding="utf-8"; 
                File file=new File(filePath); 
                if(file.isFile() && file.exists()){ 
                    InputStreamReader read = new InputStreamReader( 
                    new FileInputStream(file),encoding);
                    BufferedReader bufferedReader = new BufferedReader(read);  
                    String x=null;
                    int count=0;
                    while((x = bufferedReader.readLine()) != null){ 
                       lineTxt[count++]=Integer.parseInt(x);
                    } 
                    read.close(); 
        }else{ 
            System.out.println("文件不存在!"); 
        } 
        } catch (Exception e) { 
            System.out.println("读取文件失败!"); 
            e.printStackTrace(); 
        } 
      return lineTxt;
    } 
	public static void main(String [] args)
	{
		 Integer [] lineTxt=readTxtFile("d://1.txt");
		 for(int i=1;i<1658;i++)
		 {
			 if(lineTxt[10*i-1]!=i)
			 {
				 System.out.println(i);
			 }
		 }
		
		
	}
}
