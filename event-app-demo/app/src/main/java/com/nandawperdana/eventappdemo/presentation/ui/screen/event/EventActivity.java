package com.nandawperdana.eventappdemo.presentation.ui.screen.event;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.nandawperdana.eventappdemo.R;
import com.nandawperdana.eventappdemo.api.event.EventModel;
import com.nandawperdana.eventappdemo.presentation.presenter.EventPresenter;
import com.nandawperdana.eventappdemo.presentation.ui.adapter.EventAdapter;
import com.nandawperdana.eventappdemo.presentation.ui.screen.event.mvp.EventPresenterImpl;
import com.nandawperdana.eventappdemo.presentation.ui.screen.event.mvp.EventViewModel;
import com.nandawperdana.eventappdemo.presentation.ui.screen.main.MainActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class EventActivity extends AppCompatActivity implements EventPresenter.EventView {
    // UI references.
    @Bind(R.id.recyclerview_event)
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    @Bind(R.id.toolbar_event)
    Toolbar toolbar;
    @Bind(R.id.fragment_event)
    View fragmentView;

    EventPresenter mPresenter;
    EventViewModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        initLayout();

        ((EventAdapter) recyclerView.getAdapter()).setCallback(new EventAdapter.Callback() {
            @Override
            public void onClicked(int index) {
                doRetrieveModel().setEventChoosed(doRetrieveModel().getListEvents().get(index));
                MainActivity.eventNameListener.onButtonEventNameChanged(doRetrieveModel().getEventChoosed().getName());
                mPresenter.presentState(ViewState.OPEN_MAIN);
            }
        });
    }

    private void initLayout() {
        ButterKnife.bind(EventActivity.this);

        initToolbar(EventActivity.this, toolbar);

        this.mPresenter = new EventPresenterImpl(this);
        this.mModel = new EventViewModel();

        doRetrieveModel().setFm(getSupportFragmentManager());

        this.progressDialog = new ProgressDialog(EventActivity.this);
        progressDialog.setMessage("Memuat...");
        progressDialog.setCancelable(false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(EventActivity.this));
        recyclerView.setAdapter(new EventAdapter(doRetrieveModel().getListEvents(), EventActivity.this));

        mPresenter.presentState(ViewState.LOAD_EVENT);
    }

    private void initToolbar(AppCompatActivity appCompatActivity, Toolbar toolbar) {
        if (toolbar == null || appCompatActivity == null) {
            throw new IllegalArgumentException("toolbar or appCompatActivity is null");
        }
        appCompatActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar == null) {
            return;
        }

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Event");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                switch (doRetrieveModel().getScreenState()) {
                    case STATE_LIST:
                        onBackPressed();
                        break;
                    case STATE_MAP:
                        doRetrieveModel().setScreenState(EventViewModel.ScreenState.STATE_LIST);
                        mPresenter.presentState(ViewState.SHOW_SCREEN_STATE);
                        break;
                }
                return true;
            case R.id.action_add:
                doRetrieveModel().setScreenState(EventViewModel.ScreenState.STATE_MAP);
                mPresenter.presentState(ViewState.SHOW_SCREEN_STATE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgress(boolean flag) {
        if (flag)
            progressDialog.show();
        else progressDialog.dismiss();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(EventActivity.this, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String title, String message) {
        showToast(message);
    }


    @Override
    public void showState(ViewState viewState) {
        switch (viewState) {
            case IDLE:
                showProgress(false);
                break;
            case LOADING:
                showProgress(true);
                break;
            case SHOW_EVENT:
                showEvents();
                break;
            case OPEN_MAIN:
                openMain();
                break;
            case SHOW_SCREEN_STATE:
                showScreenState();
                break;
            case ERROR:
                showError(null, doRetrieveModel().getErrorMessage());
                break;
        }
    }

    private void showScreenState() {
        switch (doRetrieveModel().getScreenState()) {
            case STATE_LIST:
                recyclerView.setVisibility(View.VISIBLE);
                fragmentView.setVisibility(View.GONE);
                break;
            case STATE_MAP:
                recyclerView.setVisibility(View.GONE);
                fragmentView.setVisibility(View.VISIBLE);
                doRetrieveModel().setMapFragment(new MapFragment().newInstance());
                navigateTo(doRetrieveModel().getMapFragment());
                break;
        }
    }

    private void openMain() {
        onBackPressed();
    }

    private void showEvents() {
        doRetrieveModel().setScreenState(EventViewModel.ScreenState.STATE_LIST);
        mPresenter.presentState(ViewState.SHOW_SCREEN_STATE);

        mPresenter.presentState(ViewState.IDLE);
        doRetrieveModel().setListEvents();

        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void navigateTo(Fragment fragment) {
        if (!isFinishing()) {
            FragmentTransaction ft = doRetrieveModel().getFm().beginTransaction();
            fragment.setArguments(new Bundle());
            ft.replace(R.id.fragment_event, fragment);
            ft.commit();
        }
    }

    @Override
    public EventViewModel doRetrieveModel() {
        return this.mModel;
    }
}
