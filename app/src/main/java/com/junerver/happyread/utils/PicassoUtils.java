package com.junerver.happyread.utils;

/**
 * Created by junerver on 2016/10/25.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Picasso工具类
 * Created by LGL on 2016/6/23.
 */
public class PicassoUtils {

    /**
     * 指定大小加载图片
     *
     * @param mContext   上下文
     * @param url        图片路径
     * @param width      宽
     * @param height     高
     * @param mImageView 控件
     */
    public static void loadImageViewWithSize(Context mContext, String url, int width, int height, ImageView mImageView) {
        Picasso.with(mContext).load(url).resize(width, height).centerCrop().into(mImageView);
    }


    /**
     * 加载有默认图片
     *
     * @param mContext   上下文
     * @param url        图片路径
     * @param resId      默认图片资源
     * @param mImageView 控件
     */
    public static void loadImageViewWithDefault(Context mContext, String url, int resId, ImageView mImageView) {
        Picasso.with(mContext).load(url).fit().placeholder(resId).into(mImageView);
    }


    /**
     * 裁剪正方形图片
     *
     * @param mContext   上下文
     * @param url        图片路径
     * @param mImageView 控件
     */
    public static void loadSquareImageViewCrop(Context mContext, String url, ImageView mImageView) {
        Picasso.with(mContext).load(url).transform(new SquareTransformation()).into(mImageView);
    }

    /**
     * 剪裁圆形图片
     *
     * @param mContext   上下文
     * @param url        图片路径
     * @param mImageView 控件
     */
    public static void loadCircleImageViewCrop(Context mContext, String url, ImageView mImageView) {
        Picasso.with(mContext).load(url).transform(new CircleTransformation()).into(mImageView);
    }

    /**
     * 正方形图片裁剪
     */
    public static class SquareTransformation implements Transformation {

        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap newBitmap = Bitmap.createBitmap(source, x, y, size, size);

            if (newBitmap != null) {
                //内存回收
                source.recycle();
            }
            return newBitmap;
        }

        @Override
        public String key() {

            return "lgl";
        }
    }

    /**
     * 圆形图片剪裁
     */
    public static class CircleTransformation implements Transformation {
        private static final int STROKE_WIDTH = 5;

        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }
            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());
            Canvas canvas = new Canvas(bitmap);
            Paint avatarPaint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            avatarPaint.setShader(shader);

            Paint outlinePaint = new Paint();
            outlinePaint.setColor(Color.WHITE);
            outlinePaint.setStyle(Paint.Style.STROKE);
            outlinePaint.setStrokeWidth(STROKE_WIDTH);
            outlinePaint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, avatarPaint);
            canvas.drawCircle(r, r, r - STROKE_WIDTH / 2, outlinePaint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle)";
        }
    }
}