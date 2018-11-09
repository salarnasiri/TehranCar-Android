package com.sqtehrancar.tehrancarandroid.contorller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sqtehrancar.tehrancarandroid.R;
import com.sqtehrancar.tehrancarandroid.model.Car;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarsRecyclerView extends  RecyclerView.Adapter<CarsRecyclerView.ViewHolder> {


    private Context context;
    private List<Car> list;
    private OnItemClickListener onItemClickListener;
    private boolean isAdmin = true;

    public CarsRecyclerView(Context context, List<Car> list,
                            OnItemClickListener onItemClickListener) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }

    public CarsRecyclerView(Context context, List<Car> list) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = null;
        isAdmin = false;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        // Todo Butterknife bindings
        @BindView(R.id.textview_car_name)
        TextView textviewCarName;
        @BindView(R.id.textview_car_factory)
        TextView textviewCarFactory;
        @BindView(R.id.textview_car_kilometer)
        TextView textviewCarKilometer;
        @BindView(R.id.textview_car_price)
        TextView textviewCarPrice;
        @BindView(R.id.button_car_delete)
        Button buttonCarDelete;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_layout_car, parent, false);
        ButterKnife.bind(this, view);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Car item = list.get(position);
        holder.textviewCarName.setText(item.getName());
        holder.textviewCarFactory.setText(item.getFactory());
        holder.textviewCarKilometer.setText(String.valueOf(item.getKilometer()));
        holder.textviewCarPrice.setText(String.valueOf(item.getPrice()));
        if (!isAdmin) {
            holder.buttonCarDelete.setVisibility(View.GONE);
        } else {
            holder.buttonCarDelete.setVisibility(View.VISIBLE);
            holder.buttonCarDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}