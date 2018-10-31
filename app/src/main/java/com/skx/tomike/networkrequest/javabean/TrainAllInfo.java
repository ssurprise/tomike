package com.skx.tomike.networkrequest.javabean;

import java.util.List;

/**
 * Created by shiguotao on 2017/6/20.
 */
public class TrainAllInfo {
    public TrainInfo train_info;
    public List<StationInfo> station_list;

    public static class TrainInfo {
        // 列车名称
        public String name;
        // 列车起点站
        public String start;
        // 列车终点站
        public String end;
        // 列车发车时间
        public String starttime;
        // 列车达到时间
        public String endtime;
        // 里程
        public String mileage;
    }

    public static class StationInfo {
        public String train_id;
        public String station_name;
        public String arrived_time;
        public String leave_time;
        public String mileage;
        public String fsoftSeat;
        public String ssoftSeat;
        public String hardSead;
        public String softSeat;
        public String hardSleep;
        public String softSleep;
        public String wuzuo;
        public String swz;
        public String tdz;
        public String gjrw;
        public String stay;
    }

}
