package com.missionedappdev.missoned.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class quizoesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public quizoesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is quizoes fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}