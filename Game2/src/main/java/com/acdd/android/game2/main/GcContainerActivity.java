/*
 * ACDDExtension Project
 * file GcContainerActivity.java  is  part of ACDDExtension
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

package com.acdd.android.game2.main;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.acdd.android.game2.GameReceiver;
import com.acdd.android.game2.download.GcAppDownLoadService;
import com.acdd.android.game2.R;

public class GcContainerActivity extends Activity {
	private ServiceConnection e;
	GameReceiver mGameReceiver=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btnButton = (Button) findViewById(R.id.btnButton);
		Button btnRegReceiver = (Button) findViewById(R.id.btnRegReceiver);
		Button btnUnRegReceiver = (Button) findViewById(R.id.btnUnRegReceiver);
		Button btnSendDY = (Button) findViewById(R.id.btnSendDY);
		Button btnSendSTATIC = (Button) findViewById(R.id.btnSendSTATIC);
		Button btnStartAct = (Button) findViewById(R.id.btnStartAct);


		btnButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {


				try {
					Intent intent = new Intent(GcContainerActivity.this, GcAppDownLoadService.class);
					startService(intent);
					Toast.makeText(getApplication(), "GcAppDownLoadService is booting ", Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					e.printStackTrace();
				}


			}
		});

		btnRegReceiver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mGameReceiver==null)
				{
					mGameReceiver = new GameReceiver();
					IntentFilter mFilter = new IntentFilter("com.tmp.msg");

					registerReceiver(mGameReceiver, mFilter);
				}else {
					Log.d(getClass().getSimpleName(),"mGameReceiver is registered");
				}


			}
		});
		btnUnRegReceiver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mGameReceiver!=null){
					unregisterReceiver(mGameReceiver);
					mGameReceiver=null;
				}else
				{
					Log.d(getClass().getSimpleName(),"mGameReceiver is unregistered");
				}


			}
		});

		btnSendDY.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent mIntent = new Intent("com.tmp.msg");
				mIntent.putExtra("msg", "btnSendDY msg" + System.currentTimeMillis());
				sendBroadcast(mIntent);

			}
		});
		btnSendSTATIC.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent mIntent = new Intent("com.acdd.android.game2.GameStaticReceiver");
				mIntent.putExtra("msg", "btnSendSTATIC msg" + System.currentTimeMillis());
				sendBroadcast(mIntent);

			}
		});
		btnStartAct.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(GcContainerActivity.this, ActivityInternalActivity.class));

			}
		});
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
		}
		return super.onTouchEvent(event);
	}
}


