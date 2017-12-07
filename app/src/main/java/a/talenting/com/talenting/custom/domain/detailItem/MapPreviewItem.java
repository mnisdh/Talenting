package a.talenting.com.talenting.custom.domain.detailItem;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.GoogleStaticMap;
import a.talenting.com.talenting.custom.domain.style.PaddingStyle;
import a.talenting.com.talenting.databinding.CustomDetailItemMapPreviewBinding;

/**
 * Created by daeho on 2017. 11. 29..
 */

public class MapPreviewItem implements IDetailItem{
    private final DetailItemType detailItemType = DetailItemType.MAP_PREVIEW;
    private IItemClickListener onClickListener;

    public PaddingStyle padding = new PaddingStyle(0, 0, 0, 0);

    public GoogleStaticMap googleStaticMap = new GoogleStaticMap();

    public boolean useBottomLine = false;

    public MapPreviewItem(GoogleStaticMap googleStaticMap){
        this.googleStaticMap = googleStaticMap;
    }
    public MapPreviewItem(GoogleStaticMap googleStaticMap, IItemClickListener onClickListener){
        this.googleStaticMap = googleStaticMap;
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
        CustomDetailItemMapPreviewBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_detail_item_map_preview, parent, false);
        binding.setItem(this);

        return binding.getRoot();
    }




}