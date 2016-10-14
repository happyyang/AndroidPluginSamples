/*
 * ACDDExtension Project
 * file OGSdkResUtil.java  is  part of ACDDExtension
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

package com.acdd.qrcode.ext;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.util.Log;

import java.lang.reflect.Field;

public class OGSdkResUtil {
	private static OGSdkResUtil instance;
	private Context context;
	private static Class id = null;
	private static Class drawable = null;
	private static Class layout = null;
	private static Class anim = null;
	private static Class style = null;
	private static Class string = null;
	private static Class array = null;

	private OGSdkResUtil(Context paramContext) {
		this.context = paramContext.getApplicationContext();
		System.out.println("com.acdd.qrcode"+"<<<bunny  look "+this.context.getClass().getName());
		try {
			paramContext.getPackageName();
			drawable = Class.forName("com.acdd.qrcode" + ".R$drawable");
		} catch (ClassNotFoundException localClassNotFoundException1) {
			localClassNotFoundException1.printStackTrace();
		}
		try {
			layout = Class.forName("com.acdd.qrcode" + ".R$layout");
		} catch (ClassNotFoundException localClassNotFoundException2) {
			localClassNotFoundException2.printStackTrace();//OGSdkLogUtil.d(OGSdkLogUtil.TAG, "[ResUtil].ClassNotFoundException2 = " + localClassNotFoundException2.getMessage());
		}
		try {
			id = Class.forName("com.acdd.qrcode" + ".R$id");
		} catch (ClassNotFoundException localClassNotFoundException3) {
			localClassNotFoundException3.printStackTrace();//
			// 	OGSdkLogUtil.d(OGSdkLogUtil.TAG, "[ResUtil].ClassNotFoundException3 = " + localClassNotFoundException3.getMessage());
		}
		try {
			anim = Class.forName("com.acdd.qrcode" + ".R$anim");
		} catch (ClassNotFoundException localClassNotFoundException4) {
			localClassNotFoundException4.printStackTrace();;//	OGSdkLogUtil.d(OGSdkLogUtil.TAG, "[ResUtil].ClassNotFoundException4 = " + localClassNotFoundException4.getMessage());
		}
		try {
			style = Class.forName("com.acdd.qrcode" + ".R$style");
		} catch (ClassNotFoundException localClassNotFoundException5) {
			localClassNotFoundException5.printStackTrace();//	OGSdkLogUtil.d(OGSdkLogUtil.TAG, "[ResUtil].ClassNotFoundException5 = " + localClassNotFoundException5.getMessage());
		}
		try {
			string = Class.forName("com.acdd.qrcode" + ".R$string");
		} catch (ClassNotFoundException localClassNotFoundException6) {
			localClassNotFoundException6.printStackTrace();//	OGSdkLogUtil.d(OGSdkLogUtil.TAG, "[ResUtil].ClassNotFoundException6 = " + localClassNotFoundException6.getMessage());
		}
		try {
			array = Class.forName("com.acdd.qrcode" + ".R$array");
		} catch (ClassNotFoundException localClassNotFoundException7) {
			localClassNotFoundException7.printStackTrace();//	OGSdkLogUtil.d(OGSdkLogUtil.TAG, "[ResUtil].ClassNotFoundException7 = " + localClassNotFoundException7.getMessage());
		}
	}

	public static OGSdkResUtil getResofR(Context paramContext) {
		if (instance == null)
			instance = new OGSdkResUtil(paramContext);
		return instance;
	}

	public int getAnim(String paramString) {
		return getResofR(anim, paramString);
	}

	public int getId(String paramString) {
		return getResofR(id, paramString);
	}

	public int getDrawable(String paramString) {
		return getResofR(drawable, paramString);
	}

	public int getLayout(String paramString) {
		return getResofR(layout, paramString);
	}

	public int getStyle(String paramString) {
		return getResofR(style, paramString);
	}

	public int getString(String paramString) {
		return getResofR(string, paramString);
	}

	public int getArray(String paramString) {
		return getResofR(array, paramString);
	}

	private int getResofR(Class<?> paramClass, String paramString) {
		if (paramClass == null) {

			throw new IllegalArgumentException("ResClass is not initialized.");
		}
		try {
			Field localField = paramClass.getField(paramString);
			int k = localField.getInt(paramString);
			Log.e("bunny","id ="+k);
			return k;
		} catch (Exception localException) {
			localException.printStackTrace();//OGSdkLogUtil.d(OGSdkLogUtil.TAG, "getRes(" + paramClass.getName() + ", " + paramString + ")" + localException.getMessage());
		}
		return -1;
	}

	static int getResourceId(Context context, String name, String type, String packageName) {

		Resources themeResources = null;
		PackageManager pm = context.getPackageManager();
		try {
			themeResources = pm.getResourcesForApplication(packageName);
			return themeResources.getIdentifier(name, type, packageName);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}
}