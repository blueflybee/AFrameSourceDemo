package myorg.aframe.aframesourcedemo;

import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import myorg.aframe.aframesourcedemo.databinding.ActivityDataBindingBinding;
import myorg.aframe.aframesourcedemo.viewmodel.MyViewModel;


public class DataBindingActivity extends AppCompatActivity {

  private ActivityDataBindingBinding mBinding;
  private MyViewModel mMyViewModel;
  private MutableLiveData<Integer> mSource;
  private MutableLiveData<Integer> mLiveData1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);

    testTransformationsMap();
    testTransformationsSwitchMap();

//    MediatorLiveData<Integer> mediatorLiveData = new MediatorLiveData<>();
//    MutableLiveData<Integer> liveData1 = new MutableLiveData<>();
//    MutableLiveData<Integer> liveData2 = new MutableLiveData<>();

//    mediatorLiveData.observe(this, new Observer<Integer>() {
//      @Override
//      public void onChanged(Integer integer) {
//        System.out.println("DataBindingActivity.mediatorLiveData.onChanged");
//      }
//    });
//
//
//    mediatorLiveData.addSource(liveData1, new Observer<Integer>() {
//      @Override
//      public void onChanged(Integer integer) {
//        System.out.println("DataBindingActivity.liveData1.integer = " + integer);
////        mediatorLiveData.setValue(integer);
//      }
//    });
//
//    mediatorLiveData.addSource(liveData2, new Observer<Integer>() {
//      @Override
//      public void onChanged(Integer integer) {
//        System.out.println("DataBindingActivity.liveData2.integer = " + integer);
////        mediatorLiveData.setValue(integer);
//      }
//    });
//
//
//    liveData1.setValue(1);


    mMyViewModel = new MyViewModel();

    mBinding.setLifecycleOwner(this);
    mBinding.setViewModel(mMyViewModel);


    mMyViewModel.getName().setValue("abcd");

    mMyViewModel.getTime().setValue("start");

    final int[] i = {1};
    final int[] j = {100};
    mBinding.etTest.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        System.out.println("DataBindingActivity.onClick");

        mMyViewModel.getTime().setValue(String.valueOf(i[0]++));


      }
    });


    mBinding.btn2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        System.out.println("DataBindingActivity.before modify time = " + mMyViewModel.getTime().getValue());
        mBinding.myBindview.setTime(String.valueOf(j[0]++));
        mBinding.myBindview.setClickable(true);
//        mBinding.myBindview.performClick();
        System.out.println("DataBindingActivity.after modify time = " + mMyViewModel.getTime().getValue());
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

    mBinding.btn3.setOnClickListener(new View.OnClickListener() {
      int i = 1;
      int j = -1;

      @Override
      public void onClick(View view) {
//        mSource.setValue(i++);
        mLiveData1.setValue(j--);
      }
    });
  }

  private void testTransformationsSwitchMap() {

    mLiveData1 = new MutableLiveData<>();
    Transformations
        .switchMap(mLiveData1, input -> {
          MutableLiveData<String> liveDataY = new MutableLiveData<>();
          liveDataY.setValue("you input: " + input);
          return liveDataY;
        })
        .observe(this, s -> System.out.println("result = " + s));

  }

  private void testTransformationsMap() {

//    mSource = new MutableLiveData<>();
//    Transformations
//        .map(mSource, input -> "you input: " + input)
//        .observe(this, result -> System.out.println("result = " + result));


  }

}
