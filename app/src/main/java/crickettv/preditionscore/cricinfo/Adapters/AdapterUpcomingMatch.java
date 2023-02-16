package crickettv.preditionscore.cricinfo.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.AllMatchItem;
import crickettv.preditionscore.cricinfo.R;
import crickettv.preditionscore.cricinfo.databinding.AdapterUpcomingHomeBinding;

/* renamed from: com.indvssa.cricinfo.adapter.home.AdapterUpcomingMatch */
public class AdapterUpcomingMatch extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Activity activity;
    public ArrayList<AllMatchItem> crickalldata1 = new ArrayList<>();

    public AdapterUpcomingMatch(FragmentActivity activity2, ArrayList<AllMatchItem> crickalldata) {
        activity = activity2;
        crickalldata1 = crickalldata;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new UpcomingViewHolder(this, AdapterUpcomingHomeBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {

        AllMatchItem allMatchItem = crickalldata1.get(i);
        UpcomingViewHolder upcomingViewHolder = (UpcomingViewHolder) holder;
        upcomingViewHolder.matchBinding.txtTitle.setText(allMatchItem.getTitle());
        upcomingViewHolder.matchBinding.txtMatchTime.setText(allMatchItem.getMatchtime());
        Glide.with(activity).load(allMatchItem.getImageUrl()+allMatchItem.getTeamAImage()).error(R.mipmap.icon128).into(upcomingViewHolder.matchBinding.leftSideTeamImg);
        Glide.with(activity).load(allMatchItem.getImageUrl()+allMatchItem.getTeamBImage()).error(R.mipmap.icon128).into(upcomingViewHolder.matchBinding.rightSideTeamImg);
        upcomingViewHolder.matchBinding.txtLeftSideTeam.setText(allMatchItem.getTeamA());
        upcomingViewHolder.matchBinding.txtRightSideTeam.setText(allMatchItem.getTeamB());
        upcomingViewHolder.matchBinding.txtVenue.setText(allMatchItem.getVenue());
    }

    @Override
    public int getItemCount() {
        return crickalldata1.size();
    }

    public class UpcomingViewHolder extends RecyclerView.ViewHolder {
        public AdapterUpcomingHomeBinding matchBinding;

        public UpcomingViewHolder(@NonNull AdapterUpcomingMatch adapterUpcomingMatch, AdapterUpcomingHomeBinding AdapterUpcomingHomeBinding) {
            super(AdapterUpcomingHomeBinding.getRoot());
            this.matchBinding = AdapterUpcomingHomeBinding;
        }
    }
}
