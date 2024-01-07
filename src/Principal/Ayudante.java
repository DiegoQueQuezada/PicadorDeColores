package Principal;

import java.awt.Color;

public class Ayudante {

    public static Color[] GenerarArrayColores() {

        Color colores[] = new Color[102];

        int j = 0;
        for (int i = 3; i <= 255 && j < 100; i += 15) {
            colores[j] = new Color(255, i, 3);
            j++;
            colores[j] = new Color(i, 255, 3);
            j++;
            colores[j] = new Color(3, 255, i);
            j++;
            colores[j] = new Color(3, i, 255);
            j++;
            colores[j] = new Color(i, 3, 255);
            j++;
            colores[j] = new Color(255, 3, i);
            j++;
        }

        return colores;

    }

    

}
