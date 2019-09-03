package myorg.aframe.aframesourcedemo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import myorg.aframe.aframesourcedemo.databinding.ActivityDataBindingBinding;
import myorg.aframe.aframesourcedemo.viewmodel.MyViewModel;


public class DataBindingActivity extends AppCompatActivity {

  private ActivityDataBindingBinding mBinding;
  private MyViewModel mMyViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);

    mMyViewModel = new MyViewModel();

    mBinding.setLifecycleOwner(this);
    mBinding.setViewModel(mMyViewModel);



    mMyViewModel.getName().setValue("abcd");

    mMyViewModel.getTime().setValue("start");

    final int[] i = {1};
    mBinding.etTest.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        System.out.println("DataBindingActivity.onClick");

        mMyViewModel.getTime().postValue(String.valueOf(i[0]++));


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
        System.out.println("myViewModel.name = " + mMyViewModel.getName().getValue());

      }
    });
  }

}
