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

public class RSAActivity extends AppCompatActivity {

    @BindView(R.id.tvResult1)
    TextView tvResult1;

    @BindView(R.id.etText1)
    EditText etText1;

    @BindView(R.id.etP1)
    EditText etP1;

    @BindView(R.id.etQ1)
    EditText etQ1;

    @BindView(R.id.etE1)
    EditText etE1;

    @BindView(R.id.btnEncrypt1)
    Button btnEncrypt1;

    @BindView(R.id.btnDecrypt1)
    Button btnDecrypt1;

    @BindView(R.id.tvResult2)
    TextView tvResult2;

    @BindView(R.id.etText2)
    EditText etText2;

    @BindView(R.id.etP2)
    EditText etP2;

    @BindView(R.id.etQ2)
    EditText etQ2;

    @BindView(R.id.etE2)
    EditText etE2;

    @BindView(R.id.btnEncrypt2)
    Button btnEncrypt2;

    @BindView(R.id.btnDecrypt2)
    Button btnDecrypt2;

    long[] cipherResult;
    StringBuilder plainResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.rsa);
        ButterKnife.bind(this);

        btnEncrypt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etP2.getText().toString().length() < 1 || etQ2.getText().toString().length() < 1
                        || etE2.getText().toString().length() < 1) {
                    Toast.makeText(getBaseContext(), "P & Q & E are required", Toast.LENGTH_SHORT).show();
                } else {
                    if (!isPrime(Integer.parseInt(etP2.getText().toString())) ||
                            !isPrime(Integer.parseInt(etQ2.getText().toString()))) {
                        Toast.makeText(getBaseContext(), "P & Q must be prime numbers", Toast.LENGTH_SHORT).show();
                    } else {
                        long z = (Long.parseLong(etP2.getText().toString()) - 1) *
                                (Long.parseLong(etQ2.getText().toString()) - 1);

                        if (GCD(Long.parseLong(etE2.getText().toString()), z) != 1 ||
                                Long.parseLong(etE2.getText().toString()) > z) {
                            Toast.makeText(getBaseContext(), "Can't encrypt", Toast.LENGTH_SHORT).show();
                        } else {
                            cipherResult = encrypt(etText1.getText().toString().replace(" ", ""),
                                    Long.parseLong(etP2.getText().toString()),
                                    Long.parseLong(etQ2.getText().toString()),
                                    Long.parseLong(etE2.getText().toString()));
                            tvResult1.setText(Arrays.toString(cipherResult)
                                    .replace("[", "")
                                    .replace("]", "")
                                    .replace(",", ""));
                            tvResult2.setText(etText1.getText().toString());
                        }
                    }
                }
            }
        });

        btnDecrypt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etP2.getText().toString().length() < 1 || etQ2.getText().toString().length() < 1
                        || etE2.getText().toString().length() < 1) {
                    Toast.makeText(getBaseContext(), "P & Q & E are required", Toast.LENGTH_SHORT).show();
                } else {
                    if (!isPrime(Integer.parseInt(etP2.getText().toString())) ||
                            !isPrime(Integer.parseInt(etQ2.getText().toString()))) {
                        Toast.makeText(getBaseContext(), "P & Q must be prime numbers", Toast.LENGTH_SHORT).show();
                    } else {
                        long z = (Long.parseLong(etP2.getText().toString()) - 1) *
                                (Long.parseLong(etQ2.getText().toString()) - 1);

                        if (GCD(Long.parseLong(etE2.getText().toString()), z) != 1 ||
                                Long.parseLong(etE2.getText().toString()) > z) {
                            Toast.makeText(getBaseContext(), "Can't decrypt", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                String[] data = etText1.getText().toString().split(" ");
                                long[] input = new long[data.length];
                                for (int i = 0; i < data.length; i++) {
                                    input[i] = Long.parseLong(data[i]);
                                }

                                plainResult = decrypt(input,
                                        Long.parseLong(etP2.getText().toString()),
                                        Long.parseLong(etQ2.getText().toString()),
                                        Long.parseLong(etE2.getText().toString()));
                                tvResult1.setText(plainResult.toString());
                                tvResult2.setText(plainResult.toString());
                            } catch (Exception e) {
                                Toast.makeText(getBaseContext(), "Wrong format", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }
            }
        });

        btnEncrypt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etP1.getText().toString().length() < 1 || etQ1.getText().toString().length() < 1
                        || etE1.getText().toString().length() < 1) {
                    Toast.makeText(getBaseContext(), "P & Q & E are required", Toast.LENGTH_SHORT).show();
                } else {
                    if (!isPrime(Integer.parseInt(etP1.getText().toString())) ||
                            !isPrime(Integer.parseInt(etQ1.getText().toString()))) {
                        Toast.makeText(getBaseContext(), "P & Q must be prime numbers", Toast.LENGTH_SHORT).show();
                    } else {
                        long z = (Long.parseLong(etP1.getText().toString()) - 1) *
                                (Long.parseLong(etQ1.getText().toString()) - 1);

                        if (GCD(Long.parseLong(etE1.getText().toString()), z) != 1 ||
                                Long.parseLong(etE1.getText().toString()) > z) {
                            Toast.makeText(getBaseContext(), "Can't encrypt", Toast.LENGTH_SHORT).show();
                        } else {
                            cipherResult = encrypt(etText2.getText().toString().replace(" ", ""),
                                    Long.parseLong(etP1.getText().toString()),
                                    Long.parseLong(etQ1.getText().toString()),
                                    Long.parseLong(etE1.getText().toString()));
                            tvResult2.setText(Arrays.toString(cipherResult)
                                    .replace("[", "")
                                    .replace("]", "")
                                    .replace(",", ""));
                            tvResult1.setText(etText2.getText().toString());
                        }
                    }
                }
            }
        });

        btnDecrypt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etP1.getText().toString().length() < 1 || etQ1.getText().toString().length() < 1
                        || etE1.getText().toString().length() < 1) {
                    Toast.makeText(getBaseContext(), "P & Q & E are required", Toast.LENGTH_SHORT).show();
                } else {
                    if (!isPrime(Integer.parseInt(etP1.getText().toString())) ||
                            !isPrime(Integer.parseInt(etQ1.getText().toString()))) {
                        Toast.makeText(getBaseContext(), "P & Q must be prime numbers", Toast.LENGTH_SHORT).show();
                    } else {
                        long z = (Long.parseLong(etP1.getText().toString()) - 1) *
                                (Long.parseLong(etQ1.getText().toString()) - 1);

                        if (GCD(Long.parseLong(etE1.getText().toString()), z) != 1 ||
                                Long.parseLong(etE1.getText().toString()) > z) {
                            Toast.makeText(getBaseContext(), "Can't decrypt", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                String[] data = etText2.getText().toString().split(" ");
                                long[] input = new long[data.length];
                                for (int i = 0; i < data.length; i++) {
                                    input[i] = Long.parseLong(data[i]);
                                }

                                plainResult = decrypt(input,
                                        Long.parseLong(etP1.getText().toString()),
                                        Long.parseLong(etQ1.getText().toString()),
                                        Long.parseLong(etE1.getText().toString()));
                                tvResult2.setText(plainResult.toString());
                                tvResult1.setText(plainResult.toString());
                            } catch (Exception e) {
                                Toast.makeText(getBaseContext(), "Wrong format", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }
            }
        });

        tvResult1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etText1.setText(tvResult1.getText().toString());
            }
        });

        tvResult2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etText2.setText(tvResult2.getText().toString());
            }
        });
    }

    private long[] encrypt(String plain, long p, long q, long e) {
        long n = p * q;
        long z = (p - 1) * (q - 1);
        long d = BigInteger.valueOf(e).modInverse(BigInteger.valueOf(z)).longValue();
        if (d == e) {
            d += z;
        }
        long[] cipher = new long[plain.length()];
        for (int i = 0; i < plain.length(); i++) {
            cipher[i] = power((long) (plain.charAt(i) - 'A'), e, n);
        }
        return cipher;
    }

    private StringBuilder decrypt(long[] cipher, long p, long q, long e) {
        long n = p * q;
        long z = (p - 1) * (q - 1);
        long d = BigInteger.valueOf(e).modInverse(BigInteger.valueOf(z)).longValue();
        if (d == e) {
            d += z;
        }
        StringBuilder plain = new StringBuilder();
        for (int i = 0; i < cipher.length; i++) {
            plain.append((char) (power(cipher[i], d, n) + 'A'));
        }
        return plain;
    }

    /* Iterative Function to calculate
            (x^y)%p in O(log y) */
    long power(long x, long y, long p) {
        // Initialize result
        long res = 1;

        // Update x if it is more
        // than or equal to p
        x = x % p;

        while (y > 0) {
            // If y is odd, multiply x
            // with result
            if ((y & 1) == 1) {
                res = (res * x) % p;
            }

            // y must be even now
            // y = y / 2
            y = y >> 1;
            x = (x * x) % p;
        }
        return res;
    }

    public boolean isPrime(long n) {
        // Converting long to BigInteger
        BigInteger b = new BigInteger(String.valueOf(n));

        return b.isProbablePrime(1);
    }

    public long GCD(long m, long n) {
        if (n == 0) {
            return m;
        }
        return Math.abs(GCD(n, m % n));
    }
}
