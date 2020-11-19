package lk.rangafarm.pos.dao;

import lk.rangafarm.pos.entity.Attendance;
import lk.rangafarm.pos.entity.EggBucket;
import lk.rangafarm.pos.entity.LineChart;
import lk.rangafarm.pos.entity.Product;

import java.util.ArrayList;
import java.util.List;

public interface QueryDao extends SuperDao{
    public ArrayList<Integer> setLineChart() throws Exception;
}
