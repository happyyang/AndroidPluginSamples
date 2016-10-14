/*
 * ACDDExtension Project
 * file UILWidgetProvider.java  is  part of ACDDExtension
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
package com.nostra13.universalimageloader.sample.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.RemoteViews;

import com.acdd.universalimageloader.sample.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.sample.UILApplication;

import static com.nostra13.universalimageloader.sample.Constants.IMAGES;

/**
 * Example widget provider
 * 
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class UILWidgetProvider extends AppWidgetProvider {

	private static DisplayImageOptions displayOptions;

	static {
		displayOptions = DisplayImageOptions.createSimple();
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		UILApplication.initImageLoader(context);

		final int widgetCount = appWidgetIds.length;
		for (int i = 0; i < widgetCount; i++) {
			int appWidgetId = appWidgetIds[i];
			updateAppWidget(context, appWidgetManager, appWidgetId);
		}
	}

	static void updateAppWidget(Context context, final AppWidgetManager appWidgetManager, final int appWidgetId) {
		final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);

		ImageSize minImageSize = new ImageSize(70, 70); // 70 - approximate size of ImageView in widget
		ImageLoader.getInstance()
				.loadImage(IMAGES[0], minImageSize, displayOptions, new SimpleImageLoadingListener() {
			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				views.setImageViewBitmap(R.id.image_left, loadedImage);
				appWidgetManager.updateAppWidget(appWidgetId, views);
			}
		});
		ImageLoader.getInstance()
				.loadImage(IMAGES[1], minImageSize, displayOptions, new SimpleImageLoadingListener() {
			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				views.setImageViewBitmap(R.id.image_right, loadedImage);
				appWidgetManager.updateAppWidget(appWidgetId, views);
			}
		});
	}
}
