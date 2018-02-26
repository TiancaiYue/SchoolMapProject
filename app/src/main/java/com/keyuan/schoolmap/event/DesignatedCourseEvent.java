package com.keyuan.schoolmap.event;

/**
 * Created by Administrator on 2017/10/20.
 */

public class DesignatedCourseEvent {
    public int positionUUid = 0;
    public boolean changeFragment = false;

    public DesignatedCourseEvent(int positionUUid, boolean changeFragment) {
        this.positionUUid = positionUUid;
        this.changeFragment = changeFragment;
    }
}
