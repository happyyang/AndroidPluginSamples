/*
 * ACDDExtension Project
 * file ActivityInternalActivity.java  is  part of ACDDExtension
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
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.acdd.android.game2.GameProvider;
import com.acdd.android.game2.R;


public class ActivityInternalActivity extends Activity {
Button btnInsert;
Button btnQuery;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal);
		btnInsert=(Button) findViewById(R.id.btnInsert);
		btnQuery=(Button) findViewById(R.id.btnQuery);
		btnInsert.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				insertRecord("uname"+System.currentTimeMillis());
				
			}
		});
		btnQuery.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				displayRecords();
				
			}
		});
	}


	private void insertRecord(String userName) {
		ContentValues values = new ContentValues();
		values.put("USER_NAME", userName);
		getContentResolver().insert(GameProvider.AppProviderURI, values);
	}
	private void displayRecords() {
		String columns[] = new String[] { "_id","USER_NAME" };
		Uri myUri = GameProvider.AppProviderURI;
		Cursor cur = managedQuery(myUri, columns,null, null, null );
		if (cur.moveToFirst()) {
			String id = null;
			String userName = null;
			do {
				id = cur.getString(cur.getColumnIndex("_id"));
				userName = cur.getString(cur.getColumnIndex("USER_NAME"));
				System.out.println("ActivityInternalActivity.displayRecords() id "+id+" uname "+userName);
			} while (cur.moveToNext());
		}
	}
}
