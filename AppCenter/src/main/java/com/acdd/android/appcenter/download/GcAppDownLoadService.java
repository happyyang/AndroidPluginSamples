/*
 * ACDDExtension Project
 * file GcAppDownLoadService.java  is  part of ACDDExtension
 * The MIT License (MIT)  Copyright (c) 2015 Bunny Blue.
 *
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.acdd.android.appcenter.download;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.acdd.android.appcenter.R;


public class GcAppDownLoadService extends Service {
	class  BindImpl extends Binder{
		
	}
	@Override
	public void onCreate() {
		System.out.println("GcAppDownLoadService.onCreate()");
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("GcAppDownLoadService.onBind()");
		return new BindImpl();
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("GcAppDownLoadService.onStartCommand()"+this.getBaseContext().getClass().getCanonicalName());
		//addAppNotify(this);
		return super.onStartCommand(intent, flags, startId);
	}

	public  void addAppNotify(Context context) {
		NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification();
		notification.icon = R.drawable.ic_send; //通知图标
		notification.tickerText = context.getResources().getString(R.string.app_tickerText); //通知的内容
		notification.defaults = Notification.DEFAULT_SOUND; //通知的铃声
		notification.audioStreamType = android.media.AudioManager.ADJUST_LOWER;
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		Intent intent = new Intent();
		intent.setComponent(new ComponentName("com.acdd.lunacher", "com.acdd.android.appcenter.main.GcContainerActivity"));
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
		// 点击状态栏的图标出现的提示信息设置
//	点击状态栏的图标出现的提示信息设置	notification.setLatestEventInfo(context, context.getResources().getString(R.string.app_notify_title),
//				context.getResources().getString(R.string.app_notify), pendingIntent);
		manager.notify(1, notification); //这个函数中第一个参数代表identifier.如果要同时弹出多条通知，每个通知的这个参数必须不同。
	}
}
