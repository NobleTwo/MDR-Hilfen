package wettbewerbsrechner;
public class Wettbewerbsdatenbank{
  public boolean[][] disziplinen;
  public int anzahlWettbewerbsdisziplinen;
  public Wettbewerbspferd pferdchen;

    
  public Wettbewerbsdatenbank(){
    pferdchen = new Wettbewerbspferd();
    anzahlWettbewerbsdisziplinen=6;
    disziplinen = new boolean[anzahlWettbewerbsdisziplinen][pferdchen.anzahlPferdewerte];
    
    disziplinen[0][0]=true;
    disziplinen[0][5]=true;                                                             //zählende Eigenschaften Dressur
    disziplinen[0][6]=true;
    disziplinen[0][7]=true;
    disziplinen[0][9]=true;
    disziplinen[0][11]=true;
    disziplinen[0][13]=true;
    disziplinen[0][16]=true;
    
    disziplinen[1][0]=true;
    disziplinen[1][1]=true;                                                          //zählende Eigenschaften Vielseitigkeit
    disziplinen[1][5]=true;
    disziplinen[1][8]=true;
    disziplinen[1][9]=true;
    disziplinen[1][12]=true;
    disziplinen[1][13]=true;
    disziplinen[1][15]=true;
    
    disziplinen[2][1]=true;                                                            //zählende Eigenschaften Springen
    disziplinen[2][6]=true;
    disziplinen[2][7]=true;
    disziplinen[2][8]=true;
    disziplinen[2][9]=true;
    disziplinen[2][10]=true;
    disziplinen[2][12]=true;
    disziplinen[2][18]=true;
    
    disziplinen[3][2]=true;
    disziplinen[3][6]=true;                                                            //zählende Eigenschaften Western
    disziplinen[3][7]=true;
    disziplinen[3][8]=true;
    disziplinen[3][9]=true;
    disziplinen[3][14]=true;
    disziplinen[3][15]=true;
    disziplinen[3][16]=true;
    
    disziplinen[4][3]=true;
    disziplinen[4][5]=true;                                                             //zählende Eigenschaften Rennen
    disziplinen[4][7]=true;
    disziplinen[4][8]=true;
    disziplinen[4][9]=true;
    disziplinen[4][10]=true;
    disziplinen[4][12]=true;
    disziplinen[4][17]=true;
    
    disziplinen[5][4]=true;                                                             //zählende Eigenschaften Fahren
    disziplinen[5][5]=true;
    disziplinen[5][6]=true;
    disziplinen[5][7]=true;
    disziplinen[5][9]=true;
    disziplinen[5][11]=true;
    disziplinen[5][14]=true;
    disziplinen[5][19]=true;
  }
}