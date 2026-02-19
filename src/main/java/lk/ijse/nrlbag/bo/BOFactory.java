package lk.ijse.nrlbag.bo;

import lk.ijse.nrlbag.bo.custom.SuperBO;
import lk.ijse.nrlbag.bo.custom.impl.*;

public class BOFactory {

    public static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        return boFactory == null ? new BOFactory() : boFactory;
    }

    public enum BOType {
        CUSTOMER , CUSTOMER_POP, USER, DASHBOARD, ORDERS, ORDER_POP, PAYMENT, PRODUCT, MATERIAL, SUPPLIER
    }

    public SuperBO getBO(BOType boType) {
        switch(boType) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case CUSTOMER_POP:
                return new CustomerPopUpBOImpl();
            case USER:
                return new UserBOImpl();
            case DASHBOARD:
                return new DashBoardBOImpl();
            case ORDERS:
                return new OrdersBOImpl();
            case ORDER_POP:
                return new OrderPopUpBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case PRODUCT:
                return new ProductBOImpl();
            case MATERIAL:
                return new MaterialBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            default:
                return null;
        }
    }
}
