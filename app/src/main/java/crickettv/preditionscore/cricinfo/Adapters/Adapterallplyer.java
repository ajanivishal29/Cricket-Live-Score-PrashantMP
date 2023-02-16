package crickettv.preditionscore.cricinfo.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import crickettv.preditionscore.cricinfo.Apiresponse.RetrofitResponce.PlayerslistItem;
import crickettv.preditionscore.cricinfo.C6495a;
import crickettv.preditionscore.cricinfo.R;
import crickettv.preditionscore.cricinfo.databinding.AdapterPlayerItemBinding;

/* renamed from: com.indvssa.cricinfo.adapter.home.AdapterUpcomingMatch */
public class Adapterallplyer extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Activity activity;
    public ArrayList<PlayerslistItem> firstiningdata1 = new ArrayList<>();
    String playerurl= C6495a.m21868a("03D530BE4FEA3E4787195A72F8477BA88E4561E20C65BA17EC918EDCEF89C608CBAA0D8D7674AC004D9C6769E73A9D57");

    public Adapterallplyer(FragmentActivity activity2, ArrayList<PlayerslistItem> matchresultdata) {
        activity = activity2;
        firstiningdata1 = matchresultdata;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new OddsdetauilViewHolder(this, AdapterPlayerItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        PlayerslistItem playerslistItem = firstiningdata1.get(i);
        OddsdetauilViewHolder oddsdetauilViewHolder = (OddsdetauilViewHolder) holder;
        if(playerslistItem.getOutby().equalsIgnoreCase("not out"))
        {
            oddsdetauilViewHolder.oddsBinding.txtPlayerName.setText(playerslistItem.getPlayerName()+"*");
            oddsdetauilViewHolder.oddsBinding.txtPlayerName.setTextColor(activity.getResources().getColor(R.color.ball_red));
        }
        else {
            oddsdetauilViewHolder.oddsBinding.txtPlayerName.setText(playerslistItem.getPlayerName());
        }
        oddsdetauilViewHolder.oddsBinding.outBy.setText(playerslistItem.getOutby());
        oddsdetauilViewHolder.oddsBinding.txtPlayerBalls.setText(String.valueOf(playerslistItem.getBalls()));
        oddsdetauilViewHolder.oddsBinding.txtPlayerFours.setText(String.valueOf(playerslistItem.getFour()));
        oddsdetauilViewHolder.oddsBinding.txtPlayerSix.setText(String.valueOf(playerslistItem.getSix()));
        oddsdetauilViewHolder.oddsBinding.txtPlayerScore.setText(String.valueOf(playerslistItem.getRuns()));
        oddsdetauilViewHolder.oddsBinding.txtPlayerSR.setText(String.valueOf(playerslistItem.getSeqno()));

        Glide.with(activity).load(playerurl +playerslistItem.getPlayerImage()).into(oddsdetauilViewHolder.oddsBinding.imgPlayer);
    }


    @Override
    public int getItemCount() {
        return firstiningdata1 == null ? 0 : firstiningdata1.size();
    }

    public class OddsdetauilViewHolder extends RecyclerView.ViewHolder {

        public AdapterPlayerItemBinding oddsBinding;

        public OddsdetauilViewHolder(@NonNull Adapterallplyer adapterallplyer, AdapterPlayerItemBinding adapterPlayerItemBinding) {
            super(adapterPlayerItemBinding.getRoot());
            oddsBinding = adapterPlayerItemBinding;
        }
    }
}
