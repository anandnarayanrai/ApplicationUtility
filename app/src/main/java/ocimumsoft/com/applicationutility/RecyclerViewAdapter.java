package ocimumsoft.com.applicationutility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder> {

    private Context context;
    private List<RecyclerViewModel> recyclerViewModels;

    RecyclerViewAdapter(Context context, List<RecyclerViewModel> recyclerViewModels) {
        this.context = context;
        this.recyclerViewModels = recyclerViewModels;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_recycler_view, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        RecyclerViewModel recyclerViewModel = recyclerViewModels.get(i);

        ((TextView) viewHolder.itemView.findViewById(R.id.tv_item_name)).setText(recyclerViewModel.getdName());
        ((TextView) viewHolder.itemView.findViewById(R.id.tv_item_discription)).setText(recyclerViewModel.getdDescriptions());
        ((ImageView) viewHolder.itemView.findViewById(R.id.iv_item_image)).setImageResource(recyclerViewModel.getdImage());

    }

    @Override
    public int getItemCount() {
        return recyclerViewModels.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        viewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
