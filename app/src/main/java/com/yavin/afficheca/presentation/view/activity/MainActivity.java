package com.yavin.afficheca.presentation.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import com.yavin.afficheca.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Main application screen. This is the app entry point.
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_LoadData)
    FloatingActionButton btn_LoadData;

//    @BindView(R.id.tv_intro)
//    TextView tv_intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /**
     * Goes to the event list screen.
     */
    @OnClick(R.id.btn_LoadData)
    void navigateToUserList() {
        this.navigator.navigateToEventList(this);
    }
}
