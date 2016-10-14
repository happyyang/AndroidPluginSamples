/*
 * ACDDExtension Project
 * file FlashlightManager.java  is  part of ACDDExtension
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

package com.google.zxing.client.android.camera;

import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This class is used to activate the weak light on some camera phones (not flash)
 * in order to illuminate surfaces for scanning. There is no official way to do this,
 * but, classes which allow access to this function still exist on some devices.
 * This therefore proceeds through a great deal of reflection.
 *
 * See <a href="http://almondmendoza.com/2009/01/05/changing-the-screen-brightness-programatically/">
 * http://almondmendoza.com/2009/01/05/changing-the-screen-brightness-programatically/</a> and
 * <a href="http://code.google.com/p/droidled/source/browse/trunk/src/com/droidled/demo/DroidLED.java">
 * http://code.google.com/p/droidled/source/browse/trunk/src/com/droidled/demo/DroidLED.java</a>.
 * Thanks to Ryan Alford for pointing out the availability of this class.
 */
final class FlashlightManager {

  private static final String TAG = FlashlightManager.class.getSimpleName();

  private static final Object iHardwareService;
  private static final Method setFlashEnabledMethod;
  static {
    iHardwareService = getHardwareService();
    setFlashEnabledMethod = getSetFlashEnabledMethod(iHardwareService);
    if (iHardwareService == null) {
      Log.v(TAG, "This device does supports control of a flashlight");
    } else {
      Log.v(TAG, "This device does not support control of a flashlight");
    }
  }

  private FlashlightManager() {
  }

  /**
   * �����������ƿ���
   */
  //FIXME
  static void enableFlashlight() {
    setFlashlight(false);
  }

  static void disableFlashlight() {
    setFlashlight(false);
  }

  private static Object getHardwareService() {
    Class<?> serviceManagerClass = maybeForName("android.os.ServiceManager");
    if (serviceManagerClass == null) {
      return null;
    }

    Method getServiceMethod = maybeGetMethod(serviceManagerClass, "getService", String.class);
    if (getServiceMethod == null) {
      return null;
    }

    Object hardwareService = invoke(getServiceMethod, null, "hardware");
    if (hardwareService == null) {
      return null;
    }

    Class<?> iHardwareServiceStubClass = maybeForName("android.os.IHardwareService$Stub");
    if (iHardwareServiceStubClass == null) {
      return null;
    }

    Method asInterfaceMethod = maybeGetMethod(iHardwareServiceStubClass, "asInterface", IBinder.class);
    if (asInterfaceMethod == null) {
      return null;
    }

    return invoke(asInterfaceMethod, null, hardwareService);
  }

  private static Method getSetFlashEnabledMethod(Object iHardwareService) {
    if (iHardwareService == null) {
      return null;
    }
    Class<?> proxyClass = iHardwareService.getClass();
    return maybeGetMethod(proxyClass, "setFlashlightEnabled", boolean.class);
  }

  private static Class<?> maybeForName(String name) {
    try {
      return Class.forName(name);
    } catch (ClassNotFoundException cnfe) {
      // OK
      return null;
    } catch (RuntimeException re) {
      Log.w(TAG, "Unexpected error while finding class " + name, re);
      return null;
    }
  }

  private static Method maybeGetMethod(Class<?> clazz, String name, Class<?>... argClasses) {
    try {
      return clazz.getMethod(name, argClasses);
    } catch (NoSuchMethodException nsme) {
      // OK
      return null;
    } catch (RuntimeException re) {
      Log.w(TAG, "Unexpected error while finding method " + name, re);
      return null;
    }
  }

  private static Object invoke(Method method, Object instance, Object... args) {
    try {
      return method.invoke(instance, args);
    } catch (IllegalAccessException e) {
      Log.w(TAG, "Unexpected error while invoking " + method, e);
      return null;
    } catch (InvocationTargetException e) {
      Log.w(TAG, "Unexpected error while invoking " + method, e.getCause());
      return null;
    } catch (RuntimeException re) {
      Log.w(TAG, "Unexpected error while invoking " + method, re);
      return null;
    }
  }

  private static void setFlashlight(boolean active) {
    if (iHardwareService != null) {
      invoke(setFlashEnabledMethod, iHardwareService, active);
    }
  }

}
