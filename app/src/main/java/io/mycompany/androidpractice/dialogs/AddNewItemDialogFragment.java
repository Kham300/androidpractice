package io.mycompany.androidpractice.dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import io.mycompany.androidpractice.R;
import io.mycompany.androidpractice.model.Card;
import io.mycompany.androidpractice.util.DataUtilSimple;

public class AddNewItemDialogFragment extends DialogFragment {

    public static final String DIALOG_TAG = AddNewItemDialogFragment.class.getSimpleName();
    public static final int maxLengthOfInputChars = 21;

    private EditText textHeading;

    private EditText textDesc;

    private CheckBox checkBox;

    public AddNewItemDialogFragment() {
    }

    public static AddNewItemDialogFragment newInstance() {
        return new AddNewItemDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_item, container, false);

        //todo инициализацию переменных лучше выносить в отдельный метод init(view)

        textHeading = view.findViewById(R.id.editTextHeader);
        textHeading.addTextChangedListener(new TextWatcher() {
            //todo анонимный класс, когда он большой, лучше вынести в отдельный метод для лучшей читаемости
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("qwer", "onTextChanged: " + s + " " + start + " " + before + " " + count);

                String originalText = s.toString();
                int originalTextLength = originalText.length();
                int currentSelection = textHeading.getSelectionStart();

                StringBuilder sb = new StringBuilder();
                boolean hasChanged = true;
                for (int i = 0; i < originalTextLength; i++) {
                    char currentChar = originalText.charAt(i);
                    if (isAllowed(currentChar) && i < maxLengthOfInputChars) {
                        sb.append(currentChar);
                    } else {
                        hasChanged = false;
                        textHeading.setError("Please insert current letters");
                        if (currentSelection >= i) {
                            currentSelection--;
                        }
                    }
                }
                if (hasChanged) {
                    String newText = sb.toString();
                    textHeading.setText(capitalize(newText));
                    textHeading.setSelection(currentSelection);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("qwer", "afterTextChanged: " + s);
                int len = s.length();
                if (len > 20) {
                    textHeading.setError("must be less then 20 letters");
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
    private String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    public boolean makeNewItem() {
        boolean res = false;
        String heading = String.valueOf(textHeading.getText());
        String desc = String.valueOf(textDesc.getText());

        if ((heading.length() == 0) && (desc.length() == 0)) {
            textHeading.setError("Please enter value");
            textDesc.setError("Please enter value");
            res = false;
        }else if ((heading.length() == 0) && (desc.length() != 0)){
            textHeading.setError("Please enter value");
        } else if ((heading.length() != 0) && (desc.length() == 0)) {
            textDesc.setError("Please enter value");
        } else {
            DataUtilSimple.addNewItem(new Card(heading, desc, checkBox.isChecked()));
            Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();
            res = true;
        }
        return res;
    }

}
