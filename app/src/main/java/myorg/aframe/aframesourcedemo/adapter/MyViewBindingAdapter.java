package myorg.aframe.aframesourcedemo.adapter;

import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import myorg.aframe.aframesourcedemo.customview.MyBindView;

public class MyViewBindingAdapter {

  @BindingAdapter("time")
  public static void setTime(MyBindView view, String  time) {
    System.out.println("MyViewBindingAdapter.setTime");
    String oldTime = view.getTime();
    if (!oldTime.equals(time)) {
      view.setTime(time);
      view.postInvalidateDelayed(200);
    }

  }

  @InverseBindingAdapter(attribute = "time")
  public static String getTime(MyBindView view) {
    return view.getTime();
  }


  @BindingAdapter("app:hideIfZero")
  public static void hideIfZero(View view, int number) {
    System.out.println("MyViewBindingAdapter.hideIfZero");
    view.setVisibility(number == 0 ? View.GONE : View.VISIBLE);
  }

}
