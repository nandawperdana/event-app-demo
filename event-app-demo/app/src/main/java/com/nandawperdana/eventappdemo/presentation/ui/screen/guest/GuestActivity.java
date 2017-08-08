package com.nandawperdana.eventappdemo.presentation.ui.screen.guest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.nandawperdana.eventappdemo.R;
import com.nandawperdana.eventappdemo.presentation.presenter.GuestPresenter;
import com.nandawperdana.eventappdemo.presentation.ui.adapter.GuestAdapter;
import com.nandawperdana.eventappdemo.presentation.ui.screen.guest.mvp.GuestPresenterImpl;
import com.nandawperdana.eventappdemo.presentation.ui.screen.guest.mvp.GuestViewModel;
import com.nandawperdana.eventappdemo.presentation.ui.screen.main.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


public class GuestActivity extends AppCompatActivity implements GuestPresenter.GuestView {
    // UI references.
    @Bind(R.id.recyclerview_guest)
    RecyclerView recyclerView;
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
    }

    private void initLayout() {
        ButterKnife.bind(GuestActivity.this);

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

    @Override
    public void showProgress(boolean flag) {
        if (flag)
            progressDialog.show();
        else progressDialog.dismiss();
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
}
