public class Synonym {
    private String word;
    private String[] language = new String[]{};
    private String[] synonyms = new String[]{};

    public Synonym(String word, String[] language, String[] synonyms){
        this.synonyms = synonyms;
        this.word = word;
        this.language = language;
    }
    
}
