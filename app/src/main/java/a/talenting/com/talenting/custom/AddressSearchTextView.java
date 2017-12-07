package a.talenting.com.talenting.custom;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.location.places.Place;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.ActivityResultManager;
import a.talenting.com.talenting.common.GooglePlaceApi;


/**
 * Created by daeho on 2017. 11. 28..
 */

public class AddressSearchTextView extends FrameLayout {
    private Activity parentActivity;
    private ActivityResultManager manager;

    private Place place;

    private LinearLayout layout;
    private TextView textView;

    public AddressSearchTextView(@NonNull Context context) {
        super(context);

        init();
    }

    public AddressSearchTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
        getAttrs(attrs);
    }

    public AddressSearchTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
        getAttrs(attrs, defStyleAttr);
    }

    private void init(){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) this.getContext().getSystemService(infService);
        View v = li.inflate(R.layout.custom_address_search_textview, this, false);
        addView(v);

        layout = findViewById(R.id.layout);
        textView = findViewById(R.id.textView);

        this.setOnClickListener(onClickListener);
    }

    private OnClickListener onClickListener = v -> {
        GooglePlaceApi.startAutoComplateAddress(manager
                , p -> {
                    place = p;
                    textView.setText(place.getName());
                }
                ,parentActivity);
    };

    public void setParentActivity(Activity activity, ActivityResultManager manager){
        this.parentActivity = activity;
        this.manager = manager;
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.AddressSearchTextView);

        setTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.AddressSearchTextView, defStyle, 0);

        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray) {
        String text_string = typedArray.getString(R.styleable.AddressSearchTextView_searchText);
        textView.setText(text_string);

        int textColor = typedArray.getColor(R.styleable.AddressSearchTextView_searchTextColor, 0);
        textView.setTextColor(textColor);

        typedArray.recycle();
    }

    public Place getPlace(){
        return place;
    }
}
