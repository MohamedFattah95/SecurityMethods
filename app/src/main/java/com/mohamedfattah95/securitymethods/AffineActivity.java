package com.mohamedfattah95.securitymethods;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AffineActivity extends AppCompatActivity {

    @BindView(R.id.tvResult)
    TextView tvResult;

    @BindView(R.id.etText)
    EditText etText;

    @BindView(R.id.etKey)
    EditText etKey;

    @BindView(R.id.etM)
    EditText etM;

    @BindView(R.id.etAlpha)
    EditText etAlpha;

    @BindView(R.id.btnEncrypt)
    Button btnEncrypt;

    @BindView(R.id.btnDecrypt)
    Button btnDecrypt;

    String[] alpha;  /*= {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};*/

    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affine);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.affine);
        ButterKnife.bind(this);

        btnEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] data = etAlpha.getText().toString().split(" ");
                alpha = new String[data.length];
                System.arraycopy(data, 0, alpha, 0, data.length);

                if (etKey.getText().toString().length() < 1 || etM.getText().toString().length() < 1) {
                    Toast.makeText(getBaseContext(), "Key & M are required", Toast.LENGTH_SHORT).show();
                } else {
                    if (GCD(Integer.parseInt(etM.getText().toString()), alpha.length) != 1) {
                        Toast.makeText(getBaseContext(), "Can't encrypt", Toast.LENGTH_SHORT).show();
                    } else {
                        result = encrypt(etText.getText().toString().replace(" ", ""),
                                Integer.parseInt(etKey.getText().toString()),
                                Integer.parseInt(etM.getText().toString()));
                        tvResult.setText(result);
                    }
                }
            }
        });

        btnDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] data = etAlpha.getText().toString().split(" ");
                alpha = new String[data.length];
                System.arraycopy(data, 0, alpha, 0, data.length);

                if (etKey.getText().toString().length() < 1 || etM.getText().toString().length() < 1) {
                    Toast.makeText(getBaseContext(), "Key & M are required", Toast.LENGTH_SHORT).show();
                } else {
                    if (GCD(Integer.parseInt(etM.getText().toString()), alpha.length) != 1) {
                        Toast.makeText(getBaseContext(), "Can't decrypt", Toast.LENGTH_SHORT).show();
                    } else {
                        result = decrypt(etText.getText().toString().replace(" ", ""),
                                Integer.parseInt(etKey.getText().toString()),
                                Integer.parseInt(etM.getText().toString()));
                        tvResult.setText(result);
                    }
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

    private String encrypt(String plain, int key, int m) {
        String[] plainArray = toStringArray(plain);
        String[] cipher = new String[plain.length()];
        for (int i = 0; i < plain.length(); i++) {
            for (int j = 0; j < alpha.length; j++) {
                if (plainArray[i].equals(alpha[j])) {
                    String ch = alpha[checkPositivity(m * j + key) % alpha.length];
                    cipher[i] = ch;
                }
            }
        }
        return Arrays.toString(cipher).replace(" ", "")
                .replace("[", "").replace("]", "")
                .replace(",", "");
    }

    private String decrypt(String cipher, int key, int m) {
        String[] cipherArray = toStringArray(cipher);
        String[] plain = new String[cipher.length()];
        int mmi = BigInteger.valueOf(m).modInverse(BigInteger.valueOf(alpha.length)).intValue();
        for (int i = 0; i < cipher.length(); i++) {
            for (int j = 0; j < alpha.length; j++) {
                if (cipherArray[i].equals(alpha[j])) {
                    String ch = alpha[checkPositivity(mmi * (j - key)) % alpha.length];
                    plain[i] = ch;
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

    public int GCD(int m, int n) {
        if (n == 0) {
            return m;
        }
        return Math.abs(GCD(n, m % n));
    }

    public int checkPositivity(int n) {
        if (n > 0) {
            return n;
        }
        return checkPositivity(n + alpha.length);
    }
}
