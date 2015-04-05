package com.novoda.landingstrip;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;

class Attributes {

    @ColorRes
    private static final int DEFAULT_INDICATOR_COLOUR = android.R.color.white;
    private static final int DEFAULT_INDICATOR_HEIGHT = 5;
    private static final int DEFAULT_TABS_PADDING = 0;

    private static final int MISSING_TAB_LAYOUT_ID = -1;

    @LayoutRes
    private final int tabLayoutId;

    @ColorRes
    private final int indicatorColour;

    private final int indicatorHeight;
    private final int tabsPaddingLeft;
    private final int tabsPaddingRight;

    static Attributes readAttributes(Context context, AttributeSet attrs) {
        TypedArray xml = context.obtainStyledAttributes(attrs, R.styleable.LandingStrip);

        try {
            int tabLayoutId = xml.getResourceId(R.styleable.LandingStrip_tabLayoutId, MISSING_TAB_LAYOUT_ID);

            throwIfTabLayoutIsMissing(tabLayoutId);

            int indicatorColour = xml.getResourceId(R.styleable.LandingStrip_indicatorColour, DEFAULT_INDICATOR_COLOUR);
            int indicatorHeight = xml.getDimensionPixelSize(R.styleable.LandingStrip_indicatorHeight, DEFAULT_INDICATOR_HEIGHT);
            int tabsPaddingLeft = xml.getDimensionPixelSize(R.styleable.LandingStrip_tabsLeftPadding, DEFAULT_TABS_PADDING);
            int tabsPaddingRight = xml.getDimensionPixelSize(R.styleable.LandingStrip_tabsRightPadding, DEFAULT_TABS_PADDING);

            return new Attributes(tabLayoutId, indicatorColour, indicatorHeight, tabsPaddingLeft, tabsPaddingRight);
        } finally {
            xml.recycle();
        }
    }

    private static void throwIfTabLayoutIsMissing(int tabLayoutId) {
        if (tabLayoutId != MISSING_TAB_LAYOUT_ID) {
            return;
        }
        throw new MissingTabLayoutId();
    }

    Attributes(@LayoutRes int tabLayoutId, @ColorRes int indicatorColour, int indicatorHeight, int tabsPaddingLeft, int tabsPaddingRight) {
        this.tabLayoutId = tabLayoutId;
        this.indicatorColour = indicatorColour;
        this.indicatorHeight = indicatorHeight;
        this.tabsPaddingLeft = tabsPaddingLeft;
        this.tabsPaddingRight = tabsPaddingRight;
    }

    public int getTabLayoutId() {
        return tabLayoutId;
    }

    public int getIndicatorColour() {
        return indicatorColour;
    }

    public int getIndicatorHeight() {
        return indicatorHeight;
    }

    public int getTabsPaddingLeft() {
        return tabsPaddingLeft;
    }

    public int getTabsPaddingRight() {
        return tabsPaddingRight;
    }

    static class MissingTabLayoutId extends RuntimeException {
        MissingTabLayoutId() {
            super("No tabLayoutId has been set, you need to set one!");
        }
    }
}