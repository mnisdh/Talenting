package a.talenting.com.talenting.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import a.talenting.com.talenting.custom.domain.detailItem.IDetailItem;


/**
 * Created by daeho on 2017. 11. 28..
 */

public class DetailItemView extends FrameLayout {
    private IDetailItem item;

    public DetailItemView(Context context) {
        super(context);
    }

    public DetailItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DetailItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setDetailItem(IDetailItem item){
        this.removeAllViews();
        this.addView(item.getLayoutView(LayoutInflater.from(this.getContext()), this));

        this.item = item;
    }

    public IDetailItem getDetailItem(){
        return item;
    }
}
