package org.communitycookerfoundation.communitycookerfoundation.ui.admin;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.communitycookerfoundation.communitycookerfoundation.R;
import org.communitycookerfoundation.communitycookerfoundation.ui.prompt.PromptFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddCookerFragment  extends Fragment implements DatePickerDialog.OnDateSetListener {

    private TextInputEditText mEmailAddress;
    private TextInputLayout mLayoutEmail;
    private TextView mCalendarText;
    private AddCookerViewModel mViewModel;
    private TextInputEditText mNamePerson;

    @Override
    public View onCreateView(

            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View fragmentFirst = inflater.inflate(R.layout.dialog_add_cooker, container, false);
        //showCount = fragmentFirst.findViewById(R.id.textview_first);
        return fragmentFirst;


    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        mNamePerson = view.findViewById(R.id.input_answer2);
        Button dateBtn = view.findViewById(R.id.open_cal);
        mEmailAddress = view.findViewById(R.id.input_email);
        Button nextBtn = view.findViewById(R.id.next_btn);
        mViewModel = new ViewModelProvider(this).get(AddCookerViewModel.class);
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(view);
            }
        });
        mCalendarText.setText("No date chosen yet");
       /* mEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = mEditText.getText().toString().trim();
                validateText(text);
            }

            @Override
            public void afterTextChanged(Editable s) {



            }
        });*/
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(mEmailAddress.getText().toString()) && !TextUtils.isEmpty(mNamePerson.getText().toString())){
                    mViewModel.addCookerUser(mNamePerson.getText().toString(), mEmailAddress.getText().toString());
                    NavHostFragment.findNavController(AddCookerFragment.this).popBackStack();

                }
                else {
                    Toast myToast = Toast.makeText(getContext(), "Add an email address", Toast.LENGTH_SHORT);
                    myToast.show();

                }

            }
        });
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment(this);
        newFragment.show(getParentFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        mCalendarText = getView().findViewById(R.id.textPrompt);
        String text = dayOfMonth+"/"+month+"/"+year;
        mCalendarText.setText(text);

    }
}
