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

    public enum BOTypy {
        CUSTOMER , CUSTOMER_POP, CHANGE_PASSWORD, DASHBOARD, LOGIN, ORDERS, ORDER_POP
    }

    public SuperBO getBO(BOTypy boTypy) {
        switch(boTypy) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case CUSTOMER_POP:
                return new CustomerPopUpBOImpl();
            case CHANGE_PASSWORD:
                return new ChangePasswordBOImpl();
            case DASHBOARD:
                return new DashBoardBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case ORDERS:
                return new OrdersBOImpl();
            case ORDER_POP:
                return new OrderPopUpBOImpl();
            default:
                return null;
        }
    }
}
