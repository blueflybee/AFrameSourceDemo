package myorg.aframe.aframesourcedemo;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import myorg.aframe.aframesourcedemo.databinding.ActivityIntentBinding;

public class IntentActivity extends AppCompatActivity {

  public static final String COM_TEST_MYORDER_ACTION = "com.test.myorder.action";
  private ActivityIntentBinding mBinding;

//  private BroadcastReceiver1 mBroadcastReceiver1 = new BroadcastReceiver1();
//  private BroadcastReceiver2 mBroadcastReceiver2 = new BroadcastReceiver2();
//  private BroadcastReceiver3 mBroadcastReceiver3 = new BroadcastReceiver3();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_intent);

    System.out.println("getBaseContext() = " + getBaseContext());


    mBinding.btnIntentSender.setOnClickListener(v -> {
      Intent intent = new Intent(Intent.ACTION_SEND)
          .putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
          .setType("text/plain");
      Intent receiver = new Intent(IntentActivity.this, MainActivity.class)
          .putExtra("test", "test");
      PendingIntent pendingIntent = PendingIntent.getBroadcast(IntentActivity.this, 0, receiver, PendingIntent.FLAG_UPDATE_CURRENT);
      Intent chooser = null;
      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
        chooser = Intent.createChooser(intent, "test", pendingIntent.getIntentSender());
      }
      startActivity(chooser);
    });

    mBinding.btnOrderBroadcast.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(COM_TEST_MYORDER_ACTION);
//        intent.setPackage(getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        sendOrderedBroadcast(intent, null);
      }
    });

//    IntentFilter intentFilter = new IntentFilter();
//    intentFilter.addAction(COM_TEST_MYORDER_ACTION);


//    registerReceiver(mBroadcastReceiver1, intentFilter);
//    registerReceiver(mBroadcastReceiver2, intentFilter);
//    registerReceiver(mBroadcastReceiver3, intentFilter);

  }

  public static class BroadcastReceiver1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
      System.out.println("BroadcastReceiver1.onReceive");

    }
  }


  public static class BroadcastReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
      System.out.println("BroadcastReceiver2.onReceive");
//      abortBroadcast();
    }
  }

  public static class BroadcastReceiver3 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
      System.out.println("BroadcastReceiver3.onReceive");
    }
  }



  @Override
  protected void onDestroy() {
    super.onDestroy();
//    unregisterReceiver(mBroadcastReceiver1);
//    unregisterReceiver(mBroadcastReceiver2);
//    unregisterReceiver(mBroadcastReceiver3);
  }
}
