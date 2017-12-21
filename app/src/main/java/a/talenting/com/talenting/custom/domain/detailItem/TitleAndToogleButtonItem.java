package a.talenting.com.talenting.custom.domain.detailItem;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.domain.style.PaddingStyle;
import a.talenting.com.talenting.custom.domain.style.TextStyle;
import a.talenting.com.talenting.databinding.CustomDetailItemTextAndTooglebuttonBinding;

/**
 * Created by daeho on 2017. 11. 29..
 */

public class TitleAndToogleButtonItem implements IDetailItem{
    private final DetailItemType detailItemType = DetailItemType.TITLE_AND_TOOGLEBUTTON;
    private IItemClickListener onClickListener;
    private IItemClickListener onButtonClickListener;

    public PaddingStyle padding = new PaddingStyle(50, 50, 50, 50);

    public String title = "";
    public TextStyle titleStyle = new TextStyle(Color.BLACK);

    public boolean isButtonChecked = false;
    public String btnOffText = "Off";
    public String btnOnText = "On";
    public TextStyle buttonTextStyle = new TextStyle(Color.DKGRAY);

    public boolean useBottomLine = false;

    public TitleAndToogleButtonItem(){

    }
    public TitleAndToogleButtonItem(String title, String btnOnText, String btnOffText){
        this.title = title;
        this.btnOnText = btnOnText;
        this.btnOffText = btnOffText;
    }
    public TitleAndToogleButtonItem(String title, String btnOnText, String btnOffText, IItemClickListener onButtonClickListener){
        this.title = title;
        this.btnOnText = btnOnText;
        this.btnOffText = btnOffText;
        this.onButtonClickListener = onButtonClickListener;
    }

    public void onClick(View v){
        if(onClickListener != null) onClickListener.run(this);
    }
    public void setOnClickListener(IItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void onButtonClick(View v){
        if(onButtonClickListener != null) onButtonClickListener.run(this);
    }
    public void setOnButtonClickListener(IItemClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    @Override
    public DetailItemType getDetailItemType() {
        return detailItemType;
    }

    private View view;
    @Override
    public View getLayoutView(LayoutInflater layoutInflater, ViewGroup parent) {
        //if(view == null) {
            CustomDetailItemTextAndTooglebuttonBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_detail_item_text_and_tooglebutton, parent, false);
            binding.setItem(this);

            view = binding.getRoot();
        //}
        return view;
    }
}