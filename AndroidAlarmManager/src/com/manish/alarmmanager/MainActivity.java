package com.manish.alarmmanager;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * @author manish
 *
 */

public class MainActivity extends Activity implements OnClickListener{

	Button btnStartAlarm,btnStopAlarm;
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context=MainActivity.this;
		
		btnStartAlarm=(Button)findViewById(R.id.button1);
		btnStopAlarm=(Button)findViewById(R.id.button2);
		
		btnStartAlarm.setOnClickListener(this);
		btnStopAlarm.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==btnStartAlarm){
			fireAlarm();
		}
		if(v==btnStopAlarm){
			stopAlarm();
		}
	}
	public void fireAlarm() {
		/**
		 * call broadcost reciver
		 */
		Intent intent = new Intent(context, AlarmReceiver.class);
		intent.setAction("com.manish.alarm.ACTION");
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 111, intent,0);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		AlarmManager alarm = (AlarmManager) MainActivity.this.getSystemService(Context.ALARM_SERVICE);
		alarm.cancel(pendingIntent);
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),100000, pendingIntent);
	}
	public void stopAlarm(){
		
		Intent intent = new Intent(context, AlarmReceiver.class);
		PendingIntent sender = PendingIntent.getBroadcast(context, 111, intent, 0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.cancel(sender);
	}


}
