/**
 *
 */
package com.alodiga.autorization.utils;

/**
 * @author jonathanxuya
 *
 */
public class Constants {

    public static String PASSWORD_TEST = "c6DePc/9BRd2l5iL8vlcwgKfbQCOJbvhzsmFBi+iDagfTRTOnmyNmAvw0Kn5L60yG5QMxbFNdtL4wZdqe0rAS6C1duqmznK/UJN5XVPcMh6lIRIo6eAmzQ7xAFIC0uPd79RQerN+uvWKQFWXcWEces+rlR6SRtCZ+dXqGXWobEA=";
    public static String USER_TEST = "usuarioWStest";
    public static String CANAL = "AlodigaWS";
    public static String IP_NUMBER_TEST = "192.168.3.20";
    public static Long NUMBER_ENTRY_MODE = 1L;
    public static Long CURRENCY_CODE = 484L;
    

    public static String URL_TEST = "https://10.70.10.85:8000/Autorizacion";
    public static String URL_TEST_WS = "https://10.70.10.85:8000/Autorizacion?wsdl";

    //DISPERTION
    public static Long NUMBER_TRANSACTION_TYPE_DISPERTION = 35L;
    public static Long NUMBER_SUB_TRANSACTION_TYPE_DISPERTION = 0L;
    public static String CONCEPT_TRANSFER_DISPERTION = "TRANSFERENCIA DE ABONO";
    
    //LIMIT ADVANCE
    public static Long NUMBER_TRANSACTION_TYPE_LIMIT_ADVANCE = 35L;
    public static Long NUMBER_SUB_TRANSACTION_TYPE_LIMIT_ADVANCE = 3L;
    public static String CONCEPT_TRANSFER_LIMIT_ADVANCE = "TRANSFERENCIA DE RETIRO";

    //consulta de saldo
    public static Long NUMBER_TRANSACTION_TYPE_BALANCE_INQUIRY = 129L;
    public static Long NUMBER_SUB_TRANSACTION_BALANCE_INQUIRY_WITHOUT_MOVEMENTS = 0L;
    public static Long NUMBER_SUB_TRANSACTION_BALANCE_INQUIRY_WITH_MOVEMENTS = 1L;
    
    //Reverso de dispersion
    public static Long NUMBER_TRANSACTION_TYPE_DISPERTION_REVERSE = 33L;
    public static Long NUMBER_SUB_TRANSACTION_TYPE_DISPERTION_REVERSE = 0L;
    
    //reverso de adelanto de limite
    public static Long NUMBER_TRANSACTION_TYPE_LIMIT_ADVANCE_REVERSE = 49L;
    public static Long NUMBER_SUB_TRANSACTION_TYPE_LIMIT_ADVANCE_REVERSE = 2L;
    
}
