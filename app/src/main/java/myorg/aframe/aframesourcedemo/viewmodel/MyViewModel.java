package myorg.aframe.aframesourcedemo.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

  private MutableLiveData<String> name = new MutableLiveData<>();

  private MutableLiveData<Long> time = new MutableLiveData<>();


  public MutableLiveData<String> getName() {
    return name;
  }

  public void setName(MutableLiveData<String> name) {
    this.name = name;
  }

  public MutableLiveData<Long> getTime() {
    return time;
  }

  public void setTime(MutableLiveData<Long> time) {
    this.time = time;
  }
}
