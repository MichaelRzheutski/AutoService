package com.solvd.autoservice.carservice;

import com.solvd.autoservice.car.SparePart;

import java.util.Objects;

// SparePartsShop: Represents the name and cost of the spare part which is on sale
public final class SparePartsShop extends CarService {
    private SparePart sparePartName;

    public SparePartsShop(SparePart sparePartName) {
        this.sparePartName = sparePartName;
    }

    public SparePart getSparePartName() {
        return sparePartName;
    }

    public void setSparePartName(SparePart sparePartName) {
        this.sparePartName = sparePartName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SparePartsShop that = (SparePartsShop) o;
        return Objects.equals(sparePartName, that.sparePartName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sparePartName);
    }

    @Override
    public String toString() {
        return "SparePartsShop{" +
                "sparePartName=" + sparePartName +
                '}';
    }
}
