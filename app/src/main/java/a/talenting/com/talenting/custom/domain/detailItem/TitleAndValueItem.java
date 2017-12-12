package a.talenting.com.talenting.custom.domain.detailItem;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.domain.style.PaddingStyle;
import a.talenting.com.talenting.custom.domain.style.TextStyle;
import a.talenting.com.talenting.databinding.CustomDetailItemTextAndValueBinding;

/**
 * Created by daeho on 2017. 11. 29..
 */

public class TitleAndValueItem implements IDetailItem{
    private final DetailItemType detailItemType = DetailItemType.TITLE_AND_VALUE;
    private IItemClickListener onClickListener;

    public PaddingStyle padding = new PaddingStyle(50, 50, 50, 50);

    public String title = "";
    public TextStyle titleStyle = new TextStyle(Color.BLACK);

    public String value = "";
    public TextStyle valueStyle = new TextStyle(Color.DKGRAY);

    public String valueCode = "";

    public boolean useBottomLine = false;

    public TitleAndValueItem(){

    }
    public TitleAndValueItem(String title, String value){
        this.title = title;
        this.value = value;
    }
    public TitleAndValueItem(String title, String value, IItemClickListener onClickListener){
        this.title = title;
        this.value = value;
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
            CustomDetailItemTextAndValueBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_detail_item_text_and_value, parent, false);
            binding.setItem(this);

            view = binding.getRoot();
        //}

        return view;
    }
}