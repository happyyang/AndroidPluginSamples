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

package com.acdd.android.game2.download;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

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
		System.out.println("GcAppDownLoadService.onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}
}
