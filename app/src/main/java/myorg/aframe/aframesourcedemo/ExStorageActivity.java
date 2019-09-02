package myorg.aframe.aframesourcedemo;

import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.abc.MyExStorageBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;


public class ExStorageActivity extends AppCompatActivity {


  public static final String UTF_8 = "utf-8";
  private MyExStorageBinding mBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_ex_storage);

    mBinding.btnCreateExternalPrivateFile.setOnClickListener(v -> createExternalStoragePrivateFile());
    mBinding.btnCreateExternalPrivatePic.setOnClickListener(v -> createExternalStoragePrivatePicture());
    mBinding.btnCreateExternalPublicPic.setOnClickListener(v -> createExternalStoragePublicPicture());

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      File[] externalFilesDirs = getExternalFilesDirs(null);
      for (File externalFilesDir : externalFilesDirs) {
        System.out.println("externalFilesDir = " + externalFilesDir);
      }
    }

    printCacheQuotaBytes();
    printStorageInfo();

  }

  private void printStorageInfo() {
    File filesDir = getFilesDir();
    System.out.println("filesDir.getTotalSpace() = " + filesDir.getTotalSpace());
    System.out.println("filesDir.getFreeSpace() = " + filesDir.getFreeSpace());
    System.out.println("filesDir.getUsableSpace() = " + filesDir.getUsableSpace());
    File emulatedDir = new File("/storage/emulated/0/");
    System.out.println("emulatedDir.getTotalSpace() = " + emulatedDir.getTotalSpace());

    try {
      File tempFile = File.createTempFile("prefix", null, getCacheDir());
      System.out.println("File.createTempFile() = " + tempFile);
//      boolean success = deleteFile(tempFile.getName());
//      System.out.println("success = " + success);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void printCacheQuotaBytes() {
    StorageManager storageManager = (StorageManager) getSystemService(STORAGE_SERVICE);
    File cacheDir = getCacheDir();
    System.out.println("storageManager = " + storageManager);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      try {
        UUID uuidForPath = storageManager.getUuidForPath(cacheDir);
        System.out.println("uuidForPath = " + uuidForPath);
        long cacheQuotaBytes = storageManager.getCacheQuotaBytes(uuidForPath);
        System.out.println("cacheQuotaBytes = " + cacheQuotaBytes);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void createExternalStoragePrivateFile() {
    File file = new File(getExternalFilesDir(null), "ex_private_file.jpg");
    System.out.println("file ex private = " + file);


    try {
      InputStream is = getResources().openRawResource(R.raw.hz1);
      FileOutputStream os = new FileOutputStream(file);
      byte[] data = new byte[is.available()];
      is.read(data);
      os.write(data);
      is.close();
      os.close();

    } catch (IOException e) {
      e.printStackTrace();
      Log.w("ExternalStorage", "Error writing " + file, e);
    }
  }

  private void createExternalStoragePrivatePicture() {
    // Create a path where we will place our picture in our own private
    // pictures directory.  Note that we don't really need to place a
    // picture in DIRECTORY_PICTURES, since the media scanner will see
    // all media in these directories; this may be useful with other
    // media types such as DIRECTORY_MUSIC however to help it classify
    // your media for display to the user.
    File path = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    File file = new File(path, "PrivatePicture.jpg");


    try {
      // Very simple code to copy a picture from the application's
      // resource into the external file.  Note that this code does
      // no error checking, and assumes the picture is small (does not
      // try to copy it in chunks).  Note that if external storage is
      // not currently mounted this will silently fail.
      InputStream is = getResources().openRawResource(R.raw.hz_private_pic);
      OutputStream os = new FileOutputStream(file);
      byte[] data = new byte[is.available()];
      is.read(data);
      os.write(data);
      is.close();
      os.close();

      // Tell the media scanner about the new file so that it is
      // immediately available to the user.
      MediaScannerConnection.scanFile(this,
          new String[] { file.toString() }, null,
          new MediaScannerConnection.OnScanCompletedListener() {
            public void onScanCompleted(String path, Uri uri) {
              Log.i("ExternalStorage", "Scanned " + path + ":");
              Log.i("ExternalStorage", "-> uri=" + uri);
            }
          });
    } catch (IOException e) {
      // Unable to create file, likely because external storage is
      // not currently mounted.
      Log.w("ExternalStorage", "Error writing " + file, e);
    }
  }

  private void createExternalStoragePublicPicture() {
    // Create a path where we will place our picture in the user's
    // public pictures directory.  Note that you should be careful about
    // what you place here, since the user often manages these files.  For
    // pictures and other media owned by the application, consider
    // Context.getExternalMediaDir().
    File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
    File file = new File(path, "PublicPicture.jpg");
    Log.i("ExternalStorage", "file = " + file.getAbsolutePath());

    try {
      // Make sure the Pictures directory exists.
      path.mkdirs();

      // Very simple code to copy a picture from the application's
      // resource into the external file.  Note that this code does
      // no error checking, and assumes the picture is small (does not
      // try to copy it in chunks).  Note that if external storage is
      // not currently mounted this will silently fail.
      InputStream is = getResources().openRawResource(R.raw.hz_public_pic);
      OutputStream os = new FileOutputStream(file);
      byte[] data = new byte[is.available()];
      is.read(data);
      os.write(data);
      is.close();
      os.close();

      // Tell the media scanner about the new file so that it is
      // immediately available to the user.
      MediaScannerConnection.scanFile(this,
          new String[] { file.toString() }, null,
          new MediaScannerConnection.OnScanCompletedListener() {
            public void onScanCompleted(String path, Uri uri) {
              Log.i("ExternalStorage", "Scanned " + path + ":");
              Log.i("ExternalStorage", "-> uri=" + uri);
            }
          });
    } catch (IOException e) {
      // Unable to create file, likely because external storage is
      // not currently mounted.
      Log.w("ExternalStorage", "Error writing " + file, e);
    }
  }


  public static void notifyStore(Context context, File file) {
//    try {
//      MediaStore.Images.Media.insertImage(context.getContentResolver(),
//          file.getParent(), file.getName(), null);
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//    }
    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
    Uri uri = Uri.fromFile(file);
    intent.setData(uri);
    context.sendBroadcast(intent);
  }
}
