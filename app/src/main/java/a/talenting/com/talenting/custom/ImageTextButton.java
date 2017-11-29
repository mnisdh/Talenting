package a.talenting.com.talenting.custom;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
import android.view.animation.LinearInterpolator;
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
        move(3);
        move(-3);

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

    public void move(int val){
        ObjectAnimator ivX = ObjectAnimator.ofFloat(imageView, TRANSLATION_X, val);
        //ObjectAnimator ivY = ObjectAnimator.ofFloat(imageView, TRANSLATION_Y, 5);
        ObjectAnimator tvX = ObjectAnimator.ofFloat(textView, TRANSLATION_X, val);
        //ObjectAnimator tvY = ObjectAnimator.ofFloat(textView, TRANSLATION_Y, 5);

        //애니메이션 셋에 담아서 동시에 실행 할 수 있다
        AnimatorSet aniSet = new AnimatorSet();
        aniSet.playTogether(ivX, tvX);
        aniSet.setDuration(100);

        aniSet.setInterpolator(new LinearInterpolator());
        // LinearInterpolator : 일정한 속도를 유지
        // AccelerateInterpolator : 점점빠르게
        // DecelerateInterpolator : 점점느리게
        // AccelerateInterpolator : 위 둘을 동시에
        // anticipateInterpolator : 시작위치에서 조금 뒤로 당겼다 이동
        // OvershootInterpolator : 도착위치를 조금 지나쳤다가 도착위치로 이동
        // AnticipateOvershootInterpolator : 위둘을 동시에
        // BounceInterpolator : 도착위치에서 튕김
        aniSet.start();
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        if(l == onClickListener) super.setOnClickListener(l);
        else setedOnClickListener = l;
    }
}
