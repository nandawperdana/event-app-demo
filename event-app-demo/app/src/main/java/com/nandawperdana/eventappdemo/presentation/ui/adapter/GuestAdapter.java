package com.nandawperdana.eventappdemo.presentation.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nandawperdana.eventappdemo.R;
import com.nandawperdana.eventappdemo.api.people.PeopleModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by NwP.
 */
public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.ViewHolder> {
    private Context mContext;
    private List<PeopleModel> itemList;
    Callback mCallback;

    public GuestAdapter(Context mContext, List<PeopleModel> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
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
    public GuestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item_guest, parent, false);
        ViewHolder viewHolder = new ViewHolder(v, this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GuestAdapter.ViewHolder holder, int position) {
        holder.bindView(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageview_grid_guest)
        ImageView mImageView;
        @Bind(R.id.textview_grid_guest)
        TextView mTextView;
        GuestAdapter mAdapter;


        public ViewHolder(View itemView, GuestAdapter adapter) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mAdapter = adapter;
        }

        public void bindView(PeopleModel item) {
            String url = "https://t3.ftcdn.net/jpg/01/17/96/68/240_F_117966832_qprcRmPUtpmdsNNRigU3y0oSeT0yUSlB.jpg";
            Picasso.with(mContext)
                    .load(url)
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(mImageView);

            mTextView.setText(item.getName());
        }

        @OnClick(R.id.imageview_grid_guest)
        public void onClick() {
            mAdapter.mCallback.onClicked(getAdapterPosition());
        }
    }
}
