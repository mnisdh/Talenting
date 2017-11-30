package a.talenting.com.talenting.custom;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import a.talenting.com.talenting.R;


/**
 * Created by daeho on 2017. 11. 28..
 */

public class ImageTextButton extends FrameLayout {
    private LinearLayout layout;
    private ImageView imageView;
    private TextView textView;

    private OnClickListener setedOnClickListener;

    public ImageTextButton(Context context) {
        super(context);

        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ImageTextButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
        getAttrs(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ImageTextButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
        getAttrs(attrs, defStyleAttr);
    }

    private void init(Context context){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) context.getSystemService(infService);
        View v = li.inflate(R.layout.custom_image_text_button, this, false);
        addView(v);

        layout = findViewById(R.id.layout);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        this.setOnClickListener(onClickListener);
    }

    private OnClickListener onClickListener = v -> {
        if(setedOnClickListener != null) setedOnClickListener.onClick(v);
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ImageTextButton);

        setTypeArray(typedArray);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ImageTextButton, defStyle, 0);
        setTypeArray(typedArray);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setTypeArray(TypedArray typedArray) {
        int imgRes = typedArray.getResourceId(R.styleable.ImageTextButton_image, R.drawable.ic_launcher_foreground);
        imageView.setImageResource(imgRes);

        int tintColor = typedArray.getColor(R.styleable.ImageTextButton_imageTint, 0);
        imageView.setImageTintList(ColorStateList.valueOf(tintColor));

        String text_string = typedArray.getString(R.styleable.ImageTextButton_text);
        textView.setText(text_string);

        int textColor = typedArray.getColor(R.styleable.ImageTextButton_textColor, 0);
        textView.setTextColor(textColor);

        typedArray.recycle();
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        if(l == onClickListener) super.setOnClickListener(l);
        else setedOnClickListener = l;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void changeColor(int color){
        textView.setTextColor(color);
        imageView.setImageTintList(ColorStateList.valueOf(color));
    }
}
