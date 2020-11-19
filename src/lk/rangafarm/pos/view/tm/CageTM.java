package lk.rangafarm.pos.view.tm;

import lk.rangafarm.pos.entity.SuperEntity;

public class CageTM implements SuperEntity {
    private String cageId;
        private int numOfHens;
    private String qtyOnFood;
    private String qtyOnVitamin;

    public CageTM() {
    }

    public CageTM(String cageId, int numOfHens, String qtyOnFood, String qtyOnVitamin) {
        this.setCageId(cageId);
        this.setNumOfHens(numOfHens);
        this.setQtyOnFood(qtyOnFood);
        this.setQtyOnVitamin(qtyOnVitamin);
    }

    public String getCageId() {
        return cageId;
    }

    public void setCageId(String cageId) {
        this.cageId = cageId;
    }

    public int getNumOfHens() {
        return numOfHens;
    }

    public void setNumOfHens(int numOfHens) {
        this.numOfHens = numOfHens;
    }

    public String getQtyOnFood() {
        return qtyOnFood;
    }

    public void setQtyOnFood(String qtyOnFood) {
        this.qtyOnFood = qtyOnFood;
    }

    public String getQtyOnVitamin() {
        return qtyOnVitamin;
    }

    public void setQtyOnVitamin(String qtyOnVitamin) {
        this.qtyOnVitamin = qtyOnVitamin;
    }

    @Override
    public String toString() {
        return "CageTM{" +
                "cageId='" + cageId + '\'' +
                ", numOfHens=" + numOfHens +
                ", qtyOnFood='" + qtyOnFood + '\'' +
                ", qtyOnVitamin='" + qtyOnVitamin + '\'' +
                '}';
    }
}
