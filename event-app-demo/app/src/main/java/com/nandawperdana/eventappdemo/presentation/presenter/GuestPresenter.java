package com.nandawperdana.eventappdemo.presentation.presenter;

import com.nandawperdana.eventappdemo.presentation.presenter.base.BasePresenter;
import com.nandawperdana.eventappdemo.presentation.presenter.base.BaseView;
import com.nandawperdana.eventappdemo.presentation.ui.screen.guest.mvp.GuestViewModel;

/**
 * Created by nandawperdana.
 */
public interface GuestPresenter extends BasePresenter {

    interface GuestView extends BaseView {
        /**
         * This enum is used for determine the current state of this screen
         */
        enum ViewState {
            IDLE, LOADING,
            LOAD_GUEST, SHOW_GUEST, OPEN_MAIN, ERROR
        }

        /**
         * This method is to show the current state of this screen
         *
         * @param viewState
         */
        void showState(ViewState viewState);

        /**
         * This function return the model that was belong to this screen
         *
         * @return
         */
        GuestViewModel doRetrieveModel();
    }

    /**
     * This method is used for presenting the current state of this screen
     *
     * @param state
     */
    void presentState(GuestView.ViewState state);
}
