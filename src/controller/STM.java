package controller;

import javax.swing.ImageIcon;
import model.DB.ConnectionDB;
import view.LoginView;

public class STM {

    private static STM instance = null;

    private final ImageIcon ICONSTMLOGO16 = new ImageIcon(getClass().getResource("/view/img/logo16x16.png"));
    private final ImageIcon ICONSTMLOGO24 = new ImageIcon(getClass().getResource("/view/img/logo24x24.png"));
    private final ImageIcon ICONSTMLOGO32 = new ImageIcon(getClass().getResource("/view/img/logo32x32.png"));
    private final ImageIcon ICONSTMLOGO64 = new ImageIcon(getClass().getResource("/view/img/logo64x64.png"));
    private final ImageIcon ICONSTMLOGO128 = new ImageIcon(getClass().getResource("/view/img/logo128x128.png"));
    private final ImageIcon ICONSTMLOGO500 = new ImageIcon(getClass().getResource("/view/img/logo500x500.png"));

    public static void main(String[] args) {
        ConnectionDB.getInstance();
        new LoginView().setVisible(true);
    }

    public static STM getInstance() {
        if (instance == null) {
            instance = new STM();
        }
        return instance;
    }

    public ImageIcon getIcon(int sizeImage) {

        ImageIcon icon = null;
        switch (sizeImage) {
            case 16:
                icon = ICONSTMLOGO16;
                break;
            case 24:
                icon = ICONSTMLOGO24;
                break;
            case 32:
                icon = ICONSTMLOGO32;
                break;
            case 64:
                icon = ICONSTMLOGO64;
                break;
            case 128:
                icon = ICONSTMLOGO128;
                break;
            case 500:
                icon = ICONSTMLOGO500;
                break;
            default:
                icon = ICONSTMLOGO128;
        }
        return icon;

    }

}
