package com.navinnayak.android.basistask.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.navinnayak.android.basistask.R;
import com.navinnayak.android.basistask.models.BasisData;

import java.util.List;

public class BasisDataAdapter extends RecyclerView.Adapter<BasisDataAdapter.CustomViewHolder> {

    private List<BasisData> mBasisData;

    public BasisDataAdapter(List<BasisData> mBasisData) {
        this.mBasisData = mBasisData;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        BasisData basisData = mBasisData.get(position);
        holder.basisId.setText(basisData.getId());
        holder.basisText.setText(basisData.getText());
    }

    @Override
    public int getItemCount() {
        return mBasisData.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView basisId;
        public TextView basisText;

        public CustomViewHolder(View view) {
            super(view);
            basisId = view.findViewById(R.id.text_id);
            basisText = view.findViewById(R.id.content);

        }
    }
}