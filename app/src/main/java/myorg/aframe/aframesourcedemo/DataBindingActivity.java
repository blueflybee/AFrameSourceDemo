package myorg.aframe.aframesourcedemo;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.CharacterPickerDialog;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import myorg.aframe.aframesourcedemo.databinding.ActivityDataBindingBinding;
import myorg.aframe.aframesourcedemo.viewmodel.MyViewModel;


public class DataBindingActivity extends AppCompatActivity {

  private ActivityDataBindingBinding mBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);

    MyViewModel myViewModel = new MyViewModel();

    mBinding.setViewModel(myViewModel);


    myViewModel.getName().setValue("abcd");

    Long time = Long.valueOf(100);
    myViewModel.getTime().setValue(time);


    mBinding.etTest.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        System.out.println("DataBindingActivity.onClick");
        myViewModel.getTime().setValue(200l);

        Runnable runnable = new Runnable() {
          public void run() {
            System.out.println("mBinding.myBindview.getTime = " + mBinding.myBindview.getTime());

          }
        };
        new Handler().postDelayed(runnable, 6000);
      }
    });

    mBinding.etTest.addTextChangedListener(new TextWatcher() {

      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void afterTextChanged(Editable editable) {
        System.out.println("editable = " + editable);
        System.out.println("myViewModel.name = " + myViewModel.getName().getValue());

      }
    });
  }

}
