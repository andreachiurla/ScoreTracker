public class Score {
    private String score = "";

    public Score(int ptsHome, int ptsGuest){
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
