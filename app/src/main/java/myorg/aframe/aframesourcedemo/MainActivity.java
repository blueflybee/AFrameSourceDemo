package myorg.aframe.aframesourcedemo;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    SQLiteDatabase database = openOrCreateDatabase("test", MODE_ENABLE_WRITE_AHEAD_LOGGING, null, new DatabaseErrorHandler() {
      @Override
      public void onCorruption(SQLiteDatabase sqLiteDatabase) {

      }
    });

    registerComponentCallbacks(new ComponentCallbacks() {
      @Override
      public void onConfigurationChanged(Configuration configuration) {

      }

      @Override
      public void onLowMemory() {

      }
    });

  }
}
