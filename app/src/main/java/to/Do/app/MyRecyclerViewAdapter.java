package to.Do.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<String> mData;
    private ItemClickListener mClickListener;

    MyRecyclerViewAdapter(Context context, List<String> data){
        this.layoutInflater = LayoutInflater.from(context);
        this.mData = data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_recyclerview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String title = mData.get(position);
        holder.naslovPodsjetnika.setText(title);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView naslovPodsjetnika,opisPodsjetnika;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            naslovPodsjetnika = itemView.findViewById(R.id.naslovPodsjetnika);
            opisPodsjetnika = itemView.findViewById(R.id.opisPodsjetnika);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mClickListener != null){
                mClickListener.onItemClick(view,getAdapterPosition());
            }
        }

        String getItem(int id){
            return mData.get(id);
        }
        }
    void setClickListener(ItemClickListener itemClickListener){
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(View view,int position);
    }

}
