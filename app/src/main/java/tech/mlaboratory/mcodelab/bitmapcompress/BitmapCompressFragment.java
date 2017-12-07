package tech.mlaboratory.mcodelab.bitmapcompress;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import tech.mlaboratory.mcodelab.R;
import tech.mlaboratory.mcodelab.Utils;

public class BitmapCompressFragment extends Fragment {

    private static final String BUNDLE_KEY = "resId";
    int resId;

    int width;
    int height;

    @BindView(R.id.screen_size) TextView screenSize;
    @BindView(R.id.container) LinearLayout linearContainer;

    private final static int[] COMPRESS_QUALITIES = {100, 10};
    private final static Bitmap.CompressFormat[] COMPRESS_FORMATS = {Bitmap.CompressFormat.PNG, Bitmap.CompressFormat.JPEG, Bitmap.CompressFormat.WEBP};
    private final static Bitmap.Config[] COMPRESS_CONFIGS = {Bitmap.Config.ARGB_8888, Bitmap.Config.ARGB_4444, Bitmap.Config.RGB_565};
    private final static List<CompressOptionEntity> COMPRESS_OPTION_LIST = new ArrayList<>();
    static {
        for (int quality: COMPRESS_QUALITIES) {
            for (Bitmap.CompressFormat format: COMPRESS_FORMATS) {
                for (Bitmap.Config config: COMPRESS_CONFIGS) {
                    COMPRESS_OPTION_LIST.add(new CompressOptionEntity(quality, format, config));
                }
            }
        }
    }


    Bitmap originalBitmap;
    boolean loaded = false;

    public static BitmapCompressFragment newInstance(@IdRes int resId) {

        Bundle args = new Bundle();
        args.putInt(BUNDLE_KEY, resId);

        BitmapCompressFragment fragment = new BitmapCompressFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            resId = getArguments().getInt(BUNDLE_KEY);
        }
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pager, container, false);
        ButterKnife.bind(this, rootView);



        final DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = getActivity().getWindowManager().getDefaultDisplay();
        Method mGetRawHeight, mGetRawWidth;
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
                // For JellyBeans and onward
                defaultDisplay.getRealMetrics(displayMetrics);
                width = displayMetrics.widthPixels;
                height = displayMetrics.heightPixels;
            } else {
                // Below Jellybeans you can use reflection method
                mGetRawHeight = Display.class.getMethod("getRawHeight");
                mGetRawWidth = Display.class.getMethod("getRawWidth");

                width = (Integer) mGetRawWidth.invoke(defaultDisplay);
                height = (Integer) mGetRawHeight.invoke(defaultDisplay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        screenSize.setText("Screen size: "+ width + " x " + height);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            lazyLoad();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isResumed()) {
            if (isVisibleToUser) {
                lazyLoad();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (disposable != null)
            disposable.dispose();
    }

    private Disposable disposable;
    public void lazyLoad() {
        if (loaded)
            return;
        loaded = true;
        Observable
                .create(new ObservableOnSubscribe<Bitmap>() {
                    @Override
                    public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
                        e.onNext(Utils.loadBitmapByResId(getContext(), resId, width, height));
                        e.onComplete();
                    }
                })
                .subscribeOn(Schedulers.computation())

                .observeOn(Schedulers.computation())
                .flatMap(new Function<Bitmap, ObservableSource<CompressOptionEntity>>() {
                    @Override
                    public ObservableSource<CompressOptionEntity> apply(Bitmap bitmap) throws Exception {
                        originalBitmap = bitmap;
                        return Observable.fromIterable(COMPRESS_OPTION_LIST);
                    }
                })

                .observeOn(Schedulers.computation())
                .doOnNext(new Consumer<CompressOptionEntity>() {
                    @Override
                    public void accept(CompressOptionEntity entity) throws Exception {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = entity.config;

                        originalBitmap.compress(entity.format, entity.quality, byteArrayOutputStream);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length, options);

                        byteArrayOutputStream.reset();

                        bitmap.compress(entity.format, 100, byteArrayOutputStream);

                        entity.image = new ImageView(getContext());
                        entity.imageInfo = new TextView(getContext());
                        entity.image.setImageBitmap(bitmap);
                        entity.imageInfo.append(String.format(Locale.getDefault(), "ImageSize: %dx%d\n", bitmap.getWidth(), bitmap.getHeight()));
                        entity.imageInfo.append(String.format(Locale.getDefault(), "Quality: %d\n", entity.quality));
                        entity.imageInfo.append(String.format(Locale.getDefault(), "Format: %s\n", entity.format.name()));
                        entity.imageInfo.append(String.format(Locale.getDefault(), "Config: %s\n", entity.config.name()));
                        entity.imageInfo.append(String.format(Locale.getDefault(), "Size: %d\n", byteArrayOutputStream.size()));

                        byteArrayOutputStream.close();
                    }
                })

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CompressOptionEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(CompressOptionEntity entity) {
                        linearContainer.addView(entity.image, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        linearContainer.addView(entity.imageInfo, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(getContext(), "done", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private static class CompressOptionEntity {
        int quality;
        Bitmap.CompressFormat format;
        Bitmap.Config config;

        ImageView image;
        TextView imageInfo;

        CompressOptionEntity(int quality, Bitmap.CompressFormat format, Bitmap.Config config) {
            this.quality = quality;
            this.format = format;
            this.config = config;
        }
    }
}
