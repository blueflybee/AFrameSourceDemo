package myorg.aframe.aframesourcedemo;

import android.content.ComponentCallbacks;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.os.Environment.DIRECTORY_MOVIES;
import static android.os.Environment.DIRECTORY_MUSIC;

public class MainActivity extends AppCompatActivity {


  public static final String UTF_8 = "utf-8";

  @RequiresApi(api = Build.VERSION_CODES.M)
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
//    btn.setTextColor(colorStateList);

    XmlResourceParser xrp = getResources().getXml(R.color.button_text);
    try {
      ColorStateList stateList = ColorStateList.createFromXml(getResources(), xrp, null);
      System.out.println("stateList = " + stateList);
      btn.setTextColor(stateList);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (XmlPullParserException e) {
      e.printStackTrace();
    }

    final TextView myTextView = findViewById(R.id.tv_test);
    final TextView readFileTv = findViewById(R.id.tv_io_file);

    //设置按钮监听器
    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //设置定时器
        int counter = 0;
        //实例化StringBuilder
        StringBuilder sb = new StringBuilder("");
        //得到Resources资源
        Resources r = getResources();
        //通过Resources，获得XmlResourceParser实例
        XmlResourceParser xrp = r.getXml(R.xml.button_text);
        AttributeSet attributeSet = Xml.asAttributeSet(xrp);
        try {
          //如果没有到文件尾继续执行
          while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
            //如果是开始标签
            if (xrp.getEventType() == XmlResourceParser.START_TAG) {
              //获取标签名称
              String name = xrp.getName();
//              if (name.equals("selector")) {
//                sb.append("namespace" + xrp.getAttributeNamespace(0) + "\n");
//              }
              //判断标签名称是否等于friend
              if (name.equals("item")) {
                counter++;
                //获得标签属性追加到StringBuilder中
                sb.append("第" + counter + "个朋友的信息：" + "\n");
                sb.append("description:" + xrp.getPositionDescription() + "\n");
                sb.append(xrp.getAttributeValue(0) + "\n\n");
//                sb.append(xrp.getAttributeValue(0) + "\n");
//                sb.append(xrp.getAttributeValue(1) + "\n");
//                sb.append(xrp.getAttributeValue(2) + "\n");
//                sb.append(xrp.getAttributeValue(3) + "\n\n");
              }
            } else if (xrp.getEventType() == XmlPullParser.END_TAG) {
            } else if (xrp.getEventType() == XmlPullParser.TEXT) {
            }
            //下一个标签
            xrp.next();
          }
          myTextView.setText(sb.toString());
        } catch (XmlPullParserException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

//    try {
//      String name = "context_study_file.text";
//      FileOutputStream output = openFileOutput(name, MODE_PRIVATE);
//      output.write("abc".getBytes(UTF_8));
//      output.close();
//
//      FileInputStream input = openFileInput(name);
//      byte[] buffer = new byte[100];
//      int len = input.read(buffer);
//      String result = new String(buffer, 0, len, UTF_8);
//      readFileTv.setText(result);
//
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
      File dataDir = getDataDir();
      System.out.println("dataDir = " + dataDir);
      System.out.println("getNoBackupFilesDir = " + getNoBackupFilesDir());
      System.out.println("getExternalFilesDir root = " + getExternalFilesDir(null));
      System.out.println("getExternalFilesDir music = " + getExternalFilesDir(DIRECTORY_MUSIC));
      System.out.println("getExternalFilesDir movies = " + getExternalFilesDir(DIRECTORY_MOVIES));
    }


  }
}
