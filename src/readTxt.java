import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class readTxt {
	public static StringBuffer readTxtFile(String filePath){ 
		 StringBuffer lineTxt =new StringBuffer();
        try { 
                String encoding="utf-8"; 
                File file=new File(filePath); 
                if(file.isFile() && file.exists()){ 
                    InputStreamReader read = new InputStreamReader( 
                    new FileInputStream(file),encoding);
                    BufferedReader bufferedReader = new BufferedReader(read);  
                    String x=null;
                    while((x = bufferedReader.readLine()) != null){ 
                       lineTxt.append(x+"\n");
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
		System.out.println(readTxtFile("data/1.txt"));
	}
}
