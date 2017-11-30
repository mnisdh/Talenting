package a.talenting.com.talenting.common;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by daeho on 2017. 11. 30..
 */

public final class DataBinder {
    private DataBinder() {
        //NO-OP
    }

    @BindingAdapter("image_url")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions()
                        .override(500,500))
                .into(imageView);
    }

    @BindingAdapter("circle_image_url")
    public static void setCircleImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions()
                        .override(500,500)
                        .circleCrop())
                .into(imageView);
    }
}
