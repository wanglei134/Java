package progress;

import javax.swing.JProgressBar;

public class update extends Thread {
	private JProgressBar bar;
	public update(JProgressBar bar)
	{
		this.bar=bar;
	}
	public void run()
	{
		try {
			while(this.bar.getValue()<100)
			{
			Thread.sleep(1000);
			this.bar.setValue(this.bar.getValue()+1);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
