package com.hoofee.everything.main.bindingadapter;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.hoofee.everything.R;
import com.hoofee.everything.main.app.AppConfig;
import com.hoofee.everything.main.utils.StringUtils;

import cn.sharing8.blood_platform_widget.utils.LogUtils;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by hufei on 2016/12/11.
 * 图片的加载适配器
 */

public class ImageBindAdapter {

    public static final String CROP  = "crop";
    public static final String COLOR = "color";
    public static final String BLUR  = "blur";
    public static final String MASK  = "mask";

    public static final int CROP_ROUNDED_CORNERS_INT = 20;//圆角度
    public static final int BLUR_LEVEL_INT           = 16;//模糊度(0-25)

    /*
    Crop
            CropTransformation, CropCircleTransformation, CropSquareTransformation, RoundedCornersTransformation
    Color
            ColorFilterTransformation, GrayscaleTransformation
    Blur
            BlurTransformation
    Mask
            MaskTransformation
    */

    /**
     * @param transf    图片剪裁类型
     * @param zoomType  缩放尺寸
     * @param sizeInt   处理程度
     * @param animation 动画
     */
    @BindingAdapter(value = {"imageUrl", "defPhoto", "transformationEnum", "zoomType", "sizeInt", "anim"}, requireAll = false)
    public static void bindImageViewAll(ImageView view, String imageUrl, Drawable defPhoto, TransformationEnum transf, String zoomType, Integer sizeInt, AlphaAnimation animation) {
        if (!StringUtils.isEmpty(imageUrl)) {
            imageUrl = AppConfig.getAppImage(imageUrl, transf, zoomType);
            view.setTag(R.id.load_image_tag, imageUrl);
            LogUtils.i("glide_load_image_url_" + imageUrl);
//            LogUtils.i("glide_load_image_url_" + defPhoto);
//            LogUtils.i("glide_load_image_url_" + transf);
//            LogUtils.i("glide_load_image_url_" + sizeInt);
            DrawableTypeRequest<String> drawableTypeRequest = Glide.with(view.getContext()).load(imageUrl);
            DrawableRequestBuilder imageBuilder;

            //defPhoto
            imageBuilder = drawableTypeRequest.placeholder(defPhoto);

            //Transformation
            if (transf != null) {
                String transformName = transf.getTransformName();
                if (transformName.startsWith(CROP)) {

                    switch (transf) {
                        case CROP_TOP:
                            imageBuilder = imageBuilder.bitmapTransform(new CropTransformation(view.getContext(), view.getWidth(), view.getHeight(), CropTransformation.CropType.TOP));
                            break;
                        case CROP_CENTER:
                            imageBuilder = imageBuilder.bitmapTransform(new CropTransformation(view.getContext(), view.getWidth(), view.getHeight(), CropTransformation.CropType.CENTER));
                            break;
                        case CROP_CIRCLE:
                            imageBuilder = imageBuilder.bitmapTransform(new CropCircleTransformation(view.getContext())).priority(Priority.HIGH);
                            break;
                        case CROP_ROUNDED:
                            imageBuilder = imageBuilder.bitmapTransform(new RoundedCornersTransformation(view.getContext(), sizeInt == null ? CROP_ROUNDED_CORNERS_INT : sizeInt, 0));
                            break;
                        case CROP_SQUARE:
                            imageBuilder = imageBuilder.bitmapTransform(new CropSquareTransformation(view.getContext()));
                            if (sizeInt != null) {
                                imageBuilder = imageBuilder.bitmapTransform(new RoundedCornersTransformation(view.getContext(), sizeInt, 0));
                            }
                            break;
                    }
                } else if (transformName.startsWith(BLUR)) {
                    imageBuilder = imageBuilder.bitmapTransform(new BlurTransformation(view.getContext(), sizeInt == null ? BLUR_LEVEL_INT : sizeInt, 1)).priority(Priority.HIGH);
                }
            }

            imageBuilder = imageBuilder.animate(view1 -> {
                AlphaAnimation alpha = new AlphaAnimation(0, 1);
                alpha.setDuration(1000);
                view1.startAnimation(alpha);
            });

            imageBuilder = imageBuilder.listener(new RequestListener() {
                @Override
                public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                    GlideBitmapDrawable drawable = (GlideBitmapDrawable) resource;
                    if (isFirstResource) {
                        view.postDelayed(() -> {
                            if (view.getDrawable() == null) {
                                view.setImageBitmap(drawable.getBitmap());
                            }
                        }, 1000);
                    }
                    return false;
                }
            });

            //final
            imageBuilder.into(view);

        } else {
            //defPhoto
            if (defPhoto != null) {
                view.setImageDrawable(defPhoto);
            } else {
                if (transf != null && transf == TransformationEnum.CROP_CIRCLE) {
                    view.setImageResource(R.drawable.default_head);
                } else {
                    view.setImageResource(R.drawable.def_photo);
                }
            }

        }
    }

    @BindingAdapter({"imagePath"})
    public static void bindImageViewByPath(ImageView view, String path) {
        Glide.with(view.getContext()).load(path).into(view);
    }

    @BindingAdapter({"imageUrl"})
    public static void bindImage(ViewGroup view, String imageUrl) {
        String url = AppConfig.getAppImage(imageUrl);
        Glide.with(view.getContext()).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if (resource != null) {
                    view.setBackground(new BitmapDrawable(resource));
                }
            }
        });
    }

    /**
     * 转换类型
     */
    public static enum TransformationEnum {
        CROP_TOP("crop_top"),
        CROP_CENTER("crop_center"),
        CROP_CIRCLE("crop_circle"),
        CROP_SQUARE("crop_square"),
        CROP_ROUNDED("crop_rounded"),

        BLUR("blur");

        private String transformName;

        TransformationEnum(String transformName) {
            this.transformName = transformName;
        }

        public String getTransformName() {
            return transformName;
        }
    }
}
