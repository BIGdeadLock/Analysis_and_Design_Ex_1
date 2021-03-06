public class Child {
    String ID;
    int SystemID;
    double Height;
    double Weight;
    int Age;
    String name;
    EBracelet eBracelet;
    Guardian guardian;
    ETicket eTicket;

    public Child(String ID, int age,String name, Guardian guardian) {
        this.ID = ID;
        Age = age;
        this.name = name;
        setGuardian(guardian);
    }

    //GET
    public double getHeight() { return Height; }
    public double getWeight() { return Weight; }
    public String getID() { return ID; }
    public int getAge() { return Age; }
    public EBracelet geteBracelet() { return eBracelet; }
    public Guardian getGuardian() { return guardian; }
    public ETicket geteTicket() { return eTicket; }
    public int getSystemID() { return SystemID; }

    public String getName() {
        return name;
    }

    //SET
    public void setHeight(double height) { Height = height; }
    public void setWeight(double weight) { Weight = weight; }
    public void setID(String ID) { this.ID = ID; }
    public void setAge(int age) { Age = age; }
    public void setSystemID(int systemID) { SystemID = systemID;}
    public void seteBracelet(EBracelet eBracelet) {
        if (eBracelet==null || this.eBracelet!=null)
            return;
        if (eBracelet.getChild()!=this)
            if (eBracelet.getChild() == null) {
                this.eBracelet = eBracelet;
                eBracelet.setChild(this);
            }
        else
            this.eTicket = eTicket;
    }
    public void setGuardian(Guardian guardian) {
        if (guardian==null || this.guardian!=null)
            return;
        guardian.addChild(this);
        this.guardian = guardian;
    }

    public void seteTicket(ETicket eTicket) {
        if (eTicket==null || this.eTicket!=null)
            return;
        if (eTicket.getChild()!=this) {
            if (eTicket.getChild() == null) {
                this.eTicket = eTicket;
                eTicket.setChild(this);
            }
        }
        else
            this.eTicket = eTicket;

    }


    public void Delete() {
        if(this.eBracelet != null) {
            this.eBracelet.Delete();
            this.eBracelet = null;
        }
        if (this.guardian!=null) {
            this.guardian.DeleteChild(this.getID());
            this.guardian = null;
        }
        Main.systemObjects.remove(this);
    }

    public int getSystemId() {
        return this.SystemID;
    }
}

