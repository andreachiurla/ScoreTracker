public class Score {
    private String score = "";

    public Score(int ptsHome, int ptsGuest){
        if(ptsHome < 10){
            score += " ";
        }
        score += ptsHome;
        score += " - ";
        if(ptsHome < 10){
            score += " ";
        }
        score += ptsGuest;
    }

    public String toString(){
        return score;
    }
}
