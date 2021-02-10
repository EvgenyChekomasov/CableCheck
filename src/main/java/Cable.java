
class Cable {

    private String profile;
    private String material;
    private int number;
    private float distance;
    private double longCurrent;
    private double rActiv;
    private double xReactive;
    private double zLoop;

    //получение материала жилы
    void setMaterial(String m) {
        material = m;
    }

    public String getMaterial() { return material; }

    //получение сечения жилы
    void setProfile(String s) {
        profile = s;
    }

    public String getProfile() { return profile; }

    // количество параллельных кабелей
    void setNumber (int num) {
        number = num;
    }

    public int getNumber() {
        return number;
    }


    public void setDistance(float distance) {
        this.distance = distance;
    }

    // Выбор параметров сопротивления в зависимости от материала и сечения жил
    void choiceResistance() {

        if(material.equals("Cu")) {
                switch (profile) {
                    case "1.5":
                        rActiv = 12.5;
                        xReactive = 0.126;
                        zLoop = 29.1;
                        longCurrent = 19;
                        break;
                    case "2.5":
                        rActiv = 7.4;
                        xReactive = 0.116;
                        zLoop = 17.46;
                        longCurrent = 25;
                        break;
                    case "4":
                        rActiv = 4.63;
                        xReactive = 0.106;
                        zLoop = 10.94;
                        longCurrent = 35;
                        break;
                    case "6":
                        rActiv = 3.09;
                        xReactive = 0.1;
                        zLoop = 7.28;
                        longCurrent = 42;
                        break;
                    case "10":
                        rActiv = 1.84;
                        xReactive = 0.099;
                        zLoop = 4.34;
                        longCurrent = 55;
                        break;
                    case "16":
                        rActiv = 1.16;
                        xReactive = 0.095;
                        zLoop = 2.74;
                        longCurrent = 75;
                        break;
                    case "25":
                        rActiv = 0.74;
                        xReactive = 0.091;
                        zLoop = 1.746;
                        longCurrent = 95;
                        break;
                    case "35":
                        rActiv = 0.53;
                        xReactive = 0.088;
                        zLoop = 1.25;
                        longCurrent = 120;
                        break;
                    case "50":
                        rActiv = 0.37;
                        xReactive = 0.085;
                        zLoop = 0.872;
                        longCurrent = 145;
                        break;
                    case "70":
                        rActiv = 0.265;
                        xReactive = 0.082;
                        zLoop = 0.626;
                        longCurrent = 180;
                        break;
                    case "95":
                        rActiv = 0.195;
                        xReactive = 0.081;
                        zLoop = 0.46;
                        longCurrent = 220;
                        break;
                    case "120":
                        rActiv = 0.154;
                        xReactive = 0.08;
                        zLoop = 0.362;
                        longCurrent = 260;
                        break;
                    case "150":
                        rActiv = 0.124;
                        xReactive = 0.079;
                        zLoop = 0.292;
                        longCurrent = 305;
                        break;
                    case "185":
                        rActiv = 0.1;
                        xReactive = 0.078;
                        zLoop = 0.244;
                        longCurrent = 350;
                        break;
                    case "240":
                        rActiv = 0.077;
                        xReactive = 0.077;
                        zLoop = 0.18;
                        longCurrent = 415;
                        break;
                }
        }
            else {
                switch (profile) {
                    case "2.5":
                        rActiv = 12.5;
                        xReactive = 0.116;
                        zLoop = 29.5;
                        longCurrent = 19;
                        break;
                    case "4":
                        rActiv = 7.81;
                        xReactive = 0.107;
                        zLoop = 18.4;
                        longCurrent = 27;
                        break;
                    case "6":
                        rActiv = 5.21;
                        xReactive = 0.1;
                        zLoop = 12.3;
                        longCurrent = 32;
                        break;
                    case "10":
                        rActiv = 3.12;
                        xReactive = 0.099;
                        zLoop = 7.36;
                        longCurrent = 42;
                        break;
                    case "16":
                        rActiv = 1.95;
                        xReactive = 0.095;
                        zLoop = 4.6;
                        longCurrent = 60;
                        break;
                    case "25":
                        rActiv = 1.25;
                        xReactive = 0.091;
                        zLoop = 2.94;
                        longCurrent = 75;
                        break;
                    case "35":
                        rActiv = 0.894;
                        xReactive = 0.088;
                        zLoop = 2.1;
                        longCurrent = 90;
                        break;
                    case "50":
                        rActiv = 0.625;
                        xReactive = 0.085;
                        zLoop = 1.48;
                        longCurrent = 110;
                        break;
                    case "70":
                        rActiv = 0.447;
                        xReactive = 0.082;
                        zLoop = 1.054;
                        longCurrent = 140;
                        break;
                    case "95":
                        rActiv = 0.329;
                        xReactive = 0.081;
                        zLoop = 0.776;
                        longCurrent = 170;
                        break;
                    case "120":
                        rActiv = 0.261;
                        xReactive = 0.08;
                        zLoop = 0.616;
                        longCurrent = 200;
                        break;
                    case "150":
                        rActiv = 0.208;
                        xReactive = 0.079;
                        zLoop = 0.492;
                        longCurrent = 235;
                        break;
                    case "185":
                        rActiv = 0.169;
                        xReactive = 0.078;
                        zLoop = 0.4;
                        longCurrent = 270;
                        break;
                    case "240":
                        rActiv = 0.13;
                        xReactive = 0.077;
                        zLoop = 0.306;
                        longCurrent = 322;
                        break;
                }

        }

    }

    public float getDistance() {
        return distance;
    }

    double getrActiv() {
        return rActiv;
    }

    double getxReactive() {
        return xReactive;
    }

    double getzLoop() {
        return zLoop;
    }

    double getLongCurrent() {
        return longCurrent;
    }
}
