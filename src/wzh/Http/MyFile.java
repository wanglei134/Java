package wzh.Http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Hongten
 * 
 * @time 2011-12-12 2011
 */
public class MyFile {
    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        MyFile myFile = new MyFile();
        try {
            for (int i = 0; i < 5; i++) {
                myFile.creatTxtFile("result");
                myFile.writeTxtFile("��ʾ����׷�ӵ���Ϣ" + i);
                String str = myFile.readDate();
                System.out.println("*********\n" + str);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static String path = "d:\\";
    private static String filenameTemp;

    /**
     * �����ļ�
     * 
     * @throws IOException
     */
    public static boolean creatTxtFile(String name) throws IOException {
        boolean flag = false;
        filenameTemp = path + name + ".txt";
        File filename = new File(filenameTemp);
        if (!filename.exists()) {
            filename.createNewFile();
            flag = true;
        }
        return flag;
    }

    /**
     * д�ļ�
     * 
     * @param newStr
     *            ������
     * @throws IOException
     */
    public static boolean writeTxtFile(String newStr) throws IOException {
        // �ȶ�ȡԭ���ļ����ݣ�Ȼ�����д�����
        boolean flag = false;
        String filein = newStr + "\r\n";
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            // �ļ�·��
            File file = new File(filenameTemp);
            // ���ļ�����������
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            // ������ļ�ԭ�е�����
           while((temp = br.readLine()) != null) {
                buf = buf.append(temp);
                // System.getProperty("line.separator")
                // ������֮��ķָ��� �൱�ڡ�\n��
                buf = buf.append(System.getProperty("line.separator"));
            }
            buf.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            flag = true;
        } catch (IOException e1) {
            // TODO �Զ����� catch ��
            throw e1;
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return flag;
    }

    /**
     * ��ȡ����
     */
    public void readData1() {
        try {
            FileReader read = new FileReader(filenameTemp);
            BufferedReader br = new BufferedReader(read);
            String row;
            while ((row = br.readLine()) != null) {
                System.out.println(row);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readDate() {
        // ����һ�������صĿ��ַ���
        String strs = "";
        try {
            FileReader read = new FileReader(new File(filenameTemp));
            StringBuffer sb = new StringBuffer();
            char ch[] = new char[1024];
            int d = read.read(ch);
            while (d != -1) {
                String str = new String(ch, 0, d);
                sb.append(str);
                d = read.read(ch);
            }
            read.close();
            System.out.print(sb.toString());
            String a = sb.toString().replaceAll("@@@@@", ",");
            strs = a.substring(0, a.length() - 1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strs;
    }
}