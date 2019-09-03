package myorg.aframe.aframesourcedemo.adapter;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import myorg.aframe.aframesourcedemo.customview.MyBindView;

public class MyViewBindingAdapter {

  @BindingAdapter("time")
  public static void setTime(MyBindView view, Long time) {
    System.out.println("MyViewBindingAdapter.setTime");
    Long oldTime = view.getTime();
    if (time == null || oldTime == null) return;
    if (time.longValue() != oldTime.longValue()) {
      view.setTime(time);
//      view.postInvalidateDelayed(2000);
    }

  }

  @InverseBindingAdapter(attribute = "time")
  public static Long getTime(MyBindView view) {
    return view.getTime();
  }
}
