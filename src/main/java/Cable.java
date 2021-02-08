
class Cable {

    private String profile;
    private String material;
    private float distance;
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
                        break;
                    case "2.5":
                        rActiv = 7.4;
                        xReactive = 0.116;
                        zLoop = 17.46;
                        break;
                    case "4":
                        rActiv = 4.63;
                        xReactive = 0.106;
                        zLoop = 10.94;
                        break;
                    case "6":
                        rActiv = 3.09;
                        xReactive = 0.1;
                        zLoop = 7.28;
                        break;
                    case "10":
                        rActiv = 1.84;
                        xReactive = 0.099;
                        zLoop = 4.34;
                        break;
                    case "16":
                        rActiv = 1.16;
                        xReactive = 0.095;
                        zLoop = 2.74;
                        break;
                    case "25":
                        rActiv = 0.74;
                        xReactive = 0.091;
                        zLoop = 1.746;
                        break;
                    case "35":
                        rActiv = 0.53;
                        xReactive = 0.088;
                        zLoop = 1.25;
                        break;
                    case "50":
                        rActiv = 0.37;
                        xReactive = 0.085;
                        zLoop = 0.872;
                        break;
                    case "70":
                        rActiv = 0.265;
                        xReactive = 0.082;
                        zLoop = 0.626;
                        break;
                    case "95":
                        rActiv = 0.195;
                        xReactive = 0.081;
                        zLoop = 0.46;
                        break;
                    case "120":
                        rActiv = 0.154;
                        xReactive = 0.08;
                        zLoop = 0.362;
                        break;
                    case "150":
                        rActiv = 0.124;
                        xReactive = 0.079;
                        zLoop = 0.292;
                        break;
                    case "185":
                        rActiv = 0.1;
                        xReactive = 0.078;
                        zLoop = 0.244;
                        break;
                    case "240":
                        rActiv = 0.077;
                        xReactive = 0.077;
                        zLoop = 0.18;
                        break;
                }
        }
            else {
                switch (profile) {
                    case "2.5":
                        rActiv = 12.5;
                        xReactive = 0.116;
                        zLoop = 29.5;
                        break;
                    case "4":
                        rActiv = 7.81;
                        xReactive = 0.107;
                        zLoop = 18.4;
                        break;
                    case "6":
                        rActiv = 5.21;
                        xReactive = 0.1;
                        zLoop = 12.3;
                        break;
                    case "10":
                        rActiv = 3.12;
                        xReactive = 0.099;
                        zLoop = 7.36;
                        break;
                    case "16":
                        rActiv = 1.95;
                        xReactive = 0.095;
                        zLoop = 4.6;
                        break;
                    case "25":
                        rActiv = 1.25;
                        xReactive = 0.091;
                        zLoop = 2.94;
                        break;
                    case "35":
                        rActiv = 0.894;
                        xReactive = 0.088;
                        zLoop = 2.1;
                        break;
                    case "50":
                        rActiv = 0.625;
                        xReactive = 0.085;
                        zLoop = 1.48;
                        break;
                    case "70":
                        rActiv = 0.447;
                        xReactive = 0.082;
                        zLoop = 1.054;
                        break;
                    case "95":
                        rActiv = 0.329;
                        xReactive = 0.081;
                        zLoop = 0.776;
                        break;
                    case "120":
                        rActiv = 0.261;
                        xReactive = 0.08;
                        zLoop = 0.616;
                        break;
                    case "150":
                        rActiv = 0.208;
                        xReactive = 0.079;
                        zLoop = 0.492;
                        break;
                    case "185":
                        rActiv = 0.169;
                        xReactive = 0.078;
                        zLoop = 0.4;
                        break;
                    case "240":
                        rActiv = 0.13;
                        xReactive = 0.077;
                        zLoop = 0.306;
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
}
