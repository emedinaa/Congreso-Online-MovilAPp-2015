package com.emedinaa.comovil2015;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.emedinaa.comovil2015.model.entity.SpeakerEntity;
import com.emedinaa.comovil2015.presenter.RetrofitPresenter;
import com.emedinaa.comovil2015.presenter.VolleyPresenter;
import com.emedinaa.comovil2015.utils.DividerItemDecorator;
import com.emedinaa.comovil2015.view.AddSpeakerActivity;
import com.emedinaa.comovil2015.view.adapters.SpeakerAdapter;
import com.emedinaa.comovil2015.view.core.BaseView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BaseView {

    private static final String TAG = "MainActivity";
    @Bind(R.id.rviSpeakers)
    RecyclerView rviSpeakers;

    @Bind(R.id.rlayLoading)
    View rlayLoading;

    @Bind(R.id.iviAdd)
    View iviAdd;

    @Bind(R.id.llayRetrofit)
    View llayRetrofit;

    @Bind(R.id.llayVolley)
    View llayVolley;

    private RecyclerView.LayoutManager mLayoutManager;
    private VolleyPresenter volleyPresenter;
    private RetrofitPresenter retrofitPresenter;
    private SpeakerAdapter speakerAdapter;
    private List<SpeakerEntity> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        ui();
        volleyPresenter= new VolleyPresenter(this,this);
        retrofitPresenter= new RetrofitPresenter(this,this);

        //events
        iviAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoAdd();
            }
        });

        llayRetrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoading(true);
                speakerAdapter.clear();
                retrofitPresenter.loadSpeakers();
            }
        });

        llayVolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoading(true);
                speakerAdapter.clear();
                volleyPresenter.loadSpeakers();
            }
        });


        //volleyPresenter.loadSpeakers();

        //agregar expositor
        //volleyPresenter.addSpeaker("Usuario","Demo","Test");
        /*SpeakerEntity speakerEntity= new SpeakerEntity();
        speakerEntity.setName("Usuario 1");
        speakerEntity.setLastname("Demo 1");
        speakerEntity.setSkill("Test 1");
        retrofitPresenter.addSpeaker(speakerEntity);*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        //cargar expositores
        showLoading(true);
        retrofitPresenter.loadSpeakers();
    }

    private void gotoAdd() {
        Intent intent= new Intent(this, AddSpeakerActivity.class);
        startActivity(intent);
    }

    private void ui() {
        rviSpeakers.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        rviSpeakers.setLayoutManager(mLayoutManager);
        rviSpeakers.addItemDecoration(new DividerItemDecorator(this, DividerItemDecorator.VERTICAL_LIST));
    }

    private void populate(List<SpeakerEntity> lsSpeakerEntities)
    {
        speakerAdapter= new SpeakerAdapter(this,lsSpeakerEntities);
        rviSpeakers.setAdapter(speakerAdapter);
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
        data= (List<SpeakerEntity>)(object);
        switch (type)
        {
            case 100:

                    if(data!=null)populate(data);
                break;
            case 101:
                    if(data!=null)populate(data);
                break;
        }
        showLoading(false);
    }

    @Override
    public void completeError(Object object, int type) {
        switch (type)
        {
            case 100:
                    Log.v(TAG, "volley complete error " + object);
                break;
            case 101:
                    Log.v(TAG, "retrofit complete error " + object);
                break;
        }
        showLoading(false);
    }
}
