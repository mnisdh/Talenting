package a.talenting.com.talenting.custom.domain.detailItem;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.domain.style.PaddingStyle;
import a.talenting.com.talenting.custom.domain.style.TextStyle;
import a.talenting.com.talenting.databinding.CustomDetailItemTextAndCheckBinding;

/**
 * Created by daeho on 2017. 11. 29..
 */

public class TitleAndCheckItem implements IDetailItem{
    private final DetailItemType detailItemType = DetailItemType.TITLE_AND_CHECK;
    private IItemClickListener onClickListener;

    public PaddingStyle padding = new PaddingStyle(50, 50, 50, 50);

    public String title = "";
    public TextStyle titleStyle = new TextStyle(Color.BLACK);

    public boolean isCheck = false;

    public boolean useBottomLine = false;

    public TitleAndCheckItem(){

    }
    public TitleAndCheckItem(String title, boolean isCheck){
        this.title = title;
        this.isCheck = isCheck;
    }
    public TitleAndCheckItem(String title, boolean isCheck, IItemClickListener onClickListener){
        this.title = title;
        this.isCheck = isCheck;
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
            CustomDetailItemTextAndCheckBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_detail_item_text_and_check, parent, false);
            binding.setItem(this);

            view = binding.getRoot();
        //}

        return view;
    }
}