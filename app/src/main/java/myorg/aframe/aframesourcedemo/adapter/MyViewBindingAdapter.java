package myorg.aframe.aframesourcedemo.adapter;

import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import myorg.aframe.aframesourcedemo.customview.MyBindView;

public class MyViewBindingAdapter {

  @BindingAdapter("time")
  public static void setTime(MyBindView view, String  time) {

    String oldTime = view.getTime();
    if (!oldTime.equals(time)) {
      System.out.println("MyViewBindingAdapter.setTime");
      view.setTime(time);
      view.postInvalidate();
    }

  }

  @BindingAdapter(value = "app:timeAttrClickChanged", requireAll = false)
  public static void setOnClickListener(MyBindView view, final InverseBindingListener inverseBindingListener) {
//    if (inverseBindingListener != null) {
//      view.setTime("app:timeAttrClickChanged");
//      inverseBindingListener.onChange();
//    }
    view.setOnClickListener(view1 -> {
      if (inverseBindingListener != null) {
        System.out.println("MyViewBindingAdapter.setOnClickListener");
        inverseBindingListener.onChange();
      }
    });

  }

  @InverseBindingAdapter(attribute = "time", event = "app:timeAttrClickChanged")
  public static String getTime(MyBindView view) {
    return view.getTime();
  }


  @BindingAdapter("hideIfZero")
  public static void hideIfZero(View view, int number) {
    System.out.println("MyViewBindingAdapter.hideIfZero");
    view.setVisibility(number == 0 ? View.GONE : View.VISIBLE);
  }

}
