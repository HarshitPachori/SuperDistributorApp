package com.app.superdistributor.sr.reports.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.superdistributor.R;
import com.app.superdistributor.sr.reports.models.RegisteredComplaintModel;
import com.app.superdistributor.sr.reports.models.ReplaceByDealerModel;

import java.util.ArrayList;

public class ReplaceByDealerAdapter extends RecyclerView.Adapter<ReplaceByDealerAdapter.ViewHolder>{

    Context context;
    ArrayList<ReplaceByDealerModel> list,filterList;

    public ReplaceByDealerAdapter(Context context, ArrayList<ReplaceByDealerModel> list) {
        this.context = context;
        this.list = list;
        this.filterList = new ArrayList<>(list);
    }
    public void filter(String date) {
        filterList.clear();
        for (ReplaceByDealerModel item : list) {
            Log.d("item",item.getDateOfPurchase());
            Log.d("item",date);
            if (item.getDateOfPurchase().toLowerCase().contains(date.toLowerCase())) {
                filterList.add(item);
            }
        }
        Log.d("item",filterList.toString());
        Log.d("item",list.toString());
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ReplaceByDealerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.register_complaint_report_item,parent,false);
       return new ReplaceByDealerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReplaceByDealerAdapter.ViewHolder holder, int position) {
        ReplaceByDealerModel model = filterList.get(position);
        holder.CustomerName.setText(model.getCustomerName());
        holder.DateOfPurchase.setText(model.getDateOfPurchase());
        holder.ModelNumber.setText(model.getNewProductSerialNumber());
        holder.ModelNumberHead.setText("New Product Serial Number");
        holder.PhoneNumber.setText(model.getPhoneNumber());

        holder.Status.setText(model.getStatus());
        holder.SerialNumber.setText(model.getSerialNumber());
        holder.ReportUrl.setMovementMethod(LinkMovementMethod.getInstance());
        holder.ReportUrl.setPaintFlags(holder.ReportUrl.getPaintFlags());
        holder.ReportUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(model.getReportUrl()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView CustomerName, DateOfPurchase, ModelNumber, PhoneNumber, ReportUrl, SerialNumber, Status,ModelNumberHead;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CustomerName = itemView.findViewById(R.id.complaint_customer_name);
            DateOfPurchase = itemView.findViewById(R.id.complaint_dateofpurchase);
            ModelNumber = itemView.findViewById(R.id.complaint_modelno);
            ModelNumberHead = itemView.findViewById(R.id.complaint_modelno_head);
            PhoneNumber = itemView.findViewById(R.id.complaint_phone);
            ReportUrl = itemView.findViewById(R.id.complaint_report);
            Status = itemView.findViewById(R.id.complaint_status);
            SerialNumber = itemView.findViewById(R.id.complaint_serialno);
        }
    }
}
