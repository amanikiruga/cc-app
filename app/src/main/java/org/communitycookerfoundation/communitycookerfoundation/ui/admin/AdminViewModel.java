package org.communitycookerfoundation.communitycookerfoundation.ui.admin;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.communitycookerfoundation.communitycookerfoundation.db.DataRepo;
import org.communitycookerfoundation.communitycookerfoundation.db.Entity.ReportEntity;

import java.util.List;
import java.util.Map;

public class AdminViewModel extends AndroidViewModel {

    FirebaseUser mCurrentUser;
    private DataRepo mRepo;
    private LiveData<List<ReportEntity>> mAllReports;
    private MutableLiveData<List<Map<String, Object>>> mAllUsers;

    public AdminViewModel(@NonNull Application application) {
        super(application);
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        mRepo = new DataRepo(application);
        mAllReports = mRepo.getAllReports();
        mAllUsers = mRepo.getAllUsers();
    }

    public LiveData<List<ReportEntity>> getAllReports() {return mAllReports; }
    //public void insertFb(ReportEntity reportEntity) {mRepo.insertReportFb(reportEntity);}

    public String getDisplayName() {
        return mCurrentUser.getDisplayName();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mRepo.clearObservedData();
    }

    public MutableLiveData<List<Map<String, Object>>> getAllUsers() {
        return mAllUsers;
    }
    public void refreshAllUsers(){
        mRepo.refreshUserList();
    }


}


