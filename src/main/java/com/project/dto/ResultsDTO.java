package com.project.dto;

import java.math.BigDecimal;

/**
 * Created by abdullah.alnoman on 28.08.17.
 */
public class ResultsDTO {


    private BigDecimal sum;

    private BigDecimal avg;

    private double max;

    private double min;

    private long count;


    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }


    @Override
    public String toString() {
        return "ResultsDTO{" +
                "sum=" + sum +
                ", avg=" + avg +
                ", max=" + max +
                ", min=" + min +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultsDTO)) return false;

        ResultsDTO that = (ResultsDTO) o;

        if (Double.compare(that.getMax(), getMax()) != 0) return false;
        if (Double.compare(that.getMin(), getMin()) != 0) return false;
        if (getCount() != that.getCount()) return false;
        if (getSum() != null ? !getSum().equals(that.getSum()) : that.getSum() != null) return false;
        return getAvg() != null ? getAvg().equals(that.getAvg()) : that.getAvg() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getSum() != null ? getSum().hashCode() : 0;
        result = 31 * result + (getAvg() != null ? getAvg().hashCode() : 0);
        temp = Double.doubleToLongBits(getMax());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getMin());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (getCount() ^ (getCount() >>> 32));
        return result;
    }
}
