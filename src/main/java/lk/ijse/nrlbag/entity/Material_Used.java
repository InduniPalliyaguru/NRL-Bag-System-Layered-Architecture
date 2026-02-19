package lk.ijse.nrlbag.entity;

public class Material_Used {

    private int order_id;
    private int material_id;
    private double used_qty;

    public Material_Used() {
    }

    public Material_Used(int order_id, int material_id, double used_qty) {
        this.order_id = order_id;
        this.material_id = material_id;
        this.used_qty = used_qty;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public double getUsed_qty() {
        return used_qty;
    }

    public void setUsed_qty(double used_qty) {
        this.used_qty = used_qty;
    }
}
