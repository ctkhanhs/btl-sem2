package com.projects.prjsem2service.presentation.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum RecieptType {
    HOA_DON("Hóa Đơn"),
    VAN_CHUYEN("Vận Chuyển"),
    NHAP("Nhập"),
    XUAT("Xuất");

    private String title;


}
