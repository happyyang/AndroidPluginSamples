/*
 * ACDDExtension Project
 * file DecodeThread.java  is  part of ACDDExtension
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

package com.google.zxing.client.android.decoding;

import java.util.concurrent.CountDownLatch;

import android.os.Handler;
import android.os.Looper;

import com.acdd.qrcode.CaptureActivity;

/**
 * This thread does all the heavy lifting of decoding the images. 解码线程
 */
final class DecodeThread extends Thread {

	public static final String BARCODE_BITMAP = "barcode_bitmap";
	private final CaptureActivity activity;
	// private final Hashtable<DecodeHintType, Object> hints;
	private Handler handler;
	private final CountDownLatch handlerInitLatch;

	DecodeThread(CaptureActivity activity) {

		this.activity = activity;
		handlerInitLatch = new CountDownLatch(1);

		// hints = new Hashtable<DecodeHintType, Object>(3);
		//
		// if (decodeFormats == null || decodeFormats.isEmpty()) {
		// decodeFormats = new Vector<BarcodeFormat>();
		// decodeFormats.addAll(DecodeFormatManager.ONE_D_FORMATS);
		// decodeFormats.addAll(DecodeFormatManager.QR_CODE_FORMATS);
		// decodeFormats.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
		// }
		//
		// hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);
		//
		// if (characterSet != null) {
		// hints.put(DecodeHintType.CHARACTER_SET, characterSet);
		// }
		//
		// hints.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK,
		// resultPointCallback);
	}

	Handler getHandler() {
		try {
			handlerInitLatch.await();
		} catch (InterruptedException ie) {
			// continue?
		}
		return handler;
	}

	@Override
	public void run() {
		Looper.prepare();
		handler = new DecodeHandler(activity);
		handlerInitLatch.countDown();
		Looper.loop();
	}

}
