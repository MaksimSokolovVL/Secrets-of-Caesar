import java.util.ArrayList;

public class ExceptionWords {
private final ArrayList<String> wordsList = new ArrayList<>();

public ExceptionWords(){
    wordsList.add(", а ");
    wordsList.add(" на ");
    wordsList.add(", как ");
    wordsList.add(" для ");
    wordsList.add(", но ");
    wordsList.add(" не ");
    wordsList.add(" все ");
    wordsList.add(" всё ");
    wordsList.add(" вы ");
    wordsList.add(" дуб ");

}

    public ArrayList<String> getWords() {
        return wordsList;
    }

    public int getSize() {
        return wordsList.size();
    }


}
