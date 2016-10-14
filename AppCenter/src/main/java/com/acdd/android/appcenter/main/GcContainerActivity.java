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

package com.acdd.android.appcenter.main;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.acdd.android.appcenter.GameReceiver;
import com.acdd.android.appcenter.R;
import com.acdd.android.appcenter.download.DataSer;
import com.acdd.android.appcenter.download.GcAppDownLoadService;

import butterknife.Bind;
import butterknife.ButterKnife;


public class GcContainerActivity extends Activity {
    @Bind(R.id.btnButton)
    Button btnButton;
    @Bind(R.id.btnRegReceiver)
    Button btnRegReceiver;
    @Bind(R.id.btnUnRegReceiver)
    Button btnUnRegReceiver;
    @Bind(R.id.btnSendDY)
    Button btnSendDY;
    @Bind(R.id.btnSendSTATIC)
    Button btnSendSTATIC;
    @Bind(R.id.btnStartAct)
    Button btnStartAct;
    @Bind(R.id.btnStartAct2)
    Button btnStartAct2;
    private ServiceConnection e;
    GameReceiver mGameReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("AppCenter20160324");
        System.out.println("AppCenter20160324-->>>ClassLoader:"+getClassLoader().getClass().getName());
        ButterKnife.bind(this);

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
                if (mGameReceiver == null) {
                    mGameReceiver = new GameReceiver();
                    IntentFilter mFilter = new IntentFilter("com.tmp.msg");

                    registerReceiver(mGameReceiver, mFilter);
                    Log.d(getClass().getSimpleName(), "registerReceiver done");
                } else {
                    Log.d(getClass().getSimpleName(), "registerReceiver repeat");
                }


            }
        });
        btnUnRegReceiver.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mGameReceiver != null) {
                    unregisterReceiver(mGameReceiver);
                    mGameReceiver = null;
                    Log.d(getClass().getSimpleName(), "unregisterReceiver done");
                } else {
                    Log.d(getClass().getSimpleName(), "unregisterReceiver error");
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
                Intent mIntent = new Intent("com.acdd.android.appcenter.GameStaticReceiver");
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
        btnStartAct2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(GcContainerActivity.this, ActivityInternalActivity.class);
                mIntent.putExtra("Serializable", new DataSer());
                startActivity(mIntent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
