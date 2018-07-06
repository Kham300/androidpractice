package io.mycompany.androidpractice.dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
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

    public static final String DIALOG_TAG = AddNewItemDialogFragment.class.getSimpleName();
    public static final int maxLengthOfInputChars = 21;
    private EditText textHeading;
    private EditText textDesc;
    private CheckBox checkBox;
    boolean hasChanged = true;
    boolean notAllow = false;

    public AddNewItemDialogFragment() {
    }

    public static AddNewItemDialogFragment newInstance() {
        return new AddNewItemDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_item, container, false);

        textHeading = view.findViewById(R.id.editTextHeader);
        textHeading.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("qwer", "onTextChanged: " + s + " " + start + " " + before + " " + count);

                String originalText = s.toString();
                int originalTextLength = originalText.length();
                int currentSelection = textHeading.getSelectionStart();
                if (originalTextLength - 1 < 0) {
                    notAllow = true;
                }

                if (!notAllow && hasChanged) {
                    hasChanged = false;
                        if (isAllowed(s.charAt(originalTextLength - 1))) {
                            if (originalTextLength == 1) {
                                textHeading.setText(originalText.substring(0, 1).toUpperCase() + originalText.substring(1, originalText.length()));
                                textHeading.setSelection(currentSelection);
                            } else {
                                StringBuilder sb = new StringBuilder();
                                boolean hasChangedInner = false;
                                for (int i = 0; i < originalTextLength; i++) {
                                    char currentChar = originalText.charAt(i);
                                    if (i < maxLengthOfInputChars) {
                                        sb.append(currentChar);
                                        hasChangedInner = true;
                                    } else {
                                        hasChangedInner = false;
                                    }
                                }
                                if (hasChangedInner) {
                                    String newText = sb.toString();
                                    textHeading.setText(newText);
                                    textHeading.setSelection(currentSelection);
                                }
                            }
                        } else {
                            textHeading.setText(originalText.substring(0, originalTextLength - 1));
                            textHeading.setError("Please insert current letters");
                            textHeading.setSelection(currentSelection - 1);
                        }
                    } else {
                        hasChanged = true;
                    }
                }

            @Override
            public void afterTextChanged(Editable s) {
                int len = s.length();
                String originalText = s.toString();
                if (len > maxLengthOfInputChars) {
                    textHeading.setError("must be less then 20 letters");
                    textHeading.setText(originalText.substring(0, len -1));
                    textHeading.setSelection(len -1);
                }
            }
        });

        textDesc = view.findViewById(R.id.editTextDescription);
        Button button = view.findViewById(R.id.btnAdd);
        checkBox = view.findViewById(R.id.checkBoxDialog);
        checkBox.setChecked(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (makeNewItem()) {
                    dismiss();
                }
            }
        });
        return view;
    }

    private boolean isAllowed(char currentChar) {
        return Character.isLetter(currentChar) && !Character.isSpaceChar(currentChar);
    }


    public boolean makeNewItem() {
        boolean res = false;
        String heading = String.valueOf(textHeading.getText());
        String desc = String.valueOf(textDesc.getText());

        if ((heading.length() == 0) && (desc.length() == 0)) {
            textHeading.setError("Please enter value");
            textDesc.setError("Please enter value");
            res = false;
        }else if (heading.length() == 0){
            textHeading.setError("Please enter value");
        } else if (desc.length() == 0) {
            textDesc.setError("Please enter value");
        } else {
            DataUtilSimple.addNewItem(new Card(heading, desc, checkBox.isChecked()));
            Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();
            res = true;
        }
        return res;
    }
}
