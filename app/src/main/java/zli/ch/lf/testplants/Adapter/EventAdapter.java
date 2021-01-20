package zli.ch.lf.testplants.Adapter;

import android.content.Context;
import android.content.Entity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import zli.ch.lf.testplants.Data.EntityKlasse;
import zli.ch.lf.testplants.R;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    Context context;
    List<EntityKlasse> entityKlasses;

    public EventAdapter(Context context, List<EntityKlasse> entityKlasses) {
        this.context = context;
        this.entityKlasses = entityKlasses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listings_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.eventText.setText(entityKlasses.get(position).getPlantname());
        holder.timeAndDateText.setText(entityKlasses.get(position).getPlantdate() + " " + entityKlasses.get(position).getPlanttime());
    }

    @Override
    public int getItemCount() {
        return entityKlasses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView eventText, timeAndDateText;
        private LinearLayout toplayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventText = (TextView) itemView.findViewById(R.id.event);
            timeAndDateText = (TextView) itemView.findViewById(R.id.time_and_date);
            toplayout = (LinearLayout) itemView.findViewById(R.id.toplayout);
        }
    }

}
