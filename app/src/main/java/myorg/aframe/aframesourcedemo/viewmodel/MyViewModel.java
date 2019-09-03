package myorg.aframe.aframesourcedemo.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel {

  private MutableLiveData<String> name = new MutableLiveData<>();

  private MutableLiveData<String > time = new MutableLiveData<>();

  private MutableLiveData<Integer> number = new MutableLiveData<>();


  public MyViewModel() {
    number.setValue(0);
  }

  public MutableLiveData<String> getName() {
    return name;
  }

  public void setName(MutableLiveData<String> name) {
    this.name = name;
  }

  public MutableLiveData<String> getTime() {
    return time;
  }

  public void setTime(MutableLiveData<String> time) {
    this.time = time;
  }


  public void addProgress() {
    System.out.println("MyViewModel.addProgress");
    number.setValue(number.getValue() + 1);
    System.out.println("number = " + number.getValue());
  }

  public MutableLiveData<Integer> getNumber() {
    return number;
  }

  public void setNumber(MutableLiveData<Integer> number) {
    this.number = number;
  }
}
