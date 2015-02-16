//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.2.
//


package com.select.picture.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import com.select.picture.R.id;
import com.select.picture.R.layout;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;


/**
 * We use @SuppressWarning here because our java code
 * generator doesn't know that there is no need
 * to import OnXXXListeners from View as we already
 * are in a View.
 * 
 */
@SuppressWarnings("unused")
public final class MyPictureView_
    extends MyPictureView
    implements HasViews, OnViewChangedListener
{

    private boolean alreadyInflated_ = false;
    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();

    public MyPictureView_(Context context) {
        super(context);
        init_();
    }

    public MyPictureView_(Context context, AttributeSet attrs) {
        super(context, attrs);
        init_();
    }

    public MyPictureView_(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init_();
    }

    public static MyPictureView build(Context context) {
        MyPictureView_ instance = new MyPictureView_(context);
        instance.onFinishInflate();
        return instance;
    }

    /**
     * The mAlreadyInflated_ hack is needed because of an Android bug
     * which leads to infinite calls of onFinishInflate()
     * when inflating a layout with a parent and using
     * the <merge /> tag.
     * 
     */
    @Override
    public void onFinishInflate() {
        if (!alreadyInflated_) {
            alreadyInflated_ = true;
            inflate(getContext(), layout.picture_view, this);
            onViewChangedNotifier_.notifyViewChanged(this);
        }
        super.onFinishInflate();
    }

    private void init_() {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        afterJcet();
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
    }

    public static MyPictureView build(Context context, AttributeSet attrs) {
        MyPictureView_ instance = new MyPictureView_(context, attrs);
        instance.onFinishInflate();
        return instance;
    }

    public static MyPictureView build(Context context, AttributeSet attrs, int defStyle) {
        MyPictureView_ instance = new MyPictureView_(context, attrs, defStyle);
        instance.onFinishInflate();
        return instance;
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        imageView = ((ImageView) hasViews.findViewById(id.imageView));
        checkBox = ((CheckBox) hasViews.findViewById(id.checkBox));
        if (checkBox!= null) {
            checkBox.setOnClickListener(new OnClickListener() {


                @Override
                public void onClick(View view) {
                    MyPictureView_.this.checkBoxClick(view);
                }

            }
            );
        }
        afterView();
    }

}
