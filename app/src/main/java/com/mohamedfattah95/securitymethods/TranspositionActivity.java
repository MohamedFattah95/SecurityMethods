package com.mohamedfattah95.securitymethods;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TranspositionActivity extends AppCompatActivity {

    @BindView(R.id.tvResult)
    TextView tvResult;

    @BindView(R.id.etText)
    EditText etText;

    @BindView(R.id.etKey)
    EditText etKey;

    @BindView(R.id.btnEncrypt)
    Button btnEncrypt;

    @BindView(R.id.btnDecrypt)
    Button btnDecrypt;

    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transposition);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.transposition);
        ButterKnife.bind(this);

        btnEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etKey.getText().toString().length() < 1) {
                    Toast.makeText(getBaseContext(), "Key is required", Toast.LENGTH_SHORT).show();
                } else {
                    result = Encryption(etText.getText().toString(), removeDuplicate(etKey.getText().toString()));
                    tvResult.setText(result);
                }
            }
        });

        btnDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etKey.getText().toString().length() < 1) {
                    Toast.makeText(getBaseContext(), "Key is required", Toast.LENGTH_SHORT).show();
                } else {
                    result = Decryption(etText.getText().toString(), removeDuplicate(etKey.getText().toString()));
                    tvResult.setText(result);
                }
            }
        });

        tvResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etText.setText(tvResult.getText().toString());
            }
        });
    }

    public static String Encryption(String plainTxt, String key) {

        //1-make plaintxt without space and all char are small
        plainTxt = plainTxt.toLowerCase();
        plainTxt = plainTxt.replace(" ", "");
        char plainTxt1[] = plainTxt.toCharArray();

        //2-mak key small and sort it by alphapitChar
        key = key.toLowerCase();
        key = key.replace(" ", "");
        char keyaArr[] = key.toCharArray();

        //3 create matrix
        int column = keyaArr.length;
        int row = ((int) Math.ceil(plainTxt1.length / (column * 1.0))) + 1;

        char[][] matrix = new char[row][column];

        //insert the key in the frist row of matrix
        for (int j = 0; j < column; j++) {
            matrix[0][j] = keyaArr[j];
        }

        //insert  plain text in matrix
        char[] arr = {',', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z'};

        int count_plain = 0;
        for (int i = 1; i < row; i++) {
            int count_arr = 0;
            for (int j = 0; j < column; j++) {
                if (count_plain < plainTxt1.length) {
                    matrix[i][j] = plainTxt1[count_plain];
                    count_plain++;
                } else {
                    matrix[i][j] = arr[count_arr];
                    count_arr++;
                }
            }
        }

        //create cipher text:
        Arrays.sort(keyaArr);
        String cipher = "";
        char temp;
        for (int k = 0; k < keyaArr.length; k++) {
            for (int j = 0; j < column; j++) {
                if (keyaArr[k] == matrix[0][j]) {
                    for (int i = 1; i < row; i++) {
                        temp = matrix[i][j];
                        if (temp == ',') {
                            temp = 'a';
                        }
                        cipher += temp;
                    }
                }
            }
        }

        return cipher;
    }

    /////****************************************DECRYPTION METHOD
    public static String Decryption(String cipherTxt, String key) {

        //1-ciphertxt smallest char and convert to charArry
        cipherTxt = cipherTxt.replace(" ", "");
        cipherTxt = cipherTxt.toLowerCase();
        char[] cipherTxt1 = cipherTxt.toCharArray();

        //2-make key small by alphapitChar
        key = key.toLowerCase();
        key = key.replace(" ", "");
        char keyaArr[] = key.toCharArray();

        //create matrix
        int column = keyaArr.length;
        int row = (cipherTxt1.length / column) + 1;

        char[][] matrix = new char[row][column];

        //insert the key in the frist row of matrix
        for (int j = 0; j < column; j++) {
            matrix[0][j] = keyaArr[j];
        }

        //insert cipher text in matrix
        Arrays.sort(keyaArr);
        int countCipher = 0;
        for (int k = 0; k < keyaArr.length; k++) {
            for (int j = 0; j < column; j++) {
                if (keyaArr[k] == matrix[0][j]) {
                    for (int i = 1; i < row; i++) {
                        matrix[i][j] = cipherTxt1[countCipher];
                        countCipher++;
                    }
                }
            }
        }

        //create plain text
        String plainTxt = "";
        char temp;
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < column; j++) {
                temp = matrix[i][j];
                plainTxt += temp;
            }
        }

        return plainTxt;
    }

    public static String removeDuplicate(String s) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // if c is present in str, it returns
            // the index of c, else it returns -1
            if (str.toString().indexOf(c) < 0) {
                // adding c to str if -1 is returned
                str.append(c);
            }
        }

        return str.toString();
    }
}
