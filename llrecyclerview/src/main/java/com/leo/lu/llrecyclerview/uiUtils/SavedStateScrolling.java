package com.leo.lu.llrecyclerview.uiUtils;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;

/**
 * @author Created by LuLeo on 2016/8/24.
 *         you can contact me at :361769045@qq.com
 * @since 2016/8/24.
 */

public class SavedStateScrolling implements Parcelable {

    public static final SavedStateScrolling EMPTY_STATE = new SavedStateScrolling() {
    };

    public int prevFirstVisiblePosition;
    public int prevFirstVisibleChildHeight = -1;
    public int prevScrolledChildrenHeight;
    public int prevScrollY;
    public int scrollY;
    public SparseIntArray childrenHeights;

    // This keeps the parent(RecyclerView)'s state
    public Parcelable superState;

    /**
     * Called by EMPTY_STATE instantiation.
     */
    public SavedStateScrolling() {
        superState = null;
    }

    /**
     * Called by onSaveInstanceState.
     *
     * @param superState Parcelable
     */
    public SavedStateScrolling(Parcelable superState) {
        this.superState = superState != EMPTY_STATE ? superState : null;
    }

    /**
     * Called by CREATOR.
     *
     * @param in na
     */
    public SavedStateScrolling(Parcel in) {
        // Parcel 'in' has its parent(RecyclerView)'s saved state.
        // To restore it, class loader that loaded RecyclerView is required.
        Parcelable superState = in.readParcelable(RecyclerView.class.getClassLoader());
        this.superState = superState != null ? superState : EMPTY_STATE;

        prevFirstVisiblePosition = in.readInt();
        prevFirstVisibleChildHeight = in.readInt();
        prevScrolledChildrenHeight = in.readInt();
        prevScrollY = in.readInt();
        scrollY = in.readInt();
        childrenHeights = new SparseIntArray();
        final int numOfChildren = in.readInt();
        if (0 < numOfChildren) {
            for (int i = 0; i < numOfChildren; i++) {
                final int key = in.readInt();
                final int value = in.readInt();
                childrenHeights.put(key, value);
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(superState, flags);

        out.writeInt(prevFirstVisiblePosition);
        out.writeInt(prevFirstVisibleChildHeight);
        out.writeInt(prevScrolledChildrenHeight);
        out.writeInt(prevScrollY);
        out.writeInt(scrollY);
        final int numOfChildren = childrenHeights == null ? 0 : childrenHeights.size();
        out.writeInt(numOfChildren);
        if (0 < numOfChildren) {
            for (int i = 0; i < numOfChildren; i++) {
                out.writeInt(childrenHeights.keyAt(i));
                out.writeInt(childrenHeights.valueAt(i));
            }
        }
    }

    public Parcelable getSuperState() {
        return superState;
    }

    public static final Creator<SavedStateScrolling> CREATOR
            = new Creator<SavedStateScrolling>() {
        @Override
        public SavedStateScrolling createFromParcel(Parcel in) {
            return new SavedStateScrolling(in);
        }

        @Override
        public SavedStateScrolling[] newArray(int size) {
            return new SavedStateScrolling[size];
        }
    };

}
