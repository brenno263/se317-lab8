package se317.lab8;

public enum Operation {
    none,
    add,
    subtract,
    mutiply,
    divide,
    ;

    public String toSymbol() {
        switch (this) {
            case add:
                return "+";
            case subtract:
                return "-";
            case mutiply:
                return "*";
            case divide:
                return "/";
        }
        return "";
    }
}


