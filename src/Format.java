public class Format {
    private String score = "";
    private String teams = "";

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

    public Format(String strHome, String strGuest){
        int strDimension = 20;
        for (int i = 0; i < strDimension - strHome.length(); i++){
            teams += " ";
        }
        teams += strHome;
        teams += " - ";
        teams += strGuest;
    }

    public String toString(int command){
        if(command == 0) {
            return score;
        }else{
            return teams;
        }
    }
}
