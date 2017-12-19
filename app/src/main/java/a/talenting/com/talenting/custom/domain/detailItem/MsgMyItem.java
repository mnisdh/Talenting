package a.talenting.com.talenting.custom.domain.detailItem;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.domain.style.PaddingStyle;
import a.talenting.com.talenting.custom.domain.style.TextStyle;
import a.talenting.com.talenting.databinding.CustomMyMessageBinding;

/**
 * Created by user on 2017-12-18.
 */

public class MsgMyItem implements IDetailItem {
    private final DetailItemType detailItemType = DetailItemType.MSG_MY;
    private IItemClickListener onClickListener;

    public PaddingStyle padding = new PaddingStyle(50, 50, 50, 50);

    public String name="";
    public String lastTime="";
    public String content = "";
    public TextStyle contentStyle = new TextStyle(Color.DKGRAY);
    public TextStyle nameStyle = new TextStyle(40,Color.BLACK);
    public TextStyle lastStyle = new TextStyle(30,Color.DKGRAY);

    public String imageUrl = "";

    public boolean useBottomLine = false;

    public MsgMyItem(){

    }
    public MsgMyItem(String name, String imageUrl){
        this.name = name;
        this.imageUrl = imageUrl;
    }
    public MsgMyItem(String name, String imageUrl, String content, IItemClickListener onClickListener){
        this.name = name;
        this.content = content;
        this.imageUrl = imageUrl;
        this.onClickListener = onClickListener;
    }
    public MsgMyItem(String name, String imageUrl, String content, String lastTime, IItemClickListener onClickListener){
        this.name = name;
        this.content = content;
        this.imageUrl = imageUrl;
        this.lastTime = lastTime;
        this.onClickListener = onClickListener;
    }


    public void onClick(View v){
        if(onClickListener != null) onClickListener.run(this);
    }
    public void setOnClickListener(IItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public DetailItemType getDetailItemType() {
        return detailItemType;
    }

    private View view;
    @Override
    public View getLayoutView(LayoutInflater layoutInflater, ViewGroup parent) {
        //if(view == null) {
        CustomMyMessageBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_my_message, parent, false);
        binding.setItem(this);

        view = binding.getRoot();
        //}

        return view;
    }
}
