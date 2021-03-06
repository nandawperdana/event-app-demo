package com.nandawperdana.eventappdemo.presentation.ui.screen.guest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.nandawperdana.eventappdemo.R;
import com.nandawperdana.eventappdemo.presentation.presenter.GuestPresenter;
import com.nandawperdana.eventappdemo.presentation.ui.adapter.GuestAdapter;
import com.nandawperdana.eventappdemo.presentation.ui.screen.guest.mvp.GuestPresenterImpl;
import com.nandawperdana.eventappdemo.presentation.ui.screen.guest.mvp.GuestViewModel;
import com.nandawperdana.eventappdemo.presentation.ui.screen.main.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


public class GuestActivity extends AppCompatActivity implements GuestPresenter.GuestView, SwipeRefreshLayout.OnRefreshListener {
    // UI references.
    @Bind(R.id.recyclerview_guest)
    RecyclerView recyclerView;
    @Bind(R.id.toolbar_guest)
    Toolbar toolbar;
    @Bind(R.id.swipe_guest)
    SwipeRefreshLayout swipeRefreshLayout;
    GridLayoutManager gridLayoutManager;
    ProgressDialog progressDialog;

    GuestPresenter mPresenter;
    GuestViewModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        initLayout();

        ((GuestAdapter) recyclerView.getAdapter()).setCallback(new GuestAdapter.Callback() {
            @Override
            public void onClicked(int index) {
                MainActivity.guestNameListener.onButtonGuestNameChanged(doRetrieveModel().getListPeople().get(index));
                mPresenter.presentState(ViewState.OPEN_MAIN);
            }
        });

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initLayout() {
        ButterKnife.bind(GuestActivity.this);

        initToolbar(GuestActivity.this, toolbar);

        this.mPresenter = new GuestPresenterImpl(this);
        this.mModel = new GuestViewModel();

        this.progressDialog = new ProgressDialog(GuestActivity.this);
        progressDialog.setMessage("Memuat...");
        progressDialog.setCancelable(false);

        recyclerView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(GuestActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new GuestAdapter(GuestActivity.this, doRetrieveModel().getListPeople()));

        mPresenter.presentState(ViewState.LOAD_GUEST);
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
        actionBar.setTitle("Guest");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgress(boolean flag) {
        swipeRefreshLayout.setRefreshing(flag);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(GuestActivity.this, "" + message, Toast.LENGTH_SHORT).show();
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
            case SHOW_GUEST:
                showGuest();
                break;
            case OPEN_MAIN:
                openMain();
                break;
            case ERROR:
                showError(null, doRetrieveModel().getErrorMessage());
                break;
        }
    }

    private void showGuest() {
        doRetrieveModel().setListPeople();

        recyclerView.getAdapter().notifyDataSetChanged();

        mPresenter.presentState(ViewState.IDLE);
    }

    private void openMain() {
        onBackPressed();
    }

    @Override
    public GuestViewModel doRetrieveModel() {
        return this.mModel;
    }

    @Override
    public void onRefresh() {
        mPresenter.presentState(ViewState.LOAD_GUEST);
    }
}
