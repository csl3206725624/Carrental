/**
 * 
 */
package com.bd.rj.utils.TimerTask;

import java.util.Timer;
import java.util.TimerTask;

import com.itextpdf.text.log.SysoCounter;

/**
 * @desc
 * @author DELL
 * @time 2019年12月26日下午7:57:25
 */
public class MyTimerTask extends TimerTask{
public Timer timer;
public int count;
 public MyTimerTask(Timer timer) {
	this.timer=timer;
}

 /**
  * @desc 控制答应
  * 通过count来实现控制打印的次数，timer对象是将来要取消定时器
  * Timer：是定时器
  * schedule():任务调度
  *    参数：TimerTask：定时任务类，就是具体干什么
  *    long delay:1秒之后开始执行
  *    long period：以后每次都间隔“2”秒执行一回
  */
	@Override
	public void run() {
		System.out.println("执行一次");
		count++;
		if(count==3){
			System.out.println("程序结束");
			timer.cancel();//取消定时器
		}
		
	}

}
