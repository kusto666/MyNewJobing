package main.jobing.mynewjobing.ui.employee;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EmployeeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EmployeeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("EmployeeViewModel");
    }

    public LiveData<String> getText() {
        return mText;
    }
}