package com.nandawperdana.eventappdemo.presentation.presenter;

import com.nandawperdana.eventappdemo.presentation.presenter.base.BasePresenter;
import com.nandawperdana.eventappdemo.presentation.presenter.base.BaseView;
import com.nandawperdana.eventappdemo.presentation.ui.screen.event.mvp.EventViewModel;

/**
 * Created by nandawperdana.
 */
public interface EventPresenter extends BasePresenter {

    interface EventView extends BaseView {
        /**
         * This enum is used for determine the current state of this screen
         */
        enum ViewState {
            IDLE, LOADING,
            LOAD_EVENT, SHOW_EVENT, OPEN_MAIN, ERROR
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
        EventViewModel doRetrieveModel();
    }

    /**
     * This method is used for presenting the current state of this screen
     *
     * @param state
     */
    void presentState(EventView.ViewState state);
}
