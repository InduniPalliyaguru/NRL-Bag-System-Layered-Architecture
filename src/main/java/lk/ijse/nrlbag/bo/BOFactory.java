package lk.ijse.nrlbag.bo;

import lk.ijse.nrlbag.bo.custom.SuperBO;
import lk.ijse.nrlbag.bo.custom.impl.ChangePasswordBOImpl;
import lk.ijse.nrlbag.bo.custom.impl.CustomerBOImpl;
import lk.ijse.nrlbag.bo.custom.impl.CustomerPopUpBOImpl;

public class BOFactory {

    public static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        return boFactory == null ? new BOFactory() : boFactory;
    }

    public enum BOTypy {
        CUSTOMER , CUSTOMER_POP, CHANGE_PASSWORD
    }

    public SuperBO getBO(BOTypy boTypy) {
        switch(boTypy) {
            case CUSTOMER:
                return new CustomerBOImpl();

            case CUSTOMER_POP:
                return new CustomerPopUpBOImpl();
            case CHANGE_PASSWORD:
                return new ChangePasswordBOImpl();
            default:
                return null;
        }
    }
}
