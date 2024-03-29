package org.communitycookerfoundation.communitycookerfoundation.ui.admin;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.communitycookerfoundation.communitycookerfoundation.db.DataRepo;

import java.util.HashMap;
import java.util.Map;

public class AddCookerViewModel extends AndroidViewModel {

    FirebaseUser mCurrentUser;
    private DataRepo mRepo;

    public AddCookerViewModel(@NonNull Application application) {
        super(application);
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        mRepo = new DataRepo(application);
    }




    //public void insertFb(ReportEntity reportEntity) {mRepo.insertReportFb(reportEntity);}


    @Override
    protected void onCleared() {
        super.onCleared();
        mRepo.clearObservedData();
    }



    public void addCookerUser(String userEmail) {
        Map<String, String> cookerUser = new HashMap<>();
        cookerUser.put("email", userEmail);
        cookerUser.put("role", "user");

        mRepo.addUser(cookerUser);

    }
}


