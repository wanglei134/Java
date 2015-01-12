/*
 * 
 * book类，存储书籍的基本信息(包含书籍名字，库存，借出数量，和归还数量)
 */
public class book {
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	private int count;
	private int jiechu;
	public int getJiechu() {
		return jiechu;
	}
	public void setJiechu(int jiechu) {
		this.jiechu = jiechu;
	}
	public int getGuihuan() {
		return guihuan;
	}
	public void setGuihuan(int guihuan) {
		this.guihuan = guihuan;
	}
	private int guihuan;
}
