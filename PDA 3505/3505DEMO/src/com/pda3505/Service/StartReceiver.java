package com.pda3505.Service;

import com.pda3505.Service.CaptureService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartReceiver extends BroadcastReceiver {

	public static int times = 0;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
		
			//��������service
			Intent newIntent = new Intent(context, CaptureService.class);			    	 
	    	newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startService(newIntent);
			
		}
	}

}
