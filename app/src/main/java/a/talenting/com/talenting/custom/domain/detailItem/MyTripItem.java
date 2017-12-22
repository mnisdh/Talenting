package a.talenting.com.talenting.custom.domain.detailItem;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.domain.style.PaddingStyle;
import a.talenting.com.talenting.custom.domain.style.TextStyle;
import a.talenting.com.talenting.databinding.CustomMyTripBinding;

/**
 * Created by user on 2017-12-21.
 */

public class MyTripItem implements IDetailItem{
    private final DetailItemType detailItemType = DetailItemType.MYTRIP;

    private IItemClickListener onClickListener;

    public PaddingStyle padding = new PaddingStyle(20, 50, 50, 20);

    public String desTitle="Destination";
    public String des="";
    public String dateTitle = "Date";
    public String startDate = "";
    public String endDate = "";
    public String numTitle = "Number of Traveler";
    public String num = "";
    public String descriptionTitle = "Description";
    public String description = "";
    public TextStyle titleStyle = new TextStyle(Color.WHITE);
    public TextStyle contentStyle = new TextStyle(45,Color.WHITE);
    private String pk = "";

    private boolean useAddMode = false;
    private boolean useRemoveMode = false;
    public boolean useBottomLine = false;

    public MyTripItem(){

    }

    public MyTripItem( String des, String startDate, String endDate, String num, String description){
        this.des = des;
        this.startDate = startDate;
        this.endDate = endDate;
        this.num = num;
        this.description = description;
    }

    public MyTripItem( String des, String startDate, String endDate, String num, String description, IItemClickListener onClickListener){
        this.des = des;
        this.startDate = startDate;
        this.endDate = endDate;
        this.num = num;
        this.description = description;
        this.onClickListener = onClickListener;
    }

    public MyTripItem getMyTripItem(){
        return this;
    }

    public void onClick(View v){
        if(onClickListener != null) onClickListener.run(this);
    }
    public void setOnClickListener(IItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public String getPk(){
        return pk;
    }

    public void setPk(String pk){
        this.pk=pk;
    }

    private View view;

    @Override
    public DetailItemType getDetailItemType() {
        return detailItemType;
    }

    @Override
    public View getLayoutView(LayoutInflater layoutInflater, ViewGroup parent) {
        //if(view == null) {
        CustomMyTripBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_my_trip, parent, false);
        binding.setItem(this);

        view = binding.getRoot();
        //}

        return view;
    }
}
