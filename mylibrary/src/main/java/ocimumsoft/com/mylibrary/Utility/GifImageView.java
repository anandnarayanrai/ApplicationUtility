package ocimumsoft.com.mylibrary.Utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.View;

import java.io.InputStream;

import ocimumsoft.com.mylibrary.R;

public class GifImageView extends View {
    public Movie mMovie;
    public long movieStart;
    private Context context;

    public GifImageView(Context context) {
        super(context);
        this.context = context;
        initializeView();
    }

    public GifImageView(Context context, AttributeSet attrs) {

        super(context, attrs);
        initializeView();
    }

    public GifImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView();
    }

    private void initializeView() {
        //R.drawable.loader - our animated GIF
        @SuppressLint("ResourceType") InputStream is = context.getResources().openRawResource(R.drawable.preload_waiting);
        mMovie = Movie.decodeStream(is);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);
        super.onDraw(canvas);
        long now = android.os.SystemClock.uptimeMillis();
        if (movieStart == 0) {
            movieStart = now;
        }
        if (mMovie != null) {
            int relTime = (int) ((now - movieStart) % mMovie.duration());
            mMovie.setTime(relTime);
            mMovie.draw(canvas, getWidth() - mMovie.width(), getHeight() - mMovie.height());
            this.invalidate();
        }
    }

    private int gifId;

    public void setGIFResource(int resId) {
        this.gifId = resId;
        initializeView();
    }

    public int getGIFResource() {
        return this.gifId;
    }
}