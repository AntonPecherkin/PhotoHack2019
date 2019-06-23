package come.manager.direct.photohack;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
public class SlidingImage_Adapter extends PagerAdapter {


    private ArrayList<String> IMAGES;
    private ArrayList<Integer> DATES;
    private LayoutInflater inflater;
    private Context context;


    public SlidingImage_Adapter(Context context,ArrayList<String> IMAGES,ArrayList<Integer> DATES) {
        this.context = context;
        this.IMAGES=IMAGES;
        this.DATES=DATES;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return DATES.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);

        assert imageLayout != null;
        final ImageButton imageView = (ImageButton) imageLayout
                .findViewById(R.id.image);
        final TextView textView = (TextView) imageLayout
                .findViewById(R.id.date);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == 1) {
                    if(!IMAGES.get(4).endsWith("g")){
                        IMAGES.set(4,IMAGES.get(4)+"g");
                    }
                    Glide.with(view.getContext())
                            .asBitmap()
                            .load(IMAGES.get(4))
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                    imageView.setImageBitmap(resource);
                                }
                            });
                }
            }
        });

//        Glide.with(view)
//                .asBitmap()
//                .load(IMAGES.get(position))
//                .

        Glide.with(view.getContext())
                .asBitmap()
                .load(IMAGES.get(position))
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

//                        File path = Environment.getExternalStoragePublicDirectory(
//                                Environment.DIRECTORY_MOVIES);
//                        final File file = new File(path, "/" + "photo"+position);
//                        try {
//
//                            OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
//                            resource.compress(Bitmap.CompressFormat.JPEG, 100, os);
//
//                            os.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }


                        BitmapToVideoEncoder bitmapToVideoEncoder = new BitmapToVideoEncoder(new BitmapToVideoEncoder.IBitmapToVideoEncoderCallback() {
                            @Override
                            public void onEncodingComplete(File outputFile) {

                            }
                        });

                        bitmapToVideoEncoder.startEncoding(120, 120, new File("some_path"));
                        bitmapToVideoEncoder.queueFrame(resource);
//                        bitmapToVideoEncoder.queueFrame(bitmap2);
//                        bitmapToVideoEncoder.queueFrame(bitmap3);
//                        bitmapToVideoEncoder.queueFrame(bitmap4);
//                        bitmapToVideoEncoder.queueFrame(bitmap5);
                        bitmapToVideoEncoder.stopEncoding();
                        imageView.setImageBitmap(resource);
                    }
                });



        textView.setText(DATES.get(position)+"");

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}

