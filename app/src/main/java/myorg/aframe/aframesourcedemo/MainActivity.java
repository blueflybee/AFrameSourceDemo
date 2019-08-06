package myorg.aframe.aframesourcedemo;

import android.content.ComponentCallbacks;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    int[] colors = new int[]{Color.RED, Color.GREEN, Color.BLUE};
    int[] statePressed = new int[]{android.R.attr.state_pressed};
    int[] stateFocused = new int[]{android.R.attr.state_focused};

    int[][] states = new int[][]{
        statePressed,
        stateFocused,
        new int[]{0}
    };

    ColorStateList colorStateList = new ColorStateList(states, colors);
    Button btn = findViewById(R.id.btn_state);
    btn.setTextColor(colorStateList);


  }
}
