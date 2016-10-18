package com.leo.lu.llrecyclerview;

/**
 * @author Created by LuLeo on 2016/8/24.
 *         you can contact me at :361769045@qq.com
 * @since 2016/8/24.
 * <p>
 * Constants that indicates the scroll state of the Scrollable widgets.
 */

public enum ObservableScrollState {

    /**
     * Widget is stopped.
     * This state does not always mean that this widget have never been scrolled.
     */
    STOP,

    /**
     * Widget is scrolled up by swiping it down.
     */
    UP,

    /**
     * Widget is scrolled down by swiping it up.
     */
    DOWN,
}
