package cn.dsx.baidu;

public class Test {
 
    public static void main(String[] args) {
        Baidu baidu = new Baidu();
        baidu.login("��������", "songlaipu123.");
        for(int i=0;i<10;i++){                 
        try {
            System.out.println(baidu.replyBuildingPost("4544044360","5572844", "1", "csgo��Ʒ����","4","89564570810","89564570810"));
           //4544044360|5572844|csgo��Ʒ����|4|89564570810
            Thread.sleep(10000);
        } catch (Exception e) {
            
        }
        }
 
    }
 
}