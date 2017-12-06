package a.talenting.com.talenting.custom;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.Constants;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


/**
 * Created by daeho on 2017. 11. 28..
 */

public class AddressSearchTextView extends FrameLayout {
    private AppCompatActivity parentActivity;
    private GoogleApiClient googleApiClient;

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
        try {
            if(parentActivity != null) {
                Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(parentActivity);
                parentActivity.startActivityForResult(intent, Constants.REQ_EVENT_PLACE);
            }
        }catch (Exception e){
            Log.e("EventListView", e.getMessage());
        }
    };

    public void setParentActivity(AppCompatActivity activity){
        this.parentActivity = activity;
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

    public void onActivityResult(int resultCode, Intent data){
        place = null;

        switch (resultCode){
            case RESULT_OK:
                place = PlaceAutocomplete.getPlace(parentActivity, data);
                textView.setText(place.getName());
                break;
            case PlaceAutocomplete.RESULT_ERROR:
                Status status = PlaceAutocomplete.getStatus(parentActivity, data);
                break;
            case RESULT_CANCELED:
                break;
        }
    }
}
