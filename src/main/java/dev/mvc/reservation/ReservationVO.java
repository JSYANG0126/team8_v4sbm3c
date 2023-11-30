package dev.mvc.reservation;

public class ReservationVO {
  private int reservationno;
  private String tname;
  private String link;
  
  public int getReservationno() {
    return reservationno;
  }
  public void setReservationno(int reservationno) {
    this.reservationno = reservationno;
  }
  public String getTname() {
    return tname;
  }
  public void setTname(String tname) {
    this.tname = tname;
  }
  public String getLink() {
    return link;
  }
  public void setLink(String link) {
    this.link = link;
  }
  @Override
  public String toString() {
    return "ReservationVO [reservationno=" + reservationno + ", tname=" + tname + ", link=" + link + "]";
  }
  
}
