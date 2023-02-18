package com.projects.prjsem2service.presentation.util;

public interface Constants {
    String STATUS_ACTIVE = "ACTIVE";
    String STATUS_SALED= "SALED";
    String STATUS_UNACTIVE = "UNACTIVE";
    String STATUS_NEW = "NEW";

    String CONSIGNMENT_CODE="LO-HANG";

    //error code
    String ERROR_CODE_SUCCESS = "SUCCESS";
    String ERROR_CODE_FAILED = "FAILED";

    //Excel cell
    int CELL_PRODUCT_CODE = 0;
    int CELL_PRODUCT_NAME = 1;
    int CELL_CATEGORY_CODE = 2;
    int CELL_CATEGORY_NAME = 3;
    int CELL_BRAND = 4;
    int CELL_MADE_IN = 5;
    int CELL_SALE_PRICE = 6;
    int CELL_IMPORT_PRICE = 7;
    int CELL_MUNBER_PRODUCTS_IN_BOX = 8;
    int CELL_NUMBER_OF_BOXES = 9;
    int CELL_NSX = 10;
    int CELL_HSD = 11;

    String TRUE ="TRUE";
    String FALSE ="FALSE";


    String NAME_DUPLICATE_DESC = "Tên đã được sử dụng";
    String CODE_DUPLICATE_DESC = "Mã đã được sử dụng";

    String NAME_VALID_DESC="Tên hợp lệ";
    String CODE_VALID_DESC="Mã hợp lệ";
}
