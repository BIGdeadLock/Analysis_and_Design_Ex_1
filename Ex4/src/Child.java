public class Child {
    String ID;
    int Height;
    int Weight;
    int Age;
    EBracelet eBracelet;
    Guardian guardian;
    ETicket eTicket;

    public Child(String ID, int age, Guardian guardian) {
        this.ID = ID;
        Age = age;
        setGuardian(guardian);
    }

    //GET
    public int getHeight() { return Height; }
    public int getWeight() { return Weight; }
    public String getID() { return ID; }
    public int getAge() { return Age; }
    public EBracelet geteBracelet() { return eBracelet; }
    public Guardian getGuardian() { return guardian; }
    public ETicket geteTicket() { return eTicket; }

    //SET
    public void setHeight(int height) { Height = height; }
    public void setWeight(int weight) { Weight = weight; }
    public void setID(String ID) { this.ID = ID; }
    public void setAge(int age) { Age = age; }
    public void seteBracelet(EBracelet eBracelet) {
        if (eBracelet==null || this.eBracelet!=null)
            return;
        if (eBracelet.getChild()!=this)
            if (eBracelet.getChild() == null) {
                this.eBracelet = eBracelet;
                eBracelet.setChild(this);
            }
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
        if (eTicket.getChild()!=this)
            if (eTicket.getChild() == null) {
                this.eTicket = eTicket;
                eTicket.setChild(this);
            }
    }


    public void Delete() {
        this.eBracelet.Delete();
        this.eBracelet = null;
        this.guardian = null;
    }
}

