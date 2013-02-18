/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

/**
 *
 * @author fer
 */

public class Cuit implements java.io.Serializable{
    private static int dniStc;
    private static int xyStc;
    private static int digitoStc;

    public Cuit() {
    }

    public String generar(int dniInt, char xyChar) {
        if (xyChar == 'F' || xyChar == 'f') {
            xyStc = 27;
        } else if (xyChar == 'M' || xyChar == 'm') {
            xyStc = 20;
        } else {
            xyStc = 30;
        }
        dniStc = dniInt;
        calcular();
        return formatear();
    }

    public void calcular() {
        long tmp1, tmp2;
        long acum = 0;
        int n = 2;
        tmp1 = xyStc * 100000000L + dniStc;

        for (int i = 0; i < 10; i++) {
            tmp2 = tmp1 / 10;
            acum += (tmp1 - tmp2 * 10L) * n;
            tmp1 = tmp2;
            if (n < 7) {
                n++;
            } else {
                n = 2;
            }
        }

        n = (int) (11 - acum % 11);

        if (n == 10) {
            if (xyStc == 20 || xyStc == 27 || xyStc == 24) {
                xyStc = 23;
            } else {
                xyStc = 33;
            }
            calcular();
        } else {
            if (n == 11) {
                digitoStc = 0;
            } else {
                digitoStc = n;
            }
        }
    }
    
//    public static String generar(int dniInt, int xyInt) {
//        xyStc = xyInt;
//        dniStc = dniInt;
//        calcular();
//        return formatear();
//    }

    public boolean validar(String cuit) {
        //Verificaciones previas del formato.
        int posPrimerGuion = cuit.indexOf("-");
        if (posPrimerGuion == -1) {
            return false;
        }
        int posUltimoGuion = cuit.lastIndexOf("-");
        //Verificar que no haya solo un guiÃ³n.
        if (posUltimoGuion == posPrimerGuion) {
            return false;
        }
        //Verificar que no haya un guiÃ³n al final.
        if (cuit.lastIndexOf("-") == (cuit.length() - 1)) {
            return false;
        }
        String xyStr, dniStr, digitoStr;
        int digitoTmp;
        int n = cuit.lastIndexOf("-");
        xyStr = cuit.substring(0, 2);
        dniStr = cuit.substring(cuit.indexOf("-") + 1, n);
        digitoStr = cuit.substring(n + 1, n + 2);

        if (xyStr.length() != 2 || dniStr.length() > 8 || digitoStr.length() != 1) {
            return false;
        }
        try {
            xyStc = Integer.parseInt(xyStr);
            dniStc = Integer.parseInt(dniStr);
            digitoTmp = Integer.parseInt(digitoStr);
        } catch (NumberFormatException e) {
            return false;
        }
        if (xyStc != 20 && xyStc != 23 && xyStc != 24 && xyStc != 27 && xyStc != 30 && xyStc != 33 && xyStc != 34) {
            return false;
        }
        calcular();
        if (digitoStc == digitoTmp && xyStc == Integer.parseInt(xyStr)) {
            return true;
        }
        return false;
    }
    
    private String formatear() {
        return String.valueOf(xyStc) + "-" + completar(String.valueOf(dniStc))
                + "-" + String.valueOf(digitoStc);
    }
    
    private String completar(String dniStr) {
        int n = dniStr.length();

        while (n < 8) {
            dniStr = "0" + dniStr;
            n = dniStr.length();
        }

        return dniStr;
    } 
}
