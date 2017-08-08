package com.nandawperdana.eventappdemo.presentation.ui.screen.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nandawperdana.eventappdemo.R;
import com.nandawperdana.eventappdemo.api.people.PeopleModel;
import com.nandawperdana.eventappdemo.presentation.presenter.MainPresenter;
import com.nandawperdana.eventappdemo.presentation.ui.screen.event.EventActivity;
import com.nandawperdana.eventappdemo.presentation.ui.screen.guest.GuestActivity;
import com.nandawperdana.eventappdemo.presentation.ui.screen.main.mvp.MainPresenterImpl;
import com.nandawperdana.eventappdemo.presentation.ui.screen.main.mvp.MainViewModel;
import com.nandawperdana.eventappdemo.util.Common;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nandawperdana.
 */
public class MainActivity extends AppCompatActivity implements MainPresenter.MainView,
        MainViewModel.EventNameListener, MainViewModel.GuestNameListener {
    // UI references.
    @Bind(R.id.et_main_name)
    EditText editTextName;
    @Bind(R.id.progress_main)
    View progressView;
    @Bind(R.id.form_main)
    View mLoginFormView;
    @Bind(R.id.text_view_main_name)
    TextView textViewName;
    @Bind(R.id.layout_main)
    View layoutMain;
    @Bind(R.id.form_main_name)
    View layoutMainInput;
    @Bind(R.id.button_main_event)
    Button buttonEvent;
    @Bind(R.id.button_main_guest)
    Button buttonGuest;

    MainPresenter mPresenter;
    MainViewModel mModel;
    public static MainViewModel.EventNameListener eventNameListener;
    public static MainViewModel.GuestNameListener guestNameListener;

    @OnClick(R.id.button_main_login)
    public void onClickLogin() {
        attemptLogin();
    }

    @OnClick(R.id.button_main_event)
    public void onClickEvent() {
        mPresenter.presentState(ViewState.OPEN_EVENT);
    }

    @OnClick(R.id.button_main_guest)
    public void onClickGuest() {
        mPresenter.presentState(ViewState.OPEN_GUEST);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
    }

    private void initLayout() {
        ButterKnife.bind(MainActivity.this);

        this.mPresenter = new MainPresenterImpl(this);
        this.mModel = new MainViewModel();
        eventNameListener = this;
        guestNameListener = this;

        doRetrieveModel().setScreenState(MainViewModel.ScreenState.STATE_INPUT);
        mPresenter.presentState(ViewState.SHOW_SCREEN_STATE);
    }

    @Override
    public void onBackPressed() {
        switch (doRetrieveModel().getScreenState()) {
            case STATE_INPUT:
                super.onBackPressed();
                break;
            case STATE_NAME:
                doRetrieveModel().setScreenState(MainViewModel.ScreenState.STATE_INPUT);
                mPresenter.presentState(ViewState.SHOW_SCREEN_STATE);
                break;
        }
    }

    private void attemptLogin() {
        // Reset errors.
        editTextName.setError(null);

        // Store values at the time of the login attempt.
        String name = editTextName.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(name)) {
            editTextName.setError("Nama tidak boleh kosong");
            focusView = editTextName;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            textViewName.setText("Nama: " + name);
            mPresenter.presentState(ViewState.LOADING);
            doRetrieveModel().setScreenState(MainViewModel.ScreenState.STATE_NAME);
            mPresenter.presentState(ViewState.SHOW_SCREEN_STATE);
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(MainActivity.this, "" + message, Toast.LENGTH_LONG).show();
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
            case SHOW_SCREEN_STATE:
                showScreenState();
                break;
            case OPEN_EVENT:
                openEvent();
                break;
            case OPEN_GUEST:
                openGuest();
                break;
            case ERROR:
                showError(null, doRetrieveModel().getErrorMessage());
                break;
        }
    }

    private void showScreenState() {
        mPresenter.presentState(ViewState.IDLE);
        switch (doRetrieveModel().getScreenState()) {
            case STATE_INPUT:
                layoutMain.setVisibility(View.GONE);
                layoutMainInput.setVisibility(View.VISIBLE);
                break;
            case STATE_NAME:
                Common.getInstance().hideSoftKeyboard(MainActivity.this);
                layoutMain.setVisibility(View.VISIBLE);
                layoutMainInput.setVisibility(View.GONE);
                break;
        }
    }

    private void openEvent() {
        Intent intent = new Intent(MainActivity.this, EventActivity.class);
        startActivity(intent);
    }

    private void openGuest() {
        Intent intent = new Intent(MainActivity.this, GuestActivity.class);
        startActivity(intent);
    }

    @Override
    public MainViewModel doRetrieveModel() {
        return this.mModel;
    }

    @Override
    public void onButtonEventNameChanged(String name) {
        buttonEvent.setText(name);
    }

    @Override
    public void onButtonGuestNameChanged(PeopleModel model) {
        buttonGuest.setText(model.getName());
        int date = Common.getInstance().getDate(model.getBirthdate());
        String message = "feature phone";
        if (date % 2 == 0 && date % 3 == 0) {
            message = "iOS";
        } else if (date % 3 == 0) {
            message = "Android";
        } else if (date % 2 == 0) {
            message = "BlackBerry";
        }
        showToast(message);
    }
}

