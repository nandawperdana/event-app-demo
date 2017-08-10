package com.nandawperdana.eventappdemo.presentation.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nandawperdana.eventappdemo.R;
import com.nandawperdana.eventappdemo.api.event.EventModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nandawperdana.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private List<EventModel> listEvents;
    private Context mContext;
    Callback mCallback;

    public EventAdapter(List<EventModel> listEvents, Context mContext) {
        this.listEvents = listEvents;
        this.mContext = mContext;
    }

    public interface Callback {
        void onClicked(int index);
    }

    public Callback getCallback() {
        return mCallback;
    }

    public void setCallback(Callback mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_event, parent, false);
        ViewHolder vh = new ViewHolder(v, this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindView(mContext, listEvents.get(position));
    }

    @Override
    public int getItemCount() {
        return listEvents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageview_row_event)
        ImageView imageView;
        @Bind(R.id.textview_row_event_title)
        TextView textView;
        @Bind(R.id.textview_row_event_subtitle)
        TextView textViewSubtitle;
        @Bind(R.id.textview_row_event_desc)
        TextView textViewDesc;
        @Bind(R.id.textview_row_event_tags)
        TextView textViewTags;
        EventAdapter mAdapter;

        public ViewHolder(View itemView, EventAdapter adapter) {
            super(itemView);
            initLayout(itemView);
            this.mAdapter = adapter;
        }

        private void initLayout(View view) {
            ButterKnife.bind(this, view);
        }

        public void bindView(Context context, EventModel data) {
            if (data.getName() != null)
                textView.setText(data.getName());
            if (data.getDate() != null)
                textViewSubtitle.setText(data.getDate());

            String url = data.getImageUrl();
            if (url == null)
                url = "https://t3.ftcdn.net/jpg/01/17/96/68/240_F_117966832_qprcRmPUtpmdsNNRigU3y0oSeT0yUSlB.jpg";
            Picasso.with(context)
                    .load(url)
                    .into(imageView);

            if (data.getDesc() != null)
                textViewDesc.setText(data.getDesc());
        }

        @OnClick(R.id.layout_row_event)
        public void onClick() {
            mAdapter.mCallback.onClicked(getAdapterPosition());
        }
    }
}
