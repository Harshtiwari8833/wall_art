package com.pixelperfect;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.util.Util;

import java.security.MessageDigest;

public class ImageCompressor implements Transformation<Bitmap> {
    private int maxWidth;
    private int maxHeight;

    public ImageCompressor(int maxWidth, int maxHeight) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    @NonNull
    @Override
    public Resource<Bitmap> transform(@NonNull Context context, @NonNull Resource<Bitmap> resource, int outWidth, int outHeight) {
        BitmapPool bitmapPool = Glide.get(context).getBitmapPool();
        Bitmap originalBitmap = resource.get();

        // Calculate the target dimensions while maintaining the original aspect ratio
        int targetWidth, targetHeight;
        if (originalBitmap.getWidth() > originalBitmap.getHeight()) {
            float aspectRatio = (float) originalBitmap.getHeight() / (float) originalBitmap.getWidth();
            targetWidth = maxWidth;
            targetHeight = Math.round(maxWidth * aspectRatio);
        } else {
            float aspectRatio = (float) originalBitmap.getWidth() / (float) originalBitmap.getHeight();
            targetHeight = maxHeight;
            targetWidth = Math.round(maxHeight * aspectRatio);
        }

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, targetWidth, targetHeight, true);
        return BitmapResource.obtain(resizedBitmap, bitmapPool);
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(("com.pixelperfect" + maxWidth + maxHeight).getBytes());
    }
}
