package a.talenting.com.talenting.custom.domain.detailItem;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.domain.style.PaddingStyle;
import a.talenting.com.talenting.custom.domain.style.TextStyle;
import a.talenting.com.talenting.databinding.CustomDetailItemTextAndEdittextBinding;

/**
 * Created by daeho on 2017. 11. 29..
 */

public class TitleAndEditTextItem implements IDetailItem{
    private final DetailItemType detailItemType = DetailItemType.TITLE_AND_EDITTEXT;
    private IItemClickListener onClickListener;

    public PaddingStyle padding = new PaddingStyle(50, 50, 50, 50);

    public String title = "";
    public TextStyle titleStyle = new TextStyle(Color.BLACK);

    public String editText = "";
    public String editTextHint = "";
    public TextStyle editTextStyle = new TextStyle(Color.DKGRAY);

    public boolean useBottomLine = false;

    public TitleAndEditTextItem(){

    }
    public TitleAndEditTextItem(String title, String editText, String editTextHint){
        this.title = title;
        this.editText = editText;
        this.editTextHint = editTextHint;
    }
    public TitleAndEditTextItem(String title, String editText, String editTextHint, IItemClickListener onClickListener){
        this.title = title;
        this.editText = editText;
        this.editTextHint = editTextHint;
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
        return null;
    }

    @Override
    public View getLayoutView(LayoutInflater layoutInflater, ViewGroup parent) {
        CustomDetailItemTextAndEdittextBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_detail_item_text_and_edittext, parent, false);
        binding.setItem(this);

        return binding.getRoot();
    }
}