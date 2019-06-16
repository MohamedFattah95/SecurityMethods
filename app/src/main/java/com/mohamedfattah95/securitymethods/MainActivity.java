package com.mohamedfattah95.securitymethods;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnTransposition)
    public void transposition() {
        startActivity(new Intent(this, TranspositionActivity.class));
    }

    @OnClick(R.id.btnAffine)
    public void affine() {
        startActivity(new Intent(this, AffineActivity.class));
    }

    @OnClick(R.id.btnRSA)
    public void RSA() {
        startActivity(new Intent(this, RSAActivity.class));
    }

    @OnClick(R.id.btnCeaser)
    public void ceaser() {
        startActivity(new Intent(this, CeaserActivity.class));
    }

    @OnClick(R.id.btnPolyAlpha)
    public void polyAlpha() {
        startActivity(new Intent(this, PolyAlphabeticActivity.class));
    }

    @OnClick(R.id.btnJeff)
    public void jefferson() {
        startActivity(new Intent(this, JeffersonActivity.class));
    }
}
