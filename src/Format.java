public class Format {
    private String score = "";

    public Format(int ptsHome, int ptsGuest){
        if(ptsHome < 10){
            score += "  ";
        }
        score += ptsHome;
        score += " - ";
        score += ptsGuest;
        if(ptsGuest < 10){
            score += " ";
        }
    }

    public String toString(){
        return score;
    }
}
