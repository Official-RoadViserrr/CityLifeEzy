package com.citylifeezy.citylifeezy.adapters;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.citylifeezy.citylifeezy.models.PhoneNumber;
import com.citylifeezy.citylifeezy.activity.R;

import java.util.ArrayList;


public class ImportantNumberCardViewAdapter extends RecyclerView.Adapter<ImportantNumberCardViewAdapter.ViewHolder> {

    private static ArrayList<PhoneNumber> dataSet;

    public ImportantNumberCardViewAdapter(ArrayList<PhoneNumber> card) {

        dataSet = card;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.important_number_card_view_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        final PhoneNumber fp = dataSet.get(i);
        viewHolder.tvName.setText(fp.getName());
        viewHolder.tvPhoneOne.setText(fp.getPhone1());
        viewHolder.tvPhoneTwo.setText(fp.getPhone2());
        viewHolder.phone = fp;
        viewHolder.tvPhoneOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "+88" + fp.getPhone1();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                v.getContext().startActivity(intent);
            }
        });
        viewHolder.tvPhoneTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "+88" + fp.getPhone2();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvPhoneOne;
        public TextView tvPhoneTwo;

        public PhoneNumber phone;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            tvName = (TextView) itemLayoutView
                    .findViewById(R.id.tvName);
            tvPhoneOne = (TextView) itemLayoutView
                    .findViewById(R.id.tvPhoneOne);
            tvPhoneTwo = (TextView) itemLayoutView
                    .findViewById(R.id.tvPhoneTwo);
        }

    }
}