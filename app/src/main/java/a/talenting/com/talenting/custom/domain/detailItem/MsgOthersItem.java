package a.talenting.com.talenting.custom.domain.detailItem;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.domain.style.PaddingStyle;
import a.talenting.com.talenting.custom.domain.style.TextStyle;
import a.talenting.com.talenting.databinding.CustomOtherMessageBinding;

/**
 * Created by user on 2017-12-18.
 */

public class MsgOthersItem implements IDetailItem{
    private final DetailItemType detailItemType = DetailItemType.MSG_OTHER;
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

    public MsgOthersItem(){

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
        CustomOtherMessageBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_other_message, parent, false);
        binding.setItem(this);

        view = binding.getRoot();

        return view;
    }
}