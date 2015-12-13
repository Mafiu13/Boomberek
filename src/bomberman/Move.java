/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

/**
 *
 * @author Pawel
 */
public enum Move {
    down, up, left, right, bomb;

    public static Move CnvIntEnum(int x) {

        switch (x) {
            case 1:
                return down;
            case 2:
                return up;
            case 3:
                return left;
            case 4:
                return right;
            case 5:
                return bomb;
        }
        return null;
    }

    public static Move CnvIntEnumI(int x) {

        switch (x) {
            case 11:
                return down;
            case 12:
                return up;
            case 13:
                return left;
            case 14:
                return right;
            case 15:
                return bomb;
            case 21:
                return down;
            case 22:
                return up;
            case 23:
                return left;
            case 24:
                return right;
            case 25:
                return bomb;
        }
        return null;
    }

}
