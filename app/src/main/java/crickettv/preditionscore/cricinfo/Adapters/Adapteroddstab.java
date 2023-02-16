package crickettv.preditionscore.cricinfo.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.MatchstItem;
import crickettv.preditionscore.cricinfo.R;
import crickettv.preditionscore.cricinfo.databinding.AdapterMatchOddsBinding;

/* renamed from: com.indvssa.cricinfo.adapter.home.AdapterUpcomingMatch */
public class Adapteroddstab extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Activity activity;
    public ArrayList<MatchstItem> matchresultdata1 = new ArrayList<>();

    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;

    public Adapteroddstab(FragmentActivity activity2, ArrayList<MatchstItem> matchresultdata) {
        activity = activity2;
        matchresultdata1 = matchresultdata;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case VIEW_TYPE_NORMAL:
                return new OddsdetauilViewHolder(this, AdapterMatchOddsBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
            case VIEW_TYPE_LOADING:
                return new ProgressHolder(LayoutInflater.from(activity).inflate(R.layout.item_loading, viewGroup, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {

        if (holder.getItemViewType() == VIEW_TYPE_NORMAL) {
            MatchstItem matchstItem = matchresultdata1.get(i);
            OddsdetauilViewHolder oddsdetauilViewHolder = (OddsdetauilViewHolder) holder;
            oddsdetauilViewHolder.oddsBinding.Score.setText(matchstItem.getScore().trim());
            oddsdetauilViewHolder.oddsBinding.oversions.setText(matchstItem.getOvers().trim());
            oddsdetauilViewHolder.oddsBinding.time.setText(matchstItem.getSubdate().substring(matchstItem.getSubdate().indexOf(" "), matchstItem.getSubdate().lastIndexOf(":")));
            oddsdetauilViewHolder.oddsBinding.SessionA.setText(matchstItem.getSessionA().trim());
            oddsdetauilViewHolder.oddsBinding.SessionB.setText(matchstItem.getSessionB().trim());
            oddsdetauilViewHolder.oddsBinding.Battingteam.setText(matchstItem.getBattingteam());
            oddsdetauilViewHolder.oddsBinding.MrateA.setText(matchstItem.getMrateA());
            oddsdetauilViewHolder.oddsBinding.MrateB.setText(matchstItem.getMrateB());
        }

    }


    @Override
    public int getItemCount() {
        return matchresultdata1 == null ? 0 : matchresultdata1.size();
    }

    public class OddsdetauilViewHolder extends RecyclerView.ViewHolder {

        public AdapterMatchOddsBinding oddsBinding;

        public OddsdetauilViewHolder(@NonNull Adapteroddstab adapteroddstab, AdapterMatchOddsBinding adapterMatchOddsBinding) {
            super(adapterMatchOddsBinding.getRoot());
            oddsBinding = adapterMatchOddsBinding;
        }
    }

    @Override
    public int getItemViewType(int i) {
        if (isLoaderVisible) {
            return i == matchresultdata1.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    public void addItems(List<MatchstItem> list) {
        matchresultdata1.addAll(list);
        notifyDataSetChanged();
    }

    public void addLoading() {
        isLoaderVisible = true;
        matchresultdata1.add(new MatchstItem());
        notifyItemInserted(matchresultdata1.size() - 1);
    }

    public void removeLoading() {
        isLoaderVisible = false;
        int position = matchresultdata1.size() - 1;
        MatchstItem item = getItem(position);
        if (item != null) {
            matchresultdata1.remove(position);
            notifyItemRemoved(position);
        }
    }

    MatchstItem getItem(int i) {
        return matchresultdata1.get(i);
    }

    public void clear() {
        matchresultdata1.clear();
        notifyDataSetChanged();
    }

    class ProgressHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public ProgressHolder(View view) {
            super(view);
            this.progressBar = view.findViewById(R.id.progressBar);

        }

    }

}
