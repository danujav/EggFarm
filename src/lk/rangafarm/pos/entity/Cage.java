package lk.rangafarm.pos.entity;

public class Cage implements SuperEntity{
    private String cageId;
    private int noOfHens;
    private String qtyOnFood;
    private String qtyOnVitamin;

    public Cage() {
    }

    public Cage(String cageId, int noOfHens, String qtyOnFood, String qtyOnVitamin) {
        this.setCageId(cageId);
        this.setNoOfHens(noOfHens);
        this.setQtyOnFood(qtyOnFood);
        this.setQtyOnVitamin(qtyOnVitamin);
    }

    public String getCageId() {
        return cageId;
    }

    public void setCageId(String cageId) {
        this.cageId = cageId;
    }

    public int getNoOfHens() {
        return noOfHens;
    }

    public void setNoOfHens(int noOfHens) {
        this.noOfHens = noOfHens;
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
        return "Cage{" +
                "cageId='" + cageId + '\'' +
                ", noOfHens=" + noOfHens +
                ", qtyOnFood='" + qtyOnFood + '\'' +
                ", qtyOnVitamin='" + qtyOnVitamin + '\'' +
                '}';
    }
}
