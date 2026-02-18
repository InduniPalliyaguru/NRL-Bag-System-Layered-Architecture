package lk.ijse.nrlbag.dao;

import lk.ijse.nrlbag.dao.custom.impl.*;

public class DAOFactory {

    public static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? new DAOFactory() : daoFactory;
    }

    public enum DAOType {
        CUSTOMER, MATERIAL, MATERIAL_USED, ORDER_DETAIL, ORDERS, PAYMENT, PRODUCT, SUPPLIER, USER
    }

    public SuperDAO getDAO(DAOType daoType) {

        switch (daoType) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case MATERIAL:
                return new MaterialDAOImpl();
            case MATERIAL_USED:
                return new MaterialUsedDAOImpl();
            case ORDER_DETAIL:
                return new OrderDetailDAOImpl();
            case ORDERS:
                return new OrdersDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case PRODUCT:
                return new ProductDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }

    }

}
