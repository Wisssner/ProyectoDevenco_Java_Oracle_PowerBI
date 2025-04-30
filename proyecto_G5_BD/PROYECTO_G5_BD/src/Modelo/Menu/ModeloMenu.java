package Modelo.Menu;

import javax.swing.Icon;
import java.awt.Color; // Importa la clase Color de awt

public class ModeloMenu {
    Icon icon;
    String menuName;
    String subMenu[];
    Color customBackgroundColor; // Nuevo atributo para el color personalizado

    public ModeloMenu(){
    }
    
    public ModeloMenu(Icon icon, String menuName, String... subMenu) {
        this.icon = icon;
        this.menuName = menuName;
        this.subMenu = subMenu;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String[] getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(String[] subMenu) {
        this.subMenu = subMenu;
    }

    public Color getCustomBackgroundColor() {
        return customBackgroundColor;
    }

    public void setCustomBackgroundColor(Color customBackgroundColor) {
        this.customBackgroundColor = customBackgroundColor;
    }
}
