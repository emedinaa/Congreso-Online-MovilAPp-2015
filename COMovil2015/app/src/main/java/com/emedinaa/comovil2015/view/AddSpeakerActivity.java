package com.emedinaa.comovil2015.view;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.emedinaa.comovil2015.R;
import com.emedinaa.comovil2015.presenter.RetrofitPresenter;
import com.emedinaa.comovil2015.presenter.VolleyPresenter;
import com.emedinaa.comovil2015.view.core.BaseView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddSpeakerActivity extends ActionBarActivity implements BaseView {

    @Bind(R.id.eTxtName)EditText eTxtName;
    @Bind(R.id.eTxtLastName)EditText eTxtLastName;
    @Bind(R.id.eTxtSkill)EditText eTxtSkill;
    @Bind(R.id.butAddSpeaker)View butAddSpeaker;
    @Bind(R.id.rlayLoading)View rlayLoading;

    private VolleyPresenter volleyPresenter;
    private RetrofitPresenter retrofitPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_speaker);
        ButterKnife.bind(this);
        volleyPresenter= new VolleyPresenter(this,this);
        retrofitPresenter= new RetrofitPresenter(this,this);
        events();
    }

    private void events() {

        butAddSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return false;
    }

    @Override
    public void showLoading(boolean b) {
        int visibility= (b)?(View.VISIBLE):(View.GONE);
        rlayLoading.setVisibility(visibility);
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void completeSuccess(Object object, int type) {

    }

    @Override
    public void completeError(Object object, int type) {

    }
}
