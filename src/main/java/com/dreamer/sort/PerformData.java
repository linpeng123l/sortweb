package com.dreamer.sort;

import java.util.List;

/**
 * Created by hws on 16/8/26.
 */
public class PerformData {

    private int[] xAxis;
    private List<AlgorithmPerform> algorithmPerforms;


    public void setAlgorithmPerforms(List<AlgorithmPerform> algorithmPerforms) {
        this.algorithmPerforms = algorithmPerforms;
    }

    public List<AlgorithmPerform> getAlgorithmPerforms() {
        return algorithmPerforms;
    }

    public void setxAxis(int[] xAxis) {
        this.xAxis = xAxis;
    }

    public int[] getxAxis() {
        return xAxis;
    }

    static class AlgorithmPerform {
        private String name;
        private int[] datas;

        public void setDatas(int[] datas) {
            this.datas = datas;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int[] getDatas() {
            return datas;
        }

        public String getName() {
            return name;
        }
    }
}
