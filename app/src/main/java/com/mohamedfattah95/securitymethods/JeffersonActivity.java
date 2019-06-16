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

public class JeffersonActivity extends AppCompatActivity {

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

    String[] alpha = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
            "A", "S", "D", "F", "G", "H", "J", "K", "L",
            "Z", "X", "C", "V", "B", "N", "M"};

    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jefferson);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.jeff);
        ButterKnife.bind(this);

        btnEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etKey.getText().toString().length() < 1) {
                    Toast.makeText(getBaseContext(), "Key is required", Toast.LENGTH_SHORT).show();
                } else {
                    result = encrypt(etText.getText().toString().replace(" ",""),
                            Integer.parseInt(etKey.getText().toString()));
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
                    result = decrypt(etText.getText().toString().replace(" ",""),
                            Integer.parseInt(etKey.getText().toString()));
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

    private String encrypt(String plain, int key) {
        String[] plainArray = toStringArray(plain);
        String[] cipher = new String[plain.length()];
        for (int i = 0; i < plain.length(); i++) {
            for (int j = 0; j < alpha.length; j++) {
                if (plainArray[i].equalsIgnoreCase(alpha[j])) {
                    String ch = alpha[checkPositivity(j + key) % alpha.length];
                    cipher[i] = ch;
                }
            }
        }
        return Arrays.toString(cipher).replace(" ", "")
                .replace("[", "").replace("]", "")
                .replace(",", "");
    }

    private String decrypt(String cipher, int key) {
        String[] cipherArray = toStringArray(cipher);
        String[] plain = new String[cipher.length()];
        for (int i = 0; i < cipher.length(); i++) {
            for (int j = 0; j < alpha.length; j++) {
                if (cipherArray[i].equalsIgnoreCase(alpha[j])) {
                    String ch = alpha[checkPositivity(j - key) % alpha.length];
                    plain[i] = ch;
                }
            }
        }
        return Arrays.toString(plain).replace(" ", "")
                .replace("[", "").replace("]", "")
                .replace(",", "");
    }

    public static String[] toStringArray(String s) {
        String[] str = new String[s.length()];
        int j = 1;
        for (int i = 0; i < s.length(); i++) {
            str[i] = s.substring(i, j);
            j++;
        }
        return str;
    }

    public int checkPositivity(int n) {
        if (n > 0) {
            return n;
        }
        return checkPositivity(n + alpha.length);
    }

}
