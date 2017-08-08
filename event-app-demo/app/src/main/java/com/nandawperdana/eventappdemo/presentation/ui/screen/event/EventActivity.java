package com.nandawperdana.eventappdemo.presentation.ui.screen.event;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.nandawperdana.eventappdemo.R;
import com.nandawperdana.eventappdemo.presentation.presenter.EventPresenter;
import com.nandawperdana.eventappdemo.presentation.ui.adapter.EventAdapter;
import com.nandawperdana.eventappdemo.presentation.ui.screen.event.mvp.EventPresenterImpl;
import com.nandawperdana.eventappdemo.presentation.ui.screen.event.mvp.EventViewModel;
import com.nandawperdana.eventappdemo.presentation.ui.screen.main.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


public class EventActivity extends AppCompatActivity implements EventPresenter.EventView {
    // UI references.
    @Bind(R.id.recyclerview_event)
    RecyclerView recyclerView;
    ProgressDialog progressDialog;

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

        this.mPresenter = new EventPresenterImpl(this);
        this.mModel = new EventViewModel();

        this.progressDialog = new ProgressDialog(EventActivity.this);
        progressDialog.setMessage("Memuat...");
        progressDialog.setCancelable(false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(EventActivity.this));
        recyclerView.setAdapter(new EventAdapter(doRetrieveModel().getListEvents(), EventActivity.this));

        mPresenter.presentState(ViewState.LOAD_EVENT);
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
            case ERROR:
                showError(null, doRetrieveModel().getErrorMessage());
                break;
        }
    }

    private void openMain() {
        onBackPressed();
    }

    private void showEvents() {
        doRetrieveModel().setListEvents();

        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public EventViewModel doRetrieveModel() {
        return this.mModel;
    }
}
