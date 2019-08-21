/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package myorg.aframe.aframesourcedemo;

import android.Manifest;
import android.app.Application;
import android.content.Context;

import java.util.List;

import myorg.aframe.aframesourcedemo.utils.PermissionUtils;

/**
 * Android Main Application
 */
public class AndroidApplication extends Application {

  private static final String APP_NAME = "ARing";
  private static final String TAG = AndroidApplication.class.getSimpleName();


  @Override
  public void onCreate() {
    super.onCreate();
    requestPermissions();
  }

  private void requestPermissions() {
    String[] perms = {
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
//        Manifest.permission.READ_CONTACTS,
//        Manifest.permission.WRITE_CONTACTS,
//        Manifest.permission.CALL_PHONE,
//        Manifest.permission.RECEIVE_BOOT_COMPLETED
//        Manifest.permission.ACCESS_FINE_LOCATION,
//        Manifest.permission.CAMERA,
//        Manifest.permission.RECORD_AUDIO
    };
    PermissionUtils
        .permission(perms)
        .callback(new PermissionUtils.FullCallback() {
          @Override
          public void onGranted(List<String> permissionsGranted) {
            if (permissionsGranted.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//              handleCrashLog();
            }
          }

          @Override
          public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
          }
        }).request();
  }

//  @SuppressLint("MissingPermission")
//  private void handleCrashLog() {
//    File filesDir = new File(getFilesDir().getAbsolutePath() + System.getProperty("file.separator") + "crash");
//    CrashUtils.init(filesDir, (crashInfo, e) -> MobclickAgent.reportError(getApplicationContext(), e));
//  }

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
//    MultiDex.install(this);
  }

}
