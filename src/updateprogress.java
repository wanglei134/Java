import javax.swing.JProgressBar;


public class updateprogress extends Thread {
	private int k;
	private JProgressBar bar;
	public  updateprogress(int k,JProgressBar bar)
	{
		this.k=k;
		this.bar=bar;
	}
	public void run()
	{
		if(this.k%10==0)
		{
			this.bar.setValue(this.bar.getValue()+1);
		}
	}

}
