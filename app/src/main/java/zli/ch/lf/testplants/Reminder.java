package zli.ch.lf.testplants;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import zli.ch.lf.testplants.Data.EntityKlasse;

/*
 * @author Luca Fahrni
 * @version 21.01.2021
 *
 * Reminder Klasse von Plants for Dummies
 *
 * Beinhaltet vor allem den ViewHolder(RecycleView), der ViewHolder beschreibt eine Element-Ansicht innerhalb der RecyclerView.
 *
 * @quelle: https://projectnotes.org/it-projects/simple-reminder-android-application-with-source-code/
 *          https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.ViewHolder
 */
public class Reminder extends RecyclerView.Adapter<Reminder.ViewHolder>
{
    List<EntityKlasse> entityKlassen;
    Context context;

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView eventText, timeAndDateText;
        private LinearLayout toplayout;

        public ViewHolder( @NonNull View itemView)
        {
            super(itemView);
            eventText = (TextView) itemView.findViewById(R.id.event);
            timeAndDateText = (TextView) itemView.findViewById(R.id.time_and_date);
            toplayout = (LinearLayout) itemView.findViewById(R.id.toplayout);
        }
    }

    public Reminder(Context context, List<EntityKlasse> entityKlassen)
    {
        this.context = context;
        this.entityKlassen = entityKlassen;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listings_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.eventText.setText(entityKlassen.get(position).getPlantname());
        holder.timeAndDateText.setText(entityKlassen.get(position).getPlantdate() + " " + entityKlassen.get(position).getPlanttime());
    }

    @Override
    public int getItemCount()
    {
        return entityKlassen.size();
    }
}
