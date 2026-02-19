package lk.ijse.nrlbag.entity;

public class Material {

    private int material_id;
    private String  name;
    private String unit;
    private double qty_available;
    private int supplier_id;

    public Material() {
    }

    public Material(int material_id, String name, String unit, double qty_available, int supplier_id) {
        this.material_id = material_id;
        this.name = name;
        this.unit = unit;
        this.qty_available = qty_available;
        this.supplier_id = supplier_id;
    }

    public Material(String name, String unit, double qty_available, int supplier_id) {
        this.name = name;
        this.unit = unit;
        this.qty_available = qty_available;
        this.supplier_id = supplier_id;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getQty_available() {
        return qty_available;
    }

    public void setQty_available(double qty_available) {
        this.qty_available = qty_available;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }
}
