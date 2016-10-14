/*
 * ACDDExtension Project
 * file MainActivity.java  is  part of ACDDExtension
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

package com.acdd.homelauncher;


import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.acdd.homelauncher.fragment.ScreenSlidePagerAdapter;

import org.acdd.framework.ACDD;
import org.acdd.hack.Reflect;
import org.osgi.framework.BundleException;

import java.io.File;
import java.lang.reflect.Field;


public class MainActivity extends FragmentActivity {
    /* The number of pages (wizard steps) to show in this demo.
    */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            ACDD.getInstance().checkBundleInstall("com.acdd.android.appcenter");
            Intent mDelye = new Intent();
            mDelye.setClassName(this, "com.acdd.android.appcenter.main.GcContainerActivity");
            startActivity(mDelye);

            return true;
        }else if (id==R.id.action_updateQR){
            File file=new File("/sdcard/app-debug.apk");
            if (!file.exists()){

                Toast.makeText(MainActivity.this, "QRCode Update  pkg not exist", Toast.LENGTH_SHORT).show();
            }
            try {
                ACDD.getInstance().updateBundle("cn.acdd.qrcode",file);
            } catch (BundleException e) {
                e.printStackTrace();
            }
        }else  if (id==R.id.action_about){

            AlertDialog.Builder mBuild=new AlertDialog.Builder(this);
            mBuild.setTitle("About");
            mBuild.setMessage("Home Version:1.0\nACDDCore ver:" + getVersionName() + "\n" +
                    "ACDDCore build:" + getVersionCode() + "\n" +
                    "Launcher Version:" + getLauncherVerName());
            mBuild.create().show();

        }else  if (id==R.id.action_nativeFragment){
            startActivity(new Intent(this,NativeFragmentActivity.class));
        }else if (id==R.id.action_daemon){
            startService(new Intent(this,DaemonService.class));
            Toast.makeText(MainActivity.this, "DaemonService started", Toast.LENGTH_SHORT).show();
            return  true;
        }


        return super.onOptionsItemSelected(item);
    }

    String getVersionName() {
        try {
            Class sdk = Class.forName("org.acdd.sdk.BuildConfig");
            Field mField = Reflect.getField(sdk, "VERSION_NAME");
            String verName = (String) mField.get(null);
            return verName;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "not found";
    }
    int getVersionCode() {
        try {
            Class sdk = Class.forName("org.acdd.sdk.BuildConfig");
            Field mField = Reflect.getField(sdk, "VERSION_CODE");
            int verName = mField.getInt(null);
            return verName;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 1;
    }
    String getCoreVersion() {
        try {
            Class sdk = Class.forName("org.acdd.sdk.BuildConfig");
            Field mField = Reflect.getField(sdk, "ACDDCoreVersion");
            String verName = (String) mField.get(null);
            return verName;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "not found";
    }
    String getLauncherVerName()  {
        try {
            return getPackageManager().getPackageInfo(getPackageName(),0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "no found";
    }
}
