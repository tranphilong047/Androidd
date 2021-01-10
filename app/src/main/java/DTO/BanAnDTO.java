package DTO;

public class BanAnDTO {
    int MABAN;
    String TENBAN;
    boolean Chon;

    public boolean isChon() {
        return Chon;
    }

    public void setChon(boolean chon) {
        Chon = chon;
    }

    public int getMABAN() {
        return MABAN;
    }

    public void setMABAN(int MABAN) {
        this.MABAN = MABAN;
    }

    public String getTENBAN() {
        return TENBAN;
    }

    public void setTENBAN(String TENBAN) {
        this.TENBAN = TENBAN;
    }
}
