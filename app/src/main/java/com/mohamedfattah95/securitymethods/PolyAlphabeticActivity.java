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

public class PolyAlphabeticActivity extends AppCompatActivity {

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

    String[] alpha = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poly_alphabetic);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.poly);
        ButterKnife.bind(this);

        btnEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etKey.getText().toString().length() < 1) {
                    Toast.makeText(getBaseContext(), "Key is required", Toast.LENGTH_SHORT).show();
                } else {
                    result = encrypt(etText.getText().toString().replace(" ", ""),
                            etKey.getText().toString().replace(" ", ""));
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
                    result = decrypt(etText.getText().toString().replace(" ", ""),
                            etKey.getText().toString().replace(" ", ""));
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

    private String encrypt(String plain, String key) {
        String[] keyArray = toStringArray(key);
        String[] plainArray = toStringArray(plain);
        String[] cipher = new String[plain.length()];
        for (int i = 0; i < plain.length(); i++) {
            for (int j = 0; j < alpha.length; j++) {
                if (keyArray[i % keyArray.length].equalsIgnoreCase(alpha[j])) {
                    for (int n = 0; n < alpha.length; n++) {
                        if (plainArray[i].equalsIgnoreCase(alpha[n])) {
                            String ch = alpha[checkPositivity(n + j) % alpha.length];
                            cipher[i] = ch;
                        }
                    }
                }
            }
        }
        return Arrays.toString(cipher).replace(" ", "")
                .replace("[", "").replace("]", "")
                .replace(",", "");
    }

    private String decrypt(String cipher, String key) {
        String[] keyArray = toStringArray(key);
        String[] cipherArray = toStringArray(cipher);
        String[] plain = new String[cipher.length()];
        for (int i = 0; i < cipher.length(); i++) {
            for (int j = 0; j < alpha.length; j++) {
                if (keyArray[i % keyArray.length].equalsIgnoreCase(alpha[j])) {
                    for (int n = 0; n < alpha.length; n++) {
                        if (cipherArray[i].equalsIgnoreCase(alpha[n])) {
                            String ch = alpha[checkPositivity(n - j) % alpha.length];
                            plain[i] = ch;
                        }
                    }
                }
            }
        }
        return Arrays.toString(plain).replace(" ", "")
                .replace("[", "").replace("]", "")
                .replace(",", "");
    }

    public String[] toStringArray(String s) {
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
