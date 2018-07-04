package io.mycompany.androidpractice.dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import io.mycompany.androidpractice.R;
import io.mycompany.androidpractice.model.Card;
import io.mycompany.androidpractice.util.DataUtilSimple;
import io.mycompany.androidpractice.util.InputFilterMinMax;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS;
import static android.text.InputType.TYPE_TEXT_FLAG_CAP_SENTENCES;

public class AddNewItemDialogFragment extends DialogFragment {

    private EditText textHeading;
    private EditText textDesc;
    private CheckBox checkBox;

    public AddNewItemDialogFragment() {
    }

    public static AddNewItemDialogFragment newInstance(){
        return new AddNewItemDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_item, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        textHeading = view.findViewById(R.id.editTextHeader);
        textHeading.setInputType(TYPE_CLASS_TEXT| TYPE_TEXT_FLAG_CAP_SENTENCES);
        textHeading.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String originalText = s.toString();
                int originalTextLength = originalText.length();
                int currentSelection = textHeading.getSelectionStart();

                StringBuilder sb  = new StringBuilder();
                boolean hasChanged = false;
                for (int i = 0; i < originalTextLength; i++){
                    char currentChar = originalText.charAt(i);
                    if (isAllowed(currentChar) && i < 21){
                        sb.append(currentChar);
                    } else {
                        hasChanged = true;
                        if (currentSelection >= i){
                            textHeading.setError("Please insert current letters");
                            currentSelection --;
                        }
                    }
                }
                if (hasChanged){
                    String newText = sb.toString();
                    textHeading.setText(newText);
                    textHeading.setSelection(currentSelection);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int len = s.length();
                if (len > 20) {
                    textHeading.setError("must be less then 20 letters");
                }
            }

            private boolean isAllowed(char currentChar) {
                return Character.isLetter(currentChar) && !Character.isSpaceChar(currentChar);
            }
        });

        textDesc = view.findViewById(R.id.editTextDescription);
        Button button = view.findViewById(R.id.btnAdd);
        checkBox = view.findViewById(R.id.checkBoxDialog);
        checkBox.setChecked(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(makeNewItem()){
                    dismiss();
                }
            }
        });
        return view;
    }

    public boolean makeNewItem(){
        boolean res;
        String heading = String.valueOf(textHeading.getText());
        String desc = String.valueOf(textDesc.getText());

        if (!(heading.length() == 0) && !(desc.length() == 0)) {
            DataUtilSimple.addNewItem(new Card(heading, desc, checkBox.isChecked()));
            Toast toast = Toast.makeText(getActivity(),
                    "Successful", Toast.LENGTH_SHORT);
            toast.show();
            res = true;
        } else {
            textHeading.setError("Please enter value");
            textDesc.setError("Please enter value");
            res = false;
        }
        return res;
    }
}
