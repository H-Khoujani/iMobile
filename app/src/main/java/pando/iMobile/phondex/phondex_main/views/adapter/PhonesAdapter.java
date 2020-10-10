package pando.iMobile.phondex.phondex_main.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import pando.iMobile.R;
import pando.iMobile.phondex.phondex_main.db.entitys.Phone;

public class PhonesAdapter extends RecyclerView.Adapter<PhonesAdapter.PhonesViewHolder> {

    private List<Phone> phones = new ArrayList<>();
    onItemClickListener listener;

    @Override
    public void onBindViewHolder(@NonNull PhonesViewHolder holder, int position) {

        Phone currentSoldier = phones.get(position);

    }

    @NonNull
    @Override
    public PhonesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.activity_main,parent , false);
        return new PhonesViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }

    public void setPhones(List<Phone> soldiers)
    {
        this.phones = soldiers;
        notifyDataSetChanged();
    }

    public Phone getCurrent(int position){
        return phones.get(position);
    }

    public class PhonesViewHolder extends RecyclerView.ViewHolder{

        public PhonesViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.onClick(phones.get(getAdapterPosition()));
                    }
                }
            });

        }
    }

    public interface onItemClickListener{
        void onClick(Phone soldier);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.listener = listener;
    }

}
