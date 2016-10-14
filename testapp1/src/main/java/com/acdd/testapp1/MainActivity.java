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

package com.acdd.testapp1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.acdd.testapp1.R.layout.content_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.acdd.testapp1.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.acdd.testapp1.R.id.action_settings) {
            Intent intent=new Intent("com.acdd.testapp2.MainActivity.Interface");
            Log.d("com.acdd.testapp1","start Interface as Intent");
            startActivity(intent);
            return true;
        }else if(id == com.acdd.testapp1.R.id.action_on_cls){
            Intent intent=new Intent();
            intent.setClassName(this,"com.acdd.testapp2.MainActivity");

            Log.d("com.acdd.testapp1","start MainActivity non-intent");
            startActivity(intent);
        }else if (id== com.acdd.testapp1.R.id.action_rec_cls){
            Intent intent=new Intent();
            intent.setClassName(this, "com.acdd.testapp2.TestApp2Receiver");
            sendBroadcast(intent);
            Log.d("com.acdd.testapp1", "start TestApp2Receiver non-intent");
        }else if(id== com.acdd.testapp1.R.id.action_rec_action){
            Intent intent=new Intent("com.acdd.testapp2.Message");
            sendBroadcast(intent);
            Log.d("com.acdd.testapp1","start v as Intent");
        }


        return super.onOptionsItemSelected(item);
    }
}
