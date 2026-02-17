package lk.ijse.nrlbag.bo;

import lk.ijse.nrlbag.bo.custom.SuperBO;
import lk.ijse.nrlbag.bo.custom.impl.CustomerBOImpl;

public class BOFactory {

    public static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        return boFactory == null ? new BOFactory() : boFactory;
    }

    public enum BOTypy {
        CUSTOMER
    }

    public SuperBO getBO(BOTypy boTypy) {
        switch(boTypy) {
            case CUSTOMER:
                return new CustomerBOImpl();
            default:
                return null;
        }
    }
}
