package a.talenting.com.talenting.custom.domain.detailItem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by daeho on 2017. 11. 29..
 */

public interface IDetailItem {
    DetailItemType getDetailItemType();
    View getLayoutView(LayoutInflater layoutInflater, ViewGroup parent);
}
